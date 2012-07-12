/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.account.form;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.web.springmvc.controller.BasicFormController;
import org.esco.indicators.web.springmvc.validator.AccountActivationValidator;
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
 * Controller that handles requests on the form  used for the accounts activations statistics.
 * 
 * @since  2012/06/15
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
/**
 * @since  
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/accounts-activations")
public class FormAccountController extends BasicFormController  {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(FormAccountController.class);

    /** Service providing the data for form presenting the account statistics options */
    @Autowired
    private DataFormService dataFormAccountService;
    
    /** Validator of the form */
    @Autowired
    private AccountActivationValidator accountActivationValidator;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link FormAccountController} class.
     */
    public FormAccountController() {
    }
    

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the validator for the {@link AccountActivationForm}.
     * 
     * @param accountActivationValidator 
     * 			 The validator for the {@link AccountActivationForm}.
     */
    public void setAccountActivationValidator(AccountActivationValidator accountActivationValidator) {
        this.accountActivationValidator = accountActivationValidator;
    }
    
    /**
     * Sets the service providing access to the data form.
     * 
     * @param dataFormAccountService
     * 			The service providing access to the data form to set.
     */
    public void setDataFormAccountService(DataFormService dataFormService) {
        this.dataFormAccountService = dataFormService;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.BasicFormController#getDataFormService()
     */
    @Override
    public DataFormService getDataFormService() {
        return dataFormAccountService;
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
     * Initializes the account activation form.
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
	AccountActivationForm aaForm = new AccountActivationForm();
	model.addAttribute("accountactivationform", aaForm);

        // Return to the web page
	return "form-accounts";
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
	// Log of the submitted form
	if(LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Submitted form : " + aaForm.toString());
	}
	
	// Validation of the form
	accountActivationValidator.validate(aaForm, result);
	
	if(result.hasErrors()) {
	    return "form-accounts";
	}
	
	// Indication of the last submitted form, and storage of this form
	request.getSession().setAttribute(SessionConstants.LAST_SUBMITTED_FORM_ATTR, SessionConstants.ACCOUNT_FORM_ATTR);
	request.getSession().setAttribute(SessionConstants.ACCOUNT_FORM_ATTR, aaForm);
	
	// Redirection to the result controller
	String monitoringType = aaForm.getMonitoringType();
	if(monitoringType.equals(DataFormConstants.JSP_KEY_ATTENDANCE)) {
	    return "redirect:accounts-activations-attendance-result";
	}
	
	return "redirect:accounts-activations-monitoring-attendance-result";
    }
    

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
