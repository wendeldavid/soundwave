/*
 * 
 * 
 */

package tangram.comandos;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class ComandoCallMundo implements Comando {

    String modelo, metodo;
    boolean emParalelo;

    public ComandoCallMundo(String modelo, String metodo, boolean emParalelo) {
        this.modelo = modelo;
        this.metodo = metodo;
        this.emParalelo = emParalelo;
    }
    
    public void faca(Executor executor) throws ComandException {
        if(emParalelo)
            executor.getModelExecutor(modelo).toExecute(metodo);
        else
            executor.getModelExecutor(modelo).execute(metodo);
    }

}
