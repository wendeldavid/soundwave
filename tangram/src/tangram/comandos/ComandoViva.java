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
public class ComandoViva implements Comando{
    
    boolean continua;

    public ComandoViva() {
    }
    
    public void faca(final Executor executor){
        continua = true;
        new Thread(){
            @Override
            public void run(){
                while(continua){
                    try{
                        executor.getComandoVivo().faca(executor);
                    } catch(ComandException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }.start();
    }
    
    public void termina(){
        continua = false;
    }
    
    @Override
    protected Object clone(){
        Object o = null;
        try{
            o = super.clone();
        } catch (Exception e){}
        return o;
    }
}
