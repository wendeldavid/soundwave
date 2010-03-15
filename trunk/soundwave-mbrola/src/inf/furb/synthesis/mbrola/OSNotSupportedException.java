package inf.furb.synthesis.mbrola;

/**
 * Excess�o lan�ada informando que o Sistema Operacional n�o � suportado.
 */
public class OSNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = -4659742547850200268L;

	public OSNotSupportedException(String osName) {
		super("the OS " + osName + " is not supported");
	}
}
