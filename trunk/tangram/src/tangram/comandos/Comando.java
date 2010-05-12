/*
 * 
 * 
 */

package tangram.comandos;

/**
 * Esta interface disponibiliza um metodo para representa��o e execu��o de uma 
 * comando da linguagem do LTD.
 * @author GlauKo
 */
public interface Comando {
    /**
     * Executa um comando da linguagem LTD.
     * @param executor
     * @throws Tangram.comandos.ComandException
     */
    public void faca(Executor executor)throws ComandException;
}
