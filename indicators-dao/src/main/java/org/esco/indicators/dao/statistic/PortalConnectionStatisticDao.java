/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.util.Date;

/**
 * Interface providing functions to access statistical data on the connections to the portal.
 * 
 * @since 2012/05/31
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface PortalConnectionStatisticDao extends StatisticDao {
    ///////////////////////////////////////////////////////
    // DAILY STATISTICS
    ///////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves the number of connections made on the portal for the specified user profile
     * <code>userprofile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the week beggining with the day <code>firstWeekDay</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment concerned by the statistic.
     * @param firstWeekDay
     * 			The first day of the week associated to the statistic.
     * @param userProfile
     * 			The user profile concerned by the statistic.
     * @return
     * 	the number of connections made on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved. 
     */
    public Integer findWeeklyNumConnectionsByProfile(String establishmentUai, Date firstWeekDay, String userProfile);
    
    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves the number of connections made on the portal for the specified user profile
     * <code>userprofile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the month beggining with the day <code>firstMonthDay</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment concerned by the statistic.
     * @param firstMonthDay
     * 			The first day of the month associated to the statistic.
     * @param userProfile
     * 			The user profile concerned by the statistic.
     * @return
     * 	the number of connections made on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved. 
     */
    public Integer findMonthlyNumConnectionsByProfile(String establishmentUai, Date firstMonthDay, String userProfile);
}
