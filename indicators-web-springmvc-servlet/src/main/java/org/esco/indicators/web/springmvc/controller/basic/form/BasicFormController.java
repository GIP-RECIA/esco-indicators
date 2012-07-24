/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.basic.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Class representing a basic form controller
 * 
 * @since  2012/07/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public abstract class BasicFormController {
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
    * 	the JSP view name.
    */
   public String processSubmit(HttpServletRequest request, BasicForm form, BindingResult result, SessionStatus status) {
	// Log of the submitted form
	if(LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Submitted form : " + form.toString());
	}
	
	// Validation of the form
	getValidator().validate(form, result);
	
	if(result.hasErrors()) {
	    return getFailureViewName(form);
	}
	
	// Indication of the last submitted form, and storage of this form
	request.getSession().setAttribute(SessionConstants.LAST_SUBMITTED_FORM_ATTR, formSessionAttribute);
	request.getSession().setAttribute(formSessionAttribute, form);
	
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
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS

}