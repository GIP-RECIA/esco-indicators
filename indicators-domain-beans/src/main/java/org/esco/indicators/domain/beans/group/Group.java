/**
 * 
 */
package org.esco.indicators.domain.beans.group;

import org.apache.log4j.Logger;

/**
 * 
 * Class representing a user group.
 * 
 * @since 2012/05/25
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class Group {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(Group.class);
    
    /** Name of the group */
    private String name;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the group.
     * @param name
     * 			The name of the group.
     */
    public Group(String name) {
	this.name = name;
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the name of the group.
     * @return The name of the group.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the group.
     * @param name 
     * 			The name of the group to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Group other = (Group) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
