/**
 * 
 */
package org.esco.indicators.services.permission;

import java.util.List;

import org.esco.indicators.domain.beans.permission.GenericFilter;

/**
 * Interface providing functions to access permissions informations.
 * 
 * @since  2012/10/03
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface PermissionService {
    
    /**
     * Indicates if the provided string match at least one permission pattern.
     * 
     * @param stringToMatch
     * 			The string which is tested against the permissions patterns.
     * 
     * @return
     * 	<code>true</code> if the tested string has matched at least one permission pattern.<br/>
     * 	<code>false</code> in other cases.
     */
    public boolean matchAtLeastOnePermission(String stringToMatch);
    
    /**
     * Retrieves the filter associated to permissions which have a pattern that has been 
     * matched by the given string.
     * 
     * @param stringToMatch
     * 			The string to match against the permissions patterns.
     * 
     * @return
     * 	the filter aggregating the filters of the matched permissions.<br/>
     * 	an empty filter if no permission pattern has been matched by the given string.
     */
    public GenericFilter getPermissionFilter(String stringToMatch);
    
    /**
     * Retrieves the filter associated to permissions which have a pattern that has been 
     * matched by at least one of the given strings.
     * 
     * @param stringsToMatch
     * 			The strings to match against the permissions patterns.
     * 
     * @return
     * 	the filter aggregating the filters of the matched permissions.<br/>
     * 	an empty filter if no permission pattern has been matched by the given strings.
     */
    public GenericFilter getPermissionFilter(List<String> stringsToMatch);
}
