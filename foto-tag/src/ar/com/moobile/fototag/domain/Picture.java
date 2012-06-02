package ar.com.moobile.fototag.domain;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

/**
 * Class representing a picture.
 * 
 * @author gastonortiz@gmail.com
 */
public class Picture extends Entity {
	private static final long serialVersionUID = -5717039973314365655L;
	
	/** Path to the folder that contains the pictures. */
	public static final String FOLDER_URI = Environment.getExternalStorageDirectory().getAbsolutePath() + "/pictures";

	/**
	 * Default constructor.
	 */
	public Picture(String uri) {
		super(uri);
	}

	public Bitmap createThumbnail() {
		return createResizedImage(120);
	}

	public Bitmap createPreview() {
		return createResizedImage(500);
	}

	private Bitmap createResizedImage(int scale) {
		File image = new File(getUri().toString());

		BitmapFactory.Options bounds = new BitmapFactory.Options();
		bounds.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(image.getPath(), bounds);
		int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
				: bounds.outWidth;

		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inSampleSize = originalSize / scale;
		return BitmapFactory.decodeFile(image.getPath(), opts);
	}
}
