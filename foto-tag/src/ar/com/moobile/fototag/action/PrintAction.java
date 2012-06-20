/**
 * 
 */
package ar.com.moobile.fototag.action;

import java.util.List;
import java.util.concurrent.Callable;

import android.util.Log;
import ar.com.moobile.fototag.domain.Picture;

/**
 * {@link AsyncAction} that prints the selected pictures.
 * 
 * @author gastonortiz@gmail.com
 */
public class PrintAction extends AsyncAction<Void> {

	private List<Picture> pictures;

	/**
	 * Default constructor. Sets the {@link Picture}s to print.
	 * 
	 * @param pictures
	 *            The {@link Picture}s to print.
	 */
	public PrintAction(List<Picture> pictures) {
		this.pictures = pictures;
	}

	/**
	 * @see Callable#call()
	 */
	@Override
	public Void call() throws Exception {
		for (Picture picture : pictures) {
			Log.d("TEST", "PRINTING PICTURE WITH ID: " + picture.getUri().toString());
		}
		return null;
	}
}
