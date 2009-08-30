/*
 * 
 * 
 */

package tangram;

import gals.AnalysisError;
import gals.Compiler;
import gals.Lexico;
import gals.SemanticoLeMundo;
import gals.Sintatico;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;

import jogl.Camera;
import jogl.util.Vetor3f;
import tangram.comandos.CodeMaker;
import tangram.comandos.ModeloExecutavel;
import tangram.comandos.MundoExecutavel;
import tangram.comandos.CodeMaker.Gerador;
import tangram.gui.EditorGrafico;
import tangram.gui.EditorTextualMundo;
import tangram.gui.Msg;
import tangram.io.ReadWriteFile;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class Mundo {
    private String nomeDoMundo;
    private boolean salvar;
    private File file;
    private String codigo;

    private EditorTextualMundo editorTexto;
    private EditorGrafico editorGrafico;
    private ArrayList<Figura> figuras;
    private Camera camera;
    private MEHforMundo handler;
    
    private HashMap<String,HashMap<String,String>> metodosDoModelo;
    private HashMap<String,File> arquivosDoMundo;

    public Mundo(String nomeDoMundo) {
        this.nomeDoMundo = nomeDoMundo;
        figuras = new ArrayList<Figura>();
        metodosDoModelo = new HashMap<String, HashMap<String,String>>();
        arquivosDoMundo = new HashMap<String, File>();
        init();
    }

    public Mundo(File file) {
        this.file = file;
        nomeDoMundo = file.getName().replaceAll(".mun", "");
        if (carregaDoArquivo(file))
            init();
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
        setSalvar(true);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNomeDoMundo() {
        return nomeDoMundo;
    }
    
    public void setEditorGrafico(EditorGrafico editorGrafico) {
        this.editorGrafico = editorGrafico;
    }

    public EditorGrafico getEditorGrafico() {
        return editorGrafico;
    }

    public void setEditorTexto(EditorTextualMundo editorTexto) {
        this.editorTexto = editorTexto;
    }

    public EditorTextualMundo getEditorTexto() {
        return editorTexto;
    }

    public boolean isSalvar() {
        return salvar;
    }

    public void setSalvar(boolean salvar) {
        this.salvar = salvar;
    }
    
    public void salvar(){
        if(isSalvar())
            if(Msg.questao("Deseja Salvar ?")){
                if(file != null){
                    if(file.exists())
                        file.delete();
                } else
                    file = new File(nomeDoMundo+".mun");
                ReadWriteFile.write(file, geraCodigoMundo());
                salvar = false;
            }
    }
    
    public void salvar(File file, String novoNomeDoMundo){
        nomeDoMundo = novoNomeDoMundo;
        this.file = file;
        setSalvar(true);
        salvar();
    }
    
    public void fechar(){
        salvar();
        salvar = false;
        if(editorGrafico != null)
            editorGrafico.dispose();
        if(editorTexto != null)
            editorTexto.dispose();
    }
    
    public boolean carregaDoArquivo(File file){
        // se o arquivo não existir falha
        if(!file.exists())
            return false;
        
        // cria nova lista de figuras
        figuras = new ArrayList<Figura>();
        
        // Cria novo mapa com os métodos de cada modelo instanciado
        metodosDoModelo = new HashMap<String, HashMap<String,String>>();
        arquivosDoMundo = new HashMap<String, File>();
        
        // carrega código do arquivo
        setCodigo(ReadWriteFile.read(file));
        
        // inicializa GALS
        Lexico lexico = new Lexico(getCodigo());
        SemanticoLeMundo semantico = new SemanticoLeMundo(this);
        Sintatico sintatico = new Sintatico();
        
        // Traduz o código para criar as figuras
        try {
            sintatico.parse(lexico, semantico);
        } catch (AnalysisError ex) {
            Msg.erro(ex.getMessage());
            return false;
        }
        
        // retira mundo e fim. do código
        arrumaTextoCarregado();
        return true;
    }
    
    private void arrumaTextoCarregado(){
        codigo = codigo.replaceAll("fim.", "");
        codigo = codigo.replaceAll("mundo", "");
        codigo = codigo.replace(nomeDoMundo, "");
        codigo = codigo.trim();
    }
    
    private void init(){
        salvar = false;
        camera = new Camera(new Vetor3f());
        handler = new MEHforMundo(camera, figuras, this);
        
        // cria um cavas para o editor Grafico
        GLCapabilities caps = new GLCapabilities();
        caps.setDoubleBuffered(true);
        caps.setRedBits(8);
        caps.setGreenBits(8);
        caps.setBlueBits(8);
        caps.setAlphaBits(8);
        GLCanvas cavas = new GLCanvas(caps);

        // Adiciona o Evente que Desenha o cavas
        cavas.addGLEventListener(new Desenha(handler));
        
        editorGrafico = new EditorGrafico(cavas, handler);
        editorGrafico.setSize(640,480);
        
        editorTexto = new EditorTextualMundo(this);
        editorTexto.setVisible(true);
        
        CodeMaker codeMaker = new CodeMaker(editorTexto);
        codeMaker.setGerador(Gerador.MUNDO);
        handler.setCodeMaker(codeMaker);
        
        editorGrafico.setLocation(editorTexto.getSize().width,0);
        editorGrafico.setVisible(true);
        editorGrafico.requestFocus();
    }
    
    public boolean addModelo(String idDoModelo, File file, Vetor3f ponto){
        if(metodosDoModelo.containsKey(idDoModelo)){
            Msg.aviso("Este nome já foi usado para outro modelo!");
            return false;
        }
        if(!ModelMaker.isNomeAcaoValido(idDoModelo)){
            Msg.aviso("Nome Inválido!");
            return false;
        }
            
        HashMap<String,String> acoes;
        if(file.exists()){
            // carrega acoes apartir do arquivo
            acoes = Modelo.geraAcoesApartirDoCodigo(ReadWriteFile.read(file));
            // cria compilador
            gals.Compiler compiler = new gals.Compiler(Modelo.geraCodigoCRIA(acoes));
            // compila modelo
            try{
                compiler.compileModel();
            } catch (AnalysisError ane){
                Msg.erro(ane.getMessage());
                return false;
            }
            // pega modelo executavel compilado
            ModeloExecutavel exe = compiler.getModelo();
            // cria nova figura
            Figura novaFigura = new Figura(idDoModelo);
            // limpa peças
            novaFigura.resetPecas();
            // adiciona figura ao modelo executavel
            exe.setFigura(novaFigura);
            // executa metodo cria do modelo
            exe.execute(ModelMaker.CRIA);
            // inclui acoes no hashmap
            metodosDoModelo.put(idDoModelo, acoes);
            // inclui arquivo no hashmap
            arquivosDoMundo.put(idDoModelo, file);
            // adiciona figura a lista de figuras do mundo
            figuras.add(novaFigura);
            // move a figura para o ponto selecionado
            novaFigura.translateTo(ponto);
        } else {
            Msg.erro("O Arquivo " + file.getName() + " não exite");
            return false;
        }
        return true;
    }
    
    public Figura getFigura(String id){
        for(Figura f : figuras){
            if(f.getName().equals(id))
                return f;
        }
        return null;
    }
    
    public String geraCodigoMundo(){
        return "mundo " + nomeDoMundo + "\n" + codigo + "\n" + "fim.";
    }
    
    public MundoExecutavel Compila(){
        try{
            Compiler compiler = new Compiler(geraCodigoMundo());
            MundoExecutavel executavel = compiler.compileWorld();
            return executavel;
        }catch (AnalysisError e){
            Msg.aviso(e.getMessage());
        }
        return null;
    }
    
    public void setSelectedModel(String modelName, Figura figura){
        if( modelName == null)
            editorTexto.setSelectedModel(null, null, null, null);
        else
            if (metodosDoModelo.containsKey(modelName)){
                ArrayList modelAction = new ArrayList();
                for ( String s : metodosDoModelo.get(modelName).keySet()){
                    if ( ! s.equals(ModelMaker.CRIA))
                        modelAction.add(s);
                }
                editorTexto.setSelectedModel(modelName,
                        modelAction,
                        arquivosDoMundo.get(modelName),
                        figura);
            }
    }
}
