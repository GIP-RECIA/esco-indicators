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
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.domain.beans.form.ServiceForm;
import org.esco.indicators.domain.beans.result.BasicResultRow;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.form.account.ResultAccountFormService;
import org.esco.indicators.services.form.service.ResultServiceFormService;
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
 * @since  2012/07/25
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/services-attendance-result")
public class PunctualServiceResultController extends BasicResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PunctualServiceResultController.class);
    
    /** Data form service providing information on the data from for the service */
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
     * Default constructor of the {@link PunctualAccountResultController} class.
     */
    public PunctualServiceResultController() {
	super("services-attendance-result", SessionConstants.SERVICE_FORM_ATTR);
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
     * Populate the services field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted values for the services field.
     */
    @ModelAttribute("servicesItems")
    public List<String> populateServices(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        ServiceForm form =  (ServiceForm) getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String [] wantedServices = form.getWantedServices();
        List<String> jspKeys = new ArrayList<String>(Arrays.asList(wantedServices));
        List<String> i18nKeys = getI18nKeys(jspKeys);
        
        return i18nKeys;
    }
    
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
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        ServiceForm serviecForm = (ServiceForm) getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the users profiles to filter
        List<String> wantedServices = new ArrayList<String>(Arrays.asList(serviecForm.getWantedServices()));
        List<String> servicesToFilter = dataServiceFormService.getServicesToFilter(wantedServices);
        
        return servicesToFilter;
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
	if(!containsForm(request.getSession(), formSessionAttribute)) {
	    return null;
	}
	
	// Retrieval of the submitted form
	ServiceForm serviceForm = (ServiceForm) getSessionForm(request.getSession(), formSessionAttribute);
	
	// Retrieval of the establishments uai
	List<String> establishmentsUai = new ArrayList<String>(Arrays.asList(serviceForm.getEstablishments()));
	
	// Retrieval of the establishments types
	List<String> establishmentsTypes = new ArrayList<String>(Arrays.asList(serviceForm.getEstablishmentsTypes()));
	
	// Retrieval of the user profile to filter
	List<String> checkedProfiles = new ArrayList<String>(Arrays.asList(serviceForm.getUsersProfiles()));
	String userProfileToFilter = dataServiceFormService.getUsersProfilesToFilter(checkedProfiles).get(0);
	
	// Retrieval of the start date
	Date startDate = serviceForm.getStartDate();
	
	// Retrieval of the services to filter
	List<String> checkedServices = new ArrayList<String>(Arrays.asList(serviceForm.getWantedServices()));
	List<String> services = dataServiceFormService.getServicesToFilter(checkedServices);
	
	// Gets the result rows to display
	List<BasicResultRow> basicResultRows = createResultRows(establishmentsTypes, establishmentsUai, services, userProfileToFilter, startDate);
	
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
     * @param services
     * 			The services concerned by the statistics.
     * @param userProfile
     * 			The users profiles concerned by the statistics.
     * @param startDate
     * 			The start date of the statistics.
     * 
     * @return
     * 	the result rows containing the data to display.
     */
    private List<BasicResultRow> createResultRows( List<String> establishmentsTypes, List<String> establishmentsUai, List<String> services, String userProfile, Date startDate) {
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
//	return resultServiceFormService.getPunctualMonthResultRows(establishmentsUai, services, userProfile, month, year);
	return null;
    }
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
