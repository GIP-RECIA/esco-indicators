/**
 * 
 */
package org.esco.indicators.services.permission;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.xml.permission.GroupsPermissions;
import org.esco.indicators.domain.beans.xml.permission.Permissions;

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

    /** URL of the file containing the groups permissions */
    private static String groupsPermissionsFileUrl;
    
    /** URL of the file containing the permissions */
    private static String permissionsFileUrl;
    
    /** Singleton of the class */
    private static PermissionProvider instance;
    
    /** Permissions container */
    private PermissionsContainer permissionsContainer;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link PermissionProvider} class.
     */
    private PermissionProvider() {
	super();
	permissionsContainer = PermissionsContainer.getInstance();
	feedContainer();
    }
    
    //----------------------------------------------------------------------------------  PUBLIC METHODS
    /**
     * Reloads the content of the XML permissions files.
     */
    public void reloadPermissions(){
	    feedContainer();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the permissions loaded from the permissions file.
     * 
     * @return
     * 	the permissions loaded from the permissions file.
     */
    public Permissions getPermissions() {
	return (permissionsContainer != null ? permissionsContainer.getPermissions() : null);
    }
    
    /**
     * Gets the groups permissions loaded from the groups permissions file.
     * 
     * @return
     * 	the groups permissions loaded from the permissions file.
     */
    public GroupsPermissions getGroupsPermissions() {
	return (permissionsContainer != null ? permissionsContainer.getGroupsPermissions() : null);
    }
    
    
    //-------------------------------------------------------------------- STATIC GETTERS / SETTERS
    /**
     * Gets the permissions loaded from the permissions file.
     * 
     * @return
     * 	the permissions loaded from the permissions file.
     */
    public static PermissionProvider getInstance() {
	if(instance == null) {
	    instance = new PermissionProvider();
	}
	return instance;
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
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Feeds the container with the data present into the XML permissions files.
     */
    private void feedContainer() {
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
