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
     * Retrieves the number of activated accounts in the specified establishment for the specified <code>week</code>
     * of the specified <code>year</code>.<br/>
     * The activated accounts have to be associated to the specified user profile.
     *  
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile associated to the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The number of activated accounts in the establishment in the week.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findWeeklyNumActivatedAccountsForProfile(String establishmentUai, String userProfile, Integer week, Integer year);
    
    /**
     * Retrieves the number of activated accounts in the specified establishment for the specified <code>week</code>
     * of the specified <code>year</code>.<br/>
     * The activated accounts have to be associated to one of the specified users profiles.
     *  
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param usersProfiles
     * 			The available users profiles.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The number of activated accounts in the establishment in the week.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findWeeklyNumActivatedAccountsForProfiles(String establishmentUai, List<String> usersProfiles, Integer week, Integer year);

    /**
     * Retrieves the total number of accounts present in the specified establishment for the specified <code>week</code>
     * of the specified <code>year</code>.<br/>
     * This total of accounts only concerns the account associated to the specified <code>userProfile</code>.
     *  
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile associated to the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The total number of activated accounts in the establishment in the week.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findWeeklyTotalNumAccountsForProfile(String establishmentUai, String userProfile, Integer week, Integer year);
    
    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves the number of activated accounts in the specified establishment for the specified <code>month</code>
     * of the specified <code>year</code>.<br/>
     * The activated accounts have to be associated to the specified user profile.
     *  
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile associated to the statistic to retrieve.
     * @param month
     * 			The month number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The number of activated accounts in the establishment in the month.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findMonthlyNumActivatedAccountsForProfile(String establishmentUai, String userProfile, Integer month, Integer year);
    
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
     * Retrieves the total number of accounts present in the specified establishment for the specified <code>month</code>
     * of the specified <code>year</code>.<br/>
     *  
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile associated to the statistic to retrieve.
     * @param month
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The total number of activated accounts in the establishment in the month.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findMonthlyTotalNumAccountsForProfile(String establishmentUai, String userProfile, Integer month, Integer year);
    
    
}
