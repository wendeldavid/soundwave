/*
 * 
 * 
 */

package tangram.comandos;

import tangram.ModelMaker;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class ComandoVivaMundo extends ComandoCallMundo{

    String metodoVivo;
    
    public ComandoVivaMundo(String modelo, String metodoVivo) {
        super(modelo, ModelMaker.VIVA, true);
        this.metodoVivo = metodoVivo;
    }

    @Override
    public void faca(Executor executor) throws ComandException {
        executor.getModelExecutor(modelo).setMetodoVivo(metodoVivo);
        super.faca(executor);
    }
}
