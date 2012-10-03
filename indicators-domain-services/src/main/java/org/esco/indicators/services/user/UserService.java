/**
 * 
 */
package org.esco.indicators.services.user;

import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.services.constants.ServicesConstants;
import org.esupportail.commons.services.ldap.LdapUser;

/**
 * Interface providing functions to provide informations on user.
 * 
 * @since  2012/10/02
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface UserService {

    /**
     * Creates a guest user with :
     * <ul>
     * 	<li>The login of the user set with the given uid</li>
     * 	<li>The display name of the user set with "guest"</li>
     * 	<li>The attibute "isGuest" set to <code>true</code></li>
     * </ul>
     * 
     * @return
     * 	a guest user.
     */
    public User createGuestUser();
    
    /**
     * Creates a user from the LDAP informations of the LDAP user.<br/>
     * The LDAP informations will be injected in the created user as following :
     * <ul>
     * 	<li>The LDAP values of the attribute {@link ServicesConstants#LDAP_USER_DISPLAY_NAME} will be injected in the user display name</li>
     * 	<li>The LDAP values of the attribute {@link ServicesConstants#LDAP_USER_LOGIN} will be injected in the user login</li>
     * 	<li>The LDAP values of the attribute {@link ServicesConstants#LDAP_USER_UID} will be injected in the user UID</li>
     * 	<li>The LDAP values of the attribute {@link ServicesConstants#LDAP_USER_GROUPS} will be injected in the user groups</li>
     * </ul>
     * 
     * @param ldapUser
     * 			The LDAP user containing the informations of the user to create.
     * 
     * @return
     * 	a new user containing informations based on the LDAP informations.
     */
    public User createUserFromLdapUser(LdapUser ldapUser);
}
