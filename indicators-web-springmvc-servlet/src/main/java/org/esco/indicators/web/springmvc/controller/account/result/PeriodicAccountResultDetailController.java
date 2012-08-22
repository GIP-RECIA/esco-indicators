/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.account.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.result.BasicResultRow;
import org.esco.indicators.domain.beans.result.DetailResultRow;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
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
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
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
	// Checks if the there is a valid submitted form to process and a UAI to detail
	String establishmentUai = request.getParameter(RequestParameters.ESTABLISHMENT_UAI);
	if(	! containsForm(request.getSession(), formSessionAttribute)
		|| establishmentUai == null) 
	{
	    return null;
	}
	
	// Finds the establishment name
	Establishment establishment = establishmentService.findEstablishmentByUai(establishmentUai);
	
	return (establishment == null ? null : establishment.getName());
    }
    
    /**
     * Populates a map that contained the users profilesto filter and their i18n keys.<br/>
     * The keys of this map are : the users profiles to filter.
     * 
     * @param request
     * 			The request made by the user.
     * 
     * @return
     * 	a map containing the JSP keys associated to their i18n keys.
     */
    @ModelAttribute("i18nUsersProfiles")
    public Map<String, String> populateUsersProfilesI18nKeys(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process and a UAI to detail
	String establishmentUai = request.getParameter(RequestParameters.ESTABLISHMENT_UAI);
	if(	! containsForm(request.getSession(), formSessionAttribute)
		|| establishmentUai == null) 
	{
	    return null;
	}
	
	// Final result
	Map<String, String> i18nKeysByJspKeys = new HashMap<String, String>();
	
	// Retrieval of the i18n keys for the users profiles  to filter
	List<String> allUsersProfiles = getAllUsersProfiles();
	for (String userProfile : allUsersProfiles) {
	    i18nKeysByJspKeys.put(userProfile, dataAccountFormService.getI18nKey(userProfile));
	}
	
	return i18nKeysByJspKeys;
    }
    
    /**
     * Populates the data rows of the table used to display detail informations
     * on the suers profiles of an establishment.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the data rows of the table used to display the result of the submitted form.
     */
    @Override
    @ModelAttribute("tableRowsItems")
    public List<DetailResultRow> populateTableRows(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process and a UAI to detail
	String establishmentUai = request.getParameter(RequestParameters.ESTABLISHMENT_UAI);
	if(	! containsForm(request.getSession(), formSessionAttribute)
		|| establishmentUai == null) 
	{
	    return null;
	}

	// Retrieval of the submitted form
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), formSessionAttribute);
	
	// Retrieval of the establishments types
	List<String> establishmentsTypes = new ArrayList<String>(Arrays.asList(aaForm.getEstablishmentsTypes()));
	
	// Retrieval of the users profiles to filter
	List<String> allUsersProfiles = getAllUsersProfiles();
	
	// Retrieval of the start date and end date
	Date startDate = aaForm.getStartDate();
	Date endDate = aaForm.getEndDate();
	
	// Gets the result rows to display
	List<DetailResultRow> resultRows = createResultRows(establishmentsTypes, establishmentUai, allUsersProfiles, startDate, endDate);
	
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
    private List<DetailResultRow> createResultRows( List<String> establishmentsTypes, String establishmentUai, List<String> usersProfiles, Date startDate, Date endDate) {
	// List containing the only one establishment
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	
	// Creation of result rows : one result row per establishment and user profile
	List<DetailResultRow> resultRows = new ArrayList<DetailResultRow>();
	for (String userProfile : usersProfiles) {
	    String userProfileToFilter = dataAccountFormService.getUserProfileToFilter(userProfile);
	    List<String> usersProfilesToFilter = new ArrayList<String>();
	    usersProfilesToFilter.add(userProfileToFilter);
	    List<BasicResultRow> basicResultRows = super.createEstablishmentsResultRows(establishmentsTypes, establishmentsUai, usersProfilesToFilter, startDate, endDate);
	    resultRows.addAll(convertToDetailResultRows(basicResultRows, userProfile));
	}
	
	return resultRows;
    }
    
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
    
    /**
     * Gets the JSP keys corresponding to all the available users profiles.
     * 
     * @return
     * 	the list containing all the available users profiles JSP keys.
     */
    private List<String> getAllUsersProfiles() {
	// Final result
	List<String> allUsersProfiles = new ArrayList<String>();
	
	List<EntryValue> entryValues = dataAccountFormService.getEntryValues(DataFormConstants.USERS_PROFILES);
	for (EntryValue entryValue : entryValues) {
	    allUsersProfiles.add(entryValue.getJspKey());
	}
	
	return allUsersProfiles;
    }
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
