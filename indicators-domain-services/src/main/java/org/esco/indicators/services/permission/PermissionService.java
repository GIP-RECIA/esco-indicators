/**
 * 
 */
package org.esco.indicators.services.permission;

/**
 * Interface providing functions to access permissions informations.
 * 
 * @since  2012/10/03
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface PermissionService {
    
    /**
     * Function indicating if the provided string match at least one permission pattern.
     * 
     * @param stringToMatch
     * 			The string which is tested against the permissions patterns.
     * 
     * @return
     * 	<code>true</code> if the tested string has matched at least one permission pattern.<br/>
     * 	<code>false</code> in other cases.
     */
    public boolean matchAtLeastOnePermission(String stringToMatch);

}
