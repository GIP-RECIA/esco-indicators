/**
 * 
 */
package org.esco.indicators.web.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.BasicForm;
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
	// Verification of the presence of a submitted form
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    LOGGER.warn("No submitted form has been found in the user session, so there is no data to process and display. The user is redirected to the welcome page.");
	    return "redirect:welcome";
	}
	
        // Cleaning of the session
	request.getSession().setAttribute(SessionConstants.ACCOUNT_FORM_ATTR, null);
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
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted monitoring type value
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the i18n key
	String jspKey = aaForm.getMonitoringType();
	String i18nKey = dataFormService.getI18nKey(jspKey);
	
	return i18nKey;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
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
    private boolean containsForm(HttpSession session, String formAttribute) {
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
     * 	the form associated to the session attribute : <code>formAttribute</code>.<br/>.
     * 	<code>null</code> if no form is associated to the session attribute.
     */
    private BasicForm getSessionForm(HttpSession session, String formAttribute) {
	// Retrieval of the form
	return (BasicForm) session.getAttribute(formAttribute);
    }
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
