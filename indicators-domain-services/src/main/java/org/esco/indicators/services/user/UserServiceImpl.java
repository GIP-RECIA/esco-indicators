/**
 * 
 */
package org.esco.indicators.services.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.group.Group;
import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.services.constants.ServicesConstants;
import org.esupportail.commons.services.ldap.LdapUser;

/**
 * Implementation of the {@link UserService} interface.
 * 
 * @since  2012/10/02
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class UserServiceImpl implements UserService {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.user.UserService#createGuestUser()
     */
    @Override
    public User createGuestUser() {
	LOGGER.debug("Creation of a guest user");
	
	// Creation of the user
	User guestUser = new User();
	return guestUser;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.user.UserService#createUserFromLdapUser(org.esupportail.commons.services.ldap.LdapUser)
     */
    @Override
    public User createUserFromLdapUser(LdapUser ldapUser) {
	LOGGER.debug("Creation of a user from the LDAP informations : " + ldapUser);
	
	// Retrieves all the informations of the user
	String displayName = ldapUser.getAttribute(ServicesConstants.LDAP_USER_DISPLAY_NAME);
	String login = ldapUser.getAttribute(ServicesConstants.LDAP_USER_LOGIN);
	String uid = ldapUser.getAttribute(ServicesConstants.LDAP_USER_UID);
	String establishmentUai = ldapUser.getAttribute(ServicesConstants.LDAP_USER_ESTABLISHMENT_UAI);
	List<String> groupsValues = ldapUser.getAttributes(ServicesConstants.LDAP_USER_GROUPS);
	
	// Create the user with the informations
	User user = new User(displayName, login, uid, establishmentUai);
	if(groupsValues != null) {
        	for (String groupValue : groupsValues) {
        	    Group group = new Group(groupValue);
        	    user.addGroup(group);
        	}
	}
	
	return user;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
