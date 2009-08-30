/*
 * 
 * 
 */

package tangram.comandos;

/**
 *
 * @author GlauKo
 */
public class ComandException extends Exception {

    /**
     * Creates a new instance of <code>ComandException</code> without detail message.
     */
    public ComandException() {
    }


    /**
     * Constructs an instance of <code>ComandException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ComandException(String msg) {
        super(msg);
    }
}
