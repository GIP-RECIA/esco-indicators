/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.service.form;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.form.ServiceForm;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.web.springmvc.controller.BasicFormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Controller that handles requests made on the form used for the wantedServices statistics.
 * 
 * @since  2012/07/12
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/services")
public class FormServiceController extends BasicFormController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(FormServiceController.class);

    /** Service providing the data for form presenting the wantedServices statistics options */
    @Autowired
    private DataFormService dataFormServiceService;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link FormServiceController} class.
     */
    public FormServiceController() {
	super();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the service used to provide the data contained in the form.
     * 
     * @param dataFormServiceService 
     * 			The service, used to provide the data contained in the form, to set.
     */
    public void setDataFormServiceService(DataFormService dataFormServiceService) {
        this.dataFormServiceService = dataFormServiceService;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.BasicFormController#getDataFormService()
     */
    @Override
    public DataFormService getDataFormService() {
        return dataFormServiceService;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Initializes the binder in order to convert inputs.
     * 
     * @param binder
     * 			Binder providing register functions.
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
	// Register a date editor that handles date conversions
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    /**
     * Initializes the wantedServices form.
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
	// Binding of the form
	ServiceForm serviceForm = new ServiceForm();
	model.addAttribute("serviceform", serviceForm);

        // Return to the web page
	return "form-services";
    }
    
    
    /**
     * Populate the available services field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the values for the  available services field.
     */
    @ModelAttribute("availableServicesItems")
    public List<FormField> populateServices(HttpServletRequest request) {
	return getEntryFormFields(DataFormConstants.SERVICES);
    }
    
    /**
     * Validates and processes the submitted form.
     * 
     * @param request
     * 			The request made by the user.
     * @param serviceForm
     * 			The submitted form.
     * @param result
     * 			The result of the binding (containing fields values).
     * @param status
     * 			The session status.
     * @return
     * 	the JSP view name.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(HttpServletRequest request, @ModelAttribute("serviceform") ServiceForm serviceForm, BindingResult result, SessionStatus status) {
	// Log of the submitted form
	if(LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Submitted form : " + serviceForm.toString());
	}
	
	// Validation of the form
//	accountActivationValidator.validate(serviceForm, result);
	
	if(result.hasErrors()) {
	    return "form-services";
	}
	
	// Indication of the last submitted form, and storage of this form
	request.getSession().setAttribute(SessionConstants.LAST_SUBMITTED_FORM_ATTR, SessionConstants.SERVICE_FORM_ATTR);
	request.getSession().setAttribute(SessionConstants.SERVICE_FORM_ATTR, serviceForm);
	
	// Redirection to the result controller
	String monitoringType = serviceForm.getMonitoringType();
	if(monitoringType.equals(DataFormConstants.JSP_KEY_ATTENDANCE)) {
	    return "redirect:accounts-activations-attendance-result";
	}
	
	return "redirect:accounts-activations-monitoring-attendance-result";
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
