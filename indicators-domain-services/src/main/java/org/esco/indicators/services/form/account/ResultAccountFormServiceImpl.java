/**
 * 
 */
package org.esco.indicators.services.form.account;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.result.BasicResultRow;
import org.esco.indicators.domain.beans.result.EstablishmentData;
import org.esco.indicators.domain.beans.result.PeriodicAccountStatistic;
import org.esco.indicators.domain.beans.result.PunctualAccountStatistic;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.constants.ServicesConstants;
import org.esco.indicators.services.statistic.AccountStatisticService;
import org.esco.indicators.services.statistic.EstablishmentVisitStatisticService;
import org.esco.indicators.services.statistic.PortalConnectionStatisticService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.classes.IntegerPair;
import org.esco.indicators.utils.date.DateUtils;

/**
 * Implementation of the {@link ResultAccountFormService} interface.
 * 
 * @since  2012/07/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ResultAccountFormServiceImpl implements ResultAccountFormService {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ResultAccountFormServiceImpl.class);
    
    /** Service providing access to account activation statistics data */
    private AccountStatisticService accountStatisticService;
    
    /** Service providing access to establishment data */
    private EstablishmentService establishmentService;
    
    /** Service providing access to establishment visits / visitors */
    private EstablishmentVisitStatisticService establishmentVisitStatisticService;
    
    /** Service providing access to the portal connection statistics */
    private PortalConnectionStatisticService portalConnectionStatisticService;
    
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
     * Sets the service providing statistics on establishment visits / visitors.
     * 
     * @param establishmentVisitStatistic 
     * 			The service to set.
     */
    public void setEstablishmentVisitStatisticService(EstablishmentVisitStatisticService establishmentVisitStatisticService) {
        this.establishmentVisitStatisticService = establishmentVisitStatisticService;
    }

    /**
     * Sets the portal connection service.
     * 
     * @param portalConnectionStatisticService 
     * 			The portal connection service to set.
     */
    public void setPortalConnectionStatisticService(
    	PortalConnectionStatisticService portalConnectionStatisticService) {
        this.portalConnectionStatisticService = portalConnectionStatisticService;
    }



    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    ///////////////////////////////////////////////////////
    // WEEKLY RESULTS
    ///////////////////////////////////////////////////////
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.ResultFormService#getPunctualWeekResultRows(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<BasicResultRow> getPunctualWeekResultRows(List<String> establishmentsUai,
	    List<String> usersProfiles, Integer week, Integer year) {
	// Final result
	List<BasicResultRow> rows = new ArrayList<BasicResultRow>();

	// For each establishment :
	//	Creation of the corresponding result row
	//	Addition of the establishment data in the result row
	//	Addition of the statistic data in the result row (for each user profile)
	//	Addition of the global statistic
	for (String uai : establishmentsUai) {
	    BasicResultRow basicResultRow = new BasicResultRow();
	    basicResultRow.setEstablishmentData(getEstablishmentData(uai));
	    for (String profile : usersProfiles) {
		PunctualAccountStatistic statistic = createPunctualWeekStatisticDataForUai(uai, profile, week, year);
		basicResultRow.putStatisticData(profile, statistic);
	    }
	    basicResultRow.putStatisticData(ServicesConstants.GLOBAL_STATISTIC, createGlobalWeekStatisticDataForUai(uai, week, year));
	    rows.add(basicResultRow);
	}
	
	return rows;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.account.ResultAccountFormService#getPunctualWeekResultRows(java.util.List, java.util.List, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<BasicResultRow> getPunctualWeekResultRows(List<String> countyNumbers,
            List<String> establishmentsTypes, List<String> usersProfiles, Integer week, Integer year) {
	// Final result
	List<BasicResultRow> rows = new ArrayList<BasicResultRow>();
	
	// For each county number :
	//	Creation of the corresponding result row
	//	Addition of the county data in the result row
	//	Addition of the statistic data in the result row (for each user profile)
	// 	Addition of the global statistic
	for(String countyNumber : countyNumbers) {
	    BasicResultRow basicResultRow = new BasicResultRow();
	    basicResultRow.setEstablishmentData(createCountyData(countyNumber));
	    List<String> establishmentsUai = getEstablishmentsUaiByCounty(countyNumber, establishmentsTypes);
	    if(!establishmentsUai.isEmpty()) {
		    LOGGER.debug("The aggregated establishments for the county [" + countyNumber +"] are " + establishmentsUai );
        	    for(String profile : usersProfiles) {
        		PunctualAccountStatistic statistic = createPunctualWeekStatisticData(establishmentsUai,  profile, week, year);
        		basicResultRow.putStatisticData(profile, statistic);
        	    }
        	    basicResultRow.putStatisticData(ServicesConstants.GLOBAL_STATISTIC, createGlobalWeekStatisticDataForCounty(establishmentsUai, countyNumber, establishmentsTypes, week, year));
        	    rows.add(basicResultRow);
	    }
	}
	
        return rows;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.ResultFormService#getWeeklyResultRows(java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<BasicResultRow> getPeriodicWeekResultRows(List<String> establishmentsUai, String userProfile,
            Integer startWeek, Integer startYear, Integer endWeek, Integer endYear) {
	// Final result
	List<BasicResultRow> rows = new ArrayList<BasicResultRow>();
	
	// Splits the specified period into weeks
	List<IntegerPair> weeksAndYears = DateUtils.splitWeeks(startWeek, startYear, endWeek, endYear);
	
	// For each establishment :
	//	Creation of the corresponding result row
	//	Addition of the establishment data in the result row
	//	Addition of the statistic data in the result row (for each period)
	for (String uai : establishmentsUai) {
	    BasicResultRow basicResultRow = new BasicResultRow();
	    basicResultRow.setEstablishmentData(getEstablishmentData(uai));
	    for (IntegerPair weekAndYear : weeksAndYears) {
		PunctualAccountStatistic statistic = createPunctualWeekStatisticDataForUai(uai, userProfile, weekAndYear.getFirst(), weekAndYear.getSecond());
		basicResultRow.putStatisticData(weekAndYear, statistic);
	    }
	    rows.add(basicResultRow);
	}
	
	return rows;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.account.ResultAccountFormService#getPeriodicWeekResultRows(java.util.List, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<BasicResultRow> getPeriodicWeekResultRows(List<String> countyNumbers,
            List<String> establishmentsTypes, String userProfile, Integer startWeek, Integer startYear,
            Integer endWeek, Integer endYear) {
	// Final result
	List<BasicResultRow> rows = new ArrayList<BasicResultRow>();
	
	// Splits the specified period into weeks
	List<IntegerPair> weeksAndYears = DateUtils.splitWeeks(startWeek, startYear, endWeek, endYear);
	
	// For each county number :
	//	Creation of the corresponding result row
	//	Addition of the county data in the result row
	//	Addition of the statistic data in the result row (for each period)
	// 	Addition of the global statistic
	for(String countyNumber : countyNumbers) {
	    BasicResultRow basicResultRow = new BasicResultRow();
	    basicResultRow.setEstablishmentData(createCountyData(countyNumber));
	    List<String> establishmentsUai = getEstablishmentsUaiByCounty(countyNumber, establishmentsTypes);
	    if(!establishmentsUai.isEmpty()) {
		    LOGGER.debug("The aggregated establishments for the county [" + countyNumber +"] are " + establishmentsUai );
		    for (IntegerPair weekAndYear : weeksAndYears) {
        		PunctualAccountStatistic statistic = createPunctualWeekStatisticData(establishmentsUai,  userProfile, weekAndYear.getFirst(), weekAndYear.getSecond());
        		basicResultRow.putStatisticData(weekAndYear, statistic);
        	    }
        	    rows.add(basicResultRow);
	    }
	}
	
        return rows;
    }
    
    
    ///////////////////////////////////////////////////////
    // MONTHLY RESULTS
    ///////////////////////////////////////////////////////
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.ResultFormService#getPunctualMonthResultRows(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<BasicResultRow> getPunctualMonthResultRows(List<String> establishmentsUai,
	    List<String> usersProfiles, Integer month, Integer year) {
	// Final result
	List<BasicResultRow> rows = new ArrayList<BasicResultRow>();

	// For each establishment :
	//	Creation of the corresponding result row
	//	Addition of the establishment data in the result row
	//	Addition of the statistic data in the result row (for each user profile)
	for (String uai : establishmentsUai) {
	    BasicResultRow basicResultRow = new BasicResultRow();
	    basicResultRow.setEstablishmentData(getEstablishmentData(uai));
	    for (String profile : usersProfiles) {
		PunctualAccountStatistic statistic = createPunctualMonthStatisticDataForUai(uai, profile, month, year);
		basicResultRow.putStatisticData(profile, statistic);
	    }
	    rows.add(basicResultRow);
	}
	
	return rows;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.account.ResultAccountFormService#getPunctualMonthResultRows(java.util.List, java.util.List, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<BasicResultRow> getPunctualMonthResultRows(List<String> countyNumbers,
            List<String> establishmentTypes, List<String> usersProfiles, Integer month, Integer year) {
	// Final result
	List<BasicResultRow> rows = new ArrayList<BasicResultRow>();
	
	// For each county number :
	//	Creation of the corresponding result row
	//	Addition of the county data in the result row
	//	Addition of the statistic data in the result row (for each user profile)
	// 	Addition of the global statistic
	for(String countyNumber : countyNumbers) {
	    BasicResultRow basicResultRow = new BasicResultRow();
	    basicResultRow.setEstablishmentData(createCountyData(countyNumber));
	    List<String> establishmentsUai = getEstablishmentsUaiByCounty(countyNumber, establishmentTypes);
	    if(!establishmentsUai.isEmpty()) {
		    LOGGER.debug("The aggregated establishments for the county [" + countyNumber +"] are " + establishmentsUai );
        	    for(String profile : usersProfiles) {
        		PunctualAccountStatistic statistic = createPunctualMonthStatisticData(establishmentsUai,  profile, month, year);
        		basicResultRow.putStatisticData(profile, statistic);
        	    }
        	    basicResultRow.putStatisticData(ServicesConstants.GLOBAL_STATISTIC, createGlobalMonthStatisticDataForCounty(establishmentsUai, countyNumber, establishmentTypes, month, year));
        	    rows.add(basicResultRow);
	    }
	}
	
        return rows;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.ResultFormService#getMonthlyResultRows(java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<BasicResultRow> getPeriodicMonthResultRows(List<String> establishmentsUai, String userProfile,
            Integer startMonth, Integer startYear, Integer endMonth, Integer endYear) {
	// Final result
	List<BasicResultRow> rows = new ArrayList<BasicResultRow>();
	
	// Splits the specified period into weeks
	List<IntegerPair> monthsAndYears = DateUtils.splitMonths(startMonth, startYear, endMonth, endYear);
	
	// For each establishment :
	//	Creation of the corresponding result row
	//	Addition of the establishment data in the result row
	//	Addition of the statistic data in the result row (for each period)
	for (String uai : establishmentsUai) {
	    BasicResultRow basicResultRow = new BasicResultRow();
	    basicResultRow.setEstablishmentData(getEstablishmentData(uai));
	    for (IntegerPair monthAndYear : monthsAndYears) {
		PunctualAccountStatistic statistic = createPunctualMonthStatisticDataForUai(uai, userProfile, monthAndYear.getFirst(), monthAndYear.getSecond());
		basicResultRow.putStatisticData(monthAndYear, statistic);
	    }
	    rows.add(basicResultRow);
	}
	
	return rows;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.account.ResultAccountFormService#getPeriodicMonthResultRows(java.util.List, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<BasicResultRow> getPeriodicMonthResultRows(List<String> countyNumbers,
            List<String> establishmentsTypes, String userProfile, Integer startMonth, Integer startYear,
            Integer endMonth, Integer endYear) {
	// Final result
	List<BasicResultRow> rows = new ArrayList<BasicResultRow>();
	
	// Splits the specified period into months
	List<IntegerPair> monthsAndYears = DateUtils.splitMonths(startMonth, startYear, endMonth, endYear);
			
	// For each county number :
	//	Creation of the corresponding result row
	//	Addition of the county data in the result row
	//	Addition of the statistic data in the result row (for each period)
	// 	Addition of the global statistic
	for(String countyNumber : countyNumbers) {
	    BasicResultRow basicResultRow = new BasicResultRow();
	    basicResultRow.setEstablishmentData(createCountyData(countyNumber));
	    List<String> establishmentsUai = getEstablishmentsUaiByCounty(countyNumber, establishmentsTypes);
	    if(!establishmentsUai.isEmpty()) {
		    LOGGER.debug("The aggregated establishments for the county [" + countyNumber +"] are " + establishmentsUai );
		    for (IntegerPair monthAndYear : monthsAndYears) {
        		PunctualAccountStatistic statistic = createPunctualMonthStatisticData(establishmentsUai,  userProfile, monthAndYear.getFirst(), monthAndYear.getSecond());
        		basicResultRow.putStatisticData(monthAndYear, statistic);
        	    }
        	    rows.add(basicResultRow);
	    }
	}
	
        return rows;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Creates a global statistic concerning the specified county number and the specified establishments types.<br/>
     * This statistic only concerns the specified month and year.<br/>
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments located in the county, and having a type contained in the specified establishments types.
     * @param countyNumber
     * 			The county number.
     * @param establishmentsTypes
     * 			The establishments types.
     * @param month
     * 			The month in the year.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the global statistic associated to the county number and the establishments types.
     */
    private PeriodicAccountStatistic createGlobalMonthStatisticDataForCounty(List<String> establishmentsUai, String countyNumber, List<String> establishmentsTypes,
            Integer month, Integer year) {
	// Retrieval of the total account number
	Integer totalAccountNumber = accountStatisticService.findMonthlyTotalNumAccounts(establishmentsUai, month, year);
	
	// Retrieval of the number of activated accounts
	Integer numActivatedAccounts = accountStatisticService.findMonthlyNumActivatedAccounts(establishmentsUai, month, year);
	
	// Retrieval of the statistics visitors with a number of portal connections below / above a treshold
	Integer treshold = ServicesConstants.NUM_CONNECTIONS_TRESHOLD;
	Integer numVisitorsAboveTreshold = portalConnectionStatisticService.findMonthlyNumVisitorsAboveTreshold(establishmentsUai, month, year, treshold);
	Integer numVisitorsBelowTreshold = portalConnectionStatisticService.findMonthlyNumVisitorsBelowTreshold(establishmentsUai, month, year, treshold);
	
	// Retrieval of the statistics visits
	Integer numVisits = establishmentVisitStatisticService.findCountyMonthlyNumVisits(countyNumber, establishmentsTypes, month, year);
	
	// Creation of the statistic data
	PeriodicAccountStatistic data = new PeriodicAccountStatistic(totalAccountNumber, numActivatedAccounts, numVisitorsBelowTreshold, numVisitorsAboveTreshold, numVisits);

	return data;
    }
    
    /**
     * Creates a global statistic concerning the specified county number and the specified establishments types.<br/>
     * This statistic only concerns the specified week and year.<br/>
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments located in the county, and having a type contained in the specified establishments types.
     * @param countyNumber
     * 			The county number.
     * @param establishmentsTypes
     * 			The establishments types.
     * @param week
     * 			The week in the year.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the global statistic associated to the county number and the establishments types.
     */
    private PeriodicAccountStatistic createGlobalWeekStatisticDataForCounty(List<String> establishmentsUai, String countyNumber, List<String> establishmentsTypes,
            Integer week, Integer year) {
	// Retrieval of the total account number
	Integer totalAccountNumber = accountStatisticService.findWeeklyTotalNumAccounts(establishmentsUai, week, year);
	
	// Retrieval of the number of activated accounts
	Integer numActivatedAccounts = accountStatisticService.findWeeklyNumActivatedAccounts(establishmentsUai, week, year);
	
	// Retrieval of the statistics visitors with a number of portal connections below / above a treshold
	Integer treshold = ServicesConstants.NUM_CONNECTIONS_TRESHOLD;
	Integer numVisitorsAboveTreshold = portalConnectionStatisticService.findWeeklyNumVisitorsAboveTreshold(establishmentsUai, week, year, treshold);
	Integer numVisitorsBelowTreshold = portalConnectionStatisticService.findWeeklyNumVisitorsBelowTreshold(establishmentsUai, week, year, treshold);
	
	// Retrieval of the statistics visits
	Integer numVisits = establishmentVisitStatisticService.findCountyWeeklyNumVisits(countyNumber, establishmentsTypes, week, year);
	
	// Creation of the statistic data
	PeriodicAccountStatistic data = new PeriodicAccountStatistic(totalAccountNumber, numActivatedAccounts, numVisitorsBelowTreshold, numVisitorsAboveTreshold, numVisits);

	return data;
    }

    /**
     * Creates a global statistic concerning the specified establishment.<br/>
     * This statistic only concerns the specified week and year.<br/>
     * 
     * This statistic gives informations about the accounts in the establishment without considering a particular user profile.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment.
     * @param week
     * 			The week number.
     * @param year
     * 			The year number.
     * 
     * @return
     * 	the global statistic.
     */
    private PeriodicAccountStatistic createGlobalWeekStatisticDataForUai(String establishmentUai, Integer week, Integer year) {
	// Put the establishment UAI into a list
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	
	// Retrieval of the total account number
	Integer totalAccountNumber = accountStatisticService.findWeeklyTotalNumAccounts(establishmentsUai, week, year);
	
	// Retrieval of the number of activated accounts
	Integer numActivatedAccounts = accountStatisticService.findWeeklyNumActivatedAccounts(establishmentsUai, week, year);
	
	// Retrieval of the statistics visitors with a number of portal connections below / above a treshold
	Integer treshold = ServicesConstants.NUM_CONNECTIONS_TRESHOLD;
	Integer numVisitorsAboveTreshold = portalConnectionStatisticService.findWeeklyNumVisitorsAboveTreshold(establishmentsUai, week, year, treshold);
	Integer numVisitorsBelowTreshold = portalConnectionStatisticService.findWeeklyNumVisitorsBelowTreshold(establishmentsUai, week, year, treshold);
	
	// Retrieval of the statistics visits
	Integer numVisits = establishmentVisitStatisticService.findEstablishmentWeeklyNumVisits(establishmentUai, week, year);
	
	// Creation of the statistic data
	PeriodicAccountStatistic data = new PeriodicAccountStatistic(totalAccountNumber, numActivatedAccounts, numVisitorsBelowTreshold, numVisitorsAboveTreshold, numVisits);

	return data;
    }
    
    
    /**
     * Retrieves data and creates the statistic data for a specified :
     * <ul>
     * 	<li>establishment</li>
     * 	<li>user profile</li>
     * 	<li>month</li>
     * 	<li>year</li>
     * </ul>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment.
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
    private PunctualAccountStatistic createPunctualMonthStatisticDataForUai(String establishmentUai, String userProfile, Integer month, Integer year) {
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai .add(establishmentUai);
	return createPunctualMonthStatisticData(establishmentsUai, userProfile, month, year);
    }
    
    /**
     * Retrieves data and creates the statistic data for a specified :
     * <ul>
     * 	<li>establishments</li>
     * 	<li>user profile</li>
     * 	<li>month</li>
     * 	<li>year</li>
     * </ul>
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments.
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
    private PunctualAccountStatistic createPunctualMonthStatisticData(List<String> establishmentsUai, String userProfile, Integer month, Integer year) {
	// Retrieval of the total account number
	Integer totalAccountNumber = accountStatisticService.findMonthlyTotalNumAccountsForProfile(establishmentsUai, userProfile, month, year);
	
	// Retrieval of the number of activated accounts
	Integer numActivatedAccounts = accountStatisticService.findMonthlyNumActivatedAccountsForProfile(establishmentsUai, userProfile, month, year);
	
	// Retrieval of the statistics visitors with a number of portal connections below / above a treshold
	Integer treshold = ServicesConstants.NUM_CONNECTIONS_TRESHOLD;
	Integer numVisitorsAboveTreshold = portalConnectionStatisticService.findMonthlyNumVisitorsAboveTresholdByProfile(establishmentsUai, userProfile, month, year, treshold);
	Integer numVisitorsBelowTreshold = portalConnectionStatisticService.findMonthlyNumVisitorsBelowTresholdByProfile(establishmentsUai, userProfile, month, year, treshold);
	
	// Creation of the statistic data
	PunctualAccountStatistic data = new PunctualAccountStatistic(totalAccountNumber, numActivatedAccounts, numVisitorsBelowTreshold, numVisitorsAboveTreshold);
	
	return data;
    }
    
    /**
     *  Retrieves data and creates the statistic data for a specified :
     * <ul>
     * 	<li>establishments</li>
     * 	<li>user profile</li>
     * 	<li>week</li>
     * 	<li>year</li>
     * </ul>
     * 
     * The returned statistic data represents an aggregation of the statistic data of the establishments.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments.
     * @param userProfile
     * 			The user profile.
     * @param week
     * 			The week in the year.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the statistic data.
     */
    private PunctualAccountStatistic createPunctualWeekStatisticData(List<String> establishmentsUai,
            String userProfile, Integer week, Integer year) {
	// Retrieval of the total account number
	Integer totalAccountNumber = accountStatisticService.findWeeklyTotalNumAccountsForProfile(establishmentsUai, userProfile, week, year);
	
	// Retrieval of the number of activated accounts
	Integer numActivatedAccounts = accountStatisticService.findWeeklyNumActivatedAccountsForProfile(establishmentsUai, userProfile, week, year);
	
	// Retrieval of the statistics visitors with a number of portal connections below / above a treshold
	Integer treshold = ServicesConstants.NUM_CONNECTIONS_TRESHOLD;
	Integer numVisitorsAboveTreshold = portalConnectionStatisticService.findWeeklyNumVisitorsAboveTresholdByProfile(establishmentsUai, userProfile, week, year, treshold);
	Integer numVisitorsBelowTreshold = portalConnectionStatisticService.findWeeklyNumVisitorsBelowTresholdByProfile(establishmentsUai, userProfile, week, year, treshold);
	
	// Creation of the statistic data
	PunctualAccountStatistic data = new PunctualAccountStatistic(totalAccountNumber, numActivatedAccounts, numVisitorsBelowTreshold, numVisitorsAboveTreshold);
	
	return data;
    }

    /**
     * Retrieves data and creates the statistic data for a specified :
     * <ul>
     * 	<li>establishment</li>
     * 	<li>user profile</li>
     * 	<li>week</li>
     * 	<li>year</li>
     * </ul>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment.
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
    private PunctualAccountStatistic createPunctualWeekStatisticDataForUai(String establishmentUai, String userProfile, Integer week, Integer year) {
	// Put the establishment UAI into a list
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	
	return createPunctualWeekStatisticData(establishmentsUai, userProfile, week, year);
    }
    
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
    private EstablishmentData getEstablishmentData(String uai) {
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
     * Gets the UAI of the establishments located in the county and having one of the specified type.
     * 
     * @param countyNumber
     * 			The number of the county.
     * @param establishmentsTypes 
     * 			The types of the establishments.
     * 
     * @return
     * 	the list containing the UAI of the establishments located in the county.<br/>
     * 	an empty list if no establishments UAI has been retrieved.
     */
    private List<String> getEstablishmentsUaiByCounty(String countyNumber, List<String> establishmentsTypes) {
	// Final result
	List<String> establishmentsUai = new ArrayList<String>();
	
	// New list for the county number
	List<String> countyNumbers = new ArrayList<String>();
	countyNumbers.add(countyNumber);
	
	// Retrieval of the establishments of the county
	List<Establishment> establishments = establishmentService.findEstablishmentsByCountyNumbersAndTypes(countyNumbers, establishmentsTypes);
	for (Establishment establishment : establishments) {
	    establishmentsUai.add(establishment.getUai());
	}
	
	return establishmentsUai;
    }
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
