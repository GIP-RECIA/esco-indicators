/**
 * 
 */
package org.esco.indicators.domain.beans.xml.permission;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

/**
 * Class providing the permissions on the establishments statistics.
 * 
 * @since  2012/06/07
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@XmlRootElement(name = "permissions")
public class Permissions {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(Permissions.class);

    /** List of the permissions */
    private List<Permission> permissions;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link Permissions} class.
     */
    public Permissions() {
	super();
    }
    
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the permissions.
     * 
     * @return 
     * 	the permissions
     */
    @XmlElement(name = "permission")
    public List<Permission> getPermissions() {
        return permissions;
    }

    /**
     * Sets the permissions.
     * 
     * @param permissions 
     * 			The permissions to set.
     */
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
