/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.form.basic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.esco.indicators.utils.constants.web.RequestParameters;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.web.springmvc.controller.BasicController;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Basic form controller providing functions to populate data which are common to all the forms.
 * 
 * @since  2012/07/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Component
public abstract class BasicFormController extends BasicController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicFormController.class);
    
    /** Name of the attribute used to store the submitted form in the user session */
    protected String formSessionAttribute;

    /** Validator used for the form validation */
    protected Validator formValidator;
    
    /** The name of the form associated to the controller */
    protected String formName;
    
    /** The name of the form view to display to a normal user */
    protected String userFormViewName;
    
    /** The name of the form view to display to a super user */
    protected String superUserFormViewName;
    
    /** The name of the form view to display on form validation success */
    protected String successViewName;
    
    /** The name of the form view to display on form validation failure */
    protected String failureViewName;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link BasicFormController}.
     * 
     * @param formSessionAttribute
     * 			The name of the attribute used to store the submitted form in the user session.
     * @param formName
     * 			The name of the form associated to the controller
     * @param userFormViewName
     * 			The name of the form view to display to a normal user
     * @param superUserFormViewName
     * 			The name of the form view to display to a super user
     * @param successViewName
     * 			The name of the form view to display on form validation success
     * @param failureViewName 
     * 			The name of the form view to display on form validation failure
     */
    public BasicFormController(String formSessionAttribute, String formName, String userFormViewName, String superUserFormViewName, String successViewName, String failureViewName) {
	super();
	this.formSessionAttribute = formSessionAttribute;
	this.formName = formName;
	this.userFormViewName = userFormViewName;
	this.superUserFormViewName = superUserFormViewName;
	this.successViewName = successViewName;
	this.failureViewName = failureViewName;
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the name of the view used for a denied access.<br/>
     * This view is used when the user has no right on the page.
     * 
     * @return
     * 	the name of the 
     */
    public String getAccessDeniedViewName() {
	return "access-denied";
    }
    
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
    public String getFailureViewName(BasicForm unvalidForm) {
	return failureViewName;
    }
    
    /**
     * Gets the name of the form used for the ModelMap.
     * 
     * @return
     * 	the name of the form
     */
    public String getFormName() {
	return formName;
    }
    
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
    public String getSuccessViewName(BasicForm validForm) {
	return successViewName;
    }
    
    /**
     * Gets the name of the form view used for a super user.<br/>
     * 
     * @return
     * 	the name of the form view for a super user
     */
    public String getSuperUserFormViewName() {
	return superUserFormViewName;
    }
    
    /**
     * Gets the name of the form view used for a normal user.<br/>
     * A normal user is a non-super user.
     * 
     * @return
     * 	the name of the form view for a normal user
     */
    public String getUserFormViewName(){
	return userFormViewName;
    }
    
    /**
     * Gets the validator of the form.
     * 
     * @return
     * 	the form validator
     */
    public Validator getFormValidator() {
	return formValidator;
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
	BasicForm aaForm = getSessionForm(request.getSession(), formSessionAttribute);
	model.addAttribute(getFormName(), aaForm);
	
	// If the user is a super user, then display the super user form
	if(authenticator.isSuperUser()) {
	    return getSuperUserFormViewName();
	}
	
	// If the user has the right to see the normal form for his current establishment
	String establishmentUAI = authenticator.getUser().getEstablishmentUAI();
	if(authenticator.hasPermissionOnEstablishment(establishmentUAI)) {
	    return getUserFormViewName();
	}
	
        // If the user has no right
	return getAccessDeniedViewName();
    }
    
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
       ////////////////////////////////////////////////////
       // Retrieval of establishments in the request
       ////////////////////////////////////////////////////
       // Retrieval of the posted establishments
       String [] postedEstablishments = request.getParameterValues(RequestParameters.ESTABLISHMENTS);
	// If at least one establishment has already been posted
	if(postedEstablishments != null && postedEstablishments.length > 0) {
	    LOGGER.debug("At least one establishment has been retrieved in the request parameters");
	    List<String> establishments = new ArrayList<String>(Arrays.asList(postedEstablishments));
	    return establishments;
	}
	
	////////////////////////////////////////////////////
	// Retrieval of establishments in the session
	////////////////////////////////////////////////////
       // Retrieval of the submitted form
       BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
       // Retrieval of the establishments
       String [] formEstablishments = ( form.getEstablishments() == null ? new String [0] : form.getEstablishments() );
       if(formEstablishments.length > 0) {
	   LOGGER.debug("At least one establishment has been retrieved in the session");
       }
       List<String> establishments = new ArrayList<String>(Arrays.asList(formEstablishments));
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
	List<FormField> establishmentsTypes = getEntryFormFields(DataFormConstants.ESTABLISHMENTS_TYPES);
	establishmentsTypes = keepAuthorizedEstablishmentsTypes(establishmentsTypes);
	return establishmentsTypes;
   }
       
   /**
    * Populate the "la"  types field.
    * 
    * @param request
    * 			The request made by the user.
    * @return
    * 		the available values for the "la"  types field.
    */
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
    * 		the available values for the "lycees agricoles"  types field.
    */
   @ModelAttribute("lyceesTypesItems")
   public List<FormField> populateLyceesTypes(HttpServletRequest request) {
	List<FormField> lyceesTypes = getEntryFormFields(DataFormConstants.LYCEES_TYPES);
	lyceesTypes = keepAuthorizedEstablishmentsTypes(lyceesTypes);
	return lyceesTypes;
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
	List<FormField> usersProfiles = getEntryFormFields(DataFormConstants.USERS_PROFILES);
	return keepAuthorizedUsersProfiles(usersProfiles);
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
	Validator validator = getFormValidator();
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
     * Creates the form fields composed of the pair (jsp key, i18n) for the given jsp keys.<br/>
     * The i18n put into the form field is the i18n associated to the jep key in the data form service.
     * 
     * @param jspKeys
     * 			The jsp keys to associate to their respective i18n.
     * 
     * @return
     * 	the jsp keys associated to their respective i18n.
     */
    protected List<FormField> jspKeysToFormFields(List<String> jspKeys) {
	// Final result
	List<FormField> formFields = new ArrayList<FormField>();
	
	// Creates the form fields composed of (jsp key, i18n)
	for (String jspKey : jspKeys) {
	    String i18n = getDataFormService().getI18nKey(jspKey);
	    FormField formField = new FormField(i18n, jspKey);
	    formFields.add(formField);
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
	
	// Retrieves the name of the entries associated to the establishments types 
	// and verifies if the user has the right to see these types
	for (FormField establishmentsType : establishmentsTypesFields) {
	    String jspKey = establishmentsType.getValue();
	    String establishmentType = getDataFormService().getEntryName(jspKey);
	    // Checks if the user has rights on this establishment type
	    if(authenticator.hasPermissionOnEstablishmentsType(establishmentType)) {
		authorizedEstablishmentsTypes.add(establishmentsType);
	    }
	}
	
	return authorizedEstablishmentsTypes;
    }
    
    /**
     * Only keeps the services form fields authorized for the user.<br/>
     * The authorized servces are retrieved through the authenticator.
     * 
     * @param servicesFields
     * 			The services fields to test.
     * 
     * @return
     * 	a copy of the given list only containing the authorized forms fields.<br/>
     * 	an empty list if no service is authorized.
     */
    protected List<FormField> keepAuthorizedServices(List<FormField> servicesFields) {
	// Final result
	List<FormField> authorizedServices = new ArrayList<FormField>();
	
	// Retrieves the name of the entries associated to the services
	// and verifies if the user has the right to see them
	for (FormField serviceField : servicesFields) {
	    String jspKey = serviceField.getValue();
	    String serviceName = getDataFormService().getEntryName(jspKey);
	    // Checks if the user has rights on this service
	    if(authenticator.hasPermissionOnService(serviceName)) {
		authorizedServices.add(serviceField);
	    }
	}
	
	return authorizedServices;
    }
    
    /**
     * Only keeps the users profiles form fields authorized for the user.<br/>
     * The authorized users profiles are retrieved through the authenticator.
     * 
     * @param usersProfilesFields
     * 			The users profiles fields to test.
     * 
     * @return
     * 	a copy of the given list only containing the authorized forms fields.<br/>
     * 	an empty list if no users profiles are authorized.
     */
    protected List<FormField> keepAuthorizedUsersProfiles(List<FormField> usersProfilesFields) {
	// Final result
	List<FormField> authorizedProfiles = new ArrayList<FormField>();

	// Gets the JSP keys present in the form fields
	List<String> profilesJspKeys = new ArrayList<String>();
	for (FormField userProfileField : usersProfilesFields) {
	    profilesJspKeys.add(userProfileField.getValue());
	}
	
	// Keeps the authorized JSP keys
	List<String> authorizedJspKeys = keepAuthorizedJspKeysForUsersProfiles(profilesJspKeys);
	
	// Keeps the auhtorized form fields
	for (FormField userProfileField : usersProfilesFields) {
	    String profileJspKey = userProfileField.getValue();
	    if(authorizedJspKeys.contains(profileJspKey)) {
		authorizedProfiles.add(userProfileField);
	    }
	}

	return authorizedProfiles;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS

}
