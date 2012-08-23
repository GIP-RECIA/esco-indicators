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
import org.esco.indicators.utils.classes.IntegerPair;
import org.esco.indicators.utils.date.DateUtils;

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
    
    /** Manager providing informations on the sum services */
    private SumServicesManager sumServicesManager;
    
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
    
    /**
     * Sets the manager providing informations on the sum services.
     * 
     * @param sumServicesManager 
     * 			The manager of the sum services to set.
     */
    public void setSumServicesManager(SumServicesManager sumServicesManager) {
        this.sumServicesManager = sumServicesManager;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    ///////////////////////////////////////////////////////
    // WEEKLY RESULTS
    ///////////////////////////////////////////////////////
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.service.ResultServiceFormService#getPeriodicWeekResultRows(java.util.List, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<ExtendedResultRow> getPeriodicWeekResultRows(List<String> establishmentsUai,
            List<String> services, String userProfile, Integer startWeek, Integer startYear, Integer endWeek,
            Integer endYear) {
	// Final result
	List<ExtendedResultRow> rows = new ArrayList<ExtendedResultRow>();
	
	// Splits the specified period into weeks
	List<IntegerPair> weeksAndYears = DateUtils.splitWeeks(startWeek, startYear, endWeek, endYear);
	
	// For each establishment :
	//	Creation of the corresponding result row
	//	Addition of the establishment data in the result row
	//	Addition of result rows into the the created row (one row per period)
	for (String uai : establishmentsUai) {
	    // First level row : Establishment data
	    ExtendedResultRow firstLevelRow = new ExtendedResultRow();
	    firstLevelRow.setEstablishmentData(createEstablishmentData(uai));
	    
	    for (IntegerPair weekAndYear : weeksAndYears) {
		// Second level row : Statistic data for a period and a service
		ExtendedResultRow secondLevelRow = createWeeklyExtendedResultRow(uai, userProfile, weekAndYear.getFirst(), weekAndYear.getSecond());
		secondLevelRow.setEstablishmentData(createEstablishmentData(uai));
		for (String service : services) {
		    ServiceStatistic statistic = createPunctualWeekStatisticData(uai, service, userProfile, weekAndYear.getFirst(), weekAndYear.getSecond());
		    secondLevelRow.putStatisticData(service, statistic);
		}
		firstLevelRow.putStatisticData(weekAndYear, secondLevelRow);
	    }
	    
	    rows.add(firstLevelRow);
	}
	return rows;
    }

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
	    resultRow.setEstablishmentData(createEstablishmentData(uai));
	    for (String service : services) {
		ServiceStatistic statistic = createPunctualWeekStatisticData(uai, service, userProfile, week, year);
		resultRow.putStatisticData(service, statistic);
	    }
	    rows.add(resultRow);
	}
	
	return rows;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.service.ResultServiceFormService#getPunctualWeekResultRows(java.util.List, java.util.List, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<ExtendedResultRow> getPunctualWeekResultRows(List<String> countyNumbers,
            List<String> establishmentsTypes, List<String> services, String userProfile, Integer week,
            Integer year) {
	// Final result
	List<ExtendedResultRow> rows = new ArrayList<ExtendedResultRow>();
	
	// For each county number :
	//	Creation of the corresponding result row
	//	Addition of the county data in the result row
	//	Addition of the statistic data in the result row (for each service)
	for(String countyNumber : countyNumbers) {
	    List<String> establishmentsUai = establishmentService.findEstablishmentsUaiByCounty(countyNumber, establishmentsTypes);
	    if(!establishmentsUai.isEmpty()) {
		ExtendedResultRow resultRow = createWeeklyExtendedResultRow(establishmentsUai, userProfile, week, year);
		resultRow.setEstablishmentData(createCountyData(countyNumber));
		LOGGER.debug("The aggregated establishments for the county [" + countyNumber +"] are " + establishmentsUai );
        	    for(String service : services) {
        		ServiceStatistic statistic = createPunctualWeekStatisticData(establishmentsUai, service, userProfile, week, year);
        		resultRow.putStatisticData(service, statistic);
        	    }
        	    rows.add(resultRow);
	    }
	}
	
        return rows;
    }
    
    ///////////////////////////////////////////////////////
    // MONTHLY RESULTS
    ///////////////////////////////////////////////////////
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.service.ResultServiceFormService#getPeriodicMonthResultRows(java.util.List, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<ExtendedResultRow> getPeriodicMonthResultRows(List<String> establishmentsUai,
            List<String> services, String userProfile, Integer startMonth, Integer startYear,
            Integer endMonth, Integer endYear) {
	// Final result
	List<ExtendedResultRow> rows = new ArrayList<ExtendedResultRow>();
	
	// Splits the specified period into months
	List<IntegerPair> monthsAndYears = DateUtils.splitMonths(startMonth, startYear, endMonth, endYear);
	
	// For each establishment :
	//	Creation of the corresponding result row
	//	Addition of the establishment data in the result row
	//	Addition of result rows into the the created row (one row per period)
	for (String uai : establishmentsUai) {
	    // First level row : Establishment data
	    ExtendedResultRow firstLevelRow = new ExtendedResultRow();
	    firstLevelRow.setEstablishmentData(createEstablishmentData(uai));
	    
	    for (IntegerPair monthAndYear : monthsAndYears) {
		// Second level row : Statistic data for a period and a service
		ExtendedResultRow secondLevelRow = createMonthlyExtendedResultRow(uai, userProfile, monthAndYear.getFirst(), monthAndYear.getSecond());
		for (String service : services) {
		    ServiceStatistic statistic = createPunctualMonthStatisticData(uai, service, userProfile, monthAndYear.getFirst(), monthAndYear.getSecond());
		    secondLevelRow.putStatisticData(service, statistic);
		}
		firstLevelRow.putStatisticData(monthAndYear, secondLevelRow);
	    }
	    
	    rows.add(firstLevelRow);
	}
	return rows;
    }

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
     * Creates an establishment data only containing a county number.
     * 
     * @param countyNumber
     * 			The county number to put in the establishment data.
     * 
     * @return
     * 	the establishment data containing a county number set.
     */
    private EstablishmentData createCountyData(String countyNumber) {
	return (new EstablishmentData(countyNumber, null, null, null));
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
    private EstablishmentData createEstablishmentData(String uai) {
        // Gets the establishment
        Establishment establishment = establishmentService.findEstablishmentByUai(uai);
        if(establishment == null) {
            return null;
        }
        
        // Map establishment to establishment data
        String countyNumber = establishment.getCountyNumber();
        String name = establishment.getName();
        String establishmentType = establishment.getType();
        EstablishmentData data = new EstablishmentData(countyNumber, name, establishmentType, uai);
        
        return data;
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
     * @param month
     * 			The number of the month.
     * @param year 
     * 			The year.
     * @return
     * 	the exented result row containing informations on the establishment and the accounts.
     */
    private ExtendedResultRow createMonthlyExtendedResultRow(String establishmentUai, String userProfile, Integer month, Integer year) {
	// Put the UAI into a list
	List<String> establishmentsUAI = new ArrayList<String>();
	establishmentsUAI.add(establishmentUai);
	
	// Gets the informations on the accounts
	Integer numTotalAccounts = accountStatisticService.findMonthlyTotalNumAccountsForProfile(establishmentsUAI, userProfile, month, year);
	Integer numActivatedAccounts = accountStatisticService.findMonthlyNumActivatedAccountsForProfile(establishmentsUAI, userProfile, month, year);
	
	// Gets the informations on the establishment
	EstablishmentData establishmentData = createEstablishmentData(establishmentUai);
	
	// Creation of the result row
	ExtendedResultRow resultRow = new ExtendedResultRow(numTotalAccounts, numActivatedAccounts);
	resultRow.setEstablishmentData(establishmentData);
	
	return resultRow;
    }
    
    /**
     * Creates an extended result row containing the all the data the accounts in the establishment.<br/>
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
	// Put the establishment UAI into a list
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	return createWeeklyExtendedResultRow(establishmentsUai, userProfile, week, year);
    }
    
    /**
     * Creates an extended result row containing the all the data the accounts in the establishments.<br/>
     * The accounts data only concerned the the accounts having the specified user profile in the specified period.
     * 
     * The statistics data are not filled.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the created row.
     * @param userProfile
     * 			The user profile.
     * @param week
     * 			The number of the week.
     * @param year 
     * 			The year.
     * @return
     * 	the exented result row containing informations on the establishment and the accounts.
     */
    private ExtendedResultRow createWeeklyExtendedResultRow(List<String> establishmentsUai, String userProfile, Integer week, Integer year) {
	// Gets the informations on the accounts
	Integer numTotalAccounts = accountStatisticService.findWeeklyTotalNumAccountsForProfile(establishmentsUai, userProfile, week, year);
	Integer numActivatedAccounts = accountStatisticService.findWeeklyNumActivatedAccountsForProfile(establishmentsUai, userProfile, week, year);
	
	// Creation of the result row
	return ( new ExtendedResultRow(numTotalAccounts, numActivatedAccounts) );
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
	// Retrieval of the simple services concerned by the statistic
	List<String> services = getSimpleServices(service);
	
	// New list for the UAI
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	
	// Retrieval of the total account number
	Integer totalAccountNumber = accountStatisticService.findMonthlyTotalNumAccountsForProfile(establishmentsUai, userProfile, month, year);
	
	// Retrieval of the service visitors statistics with a number of connections below / above a treshold
	Integer treshold = ServicesConstants.NUM_CONNECTIONS_TRESHOLD;
	Integer numVisitorsAboveTreshold = serviceConnectionStatisticService.findMonthlyNumVisitorsAboveTreshold(establishmentUai, services, userProfile, treshold, month, year);
	Integer numVisitorsBelowTreshold = serviceConnectionStatisticService.findMonthlyNumVisitorsBelowTreshold(establishmentUai, services, userProfile, treshold, month, year);
	
	// Retrieval of the number of visits realized on the service
	Integer numVisits = serviceConnectionStatisticService.findMonthlyNumVisits(establishmentUai, services, userProfile, month, year);
	
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
	// New list for the UAI
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	return createPunctualWeekStatisticData(establishmentsUai, service, userProfile, week, year);
    }
    
    /**
     * Retrieves data and creates the statistic data for a specified :
     * <ul>
     * 	<li>establishments</li>
     * 	<li>service</li>
     * 	<li>user profile</li>
     * 	<li>week</li>
     * 	<li>year</li>
     * </ul>
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments.
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
    private ServiceStatistic createPunctualWeekStatisticData(List<String> establishmentsUai, String service, String userProfile, Integer week, Integer year) {
	// Retrieval of the simple services concerned by the statistic
	List<String> services = getSimpleServices(service);
	
	// Retrieval of the total account number
	Integer totalAccountNumber = accountStatisticService.findWeeklyTotalNumAccountsForProfile(establishmentsUai, userProfile, week, year);
	
	// Retrieval of the service visitors statistics with a number of connections below / above a treshold
	Integer treshold = ServicesConstants.NUM_CONNECTIONS_TRESHOLD;
	Integer numVisitorsAboveTreshold = serviceConnectionStatisticService.findWeeklyNumVisitorsAboveTreshold(establishmentsUai, services, userProfile, treshold, week, year);
	Integer numVisitorsBelowTreshold = serviceConnectionStatisticService.findWeeklyNumVisitorsBelowTreshold(establishmentsUai, services, userProfile, treshold, week, year);
	
	// Retrieval of the number of visits realized on the service
	Integer numVisits = serviceConnectionStatisticService.findWeeklyNumVisits(establishmentsUai, services, userProfile, week, year);
	
	// Creation of the statistic data
	ServiceStatistic data = new ServiceStatistic(totalAccountNumber, numVisitorsBelowTreshold, numVisitorsAboveTreshold, numVisits);
	
	return data;
    }
    
    /**
     * Retrieves the simple services to use for retrieving the statisitcs data.<br/>
     * 
     * @param service
     * 			The service name.
     * 
     * @return
     * 	if the specified service is a sum service : a list containing the simple services associated to the service.<br/>
     * 	in other cases : a list only containing the specified service.
     */
    private List<String> getSimpleServices(String service) {
	// Final result
	List<String> simpleServices = new ArrayList<String>();
	
	// If the service is the sum of simple services
	if(sumServicesManager.isSumService(service)) {
	    // Gets the simple services
	    simpleServices.addAll(sumServicesManager.getSimpleServices(service));
	} else {
	    // The service is a simple service
	    simpleServices.add(service);
	}
	
	return simpleServices;
    }
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
