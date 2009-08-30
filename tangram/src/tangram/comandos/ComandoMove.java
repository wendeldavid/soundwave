/*
 * 
 * 
 */

package tangram.comandos;

import jogl.util.Vetor3f;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class ComandoMove implements Comando{
    // nome da peça ou modelo para mover
    String model;
    // valor para mover
    Vetor3f parm;
    
    public ComandoMove(String model, float x, float y, float z){
        this.model = model;
        this.parm = new Vetor3f(x,y,z);
    }
    
    public void faca(Executor executor)throws ComandException {
        // Pega o TranslateMode e chama o metodo para mover
        executor.getTranslateModel(model).translate(parm);
    }
}
