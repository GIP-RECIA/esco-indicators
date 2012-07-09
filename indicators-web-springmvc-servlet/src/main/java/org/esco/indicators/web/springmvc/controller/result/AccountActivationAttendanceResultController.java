/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.result.ResultRow;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.form.ResultFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.utils.date.DateUtils;
import org.esco.indicators.web.springmvc.controller.BasicController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.icu.util.Calendar;

/**
 * Controller handling the requests on the page displaying the results of a form submission.
 * 
 * @since  2012/06/29
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/accounts-activations-attendance-result")
public class AccountActivationAttendanceResultController extends BasicController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(AccountActivationAttendanceResultController.class);

    /** Establishment service providing access to establishments data */
    @Autowired
    private EstablishmentService establishmentService;
    
    /** Service providing access to result data */
    @Autowired
    private ResultFormService resultFormService;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link AccountActivationAttendanceResultController} class.
     */
    public AccountActivationAttendanceResultController() {
	super();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
   /**
     * Sets the service providing access to establisments data.
     * @param establishmentService 
     * 			The ervice providing access to establisments data to set.
     */
    public void setEstablishmentService(EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
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
     * Sets the service providing access to result data.
     * 
     * @param resultFormService 
     * 			The service providing access to result data to set.
     */
    public void setResultFormService(ResultFormService resultFormService) {
        this.resultFormService = resultFormService;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS

    /**
     * Initializes the result view and redirect to the view.
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
	// Verification of the presence of a submitted form
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    LOGGER.warn("No submitted form has been found in the user session, so there is no data to process and display. The user is redirected to the welcome page.");
	    return "redirect:welcome";
	}
	
        // Cleaning of the session
	request.getSession().setAttribute(SessionConstants.ACCOUNT_FORM_ATTR, null);
	return "accounts-activations-attendance-result";
    }
    

    /**
     * Populate the county field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted value for the county field.
     */
    @ModelAttribute("countyItem")
    public String populateCounty(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted monitoring type value
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the i18n key
	String jspKey = aaForm.getCounty();
	String i18nKey = null;
	if(jspKey != null) {
	    i18nKey = dataFormService.getI18nKey(jspKey);
	}

	return i18nKey;
    }
    
    /**
     * Populate the end date field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted value for the end date field.
     */
    @ModelAttribute("endDateItem")
    public String populateEndDate(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted monitoring type value
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the end date
	String endDate = aaForm.getEndDatePicker();
	
	return endDate;
    }
    
    /**
     * Populate the establishments types field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted values for the establishments types field.
     */
    @ModelAttribute("estbalishmentsTypesItems")
    public List<String> populateEstablishmentsTypes(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted monitoring type value
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the i18n key
	String [] establishmentsTypes = aaForm.getEstablishmentsTypes();
	List<String> jspKeys = new ArrayList<String>(Arrays.asList(establishmentsTypes));
	List<String> i18nKeys = getI18nKeys(jspKeys);
	
	return i18nKeys;
    }
    
    /**
     * Populate the field containing the list of the filtered users profiles.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the users profiles that have been filtered.
     */
    @ModelAttribute("filteredUsersProfilesItems")
    public List<String> populateFilteredUsersProfiles(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted monitoring type value
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the users profiles to filter
	List<String> checkedProfiles = new ArrayList<String>(Arrays.asList(aaForm.getUsersProfiles()));
	List<String> usersProfilesToFilter = dataFormService.getUsersProfilesToFilter(checkedProfiles);
	
	return usersProfilesToFilter;
    }
    
    /**
     * Populate the 'lycees' types field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted values for the 'lycees' types field.
     */
    @ModelAttribute("lyceesTypesItems")
    public List<String> populateLyceesTypes(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted monitoring type value
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the i18n key
	String [] lyceesTypes = aaForm.getLyceesTypes();
	List<String> i18nKeys = null;
	if(lyceesTypes != null) {
		List<String> jspKeys = new ArrayList<String>(Arrays.asList(lyceesTypes));
		i18nKeys = getI18nKeys(jspKeys);
	}
	
	return i18nKeys;
    }
    
    /**
     * Populate the 'la' types field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted values for the 'la' types field.
     */
    @ModelAttribute("laTypesItems")
    public List<String> populateLaTypes(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted monitoring type value
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the i18n key
	String [] lyceesTypes = aaForm.getLaTypes();
	List<String> i18nKeys = null;
	if(lyceesTypes != null) {
		List<String> jspKeys = new ArrayList<String>(Arrays.asList(lyceesTypes));
		i18nKeys = getI18nKeys(jspKeys);
	}
	
	return i18nKeys;
    }
    
    /**
     * Populate the monitoring  type field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted value for the monitoring  type  field.
     */
    @ModelAttribute("monitoringTypeItem")
    public String populateMonitoringType(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted monitoring type value
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the i18n key
	String jspKey = aaForm.getMonitoringType();
	String i18nKey = dataFormService.getI18nKey(jspKey);
	
	return i18nKey;
    }
    
    /**
     * Populates the data rows of the table used to display the result of the submitted form.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the data rows of the table used to display the result of the submitted form.
     */
    @ModelAttribute("tableRowsItems")
    public List<ResultRow> populateTableRows(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted form
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the establishments uai
	List<String> establishmentsUai = new ArrayList<String>(Arrays.asList(aaForm.getEstablishments()));
	
	// Retrieval of the establishments types
	List<String> establishmentsTypes = new ArrayList<String>(Arrays.asList(aaForm.getEstablishmentsTypes()));
	
	// Retrieval of the users profiles to filter
	List<String> checkedProfiles = new ArrayList<String>(Arrays.asList(aaForm.getUsersProfiles()));
	List<String> usersProfilesToFilter = dataFormService.getUsersProfilesToFilter(checkedProfiles);
	
	// Retrieval of the start date
	Date startDate = aaForm.getStartDate();
	
	// Gets the result rows to display
	List<ResultRow> resultRows = getResultRows(establishmentsTypes, establishmentsUai, usersProfilesToFilter, startDate);
	
	return resultRows;
    }
    
    /**
     * Populate the start date field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted value for the start date field.
     */
    @ModelAttribute("startDateItem")
    public String populateStartDate(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted monitoring type value
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the start date
	String startDate = aaForm.getStartDatePicker();
	
	return startDate;
    }
    
    /**
     * Populate the sum on counties field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted value for the sum on counties field.
     */
    @ModelAttribute("sumOnCountiesItem")
    public String populateSumOnCounties(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted monitoring type value
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the i18n key
	String jspKey = aaForm.getSumOnCounties();
	String i18nKey = null;
	if(jspKey != null) {
	    i18nKey = dataFormService.getI18nKey(jspKey);
	}
	
	return i18nKey;
    }
    
    /**
     * Populate the users profiles field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted values for the users profiles field.
     */
    @ModelAttribute("usersProfilesItems")
    public List<String> populateUsersProfiles(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted monitoring type value
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the i18n key
	String [] usersProfiles = aaForm.getUsersProfiles();
	List<String> jspKeys = new ArrayList<String>(Arrays.asList(usersProfiles));
	List<String> i18nKeys = getI18nKeys(jspKeys);
	
	return i18nKeys;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    /**
     * Checks if the specified session contains a form associated to the session attribute <code>formAttribute</code>.<br/>
     * 
     * @param session
     * 			The user session.
     * @param formAttribute
     * 			The session attribute associated to the form.
     * 
     * @return
     * 	<code>true</code> if the session contained a form associated to <code>formAttribute</code>.<br/>
     * 	<code>false</code> in other cases.
     */
    private boolean containsForm(HttpSession session, String formAttribute) {
	// Retrieval of the associated form
	BasicForm basicForm = (BasicForm) session.getAttribute(formAttribute);
	if(basicForm == null) {
	    return false;
	}
	return true;
    }
    
    /**
     * Gets the form associated to the session attribute : <code>formAttribute</code>.
     * 
     * @param session
     * 			The user session.
     * @param formAttribute
     * 			The session attribute associated to the form.
     * 
     * @return
     * 	the form associated to the session attribute : <code>formAttribute</code>.<br/>.
     * 	<code>null</code> if no form is associated to the session attribute.
     */
    private BasicForm getSessionForm(HttpSession session, String formAttribute) {
	// Retrieval of the form
	return (BasicForm) session.getAttribute(formAttribute);
    }
    
    /**
     * Gets the i18n keys associated to the specified JSP keys.
     * 
     * @param jspKeys
     * 			The JSP keys associated to the i18n keys to retrieve.
     * 
     * @return
     * 	the list of the i18n keys associated to the JSP keys.<br/>
     * 	an empty list if no i18n keys has been retrieved.
     */
    private List<String> getI18nKeys(List<String> jspKeys) {
	// Retrieval of the i18n keys
	List<String> i18nKeys = new ArrayList<String>();
	for (String jspKey : jspKeys) {
	    i18nKeys.add(dataFormService.getI18nKey(jspKey));
	}
	return i18nKeys;
    }
    
    /**
     * Creates the result row; each result row containing the following data :
     * <ul>
     * 	<li>The establishment data (name, UAI,..)</li>
     * 	<li>The statistic data (number of connections,...)</li>
     * </ul>
     * 
     * @param establishmentsTypes
     * 			The types of the establishments.
     * @param establishmentsUai
     * 			The UAI of the establishments.
     * @param usersProfiles
     * 			The users profiles concerned by the statistics.
     * @param startDate
     * 			The start date of the statistics.
     * 
     * @return
     * 	the result rows containing the data to display.
     */
    private List<ResultRow> getResultRows( List<String> establishmentsTypes, List<String> establishmentsUai,List<String> usersProfiles, Date startDate) {
	// Final result
	List<ResultRow> rows =  new ArrayList<ResultRow>();
	
	// Retrieval of the year
	Integer year = DateUtils.getYear(startDate);
	
	// Retrieval of the month / or week
	if(	establishmentsTypes.contains(DataFormConstants.JSP_KEY_CFA) 
		&& establishmentsTypes.size() == 1
	) {
	    // If the only selected establishment type is : CFA
	    Integer week = DateUtils.getWeekOfYear(startDate);
	    rows.addAll(resultFormService.getPunctualWeekResultRows(establishmentsUai, usersProfiles, week, year));
	} else {
	    Integer month = DateUtils.getMonthOfYear(startDate);
	    rows.addAll(resultFormService.getPunctualMonthResultRows(establishmentsUai, usersProfiles, month, year));
	}
	
	return rows;
    }
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
