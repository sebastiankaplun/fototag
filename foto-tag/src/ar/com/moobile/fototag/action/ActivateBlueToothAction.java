package ar.com.moobile.fototag.action;

import java.util.concurrent.Callable;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * {@link AsyncAction} that enables Bluetooth data transference.
 * 
 * @author gastonortiz@gmail.com
 */
public class ActivateBlueToothAction extends AsyncAction<Void> {
	public static final Integer ACTIVATE_BLUETOOTH_REQUEST_CODE = 1;

	@Inject
	private Provider<Activity> activityProvider;

	/**
	 * @see Callable#call()
	 */
	@Override
	public Void call() throws Exception {
		Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		activityProvider.get().startActivityForResult(intent,
				ACTIVATE_BLUETOOTH_REQUEST_CODE);
		return null;
	}
}
