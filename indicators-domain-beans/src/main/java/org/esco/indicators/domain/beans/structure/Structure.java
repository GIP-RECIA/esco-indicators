/**
 * 
 */
package org.esco.indicators.domain.beans.structure;

import org.apache.log4j.Logger;

/**
 * Class representing a physical or logical structure like : enterprise,
 * college, department, and so on.
 * 
 * @since 2012/05/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr> 
 */
public class Structure {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(Structure.class);

    /**
     *  SIREN of the structure
     *  The SIREN is a unique identifier of a structure
     */
    private String siren;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the class.
     */
    public Structure() {
	super();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS

    /**
     * Gets the SIREN of the structure.
     * 
     * @return
     * 	the SIREN of the strucure.
     */
    public String getSiren() {
        return siren;
    }


    /**
     * Sets the SIREN of the structure.
     * 
     * @param name
     * 			The SIREN to set.
     */
    public void setSiren(String siren) {
        this.siren = siren;
    }



    //------------------------------------------------------------------------------ PUBLIC METHODS
        @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((siren == null) ? 0 : siren.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Structure other = (Structure) obj;
	if (siren == null) {
	    if (other.siren != null)
		return false;
	} else if (!siren.equals(other.siren))
	    return false;
	return true;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
