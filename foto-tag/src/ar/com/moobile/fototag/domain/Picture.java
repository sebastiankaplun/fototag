package ar.com.moobile.fototag.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import ar.com.moobile.fototag.utils.Files;

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

	public static final int MAX_BRIGHTNESS = 200;
	public static final int AVERAGE_BRIGHTNESS = MAX_BRIGHTNESS / 2;

	private Thumbnail thumbnail;
	private Resolution resolution;
	private int brightnessFactor = AVERAGE_BRIGHTNESS;
	private boolean printable;

	/**
	 * Default constructor.
	 */
	public Picture(String uri) {
		super(uri);
		long pixelsAmount = new File(uri).length();
		for (Resolution resolution : Resolution.values()) {
			if (resolution.isInRange(pixelsAmount)) {
				this.resolution = resolution;
			}
		}
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
				File file = Files.newFile(thumbnail.getUri().toString());
				FileOutputStream fos = new FileOutputStream(file);
				createResizedImage(Thumbnail.RESIZE_VALUE).compress(
						Bitmap.CompressFormat.PNG, 90, fos);
			}
			return thumbnail;
		} catch (IOException e) {
			// Shouldn't happen.
			throw new RuntimeException(e);
		}
	}

	public Resolution getResolution() {
		return this.resolution;
	}

	/**
	 * Creates a preview image of this picture.
	 * 
	 * @return {@link Bitmap} The preview image.
	 */
	public Bitmap createPreview() {
		return createBrightenedImage(createResizedImage(500), brightnessFactor
				- AVERAGE_BRIGHTNESS);
	}

	/**
	 * Creates a scaled version of this picture.
	 * 
	 * @param scale
	 *            The scale with which the picture will be converted.
	 * @return {@link Bitmap} The scaled image.
	 */
	private Bitmap createResizedImage(int scale) {
		File image = Files.newFile(getUri().toString());
		BitmapFactory.Options bounds = new BitmapFactory.Options();
		bounds.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(image.getPath(), bounds);
		int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
				: bounds.outWidth;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inSampleSize = originalSize / scale;
		return BitmapFactory.decodeFile(image.getPath(), opts);
	}

	/**
	 * Sets the brightness of the image.
	 * 
	 * @param brightnessValue
	 *            The brightness value.
	 */
	private Bitmap createBrightenedImage(Bitmap original, int brightnessValue) {
		Bitmap dest = Bitmap.createBitmap(original.getWidth(),
				original.getHeight(), original.getConfig());
		for (int x = 0; x < original.getWidth(); x++) {
			for (int y = 0; y < original.getHeight(); y++) {
				int pixelColor = original.getPixel(x, y);
				int pixelAlpha = Color.alpha(pixelColor);
				int pixelRed = Color.red(pixelColor) + brightnessValue;
				int pixelGreen = Color.green(pixelColor) + brightnessValue;
				int pixelBlue = Color.blue(pixelColor) + brightnessValue;
				if (pixelRed > 255) {
					pixelRed = 255;
				} else if (pixelRed < 0) {
					pixelRed = 0;
				}
				if (pixelGreen > 255) {
					pixelGreen = 255;
				} else if (pixelGreen < 0) {
					pixelGreen = 0;
				}
				if (pixelBlue > 255) {
					pixelBlue = 255;
				} else if (pixelBlue < 0) {
					pixelBlue = 0;
				}
				int newPixel = Color.argb(pixelAlpha, pixelRed, pixelGreen,
						pixelBlue);
				dest.setPixel(x, y, newPixel);
			}
		}
		return dest;
	}

	/**
	 * Gets the picture bitmap.
	 * 
	 * @return {@link Bitmap} The picture bitmap.
	 */
	public Bitmap getBitmap() {
		return createBrightenedImage(
				BitmapFactory.decodeFile(getUri().toString()), brightnessFactor);
	}

	/**
	 * Gets the brightness factor of this image.
	 * 
	 * @return <b>int</b> The brightness factor.
	 */
	public int getBrightnessFactor() {
		return brightnessFactor;
	}

	/**
	 * Sets the brightness factor of this image.
	 * 
	 * @param brightnessFactor
	 *            The brightness factor.
	 */
	public void setBrightnessFactor(int brightnessFactor) {
		this.brightnessFactor = brightnessFactor;
	}

	/**
	 * Sets the picture as printable.
	 * 
	 * @param printable
	 *            <code>true</code> if the picture is printable.
	 */
	public void setPrintable(boolean printable) {
		this.printable = printable;
	}

	/**
	 * Checks if the picture is printable.
	 * 
	 * @return {@link boolean} <code>true</code> if the picture is printable.
	 */
	public boolean isPrintable() {
		return this.printable;
	}
}
