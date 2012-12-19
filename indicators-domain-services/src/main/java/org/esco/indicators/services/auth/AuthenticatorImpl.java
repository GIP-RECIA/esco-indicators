/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esco.indicators.services.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.group.Group;
import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.domain.beans.permission.GenericFilter;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.domain.DomainService;
import org.esco.indicators.services.permission.PermissionService;
import org.esco.indicators.services.structure.EstablishmentService;
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
	 * The session attribute to store the allowed establishments.
	 */
	private static final String ALLOWED_ESTAB_ATTRIBUTE = AuthenticatorImpl.class
			.getName()
			+ ".allowedEstablishments";
	
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
	
	/** 
	 * Service providing access to the establishments.
	 */
	private EstablishmentService establishmentService;
	
	/**
	 * Property name of the filter indicating if the authenticated user is a super user or not.
	 */
	private  String superUserFilterPropertyName;
	
	/**
	 * Property name containing the establishments UAI (as property values).
	 */
	private  String establishmentUaiPropertyName;
	
	/**
	 * Property name containing the establishment types.
	 */
	private  String establishmentsTypePropertyName;
	
	/**
	 * Property name containing the users profiles.
	 */
	private  String usersProfilesPropertyName;
	
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
		LOGGER.debug("The authentication service set is : [" + authenticationService.getClass() +"]");
		Assert.notNull(domainService, 
			"property domainService of class " + this.getClass().getName() + " can not be null");
		Assert.notNull(establishmentPermissionService, 
			"property establishmentPermissionService of class " + this.getClass().getName() + " can not be null");
		Assert.notNull(establishmentService, 
			"property establishmentService of class " + this.getClass().getName() + " can not be null");
		Assert.notNull(superUserFilterPropertyName, 
			"property superUserFilterPropertyName of class " + this.getClass().getName() + " can not be null");
		Assert.notNull(establishmentUaiPropertyName, 
			"property establishmentUaiPropertyName of class " + this.getClass().getName() + " can not be null");
		Assert.notNull(establishmentsTypePropertyName, 
			"property establishmentsTypePropertyName of class " + this.getClass().getName() + " can not be null");
		Assert.notNull(usersProfilesPropertyName, 
			"property usersProfilesPropertyName of class " + this.getClass().getName() + " can not be null");
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
	
	/**
	 * Sets the establishment service.
	 * 
	 * @param establishmentService 
	 * 			the establishment service to set.
	 */
	public void setEstablishmentService(EstablishmentService establishmentService) {
	    this.establishmentService = establishmentService;
	}

	/**
	 * Sets the name of the property containing the establishments UAI (aproperty values).
	 * 
	 * @param establishmentUaiPropertyName 
	 * 				the property name containing the establishments UAI to set.
	 */
	public void setEstablishmentUaiPropertyName(String establishmentUaiPropertyName) {
	    this.establishmentUaiPropertyName = establishmentUaiPropertyName;
	}

	/**
	 * Sets the name of the property containing the establishments types.
	 * 
	 * @param establishmentsTypePropertyName 
	 * 				the property name containing the establishments types to set.
	 */
	public void setEstablishmentsTypePropertyName(String establishmentsTypePropertyName) {
	    this.establishmentsTypePropertyName = establishmentsTypePropertyName;
	}

	/**
	 * Sets the property name used to know if the authenticated user is a super user or not.
	 * 
	 * @param superUserFilterPropertyName 
	 * 				the property name (of the filter) to set.
	 */
	public void setSuperUserFilterPropertyName(String superUserFilterPropertyName) {
	    this.superUserFilterPropertyName = superUserFilterPropertyName;
	}
	
	/**
	 * Sets the property name containing the users profiles.
	 * 
	 * @param usersProfilesPropertyName 
	 * 				the property name containing the users profiles to set.
	 */
	public void setUsersProfilesPropertyName(String usersProfilesPropertyName) {
	    this.usersProfilesPropertyName = usersProfilesPropertyName;
	}
	
	//------------------------------------------------------------------------------ PUBLIC METHODS

	/* (non-Javadoc)
	 * @see org.esco.indicators.services.auth.Authenticator#getAllowedEstablishments()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Establishment> getAllowedEstablishments() {
	    // If there is no authenticated user
	    User user = getUser();
	    if(user == null) {
		LOGGER.debug("The allowed establishments can be retrieved, because the user is not authenticated");
		return null;
	    }
	    
	    // Try to get the allowed establishments from the session
	    List<Establishment> allowedEstablishments = (List<Establishment>) getSessionAttribute(ALLOWED_ESTAB_ATTRIBUTE);
	    if(allowedEstablishments != null) {
		return allowedEstablishments;
	    }
	    
	    // Gets the allowed establishments regarding to the filter properties names and values
	    GenericFilter filter = getEstablishmentFilter();
	    HashMap<String, Set<String>> propertiesNamesAndValues = filter.getPropertiesNamesAndValues();
	    allowedEstablishments = establishmentService.findEstablishmentsByPropertiesNamesAndValues(propertiesNamesAndValues);
	    
	    // Stores the establishments into the session
	    setSessionAttribute(ALLOWED_ESTAB_ATTRIBUTE, allowedEstablishments);

	    return allowedEstablishments;
	}

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
	    
	   // Try to get the filter from the session
	   GenericFilter establishmentFilter = (GenericFilter) getSessionAttribute(FILTER_ATTRIBUTE);
	   if(establishmentFilter != null) {
	       return establishmentFilter;
	   }
	    
	    // If the filter has not already been stored in session
	    // Gets the establishment filter regarding to the user groups
	    establishmentFilter = getEstablishmentFilterFromGroups(user.getGroups());
	    
	    // Stores the filter in session
	   setSessionAttribute(FILTER_ATTRIBUTE, establishmentFilter);
	    
	    return establishmentFilter;
	}


	
	/* (non-Javadoc)
	 * @see org.esco.indicators.services.auth.Authenticator#getUser()
	 */
	@Override
	public User getUser() {
		// If the auth info and user are already stored in session
	    	AuthInfo authInfo = (AuthInfo) getSessionAttribute(AUTH_INFO_ATTRIBUTE);
		if (authInfo != null) {
			User user = (User) getSessionAttribute(USER_ATTRIBUTE);
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
			setSessionAttribute(AUTH_INFO_ATTRIBUTE, authInfo);
			setSessionAttribute(USER_ATTRIBUTE, user);
			return user;
		}
		if (AuthUtils.SHIBBOLETH.equals(authInfo.getType())) {
		    	LOGGER.debug("Authentication made by Shibboleth");
			User user = domainService.getUser(authInfo.getId());
			setSessionAttribute(AUTH_INFO_ATTRIBUTE, authInfo);
			setSessionAttribute(USER_ATTRIBUTE, user);
			return user;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.esco.indicators.services.auth.Authenticator#hasPermissionOnEstablishment(java.lang.String)
	 */
	@Override
	public boolean hasPermissionOnEstablishment(String establishmentUAI) {
	    // Gets the establishment filter
	    GenericFilter establishmentFilter = getEstablishmentFilter();
	    if(establishmentFilter == null) {
		LOGGER.debug("The user has no right on the establishment [" + establishmentUAI + "], because the establishment filter can be retrieved.");
		return false;
	    }
	    
	    // Gets the allowed establishments
	    Set<String> allowedEstablishmentsUAI = establishmentFilter.getPropertyValues(establishmentUaiPropertyName);
	    if(allowedEstablishmentsUAI != null && allowedEstablishmentsUAI.contains(establishmentUAI) ) {
		LOGGER.debug("The user has the right to see the establishment [" + establishmentUAI + "]");
		return true;
	    }
	    
	    LOGGER.debug("The user does not have the right to see the establishment : [" + establishmentUAI +"]");
	    return false;
	}

	/* (non-Javadoc)
	 * @see org.esco.indicators.services.auth.Authenticator#hasPermissionOnEstablishmentsType(java.lang.String)
	 */
	@Override
	public boolean hasPermissionOnEstablishmentsType(String establishmentType) {
	    return filterPropertyContainsValue(establishmentsTypePropertyName, establishmentType);
	}

	/* (non-Javadoc)
	 * @see org.esco.indicators.services.auth.Authenticator#hasPermissionOnUserProfile(java.lang.String)
	 */
	@Override
	public boolean hasPermissionOnUserProfile(String userProfile) {
	    return filterPropertyContainsValue(usersProfilesPropertyName, userProfile);
	}
	
	/* (non-Javadoc)
	 * @see org.esco.indicators.services.auth.Authenticator#isSuperUser()
	 */
	@Override
	public boolean isSuperUser() {
	    // Gets the establishment filter
	    GenericFilter establishmentFilter = getEstablishmentFilter();
	    if(establishmentFilter == null) {
		LOGGER.debug("The user is not considered as a super user, because the establishment filter can be retrieved.");
		return false;
	    }
	    
	    // If the filter contains the super user property name
	    if(establishmentFilter.containsPropertyValuesFor(superUserFilterPropertyName)) {
		LOGGER.debug("The user is considered as a super user, because the establishment filter contains the property name : [" + superUserFilterPropertyName +"]");
		return true;
	    }
	    
	    LOGGER.debug("The user is not considered as a super user, because the establishment filter does not contain the property name : [" + superUserFilterPropertyName +"]");
	    return false;
	}

	/**
	 * @see org.esupportail.helpdesk.services.authentication.Authenticator#unsetUser()
	 */
	public void unsetUser() {
	    	LOGGER.debug("Unsetting the session for the auth info and the user");
		setSessionAttribute(AUTH_INFO_ATTRIBUTE, null);
		setSessionAttribute(USER_ATTRIBUTE, null);
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
	
	/**
	 * Indicates if the authenticated user has the right to see the specified value of the given
	 * property name.
	 * 
	 * @param propertyName
	 * 				The property name associtaed to the value to test.
	 * @param value
	 * 				The property value to test.
	 * @return
	 * 	<code>true</code> if the authenticated user has the right to see informations on the property value associated to the property name.<br/>
	 * 	<code>false</code> in other cases.
	 */
	private boolean filterPropertyContainsValue(String propertyName, String value) {
	    // Gets the establishment filter
	    GenericFilter establishmentFilter = getEstablishmentFilter();
	    if(establishmentFilter == null) {
		LOGGER.debug("The user has no right on the value [" + value + "] of the property [" + propertyName + "], because the establishment filter can be retrieved.");
		return false;
	    }
	    
	    // Gets the allowed values
	    Set<String> allowedPropertyValues = establishmentFilter.getPropertyValues(propertyName);
	    if(allowedPropertyValues != null && allowedPropertyValues.contains(value) ) {
		LOGGER.debug("The user has the right to see the value [" + value + "] of the property [" + propertyName + "]");
		return true;
	    }
	    
	    LOGGER.debug("The user does not have the right to see the value [" + value + "] of the property [" + propertyName + "]");
	    return false;
	}
	
	//------------------------------------------------------------------------------ STATIC METHODS
	/**
	 * Tries to retrieve an object stored in session by the attribute name.
	 * 
	 * @param attributeName
	 * 				The name of the attribute stored in session.
	 * 
	 * @return
	 * 		the object associated to the attribute name in session.<br/>
	 * 		<code>null</code> if no object has been retrived in session.
	 */
	private static Object getSessionAttribute(String attributeName) {
	    	// If the session attribute has already been set
	        Object sessionAttribute = ContextUtils.getSessionAttribute(attributeName);
	        if(sessionAttribute == null) {
	    		LOGGER.debug("No object associated to [" + attributeName + "] has been found in the session");
	        } else {
	            	LOGGER.debug("One object associated to [" + attributeName + "] has been found in the session");
	        }
	        return sessionAttribute;
	}
	
	/**
	 * Store an object into the session.<br/>
	 * The object will be associated to the given attribute name.
	 * 
	 * @param attributeName
	 * 				The attribute name used to store the object in session.
	 * @param value
	 * 				The object to store into the session
	 */
	private static void setSessionAttribute(String attributeName, Object value) {
		LOGGER.debug("Storing to session : [" + value +"] with : [" + attributeName + "]");
		ContextUtils.setSessionAttribute(attributeName, value);
	}

}