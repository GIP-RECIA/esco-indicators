/**
 * 
 */
package org.esco.indicators.web.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.services.auth.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller handling the requests and controls on the Welcome page.
 * 
 * @since  2012/06/08
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
public class WelcomeController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(WelcomeController.class);
    
    /** Service providing user informations */
    @Autowired
    private Authenticator authenticator;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    @RequestMapping("/welcome")
    public ModelAndView renderView() throws Exception {
        return new ModelAndView("welcome", null);
    }

    /**
     * Populate the user name field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the user name.<br/>
     */
    @ModelAttribute("username")
    public String populateUsername(HttpServletRequest request) {
	try {
	    return authenticator.getUser().getDisplayName();
	} catch (Exception e) {
	    LOGGER.debug("An error has been thrown : " + e.getMessage());
	    return "guest";
	}
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
