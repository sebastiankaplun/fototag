package ar.com.moobile.fototag.activity;

import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ar.com.moobile.fototag.R;
import ar.com.moobile.fototag.action.DisplayPicturesAction;
import ar.com.moobile.fototag.action.ShowFolderAction;
import ar.com.moobile.fototag.action.TakePictureAction;
import ar.com.moobile.fototag.domain.Picture;

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
		takePictureButton.setOnClickListener(new ExecuteOnClickListener(new TakePictureAction()));
		blueToothDeviceButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DisplayPicturesAction(Picture.FOLDER_URI).execute();
			}
		});
		sdcardDeviceButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new ShowFolderAction(Environment.getExternalStorageDirectory().getAbsolutePath()).execute();
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == TakePictureAction.TAKE_PICTURE_REQUEST_CODE) {
				new DisplayPicturesAction(Picture.FOLDER_URI).execute();
			}
		}
	}
}
