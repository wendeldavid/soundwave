/*
 * 
 * 
 */

package tangram;

import gals.AnalysisError;
import gals.Compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;

import jogl.Camera;
import jogl.util.Vetor3f;
import tangram.comandos.CodeMaker;
import tangram.comandos.ModeloExecutavel;
import tangram.gui.EditorGrafico;
import tangram.gui.EditorTextual;
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
public class Modelo{
    
    
    public Modelo(String nome) {
        this.nomeDoModelo = nome;
        acoes = new HashMap<String, String>();
        ModelMaker.createModel(this);
        init();
    }
    
    public Modelo(File file){
        this.file = file;
        nomeDoModelo = file.getName().replaceAll(".mod", "");
        carregaDoArquivo(file);
        // se carregou sem erro não é nulo.
        if(acoes != null)
            init();
    }
    
    private void init(){
        salvar = false;
        
        // Modelos para serem desenhados na tela
        figuras = new ArrayList();
        figuras.add(new Figura(nomeDoModelo));
        // camera e posição inicial
        camera = new Camera(new Vetor3f(0f,0f,0f));
        
        // trata os eventos do mouse
        handler = new MEHforModels(figuras, camera);
        
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
                        
        editorModelos = new EditorGrafico(cavas, handler);
        editorModelos.setSize(640, 480);
                
        // novo edito de texto
        editorTexto = new EditorTextual(this);
        
        // Gerador de Código
        codeMaker = new CodeMaker(editorTexto);
        handler.setCodeMaker(codeMaker);
        
        // adciona acoes ao editor de texto e mostra-o
        editorTexto.carregaAcoesParaTela();
        editorTexto.setVisible(true);
        
        editorModelos.setLocation(editorTexto.getSize().width,0);
        editorModelos.setVisible(true);
        editorModelos.requestFocus();
    }
    
    public void salvar(){
        if(isSalvar())
            if(Msg.questao("Deseja Salvar ?")){
                if(file != null){
                    if(file.exists())
                        file.delete();
                } else
                    file = new File(nomeDoModelo+".mod");
                guardaAcao();
                ReadWriteFile.write(file, geraCodigo(nomeDoModelo,acoes));
                salvar = false;
            }
    }
    
    public void salvar(File file, String novoNome){
        this.file = file;
        for(String acao : acoes.values()){
            acao.replaceAll(nomeDoModelo, novoNome);
        }
        nomeDoModelo = novoNome;
        salvar();
    }
    
    public void fechar(){
        guardaAcao();
        salvar();
        salvar = false;
        if(editorModelos != null)
            editorModelos.dispose();
        if(editorTexto != null)
            editorTexto.dispose();
    }
    
    public static String geraCodigo(String nomeDoModelo, 
            HashMap<String,String> acoes){
        // Escreve Modelo
        String code = "modelo " + nomeDoModelo + "\n";
        // Escreve metodo CRIA
        code += "metodo " + ModelMaker.CRIA + "\n" +
                    acoes.get(ModelMaker.CRIA) + "\n" + "fim;"
                    + "\n";
        // escreve outros metodos
        for(String metodo : acoes.keySet()){
            if(! (metodo.equals(ModelMaker.CRIA) || 
                    metodo.equals(ModelMaker.VIVA)))
            code += "metodo " + metodo + "\n" +
                    acoes.get(metodo) + "\n" + "fim;" + "\n";
        }
        code += "fim.";
        return code;
    }
    
    public static String geraCodigoCRIA(HashMap<String,String> acoes){
        // Escreve metodo CRIA
        return "metodo " + ModelMaker.CRIA + "\n" +
                    acoes.get(ModelMaker.CRIA) + "\n" + "fim;";
    }
    
    public static HashMap<String, String> geraAcoesApartirDoCodigo(String code){
        code = code.trim();
        int posIni,posFim, lastEnd;
        String subCode;
        HashMap<String, String> acoesDoCodigo = new HashMap<String, String>();
        try{
            if(code.startsWith("modelo")){
                // pega o nome do modelo
                posIni = code.indexOf("modelo")+6;
                posFim = code.indexOf("metodo");
                subCode = code.substring(posIni,posFim);

                // pega a parte de código dos modelos
                posIni = posFim;
                posFim = code.indexOf("fim.");
                String codigoAcoes = code.substring(posIni, posFim);
                lastEnd = codigoAcoes.lastIndexOf("fim;");

                // Percorre o codigoAcoes pegando as acoes
                posIni = 0;
                while(posIni < lastEnd){
                    // Carrega nome do metodo
                    posIni = codigoAcoes.indexOf("metodo",posIni) + 6;
                    posFim = codigoAcoes.indexOf("\n", posIni);
                    String nomeDoMetodo = codigoAcoes.substring(posIni,posFim);

                    // Pega Código do Metodo
                    posIni = posFim;
                    posFim = codigoAcoes.indexOf("fim;", posIni);
                    subCode = codigoAcoes.substring(posIni,posFim);

                    // Adiciona no hashmap
                    acoesDoCodigo.put(nomeDoMetodo.trim(), subCode.trim());
                    
                    // Proximo metodo
                    posIni = posFim;
                }
                return acoesDoCodigo;
            } else
                Msg.erro("Código Desconhecido!\n" +
                        "Não é um Modelo!");
        } catch (Exception ex){
            Msg.erro("Código Desconhecido!\n" +
                        "Não é um Modelo!");
            ex.printStackTrace();
        }
        return null;
    }
    
    public ModeloExecutavel compila(){
        try{
            guardaAcao();
            Compiler compiler = new Compiler(geraCodigo(nomeDoModelo,acoes));
            return compiler.compileModel();
        } catch (AnalysisError ex){
            Msg.aviso(ex.getMessage());
        }
        return null;
    }
    
    public ModeloExecutavel compilaCria(){
        try{
            guardaAcao();
            Compiler compiler = new Compiler(geraCodigoCRIA(acoes));
            return compiler.compileModel();
        } catch (AnalysisError ex){
            Msg.aviso(ex.getMessage());
        }
        return null;
    }
    
    private void carregaDoArquivo(File file){
        if(file.exists()){
            acoes = geraAcoesApartirDoCodigo(ReadWriteFile.read(file));
        }
    }
    
    private void guardaAcao(){
        if(acoes != null){
            if(acoes.containsKey(getNomeAcaoSelecionada())){
                if(!acoes.get(getNomeAcaoSelecionada()).equals(getCodigoAcaoSelecionada())){
                    acoes.put(getNomeAcaoSelecionada(), getCodigoAcaoSelecionada());
                    salvar = true;
                }
            }
        }
    }
    
    /* Variaveis e Metodos Set and Get */
    
    private String nomeDoModelo;
    private String codigoAcaoSelecionada;
    private String nomeAcaoSelecionada;
    private HashMap<String,String> acoes;
    private boolean salvar;
    private File file;
    
    private EditorTextual editorTexto;
    private EditorGrafico editorModelos;
    private ArrayList figuras;
    private Camera camera;
    private MEHforModels handler;
    private CodeMaker codeMaker;
          
    public HashMap<String, String> getAcoes() {
        return acoes;
    }
    
    public ArrayList<String> getNomesDasAcoes(){
        return new ArrayList<String>(acoes.keySet());
    }
    
    public void addAcao(String nome){
        acoes.put(nome, "");
    }

    public String getAcao(String nome){
        return getAcoes().get(nome);
    }
    
    public String getCodigoAcaoSelecionada() {
        if(codigoAcaoSelecionada == null)
            return "";
        String code = codigoAcaoSelecionada.replaceAll(Msg.newLine,"\n");
        return code;
    }

    public String getNomeAcaoSelecionada() {
        return nomeAcaoSelecionada;
    }

    public void setCodigoAcaoSelecionada(String CodigoAcaoSelecionada) {
        this.codigoAcaoSelecionada = CodigoAcaoSelecionada;
    }

    public void setNomeAcaoSelecionada(String nomeAcaoSelecionada) {
        guardaAcao();
        this.nomeAcaoSelecionada = nomeAcaoSelecionada;
        setCodigoAcaoSelecionada(getAcao(nomeAcaoSelecionada));
        if (editorTexto != null)
            editorTexto.carregaCodigoDaAcaoSelecionada();
    }

    public String getNomeDoModelo() {
        return nomeDoModelo;
    }

    public void setNomeDoModelo(String nomeDoModelo) {
        this.nomeDoModelo = nomeDoModelo;
    }

    public boolean isSalvar() {
        guardaAcao();
        return salvar;
    }

    public MEHforModels getHandler() {
        return handler;
    }

    public EditorGrafico getEditorModelos() {
        return editorModelos;
    }

    public EditorTextual getEditorTexto() {
        return editorTexto;
    }

    public CodeMaker getCodeMaker() {
        return codeMaker;
    }

    public void setFiguras(ArrayList figuras) {
        this.figuras = figuras;
    }

    public ArrayList getFiguras() {
        return figuras;
    }
    
}
