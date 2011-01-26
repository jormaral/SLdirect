package net.neurowork.cenatic.centraldir.model;

import javax.persistence.Column;


/**
 * Simple JavaBean domain object adds a name property to <code>BaseEntity</code>.
 * Used as a base class for objects needing these properties.
 *  
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 */
public class NamedEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	@Column(name = "name", nullable = false, length=50)
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
