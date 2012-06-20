package ar.com.moobile.fototag.activity;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import ar.com.moobile.fototag.R;
import ar.com.moobile.fototag.action.ActivateBlueToothAction;
import ar.com.moobile.fototag.action.CleanFilesAction;
import ar.com.moobile.fototag.action.DisplayPicturesAction;
import ar.com.moobile.fototag.action.ShowFolderAction;
import ar.com.moobile.fototag.action.TakePictureAction;
import ar.com.moobile.fototag.domain.Picture;
import ar.com.moobile.fototag.domain.Thumbnail;

/**
 * Main Activity of the application.
 * 
 * @author gastonortiz@gmail.com
 */
public class HomeActivity extends FotoTagActivity {

	@InjectView(R.id.take_picture_button)
	private Button takePictureButton;

	@InjectView(R.id.bluetooth_device_button)
	private Button blueToothDeviceButton;

	@InjectView(R.id.sdcard_device_button)
	private Button sdcardDeviceButton;

	BroadcastReceiver fileReceivedBroadcastReceiver;

	/**
	 * Default constructor.
	 */
	public HomeActivity() {
		super(R.layout.home);
	}

	/**
	 * @see Activity#onCreate(Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		takePictureButton.setOnClickListener(new ExecuteOnClickListener(
				new TakePictureAction()));
		blueToothDeviceButton.setOnClickListener(new ExecuteOnClickListener(
				new ActivateBlueToothAction()));
		sdcardDeviceButton.setOnClickListener(new ExecuteOnClickListener(
				new ShowFolderAction("/udisk")));
	}

	/**
	 * @see RoboActivity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		new CleanFilesAction(Thumbnail.FOLDER_URI).execute();
	}

	/**
	 * @see RoboActivity#onActivityResult(int, int, Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == TakePictureAction.TAKE_PICTURE_REQUEST_CODE) {
				new DisplayPicturesAction(Picture.FOLDER_URI).execute();
			}
			if (requestCode == ActivateBlueToothAction.ACTIVATE_BLUETOOTH_REQUEST_CODE) {
				BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
				if (adapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
					Intent discoverableIntent = new Intent(
							BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
					discoverableIntent.putExtra(
							BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 60);
					startActivity(discoverableIntent);
				}
				fileReceivedBroadcastReceiver = new FileReceivedBroadcastReceiver();
				registerReceiver(fileReceivedBroadcastReceiver,
						new IntentFilter(
								BluetoothDevice.ACTION_ACL_DISCONNECTED));
			}
		}
	}

	/**
	 * @see RoboActivity#onDestroy()
	 */
	protected void onDestroy() {
		unregisterReceiver(fileReceivedBroadcastReceiver);
	};
}
