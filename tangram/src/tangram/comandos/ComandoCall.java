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
public class ComandoCall implements Comando{
    String metodo;
    boolean paralelo;
    long wait;

    public ComandoCall(String model, String metodo, boolean paralelo, long wait) {
        this.metodo = metodo;
        this.paralelo = paralelo;
        this.wait = wait;
    }

    public void faca(final Executor executor) throws ComandException {
        if(paralelo){
            new Thread(){
                @Override
                public void run(){
                    //System.out.println("Comando Faça: " + metodo + " paralelo");
                    executor.execute(metodo);
                }
            }.start();
        }else{
            //System.out.println("Comando Faça: " + metodo);
            executor.execute(metodo);
        }
    }
}
