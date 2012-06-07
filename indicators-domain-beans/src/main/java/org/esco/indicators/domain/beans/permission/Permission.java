/**
 * 
 */
package org.esco.indicators.domain.beans.permission;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

/**
 * Class providing the permission on an establishment category.
 * 
 * @since  2012/06/07
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@XmlType(name = "PermissionType")
public class Permission {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(Permission.class);

    /** Name of the permission */
    private String name;
    
    /** Authorized type of establishment */
    private String establishmentType;
    
    /** Authorized counties */
    private Counties counties;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link Permission} class.
     */
    public Permission() {
	super();
    }


    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the permission name.
     * 
     * @return 
     * 	the permission name.
     */
    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Setes the permission name.
     * 
     * @param name 
     * 			The permission name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the authorized establishment type.
     * 
     * @return 
     * 	the authorized establishment type.
     */
    @XmlElement(name = "establishment-type")
    public String getEstablishmentType() {
        return establishmentType;
    }

    /**
     * Sets the authorized establishment type.
     * 
     * @param establishmentType 
     * 			The authorized establishment type to set.
     */
    public void setEstablishmentType(String establishmentType) {
        this.establishmentType = establishmentType;
    }

    /**
     * Gets the authorized counties.
     * 
     * @return 
     * 	the authorized counties.
     */
    @XmlElement(name = "counties")
    public Counties getCounties() {
        return counties;
    }

    /**
     * Sets the authorized counties.
     * 
     * @param counties 
     * 			The authorized counties to set.
     */
    public void setCounties(Counties counties) {
        this.counties = counties;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
