/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.util.Date;
import java.util.List;

import org.esco.indicators.domain.beans.statistic.WeeklyPortalConnectionStatistic;

/**
 * Interface providing functions to access statistical data on the connections to the portal.<br/>
 * These data represent non-especial data which have been aggregated at the end of a past week / month.<br/>
 * All the data concerning the current week / month are accessible through the {@link EspecialPortalConnectionStatisticDao} interface.<br/>
 * Moreover, all the past especial data are accessible through the {@link EspecialPortalConnectionStatisticDao} interface.
 * 
 * @since 2012/06/05
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface PortalConnectionStatisticDao extends StatisticDao {
    // /////////////////////////////////////////////////////
    // DAILY STATISTICS
    // /////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    // /////////////////////////////////////////////////////
    /**
     * Retrieves the number of connections made on the portal for the specified user profile
     * <code>userprofile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the week beggining with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentUai
     *            The UAI of the establishment concerned by the statistic.
     * @param firstWeekDay
     *            The first day of the week associated to the statistic.
     * @param userProfile
     *            The user profile concerned by the statistic.
     * @return 
     * 	the number of connections made on the portal.<br/>
     *         the number 0 if no statistic has been retrieved.
     */
    public Integer findWeeklyNumConnectionsByProfile(String establishmentUai, Date firstWeekDay,
	    String userProfile);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal, and who has the specified 
     * <code>userProfile</code> the specified <code>establishmentUai</code>.
     * This number of connections only concerns the week beggining with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param firstWeekDay
     * 			he first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsBelowTreshold(String establishmentUai, Date firstWeekDay,
	    String userProfile, Integer treshold);
    
    // /////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    // /////////////////////////////////////////////////////
    /**
     * Retrieves the number of connections made on the portal for the specified user profile
     * <code>userprofile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the month beggining with the day <code>firstMonthDay</code>.
     * 
     * @param establishmentUai
     *            The UAI of the establishment concerned by the statistic.
     * @param firstMonthDay
     *            The first day of the month associated to the statistic.
     * @param userProfile
     *            The user profile concerned by the statistic.
     * @return 
     * 	the number of connections made on the portal.<br/>
     *         the number 0 if no statistic has been retrieved.
     */
    public Integer findMonthlyNumConnectionsByProfile(String establishmentUai, Date firstMonthDay,
	    String userProfile);

}
