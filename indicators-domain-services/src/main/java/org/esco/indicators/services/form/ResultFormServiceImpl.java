/**
 * 
 */
package org.esco.indicators.services.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.result.EstablishmentData;
import org.esco.indicators.domain.beans.result.ResultRow;
import org.esco.indicators.domain.beans.result.StatisticData;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.domain.beans.util.IntegerPair;
import org.esco.indicators.services.constants.ServicesConstants;
import org.esco.indicators.services.statistic.AccountStatisticService;
import org.esco.indicators.services.statistic.PortalConnectionStatisticService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.date.DateUtils;

/**
 * Implementation of the {@link ResultFormService} interface.
 * 
 * @since  2012/07/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ResultFormServiceImpl implements ResultFormService {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ResultFormServiceImpl.class);
    
    /** Service providing access to account activation statistics data */
    private AccountStatisticService accountStatisticService;
    
    /** Service providing access to establishment data */
    private EstablishmentService establishmentService;
    
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
    public List<ResultRow> getPunctualWeekResultRows(List<String> establishmentsUai,
	    List<String> usersProfiles, Integer week, Integer year) {
	// Final result
	List<ResultRow> rows = new ArrayList<ResultRow>();

	// For each establishment :
	//	Creation of the corresponding result row
	//	Addition of the establishment data in the result row
	//	Addition of the statistic data in the result row (for each user profile)
	for (String uai : establishmentsUai) {
	    ResultRow resultRow = new ResultRow();
	    resultRow.setEstablishmentData(getEstablishmentData(uai));
	    for (String profile : usersProfiles) {
		StatisticData statistic = createPunctualWeekStatisticData(uai, profile, week, year);
		resultRow.putStatisticData(profile, statistic);
	    }
	    rows.add(resultRow);
	}
	
	return rows;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.ResultFormService#getWeeklyResultRows(java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<ResultRow> getWeeklyResultRows(List<String> establishmentsUai, String userProfile,
            Integer startWeek, Integer startYear, Integer endWeek, Integer endYear) {
	// Final result
	List<ResultRow> rows = new ArrayList<ResultRow>();
	
	// Splits the specified period into weeks
	List<IntegerPair> weeksAndYears = DateUtils.splitWeeks(startWeek, startYear, endWeek, endYear);
	
	// For each establishment :
	//	Creation of the corresponding result row
	//	Addition of the establishment data in the result row
	//	Addition of the statistic data in the result row (for each period)
	for (String uai : establishmentsUai) {
	    ResultRow resultRow = new ResultRow();
	    resultRow.setEstablishmentData(getEstablishmentData(uai));
	    for (IntegerPair weekAndYear : weeksAndYears) {
		StatisticData statistic = createPunctualWeekStatisticData(uai, userProfile, weekAndYear.getFirst(), weekAndYear.getSecond());
		resultRow.putStatisticData(weekAndYear, statistic);
	    }
	    rows.add(resultRow);
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
    public List<ResultRow> getPunctualMonthResultRows(List<String> establishmentsUai,
	    List<String> usersProfiles, Integer month, Integer year) {
	// Final result
	List<ResultRow> rows = new ArrayList<ResultRow>();

	// For each establishment :
	//	Creation of the corresponding result row
	//	Addition of the establishment data in the result row
	//	Addition of the statistic data in the result row (for each user profile)
	for (String uai : establishmentsUai) {
	    ResultRow resultRow = new ResultRow();
	    resultRow.setEstablishmentData(getEstablishmentData(uai));
	    for (String profile : usersProfiles) {
		StatisticData statistic = createPunctualMonthStatisticData(uai, profile, month, year);
		resultRow.putStatisticData(profile, statistic);
	    }
	    rows.add(resultRow);
	}
	
	return rows;
    }
    
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.ResultFormService#getMonthlyResultRows(java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<ResultRow> getMonthlyResultRows(List<String> establishmentsUai, String userProfile,
            Integer startMonth, Integer startYear, Integer endMonth, Integer endYear) {
	// Final result
	List<ResultRow> rows = new ArrayList<ResultRow>();
	
	// Splits the specified period into weeks
	List<IntegerPair> monthsAndYears = DateUtils.splitMonths(startMonth, startYear, endMonth, endYear);
	
	// For each establishment :
	//	Creation of the corresponding result row
	//	Addition of the establishment data in the result row
	//	Addition of the statistic data in the result row (for each period)
	for (String uai : establishmentsUai) {
	    ResultRow resultRow = new ResultRow();
	    resultRow.setEstablishmentData(getEstablishmentData(uai));
	    for (IntegerPair monthAndYear : monthsAndYears) {
		StatisticData statistic = createPunctualMonthStatisticData(uai, userProfile, monthAndYear.getFirst(), monthAndYear.getSecond());
		resultRow.putStatisticData(monthAndYear, statistic);
	    }
	    rows.add(resultRow);
	}
	
	return rows;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
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
    private StatisticData createPunctualMonthStatisticData(String establishmentUai, String userProfile, Integer month, Integer year) {
	// Retrieval of the total account number
	Integer totalAccountNumber = accountStatisticService.findMonthlyTotalNumAccounts(establishmentUai, month, year);
	
	// Retrieval of the number of activated accounts
	Integer numActivatedAccounts = accountStatisticService.findMonthlyNumActivatedAccountsForProfile(establishmentUai, userProfile, month, year);
	
	// Retrieval of the statistics visitors with a number of portal connections below / above a treshold
	Integer treshold = ServicesConstants.NUM_CONNECTIONS_TRESHOLD;
	Integer numVisitorsAboveTreshold = portalConnectionStatisticService.findWeeklyNumVisitorsAboveTreshold(establishmentUai, userProfile, month, year, treshold);
	Integer numVisitorsBelowTreshold = portalConnectionStatisticService.findWeeklyNumVisitorsBelowTreshold(establishmentUai, userProfile, month, year, treshold);
	
	// Creation of the statistic data
	StatisticData data = new StatisticData(totalAccountNumber, numActivatedAccounts, numVisitorsBelowTreshold, numVisitorsAboveTreshold);
	
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
    private StatisticData createPunctualWeekStatisticData(String establishmentUai, String userProfile, Integer week, Integer year) {
	// Retrieval of the total account number
	Integer totalAccountNumber = accountStatisticService.findWeeklyTotalNumAccounts(establishmentUai, week, year);
	
	// Retrieval of the number of activated accounts
	Integer numActivatedAccounts = accountStatisticService.findWeeklyNumActivatedAccountsForProfile(establishmentUai, userProfile, week, year);
	
	// Retrieval of the statistics visitors with a number of portal connections below / above a treshold
	Integer treshold = ServicesConstants.NUM_CONNECTIONS_TRESHOLD;
	Integer numVisitorsAboveTreshold = portalConnectionStatisticService.findWeeklyNumVisitorsAboveTreshold(establishmentUai, userProfile, week, year, treshold);
	Integer numVisitorsBelowTreshold = portalConnectionStatisticService.findWeeklyNumVisitorsBelowTreshold(establishmentUai, userProfile, week, year, treshold);
	
	// Creation of the statistic data
	StatisticData data = new StatisticData(totalAccountNumber, numActivatedAccounts, numVisitorsBelowTreshold, numVisitorsAboveTreshold);
	
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
