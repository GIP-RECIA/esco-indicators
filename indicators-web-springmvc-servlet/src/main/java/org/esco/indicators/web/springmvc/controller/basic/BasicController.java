/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.basic;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.services.auth.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
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
