package ar.com.moobile.fototag.exception;

/**
 * Default application exception.
 * 
 * @author gastonortiz@gmail.com
 */
public class FotoTagException extends RuntimeException {
	private static final long serialVersionUID = -9120372085066432874L;

	/**
	 * @see RuntimeException#RuntimeException(String)
	 */
	public FotoTagException(String msg) {
		super(msg);
	}

	/**
	 * @see RuntimeException#RuntimeException(Throwable)
	 */
	public FotoTagException(Throwable t) {
		super(t);
	}
}
