/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.service.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.form.ServiceForm;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.web.springmvc.controller.basic.form.BasicEstablishmentFormController;
import org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController;
import org.esco.indicators.web.springmvc.validator.service.BasicServiceValidator;
import org.esco.indicators.web.springmvc.validator.service.ServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Controller that handles requests made on the form used for the wantedServices statistics for only one.
 * 
 * @since  2012/07/12
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/establishment-services")
public class EstablishmentFormServiceController extends BasicEstablishmentFormController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentFormServiceController.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link EstablishmentFormServiceController} class.
     */
    public EstablishmentFormServiceController() {
	super(SessionConstants.SERVICE_FORM_ATTR);
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getFailureViewName(org.esco.indicators.domain.beans.form.BasicForm)
     */
    @Override
    public String getFailureViewName(BasicForm unvalidForm) {
        return "establishment-form-services";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getFormName()
     */
    @Override
    public String getFormName() {
        return "serviceform";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getSuccessViewName(org.esco.indicators.domain.beans.form.BasicForm)
     */
    @Override
    public String getSuccessViewName(BasicForm validForm) {
	String monitoringType = validForm.getMonitoringType();
	if(monitoringType.equals(DataFormConstants.JSP_KEY_ATTENDANCE)) {
	    return "redirect:establishment-services-attendance-result";
	}
	return "redirect:establishment-services-monitoring-attendance-result";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getSuperUserFormViewName()
     */
    @Override
    public String getSuperUserFormViewName() {
        return "establishment-form-services";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getUserFormViewName()
     */
    @Override
    public String getUserFormViewName() {
        return "establishment-form-services";
    }

    /**
     * Sets the data form service
     * 
     * @param dataFormService
     * 			the data form service to set
     */
    @Autowired
    @Qualifier("dataServiceFormService")
    public void setDataFormService(DataFormService dataFormService) {
	this.dataFormService = dataFormService;
    }
    
    /**
     * Sets the form validator
     * 
     * @param formValidator
     * 			the data form validator to set
     */
    @Autowired
    @Qualifier("basicServiceValidator")
    public void setFormValidator(Validator formValidator) {
	this.formValidator = formValidator;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
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
    
    //--------------------------------------------------------------------------- PROTECTED METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.BasicController#getSessionForm(javax.servlet.http.HttpSession, java.lang.String)
     */
    @Override
    protected BasicForm getSessionForm(HttpSession session, String formAttribute) {
        // Retrieval of the form
        ServiceForm form = (ServiceForm) session.getAttribute(formAttribute);
        return (form == null ? new ServiceForm() : form);
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
