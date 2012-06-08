/**
 * 
 */
package org.esco.indicators.web.springmvc;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.people.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    @RequestMapping("/welcome")
    public ModelAndView renderView() throws Exception {
        return new ModelAndView("welcome", null);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
