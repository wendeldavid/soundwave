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
public class ComandoMoveParaMundo implements Comando{

    String modelId, modelName;
    Vetor3f parm;

    public ComandoMoveParaMundo(String modelId, String modelName,
            float x, float y, float z){
        this.modelId = modelId;
        this.modelName = modelName;
        this.parm = new Vetor3f(x,y,z);
    }

    public void faca(Executor executor) throws ComandException  {
        executor.getModelExecutor(modelId).
                getTranslateModel(modelName).
                translateTo(parm);
    }
    
}
