package ar.com.moobile.fototag.action;

import java.util.concurrent.Callable;

import android.content.Context;
import android.content.Intent;
import ar.com.moobile.fototag.activity.ListedPicturesActivity;
import ar.com.moobile.fototag.domain.Folder;

/**
 * Displays a the pictures on a determined folder.
 * 
 * @author gastonortiz@gmail.com
 */
public class DisplayPicturesAction extends AsyncAction<Void> {

	private Folder folder;

	/**
	 * Default constructor. Creates a folder based on the provided URI.
	 * 
	 * @param uri
	 *            The URI from which the folder is created.
	 */
	public DisplayPicturesAction(String uri) {
		this(new Folder(uri));
	}

	/**
	 * Constructor for in folder browsing. Sets the folder.
	 * 
	 * @param folder
	 *            The folder to show.
	 */
	public DisplayPicturesAction(Folder folder) {
		this.folder = folder;
	}

	/**
	 * @see Callable#call()
	 */
	@Override
	public Void call() throws Exception {
		Context context = contextProvider.get();
		Intent intent = new Intent(context, ListedPicturesActivity.class);
		intent.putExtra(ListedPicturesActivity.FOLDER_EXTRA, folder);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		return null;
	}
}
