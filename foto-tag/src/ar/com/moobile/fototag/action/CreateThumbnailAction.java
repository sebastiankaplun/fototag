package ar.com.moobile.fototag.action;

import java.util.Map;

import android.view.View;
import android.widget.ImageView;
import ar.com.moobile.fototag.R;
import ar.com.moobile.fototag.domain.Picture;
import ar.com.moobile.fototag.domain.Thumbnail;

import com.google.inject.internal.Maps;

/**
 * {@link AsyncAction} that creates a {@link Thumbnail} for a determined
 * {@link Picture} on a background thread.
 * 
 * @author gastonortiz@gmail.com
 */
public class CreateThumbnailAction extends AsyncAction<Thumbnail> {
	private Picture picture;
	private ImageView imageView;
	private static Map<View, Action> displayedViews = Maps.newHashMap();

	/**
	 * Default constructor.<br>
	 * Sets the imageView to contain the thumbnail image as well as the picture
	 * that will have the thumbnail created.
	 * 
	 * @param imageView
	 *            The {@link ImageView} that will display the thumbnail.
	 * @param picture
	 *            The picture that will own the thumbnail.
	 */
	public CreateThumbnailAction(ImageView imageView, Picture picture) {
		this.imageView = imageView;
		this.picture = picture;
		displayedViews.put(imageView, this);
	}

	/**
	 * @see SafeAsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() throws Exception {
		super.onPreExecute();
		imageView.setImageResource(R.drawable.spinner);
	}

	/**
	 * @see Callable#call()
	 */
	@Override
	public Thumbnail call() throws Exception {

		// If the picture already has a thumbnail then no background processing
		// is required.
		if (!picture.hasThumbnail()) {

			// If background processing is required to get the thumbnail. Wait
			// half a second to see if the user hasn't scrolled the picture off
			// the screen.
			Thread.sleep(500);

			// If after the half second the image is still on the screen, then
			// create the thumbnail for it.
			if (displayedViews.get(imageView).equals(this)) {
				return picture.getThumbnail();
			} else {

				// If after the half second the image is not on the screen, then
				// return null and let the onSuccess method handle the image.
				return null;
			}
		} else {
			return picture.getThumbnail();
		}
	}

	/**
	 * @see SafeAsyncTask#onSuccess(Object)
	 */
	@Override
	protected void onSuccess(Thumbnail thumbnail) throws Exception {
		super.onSuccess(thumbnail);

		// If the thumbnail of the image is null, then this means that the image
		// is not on the screen, so the default empty image should be left on
		// the image view.
		if (thumbnail != null) {
			imageView.setImageURI(thumbnail.getUri());
		}
	}
}
