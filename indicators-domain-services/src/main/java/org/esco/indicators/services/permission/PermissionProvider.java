/**
 * 
 */
package org.esco.indicators.services.permission;

import org.esco.indicators.domain.beans.xml.permission.GroupsPermissions;
import org.esco.indicators.domain.beans.xml.permission.Permissions;

/**
 * Service providing the permissions.
 * 
 * @since  2012/08/31
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface PermissionProvider {
    /**
     * Reloads the content of the XML permissions files.
     */
    public void reloadPermissions();
    
    /**
     * Gets the permissions loaded from the permissions file.
     * 
     * @return
     * 	the permissions loaded from the permissions file.
     */
    public Permissions getPermissions();
    
    /**
     * Gets the groups permissions loaded from the groups permissions file.
     * 
     * @return
     * 	the groups permissions loaded from the permissions file.
     */
    public GroupsPermissions getGroupsPermissions();

}
