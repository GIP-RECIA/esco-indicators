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
import org.esco.indicators.domain.beans.form.ServiceForm;
import org.esco.indicators.domain.beans.result.BasicResultRow;
import org.esco.indicators.domain.beans.result.ExtendedResultRow;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.form.service.ResultServiceFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.utils.date.DateUtils;
import org.esco.indicators.web.springmvc.controller.account.result.PunctualAccountResultController;
import org.esco.indicators.web.springmvc.controller.basic.result.BasicResultController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller handling the requests on the page displaying the results.<br/>
 * The results contained in this page only concern the monitoring type : {@link DataFormConstants#JSP_KEY_ATTENDANCE}.
 * 
 * @since  2012/07/25
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/services-attendance-result")
public class PunctualServiceResultController extends BasicServiceResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PunctualServiceResultController.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link PunctualAccountResultController} class.
     */
    public PunctualServiceResultController() {
	super("services-attendance-result", SessionConstants.SERVICE_FORM_ATTR);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    //--------------------------------------------------------------------------- PROTECTED METHODS
    
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.service.result.BasicServiceResultController#createResultRows(java.util.List, java.util.List, java.util.List, java.lang.String, java.util.Date, java.util.Date)
     */
    @Override
    protected List<ExtendedResultRow> createResultRows( List<String> establishmentsTypes, List<String> establishmentsUai, List<String> services, String userProfile, Date startDate, Date endDate) {
	// Retrieval of the year
	Integer year = DateUtils.getYear(startDate);
	
	// Retrieval of the month / or week
	if(	establishmentsTypes.contains(DataFormConstants.JSP_KEY_CFA) 
		&& establishmentsTypes.size() == 1
	) {
	    // If the only selected establishment type is : CFA
	    Integer week = DateUtils.getWeekOfYear(startDate);
	    return resultServiceFormService.getPunctualWeekResultRows(establishmentsUai, services, userProfile, week, year);
	} 
	
	Integer month = DateUtils.getMonthOfYear(startDate);
	return resultServiceFormService.getPunctualMonthResultRows(establishmentsUai, services, userProfile, month, year);
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
