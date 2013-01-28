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
import org.esco.indicators.domain.beans.result.DetailResultRow;
import org.esco.indicators.domain.beans.result.ExtendedResultRow;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.form.service.ResultServiceFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Basic controller handling the requests on the page displaying the results of the services for only one establishment.<br/>
 * 
 * @since  2012/11/05
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public abstract class BasicEstablishmentServiceResultController extends BasicServiceResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicEstablishmentServiceResultController.class);
    
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
     * Populate the field containing the list of the keys used to index the statistic data in each result row.<br/>
     * In this page, the keys used to index the statistic data are : the services.<br/>
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the list of the keys used to index the statistic data.
     */
    @Override
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
    @Override
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
	List<String> authorizedProfiles = keepAuthorizedJspKeysForUsersProfiles(getAllUsersProfiles());
	
	// Retrieval of the start date and end date
	Date startDate = serviceForm.getStartDate();
	Date endDate = serviceForm.getEndDate();
	
	// Retrieval of the services to filter
	List<String> checkedServices = new ArrayList<String>(Arrays.asList(serviceForm.getWantedServices()));
	List<String> services = getDataFormService().getServicesToFilter(checkedServices);
	 
	// Retrieval of the establishment uai
	String establishmentUai = getEstablishmentUai(request);
	LOGGER.debug("The result rows will concern the establishment : [" + establishmentUai + "]");
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	return createEstablishmentsResultRows(establishmentsTypes, establishmentsUai, services, authorizedProfiles, startDate, endDate);
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
    
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.service.result.BasicServiceResultController#createEstablishmentsResultRows(java.util.List, java.util.List, java.util.List, java.util.List, java.util.Date, java.util.Date)
     */
    @Override
    protected abstract List<DetailResultRow> createEstablishmentsResultRows( List<String> establishmentsTypes,  List<String> establishmentsUai, List<String> services, List<String> userProfiles, Date startDate, Date endDate);


    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.service.result.BasicServiceResultController#createSumOnCountiesResultRows(java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.Date, java.util.Date)
     */
    @Override
    protected List<ExtendedResultRow> createSumOnCountiesResultRows( List<String> checkedEstablishmentTypes, List<String> countyNumbers, List<String> establishmentsTypes, List<String> services, List<String> usersProfiles, Date startDate, Date endDate) {
	return null;
    }
 
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
