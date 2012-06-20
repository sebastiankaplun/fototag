package ar.com.moobile.fototag.exception;

import android.content.Context;
import android.widget.Toast;
import ar.com.moobile.fototag.action.HomeAction;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Class that handles the {@link Exception}s.
 * 
 * @author gastonortiz@gmail.com
 */
public class ExceptionHandler {

	@Inject
	private Provider<Context> contextProvider;

	/**
	 * Handles an {@link Exception}.
	 * 
	 * @param e
	 *            The {@link Exception} handled.
	 */
	public void handleException(String tag, Exception e) {
		if (ValidationException.class.isInstance(e)) {
			handleValidationException(tag, ValidationException.class.cast(e));
		} else {
			handleRegularException(tag, e);
		}
	}

	/**
	 * Method that handles {@link ValidationException}s.
	 * 
	 * @param e
	 *            The {@link ValidationException} to handle.
	 */
	private void handleValidationException(String tag, ValidationException e) {
		Toast.makeText(contextProvider.get(), e.getMessage(),
				Toast.LENGTH_SHORT).show();
	}

	/**
	 * Method that handles regular {@link Exception}s.
	 * 
	 * @param e
	 *            The {@link Exception} to handle.
	 */
	private void handleRegularException(String tag, Exception e) {
		new HomeAction().execute();
	}
}
