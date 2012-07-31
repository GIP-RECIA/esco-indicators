/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.sql.Date;

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
     * Retrieves the number of connections made on the portal of the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the week beginning with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentUai
     *            The UAI of the establishment concerned by the statistic.
     * @param firstWeekDay
     *            The first day of the week associated to the statistic.
     * @return 
     * 	the number of connections made on the portal.<br/>
     *         <code>null</code> if no statistic has been retrieved.
     */
    public Integer findWeeklyNumConnections(String establishmentUai, Date firstWeekDay);
    
    /**
     * Retrieves the number of connections made on the portal for the specified user profile
     * <code>userprofile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the week beginning with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentUai
     *            The UAI of the establishment concerned by the statistic.
     * @param firstWeekDay
     *            The first day of the week associated to the statistic.
     * @param userProfile
     *            The user profile concerned by the statistic.
     * @return 
     * 	the number of connections made on the portal.<br/>
     *        <code>null</code> if no statistic has been retrieved.
     */
    public Integer findWeeklyNumConnectionsByProfile(String establishmentUai, Date firstWeekDay,
	    String userProfile);
    
    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal of  the specified <code>establishmentUai</code>.
     * This number of connections only concerns the week beginning with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param firstWeekDay
     * 			The first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsAboveTreshold(String establishmentUai, Date firstWeekDay,
            Integer treshold);

    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal, and who has the specified 
     * <code>userProfile</code> the specified <code>establishmentUai</code>.
     * This number of connections only concerns the week beginning with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param firstWeekDay
     * 			The first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsAboveTresholdByProfile(String establishmentUai, Date firstWeekDay,
	    String userProfile, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portalof  the specified <code>establishmentUai</code>.
     * This number of connections only concerns the week beginning with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param firstWeekDay
     * 			The first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsBelowTreshold(String establishmentUai, Date firstWeekDay, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal, and who has the specified 
     * <code>userProfile</code> the specified <code>establishmentUai</code>.
     * This number of connections only concerns the week beginning with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param firstWeekDay
     * 			The first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsBelowTresholdByProfile(String establishmentUai, Date firstWeekDay,
	    String userProfile, Integer treshold);
    
    // /////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    // /////////////////////////////////////////////////////
    /**
     * Retrieves the number of connections made on the portal for the specified user profile
     * <code>userprofile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the month beginning with the day <code>firstMonthDay</code>.
     * 
     * @param establishmentUai
     *            The UAI of the establishment concerned by the statistic.
     * @param firstMonthDay
     *            The first day of the month associated to the statistic.
     * @return 
     * 	the number of connections made on the portal.<br/>
     *         <code>null</code> if no statistic has been retrieved.
     */
    public Integer findMonthlyNumConnections(String establishmentUai, Date firstMonthDay);
    
    /**
     * Retrieves the number of connections made on the portal for the specified user profile
     * <code>userprofile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the month beginning with the day <code>firstMonthDay</code>.
     * 
     * @param establishmentUai
     *            The UAI of the establishment concerned by the statistic.
     * @param firstMonthDay
     *            The first day of the month associated to the statistic.
     * @param userProfile
     *            The user profile concerned by the statistic.
     * @return 
     * 	the number of connections made on the portal.<br/>
     *         <code>null</code> if no statistic has been retrieved.
     */
    public Integer findMonthlyNumConnectionsByProfile(String establishmentUai, Date firstMonthDay,
	    String userProfile);

    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal of the specified <code>establishmentUai</code>.
     * This number of connections only concerns the month beginning with the day <code>firstMonthDay</code>.<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param firstMonthDay
     * 			The first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findMonthlyNumVisitorsAboveTreshold(String establishmentUai, Date firstMonthDay, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal, and who has the specified 
     * <code>userProfile</code> the specified <code>establishmentUai</code>.
     * This number of connections only concerns the month beginning with the day <code>firstMonthDay</code>.<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param firstMonthDay
     * 			The first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findMonthlyNumVisitorsAboveTresholdByProfile(String establishmentUai, Date firstMonthDay,
	    String userProfile, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal of the specified <code>establishmentUai</code>.
     * This number of connections only concerns the month beginning with the day <code>firstMonthDay</code>.<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param firstMonthDay
     * 			The first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findMonthlyNumVisitorsBelowTreshold(String establishmentUai, Date firstMonthDay, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal, and who has the specified 
     * <code>userProfile</code> the specified <code>establishmentUai</code>.
     * This number of connections only concerns the month beginning with the day <code>firstMonthDay</code>.<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param firstMonthDay
     * 			The first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findMonthlyNumVisitorsBelowTresholdByProfile(String establishmentUai, Date firstMonthDay,
	    String userProfile, Integer treshold);

}
