/*
 * 
 * 
 */

package tangram.comandos;

import java.util.ArrayList;
import java.util.HashMap;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

import jogl.Camera;
import jogl.intefaces.ColorModel;
import jogl.intefaces.DrawModel;
import jogl.intefaces.MirrorModel;
import jogl.intefaces.RotateModel;
import jogl.intefaces.TranslateModel;
import jogl.util.Vetor3f;
import tangram.Desenha;
import tangram.Figura;
import tangram.MEHforMundoExecutavel;
import tangram.gui.EditorGrafico;
import tangram.gui.Msg;

import com.sun.opengl.util.Animator;

/**
 * Esta Classe implementa metodos para executar os comando de um mundo da 
 * linguagem do LTD.
 * @author GlauKo
 */
public class MundoExecutavel extends Thread implements Executor{
    
    /**
     * Contém os modelos do mundo.
     */
    private HashMap<String,ModeloExecutavel> modelosDoMundo;
    
    /**
     * Contem os comandos do mundo.
     */
    private ArrayList<Comando> comandosDoMundo;
    
    /**
     * Interface Gráfica a qual será desenhada as figuras.
     */
    private EditorGrafico editorGrafico;

    /**
     * cria um novo MundoExecutavel
     */
    public MundoExecutavel() {
    }

    /**
     * Este Método inicializa a execução do mundo, criando a tela, os modelos e
     * executando os comandos.
     */
    @Override
    public void run() {
        criaTela();
        startAllModelosMundo();
        for (Comando comando : comandosDoMundo) {
            try{
                comando.faca(this);
            }catch (Exception e){
                Msg.erro(e.getMessage());
            }
        }
        stopAllModelosMundo();
        //editorGrafico.dispose();
    }

    /**
     * Atribui os modelo do mundo.
     * @param modelosDoMundo
     */
    public void setModelosDoMundo(HashMap<String, ModeloExecutavel> modelosDoMundo) {
        this.modelosDoMundo = modelosDoMundo;
    }

    /**
     * retorna os modelos do mundo.
     * @return
     */
    public HashMap<String, ModeloExecutavel> getModelosDoMundo() {
        return modelosDoMundo;
    }

    /**
     * Atribui os comandos do mundo
     * @param comandosDoMundo
     */
    public void setComandosDoMundo(ArrayList<Comando> comandosDoMundo) {
        this.comandosDoMundo = comandosDoMundo;
    }

    /**
     * Retorna os comandos do mundo
     * @return
     */
    public ArrayList<Comando> getComandosDoMundo() {
        return comandosDoMundo;
    }
    
    /**
     * retorna as figuras do mundo
     * @return
     */
    public ArrayList<Figura> getFiguras(){
        ArrayList<Figura> array = new ArrayList();
        for(ModeloExecutavel me : modelosDoMundo.values()){
            array.add(me.getFigura());
        }
        return array;
    }

    public Executor getModelExecutor(String model) throws ComandException {
        if(modelosDoMundo == null)
            throw new ComandException("Mundo não possui modelos.");
        if(!modelosDoMundo.containsKey(model))
            throw new ComandException("Mundo não possui o modelo "+model+".");
        return modelosDoMundo.get(model);
    }

    public RotateModel getRotateModel(String model) throws ComandException {
        return getFigura(model);
    }

    public TranslateModel getTranslateModel(String model) throws ComandException {
        return getFigura(model);
    }

    public MirrorModel getMirrorModel(String model) throws ComandException {
        return getFigura(model);
    }

    public ColorModel getColorModel(String model) throws ComandException {
        return getFigura(model);
    }

    public DrawModel getDrawModel(String model) throws ComandException {
        return getFigura(model);
    }

    public Vetor3f getPoint(String pointName) throws ComandException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Comando getComandoVivo() throws ComandException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMetodoVivo(String metodoVivo) throws ComandException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void toExecute(String metodo) throws ComandException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void execute(String metodo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Retorna a Figura que pelo seu nome.
     * @param model
     * @return
     * @throws Tangram.comandos.ComandException
     */
    private Figura getFigura(String model) throws ComandException{
        if(modelosDoMundo == null)
            throw new ComandException("Mundo não possui modelos.");
        if(!modelosDoMundo.containsKey(model))
            throw new ComandException("Mundo não possui o modelo "+model+".");
        return modelosDoMundo.get(model).getFigura();
    }
    
    /**
     * Executa o método start() em todos os ModeloExecutavel do mundo.
     */
    private void startAllModelosMundo(){
        for (ModeloExecutavel modeloExecutavel : modelosDoMundo.values()) {
            modeloExecutavel.start();
        }
    }
    
    /**
     * executa o método parar() em todos os ModelosExecutavel do mundo.
     */
    private void stopAllModelosMundo(){
        for (ModeloExecutavel modeloExecutavel : modelosDoMundo.values()) {
            modeloExecutavel.parar();
        }
    }
    
    /**
     * Cria interface gráfica para desenhar os modelos.
     */
    private void criaTela(){
        // cria um GLCapabilities para definir que trabalha com duplo buffer e
        // definir o número de bits por pixel
        GLCapabilities caps = new GLCapabilities();
        caps.setDoubleBuffered(true);
        caps.setRedBits(8);
        caps.setGreenBits(8);
        caps.setBlueBits(8);
        caps.setAlphaBits(8);
        
        // cria um cavas para o editor Grafico
        GLCanvas canvas = new GLCanvas(caps);
        
        // Cria um objeto da classe que implementa MouseEventHandler
        MEHforMundoExecutavel handler = 
                new MEHforMundoExecutavel(getFiguras(),
                        new Camera(new Vetor3f()));
        
        // Adiciona o GLEventListener, ouvinte dos eventos do GLCanvas
        canvas.addGLEventListener(new Desenha(handler));
        
        // cria a janela para visualizar o GLCanvas
        JFrame janela = new JFrame();
        canvas.setSize(640, 480);
        janela.add(canvas);
        janela.pack();
        
        // Cria um Animator que chama o método display() constantemente.
        Animator anime = new Animator(canvas);
        anime.setRunAsFastAsPossible(true);
        anime.start();
        
        // mostra janela
        janela.setVisible(true);
        janela.requestFocus();
    }
        
        /*
        // cria editor grafico
        editorGrafico = new EditorGrafico(cavas, handler);
        editorGrafico.setSize(640,480);
        editorGrafico.setVisible(true);
        editorGrafico.requestFocus();
        //editorGrafico
    }
         */
}
