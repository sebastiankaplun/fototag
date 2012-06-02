package ar.com.moobile.fototag.domain;

import java.io.Serializable;

import android.net.Uri;

/**
 * Abstract classs representing an entity.<br>
 * An entity is an object that can be uniquely identified.
 * 
 * @author gastonortiz@gmail.com
 */
public class Entity implements Serializable {
	private static final long serialVersionUID = 5296422080130667888L;

	private String uri;

	/**
	 * Default constructor. Sets the URI of the entity.
	 * 
	 * @param uri
	 *            The URI of the entity.
	 */
	public Entity(String uri) {
		this.uri = uri;
	}

	/**
	 * Gets the URI of the entity.
	 * 
	 * @return {@link String} The URI.
	 */
	public Uri getUri() {
		return Uri.parse(uri);
	}
}
