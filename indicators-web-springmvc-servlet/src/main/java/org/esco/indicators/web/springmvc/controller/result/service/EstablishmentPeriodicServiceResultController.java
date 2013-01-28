/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.result.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.result.DetailResultRow;
import org.esco.indicators.domain.beans.result.ExtendedResultRow;
import org.esco.indicators.utils.classes.IntegerPair;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.utils.date.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller handling the requests on the page displaying the results.<br/>
 * The results contained in this page only concern the monitoring type : {@link DataFormConstants#JSP_KEY_MONITORING_ATTENDANCE}.<br/>
 * The results concern only one establishment.
 * 
 * @since  2012/11/05
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/establishment-services-monitoring-attendance-result")
public class EstablishmentPeriodicServiceResultController extends BasicEstablishmentServiceResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentPeriodicServiceResultController.class);
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link EstablishmentPeriodicServiceResultController} class.
     */
    public EstablishmentPeriodicServiceResultController() {
	super("establishment-services-monitoring-attendance-result", SessionConstants.SERVICE_FORM_ATTR);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    //----------------------------------------------------------------------------- PROTECTED METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.service.result.BasicEstablishmentServiceResultController#createEstablishmentsResultRows(java.util.List, java.util.List, java.util.List, java.util.List, java.util.Date, java.util.Date)
     */
    @Override
    protected List<DetailResultRow> createEstablishmentsResultRows(List<String> establishmentsTypes,
	    List<String> establishmentsUai, List<String> services, List<String> userProfiles, Date startDate,
	    Date endDate) {
	// Retrieval of the establishment
	String establishmentUai = establishmentsUai.get(0);
	
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
	    return createDetailWeekResultRows(establishmentUai, services, userProfiles, startWeek, startYear, endWeek, endYear);
		    
	}

	Integer startMonth = DateUtils.getMonthOfYear(startDate);
	Integer endMonth = DateUtils.getMonthOfYear(endDate);
	return createDetailMonthResultRows(establishmentUai, services, userProfiles, startMonth, startYear, endMonth, endYear);
    }

    /**
     * Gets the result rows containing the data on the user profile for the specified period (composed of months).<br/>
     * These data only concern the period beginning with the specified <code>startMonth</code> of the <code>startYear</code>, and finishing with the <code>endMonth</code> of the <code>endYear</code>.<br/>
     * Each result row is associated to a user profile and one month, and contained two kinds of data :
     * <ul>
     * 	<li>The user profile</li>
     * 	<li>The statistic data on the services indexed by :
     * 		<ul>
     * 			<li>First level : A pair of month and year (see {@link IntegerPair})</li>
     * 			<li>Second level : A service name</li>
     * 		</ul>
     * 	</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per month contained in the period.<br/>
     * For more informations on the result row content, see {@link ExtendedResultRow}.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment.
     * @param services
     * 			The services.
     * @param userProfiles
     * 			The user profiles.
     * @param startMonth
     * 			The number of the beginning month (in the beginning year).
     * @param startYear
     * 			The beginning year.
     * @param endMonth
     * 			The number of the finishing month (in the finishing year).
     * @param endYear
     * 			The number of the finishing year.
     * 
     * @return
     * 	the result rows containing establishment data, and statistics data, for each period of one month in each establishment.
     */
    private List<DetailResultRow> createDetailMonthResultRows(String establishmentUai, List<String> services,
	    List<String> userProfiles, Integer startMonth, Integer startYear, Integer endMonth,
	    Integer endYear) {
	// Final result
	List<DetailResultRow> resultRows = new ArrayList<DetailResultRow>();

	// Puts the uai into a list
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	
	// Creation of result rows for each user profile and each period
	for (String userProfile : userProfiles) {
	    List<String> usersProfilesToFilter = getDataFormService().getUsersProfilesToFilter(userProfile);
	    List<ExtendedResultRow> rows = resultServiceFormService.getPeriodicMonthResultRows(establishmentsUai, services, usersProfilesToFilter, startMonth, startYear, endMonth, endYear);
	    resultRows.addAll(convertToDetailResultRows(rows, userProfile));
	}
	
	return resultRows;
    }

    /**
     * Gets the result rows containing the data on the user profile for the specified period (composed of months).<br/>
     * These data only concern the period beginning with the specified <code>startMonth</code> of the <code>startYear</code>, and finishing with the <code>endMonth</code> of the <code>endYear</code>.<br/>
     * Each result row is associated to a user profile and one week, and contained two kinds of data :
     * <ul>
     * 	<li>The user profile</li>
     * 	<li>The statistic data on the services indexed by :
     * 		<ul>
     * 			<li>First level : A pair of week and year (see {@link IntegerPair})</li>
     * 			<li>Second level : A service name</li>
     * 		</ul>
     * 	</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per week contained in the period.<br/>
     * For more informations on the result row content, see {@link ExtendedResultRow}.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment.
     * @param services
     * 			The services.
     * @param userProfiles
     * 			The user profiles.
     * @param startWeek
     * 			The number of the beginning week (in the beginning year).
     * @param startYear
     * 			The beginning year.
     * @param endWeek
     * 			The number of the finishing week (in the finishing year).
     * @param endYear
     * 			The number of the finishing year.
     * 
     * @return
     * 	the result rows containing establishment data, and statistics data, for each period of one month in each establishment.
     */
    private List<DetailResultRow> createDetailWeekResultRows(String establishmentUai, List<String> services,
	    List<String> userProfiles, Integer startWeek, Integer startYear, Integer endWeek, Integer endYear) {
	// Final result
	List<DetailResultRow> resultRows = new ArrayList<DetailResultRow>();

	// Puts the uai into a list
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	
	// Creation of result rows for each user profile and each period
	for (String userProfile : userProfiles) {
	    List<String> usersProfilesToFilter = getDataFormService().getUsersProfilesToFilter(userProfile);
	    List<ExtendedResultRow> rows = resultServiceFormService.getPeriodicWeekResultRows(establishmentsUai, services, usersProfilesToFilter, startWeek, startYear, endWeek, endYear);
	    resultRows.addAll(convertToDetailResultRows(rows, userProfile));
	}
	
	return resultRows;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
