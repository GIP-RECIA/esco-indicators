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
import org.esco.indicators.domain.beans.result.DetailResultRow;
import org.esco.indicators.domain.beans.result.ExtendedResultRow;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.form.service.ResultServiceFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.classes.IntegerPair;
import org.esco.indicators.web.springmvc.controller.basic.result.BasicResultController;
import org.esco.indicators.web.springmvc.controller.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Basic controller handling the requests on the page displaying the results of the services for only one establishment.<br/>
 * 
 * @since  2012/11/05
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public abstract class BasicEstablishmentServiceResultController extends BasicResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicEstablishmentServiceResultController.class);
    
    /** Data form service providing information on the data from for the accounts */
    @Autowired
    protected DataFormService dataServiceFormService;
    
    /** Establishment service providing access to establishments data */
    @Autowired
    protected EstablishmentService establishmentService;
    
    /** Service providing access to result data */
    @Autowired
    protected ResultServiceFormService resultServiceFormService;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of {@link UserController}.
     * 
     * @param viewName
     * 			The view name associated to the controller.
     * @param formSessionAttribute
     * 			The session attribute containing the submitted form.
     */
    public BasicEstablishmentServiceResultController(String viewName, String formSessionAttribute) {
	super(viewName, formSessionAttribute);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.result.BasicResultController#getDataFormService()
     */
    @Override
    public DataFormService getDataFormService() {
        return dataServiceFormService;
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
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), formSessionAttribute)) 
	{
	    return null;
	}

	// Retrieval of the submitted form
	ServiceForm aaForm = (ServiceForm) getSessionForm(request.getSession(), formSessionAttribute);
	
	// Retrieval of the establishment UAI
	String establishmentUai = aaForm.getEstablishments()[0];
	
	// Finds the establishment name
	Establishment establishment = establishmentService.findEstablishmentByUai(establishmentUai);
	
	return (establishment == null ? null : establishment.getName());
    }
    
    /**
     * Populate the field containing the list of the keys used to index the statistic data in each result row.<br/>
     * In this page, the keys used to index the statistic data are : the services.<br/>
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the list of the keys used to index the statistic data.
     */
    @ModelAttribute("statisticDataKeys")
    public List<String> populateStatisticDataKeys(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        ServiceForm aaForm = (ServiceForm) getSessionForm(request.getSession(), formSessionAttribute);
        
	// Retrieval of the wanted services
	List<String> wantedServices = new ArrayList<String>(Arrays.asList(aaForm.getWantedServices()));
	List<String> services = dataServiceFormService.getServicesToFilter(wantedServices);
	
        return services;
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
    public List<DetailResultRow> populateTableRows(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), formSessionAttribute)) {
	    return null;
	}
	
	// Retrieval of the submitted form
	ServiceForm serviceForm = (ServiceForm) getSessionForm(request.getSession(), formSessionAttribute);
	
	// Retrieval of the establishments types
	List<String> establishmentsTypes = new ArrayList<String>(Arrays.asList(serviceForm.getAllEstablishmentsTypes()));
	
	// Retrieval of the users profiles to filter
	// All the authorized users profiles have to be displayed
	List<String> authorizedProfiles = keepAuthorizedJspKeysForUsersProfile(getAllUsersProfiles());
	
	// Retrieval of the start date and end date
	Date startDate = serviceForm.getStartDate();
	Date endDate = serviceForm.getEndDate();
	
	// Retrieval of the services to filter
	List<String> checkedServices = new ArrayList<String>(Arrays.asList(serviceForm.getWantedServices()));
	List<String> services = getDataFormService().getServicesToFilter(checkedServices);
	 
	// Retrieval of the establishment uai
	String establishmentUai = new ArrayList<String>(Arrays.asList(serviceForm.getEstablishments())).get(0);
	LOGGER.debug("The result rows will concern the establishment : [" + establishmentUai + "]");
	return createEstablishmentResultRows(establishmentsTypes, establishmentUai, services, authorizedProfiles, startDate, endDate);
    }
    
    /**
     * Populate the field containing the list of i18n keys for the wanted services.<br/>
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the list of the keys used to index the statistic data.
     */
    @ModelAttribute("wantedServicesItems")
    public List<String> populateWantedServices(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        ServiceForm aaForm = (ServiceForm) getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the wanted services
        List<String> wantedServices = new ArrayList<String>(Arrays.asList(aaForm.getWantedServices()));
        List<String> i18nKeys = getI18nKeys(wantedServices);
        
        return i18nKeys;
    }
    
    //--------------------------------------------------------------------------- PROTECTED METHODS
    /**
     * Converts a list of {@link ExtendedResultRows} to a list of {@link DetailResultRow}.<br/>
     * This conversion consists in copying the statistic and establishment data, and adding the user profile associated to these statistic.
     * 
     * @param extendedResultRows
     * 			The extended result rows.
     * @param userProfile
     * 			The user profile.
     * 
     * @return
     * 	a list containing the detail result rows based on the basic ones.
     */
    protected List<DetailResultRow> convertToDetailResultRows(List<ExtendedResultRow> extendedResultRows, String userProfile) {
	// Final result
	List<DetailResultRow> detailResultRows = new ArrayList<DetailResultRow>();
	
	// Conversion from extended to detail rows
	for (ExtendedResultRow extendedResultRow : extendedResultRows) {
	    DetailResultRow detailResultRow = new DetailResultRow(extendedResultRow, userProfile);
	    detailResultRows.add(detailResultRow);
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
     * The statistic data are indexed by a {@link String} or an {@link IntegerPair}.<br/>
     * When the statistics are punctual, the index is a string and represents : the name of a service.<br/>
     * When the statistics are periodic, the index is an Integer pair containing : 
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
     * @param services
     * 			The services concerned by the statistics.
     * @param userProfiles
     * 			The user profiles concerned by the statistics.
     * @param startDate
     * 			The start date of the statistics.
     * @param endDate
     * 			The end date of the statistics (can be <code>null</code>).
     * 
     * @return
     * 	the result rows containing the data to display.
     */
    protected abstract List<DetailResultRow> createEstablishmentResultRows( List<String> establishmentsTypes, String establishmentUai, List<String> services, List<String> userProfiles, Date startDate, Date endDate);

    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Only keeps the jsp keys (associated to users profiles) which are authorized
     * for the authenticated user.
     * 
     * @param jspKeys
     * 			The jsp keys (associated to users profiles) to test.
     * 
     * @return
     * 	the authorized jsp keys.<br/>
     * 	an empty list if no jsp is authorized.
     */
    private List<String> keepAuthorizedJspKeysForUsersProfile(List<String> jspKeys) {
	// Final result
	List<String> authorizedUsersProfilesKeys =  new ArrayList<String>();
	
	// Retrieves the authorized jsp keys (associated to users profiles)
	for (String jspKey : jspKeys) {
	    String userProfile = getDataFormService().getEntryName(jspKey);
	    // Checks if the user has rights on this user profile
	    if(authenticator.hasPermissionOnUserProfile(userProfile)) {
		authorizedUsersProfilesKeys.add(jspKey);
	    }
	}
	
	return authorizedUsersProfilesKeys;
    }
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
