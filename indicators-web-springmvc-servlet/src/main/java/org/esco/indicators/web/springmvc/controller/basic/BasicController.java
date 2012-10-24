/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.domain.beans.permission.GenericFilter;
import org.esco.indicators.services.auth.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Controller providing functions to populate data which are common to all the controllers.
 * 
 * @since  2012/10/11
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
public class BasicController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicController.class);

    /** Service providing user informations */
    @Autowired
    private Authenticator authenticator;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link BasicController} class.
     */
    public BasicController() {
	super();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Populate the allowedProperties field.<br/>
     * 
     * The allowed properties are extracted from the permission filter associated
     * to the authenticated user.<br/>
     * 
     * They allowed properties are stored into a map containing :
     * <ul>
     * 	<li>Keys : Properties names</li>
     * 	<li>Values : Properties values</li>
     * </ul>
     * 
     * This is made in order to test user permissions in the JSP.<br/>
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the allowed properties.<br/>
     * 	an empty map if no properties has been extracted from the permission filter.
     */
    @ModelAttribute("allowedProperties")
    public Map<String, Set<String>> populateAllowedProperties(HttpServletRequest request) {
 	// Gets the establishment view permission filter
	GenericFilter filter = authenticator.getEstablishmentFilter();
	if(filter == null) {
	    return new HashMap<String, Set<String>>();
	}
	
	// Gets the allowed properties map
	Map<String, Set<String>> allowedProperties = filter.getPropertiesNamesAndValues();
	if(allowedProperties == null) {
	    return new HashMap<String, Set<String>>();
	}
	
	return allowedProperties;
    }
 	
    /**
     * Populate the user field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the user.<br/>
     */
    @ModelAttribute("user")
    public User populateUser(HttpServletRequest request) {
 	try {
 	    return authenticator.getUser();
 	} catch (Exception e) {
 	    LOGGER.debug("An error has been thrown : " + e.getMessage());
 	    return new User();
 	}
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
