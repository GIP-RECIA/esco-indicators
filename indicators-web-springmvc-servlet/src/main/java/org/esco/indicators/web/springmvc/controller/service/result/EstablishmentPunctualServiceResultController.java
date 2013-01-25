/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.service.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.result.DetailResultRow;
import org.esco.indicators.domain.beans.result.ExtendedResultRow;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.utils.date.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller handling the requests on the page displaying the results.<br/>
 * The results contained in this page only concern the monitoring type : {@link DataFormConstants#JSP_KEY_ATTENDANCE}.<br/>
 * The results only concerns one establishment.
 * 
 * 
 * @since  2012/11/05
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/establishment-services-attendance-result")
public class EstablishmentPunctualServiceResultController extends BasicEstablishmentServiceResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentPunctualServiceResultController.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link EstablishmentPunctualServiceResultController} class.
     */
    public EstablishmentPunctualServiceResultController() {
	super("establishment-services-attendance-result", SessionConstants.SERVICE_FORM_ATTR);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    //--------------------------------------------------------------------------- PROTECTED METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.service.result.BasicEstablishmentServiceResultController#createEstablishmentResultRows(java.util.List, java.lang.String, java.util.List, java.util.List, java.util.Date, java.util.Date)
     */
    @Override
    protected List<DetailResultRow> createEstablishmentResultRows(List<String> establishmentsTypes,
	    String establishmentUai, List<String> services, List<String> userProfiles, Date startDate,
	    Date endDate) {
	// Retrieval of the year
	Integer year = DateUtils.getYear(startDate);
	
	// Retrieval of the month / or week
	if(	establishmentsTypes.contains(DataFormConstants.JSP_KEY_CFA) 
		&& establishmentsTypes.size() == 1
	) {
	    // If the only selected establishment type is : CFA
	    Integer week = DateUtils.getWeekOfYear(startDate);
	    return createDetailWeekResultRow(establishmentUai, services, userProfiles, week, year);
	} 
	
	Integer month = DateUtils.getMonthOfYear(startDate);
	return createDetailMonthResultRow(establishmentUai, services, userProfiles, month, year);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Creates the result rows; each result row containing the following data :
     * <ul>
     * 	<li>The user profile</li>
     * 	<li>The statistic data (number of connections,...)</li>
     * </ul>
     * 
     * The statistic data are indexed by a {@link String} and represents : the name of a service.<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment.
     * @param services
     * 			The services concerned by the statistics.
     * @param userProfiles
     * 			The user profiles concerned by the statistics.
     * @param month
     * 			The month of the statistics.
     * @param year
     * 			The year of the statistics.
     * 
     * @return
     * 	the result rows containing the data to display.
     */
    private List<DetailResultRow> createDetailMonthResultRow(String establishmentUai, List<String> services,
	    List<String> userProfiles, Integer month, Integer year) {
	// Final result
	List<DetailResultRow> detailResultRows = new ArrayList<DetailResultRow>();
	
	// Create the detail rows for each user profile
	for (String userProfile : userProfiles) {
	    List<String> userProfilesToFilter = getDataFormService().getUsersProfilesToFilter(userProfile);
	    ExtendedResultRow extendedRow = resultServiceFormService.getPunctualMonthResultRow(establishmentUai, services, userProfilesToFilter, month, year);
	    List<ExtendedResultRow> extendedRows = new ArrayList<ExtendedResultRow>();
	    extendedRows.add(extendedRow);
	    detailResultRows.addAll(convertToDetailResultRows(extendedRows, userProfile));
	}
	
	return detailResultRows;
    }

    /**
     * Creates the result rows; each result row containing the following data :
     * <ul>
     * 	<li>The user profile</li>
     * 	<li>The statistic data (number of connections,...)</li>
     * </ul>
     * 
     * The statistic data are indexed by a {@link String} and represents : the name of a service.<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment.
     * @param services
     * 			The services concerned by the statistics.
     * @param userProfiles
     * 			The user profiles concerned by the statistics.
     * @param week
     * 			The week of the statistics.
     * @param year
     * 			The year of the statistics.
     * 
     * @return
     * 	the result rows containing the data to display.
     */
    private List<DetailResultRow> createDetailWeekResultRow(String establishmentUai, List<String> services,
	    List<String> userProfiles, Integer week, Integer year) {
	// Final result
	List<DetailResultRow> detailResultRows = new ArrayList<DetailResultRow>();
	
	// Create the detail rows for each user profile
	for (String userProfile : userProfiles) {
	    List<String> userProfilesToFilter = getDataFormService().getUsersProfilesToFilter(userProfile);
	    List<ExtendedResultRow> extendedRows = resultServiceFormService.getPunctualWeekResultRows(establishmentUai, services, userProfilesToFilter, week, year);
	    detailResultRows.addAll(convertToDetailResultRows(extendedRows, userProfile));
	}
	
	return detailResultRows;
    }
    


    //------------------------------------------------------------------------------ STATIC METHODS
}
