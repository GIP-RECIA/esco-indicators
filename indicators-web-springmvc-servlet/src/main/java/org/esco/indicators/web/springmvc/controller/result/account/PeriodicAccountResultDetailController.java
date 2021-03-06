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
import org.esco.indicators.domain.beans.result.DetailResultRow;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.utils.classes.IntegerPair;
import org.esco.indicators.utils.constants.web.RequestParameters;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller handling the requests on the page displaying the detail results.<br/>
 * The results contained in this page only concern the monitoring type : {@link DataFormConstants#JSP_KEY_MONITORING_ATTENDANCE}.
 * 
 * @since  2012/08/01
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/accounts-activations-monitoring-detail")
public class PeriodicAccountResultDetailController extends PeriodicAccountResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PeriodicAccountResultDetailController.class);
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link PeriodicAccountResultDetailController} class.
     */
    public PeriodicAccountResultDetailController() {
	super("accounts-activations-monitoring-detail", SessionConstants.ACCOUNT_FORM_ATTR);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    
    /**
     * Constructor of the {@link PeriodicAccountResultController} class.
     * 
     * @param viewName
     * 			The view name associated to the controller.
     * @param formSessionAttribute
     * 			The session attribute containing the submitted form.
     */
    public PeriodicAccountResultDetailController(String viewName, String formSessionAttribute) {
	super(viewName, formSessionAttribute);
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Populates the county number.
     * 
     * @param request
     * 			The request made by the user.
     * 
     * @return
     * 	the county number concerned by the statistics.
     */
    @ModelAttribute("countyNumber")
    public String populateCountyNumber(HttpServletRequest request) {
	// Checks if the there is a county number provided in the parameters
	return request.getParameter(RequestParameters.COUNTY_NUMBER);
    }
    
    /**
     * Populates the establishment name.
     * 
     * @param request
     * 			The request made by the user.
     * 
     * @return
     * 	the name of the establishment concerned by the statistics.
     */
    @ModelAttribute("establishmentName")
    public String populateEstablishmentName(HttpServletRequest request) {
	// Gets the establishent UAI
	String establishmentUai = getEstablishmentUai(request);
	if(establishmentUai == null) {
	    return null;
	}
	
	// Finds the establishment name
	Establishment establishment = establishmentService.findEstablishmentByUai(establishmentUai);
	
	return (establishment == null ? null : establishment.getName());
    }
    
    /**
     * Populates the data rows of the table used to display detail informations
     * on the users profiles of an establishment.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the data rows of the table used to display the result of the submitted form.
     */
    @Override
    @ModelAttribute("tableRowsItems")
    public List<DetailResultRow> populateTableRows(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(	! containsForm(request.getSession(), formSessionAttribute) ) {
	    return null;
	}
	
	// Retrieval of the submitted form
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), formSessionAttribute);
	
	// Retrieval of the establishments types
	List<String> establishmentsTypes = new ArrayList<String>(Arrays.asList(aaForm.getAllEstablishmentsTypes()));
	
	// Retrieval of the users profiles to filter
	List<String> allUsersProfiles = keepAuthorizedJspKeysForUsersProfiles(getAllUsersProfiles());
	
	// Retrieval of the start date and end date
	Date startDate = aaForm.getStartDate();
	Date endDate = aaForm.getEndDate();
	
	// If the detail concerns an establishment
	String establishmentUai = request.getParameter(RequestParameters.ESTABLISHMENT_UAI);
	if(establishmentUai != null) {
	    return createEstablishmentResultRows(establishmentsTypes, establishmentUai, allUsersProfiles, startDate, endDate);
	}
	
	// If the detail concerns a county
	String countyNumber = request.getParameter(RequestParameters.COUNTY_NUMBER);
	if(countyNumber != null) {
	    return createCountyResultRows(establishmentsTypes, countyNumber, allUsersProfiles, startDate, endDate);
	}
	
	// If it is not possible to know which kind of detail has been asked
	return null;
    }
    
    //--------------------------------------------------------------------------- PROTECTED METHODS
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
     * @param establishmentUai
     * 			The UAI of the establishment.
     * @param usersProfiles
     * 			The users profiles concerned by the statistics.
     * @param startDate
     * 			The start date of the statistics.
     * @param endDate
     * 			The end date of the statistics.
     * 
     * @return
     * 	the result rows containing the data to display.
     */
    protected List<DetailResultRow> createEstablishmentResultRows( List<String> establishmentsTypes, String establishmentUai, List<String> usersProfiles, Date startDate, Date endDate) {
	// List containing the only one establishment
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	
	// Creation of result rows : one result row per establishment and user profile
	List<DetailResultRow> resultRows = new ArrayList<DetailResultRow>();
	for (String userProfile : usersProfiles) {
	    List<String> usersProfilesToFilter = getDataFormService().getUsersProfilesToFilter(userProfile);
	    List<BasicResultRow> basicResultRows = createEstablishmentsResultRows(establishmentsTypes, establishmentsUai, usersProfilesToFilter, startDate, endDate);
	    resultRows.addAll(convertToDetailResultRows(basicResultRows, userProfile));
	}
	
	return resultRows;
    }
    
    /**
     * Creates the result rows; each result row containing the following data :
     * <ul>
     * 	<li>The county data (county number,..)</li>
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
     * @param checkedEstablishmentsTypes
     * 			The types of the establishments checked in the user view.
     * @param establishmentsTypes
     * 			The types of the establishments to filter.
     * @param countyNumber
     * 			The county number.
     * @param usersProfiles
     * 			The users profiles concerned by the statistics.
     * @param startDate
     * 			The start date of the statistics.
     * @param endDate
     * 			The end date of the statistics.
     * 
     * @return
     * 	the result rows containing the data to display.
     */
    protected List<DetailResultRow> createCountyResultRows( List<String> checkedEstablishmentsTypes, String countyNumber, List<String> usersProfiles, Date startDate, Date endDate) {
	// List containing the only one county number
	List<String> countyNumbers = new ArrayList<String>();
	countyNumbers.add(countyNumber);
	
	// Retrieval of the establishments types
	List<String> establishmentsTypesToFilter = getDataFormService().getEstablishmentsTypesToFilter(checkedEstablishmentsTypes);
	
	// Creation of result rows : one result user profile
	List<DetailResultRow> resultRows = new ArrayList<DetailResultRow>();
	for (String userProfile : usersProfiles) {
	    List<String> usersProfilesToFilter = getDataFormService().getUsersProfilesToFilter(userProfile);
	    List<BasicResultRow> basicResultRows = createSumOnCountiesResultRows(checkedEstablishmentsTypes, countyNumbers, establishmentsTypesToFilter, usersProfilesToFilter, startDate, endDate);
	    resultRows.addAll(convertToDetailResultRows(basicResultRows, userProfile));
	}
	
	return resultRows;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Converts a list of {@link BasicResultRow} to a list of {@link DetailResultRow}.<br/>
     * This conversion consists in copying the statistic and establishment data, and adding the user profile associated to these statistic.
     * 
     * @param basicResultRows
     * 			The basic result rows.
     * @param userProfile
     * 			The user profile.
     * 
     * @return
     * 	a list containing the detail result rows based on the basic ones.
     */
    private List<DetailResultRow> convertToDetailResultRows(List<BasicResultRow> basicResultRows, String userProfile) {
	// Final result
	List<DetailResultRow> detailResultRows = new ArrayList<DetailResultRow>();
	
	// Conversion from basic to detail rows
	for (BasicResultRow basicResultRow : basicResultRows) {
	    DetailResultRow detailResultRow = new DetailResultRow(basicResultRow, userProfile);
	    detailResultRows.add(detailResultRow);
	}
	
	return detailResultRows;
    }
    //------------------------------------------------------------------------------ STATIC METHODS
}
