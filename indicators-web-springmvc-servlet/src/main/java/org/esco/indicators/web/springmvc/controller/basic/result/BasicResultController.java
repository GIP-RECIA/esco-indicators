/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.basic.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.classes.IntegerPair;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.utils.date.DateUtils;
import org.esco.indicators.web.springmvc.controller.basic.BasicController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Basic controller used to populate commons fields in the result controllers displaying
 * the results after a form submission.
 * 
 * @since  2012/07/10
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public abstract class BasicResultController extends BasicController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicResultController.class);

    /** Name of the attribute used to retrieve the submitted form in the user session */
    protected String formSessionAttribute;
    
    /** Name of the view associated to the controller */
    protected String viewName;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of {@link BasicResultController}.
     * 
     * @param viewName
     * 			The view name associated to the controller.
     * @param formSessionAttribute
     * 			The session attribute containing the submitted form.
     */
    public BasicResultController(String viewName, String formSessionAttribute) {
	super();
	this.viewName = viewName;
	this.formSessionAttribute = formSessionAttribute;
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the data form service.
     * 
     * @return
     * 	the data form service
     */
    public abstract DataFormService getDataFormService(); 
    
    /**
     * Gets the establishment service.
     * 
     * @return
     * 	the establishment service
     */
    public abstract EstablishmentService getEstablishmentService(); 
    
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
	if(!containsForm(request.getSession(), formSessionAttribute)) {
	    LOGGER.warn("No submitted form has been found in the user session, so there is no data to process and display. The user is redirected to the welcome page.");
	    return "redirect:welcome";
	}
	
	return viewName;
    }


    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
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
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String jspKey = form.getCounty();
        String i18nKey = null;
        if(jspKey != null) {
            i18nKey = getDataFormService().getI18nKey(jspKey);
        }
    
        return i18nKey;
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
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
       
        // Retrieval of the i18n key
        String [] establishmentsTypes = form.getEstablishmentsTypes();
        List<String> jspKeys = new ArrayList<String>(Arrays.asList(establishmentsTypes));
        List<String> i18nKeys = getI18nKeys(jspKeys);
        
        return i18nKeys;
    }
    
    /**
     * Populates a map that contained the users profilesto filter and their i18n keys.<br/>
     * The keys of this map are : the users profiles to filter.
     * 
     * @param request
     * 			The request made by the user.
     * 
     * @return
     * 	a map containing the JSP keys associated to their i18n keys.
     */
    @ModelAttribute("i18nUsersProfiles")
    public Map<String, String> populateUsersProfilesI18nKeys(HttpServletRequest request) {
	// Final result
	Map<String, String> i18nKeysByJspKeys = new HashMap<String, String>();
	
	// Retrieval of the i18n keys for the users profiles  to filter
	List<String> allUsersProfiles = getAllUsersProfiles();
	for (String userProfile : allUsersProfiles) {
	    i18nKeysByJspKeys.put(userProfile, getDataFormService().getI18nKey(userProfile));
	}
	
	return i18nKeysByJspKeys;
    }
    
    /**
     * Indicates if the periods are weekly.
     * 
     * @param request
     * 			The request made by the user.
     * 
     * @return
     * 	<code>true</code> if the periods are weekly.<br/>
     * 	<code>false</code> in other cases.
     */
    @ModelAttribute("isWeekly")
    public Boolean populateIsWeekly(HttpServletRequest request) {
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String [] establishmentsTypes = form.getEstablishmentsTypes();
        List<String> jspKeys = new ArrayList<String>(Arrays.asList(establishmentsTypes));
        
        // Only CFA establishment type is selected ?
        return (jspKeys.size() == 1 && jspKeys.contains(DataFormConstants.JSP_KEY_CFA));
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
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String [] lyceesTypes = form.getLyceesTypes();
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
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String [] lyceesTypes = form.getLaTypes();
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
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String jspKey = form.getMonitoringType();
        String i18nKey = getDataFormService().getI18nKey(jspKey);
        
        return i18nKey;
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
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the start date
        String startDate = form.getStartDatePicker();
        
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
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String jspKey = form.getSumOnCounties();
        String i18nKey = null;
        if(jspKey != null) {
            i18nKey = getDataFormService().getI18nKey(jspKey);
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
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String [] usersProfiles = form.getUsersProfiles();
        List<String> jspKeys = new ArrayList<String>(Arrays.asList(usersProfiles));
        List<String> i18nKeys = getI18nKeys(jspKeys);
        
        return i18nKeys;
    }

    
    //--------------------------------------------------------------------------- PROTECTED METHODS
    /**
     * Gets the JSP keys corresponding to all the available users profiles.
     * 
     * @return
     * 	the list containing all the available users profiles JSP keys.
     */
    protected List<String> getAllUsersProfiles() {
	// Final result
	List<String> allUsersProfiles = new ArrayList<String>();
	
	List<EntryValue> entryValues = getDataFormService().getEntryValues(DataFormConstants.USERS_PROFILES);
	for (EntryValue entryValue : entryValues) {
	    allUsersProfiles.add(entryValue.getJspKey());
	}
	
	return allUsersProfiles;
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
    protected List<String> getI18nKeys(List<String> jspKeys) {
        // Retrieval of the i18n keys
        List<String> i18nKeys = new ArrayList<String>();
        for (String jspKey : jspKeys) {
            i18nKeys.add(getDataFormService().getI18nKey(jspKey));
        }
        return i18nKeys;
    }
    
    /**
     * Gets the statistic periods present in the specified period starting with the <code>startDate</code> and ending with the <code>endDate</code>.<br/>
     * These periods will be split by weeks if the only selected establishment type is {@link DataFormConstants#JSP_KEY_CFA}.<br/>
     * Else, the periods will be split by months.
     * 
     * @param establishmentsTypes
     * 			The establishments types selected in the user view.
     * @param startDate
     * 			The start
     * @param endDate
     * 
     * @return
     * 	the statistic periods.
     */
    protected List<IntegerPair> getStatisticPeriods(List<String> establishmentsTypes, Date startDate, Date endDate) {
	// Get the start and end years
	Integer startYear = DateUtils.getYear(startDate);
	Integer endYear = DateUtils.getYear(endDate);
	
	// Retrieval of the periods by month / or by week
	if(	establishmentsTypes.contains(DataFormConstants.JSP_KEY_CFA) 
		&& establishmentsTypes.size() == 1
	) {
	    // If the only selected establishment type is : CFA
	    Integer startWeek = DateUtils.getWeekOfYear(startDate);
	    Integer endWeek = DateUtils.getWeekOfYear(endDate);
	    return DateUtils.splitWeeks(startWeek, startYear, endWeek, endYear);
	}
	   
	Integer startMonth = DateUtils.getMonthOfYear(startDate);
	Integer endMonth = DateUtils.getMonthOfYear(endDate);
	return DateUtils.splitMonths(startMonth, startYear, endMonth, endYear);
    }
    
    /**
     * Only keeps the jsp keys (associated to users profiles) which are authorized
     * for the authenticated user.
     * 
     * @param jspKeys
     * 			The jsp keys (associated to users profiles) to test.
     * 
     * @return
     * 	the authorized jsp keys.<br/>
     * 	an empty list if no jsp is authorized.
     */
    protected List<String> keepAuthorizedJspKeysForUsersProfiles(List<String> jspKeys) {
	// Final result
	List<String> authorizedUsersProfilesKeys =  new ArrayList<String>();
	
	// Retrieves the authorized jsp keys (associated to users profiles)
	for (String jspKey : jspKeys) {
	    String userProfile = getDataFormService().getEntryName(jspKey);
	    // Checks if the user has rights on this user profile
	    if(authenticator.hasPermissionOnUserProfile(userProfile)) {
		authorizedUsersProfilesKeys.add(jspKey);
	    }
	}
	
	return authorizedUsersProfilesKeys;
    }
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
