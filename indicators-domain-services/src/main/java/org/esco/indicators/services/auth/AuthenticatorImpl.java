/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esco.indicators.services.auth;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.services.domain.DomainService;
import org.esupportail.commons.services.authentication.AuthUtils;
import org.esupportail.commons.services.authentication.AuthenticationService;
import org.esupportail.commons.services.authentication.info.AuthInfo;
import org.esupportail.commons.services.ldap.LdapUser;
import org.esupportail.commons.services.ldap.LdapUserService;
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
	 * see  {@link DomainService} .
	 */
	private DomainService domainService;
	
	/**
	 * The external authenticator.
	 */
	private AuthenticationService authenticationService;

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
		LOGGER.debug("The authentication service set is : [" + authenticationService.getClass() +"]");
	}

	//--------------------------------------------------------------------------- GETTERS / SETTERS

	/**
	 * @return  the domainService
	 */
	public DomainService getDomainService() {
		return domainService;
	}
	

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

	//------------------------------------------------------------------------------ PUBLIC METHODS
	/* (non-Javadoc)
	 * @see org.esco.indicators.services.auth.Authenticator#getUser()
	 */
	@Override
	public User getUser() throws Exception {
		AuthInfo authInfo = (AuthInfo) ContextUtils.getSessionAttribute(AUTH_INFO_ATTRIBUTE);
		if (authInfo != null) {
			User user = (User) ContextUtils.getSessionAttribute(USER_ATTRIBUTE);
			if (LOGGER.isDebugEnabled()) {
			    LOGGER.debug("Authentication info has been found in session: " + user);
			}
			return user;
		}
		if (LOGGER.isDebugEnabled()) {
		    LOGGER.debug("No authentication info has been found in session");
		}
		authInfo = authenticationService.getAuthInfo();
		if (authInfo == null) {
		    	LOGGER.debug("Authentication info is null");
			unsetUser();
			return null;
		}
		if (AuthUtils.CAS.equals(authInfo.getType())) {
		    	LOGGER.debug("Authentication made by CAS");
		    	LOGGER.debug("The authentication info contains the id : " + authInfo.getId());
		    	LOGGER.debug("The domain service : " + getDomainService());
		    	User user = getDomainService().getUser(authInfo.getId());
			//storeToSession(authInfo, user);
			return user;
		}
		if (AuthUtils.SHIBBOLETH.equals(authInfo.getType())) {
		    	LOGGER.debug("Authentication made by Shibboleth");
			User user = getDomainService().getUser(authInfo.getId());
			storeToSession(authInfo, user);
			return user;
		}
		return null;
	}
	
	/**
	 * @see org.esupportail.helpdesk.services.authentication.Authenticator#unsetUser()
	 */
	public void unsetUser() {
		storeToSession(null, null);
	}
	
	/**
	 * Store the authentication information to the session.
	 * 
	 * @param authInfo
	 * @param user
	 */
	protected void storeToSession(final AuthInfo authInfo, final User user) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("storing to session: " + authInfo);
		}
		ContextUtils.setSessionAttribute(AUTH_INFO_ATTRIBUTE, authInfo);
		ContextUtils.setSessionAttribute(USER_ATTRIBUTE, user);
	}

	//----------------------------------------------------------------------------- PRIVATE METHODS

	//------------------------------------------------------------------------------ STATIC METHODS
	
}