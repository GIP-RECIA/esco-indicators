/**
 * 
 */
package org.esco.indicators.services.permission;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.xml.permission.GroupsPermissions;
import org.esco.indicators.domain.beans.xml.permission.Permissions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

/**
 * Utils class providing functions to access the permissions defined into XML files.<br/>
 * 
 * @since  2012/06/07
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class PermissionProviderImpl implements PermissionProvider, InitializingBean {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PermissionProviderImpl.class);

    /** URL of the file containing the groups permissions */
    private Resource groupsPermissionsFile;
    
    /** URL of the file containing the permissions */
    private Resource permissionsFile;
    
    /** Permissions container */
    private PermissionsContainer permissionsContainer;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link PermissionProviderImpl} class.
     */
    public PermissionProviderImpl() {
	super();
    }
    
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        feedContainer();
    }

    //----------------------------------------------------------------------------------  PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.permission.PermissionProvider#reloadPermissions()
     */
    @Override
    public void reloadPermissions(){
	    feedContainer();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.permission.PermissionProvider#getPermissions()
     */
    @Override
    public Permissions getPermissions() {
	return (permissionsContainer != null ? permissionsContainer.getPermissions() : null);
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.permission.PermissionProvider#getGroupsPermissions()
     */
    @Override
    public GroupsPermissions getGroupsPermissions() {
	return (permissionsContainer != null ? permissionsContainer.getGroupsPermissions() : null);
    }
    
    
    /**
     * Sets the groups permissions file.<br/>
     * 
     * @param groupsPermissionsFile
     * 			The groups permissions file to set.
     */
    public void setGroupsPermissionsFile(Resource groupsPermissionsFile) {
        this.groupsPermissionsFile = groupsPermissionsFile;
    }

    /**
     * Sets the permissions file.
     * 
     * @param permissionsFile
     * 			The permissions file, to set.
     */
    public void setPermissionsFile(Resource permissionsFile) {
        this.permissionsFile= permissionsFile;
    }
    
    /**
     * Sets the permissions container.
     * 
     * @param permissionsContainer 
     * 			the permissions container to set
     */
    public void setPermissionsContainer(PermissionsContainer permissionsContainer) {
        this.permissionsContainer = permissionsContainer;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Feeds the container with the data present into the XML permissions files.
     */
    private void feedContainer() {
	// Debug infos
	LOGGER.debug("Loading of the permissions file : [" + permissionsFile +"] and the groups permissions file [" + groupsPermissionsFile +"]" );
	try {
        	// Construction of the context with the "root" classes
        	JAXBContext jaxbContext = JAXBContext.newInstance(GroupsPermissions.class, Permissions.class);
        	
        	// Creation of the unmarshaller
        	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        	
        	// Mapping of the XML files into the classes
        	File permissionsXML = permissionsFile.getFile();
        	File groupsPermissionsXML = groupsPermissionsFile.getFile();
        	
        	Permissions permissions = (Permissions) unmarshaller.unmarshal(permissionsXML);
        	GroupsPermissions groupsPermissions = (GroupsPermissions) unmarshaller.unmarshal(groupsPermissionsXML);
        	
        	// Feeding of the container
        	permissionsContainer.setPermissions(permissions);
        	permissionsContainer.setGroupsPermissions(groupsPermissions);
        	
	}  catch(IOException e) {
	    LOGGER.error("An error occured during an I/O operation : " + e.getMessage());
	} catch (JAXBException e) {
	    LOGGER.error("An error occured during the mapping of XML permissions files : " + e.getMessage());
	}
    }
    
}
