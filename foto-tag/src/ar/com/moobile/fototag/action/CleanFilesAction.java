package ar.com.moobile.fototag.action;

import java.io.File;
import java.util.concurrent.Callable;

import ar.com.moobile.fototag.utils.Files;

/**
 * Cleans all the files on a determined folder.
 * 
 * @author gastonortiz@gmail.com
 */
public class CleanFilesAction extends AsyncAction<Void> {

	private String uri;

	/**
	 * Default constructor. Sets the URI to clean.
	 * 
	 * @param uri
	 *            The URI to clean.
	 */
	public CleanFilesAction(String uri) {
		this.uri = uri;
	}

	/**
	 * @see Callable#call()
	 */
	@Override
	public Void call() throws Exception {
		File pictureFolder = Files.newDirectory(uri);
		for (File file : pictureFolder.listFiles()) {
			if (!file.isDirectory()) {
				file.delete();
			}
		}
		return null;
	}
}
