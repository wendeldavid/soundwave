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
public class ComandoMovePara implements Comando{
    
    String model;
    Vetor3f parm;

    public ComandoMovePara(String model, float x, float y, float z){
        this.model = model;
        this.parm = new Vetor3f(x,y,z);
    }

    public void faca(Executor executor)throws ComandException {
        executor.getTranslateModel(model).translateTo(parm);
        //System.out.println("Comando MovePara" + parm);
    }
    
}
