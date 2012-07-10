/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.account.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.result.ResultRow;
import org.esco.indicators.domain.beans.util.IntegerPair;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.utils.date.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller handling the requests on the page displaying the results.<br/>
 * The results contained in this page only concern the monitoring type : {@link DataFormConstants#JSP_KEY_MONITORING_ATTENDANCE}.
 * 
 * @since  2012/07/10
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/accounts-activations-monitoring-attendance-result")
public class MonitoringAttendanceResultController extends BasicResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(MonitoringAttendanceResultController.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link MonitoringAttendanceResultController} class.
     * @param viewName
     */
    public MonitoringAttendanceResultController() {
	super("accounts-activations-monitoring-attendance-result");
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    /**
     * Populate the field containing the list of the keys used to index the statistic data in each result row.<br/>
     * In this page, the keys used to index the statistic data are : the statistic periods.<br/>
     * 
     * All the statistic periods are represented by pairs ({@link IntegerPair}).<br/>
     * Each pair contains :
     * <ul>
     * 	<li>First value : number of a week (or month)</li>
     * 	<li>Second value : year (of the first value : week or month)</li>
     * </ul>
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the list of the keys used to index the statistic data.
     */
    @ModelAttribute("statisticDataKeys")
    public List<IntegerPair> populateStatisticDataKeys(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
        
	// Retrieval of the establishments types
	List<String> establishmentsTypes = new ArrayList<String>(Arrays.asList(aaForm.getEstablishmentsTypes()));
	
        // Retrieval of the start and end date
        Date startDate = aaForm.getStartDate();
        Date endDate = aaForm.getEndDate();
        
        return getStatisticPeriods(establishmentsTypes, startDate, endDate);
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
	String userProfileToFilter = dataFormService.getUsersProfilesToFilter(checkedProfiles).get(0);
	
	// Retrieval of the start date and end date
	Date startDate = aaForm.getStartDate();
	Date endDate = aaForm.getEndDate();
	
	// Gets the result rows to display
	List<ResultRow> resultRows = createResultRows(establishmentsTypes, establishmentsUai, userProfileToFilter, startDate, endDate);
	
	return resultRows;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Creates the result rows; each result row containing the following data :
     * <ul>
     * 	<li>The establishment data (name, UAI,..)</li>
     * 	<li>The statistic data (number of connections,...)</li>
     * </ul>
     * 
     * The statistic data are indexed by {@link IntegerPair} containing :
     * <ul>
     * 	<li>First value : the number of a week / month</li>
     * 	<li>Second value : the year of the week</li>
     * </ul>
     * The periods (week/month and year) represented by the pairs are extracted from the original period
     * specified by the <code>startDate</code> and the <code>endDate</code>.
     * 
     * @param establishmentsTypes
     * 			The types of the establishments.
     * @param establishmentsUai
     * 			The UAI of the establishments.
     * @param userProfile
     * 			The user profile concerned by the statistics.
     * @param startDate
     * 			The start date of the statistics.
     * @param endDate
     * 			The end date of the statistics.
     * 
     * @return
     * 	the result rows containing the data to display.
     */
    private List<ResultRow> createResultRows( List<String> establishmentsTypes, List<String> establishmentsUai,String userProfile, Date startDate, Date endDate) {
	// Retrieval of the start and end years
	Integer startYear = DateUtils.getYear(startDate);
	Integer endYear = DateUtils.getYear(endDate);
	
	// Retrieval of the month / or week
	if(	establishmentsTypes.contains(DataFormConstants.JSP_KEY_CFA) 
		&& establishmentsTypes.size() == 1
	) {
	    // If the only selected establishment type is : CFA
	    Integer startWeek = DateUtils.getWeekOfYear(startDate);
	    Integer endWeek = DateUtils.getWeekOfYear(endDate);
	    return resultFormService.getWeeklyResultRows(establishmentsUai, userProfile, startWeek, startYear, endWeek, endYear);
	}
			
	Integer startMonth = DateUtils.getMonthOfYear(startDate);
	Integer endMonth = DateUtils.getMonthOfYear(endDate);
	return resultFormService.getMonthlyResultRows(establishmentsUai, userProfile, startMonth, startYear, endMonth, endYear);
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
    private List<IntegerPair> getStatisticPeriods(List<String> establishmentsTypes, Date startDate, Date endDate) {
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
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
