package ar.com.moobile.circuit.exception;

import android.content.Context;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Validation exception class.
 * 
 * @author gastonortiz@gmail.com
 */
public class ValidationException extends AndroidException {
	private static final long serialVersionUID = 987038356863462811L;

	@Inject
	private static Provider<Context> context;
	
	/**
	 * Default constructor. Sets the message to be displayed.
	 * 
	 * @param msg
	 *            The message to be displayed.
	 */
	public ValidationException(Integer messageResource) {
		this(context.get().getString(messageResource));
	}

	/**
	 * Default constructor. Sets the message to be displayed.
	 * 
	 * @param msg
	 *            The message to be displayed.
	 */
	public ValidationException(String msg) {
		super(msg);
	}
}
