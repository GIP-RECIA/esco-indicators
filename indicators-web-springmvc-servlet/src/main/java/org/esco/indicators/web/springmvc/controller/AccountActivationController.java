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
import org.esco.indicators.domain.beans.form.LabelValue;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.esco.indicators.services.form.DataFormService;
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
    
    /** Service providing access to internationalized strings */
    @Autowired
    private I18nService i18nService;
    
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

    /**
     * Sets the service providing access to i18n string.
     * 
     * @param i18nService 
     * 			The service providing access to i18n string to set.
     */
    public void setI18nService(I18nService i18nService) {
        this.i18nService = i18nService;
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
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
    public List<LabelValue> populateCounty(HttpServletRequest request) {
	return getEntryLabelValues(request, DataFormConstants.COUNTY);
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
    public List<LabelValue> populateEstablishments(HttpServletRequest request) {
	Establishment estab = new Establishment(45, "0458751U", "CFA");
	estab.setName("CFA des Sports");
	estab.setSiren("4515452");
	
	List<LabelValue> labels = new ArrayList<LabelValue>();
	labels.add(new LabelValue(estab.getName(), estab.getUai()));
	
	return labels;
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
    public List<LabelValue> populateEstablishmentsTypes(HttpServletRequest request) {
	return getEntryLabelValues(request, DataFormConstants.ESTABLISHMENTS_TYPES);
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
    public List<LabelValue> populateLaTypes(HttpServletRequest request) {
	return getEntryLabelValues(request, DataFormConstants.LA_TYPES);
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
    public List<LabelValue> populateLyceesTypes(HttpServletRequest request) {
	return getEntryLabelValues(request, DataFormConstants.LYCEES_TYPES);
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
    public List<LabelValue> populateMonitoringType(HttpServletRequest request) {
	return getEntryLabelValues(request, DataFormConstants.MONITORING_TYPE);
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
    public List<LabelValue> populateUsersProfiles(HttpServletRequest request) {
	return getEntryLabelValues(request, DataFormConstants.USERS_PROFILES);
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
	
	System.out.println(aaForm);
	
	// Validation of the form
	accountActivationValidator.validate(aaForm, result);
	
	if(result.hasErrors()) {
	    return "accounts-activations";
	}
	return "accounts-activations-success";
    }
    

    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Gets the web user locale from the request.
     * 
     * @param request
     * 			The request made by the web user.
     * @return
     * 	the locale of the web user.
     */
    private Locale getLocale(HttpServletRequest request) {
	RequestContext requestContext = new RequestContext(request);
	Locale locale = requestContext.getLocale();
	return locale;
    }
    
    /**
     * Gets the items (labels and values) available for the specified entry.
     * 
     * @param request
     * 			The request of the user.
     * @param entryName
     * 			The name of the entry associated to the items (labels and values).
     * @return
     * 	the available items (labels and values) for the entry.
     */
    private List<LabelValue> getEntryLabelValues(HttpServletRequest request, String entryName) {
	// Retrival of the locale
	Locale locale = getLocale(request);
	
	// Retrieval of the entry values
	List<EntryValue> entries = dataFormService.getEntryValues(entryName);
	
	// Creation of the corresponding items (labels and values)
	List<LabelValue> labelValues = new ArrayList<LabelValue>();
	for (EntryValue entry : entries) {
	    String label = i18nService.getString(entry.getI18nKey(), locale);
	    String value = entry.getJspKey();
	    
	    LabelValue labelValue = new LabelValue(label, value);
	    labelValues.add(labelValue);
	}
	return labelValues;
    }
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
