/**
 * 
 */
package org.esco.indicators.domain.beans.xml.permission;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

/**
 * Class defining a mapping between a user group and the associated permission.
 * 
 * @since 2012/06/07  
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@XmlType(name = "GroupPermissionType")
public class GroupPermission {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(GroupPermission.class);

    /** Pattern to match */
    private String matchPattern;
    
    /** Name of the permission to apply */
    private String permissionName;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link GroupPermission} class.
     */
    public GroupPermission() {
	super();
    }
   
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the pattern group to match in order.
     * 
     * @return 
     * 	the pattern group to match.
     */
    @XmlElement(name = "match-pattern")
    public String getMatchPattern() {
        return matchPattern;
    }

    /**
     * Sets the pattern group to match.
     * 
     * @param matchPattern 
     * 			The pattern group to set.
     */
    public void setMatchPattern(String matchPattern) {
        this.matchPattern = matchPattern;
    }

    /**
     * Gets the name of the permission for the group.
     * 
     * @return 
     * 	The name of the permission for the group.
     */
    @XmlElement(name = "permission-name")
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * Sets the name of the permissions for the group.
     * 
     * @param permissionName 
     * 			The permission name to set.
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }


    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
