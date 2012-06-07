/**
 * 
 */
package org.esco.indicators.domain.beans.permission;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

/**
 * Class providing a list of counties.
 * 
 * @since  2012/06/07
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@XmlType(name = "CountiesType")
public class Counties {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(Counties.class);

    /** The list of counties */
    private List<Integer> counties;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link Counties} class.
     */
    public Counties() {
	super();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the counties.
     * 
     * @return 
     * 	the counties
     */
    @XmlElement(name = "county")
    public List<Integer> getCounties() {
        return counties;
    }

    /**
     * Sets the counties.
     * 
     * @param counties 
     * 			The counties to set.
     */
    public void setCounties(List<Integer> counties) {
        this.counties = counties;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
