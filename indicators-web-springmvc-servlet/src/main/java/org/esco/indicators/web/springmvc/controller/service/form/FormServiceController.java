/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.service.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.form.ServiceForm;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.web.RequestParameters;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController;
import org.esco.indicators.web.springmvc.validator.service.ServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link FormServiceController} class.
     */
    public FormServiceController() {
	super(SessionConstants.SERVICE_FORM_ATTR);
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getFailureViewName(org.esco.indicators.domain.beans.form.BasicForm)
     */
    @Override
    public String getFailureViewName(BasicForm unvalidForm) {
        return "form-services";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getFormName()
     */
    @Override
    public String getFormName() {
        return "serviceform";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getUserFormViewName()
     */
    @Override
    public String getUserFormViewName() {
        return "redirect:establishment-services";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getSuperUserFormViewName()
     */
    @Override
    public String getSuperUserFormViewName() {
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
    @Qualifier("serviceValidator")
    public void setFormValidator(Validator formValidator) {
	this.formValidator = formValidator;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Populate the establishments types field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the establishments types field.
     */
    @Override
    @ModelAttribute("estbalishmentsTypesItems")
    public List<FormField> populateEstablishmentsTypes(HttpServletRequest request) {
 	List<FormField> establishmentsTypes = getEntryFormFields(DataFormConstants.ESTABLISHMENTS_TYPES);
 	establishmentsTypes = keepAuthorizedEstablishmentsTypes(establishmentsTypes);
 	return establishmentsTypes;
    }
    
    /**
     * Populate the "lycees"  types field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the "lycees"  types field.
     */
    @Override
    @ModelAttribute("laTypesItems")
    public List<FormField> populateLaTypes(HttpServletRequest request) {
 	List<FormField> laTypes = getEntryFormFields(DataFormConstants.LA_TYPES);
 	laTypes = keepAuthorizedEstablishmentsTypes(laTypes);
 	return laTypes;
    }    
    
    /**
     * Populate the "lycees agricoles"  types field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the "lycees agricoles"  types field.
     */
    @Override
    @ModelAttribute("lyceesTypesItems")
    public List<FormField> populateLyceesTypes(HttpServletRequest request) {
 	List<FormField> lyceesTypes = getEntryFormFields(DataFormConstants.LYCEES_TYPES);
 	lyceesTypes = keepAuthorizedEstablishmentsTypes(lyceesTypes);
 	return lyceesTypes;
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
	List<FormField> servicesFields = getEntryFormFields(DataFormConstants.SERVICES);
	servicesFields = keepAuthorizedServices(servicesFields);
	return servicesFields;
    }
    
    /**
    * Populate the posted (wanted) services field.<br/>
    * This field is used to know which wanted services have been posted during the last form submission.
    * 
    * @param request
    * 			The request made by the user.
    * @return
    * 		the last posted (wanted) services.
    */
   @ModelAttribute("postedServicesItems")
   public List<FormField> populateWantedServices(HttpServletRequest request) {
       ////////////////////////////////////////////////////
       // Retrieval of services in the request
       ////////////////////////////////////////////////////
       // Retrieval of the posted services
       String [] postedServices = request.getParameterValues(RequestParameters.WANTED_SERVICES);
	// If at least one service has already been posted
	if(postedServices != null && postedServices.length > 0) {
	    LOGGER.debug("At least one service has been retrieved in the request parameters");
	    List<String> services = new ArrayList<String>(Arrays.asList(postedServices));
	    return jspKeysToFormFields(services);
	}
	
	////////////////////////////////////////////////////
	// Retrieval of services in the session
	////////////////////////////////////////////////////
       // Retrieval of the submitted form
       ServiceForm form =  getSessionForm(request.getSession(), formSessionAttribute);
       // Retrieval of the services
       String [] formServices = ( form.getWantedServices() == null ? new String [0] : form.getWantedServices() );
       if(formServices.length > 0) {
	   LOGGER.debug("At least one service has been retrieved in the session");
       }
       List<String> services = new ArrayList<String>(Arrays.asList(formServices));
       return jspKeysToFormFields(services);
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
    protected ServiceForm getSessionForm(HttpSession session, String formAttribute) {
        // Retrieval of the form
        ServiceForm form = (ServiceForm) session.getAttribute(formAttribute);
        return (form == null ? new ServiceForm() : form);
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
