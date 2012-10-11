/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.service.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.form.ServiceForm;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController;
import org.esco.indicators.web.springmvc.validator.service.ServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
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

    /** Service providing the data for the form presenting the wantedServices statistics options */
    @Autowired
    private DataFormService dataServiceFormService;

    /** Validator of the form */
    @Autowired
    private ServiceValidator serviceValidator;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link FormServiceController} class.
     */
    public FormServiceController() {
	super(SessionConstants.SERVICE_FORM_ATTR);
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.BasicFormController#getDataFormService()
     */
    @Override
    public DataFormService getDataFormService() {
        return dataServiceFormService;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getValidator()
     */
    @Override
    public Validator getValidator() {
        return serviceValidator;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getFailureViewName(org.esco.indicators.domain.beans.form.BasicForm)
     */
    @Override
    public String getFailureViewName(BasicForm unvalidForm) {
        return "form-services";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getSuccessViewName(org.esco.indicators.domain.beans.form.BasicForm)
     */
    @Override
    public String getSuccessViewName(BasicForm validForm) {
	String monitoringType = validForm.getMonitoringType();
	if(monitoringType.equals(DataFormConstants.JSP_KEY_ATTENDANCE)) {
	    return "redirect:services-attendance-result";
	}
	return "redirect:services-monitoring-attendance-result";
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
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
	return super.processSubmit(request, serviceForm, result, status);
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
