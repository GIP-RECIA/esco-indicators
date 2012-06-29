/**
 * 
 */
package org.esco.indicators.web.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.services.structure.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller handling the requests on the page displaying the results of a form submission.
 * 
 * @since  2012/06/29
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/result")
public class ResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ResultController.class);

    /** Establishment service providing access to establishments data */
    @Autowired
    private EstablishmentService establishmentService;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link ResultController} class.
     */
    public ResultController() {
	super();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
        /**
     * Sets the service providing access to establisments data
     * @param establishmentService the establishmentService to set
     */
    public void setEstablishmentService(EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    /**
     * Initializes the result view and redirect to the view.
     * 
     * @param model
     * 			Model data.
     * @param request
     * 			Request made by the user.
     * @return
     * 	the JSP view name.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model, HttpServletRequest request){
        // Return to the web page
	return "result";
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
