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
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findWeeklyNumActivatedAccounts(java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumActivatedAccounts(List<String> establishmentsUai, Integer week, Integer year) {
	// Gets the last day of the week
	Date lastWeekDay = DateUtils.getLastWeekDay(week, year);
	
         return findNumActivatedAccounts(establishmentsUai, lastWeekDay, lastWeekDay);
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findWeeklyNumActivatedAccountsForProfiles(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumActivatedAccountsForProfiles(List<String> establishmentsUai,
	    List<String> usersProfiles, Integer week, Integer year) {
	// Get the  dates corresponding to the last day of the week for the year
	Date lastWeekDay = DateUtils.getLastWeekDay(week, year);
	// Get the number of activated accounts for this week
	return findNumActivatedAccountsForProfiles(establishmentsUai, usersProfiles, lastWeekDay, lastWeekDay);
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findWeeklyTotalNumAccounts(java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyTotalNumAccounts(List<String> establishmentsUai, Integer week, Integer year) {
	// Get the  dates corresponding to the last day of the week for the year
	Date lastWeekDay = DateUtils.getLastWeekDay(week, year);
	
	// Get the total number of accounts for this week
	Integer totalNumAccounts = profileLinkDao.findTotalNumLinkedAccounts(establishmentsUai, lastWeekDay, lastWeekDay);
	totalNumAccounts = (totalNumAccounts == null ? 0 : totalNumAccounts);
	
	return totalNumAccounts;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findWeeklyTotalNumAccountsForProfile(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyTotalNumAccountsForProfiles(List<String> establishmentsUai, List<String> usersProfiles, Integer week, Integer year) {
	// Get the  dates corresponding to the last day of the week for the year
	Date lastWeekDay = DateUtils.getLastWeekDay(week, year);
	// Get the total number of accounts for this week
	return findTotalNumAccountsForProfiles(establishmentsUai, usersProfiles, lastWeekDay, lastWeekDay);
    }


    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////   

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findMonthlyNumActivatedAccounts(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumActivatedAccounts(List<String> establishmentsUai, Integer month, Integer year) {
	// Gets the last day of the month
	Date lastMonthDay = DateUtils.getLastMonthDay(month, year);
	// Get the total number of accounts for this month
         return findNumActivatedAccounts(establishmentsUai, lastMonthDay, lastMonthDay);
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findMonthlyNumActivatedAccountsForProfile(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumActivatedAccountsForProfiles(List<String> establishmentsUai, List<String> usersProfiles,
	    Integer month, Integer year) {
	// Get the dates corresponding to the last day of the month for the year
	Date lastMonthDay = DateUtils.getLastMonthDay(month, year);
	// Get the number of activated accounts for this month
	return findNumActivatedAccountsForProfiles(establishmentsUai, usersProfiles, lastMonthDay, lastMonthDay);
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findMonthlyNumActivatedAccountsForProfiles(java.lang.String, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumActivatedAccountsForProfiles(String establishmentUai, List<String> usersProfiles, Integer month, Integer year) {
	// Get the dates corresponding to the last day of the month for the year
	Date lastMonthDay = DateUtils.getLastMonthDay(month, year);
	
	// Get the number of activated accounts for this month
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	return findNumActivatedAccountsForProfiles(establishmentsUai, usersProfiles, lastMonthDay, lastMonthDay);
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findMonthlyTotalNumAccounts(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyTotalNumAccounts(List<String> establishmentsUai, Integer month, Integer year) {
	// Get the  dates corresponding to the last day of the week for the year
	Date lastMonthDay = DateUtils.getLastMonthDay(month, year);
	
	// Get the total number of accounts for this week
	Integer totalNumAccounts = profileLinkDao.findTotalNumLinkedAccounts(establishmentsUai, lastMonthDay, lastMonthDay);
	totalNumAccounts = (totalNumAccounts == null ? 0 : totalNumAccounts);
	
	return totalNumAccounts;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountStatisticService#findMonthlyTotalNumAccountsForProfiles(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyTotalNumAccountsForProfiles(List<String> establishmentsUai, List<String> usersProfiles, Integer month, Integer year) {
	// Get the dates corresponding to the last day of the month for the year
	Date lastMonthDay = DateUtils.getLastMonthDay(month, year);
	// Get the number of activated accounts for this month
	return findTotalNumAccountsForProfiles(establishmentsUai, usersProfiles, lastMonthDay, lastMonthDay);
    }



    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Retrieves the number of activated accounts in the specified establishments between the start date (<code>startDate</code>) and the end date (<code>endDate</code>).<br/>
     * 
     * @param establishmentsUai
     * 			UAI of the establishment.
     * @param startDate
     * 			The start date of account activation.
     * @param endDate
     * 			The end date of account activation.
     * 
     * @return
     * 	the number of actived accounts in the establishments, during the specified period.<br/>
     * 	the number 0 if no data has been retrieved.
     */
    private Integer findNumActivatedAccounts(List<String> establishmentsUai, Date startDate, Date endDate) {
	Integer numActivatedAccounts = accountActivationDao.findNumActivatedAccounts(establishmentsUai, startDate, endDate);
	numActivatedAccounts = (numActivatedAccounts == null ? 0 : numActivatedAccounts);
	return numActivatedAccounts;
    }
    
    /**
     * Retrieves the number of activated accounts in the specified establishments between the start date (<code>startDate</code>) and the end date (<code>endDate</code>).<br/>
     * These activated accounts have to be associated to one of the specified users profiles.
     * 
     * @param establishmentsUai
     * 			UAI of the establishment.
     * @param usersProfiles
     * 			The profiles of the users.
     * @param startDate
     * 			The start date of account activation.
     * @param endDate
     * 			The end date of account activation.
     * 
     * @return
     * 	the number of actived accounts in the establishments, during the specified period.<br/>
     * 	the number 0 if no data has been retrieved.
     */
    private Integer findNumActivatedAccountsForProfiles(List<String> establishmentsUai, List<String> usersProfiles, Date startDate, Date endDate) {
	Integer numActivatedAccounts = accountActivationDao.findNumActivatedAccountsForProfiles(establishmentsUai, usersProfiles, startDate, endDate);
	numActivatedAccounts = (numActivatedAccounts == null ? 0 : numActivatedAccounts);
	return numActivatedAccounts;
    }
    
    /**
     * Retrieves the total number of accounts that were linked to the establishments in the period (delimited by the start date and end date).<br/>
     * These accounts have to be associated to one of the specified <code>userProfile</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments.
     * @param usersProfiles
     * 			The users profiles.
     * @param startDate
     * 			The start date of the period.
     * @param endDate
     * 			The end date of the period.
     * 
     * @return
     * 	the total number of accounts linked to the establishments in the specified period, with the specified user profile.<br/>
     * 	the number 0 if no data has been retrieved.
     */
    private Integer findTotalNumAccountsForProfiles(List<String> establishmentsUai, List<String> usersProfiles, Date startDate, Date endDate) {
	Integer totalNumAccounts = profileLinkDao.findTotalNumLinkedAccountsForProfiles(establishmentsUai, usersProfiles, startDate, endDate);
	totalNumAccounts = (totalNumAccounts == null ? 0 : totalNumAccounts);
	return totalNumAccounts;
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
