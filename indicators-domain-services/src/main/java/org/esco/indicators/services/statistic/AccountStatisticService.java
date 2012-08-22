/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.util.List;


/**
 * Interface providing access statistical data on accounts.
 * 
 * @since 2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface AccountStatisticService {

    
    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves the number of activated accounts in the specified establishments for the specified <code>week</code>
     * of the specified <code>year</code>.<br/>
     * This number of activated accounts does not consider a particular user profile.
     *  
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The number of activated accounts in the establishments in the week.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findWeeklyNumActivatedAccounts(List<String> establishmentsUai, Integer week, Integer year);
    
    /**
     * Retrieves the number of activated accounts in the specified establishments for the specified <code>week</code>
     * of the specified <code>year</code>.<br/>
     * The activated accounts have to be associated to the specified user profile.
     *  
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile associated to the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The number of activated accounts in the establishments in the week.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findWeeklyNumActivatedAccountsForProfile(List<String> establishmentsUai, String userProfile, Integer week, Integer year);
    
    /**
     * Retrieves the number of activated accounts in the specified establishments for the specified <code>week</code>
     * of the specified <code>year</code>.<br/>
     * The activated accounts have to be associated to one of the specified users profiles.
     *  
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param usersProfiles
     * 			The available users profiles.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The number of activated accounts in the establishments in the week.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findWeeklyNumActivatedAccountsForProfiles(List<String> establishmentsUai, List<String> usersProfiles, Integer week, Integer year);

    /**
     * Retrieves the total number of accounts present in the specified establishments for the specified <code>week</code>
     * of the specified <code>year</code>.<br/>
     * This total of accounts does not consider a particular user profile.
     *  
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The total number of activated accounts in the establishments in the week.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findWeeklyTotalNumAccounts(List<String> establishmentsUai, Integer week, Integer year);
    
    /**
     * Retrieves the total number of accounts present in the specified establishments for the specified <code>week</code>
     * of the specified <code>year</code>.<br/>
     * This total of accounts only concerns the account associated to the specified <code>userProfile</code>.
     *  
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile associated to the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The total number of activated accounts in the establishments in the week.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findWeeklyTotalNumAccountsForProfile(List<String> establishmentsUai, String userProfile, Integer week, Integer year);
    
    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves the number of activated accounts in the specified establishment for the specified <code>month</code>
     * of the specified <code>year</code>.<br/>
     * This number of activated accounts does not consider a particular user profile.
     *  
     * @param establishmentsUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param month
     * 			The month number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The number of activated accounts in the establishment in the month.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findMonthlyNumActivatedAccounts(List<String> establishmentsUai, Integer month, Integer year);
    
    /**
     * Retrieves the number of activated accounts in the specified establishments for the specified <code>month</code>
     * of the specified <code>year</code>.<br/>
     * The activated accounts have to be associated to the specified user profile.
     *  
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile associated to the statistic to retrieve.
     * @param month
     * 			The month number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The number of activated accounts in the establishments in the month.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findMonthlyNumActivatedAccountsForProfile(List<String> establishmentsUai, String userProfile, Integer month, Integer year);
    
    /**
     * Retrieves the number of activated accounts in the specified establishment for the specified <code>month</code>
     * of the specified <code>year</code>.<br/>
     * The activated accounts have to be associated to one of the specified users profiles.
     *  
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param usersProfiles
     * 			The available users profiles.
     * @param month
     * 			The month number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The number of activated accounts in the establishment in the month.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findMonthlyNumActivatedAccountsForProfiles(String establishmentUai, List<String> usersProfiles, Integer month, Integer year);

    /**
     * Retrieves the total number of accounts present in the specified establishments for the specified <code>month</code>
     * of the specified <code>year</code>.<br/>
     * This total of accounts does not consider a particular user profile.
     *  
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param month
     * 			The monthnumber of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The total number of activated accounts in the establishments in the month.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findMonthlyTotalNumAccounts(List<String> establishmentsUai, Integer month, Integer year);
    
    /**
     * Retrieves the total number of accounts present in the specified establishments for the specified <code>month</code>
     * of the specified <code>year</code>.<br/>
     *  
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile associated to the statistic to retrieve.
     * @param month
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The total number of activated accounts in the establishments in the month.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findMonthlyTotalNumAccountsForProfile(List<String> establishmentsUai, String userProfile, Integer month, Integer year);

}
