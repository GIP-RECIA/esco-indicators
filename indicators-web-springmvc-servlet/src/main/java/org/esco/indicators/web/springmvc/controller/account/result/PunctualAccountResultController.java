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
import org.esco.indicators.domain.beans.result.BasicResultRow;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.form.account.ResultAccountFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.utils.date.DateUtils;
import org.esco.indicators.web.springmvc.controller.basic.result.BasicResultController;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PunctualAccountResultController extends BasicResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PunctualAccountResultController.class);
    
    /** Data form service providing information on the data from for the accounts */
    @Autowired
    protected DataFormService dataAccountFormService;
    
    /** Establishment service providing access to establishments data */
    @Autowired
    protected EstablishmentService establishmentService;
    
    /** Service providing access to result data */
    @Autowired
    protected ResultAccountFormService resultAccountFormService;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link PunctualAccountResultController} class.
     */
    public PunctualAccountResultController() {
	super("accounts-activations-attendance-result", SessionConstants.ACCOUNT_FORM_ATTR);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.result.BasicResultController#getDataFormService()
     */
    @Override
    public DataFormService getDataFormService() {
        return dataAccountFormService;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.result.BasicResultController#getEstablishmentService()
     */
    @Override
    public EstablishmentService getEstablishmentService() {
        return establishmentService;
    }

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
        List<String> usersProfilesToFilter = dataAccountFormService.getUsersProfilesToFilter(checkedProfiles);
        
        return usersProfilesToFilter;
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
    public List<BasicResultRow> populateTableRows(HttpServletRequest request) {
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
	List<String> usersProfilesToFilter = dataAccountFormService.getUsersProfilesToFilter(checkedProfiles);
	
	// Retrieval of the start date
	Date startDate = aaForm.getStartDate();
	
	// Gets the result rows to display
	List<BasicResultRow> basicResultRows = createResultRows(establishmentsTypes, establishmentsUai, usersProfilesToFilter, startDate);
	
	return basicResultRows;
    }
    
    

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
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
    private List<BasicResultRow> createResultRows( List<String> establishmentsTypes, List<String> establishmentsUai,List<String> usersProfiles, Date startDate) {
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
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
