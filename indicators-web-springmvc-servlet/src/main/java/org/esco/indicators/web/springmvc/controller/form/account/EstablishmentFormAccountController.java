/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.form.account;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.web.springmvc.controller.form.basic.BasicEstablishmentFormController;
import org.esco.indicators.web.springmvc.validator.account.EstablishmentAccountValidator;
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
 * Controller that handles requests on the form  used for the accounts activations statistics of only one establishment.
 * 
 * @since  2012/10/29
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/establishment-accounts-activations")
public class EstablishmentFormAccountController extends BasicEstablishmentFormController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentFormAccountController.class);
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link EstablishmentFormAccountController} class.
     */
    public EstablishmentFormAccountController() {
	super(SessionConstants.ACCOUNT_FORM_ATTR, "accountactivationform", "establishment-form-accounts", "establishment-form-accounts", "redirect:establishment-accounts-activations-result", "establishment-form-accounts");
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
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
    
    /**
     * Sets the form validator
     * 
     * @param formValidator
     * 			the data form validator to set
     */
    @Autowired
    @Qualifier("establishmentAccountValidator")
    public void setFormValidator(Validator formValidator) {
	this.formValidator = formValidator;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
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
