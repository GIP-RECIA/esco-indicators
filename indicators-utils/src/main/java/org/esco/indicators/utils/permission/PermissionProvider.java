/**
 * 
 */
package org.esco.indicators.utils.permission;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.permission.GroupsPermissions;
import org.esco.indicators.domain.beans.permission.Permissions;

/**
 * Utils class providing functions to access the permissions defined into XML files.<br/>
 * 
 * @since  2012/06/07
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class PermissionProvider {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PermissionProvider.class);

    /** Boolean indicatinf if the provider has already initialized the permissions */
    private static boolean permissionsInitialized = false;
    
    /** URL of the file containing the groups permissions */
    private static String groupsPermissionsFileUrl;
    
    /** URL of the file containing the permissions */
    private static String permissionsFileUrl;
    
    /** Permissions container */
    private static PermissionsContainer permissionsContainer;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //-------------------------------------------------------------------- STATIC GETTERS / SETTERS
    /**
     * Gets the permissions loaded from the permissions file.
     * 
     * @return
     * 	the permissions loaded from the permissions file.
     */
    public static Permissions getPermissions() {
	return (permissionsContainer != null ? permissionsContainer.getPermissions() : null);
    }
    
    /**
     * Gets the groups permissions loaded from the groups permissions file.
     * 
     * @return
     * 	the groups permissions loaded from the permissions file.
     */
    public static GroupsPermissions getGroupsPermissions() {
	return (permissionsContainer != null ? permissionsContainer.getGroupsPermissions() : null);
    }
    
    /**
     * Sets the URL of the XML groups permissions file.<br/>
     * 
     * @param groupsPermissionsFileUrl 
     * 			The URL, of the XML groups permissions file, to set.
     */
    public static void setGroupsPermissionsFileUrl(String groupsPermissionsFileUrl) {
        PermissionProvider.groupsPermissionsFileUrl = groupsPermissionsFileUrl;
    }

    /**
     * Sets the URL of the XML permissions file.
     * 
     * @param permissionsFileUrl 
     * 			The URL, of the XML permissions file, to set.
     */
    public static void setPermissionsFileUrl(String permissionsFileUrl) {
        PermissionProvider.permissionsFileUrl = permissionsFileUrl;
    }
    
    //------------------------------------------------------------------------------ STATIC PUBLIC METHODS
    
    /**
     * Initializes everything in order to get access to the permissions and groups permissions.
     */
    public static void initializePermissions() {
	if(!permissionsInitialized) {
        	// Retrieval of the container and feeding
        	permissionsContainer = PermissionsContainer.getInstance();
        	feedContainer();
        	permissionsInitialized = true;
	}
    }
    
    /**
     * Reloads the content of the XML permissions files.
     */
    public static void reloadPermissions(){
	if(permissionsInitialized) {
	    feedContainer();
	}
    }
    
    
    //---------------------------------------------------------------------- STATIC PRIVATE METHODS
    /**
     * Feeds the container with the data present into the XML permissions files.
     */
    private static void feedContainer() {
	try {
        	// Construction of the context with the "root" classes
        	JAXBContext jaxbContext = JAXBContext.newInstance(GroupsPermissions.class, Permissions.class);
        	
        	// Creation of the unmarshaller
        	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        	
        	// Mapping of the XML files into the classes
        	File permissionsFile = new File(permissionsFileUrl);
        	File groupsPermissionsFile = new File(groupsPermissionsFileUrl);
        	
        	Permissions permissions = (Permissions) unmarshaller.unmarshal(permissionsFile);
        	GroupsPermissions groupsPermissions = (GroupsPermissions) unmarshaller.unmarshal(groupsPermissionsFile);
        	
        	// Feeding of the container
        	permissionsContainer.setPermissions(permissions);
        	permissionsContainer.setGroupsPermissions(groupsPermissions);
        	
	} catch (JAXBException e) {
	    LOGGER.error("An error occured during the mapping of XML permissions files : " + e.getMessage());
	}
    }
    
}
