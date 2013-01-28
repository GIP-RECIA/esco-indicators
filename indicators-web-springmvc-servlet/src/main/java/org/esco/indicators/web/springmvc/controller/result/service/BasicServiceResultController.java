/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.result.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.ServiceForm;
import org.esco.indicators.domain.beans.result.ExtendedResultRow;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.form.service.ResultServiceFormService;
import org.esco.indicators.utils.classes.IntegerPair;
import org.esco.indicators.web.springmvc.controller.result.basic.BasicResultController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;

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
    public BasicServiceResultController(String viewName, String formSessionAttribute) {
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
    @Qualifier("dataServiceFormService")
    public void setDataFormService(DataFormService dataFormService) {
	this.dataFormService = dataFormService;
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
	List<String> services = getDataFormService().getServicesToFilter(wantedServices);
	
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
    public List<? extends ExtendedResultRow> populateTableRows(HttpServletRequest request) {
	// Checks if the there is a valid submitted form to process
	if(!containsForm(request.getSession(), formSessionAttribute)) {
	    return null;
	}
	
	// Retrieval of the submitted form
	ServiceForm serviceForm = (ServiceForm) getSessionForm(request.getSession(), formSessionAttribute);
	
	// Retrieval of the establishments types
	List<String> establishmentsTypes = new ArrayList<String>(Arrays.asList(serviceForm.getAllEstablishmentsTypes()));
	
	// Retrieval of the users profiles to filter
	List<String> checkedProfiles = new ArrayList<String>(Arrays.asList(serviceForm.getUsersProfiles()));
	List<String> usersProfilesToFilter = getDataFormService().getUsersProfilesToFilter(checkedProfiles);
	
	// Retrieval of the start date and end date
	Date startDate = serviceForm.getStartDate();
	Date endDate = serviceForm.getEndDate();
	
	// Retrieval of the services to filter
	List<String> checkedServices = new ArrayList<String>(Arrays.asList(serviceForm.getWantedServices()));
	List<String> services = getDataFormService().getServicesToFilter(checkedServices);
	 
	// If the sum on counties has to be made on the result rows
	String sumOnCounties = serviceForm.getSumOnCounties();
	if(sumOnCounties != null) {
	    // Retrieval of the county numbers and types to filter
	    List<String> countyNumbersToFilter = getDataFormService().getCountyNumbersToFilter(sumOnCounties);
	    List<String> establishmentsTypesToFilter = getDataFormService().getEstablishmentsTypesToFilter(establishmentsTypes);
	    LOGGER.debug("The sum on counties has been asked. The result rows will concern the counties : " + countyNumbersToFilter + " and the establishments types : " + establishmentsTypesToFilter);
	    return createSumOnCountiesResultRows(establishmentsTypes, countyNumbersToFilter, establishmentsTypesToFilter, services, usersProfilesToFilter, startDate, endDate);
	}
	
	// Retrieval of the establishments uai
	List<String> establishmentsUai = new ArrayList<String>(Arrays.asList(serviceForm.getEstablishments()));
	LOGGER.debug("The sum on counties has not been asked. The result rows will concern the establishments : " + establishmentsUai);
	return createEstablishmentsResultRows(establishmentsTypes, establishmentsUai, services, usersProfilesToFilter, startDate, endDate);
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
    protected abstract List<? extends ExtendedResultRow> createEstablishmentsResultRows( List<String> establishmentsTypes, List<String> establishmentsUai, List<String> services, List<String> usersProfiles, Date startDate, Date endDate);

    /**
     * Creates the result rows; each result row containing the following data :
     * <ul>
     * 	<li>The county data (county number)</li>
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
     * @param checkedEstablishmentTypes
     * 			The establishments types checked in the user view.
     * @param countyNumbers
     * 			The numbers of the counties concerned by the statistics.
     * @param establishmentsTypes
     * 			The types of the establishments concerned by the statistics.
     * @param services
     * 			The services concerned by the statistics.
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
    protected abstract List<ExtendedResultRow> createSumOnCountiesResultRows( List<String> checkedEstablishmentTypes, List<String> countyNumbers, List<String> establishmentsTypes, List<String> services, List<String> usersProfiles, Date startDate, Date endDate);
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
