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
public class ComandoEspera extends Thread implements Comando{

    long tempo;
    
    public ComandoEspera(long tempo){
        this.tempo = tempo;
    }
    
    public void faca(Executor executor)throws ComandException {
        try {
            sleep(tempo);
            //System.out.println("Comando Sleep: " + tempo);
        } catch (Exception e){
            //System.out.println("Erro no Sleep");
        }
    }
}
