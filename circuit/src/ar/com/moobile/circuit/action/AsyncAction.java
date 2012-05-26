package ar.com.moobile.circuit.action;

import roboguice.util.RoboAsyncTask;
import roboguice.util.SafeAsyncTask;
import ar.com.moobile.circuit.exception.ExceptionHandler;
import ar.com.moobile.circuit.utils.Injector;

import com.google.inject.Inject;

/**
 * {@link Action} that executes mainly on a background thread.
 * 
 * @author gastonortiz@gmail.com
 */
public abstract class AsyncAction<T> extends RoboAsyncTask<T> implements Action {

	protected final String TAG = getClass().getSimpleName();
	
	@Inject
	private ExceptionHandler exceptionHandler;

	/**
	 * Default constructor.
	 */
	public AsyncAction() {

		// Hack made so that injection is provided for this class.
		Injector.getInstance().injectMembers(this);
	}

	/**
	 * @see SafeAsyncTask#onException(Exception)
	 */
	@Override
	protected void onException(Exception e) throws RuntimeException {
		super.onException(e);
		exceptionHandler.handleException(TAG, e);
	}
}
