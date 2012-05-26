package ar.com.moobile.circuit.exception;

/**
 * Default application exception.
 * 
 * @author gastonortiz@gmail.com
 */
public class AndroidException extends RuntimeException {
	private static final long serialVersionUID = -9120372085066432874L;

	/**
	 * @see RuntimeException#RuntimeException(String)
	 */
	public AndroidException(String msg) {
		super(msg);
	}

	/**
	 * @see RuntimeException#RuntimeException(Throwable)
	 */
	public AndroidException(Throwable t) {
		super(t);
	}
}
