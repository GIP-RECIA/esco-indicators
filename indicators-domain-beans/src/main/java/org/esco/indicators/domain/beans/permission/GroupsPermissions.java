/**
 * 
 */
package org.esco.indicators.domain.beans.permission;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

/**
 * Class defining the mapping between a user group and the statistics permissions.<br/>
 * This class is used to map the content of a XML defining the permissions.
 * 
 * @since  2012/06/07
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@XmlRootElement(name = "groups-permissions")
public class GroupsPermissions {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(GroupsPermissions.class);

    /** The list of group permissions */
    private List<GroupPermission> groupsPermissions;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link GroupsPermissions} class.
     */
    public GroupsPermissions() {
	super();
    }



    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the groups permissions defined in the XML.
     * 
     * @return 
     * 	the groups permissions defined in the XML.
     */
    @XmlElement(name = "group-permission")
    public List<GroupPermission> getGroupsPermissions() {
        return groupsPermissions;
    }

    /**
     * Sets the groups permissions defined in the XML.
     * 
     * @param groupsPermissions 
     * 			 The groups permissions to set.
     */
    public void setGroupsPermissions(List<GroupPermission> groupsPermissions) {
        this.groupsPermissions = groupsPermissions;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
