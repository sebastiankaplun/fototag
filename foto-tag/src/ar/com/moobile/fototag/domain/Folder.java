package ar.com.moobile.fototag.domain;

import java.io.File;
import java.util.List;

import android.os.Environment;
import ar.com.moobile.fototag.utils.Files;

import com.google.inject.internal.Lists;

/**
 * Class representing a folder into which to search.
 * 
 * @author gastonortiz@gmail.com
 */
public class Folder extends Entity {

	private static final long serialVersionUID = 8888423771631051184L;

	private File folderFile;
	
	public static final String BLUETOOTH_PATH = Environment.getExternalStorageDirectory() + "/bluetooth";

	/**
	 * Default constructor. Sets the file that matches this Folder.
	 * 
	 * @param uri
	 *            The URI of the file.
	 */
	public Folder(String uri) {
		super(uri);
		this.folderFile = Files.newDirectory(uri);
	}

	/**
	 * Gets the folders within this folder.
	 * 
	 * @return {@link List} The subfolders.
	 */
	public List<Folder> getSubfolders() {
		List<Folder> folders = Lists.newArrayList();
		for (File file : folderFile.listFiles()) {
			if (file.isDirectory()) {
				folders.add(new Folder(file.getAbsolutePath()));
			}
		}
		return folders;
	}

	/**
	 * Gets all the pictures within this folder.
	 * 
	 * @return {@link List} The pictures.
	 */
	public List<Picture> getPictures() {
		List<Picture> pictures = Lists.newArrayList();
		for (File file : folderFile.listFiles()) {
			if ((!file.isDirectory())
					&& ((file.getName().toLowerCase().contains(".png"))
							|| (file.getName().toLowerCase().contains(".jpg")) || (file
							.getName().toLowerCase().contains(".bmp")))) {
				pictures.add(new Picture(file.getAbsolutePath()));
			}
		}
		return pictures;
	}

	/**
	 * Counts the amount of pictures on this folder.
	 * 
	 * @return {@link Integer} The amount of pictures.
	 */
	public Integer countPictures() {
		return getPictures().size();
	}

	/**
	 * Gets the name of the folder.
	 * 
	 * @return {@link String} The name of the folder.
	 */
	public String getName() {
		return folderFile.getName();
	}
}
