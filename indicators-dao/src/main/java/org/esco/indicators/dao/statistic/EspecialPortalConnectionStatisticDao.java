/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.util.Date;

/**
 * Interface providing functions to access statistical data on the connections to the portal.<br/>
 * These data can have two kind of significations.<br/>
 * <ul>
 * <li>
 * 	If the data are associated to the current week / month : <br/>
 * 		Then no aggregation / sum has already been made on the data (aggregation represented by the beans
 * 		<code>AggregatedPortalConnectionStatistic</code>).<br/>
 * 		In this case, these data can be used to calculate statistics on the connections for the current week / month.
 * </li>
 * <li>
 * 	If the data are associated to a past week / month :<br/>
 * 		Then an aggregation / sum has already been made on the data  (aggregation represented by the beans
 * 		<code>AggregatedPortalConnectionStatistic</code>).<br/>
 * 		In this case, these data only represent the connections of the users that are especial (i.e. users having different profiles,...).<br/>
 * 		Moreover, these data has to be used to complete the aggregated data which not consider the especial data.
 * </li>
 * </ul>
 * All the non-especial data (for the past weeks / months) are accessible through the {@link PortalConnectionStatisticDao} interface.
 * 
 * @since 2012/05/31
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface EspecialPortalConnectionStatisticDao extends StatisticDao {
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
     * @return the number of connections made on the portal.<br/>
     *         <code>null</code> if no statistic has been retrieved.
     */
    public Integer findWeeklyNumConnectionsByProfile(String establishmentUai, Date firstWeekDay,
	    String userProfile);

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
     * @return the number of connections made on the portal.<br/>
     *         <code>null</code> if no statistic has been retrieved.
     */
    public Integer findMonthlyNumConnectionsByProfile(String establishmentUai, Date firstMonthDay,
	    String userProfile);
}
