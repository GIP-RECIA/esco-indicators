/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.account.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.web.springmvc.controller.basic.form.BasicEstablishmentFormController;
import org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController;
import org.esco.indicators.web.springmvc.validator.account.EstablishmentAccountValidator;
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

    /** Service providing the data for the form presenting the account statistics options */
    @Autowired
    private DataFormService dataAccountFormService;
    
    /** Validator of the form */
    @Autowired
    private EstablishmentAccountValidator establishmentAccountValidator;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link EstablishmentFormAccountController} class.
     */
    public EstablishmentFormAccountController() {
	super(SessionConstants.ACCOUNT_FORM_ATTR);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.BasicFormController#getDataFormService()
     */
    @Override
    public DataFormService getDataFormService() {
        return dataAccountFormService;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getFailureViewName(org.esco.indicators.domain.beans.form.BasicForm)
     */
    @Override
    public String getFailureViewName(BasicForm unvalidForm) {
        return "establishment-form-accounts";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getSuccessViewName(org.esco.indicators.domain.beans.form.BasicForm)
     */
    @Override
    public String getSuccessViewName(BasicForm validForm) {
	return "redirect:establishment-accounts-activations-result";
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getValidator()
     */
    @Override
    public Validator getValidator() {
        return establishmentAccountValidator;
    }


    //------------------------------------------------------------------------------ PUBLIC METHODS
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

	// If the user has the permission to see informations on his current establishment
	String establishmentUAI = authenticator.getUser().getEstablishmentUAI();
	if(authenticator.hasPermissionOnEstablishment(establishmentUAI)) {
	    return "establishment-form-accounts";
	}
	
        // If the user has not the permission to view establishment information
	return "access-denied";
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
