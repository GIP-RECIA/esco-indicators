/**
 * 
 */
package org.esco.indicators.web.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.services.auth.Authenticator;
import org.esco.indicators.web.springmvc.controller.basic.BasicController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller handling the requests and controls on the Welcome page.
 * 
 * @since  2012/06/08
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping(value = { "/", "/welcome" } )
public class WelcomeController extends BasicController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(WelcomeController.class);
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView renderView() throws Exception {
        return new ModelAndView("welcome", null);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
