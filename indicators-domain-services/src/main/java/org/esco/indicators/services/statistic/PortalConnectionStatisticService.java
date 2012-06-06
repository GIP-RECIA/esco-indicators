/**
 * 
 */
package org.esco.indicators.services.statistic;


/**
 * Interface providing functions to access statistical data on the portal connections.
 * 
 * @since  2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface PortalConnectionStatisticService {

    ///////////////////////////////////////////////////////
    // DAILY STATISTICS
    ///////////////////////////////////////////////////////
    
    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves the number of connections made on the portal for the specified user profile
     * <code>userprofile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the week having the number <code>week</code> in the year <code>year</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @return
     * 	the number of connections made on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved. 
     */
    public Integer findWeeklyNumConnectionsByProfile(String establishmentUai, String userProfile, Integer week, Integer year);
    
    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves the number of connections made on the portal for the specified user profile
     * <code>userprofile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the month having the number <code>month</code> of the year <code>year</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param month
     * 			The month number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @return
     * 	the number of connections made on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved. 
     */
    public Integer findMonthlyNumConnectionsByProfile(String establishmentUai, String userProfile, Integer month, Integer year);
}
