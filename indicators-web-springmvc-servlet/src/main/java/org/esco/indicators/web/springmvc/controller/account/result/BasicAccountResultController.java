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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Basic controller handling the requests on the page displaying the results for accounts activations.<br/>
 * 
 * @since  2012/08/22
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public abstract class BasicAccountResultController extends BasicResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicAccountResultController.class);
    
    /** Service providing access to result data */
    @Autowired
    protected ResultAccountFormService resultAccountFormService;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of {@link BasicAccountResultController}.
     * 
     * @param viewName
     * 			The view name associated to the controller.
     * @param formSessionAttribute
     * 			The session attribute containing the submitted form.
     */
    public BasicAccountResultController(String viewName, String formSessionAttribute) {
	super(viewName, formSessionAttribute);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the data form service
     * 
     * @param dataFormService
     * 			the data form service to set
     */
    @Autowired
    @Qualifier("dataAccountFormService")
    public void setDataFormService(DataFormService dataFormService) {
	this.dataFormService = dataFormService;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    /**
     * Populates the data rows of the table used to display the result of the submitted form.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the data rows of the table used to display the result of the submitted form.
     */
    @ModelAttribute("tableRowsItems")
    public List<? extends BasicResultRow> populateTableRows(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR)) {
	    return null;
	}
	
	// Retrieval of the submitted form
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), SessionConstants.ACCOUNT_FORM_ATTR);
	
	// Retrieval of the establishments types
	List<String> establishmentsTypes = new ArrayList<String>(Arrays.asList(aaForm.getAllEstablishmentsTypes()));
	
	// Retrieval of the users profiles to filter
	List<String> checkedProfiles = new ArrayList<String>(Arrays.asList(aaForm.getUsersProfiles()));
	List<String> usersProfilesToFilter = getDataFormService().getUsersProfilesToFilter(checkedProfiles);
	
	// Retrieval of the dates
	Date startDate = aaForm.getStartDate();
	Date endDate = aaForm.getEndDate();
	
	// If the sum on counties has to be made on the result rows
	String sumOnCounties = aaForm.getSumOnCounties();
	if(sumOnCounties != null) {
	    // Retrieval of the county numbers and types to filter
	    List<String> countyNumbersToFilter = getDataFormService().getCountyNumbersToFilter(sumOnCounties);
	    List<String> establishmentsTypesToFilter = getDataFormService().getEstablishmentsTypesToFilter(establishmentsTypes);
	    LOGGER.debug("The sum on counties has been asked. The result rows will concern the counties : " + countyNumbersToFilter + " and the establishments types : " + establishmentsTypesToFilter);
	    return createSumOnCountiesResultRows(establishmentsTypes, countyNumbersToFilter, establishmentsTypesToFilter, usersProfilesToFilter, startDate, endDate);
	}
	
	// If the sum on counties has not to be made on the result rows
	List<String> establishmentsUai = new ArrayList<String>(Arrays.asList(aaForm.getEstablishments()));
	LOGGER.debug("The sum on counties has not been asked. The result rows will concern the establishments : " + establishmentsUai);
	return createEstablishmentsResultRows(establishmentsTypes, establishmentsUai, usersProfilesToFilter, startDate, endDate);
    }
    
    

    //--------------------------------------------------------------------------- PROTECTED METHODS
    
    /**
     * Creates the result row; each result row containing the following data :
     * <ul>
     * 	<li>The establishment data (name, UAI,..)</li>
     * 	<li>The statistic data (number of connections,...)</li>
     * </ul>
     * 
     * @param establishmentsTypes
     * 			The types of the establishments checked in the user view.
     * @param establishmentsUai
     * 			The UAI of the establishments.
     * @param usersProfiles
     * 			The users profiles concerned by the statistics.
     * @param startDate
     * 			The start date of the statistics.
     * @param endDate
     * 			The end date of the statistics (can be <code>null</code>).
     * 
     * @return
     * 	the result rows containing the data to display.
     */
    protected abstract List<BasicResultRow> createEstablishmentsResultRows( List<String> establishmentsTypes, List<String> establishmentsUai, List<String> usersProfiles, Date startDate, Date endDate);
    
    /**
     * Creates the result row; each result row containing the following data :
     * <ul>
     * 	<li>The county data (county number)</li>
     * 	<li>The statistic data (number of connections,...)</li>
     * </ul>
     * 
     * @param checkedEstablishmentTypes
     * 			The establishments types checked in the user view.
     * @param countyNumbers
     * 			The numbers of the counties concerned by the statistics.
     * @param establishmentsTypes
     * 			The types of the establishments concerned by the statistics.
     * @param usersProfiles
     * 			The users profiles concerned by the statistics.
     * @param startDate
     * 			The start date of the statistics.
     * @param endDate
     * 			The end date of the statistics (can be <code>null</code>).
     * 
     * @return
     * 	the result rows containing the data to display.
     */
    protected abstract List<BasicResultRow> createSumOnCountiesResultRows( List<String> checkedEstablishmentTypes, List<String> countyNumbers, List<String> establishmentsTypes, List<String> usersProfiles, Date startDate, Date endDate);
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
