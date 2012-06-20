package ar.com.moobile.fototag.utils;

import java.io.File;
import java.io.IOException;

/**
 * Utilities class for {@link File}s.
 * 
 * @author gastonortiz@gmail.com
 */
public final class Files {
	/**
	 * Do not instantiate this class.
	 */
	private Files() {
		// Do nothing.
	}

	/**
	 * Instantiates a created {@link File} from a URI.
	 * 
	 * @param uri
	 *            The uri of the file.
	 * @return
	 */
	public static File newFile(String uri) {
		try {
			File file = new File(uri);
			newDirectory(file.getParent());
			file.createNewFile();
			return file;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static File newDirectory(String uri) {
		File directory = new File(uri);
		if (!directory.exists()) {
			newDirectory(directory.getParent());
			directory.mkdir();
		}
		return directory;
	}
	
	/**
	 * Recursive method that ensures that a folder strucutre exists.
	 * 
	 * @param folder
	 *            The folder to check.
	 */
	public static void ensureFolderExistence(File folder) {
		if (!folder.exists()) {
			ensureFolderExistence(folder.getParentFile());
			folder.mkdir();
		}
	}
}
