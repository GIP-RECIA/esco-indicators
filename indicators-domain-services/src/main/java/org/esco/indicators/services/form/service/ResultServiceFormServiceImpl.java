/**
 * 
 */
package org.esco.indicators.services.form.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.result.EstablishmentData;
import org.esco.indicators.domain.beans.result.ExtendedResultRow;
import org.esco.indicators.domain.beans.result.PunctualAccountStatistic;
import org.esco.indicators.domain.beans.result.BasicResultRow;
import org.esco.indicators.domain.beans.result.ServiceStatistic;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.constants.ServicesConstants;
import org.esco.indicators.services.statistic.AccountStatisticService;
import org.esco.indicators.services.statistic.ServiceConnectionStatisticService;
import org.esco.indicators.services.structure.EstablishmentService;

/**
 * Implementation of the {@link ResultServiceFormService} interface.
 * 
 * @since  2012/07/25
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ResultServiceFormServiceImpl implements ResultServiceFormService {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ResultServiceFormServiceImpl.class);
    
    /** Service providing access to account activation statistics data */
    private AccountStatisticService accountStatisticService;
    
    /** Service providing access to establishment data */
    private EstablishmentService establishmentService;
    
    /** Service providing access to the services connections statistics */
    private ServiceConnectionStatisticService serviceConnectionStatisticService;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS


    //--------------------------------------------------------------------------- GETTERS / SETTERS
    
    /**
     * Sets the account activation service.
     * 
     * @param accountStatisticService 
     * 			The account activation service to set.
     */
    public void setAccountStatisticService(
    	AccountStatisticService accountStatisticService) {
        this.accountStatisticService = accountStatisticService;
    }
    
    /**
     * Sets the establishment service.
     * @param establishmentService 
     * 				The establishment service to set.
     */
    public void setEstablishmentService(EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }

    /**
     * Sets the services statistics service.
     * @param serviceConnectionStatisticService 
     * 				The services statistics service to set.
     */
    public void setServiceConnectionStatisticService(
    	ServiceConnectionStatisticService serviceConnectionStatisticService) {
        this.serviceConnectionStatisticService = serviceConnectionStatisticService;
    }



    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    ///////////////////////////////////////////////////////
    // WEEKLY RESULTS
    ///////////////////////////////////////////////////////
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.service.ResultServiceFormService#getPunctualWeekResultRows(java.util.List, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<ExtendedResultRow> getPunctualWeekResultRows(List<String> establishmentsUai, List<String> services,
            String userProfile, Integer week, Integer year) {
	// Final result
	List<ExtendedResultRow> rows = new ArrayList<ExtendedResultRow>();

	// For each establishment :
	//	Creation of the corresponding result row
	//	Addition of the establishment data in the result row
	//	Addition of the statistic data in the result row (for each service)
	for (String uai : establishmentsUai) {
	    ExtendedResultRow resultRow = createWeeklyExtendedResultRow(uai, userProfile, week, year);
	    for (String service : services) {
		ServiceStatistic statistic = createPunctualWeekStatisticData(uai, service, userProfile, week, year);
		resultRow.putStatisticData(service, statistic);
	    }
	    rows.add(resultRow);
	}
	
	return rows;
    }

    ///////////////////////////////////////////////////////
    // MONTHLY RESULTS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.service.ResultServiceFormService#getPunctualMonthResultRows(java.util.List, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<ExtendedResultRow> getPunctualMonthResultRows(List<String> establishmentsUai,
            List<String> services, String userProfile, Integer month, Integer year) {
	// Final result
	List<ExtendedResultRow> rows = new ArrayList<ExtendedResultRow>();

	// For each establishment :
	//	Creation of the corresponding result row
	//	Addition of the establishment data in the result row
	//	Addition of the statistic data in the result row (for each service)
	for (String uai : establishmentsUai) {
	    ExtendedResultRow resultRow = createMonthlyExtendedResultRow(uai, userProfile, month, year);
	    for (String service : services) {
		ServiceStatistic statistic = createPunctualMonthStatisticData(uai, service, userProfile, month, year);
		resultRow.putStatisticData(service, statistic);
	    }
	    rows.add(resultRow);
	}
	
	return rows;
    }
    

    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Creates an extended result row containing the all the data on the establishment, and the accounts.<br/>
     * The accounts data only concerned the the accounts having the specified user profile in the specified period.
     * 
     * The statistics data are not filled.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the created row.
     * @param userProfile
     * 			The user profile.
     * @param month
     * 			The number of the month.
     * @param year 
     * 			The year.
     * @return
     * 	the exented result row containing informations on the establishment and the accounts.
     */
    private ExtendedResultRow createMonthlyExtendedResultRow(String establishmentUai, String userProfile, Integer month, Integer year) {
	// Gets the informations on the accounts
	Integer numTotalAccounts = accountStatisticService.findMonthlyTotalNumAccountsForProfile(establishmentUai, userProfile, month, year);
	Integer numActivatedAccounts = accountStatisticService.findMonthlyNumActivatedAccountsForProfile(establishmentUai, userProfile, month, year);
	
	// Gets the informations on the establishment
	EstablishmentData establishmentData = getEstablishmentData(establishmentUai);
	
	// Creation of the result row
	ExtendedResultRow resultRow = new ExtendedResultRow(numTotalAccounts, numActivatedAccounts);
	resultRow.setEstablishmentData(establishmentData);
	
	return resultRow;
    }
    
    /**
     * Creates an extended result row containing the all the data on the establishment, and the accounts.<br/>
     * The accounts data only concerned the the accounts having the specified user profile in the specified period.
     * 
     * The statistics data are not filled.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the created row.
     * @param userProfile
     * 			The user profile.
     * @param week
     * 			The number of the week.
     * @param year 
     * 			The year.
     * @return
     * 	the exented result row containing informations on the establishment and the accounts.
     */
    private ExtendedResultRow createWeeklyExtendedResultRow(String establishmentUai, String userProfile, Integer week, Integer year) {
	// Gets the informations on the accounts
	Integer numTotalAccounts = accountStatisticService.findWeeklyTotalNumAccountsForProfile(establishmentUai, userProfile, week, year);
	Integer numActivatedAccounts = accountStatisticService.findWeeklyNumActivatedAccountsForProfile(establishmentUai, userProfile, week, year);
	
	// Gets the informations on the establishment
	EstablishmentData establishmentData = getEstablishmentData(establishmentUai);
	
	// Creation of the result row
	ExtendedResultRow resultRow = new ExtendedResultRow(numTotalAccounts, numActivatedAccounts);
	resultRow.setEstablishmentData(establishmentData);
	
	return resultRow;
    }
    
    /**
     * Retrieves data and creates the statistic data for a specified :
     * <ul>
     * 	<li>establishment</li>
     * 	<li>service</li>
     * 	<li>user profile</li>
     * 	<li>month</li>
     * 	<li>year</li>
     * </ul>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment.
     * @param service
     * 			The service.
     * @param userProfile
     * 			The user profile.
     * @param month
     * 			The number of the month in the year.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the statistic data.
     */
    private ServiceStatistic createPunctualMonthStatisticData(String establishmentUai, String service, String userProfile, Integer month, Integer year) {
	// Retrieval of the total account number
	Integer totalAccountNumber = accountStatisticService.findMonthlyTotalNumAccountsForProfile(establishmentUai, userProfile, month, year);
	
	// Retrieval of the service visitors statistics with a number of connections below / above a treshold
	Integer treshold = ServicesConstants.NUM_CONNECTIONS_TRESHOLD;
	Integer numVisitorsAboveTreshold = serviceConnectionStatisticService.findMonthlyNumVisitorsAboveTreshold(establishmentUai, service, userProfile, treshold, month, year);
	Integer numVisitorsBelowTreshold = serviceConnectionStatisticService.findMonthlyNumVisitorsBelowTreshold(establishmentUai, service, userProfile, treshold, month, year);
	
	// Retrieval of the number of visits realized on the service
	Integer numVisits = serviceConnectionStatisticService.findMonthlyNumVisits(establishmentUai, service, userProfile, month, year);
	
	// Creation of the statistic data
	ServiceStatistic data = new ServiceStatistic(totalAccountNumber, numVisitorsBelowTreshold, numVisitorsAboveTreshold, numVisits);
	
	return data;
    }
    
    /**
     * Retrieves data and creates the statistic data for a specified :
     * <ul>
     * 	<li>establishment</li>
     * 	<li>service</li>
     * 	<li>user profile</li>
     * 	<li>week</li>
     * 	<li>year</li>
     * </ul>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment.
     * @param service
     * 			The service.
     * @param userProfile
     * 			The user profile.
     * @param week
     * 			The number of the week in the year.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the statistic data.
     */
    private ServiceStatistic createPunctualWeekStatisticData(String establishmentUai, String service, String userProfile, Integer week, Integer year) {
	// Retrieval of the total account number
	Integer totalAccountNumber = accountStatisticService.findWeeklyTotalNumAccountsForProfile(establishmentUai, userProfile, week, year);
	
	// Retrieval of the service visitors statistics with a number of connections below / above a treshold
	Integer treshold = ServicesConstants.NUM_CONNECTIONS_TRESHOLD;
	Integer numVisitorsAboveTreshold = serviceConnectionStatisticService.findWeeklyNumVisitorsAboveTreshold(establishmentUai, service, userProfile, treshold, week, year);
	Integer numVisitorsBelowTreshold = serviceConnectionStatisticService.findWeeklyNumVisitorsBelowTreshold(establishmentUai, service, userProfile, treshold, week, year);
	
	// Retrieval of the number of visits realized on the service
	Integer numVisits = serviceConnectionStatisticService.findWeeklyNumVisits(establishmentUai, service, userProfile, week, year);
	
	// Creation of the statistic data
	ServiceStatistic data = new ServiceStatistic(totalAccountNumber, numVisitorsBelowTreshold, numVisitorsAboveTreshold, numVisits);
	
	return data;
    }
    
    /**
     * Gets the establishment data for the specified UAI.
     * 
     * @param uai
     * 			The UAI of the establishment.
     * 
     * @return
     * 	the result data associated to the establishment.<br/>
     * 	<code>null</code> if no establishment data has been retrieved.
     */
    private EstablishmentData getEstablishmentData(String uai) {
	// Gets the establishment
	Establishment establishment = establishmentService.findEstablishmentByUai(uai);
	if(establishment == null) {
	    return null;
	}
	
	// Map establishment to establishment data
	String countyNumber = establishment.getCountyNumber().toString();
	String name = establishment.getName();
	String establishmentType = establishment.getType();
	EstablishmentData data = new EstablishmentData(countyNumber, name, establishmentType, uai);
	
	return data;
    }
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
