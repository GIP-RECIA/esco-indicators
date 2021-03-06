/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.result.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.result.BasicResultRow;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.utils.date.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller handling the requests on the page displaying the results.<br/>
 * The results contained in this page only concern the monitoring type : {@link DataFormConstants#JSP_KEY_ATTENDANCE}.
 * 
 * @since  2012/06/29
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/accounts-activations-attendance-result")
public class PunctualAccountResultController extends BasicAccountResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PunctualAccountResultController.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link PunctualAccountResultController} class.
     */
    public PunctualAccountResultController() {
	super("accounts-activations-attendance-result", SessionConstants.ACCOUNT_FORM_ATTR);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Populate the field containing the list of the keys used to index the statistic data in each result row.<br/>
     * In this page, the keys used to index the statistic data are : the filtered users profiles.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the list of the keys used to index the statistic data.
     */
    @ModelAttribute("statisticDataKeys")
    public List<String> populateStatisticDataKeys(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
        
        // Retrieval of the users profiles to filter
        List<String> checkedProfiles = new ArrayList<String>(Arrays.asList(aaForm.getUsersProfiles()));
        List<String> usersProfilesToFilter = getDataFormService().getUsersProfilesToFilter(checkedProfiles);
        
        return usersProfilesToFilter;
    }
    
    //--------------------------------------------------------------------------- PROTECTED METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.account.result.BasicAccountResultController#createEstablishmentsResultRows(java.util.List, java.util.List, java.util.List, java.util.Date, java.util.Date)
     */
    @Override
    protected List<BasicResultRow> createEstablishmentsResultRows( List<String> establishmentsTypes, List<String> establishmentsUai,List<String> usersProfiles, Date startDate, Date endDate) {
	// Retrieval of the year
	Integer year = DateUtils.getYear(startDate);
	
	// Retrieval of the month / or week
	if(	establishmentsTypes.contains(DataFormConstants.JSP_KEY_CFA) 
		&& establishmentsTypes.size() == 1
	) {
	    // If the only selected establishment type is : CFA
	    Integer week = DateUtils.getWeekOfYear(startDate);
	    return resultAccountFormService.getPunctualWeekResultRows(establishmentsUai, usersProfiles, week, year);
	} 
	
	Integer month = DateUtils.getMonthOfYear(startDate);
	return resultAccountFormService.getPunctualMonthResultRows(establishmentsUai, usersProfiles, month, year);
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.account.result.BasicAccountResultController#createSumOnCountiesResultRows(java.util.List, java.util.List, java.util.List, java.util.List, java.util.Date, java.util.Date)
     */
    @Override
    protected List<BasicResultRow> createSumOnCountiesResultRows( List<String> checkedEstablishmentTypes, List<String> countyNumbers, List<String> establishmentsTypes, List<String> usersProfiles, Date startDate, Date endDate) {
	// Retrieval of the year
	Integer year = DateUtils.getYear(startDate);
	
	// Retrieval of the month / or week
	if(	checkedEstablishmentTypes.contains(DataFormConstants.JSP_KEY_CFA) 
		&& establishmentsTypes.size() == 1
	) {
	    // If the only selected establishment type is : CFA
	    Integer week = DateUtils.getWeekOfYear(startDate);
	    return resultAccountFormService.getPunctualWeekResultRows(countyNumbers, establishmentsTypes, usersProfiles, week, year);
	} 
	
	Integer month = DateUtils.getMonthOfYear(startDate);
	return resultAccountFormService.getPunctualMonthResultRows(countyNumbers, establishmentsTypes, usersProfiles, month, year);
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
