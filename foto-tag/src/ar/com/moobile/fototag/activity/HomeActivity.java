package ar.com.moobile.fototag.activity;

import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import ar.com.moobile.fototag.R;

/**
 * Main Activity of the application.
 * 
 * @author gastonortiz@gmail.com
 */
public class HomeActivity extends FotoTagActivity {
	
	@InjectView(R.id.usb_device_button)
	private Button usbDeviceButton;

	@InjectView(R.id.bluetooth_device_button)
	private Button blueToothDeviceButton;

	@InjectView(R.id.sdcard_device_button)
	private Button sdcardDeviceButton;

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
		
	}
}
