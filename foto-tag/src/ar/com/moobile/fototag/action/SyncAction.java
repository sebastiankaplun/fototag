package ar.com.moobile.fototag.action;

import java.util.concurrent.Callable;

import roboguice.util.RoboAsyncTask;
import roboguice.util.SafeAsyncTask;
import android.app.Activity;
import ar.com.moobile.fototag.exception.ExceptionHandler;
import ar.com.moobile.fototag.utils.Injector;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * {@link Action} that executes mainly on the main thread.
 */
public abstract class SyncAction implements Action, Callable<Void> {

	protected final String TAG = getClass().getSimpleName();

	@Inject
	private ExceptionHandler exceptionHandler;

	@Inject
	protected Provider<Activity> contextProvider;

	/**
	 * Default constructor.
	 */
	public SyncAction() {

		// Hack made so that injection is provided for this class.
		Injector.getInstance().injectMembers(this);
	}

	/**
	 * @see RoboAsyncTask#call()
	 */
	public void execute() {
		try {
			call();
		} catch (Exception e) {
			onException(e);
		}
	}

	/**
	 * @see SafeAsyncTask#onException(Exception)
	 */
	protected void onException(Exception e) throws RuntimeException {
		exceptionHandler.handleException(TAG, e);
	}
}
