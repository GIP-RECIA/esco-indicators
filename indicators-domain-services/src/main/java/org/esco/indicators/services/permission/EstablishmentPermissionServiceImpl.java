/**
 * 
 */
package org.esco.indicators.services.permission;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.permission.EstablishmentViewPermission;
import org.esco.indicators.domain.beans.permission.GenericFilter;
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
    
    //------------------------------------------------------------------------------ STATIC METHODS
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.permission.PermissionService#getPermissionFilter(java.lang.String)
     */
    @Override
    public GenericFilter getPermissionFilter(String stringToMatch) {
	// Gets the permissions containing patterns that has been match
	List<EstablishmentViewPermission> matchedPermissions = getPermissions(stringToMatch);
	
	// Creates final permissions from the matched permissions
	// These final permissions are based on the matched ones
	// In these final permission all the (pattern) groups references in the properties values are replaced by the matching groups
	GenericFilter filter = createFinalFilter(stringToMatch, matchedPermissions);

        return filter;
    }
    
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
     * Creates {@link GenericFilter} from the given {@link EstablishmentViewPermission}s.<br/>
     * The created {@link GenericFilter} contains all the informations of the filters contained into the the given {@link EstablishmentViewPermission}s.<br/>
     * The main difference is : all the (pattern) groups references present in the properties values are replaced by the (pattern) matching groups.<br/>
     * <br/>
     * 
     * <u>For instance :</u><br/>
     * If one {@link EstablishmentViewPermission} has :
     *	<ul>
     *		<li>Pattern to match = "establishment_([0-9]+)"</li>
     * 	<li>List of properties and values of the filter :</li>
     * 		<ul>
     * 			<li>Property name  = "uai"</li>
     * 			<li>Property value = "id_%1%"</li>
     * 		</ul>
     * 	</li>
     * </ul>
     * 
     * Then if we have a <code>matchingString</code> equals to "establishment_4587".<br/>
     * 
     * Then, applying this method on this previous {@link EstablishmentViewPermission} and the previous <code>matchingString</code> will give this {@link GenericFilter} :<br/>
     *	<ul>
     * 	<li>List of properties and values of the filter :</li>
     * 		<ul>
     * 			<li>Property name  = "uai"</li>
     * 			<li>Property value = "id_4587"</li>
     * 		</ul>
     * 	</li>
     * </ul>
     * 
     * @param matchingString
     * 			The string which matches against the permissions patterns.
     * @param permissions
     * 			The permissions (containing filters) that have patterns which are matched by the <code>matchingString</code>
     * 
     * @return
     * 	a generic filter containing all the properties names and values contained into the given permissions.<br/>
     * 	All the (pattern) groups references properties values are replaced by the m(pattern) matching groups.
     */
    private GenericFilter createFinalFilter(String matchingString, List<EstablishmentViewPermission> permissions) {
	// Final result
	GenericFilter filter = new GenericFilter();
	
	// Replace the groups references in all the properties values
	for (EstablishmentViewPermission permission : permissions) {
	    Set<String> propertiesNames = permission.getPropertiesNames();
	    for (String propertyName : propertiesNames) {
		Set<String> propertiesValuesWithRef = permission.getPropertyValues(propertyName);
		Set<String> propertiesValuesWithoutRef = replacePatternReferences(permission.getPatternToMatch(), matchingString, propertiesValuesWithRef);
		filter.addPropertyValues(propertyName, propertiesValuesWithoutRef);
	    }
	}
	
	return filter;
    }
    
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
    
    /**
     * Retrieves the permissions which have pattern that matched the given string.<br/>
     * All the available permissions are tested.
     * 
     * @param stringToMatch
     * 			The string to match against permissions patterns.
     * @return
     * 	the list of the permissions that matched against the given string.<br/>
     * 	an empty list if no permission matched.
     */
    private List<EstablishmentViewPermission> getPermissions(String stringToMatch) {
	// Final result
	List<EstablishmentViewPermission> matchedPermissions = new ArrayList<EstablishmentViewPermission>();
	
	// Tests the string against the patterns
	for (EstablishmentViewPermission establishmentViewPermission : establishmentViewPermissions) {
	    String pattern = establishmentViewPermission.getPatternToMatch();
	    if(Pattern.matches(pattern, stringToMatch)) {
		matchedPermissions.add(establishmentViewPermission);
	    }
	}
	
	return matchedPermissions;
    }
    
    /**
     * Replaces all the groups references present into the <code>stringsWithRef</code> by the groups of the <code>matchingString</code>
     * which match the <code>pattern</code>.<br/>
     * 
     * <u>For instance :</u><br/>
     * 	<ul>
     * 		<li><code>pattern</code> is : "pattern_to_match_([a-z]+)"
     * 		<li><code>matchingString</code> is : "pattern_to_match_hello"</li>
     * 		<li><code>stringsWithRef</code> only contains the string : "my_matched_string_is_%1%"</li>
     * 	</ul>
     * 
     * Applying this method on the previous values will return a new list containing only one element which is :<br/>
     * "my_matched_string_is_hello".
     * 
     * @param pattern
     * 			The pattern to match.
     * @param matchingString
     * 			The string matching the patterns.
     * @param stringsWithRef
     * 			The strings containing groups references to the pattern.
     * 
     * @return
     * 	a copy of the given set where each string has been modified to replace the groups references by the matching groups.
     */
    private Set<String> replacePatternReferences(String pattern, String matchingString, Set<String> stringsWithRef) {
	// Final result
	Set<String> stringsWithoutRef = new HashSet<String>();
	
	// Matches the string against the pattern
	Pattern p = Pattern.compile(pattern);
	Matcher matcher = p.matcher(matchingString);
	
	// If there is no matching
	if(!matcher.matches()) {
	    LOGGER.warn("No matching has been found between the pattern + [" + p.pattern() +"] and the string [" + matchingString +"]");
	    return stringsWithoutRef;
	}

	// Replaces the groups references in each string
	for (String stringWithRef : stringsWithRef) {
	    String stringWithoutRef = stringWithRef;
	    for(int i = 1; i <= matcher.groupCount(); i++) {
		stringWithoutRef = stringWithoutRef.replaceAll("%" + i +"%", matcher.group(i));
	    }
	    stringsWithoutRef.add(stringWithoutRef);
	}
	
	return stringsWithoutRef;
    }
    //------------------------------------------------------------------------------ STATIC METHODS
}
