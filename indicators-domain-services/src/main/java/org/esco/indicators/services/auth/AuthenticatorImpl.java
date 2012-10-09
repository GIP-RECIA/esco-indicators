/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esco.indicators.services.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.group.Group;
import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.domain.beans.permission.GenericFilter;
import org.esco.indicators.services.domain.DomainService;
import org.esco.indicators.services.permission.PermissionService;
import org.esupportail.commons.services.authentication.AuthUtils;
import org.esupportail.commons.services.authentication.AuthenticationService;
import org.esupportail.commons.services.authentication.info.AuthInfo;
import org.esupportail.commons.utils.Assert;
import org.esupportail.commons.utils.ContextUtils;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Yves Deschamps (Universite de Lille 1) - 2010
 * 
 */
public class AuthenticatorImpl implements Serializable, InitializingBean,
		Authenticator {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(AuthenticatorImpl.class);
    
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 6382142726147456592L;

	/**
	 * The session attribute to store the auth info.
	 */
	private static final String AUTH_INFO_ATTRIBUTE = AuthenticatorImpl.class
			.getName()
			+ ".authInfo";

	/**
	 * The session attribute to store the user.
	 */
	private static final String USER_ATTRIBUTE = AuthenticatorImpl.class
			.getName()
			+ ".user";
	
	/**
	 * The session attribute to store the establishment filter.
	 */
	private static final String FILTER_ATTRIBUTE = AuthenticatorImpl.class
			.getName()
			+ ".filter";

	/**
	 * Service providing access to authentication functions.
	 */
	private AuthenticationService authenticationService;
	
	/**
	 * Service providing access to user informations.
	 */
	private DomainService domainService;
	
	/** 
	 * Service providing access to permissions concerning the establishments.
	 */
	private PermissionService establishmentPermissionService;

	//-------------------------------------------------------------------------------- CONSTRUCTORS

	/**
	 * Bean constructor.
	 */
	public AuthenticatorImpl() {
		super();
	}

	@Override
	public void afterPropertiesSet()  {
		Assert.notNull(authenticationService, "property authenticationService of class "
				+ this.getClass().getName() + " can not be null");
		Assert.notNull(domainService, 
			"property domainService of class " + this.getClass().getName() + " can not be null");
		Assert.notNull(establishmentPermissionService, 
			"property establishmentPermissionService of class " + this.getClass().getName() + " can not be null");
		LOGGER.debug("The authentication service set is : [" + authenticationService.getClass() +"]");
	}

	//--------------------------------------------------------------------------- GETTERS / SETTERS

	/**
	 * @param domainService  the domainService to set
	 */
	public void setDomainService(final DomainService domainService) {
		this.domainService = domainService;
	}
	
	/**
	 * @param authenticationService
	 *            the authenticationService to set
	 */
	public void setAuthenticationService(
			final AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	/**
	 * @return the authenticationService
	 */
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}


	/**
	 * Sets the permission establishment service.
	 * 
	 * @param establishmentPermissionService 
	 * 				the establishment permission service to set.
	 */
	public void setEstablishmentPermissionService(PermissionService establishmentPermissionService) {
	    this.establishmentPermissionService = establishmentPermissionService;
	}
	

	//------------------------------------------------------------------------------ PUBLIC METHODS

	/* (non-Javadoc)
	 * @see org.esco.indicators.services.auth.Authenticator#getEstablishmentFilter()
	 */
	@Override
	public GenericFilter getEstablishmentFilter() {
	    // If there is no authenticated user
	    User user = getUser();
	    if(user == null) {
		LOGGER.debug("The establishment filter can be retrieved, because the user is not authenticated");
		return null;
	    }
	    
	   // If the filter has already been stored in session
	    GenericFilter establishmentFilter = (GenericFilter) ContextUtils.getSessionAttribute(FILTER_ATTRIBUTE);
	    if(establishmentFilter != null) {
		LOGGER.debug("User establishment filter found in session : " + establishmentFilter);
		return establishmentFilter;
	    }
	    
	    // If the filter has not already been stored in session
	    // Gets the establishment filter regarding to the user groups
	    establishmentFilter = getEstablishmentFilterFromGroups(user.getGroups());
	    
	    // stores the filter in session
	    LOGGER.debug("Storing to session : " + establishmentFilter);
	    ContextUtils.setSessionAttribute(FILTER_ATTRIBUTE, establishmentFilter);
	    
	    return establishmentFilter;
	}
	
	/* (non-Javadoc)
	 * @see org.esco.indicators.services.auth.Authenticator#getUser()
	 */
	@Override
	public User getUser() {
		// If the auth info and user are already stored in session
	    	AuthInfo authInfo = (AuthInfo) ContextUtils.getSessionAttribute(AUTH_INFO_ATTRIBUTE);
		if (authInfo != null) {
			User user = (User) ContextUtils.getSessionAttribute(USER_ATTRIBUTE);
			LOGGER.debug("Authentication info has been found in session: " + user);
			return user;
		}
		
		// If nothing has already been stored in session
		LOGGER.debug("No authentication info has been found in session");
		authInfo = authenticationService.getAuthInfo();
		if (authInfo == null) {
		    	LOGGER.debug("Authentication info is null");
			unsetUser();
			return null;
		}
		if (AuthUtils.CAS.equals(authInfo.getType())) {
		    	LOGGER.debug("Authentication made by CAS");
		    	LOGGER.debug("The authentication info contains the id : " + authInfo.getId());
		    	LOGGER.debug("The domain service : " + domainService);
		    	User user = domainService.getUser(authInfo.getId());
			storeToSession(authInfo, user);
			return user;
		}
		if (AuthUtils.SHIBBOLETH.equals(authInfo.getType())) {
		    	LOGGER.debug("Authentication made by Shibboleth");
			User user = domainService.getUser(authInfo.getId());
			storeToSession(authInfo, user);
			return user;
		}
		return null;
	}
	
	/**
	 * @see org.esupportail.helpdesk.services.authentication.Authenticator#unsetUser()
	 */
	public void unsetUser() {
	    	LOGGER.debug("Unsetting the session for the auth info and the user");
		storeToSession(null, null);
	}
	
	/**
	 * Store the authentication information to the session.
	 * 
	 * @param authInfo
	 * @param user
	 */
	protected void storeToSession(final AuthInfo authInfo, final User user) {
		LOGGER.debug("Storing to session: " + authInfo);
		ContextUtils.setSessionAttribute(AUTH_INFO_ATTRIBUTE, authInfo);
		ContextUtils.setSessionAttribute(USER_ATTRIBUTE, user);
	}

	//----------------------------------------------------------------------------- PRIVATE METHODS
	
	/**
	 * Gets the filter created by matching groups names against available permissions patterns.
	 * 
	 * @param goups
	 * 			The groups to match against permissions patterns.
	 * 
	 * @return
	 * 		the filter created by matching groups names against the available permissions patterns.<br/>
	 * 		An empty filter if no permissions patterns has been matched.
	 */
	private GenericFilter getEstablishmentFilterFromGroups(List<Group> goups) {
	    // Gets the names of the groups
	    List<String> groupsNames = new ArrayList<String>();
	    for (Group group : goups) {
		groupsNames.add(group.getName());
	    }
	    // Gets the establishment filter based on the groups names
	    GenericFilter establishmentFilter = establishmentPermissionService.getPermissionFilter(groupsNames);
	    return establishmentFilter;
	}

	//------------------------------------------------------------------------------ STATIC METHODS
	
}