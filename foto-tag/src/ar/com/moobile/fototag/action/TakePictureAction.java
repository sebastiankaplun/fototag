package ar.com.moobile.fototag.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import ar.com.moobile.fototag.domain.Picture;
import ar.com.moobile.fototag.utils.Files;

/**
 * {@link SyncAction} that takes a picture.
 * 
 * @author gastonortiz@gmail.com
 */
public class TakePictureAction extends SyncAction {
	public static final int TAKE_PICTURE_REQUEST_CODE = 0;

	/**
	 * @see Callable#call()
	 */
	@Override
	public Void call() throws Exception {
		Intent intent = new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(
				MediaStore.EXTRA_OUTPUT,
				Uri.fromFile(Files.newFile(Picture.FOLDER_URI
						+ "/"
						+ new SimpleDateFormat("yyyyMMddhhmmss")
								.format(new Date()) + ".png")));
		contextProvider.get().startActivityForResult(intent,
				TAKE_PICTURE_REQUEST_CODE);
		return null;
	}

}
