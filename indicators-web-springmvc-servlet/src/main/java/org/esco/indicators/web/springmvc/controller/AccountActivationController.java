/**
 * 
 */
package org.esco.indicators.web.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.web.springmvc.validator.AccountActivationValidator;
import org.esupportail.commons.services.i18n.I18nService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.support.RequestContext;

/**
 * Controller handling the requests and controls the form of the accounts activations web page.
 * 
 * @since  2012/06/15
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/accounts-activations")
public class AccountActivationController  {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(AccountActivationController.class);

    /** Service providing the data form */
    @Autowired
    private DataFormService dataFormService;
    
    /** Validator of the form */
    @Autowired
    private AccountActivationValidator accountActivationValidator;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default construcotr of the {@link AccountActivationController} class.
     */
    public AccountActivationController() {
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
     * @param dataFormService
     * 			The service providing access to the data form to set.
     */
    public void setDataFormService(DataFormService dataFormService) {
        this.dataFormService = dataFormService;
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
	return "accounts-activations";
    }
    
     /**
      * Populates the county field.
      * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the county field.
     */
    @ModelAttribute("countyItems")
    public List<FormField> populateCounty(HttpServletRequest request) {
	return getEntryFormFields(request, DataFormConstants.COUNTY);
    }
    
    /**
     * Populate the establishments field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the establishments field.
     */
    @ModelAttribute("establishmentsItems")
    public List<FormField> populateEstablishments(HttpServletRequest request) {
	return  (new ArrayList<FormField>());
    }
    
    /**
     * Populate the establishments types field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the establishments types field.
     */
    @ModelAttribute("estbalishmentsTypesItems")
    public List<FormField> populateEstablishmentsTypes(HttpServletRequest request) {
	return getEntryFormFields(request, DataFormConstants.ESTABLISHMENTS_TYPES);
    }
        
    /**
     * Populate the "lycees"  types field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the "lycees"  types field.
     */
    @ModelAttribute("laTypesItems")
    public List<FormField> populateLaTypes(HttpServletRequest request) {
	return getEntryFormFields(request, DataFormConstants.LA_TYPES);
    }    
    
    /**
     * Populate the "lycees agricoles"  types field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the "lycees agricoles"  types field.
     */
    @ModelAttribute("lyceesTypesItems")
    public List<FormField> populateLyceesTypes(HttpServletRequest request) {
	return getEntryFormFields(request, DataFormConstants.LYCEES_TYPES);
    }
        
    /**
     * Populate the monitoring  type field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the monitoring  type  field.
     */
    @ModelAttribute("monitoringTypeItems")
    public List<FormField> populateMonitoringType(HttpServletRequest request) {
	return getEntryFormFields(request, DataFormConstants.MONITORING_TYPE);
    }
    
    /**
     * Populate the sum on counties field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the  sum on counties field.<br/>
     * 	<b>NOTE :</b> normaly, only one available value should be present in this returned list.
     */
    @ModelAttribute("sumOnCountiesItems")
    public List<FormField> populateSumOnCounties(HttpServletRequest request) {
	return getEntryFormFields(request, DataFormConstants.SUM_ON_COUNTIES);
    }
    
    /**
     * Populate the users profiles field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the  users profiles field.
     */
    @ModelAttribute("usersProfilesItems")
    public List<FormField> populateUsersProfiles(HttpServletRequest request) {
	return getEntryFormFields(request, DataFormConstants.USERS_PROFILES);
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
	    return "accounts-activations";
	}
	
	// Indication of the last submitted form, and storage of this form
	request.getSession().setAttribute(SessionConstants.LAST_SUBMITTED_FORM_ATTR, SessionConstants.ACCOUNT_FORM_ATTR);
	request.getSession().setAttribute(SessionConstants.ACCOUNT_FORM_ATTR, aaForm);
	
	// Redirection to the result controller
	return "redirect:result";
    }
    

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    /**
     * Gets the items (labels, values, disable states) available for the specified entry.
     * 
     * @param request
     * 			The request of the user.
     * @param entryName
     * 			The name of the entry associated to the items (labels and values).
     * @return
     * 	the available items (labels, values, disable states) for the entry.
     */
    private List<FormField> getEntryFormFields(HttpServletRequest request, String entryName) {
	// Retrieval of the entry values
	List<EntryValue> entries = dataFormService.getEntryValues(entryName);
	
	// Creation of the corresponding items (labels and values)
	List<FormField> formFields = new ArrayList<FormField>();
	for (EntryValue entry : entries) {
	    String label = entry.getI18nKey();
	    String value = entry.getJspKey();
	    
	    FormField formField = new FormField(label, value);
	    formFields.add(formField);
	    if (entry.isDisabledByDefault()) {
		formField.disable();
	    } else {
		formField.enable();
	    }
	}
	return formFields;
    }
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
