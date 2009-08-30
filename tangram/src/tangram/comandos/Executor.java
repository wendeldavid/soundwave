/*
 * 
 * 
 */

package tangram.comandos;

import jogl.intefaces.ColorModel;
import jogl.intefaces.DrawModel;
import jogl.intefaces.MirrorModel;
import jogl.intefaces.RotateModel;
import jogl.intefaces.TranslateModel;
import jogl.util.Vetor3f;

/**
 *
 * @author GlauKo
 */
public interface Executor {
    
    /**
     * Retorna um objeto de Executor que tenha o nome passado por parametro
     * @param model
     * @return
     * @throws Tangram.comandos.ComandException
     */
    public Executor getModelExecutor(String model)throws ComandException;
    
    /**
     * Retorna um objeto de RotateModel que tenha o nome passado por parametro
     * @param model
     * @return
     * @throws Tangram.comandos.ComandException
     */
    public RotateModel getRotateModel(String model)throws ComandException;
    
    /**
     * Retorna um objeto de TranslateModel que tenha o nome passado por parametro
     * @param model
     * @return
     * @throws Tangram.comandos.ComandException
     */
    public TranslateModel getTranslateModel(String model)throws ComandException;
    
    /**
     * Retorna um objeto de MirrorModel que tenha o nome passado por parametro
     * @param model
     * @return
     * @throws Tangram.comandos.ComandException
     */
    public MirrorModel getMirrorModel(String model)throws ComandException;
    
    /**
     * Retorna um objeto de ColorModel que tenha o nome passado por parametro
     * @param model
     * @return
     * @throws Tangram.comandos.ComandException
     */
    public ColorModel getColorModel(String model)throws ComandException;
    
    /**
     * Retorna um objeto de DrawModel que tenha o nome passado por parametro
     * @param model
     * @return
     * @throws Tangram.comandos.ComandException
     */
    public DrawModel getDrawModel(String model)throws ComandException;
    
    /**
     * retorna um Vetor3f que representa um ponto que tenha o nome passado por parametro
     * @param pointName
     * @return
     * @throws Tangram.comandos.ComandException
     */
    public Vetor3f getPoint(String pointName)throws ComandException;
    
    /**
     * Retorna o Comando que esta executando constantemente.
     * @return
     * @throws Tangram.comandos.ComandException
     */
    public Comando getComandoVivo()throws ComandException;
    
    /**
     * Seleciona o metodo que deve ser executado constantemente.
     * @param metodoVivo
     * @throws Tangram.comandos.ComandException
     */
    public void setMetodoVivo(String metodoVivo)throws ComandException;
    
    /**
     * Envia o nome de um metodo que deve ser executado paralelamente
     * @param metodo
     * @throws Tangram.comandos.ComandException
     */
    public void toExecute(String metodo)throws ComandException;
    
    /**
     * Executa um metodo pasando o nome dele por parametro
     * @param metodo
     */
    public void execute(String metodo);
}
