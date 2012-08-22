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
 * Basic controller handling the requests on the page displaying the results of the services.<br/>
 * 
 * @since  2012/08/22
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public abstract class BasicServiceResultController extends BasicResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicServiceResultController.class);
    
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
     * Constructor of {@link BasicServiceResultController}.
     * 
     * @param viewName
     * 			The view name associated to the controller.
     * @param formSessionAttribute
     * 			The session attribute containing the submitted form.
     */
    public BasicServiceResultController(String viewName, String formSessionAttribute) {
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
    public List<ExtendedResultRow> populateTableRows(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), formSessionAttribute)) {
	    return null;
	}
	
	// Retrieval of the submitted form
	ServiceForm serviceForm = (ServiceForm) getSessionForm(request.getSession(), formSessionAttribute);
	
	// Retrieval of the establishments uai
	List<String> establishmentsUai = new ArrayList<String>(Arrays.asList(serviceForm.getEstablishments()));
	
	// Retrieval of the establishments types
	List<String> establishmentsTypes = new ArrayList<String>(Arrays.asList(serviceForm.getEstablishmentsTypes()));
	
	// Retrieval of the users profiles to filter
	List<String> checkedProfiles = new ArrayList<String>(Arrays.asList(serviceForm.getUsersProfiles()));
	String userProfileToFilter = dataServiceFormService.getUsersProfilesToFilter(checkedProfiles).get(0);
	
	// Retrieval of the start date and end date
	Date startDate = serviceForm.getStartDate();
	Date endDate = serviceForm.getEndDate();
	
	// Retrieval of the services to filter
	List<String> checkedServices = new ArrayList<String>(Arrays.asList(serviceForm.getWantedServices()));
	List<String> services = dataServiceFormService.getServicesToFilter(checkedServices);
	 
	// Gets the result rows to display
	List<ExtendedResultRow> resultRows = createResultRows(establishmentsTypes, establishmentsUai, services, userProfileToFilter, startDate, endDate);
	
	return resultRows;
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
     * Creates the result rows; each result row containing the following data :
     * <ul>
     * 	<li>The establishment data (name, UAI,..)</li>
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
     * @param establishmentsUai
     * 			The UAI of the establishments.
     * @param services
     * 			The services concerned by the statistics.
     * @param userProfile
     * 			The user profile concerned by the statistics.
     * @param startDate
     * 			The start date of the statistics.
     * @param endDate
     * 			The end date of the statistics (can be <code>null</code>).
     * 
     * @return
     * 	the result rows containing the data to display.
     */
    protected abstract List<ExtendedResultRow> createResultRows( List<String> establishmentsTypes, List<String> establishmentsUai, List<String> services, String userProfile, Date startDate, Date endDate);
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
