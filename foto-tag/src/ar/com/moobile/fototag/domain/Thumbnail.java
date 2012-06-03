package ar.com.moobile.fototag.domain;

/**
 * Class representing a thumbnail. A thumbnail is a reduced version of a
 * {@link Picture}.
 * 
 * @author gastonortiz@gmail.com
 */
public class Thumbnail extends Entity {
	private static final long serialVersionUID = -2602070761875039260L;
	private static final String THUMB_SUFFIX = "-thumb";
	public static final Integer RESIZE_VALUE = 120;
	public static final String FOLDER_URI = Picture.FOLDER_URI + "/thumbnail";

	private Picture picture;

	/**
	 * Default constructor. Sets the {@link Picture} that owns this thumbnail.
	 * 
	 * @param picture
	 */
	public Thumbnail(Picture picture) {
		super(nameFrom(picture));
	}

	/**
	 * Creates the name of the thumbnail from the name of it's owner.
	 * 
	 * @param picture
	 *            The owner picture.
	 * @return {@link String} The name of the thumbnail.
	 */
	private static String nameFrom(Picture picture) {
		String uri = picture.getUri().toString();
		return FOLDER_URI + "/"
				+ uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."))
				+ THUMB_SUFFIX + uri.substring(uri.lastIndexOf("."));
	}

	/**
	 * Gets the picture that owns this thumbnail.
	 * 
	 * @return {@link Picture} The owner picture.
	 */
	public Picture getPicture() {
		return this.picture;
	}
}
