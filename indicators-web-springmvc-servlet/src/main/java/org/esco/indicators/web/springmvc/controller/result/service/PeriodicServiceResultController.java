/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.result.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.result.ExtendedResultRow;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.utils.date.DateUtils;
import org.esco.indicators.web.springmvc.controller.result.account.PeriodicAccountResultController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller handling the requests on the page displaying the results.<br/>
 * The results contained in this page only concern the monitoring type : {@link DataFormConstants#JSP_KEY_MONITORING_ATTENDANCE}.
 * 
 * @since  2012/07/10
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/services-monitoring-attendance-result")
public class PeriodicServiceResultController extends BasicServiceResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PeriodicServiceResultController.class);
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link PeriodicAccountResultController} class.
     */
    public PeriodicServiceResultController() {
	super("services-monitoring-attendance-result", SessionConstants.SERVICE_FORM_ATTR);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    //----------------------------------------------------------------------------- PROTECTED METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.service.result.BasicServiceResultController#createEstablishmentsResultRows(java.util.List, java.util.List, java.util.List, java.util.List, java.util.Date, java.util.Date)
     */
    @Override
    protected List<ExtendedResultRow> createEstablishmentsResultRows( List<String> establishmentsTypes, List<String> establishmentsUai, List<String> services, List<String> usersProfiles, Date startDate, Date endDate) {
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
	    return resultServiceFormService.getPeriodicWeekResultRows(establishmentsUai, services, usersProfiles, startWeek, startYear, endWeek, endYear);
	}

	Integer startMonth = DateUtils.getMonthOfYear(startDate);
	Integer endMonth = DateUtils.getMonthOfYear(endDate);
	return resultServiceFormService.getPeriodicMonthResultRows(establishmentsUai, services, usersProfiles, startMonth, startYear, endMonth, endYear);
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.service.result.BasicServiceResultController#createSumOnCountiesResultRows(java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.Date, java.util.Date)
     */
    @Override
    protected List<ExtendedResultRow> createSumOnCountiesResultRows( List<String> checkedEstablishmentTypes, List<String> countyNumbers, List<String> establishmentsTypes, List<String> services, List<String> usersProfiles, Date startDate, Date endDate) {
		// Retrieval of the start and end years
		Integer startYear = DateUtils.getYear(startDate);
		Integer endYear = DateUtils.getYear(endDate);
		
		// Retrieval of the month / or week
		if(	checkedEstablishmentTypes.contains(DataFormConstants.JSP_KEY_CFA) 
			&& establishmentsTypes.size() == 1
		) {
		    // If the only selected establishment type is : CFA
		    Integer startWeek = DateUtils.getWeekOfYear(startDate);
		    Integer endWeek = DateUtils.getWeekOfYear(endDate);
		    return resultServiceFormService.getPeriodicWeekResultRows(countyNumbers, establishmentsTypes, services, usersProfiles, startWeek, startYear, endWeek, endYear);
		}

		Integer startMonth = DateUtils.getMonthOfYear(startDate);
		Integer endMonth = DateUtils.getMonthOfYear(endDate);
		return resultServiceFormService.getPeriodicMonthResultRows(countyNumbers, establishmentsTypes, services, usersProfiles, startMonth, startYear, endMonth, endYear);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
