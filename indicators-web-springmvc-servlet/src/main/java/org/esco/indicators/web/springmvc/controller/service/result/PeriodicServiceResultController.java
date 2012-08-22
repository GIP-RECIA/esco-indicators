/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.service.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.ServiceForm;
import org.esco.indicators.domain.beans.result.ExtendedResultRow;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.form.service.ResultServiceFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.classes.IntegerPair;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.utils.date.DateUtils;
import org.esco.indicators.web.springmvc.controller.account.result.PeriodicAccountResultController;
import org.esco.indicators.web.springmvc.controller.basic.result.BasicResultController;
import org.springframework.beans.factory.annotation.Autowired;
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
        ServiceForm aaForm = (ServiceForm) getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the end date
        String endDate = aaForm.getEndDatePicker();
        
        return endDate;
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
        ServiceForm aaForm = (ServiceForm) getSessionForm(request.getSession(), formSessionAttribute);
        
	// Retrieval of the establishments types
	List<String> establishmentsTypes = new ArrayList<String>(Arrays.asList(aaForm.getEstablishmentsTypes()));
	
        // Retrieval of the start and end date
        Date startDate = aaForm.getStartDate();
        Date endDate = aaForm.getEndDate();
        
        return getStatisticPeriods(establishmentsTypes, startDate, endDate);
    }
    
    //----------------------------------------------------------------------------- PROTECTED METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.service.result.BasicServiceResultController#createResultRows(java.util.List, java.util.List, java.util.List, java.lang.String, java.util.Date, java.util.Date)
     */
    @Override
    protected List<ExtendedResultRow> createResultRows( List<String> establishmentsTypes, List<String> establishmentsUai, List<String> services, String userProfile, Date startDate, Date endDate) {
	// Retrieval of the start and end years
	Integer startYear = DateUtils.getYear(startDate);
	Integer endYear = DateUtils.getYear(endDate);
	
	// Retrieval of the month / or week
	if(	establishmentsTypes.contains(DataFormConstants.JSP_KEY_CFA) 
		&& establishmentsTypes.size() == 1
	) {
	    // Debug informations
	    LOGGER.debug("Creation of rows for weekly periods");
	    
	    // If the only selected establishment type is : CFA
	    Integer startWeek = DateUtils.getWeekOfYear(startDate);
	    Integer endWeek = DateUtils.getWeekOfYear(endDate);
	    return resultServiceFormService.getPeriodicWeekResultRows(establishmentsUai, services, userProfile, startWeek, startYear, endWeek, endYear);
	}

	// Debug informations
	LOGGER.debug("Creation of rows for monthly periods");
	    
	Integer startMonth = DateUtils.getMonthOfYear(startDate);
	Integer endMonth = DateUtils.getMonthOfYear(endDate);
	return resultServiceFormService.getPeriodicMonthResultRows(establishmentsUai, services, userProfile, startMonth, startYear, endMonth, endYear);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
