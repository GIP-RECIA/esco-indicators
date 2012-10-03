/**
 * 
 */
package org.esco.indicators.services.permission;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.permission.EstablishmentViewPermission;
import org.esupportail.commons.utils.Assert;
import org.springframework.beans.factory.InitializingBean;

/**
 * Implementation of {@link PermissionService} interface.<br/>
 * 
 * This implementation only concern permissions on the establishments informations.
 * 
 * @since  2012/10/03
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentPermissionServiceImpl implements PermissionService, InitializingBean {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentPermissionServiceImpl.class);

    /** Permissions defining the establishments */
    private List<EstablishmentViewPermission> establishmentViewPermissions;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	Assert.notNull(establishmentViewPermissions, 
		"property establishmentViewPermissions of class " + this.getClass().getName() + " can not be null");
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the establishments view permissions.
     * 
     * @param establishmentViewPermissions
     * 			The establishment view permissions to set.
     */
    public void setEstablishmentViewPermissions(List<EstablishmentViewPermission> establishmentViewPermissions) {
        this.establishmentViewPermissions = establishmentViewPermissions;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.permission.PermissionService#matchAtLeastOnePermission(java.lang.String)
     */
    @Override
    public boolean matchAtLeastOnePermission(String stringToMatch) {
	// Get all the available patterns
	List<String> availablePatterns = getPatternsToMatch();
	
	// Try to match the string against one of the available pattern
	for (String availablePattern : availablePatterns) {
	    if(Pattern.matches(availablePattern, stringToMatch)) {
		return true;
	    }
	}
	// No pattern has been matched
	return false;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Gets all the available permissions patterns.
     * 
     * @return
     * 	all the available permissions patterns.<br/>
     * 	an empty list if there is no available permission pattern.
     */
    private List<String> getPatternsToMatch() {
	// All the patterns available
	List<String> availablePatterns = new ArrayList<String>();
	for (EstablishmentViewPermission permissionView : establishmentViewPermissions) {
	    availablePatterns.add(permissionView.getPatternToMatch());
	}
	return availablePatterns;
    }
    //------------------------------------------------------------------------------ STATIC METHODS
}
