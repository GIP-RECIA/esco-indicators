/**
 * 
 */
package org.esco.indicators.web.springmvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.domain.beans.permission.GenericFilter;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.auth.Authenticator;
import org.esco.indicators.services.form.DataFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Controller providing functions to populate data which are common to all the controllers.
 * 
 * @since  2012/10/11
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Component
public class BasicController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicController.class);

    /** Service providing user informations and authorizations (autowired by the setter) */
    @Autowired
    protected Authenticator authenticator;
    
    /** Service providing informations on the data of the form (autowired in the sub classes by the setter) */
    protected DataFormService dataFormService;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link BasicController} class.
     */
    public BasicController() {
	super();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the data form service of the form controller.
     * 
     * @return
     * 	the data form service of the form controller
     */
    public DataFormService getDataFormService() {
	return dataFormService;
    }

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
     * Populate the number of establishments that can be viewed by the authenticated user.<br/>
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the number of viewable establishments.<br/>
     * 	The number 0 if the number can be retrieved properly.
     */
    @ModelAttribute("numAllowedEstablishments")
    public int populateAllowedEstablishments(HttpServletRequest request) {
 	List<Establishment> allowedEstablishments = authenticator.getAllowedEstablishments();
 	if(allowedEstablishments == null) {
 	    return 0;
 	}
 	return allowedEstablishments.size();
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
    
    //--------------------------------------------------------------------------- PROTECTED METHODS
    /**
     * Checks if the specified session contains a form associated to the session attribute <code>formAttribute</code>.<br/>
     * 
     * @param session
     * 			The user session.
     * @param formAttribute
     * 			The session attribute associated to the form.
     * 
     * @return
     * 	<code>true</code> if the session contained a form associated to <code>formAttribute</code>.<br/>
     * 	<code>false</code> in other cases.
     */
    protected boolean containsForm(HttpSession session, String formAttribute) {
	// Retrieval of the associated form
	BasicForm basicForm = (BasicForm) session.getAttribute(formAttribute);
	if(basicForm == null) {
	    return false;
	}
	return true;
    }

    
    /**
     * Gets the form associated to the session attribute : <code>formAttribute</code>.
     * 
     * @param session
     * 			The user session.
     * @param formAttribute
     * 			The session attribute associated to the form.
     * 
     * @return
     * 	the form associated to the session attribute : <code>formAttribute</code><br/>
     * 	an empty form if no form is associated to the session attribute.
     */
    protected BasicForm getSessionForm(HttpSession session, String formAttribute) {
        // Retrieval of the form
        BasicForm form = (BasicForm) session.getAttribute(formAttribute);
        return (form == null ? new BasicForm() : form);
    }
    
    //--------------------------------------------------------------------------- PROTECTED METHODS
    /**
     * Only keeps the jsp keys (associated to users profiles) which are authorized
     * for the authenticated user.
     * 
     * @param jspKeys
     * 			The jsp keys (associated to users profiles) to test.
     * 
     * @return
     * 	the authorized jsp keys.<br/>
     * 	an empty list if no jsp is authorized.
     */
    protected List<String> keepAuthorizedJspKeysForUsersProfiles(List<String> jspKeys) {
	// Final result
	List<String> authorizedUsersProfilesKeys =  new ArrayList<String>();
	
	// Retrieves the authorized jsp keys (associated to users profiles)
	for (String jspKey : jspKeys) {
	    String userProfile = getDataFormService().getEntryName(jspKey);
	    // Checks if the user has rights on this user profile
	    if(authenticator.hasPermissionOnUserProfile(userProfile)) {
		authorizedUsersProfilesKeys.add(jspKey);
	    }
	}
	LOGGER.debug("The authorized users profiles JSP keys among : " + jspKeys + " are : " + authorizedUsersProfilesKeys );
	
	return authorizedUsersProfilesKeys;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
