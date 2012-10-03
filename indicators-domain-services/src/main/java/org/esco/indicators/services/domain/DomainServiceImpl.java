package org.esco.indicators.services.domain;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.services.user.UserService;
import org.esupportail.commons.exceptions.UserNotFoundException;
import org.esupportail.commons.services.ldap.LdapException;
import org.esupportail.commons.services.ldap.LdapUser;
import org.esupportail.commons.services.ldap.LdapUserService;
import org.esupportail.commons.utils.Assert;
import org.springframework.beans.factory.InitializingBean;

/**
 * Implementation of the {@link DomainService} interface.
 * 
 * @since  2012/10/02
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 * 
 */
public class DomainServiceImpl implements DomainService, InitializingBean {
    
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Default version UID */
    private static final long serialVersionUID = -5691007282124472952L;
    
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(DomainServiceImpl.class);
	
    /**
     * The service to retrieve user ldap informations.
     */
    private LdapUserService ldapUserService;
    
    /**
     * The user service.
     */
    private UserService userService;

    // -------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link DomainServiceImpl} class.
     */
    public DomainServiceImpl() {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	Assert.notNull(ldapUserService, 
		"property ldapUserService of class " + this.getClass().getName() + " can not be null");
	Assert.notNull(userService, 
		"property userService of class " + this.getClass().getName() + " can not be null");
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the service used to retrieve LDAP user informations.
     * 
     * @param ldapUserService 
     * 			The ldap user service to set.
     */
    public void setLdapUserService(LdapUserService ldapUserService) {
        this.ldapUserService = ldapUserService;
    }

    /**
     * Sets the user service.
     * 
     * @param userService 
     * 			the user service to set.
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.domain.DomainService#getUser(java.lang.String)
     */
    @Override
    public User getUser(String uid) {
	// Retrieves the LDAP user associated to the UID
	LdapUser ldapUser = retrieveLdapUser(uid);
	
	// If no user has been retrieved
	if(ldapUser == null) {
	    return userService.createGuestUser();
	}
	
	// If a user has been retrieved
	return userService.createUserFromLdapUser(ldapUser);
    }
    
    // ----------------------------------------------------------------------------- PRIVATE METHODS
    
    /**
     * Retrieves a LDAP user bu his UID.
     * 
     * @param uid
     * 			The UID of the LDAP user.
     * 
     * @return
     * 	the LDAP user associated to the UID<br/>
     * 	<code>null</code> if no user has been retrieved, or an error occured
     */
    private LdapUser retrieveLdapUser(String uid) {
	try { 
	    return ldapUserService.getLdapUser(uid);
	} catch (UserNotFoundException e) {
	    LOGGER.warn("No LDAP user has been retrieved with the UID : [" + uid + "]");
	} catch (LdapException e) {
	    LOGGER.error("An LDAP error occured while retrieving a user with the UID [" + uid + "] : " + e.getMessage());
	}
	return null;
    }

}
