/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.account.AccountActivationDao;
import org.esco.indicators.dao.profile.ProfileLinkDao;
import org.esco.indicators.domain.beans.account.AccountActivation;
import org.esco.indicators.utils.date.DateUtils;

/**
 * Implementation of the {@link AccountStatisticService} interface.
 * 
 * @since  2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class AccountStatisticServiceImpl implements AccountStatisticService {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(AccountStatisticServiceImpl.class);
    
    /** DAO providing access to account activations data */
    private AccountActivationDao accountActivationDao;
    
    /** DAO providing access to profile links data */
    private ProfileLinkDao profileLinkDao;


    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link AccountStatisticServiceImpl} class. 
     */
    public AccountStatisticServiceImpl() {
	super();
    }


    //--------------------------------------------------------------------------- GETTERS / SETTERS

    /**
     * Sets the DAO providing access to account activations data.
     * 
     * @param accountActivationDao 
     * 				The DAO, providing access to account activations data, to set.
     */
    public void setAccountActivationDao(AccountActivationDao accountActivationDao) {
        this.accountActivationDao = accountActivationDao;
    }
    
    /**
     * Sets the DAO providing access to profile links data.
     * 
     * @param profileLinkDao 
     * 			The DAO,providing access to profile links data, to set.
     */
    public void setProfileLinkDao(ProfileLinkDao profileLinkDao) {
        this.profileLinkDao = profileLinkDao;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findWeeklyNumActivatedAccountsForProfile(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumActivatedAccountsForProfile(String establishmentUai, String userProfile, Integer week, Integer year) {
	// List of the available users profiles
	List<String> usersProfiles = new ArrayList<String>();
	usersProfiles.add(userProfile);
	
	// Get the number of activated accounts for this profile
	return findWeeklyNumActivatedAccountsForProfiles(establishmentUai, usersProfiles, week, year);
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findWeeklyNumActivatedAccountsForProfiles(java.lang.String, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumActivatedAccountsForProfiles(String establishmentUai,
	    List<String> usersProfiles, Integer week, Integer year) {
	// Get the  dates corresponding to the first (and last) days of the week for the year
	Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
	Date lastWeekDay = DateUtils.addDays(firstWeekDay, 6);
	
	// Get the number of activated accounts for this week
	Integer numActivatedAccounts = findNumActivatedAccounts(establishmentUai, usersProfiles,	firstWeekDay, lastWeekDay);
	
	return numActivatedAccounts;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findWeeklyTotalNumAccounts(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyTotalNumAccounts(String establishmentUai, Integer week, Integer year) {
	// Get the  dates corresponding to the first (and last) days of the week for the year
	Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
	Date lastWeekDay = DateUtils.addDays(firstWeekDay, 6);
	
	// Get the total number of accounts for this week
	return findTotalNumAccounts(establishmentUai, firstWeekDay, lastWeekDay);
    }


    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////   

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findMonthlyNumActivatedAccountsForProfile(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumActivatedAccountsForProfile(String establishmentUai, String userProfile,
	    Integer month, Integer year) {
	// List of the available users profiles
	List<String> usersProfiles = new ArrayList<String>();
	usersProfiles.add(userProfile);
	
	// Get the number of activated accounts for this profile in this month
	return findMonthlyNumActivatedAccountsForProfiles(establishmentUai, usersProfiles, month, year);
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findMonthlyNumActivatedAccountsForProfiles(java.lang.String, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumActivatedAccountsForProfiles(String establishmentUai, List<String> usersProfiles, Integer month, Integer year) {
	// Get the dates corresponding to the first (and last) days of the month for the year
	Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
	Date lastMonthDay = DateUtils.getLastMonthDay(month, year);
	
	// Get the number of activated accounts for this month
	return findNumActivatedAccounts(establishmentUai, usersProfiles, firstMonthDay, lastMonthDay);
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findMonthlyTotalNumAccounts(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyTotalNumAccounts(String establishmentUai, Integer month, Integer year) {
	// Get the dates corresponding to the first (and last) days of the month for the year
	Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
	Date lastMonthDay = DateUtils.getLastMonthDay(month, year);
	
	// Get the number of activated accounts for this month
	return findTotalNumAccounts(establishmentUai, firstMonthDay, lastMonthDay);
    }



    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Retrieves the number of activated accounts in the specified establishment between the start date (<code>startDate</code>) and the end date (<code>endDate</code>).<br/>
     * These activated accounts have to be associated to one of the specified users profiles.
     * 
     * @param establishmentUai
     * 			UAI of the establishment.
     * @param usersProfiles
     * 			The profiles of the users.
     * @param startDate
     * 			The start date of account activation.
     * @param endDate
     * 			The end date of account activation.
     * 
     * @return
     * 	the number of actived accounts in the establishment, during the specified period.<br/>
     * 	the number 0 if no data has been retrieved.
     */
    private Integer findNumActivatedAccounts(String establishmentUai, List<String> usersProfiles, Date startDate, Date endDate) {
	Integer numActivatedAccounts = accountActivationDao.findNumActivatedAccountsForProfiles(establishmentUai, usersProfiles, startDate, endDate);
	numActivatedAccounts = (numActivatedAccounts == null ? 0 : numActivatedAccounts);
	return numActivatedAccounts;
    }
    
    /**
     * Retrieves the total number of accounts that were linked to the establishment in the period (delimited by the start date and end date).<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment.
     * @param startDate
     * 			The start date of the period.
     * @param endDate
     * 			The end date of the period.
     * 
     * @return
     * 	the total number of accounts linked to the establishment in the specified period.<br/>
     * 	the number 0 if no data has been retrieved.
     */
    private Integer findTotalNumAccounts(String establishmentUai, Date startDate, Date endDate) {
	Integer totalNumAccounts = profileLinkDao.findTotalNumLinkedAccounts(establishmentUai, startDate, endDate);
	totalNumAccounts = (totalNumAccounts == null ? 0 : totalNumAccounts);
	return totalNumAccounts;
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
