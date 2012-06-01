/**
 * 
 */
package org.esco.indicators.services.ldap;

import org.esupportail.commons.services.ldap.LdapEntityService;

/**
 * Interface providing functions to access LDAP data of an establishment.
 * 
 * @since : 31/05/2012
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface LdapEstablishmentService extends LdapEntityService {

    /**
     * Retrieves the name of an establishment by its UAI.
     * 
     * @param uai
     * 			The UAI of the concerned establishment.
     * @return
     * 	the name of the establishment.<br/>
     * 	an empty <code>String</code> if no name has been found.
     */
    public String findEstablishmentName(String uai);
    
}
