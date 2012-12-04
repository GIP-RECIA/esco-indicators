/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.basic.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.web.RequestParameters;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.web.springmvc.controller.basic.BasicController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Basic form controller providing functions to populate data which are common to all the forms.
 * 
 * @since  2012/07/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public abstract class BasicFormController extends BasicController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicFormController.class);
    
    /** Name of the attribute used to store the submitted form in the user session */
    protected String formSessionAttribute;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link BasicFormController}.
     * 
     * @param formSessionAttribute
     * 			The name of the attribute used to store the submitted form in the user session.
     */
    public BasicFormController(String formSessionAttribute) {
	super();
	this.formSessionAttribute = formSessionAttribute;
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the data form service of the form controller.
     * 
     * @return
     * 	the data form service of the form controller
     */
    public abstract DataFormService getDataFormService();
    
    /**
     * Gets the validator of the form.
     * 
     * @return
     * 	the form validator
     */
    public abstract Validator getValidator();
    
    /**
     * Gets the name of the view name to use when the form validation is a failure.<br/>
     * This name can depend on the values present in the submitted form.
     * 
     * @param unvalidForm
     * 			The submitted form.
     * 
     * @return
     * 	the name of the failure view
     */
    public abstract String getFailureViewName(BasicForm unvalidForm);
    
    /**
     * Gets the name of the view name to use when the form validation is a success.<br/>
     * This name can depend on the values present in the submitted form.
     * 
     * @param validForm
     * 			The submitted form.
     * 
     * @return
     * 	the name of the success view
     */
    public abstract String getSuccessViewName(BasicForm validForm);
    
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
     * Populate the counties field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the available values for the  counties field.<br/>
     */
    @ModelAttribute("countyItems")
    public List<FormField> populateCounties(HttpServletRequest request) {
 	return getEntryFormFields(DataFormConstants.COUNTY);
    }
    
    /**
    * Populate the posted establishments field.<br/>
    * This filed is used to know which establishments have been posted during the last form submission.
    * 
    * @param request
    * 			The request made by the user.
    * @return
    * 		the last posted establishments.
    */
   @ModelAttribute("postedEstablishmentsItems")
   public List<String> populateEstablishments(HttpServletRequest request) {
       // Retrieval of the posted establishments
       String [] postedEstablishments = request.getParameterValues(RequestParameters.ESTABLISHMENTS);
       
	// If no establishments have already been posted
	if(postedEstablishments == null || postedEstablishments.length == 0) {
	    LOGGER.debug("No establishments have already been posted");
	    return  (new ArrayList<String>());
	}
	
	// Populates the establishments in order to select them in the view
	LOGGER.debug("Some establishments have already been posted");
        List<String> establishments = new ArrayList<String>(Arrays.asList(postedEstablishments));
     	return establishments;
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
	return getEntryFormFields(DataFormConstants.ESTABLISHMENTS_TYPES);
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
	return getEntryFormFields(DataFormConstants.LA_TYPES);
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
	return getEntryFormFields(DataFormConstants.LYCEES_TYPES);
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
	return getEntryFormFields(DataFormConstants.MONITORING_TYPE);
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
	return getEntryFormFields(DataFormConstants.SUM_ON_COUNTIES);
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
	return getEntryFormFields(DataFormConstants.USERS_PROFILES);
   }
   
   /**
    * Validates and processes the submitted form.
    * 
    * @param request
    * 			The request made by the user.
    * @param form
    * 			The submitted form.
    * @param result
    * 			The result of the binding (containing fields values).
    * @param status
    * 			The session status.
    * @return
    * 		the JSP view name.
    */
   public String processSubmit(HttpServletRequest request, BasicForm form, BindingResult result, SessionStatus status) {
	// Log of the submitted form
       LOGGER.debug("Submitted form : [" + form.toString() +"]");
	
	// Indication of the last submitted form, and storage of this form
	request.getSession().setAttribute(SessionConstants.LAST_SUBMITTED_FORM_ATTR, formSessionAttribute);
	request.getSession().setAttribute(formSessionAttribute, form);
	
	// Validation of the form
	Validator validator = getValidator();
	LOGGER.debug("The class of the called validator is : [" + validator.getClass() +"]");
	validator.validate(form, result);
	
	if(result.hasErrors()) {
	    LOGGER.debug("The submitted form has not been validated due to the following errors : [" + result.getFieldErrors() + "]");
	    return getFailureViewName(form);
	}
		
	// Redirection to the result controller
	return getSuccessViewName(form);
   }
    
    //--------------------------------------------------------------------------- PROTECTED METHODS
    /**
     * Gets the items (labels, values, disable states) available for the specified entry.
     * @param entryName
     * 			The name of the entry associated to the items (labels and values).
     * 
     * @return
     * 	the available items (labels, values, disable states) for the entry.
     */
    protected List<FormField> getEntryFormFields(String entryName) {
	// Retrieval of the entry values
	List<EntryValue> entries = getDataFormService().getEntryValues(entryName);
	
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
    
    
    /**
     * Only keeps the establishments types form fields authorized for the user.<br/>
     * The authorized establishments types are retrieved through the authenticator.
     * 
     * @param establishmentsTypesFields
     * 			The establishments types fields to test.
     * 
     * @return
     * 	a copy of the given list only containing the authorized forms fields.<br/>
     * 	an empty list if no establishments types are authorized.
     */
    protected List<FormField> keepAuthorizedEstablishmentsTypes(List<FormField> establishmentsTypesFields) {
	// Final result
	List<FormField> authorizedEstablishmentsTypes = new ArrayList<FormField>();
	
	// Retrieves the establishments types to filter and verifies if the user
	// has the right to see these types
	for (FormField establishmentsType : establishmentsTypesFields) {
	    String jspKey = establishmentsType.getValue();
	    String establishmentTypeToFilter = getDataFormService().getEstablishmentTypeToFilter(jspKey);
	    // Checks if the user has rights on this establishment type
	    if(authenticator.hasPermissionOnEstablishmentsType(establishmentTypeToFilter)) {
		authorizedEstablishmentsTypes.add(establishmentsType);
	    }
	}
	
	return authorizedEstablishmentsTypes;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS

}
