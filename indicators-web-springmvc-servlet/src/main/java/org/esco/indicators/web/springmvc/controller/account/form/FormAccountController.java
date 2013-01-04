/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.account.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController;
import org.esco.indicators.web.springmvc.validator.account.AccountValidator;
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
 * Controller that handles requests on the form  used for the accounts activations statistics.
 * 
 * @since  2012/06/15
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/accounts-activations")
public class FormAccountController extends BasicFormController  {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(FormAccountController.class);

    /** Validator of the form */
    @Autowired
    private AccountValidator accountValidator;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link FormAccountController} class.
     */
    public FormAccountController() {
	super(SessionConstants.ACCOUNT_FORM_ATTR);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getFailureViewName(org.esco.indicators.domain.beans.form.BasicForm)
     */
    @Override
    public String getFailureViewName(BasicForm unvalidForm) {
        return "form-accounts";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getFormName()
     */
    @Override
    public String getFormName() {
        return "accountactivationform";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getSuccessViewName(org.esco.indicators.domain.beans.form.BasicForm)
     */
    @Override
    public String getSuccessViewName(BasicForm validForm) {
	String monitoringType = validForm.getMonitoringType();
	if(monitoringType.equals(DataFormConstants.JSP_KEY_ATTENDANCE)) {
	    return "redirect:accounts-activations-attendance-result";
	}
	return "redirect:accounts-activations-monitoring-attendance-result";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getSuperUserFormViewName()
     */
    @Override
    public String getSuperUserFormViewName() {
        return "form-accounts";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getUserFormViewName()
     */
    @Override
    public String getUserFormViewName() {
        return "redirect:establishment-accounts-activations";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getValidator()
     */
    @Override
    public Validator getValidator() {
        return accountValidator;
    }

    /**
     * Sets the data form service
     * 
     * @param dataFormService
     * 			the data form service to set
     */
    @Autowired
    @Qualifier("dataAccountFormService")
    public void setDataFormService(DataFormService dataFormService) {
	this.dataFormService = dataFormService;
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
     * Validates and processes the submitted form.
     * 
     * @param request
     * 			The request made by the user.
     * @param aaForm
     * 			The submitted form.
     * @param result
     * 			The result of the binding (containing fields values).
     * @param status
     * 			The session status.
     * @return
     * 	the JSP view name.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(HttpServletRequest request, @ModelAttribute("accountactivationform") AccountActivationForm aaForm, BindingResult result, SessionStatus status) {
	return super.processSubmit(request, aaForm, result, status);
    }
    

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
