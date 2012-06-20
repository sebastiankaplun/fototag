package ar.com.moobile.fototag.action;

import java.util.concurrent.Callable;

import roboguice.util.SafeAsyncTask;
import android.graphics.Bitmap;
import ar.com.moobile.fototag.domain.Picture;

/**
 * {@link AsyncAction} That creates a preview of a {@link Picture} to be
 * displayed.
 * 
 * @author gastonortiz@gmail.com
 */
public class CreatePreviewAction extends AsyncAction<Bitmap> {

	/**
	 * Interface that listens to the {@link CreatePreviewAction} events.
	 * 
	 * @author gastonortiz@gmail.com
	 */
	public interface CreatePreviewListener {
		/**
		 * Method called when the creation of the preview is starting.
		 */
		public void onPreviewCreationStart();

		/**
		 * Method called when the preview is created.
		 * 
		 * @param preview
		 *            The created preview.
		 */
		public void onPreviewCreated(Bitmap preview);
	}

	private Picture picture;
	private CreatePreviewListener listener;

	/**
	 * Default constructor. Sets the {@link Picture} to preview.
	 * 
	 * @param picture
	 *            The {@link Picture} to preview.
	 * @param listener
	 *            The {@link CreatePreviewListener listener} that will listen
	 *            for the events of this action.
	 */
	public CreatePreviewAction(Picture picture, CreatePreviewListener listener) {
		this.picture = picture;
		this.listener = listener;
	}

	/**
	 * @see SafeAsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() throws Exception {
		super.onPreExecute();
		listener.onPreviewCreationStart();
	}

	/**
	 * @see Callable#call()
	 */
	@Override
	public Bitmap call() throws Exception {
		return picture.createPreview();
	}

	/**
	 * @see SafeAsyncTask#onSuccess(Object)
	 */
	@Override
	protected void onSuccess(Bitmap preview) throws Exception {
		super.onSuccess(preview);
		listener.onPreviewCreated(preview);
	}
}
