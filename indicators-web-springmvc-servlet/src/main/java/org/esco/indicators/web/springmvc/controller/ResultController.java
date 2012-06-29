/**
 * 
 */
package org.esco.indicators.web.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    /** Data form service providing information on the data from */
    @Autowired
    private DataFormService dataFormService;
    
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
     * Sets the service providing access to establisments data.
     * @param establishmentService 
     * 			The ervice providing access to establisments data to set.
     */
    public void setEstablishmentService(EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }
    
     /**
      * Sets the service providing access to the data form.
      * 
     * @param dataFormService 
     * 			The service providing access to the data form to set.
     */
    public void setDataFormService(DataFormService dataFormService) {
        this.dataFormService = dataFormService;
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
    
    /**
     * Populate the monitoring  type field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the monitoring  type  field.
     */
    @ModelAttribute("monitoringTypeItem")
    public String populateMonitoringTypeItem(HttpServletRequest request) {
	// Retrieval of the submitted monitoring type value
	AccountActivationForm aaForm = (AccountActivationForm) request.getSession().getAttribute(SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the i18n key
	String jspKey = aaForm.getMonitoringType();
	String i18nKey = dataFormService.getI18nKey(jspKey);
	
	return i18nKey;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
