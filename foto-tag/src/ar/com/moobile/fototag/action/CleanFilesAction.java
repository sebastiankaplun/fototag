package ar.com.moobile.fototag.action;

import java.io.File;

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
		File pictureFolder = new File(uri);
		for (File file : pictureFolder.listFiles()) {
			if (!file.isDirectory()) {
				file.delete();
			}
		}
		return null;
	}
}
