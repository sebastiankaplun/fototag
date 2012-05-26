package ar.com.moobile.circuit.domain;

import java.io.Serializable;

/**
 * Abstract classs representing an entity.<br>
 * An entity is an object that can be uniquely identified.
 * 
 * @author gastonortiz@gmail.com
 */
public class Entity implements Serializable {
	private static final long serialVersionUID = 5296422080130667888L;

	private Integer id;

	/**
	 * Default constructor. Sets the ID of the entity.
	 * 
	 * @param id
	 *            The ID of the entity.
	 */
	public Entity(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the ID of the entity.
	 * 
	 * @return {@link Integer} The ID.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
