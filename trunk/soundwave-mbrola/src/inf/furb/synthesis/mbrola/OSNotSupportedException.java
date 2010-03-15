package inf.furb.synthesis.mbrola;

/**
 * Excessão lançada informando que o Sistema Operacional não é suportado.
 */
public class OSNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = -4659742547850200268L;

	public OSNotSupportedException(String osName) {
		super("the OS " + osName + " is not supported");
	}
}
