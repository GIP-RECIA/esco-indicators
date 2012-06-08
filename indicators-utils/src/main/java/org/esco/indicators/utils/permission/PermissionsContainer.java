/**
 * 
 */
package org.esco.indicators.utils.permission;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.permission.GroupsPermissions;
import org.esco.indicators.domain.beans.permission.Permissions;

/**
 * Class containing the groups permissions on the statistics.
 * 
 * @since  2012/06/07
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
class PermissionsContainer {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PermissionsContainer.class);

    /** Single instance of the class */
    private static PermissionsContainer instance;
    
    /** Groups permissions */
    private GroupsPermissions groupsPermissions;
    
    /** Permissions */
    private Permissions permissions;
    
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Private constructor of the {@link PermissionsContainer} class.
     */
    private PermissionsContainer() {
	super();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS

    /**
     * Gets the groups permissions.
     * 
     * @return 
     * 	the groups permissions.
     */
    public GroupsPermissions getGroupsPermissions() {
        return groupsPermissions;
    }

    /**
     * Sets the groups permissions.
     * 
     * @param groupsPermissions 
     * 			The groups permissions to set.
     */
    public void setGroupsPermissions(GroupsPermissions groupsPermissions) {
        this.groupsPermissions = groupsPermissions;
    }

    /**
     * Gets the permissions.
     * 
     * @return 
     * 	the permissions
     */
    public Permissions getPermissions() {
        return permissions;
    }

    /**
     * Sets the permissions.
     * 
     * @param permissions 
     * 			The permissions to set.
     */
    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    
    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
    /**
     * Gets the {@link PermissionsContainer} singleton.
     * 
     * @return
     * 	the {@link PermissionsContainer} singleton
     */
    public static PermissionsContainer getInstance() {
	if(instance == null) {
	    instance = new PermissionsContainer();
	}
	return instance;
    }
}
