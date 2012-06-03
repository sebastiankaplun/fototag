package ar.com.moobile.fototag.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
	public static final String FOLDER_URI = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/pictures";

	private Thumbnail thumbnail;

	/**
	 * Default constructor.
	 */
	public Picture(String uri) {
		super(uri);
	}

	/**
	 * Checks if this picture has a created thumbnail.
	 * 
	 * @return {@link Boolean} <code>true</code> if this picture has a
	 *         thumbnail.
	 */
	public Boolean hasThumbnail() {
		return thumbnail != null;
	}

	/**
	 * Gets the thumbnail of this picture. Creates it if it doesn't exist.
	 * 
	 * @return {@link Thumbnail} The thumbnail.
	 */
	public Thumbnail getThumbnail() {
		try {
			if (!hasThumbnail()) {
				thumbnail = new Thumbnail(this);
				File file = new File(thumbnail.getUri().toString());
				if (!file.exists()) {
					file.createNewFile();
					FileOutputStream fos = new FileOutputStream(file);
					createResizedImage(Thumbnail.RESIZE_VALUE).compress(
							Bitmap.CompressFormat.PNG, 90, fos);
				}
			}
			return thumbnail;
		} catch (IOException e) {
			// Shouldn't happen.
			throw new RuntimeException(e);
		}
	}

	/**
	 * Creates a preview image of this picture.
	 * 
	 * @return {@link Bitmap} The preview image.
	 */
	public Bitmap createPreview() {
		return createResizedImage(500);
	}

	/**
	 * Creates a scaled version of this picture.
	 * 
	 * @param scale
	 *            The scale with which the picture will be converted.
	 * @return {@link Bitmap} The scaled image.
	 */
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
