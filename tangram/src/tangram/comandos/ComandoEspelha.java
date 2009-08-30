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
public class ComandoEspelha implements Comando{
    String model;
    
    public ComandoEspelha(String model){
        this.model = model;
    }

    public void faca(Executor executor)throws ComandException {
        executor.getMirrorModel(model).mirror();
        //System.out.println("Comando Espelha");
    }
}
