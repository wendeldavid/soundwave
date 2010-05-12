/*
 * 
 * 
 */

package tangram.comandos;

import java.util.ArrayList;
import java.util.HashMap;

import jogl.intefaces.ColorModel;
import jogl.intefaces.DrawModel;
import jogl.intefaces.MirrorModel;
import jogl.intefaces.RotateModel;
import jogl.intefaces.TranslateModel;
import jogl.util.Vetor3f;
import tangram.Figura;
import tangram.Peca;
import tangram.gui.Msg;

/**
 * Esta classe implementa m�todos para executar os comandos de uma modelo da 
 * linguagem LTD.
 * @author GlauKo
 */
public class ModeloExecutavel extends Thread implements Executor, Cloneable {
    
    /**
     * Cont�m os comandos de cada m�todo de um modelo.
     */
    private HashMap<String,Comando> metodos;
    
    /**
     * Armazena o objeto de Figura do modelo.
     */
    private Figura figura;
    
    /**
     * Nome do M�todo que deve ser executado pelo comando viva.
     */
    private String metodoVivo;
    
    /**
     * Nome deste modelo.
     */
    private String nomeModelo;
    
    /**
     * Lista de m�todos para executar paralelamente (Atrav�s do m�todo run()).
     */
    private ArrayList<String> metodosParaExecutar;
    
    /**
     * flag para o m�todo run()
     */
    private boolean continua;

    /**
     * Cria nova instâcia da classe ModeloExecutavel passando os metodos do 
     * modelo e seu nome.
     * @param metodos
     * @param nomeModelo
     */
    public ModeloExecutavel(HashMap<String,Comando>metodos, String nomeModelo) {
        this.metodos = metodos;
        this.nomeModelo = nomeModelo;
        metodosParaExecutar = new ArrayList<String>();
        metodoVivo = "";
        continua = true;
    }
    
    /**
     * para a execu��o m�todo run()
     */
    public void parar(){
        continua = false;
    }
        
    /**
     * Atribui a figura do modelo
     * @param figura
     */
    public void setFigura(Figura figura) {
        this.figura = figura;
    }

    /**
     * retorna a figura do modelo
     * @return
     */
    public Figura getFigura() {
        return figura;
    }

    /**
     * retorna os m�todos do modelo.
     * @return
     */
    public HashMap<String, Comando> getMetodos() {
        return metodos;
    }
    
    /**
     * Adiciona um m�todo do modelo a lista de m�todos para executar em paralelo.
     * @param metodo
     * @throws Tangram.comandos.ComandException
     */
    public void toExecute(String metodo)throws ComandException{
        metodosParaExecutar.add(metodo);
    }
    
    /**
     * Este m�todo fica em loop, executando os m�todos do modelo que est�o na 
     * lista de m�todos para executar, at� o m�todo parar ser executado.
     */
    @Override
    public void run(){
        continua = true;
        while(continua){
            ArrayList<String> nomeMetodo = metodosParaExecutar;
            metodosParaExecutar = new ArrayList<String>();
            for(String s : nomeMetodo){
                execute(s);
            }
            try{
                sleep(60);
            } catch(Exception e) {}
        }
    }
    
    @Override
    protected Object clone() {
        try{
            return super.clone();
        } catch(Exception ex){
            return null;
        }
    }
    
    public synchronized void execute(String metodo){
        if(metodo.equals("TERMINA")){
            ComandoViva cv = (ComandoViva) metodos.get("VIVA");
            cv.termina();
        } else if (metodo.equals("APAGA")){
            for(Peca p : figura.getPecas()){
                p.setVisible(false);
            }
            continua = false;
        }else
            try{
                metodos.get(metodo).faca(this);
            }catch( ComandException ex){
                Msg.erro(ex.getMessage());
            }
    }

    public RotateModel getRotateModel(String model) throws ComandException{
        if(nomeModelo.equals(model))
            return figura;
        else if(model.length() == 2){
            if(model.charAt(0) == 'p'){
                RotateModel rm = figura.getPeca(
                        Integer.parseInt(model.substring(1)));
                if(rm != null)
                    return rm;
            }
        }
        throw new ComandException("Nome do identificador Inv�lido!");
    }

    public TranslateModel getTranslateModel(String model) throws ComandException{
        if(nomeModelo.equals(model))
            return figura;
        else if(model.length() == 2){
            if(model.charAt(0) == 'p'){
                TranslateModel tm = figura.getPeca(
                        Integer.parseInt(model.substring(1)));
                if(tm != null)
                    return tm;
            }
        }
        throw new ComandException("Nome do identificador Inv�lido!");
    }

    public MirrorModel getMirrorModel(String model) throws ComandException{
        if(nomeModelo.equals(model))
            return figura;
        else if(model.length() == 2){
            if(model.charAt(0) == 'p'){
                MirrorModel mm = figura.getPeca(
                        Integer.parseInt(model.substring(1)));
                if(mm != null)
                    return mm;
            }
        }
        throw new ComandException("Nome do identificador Inv�lido!");
    }

    public ColorModel getColorModel(String model) throws ComandException{
        if(nomeModelo.equals(model))
            return figura;
        else if(model.length() == 2){
            if(model.charAt(0) == 'p'){
                ColorModel cm = figura.getPeca(
                        Integer.parseInt(model.substring(1)));
                if(cm != null)
                    return cm;
            }
        }
        throw new ComandException("Nome do identificador Inv�lido!");
    }

    public Vetor3f getPoint(String pointName) throws ComandException{
        Vetor3f v = figura.getPoint(pointName);
        if (v != null)
            return v;
        throw new ComandException("N�mero do Ponto Inv�lido!");
    }

    public DrawModel getDrawModel(String model) throws ComandException {
        if(nomeModelo.equals(model))
            return figura;
        else if(model.length() == 2){
            if(model.charAt(0) == 'p'){
                DrawModel dm = figura.getPeca(
                        Integer.parseInt(model.substring(1)));
                if(dm != null)
                    return dm;
            }
        }
        throw new ComandException("Nome do identificador Inv�lido!");
    }
    
    public void setMetodoVivo(String metodoVivo) throws ComandException {
        this.metodoVivo = metodoVivo;
    }
    
    public Comando getComandoVivo() throws ComandException {
        if(metodoVivo.length() == 0)
            throw new ComandException("N�o h� A��o Vivo!");
        if(!metodos.containsKey(metodoVivo))
            throw new ComandException("A A��o Vivo n�o existe!");
        return metodos.get(metodoVivo);
    }

    public Executor getModelExecutor(String model) throws ComandException {
        if(model.equals(nomeModelo))
            return this;
        throw new ComandException("Nome do Modelo Errado!");
    }

}
