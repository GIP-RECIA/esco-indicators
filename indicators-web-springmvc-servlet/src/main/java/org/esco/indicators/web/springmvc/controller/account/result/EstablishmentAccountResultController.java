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
import org.esco.indicators.domain.beans.result.BasicResultRow;
import org.esco.indicators.domain.beans.result.DetailResultRow;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.utils.date.DateUtils;
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
@RequestMapping("/establishment-accounts-activations-result")
public class EstablishmentAccountResultController extends PeriodicAccountResultDetailController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentAccountResultController.class);
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link EstablishmentAccountResultController} class.
     */
    public EstablishmentAccountResultController() {
	super("establishment-accounts-activations-result", SessionConstants.ACCOUNT_FORM_ATTR);
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
    @Override
    @ModelAttribute("establishmentName")
    public String populateEstablishmentName(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), formSessionAttribute)) 
	{
	    return null;
	}

	// Retrieval of the submitted form
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), formSessionAttribute);
	
	// Retrieval of the establishment UAI
	String establishmentUai = aaForm.getEstablishments()[0];
	
	// Finds the establishment name
	Establishment establishment = establishmentService.findEstablishmentByUai(establishmentUai);
	
	return (establishment == null ? null : establishment.getName());
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.account.result.PeriodicAccountResultDetailController#populateUsersProfilesI18nKeys(javax.servlet.http.HttpServletRequest)
     */
    @Override
    @ModelAttribute("i18nUsersProfiles")
    public Map<String, String> populateUsersProfilesI18nKeys(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process and a UAI to detail
	if(!containsForm(request.getSession(), formSessionAttribute)) {
	    return null;
	}
	
	// Final result
	Map<String, String> i18nKeysByJspKeys = new HashMap<String, String>();
	
	// Retrieval of the i18n keys for the users profiles  to filter
	List<String> allUsersProfiles = getAllUsersProfiles();
	for (String userProfile : allUsersProfiles) {
	    i18nKeysByJspKeys.put(userProfile, getDataFormService().getI18nKey(userProfile));
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
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), formSessionAttribute)) 
	{
	    return null;
	}

	// Retrieval of the submitted form
	AccountActivationForm aaForm = (AccountActivationForm) getSessionForm(request.getSession(), formSessionAttribute);
	
	// Addition of the current establishment type
	List<String> establishmentsTypes = new ArrayList<String>(Arrays.asList(aaForm.getEstablishmentsTypes()));
	
	// Retrieval of the users profiles to filter
	List<String> allUsersProfiles = new ArrayList<String>(Arrays.asList(aaForm.getUsersProfiles()));
	
	// Retrieval of the establishment UAI
	String establishmentUai = aaForm.getEstablishments()[0];
	
	// Retrieval of the start date and end date
	Date startDate = aaForm.getStartDate();
	Date endDate = (aaForm.getEndDate() != null ? aaForm.getEndDate() : aaForm.getStartDate());
	
	// Gets the result rows to display
	List<DetailResultRow> resultRows = createEstablishmentResultRows(establishmentsTypes, establishmentUai, allUsersProfiles, startDate, endDate);
	
	return resultRows;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.account.result.BasicAccountResultController#createEstablishmentsResultRows(java.util.List, java.util.List, java.util.List, java.util.Date, java.util.Date)
     */
    @Override
    protected List<BasicResultRow> createEstablishmentsResultRows( List<String> establishmentsTypes, List<String> establishmentsUai, List<String> usersProfiles, Date startDate, Date endDate) {
	// Retrieval of the only selected user profile
	String userProfile = usersProfiles.get(0);
	
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
	    return resultAccountFormService.getPeriodicWeekResultRowsWithTimeStats(establishmentsUai, userProfile, startWeek, startYear, endWeek, endYear);
	}
			
	Integer startMonth = DateUtils.getMonthOfYear(startDate);
	Integer endMonth = DateUtils.getMonthOfYear(endDate);
	return resultAccountFormService.getPeriodicMonthResultRowsWithTimeStats(establishmentsUai, userProfile, startMonth, startYear, endMonth, endYear);
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
