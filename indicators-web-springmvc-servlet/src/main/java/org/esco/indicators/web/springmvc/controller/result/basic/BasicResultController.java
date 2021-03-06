/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.result.basic;

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
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.classes.IntegerPair;
import org.esco.indicators.utils.constants.web.RequestParameters;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.utils.date.DateUtils;
import org.esco.indicators.web.springmvc.controller.BasicController;
import org.springframework.beans.factory.annotation.Autowired;
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

    /** Establishment service providing access to establishments data */
    @Autowired
    protected EstablishmentService establishmentService;
    
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
     * Gets the establishment service.
     * 
     * @return
     * 	the establishment service
     */
    public EstablishmentService getEstablishmentService() {
	return establishmentService;
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
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        BasicForm aaForm = getSessionForm(request.getSession(), formSessionAttribute);
        
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
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
       
        // Retrieval of the i18n key
        String [] establishmentsTypes = form.getEstablishmentsTypes();
        List<String> jspKeys = new ArrayList<String>(Arrays.asList(establishmentsTypes));
        List<String> i18nKeys = getI18nKeys(jspKeys);
        
        return i18nKeys;
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
     * Populate the field containing the list of the periods used to index the statistic data in the sub rows.<br/>
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the list of the periods used to index the statistic data.
     */
    @ModelAttribute("statisticPeriodsItems")
    public List<IntegerPair> populatePeriods(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        BasicForm form = getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the establishments types
        List<String> establishmentsTypes = new ArrayList<String>(Arrays.asList(form.getEstablishmentsTypes()));
        
        // Retrieval of the start and end date
        Date startDate = form.getStartDate();
        Date endDate = form.getEndDate();
        
        return getStatisticPeriods(establishmentsTypes, startDate, endDate);
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
     * Gets the first establishment UAI found.<br/>
     * The method will return, in this order :
     * 	<ul>
     * 		<li>The first UAI found in the request parameters, if at least one UAI is present</li>
     * 		<li>The first UAI found in the request session, if at least one UAI is present</li>
     * 		<li><code>null</code> in other cases</li>
     * 	</ul>
     * 
     * @param request
     * 			The request made by the user
     * 
     * @return
     * 	the first UAI found into the request parameters, or the request session<br/>
     * 	<code>null</code> if no UAI has been found
     */
    protected String getEstablishmentUai(HttpServletRequest request) {
	// If the request parameters contains the establishment UAI
	String establishmentUai = request.getParameter(RequestParameters.ESTABLISHMENT_UAI);
	if(establishmentUai != null) {
	    return establishmentUai;
	}
	
	// If the request session contains the establishment UAI
	BasicForm form = getSessionForm(request.getSession(), formSessionAttribute);
	String [] establishmentsUai = form.getEstablishments();
	if(establishmentsUai != null && establishmentsUai.length > 0) {
	    return establishmentsUai[0];
	}
	
	// If no establishment UAI has been found
	return null;
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

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
