/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.sql.Date;
import java.util.List;

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
     * Retrieves the average duration time of the connections made on the portal for the specified users profiles
     * <code>usersProfiles</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This average duration time only concerns the week beginning with the day <code>firstWeekDay</code>.
     * 
     * @param establishmentUai
     *            The UAI of the establishment concerned by the statistic.
     * @param firstWeekDay
     *            The first day of the week associated to the statistic.
     * @param usersProfiles
     *            The users profiles concerned by the statistic.
     *            
     * @return 
     * 	the average duration time the connections made on the portal.<br/>
     *         <code>null</code> if no statistic has been retrieved.
     */
    public Float findWeeklyConnectionsAverageDurationByProfiles(String establishmentUai, Date firstWeekDay,
            List<String> usersProfiles);
    
    /**
     * Retrieves the number of connections made on the portal of the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the week beggining with the day <code>firstWeekDay</code>.<br/>
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
     * Retrieves the number of connections made on the portal for the specified users profiles
     * <code>userprofile</code> and the specified establishments UAI <code>establishmentsUai</code>.<br/>
     * This number of connections only concerns the week beggining with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentsUai
     *            The UAI of the establishments concerned by the statistic.
     * @param firstWeekDay
     *            The first day of the week associated to the statistic.
     * @param usersProfiles
     *            The users profiles concerned by the statistic.
     * @return 
     * 	the number of connections made on the portal.<br/>
     *         <code>null</code> if no statistic has been retrieved.
     */
    public Integer findWeeklyNumConnectionsByProfiles(List<String> establishmentsUai, Date firstWeekDay,
	    List<String> usersProfiles);
    
    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal of the specified <code>establishmentsUai</code>.
     * This number of connections only concerns the week beggining with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param firstWeekDay
     * 			he first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsAboveTreshold(List<String> establishmentsUai, Date firstWeekDay, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal, and who has the specified 
     * <code>usersProfiles</code> the specified <code>establishmentsUai</code>.
     * This number of connections only concerns the week beggining with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param usersProfiles
     * 			The users profiles of the statistic to retrieve.
     * @param firstWeekDay
     * 			he first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsAboveTresholdByProfiles(List<String> establishmentsUai, Date firstWeekDay,
	    List<String> usersProfiles, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal of the specified <code>establishmentsUai</code>.
     * This number of connections only concerns the week beggining with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param firstWeekDay
     * 			he first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsBelowTreshold(List<String> establishmentsUai, Date firstWeekDay, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal, and who has one of the specified 
     * <code>usersProfiles</code> the specified <code>establishmentsUai</code>.
     * This number of connections only concerns the week beggining with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param usersProfiles
     * 			The users profiles of the statistic to retrieve.
     * @param firstWeekDay
     * 			he first day of the week associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsBelowTresholdByProfiles(List<String> establishmentsUai, Date firstWeekDay,
	    List<String> usersProfiles, Integer treshold);
    
    // /////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    // /////////////////////////////////////////////////////
    /**
     * Retrieves the average duration time of the connections made on the portal for the specified users profiles
     * <code>usersProfiles</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This average duration time only concerns the month beginning with the day <code>firstMonthDay</code>.
     * 
     * @param establishmentUai
     *            The UAI of the establishment concerned by the statistic.
     * @param firstMonthDay
     *            The first day of the month associated to the statistic.
     * @param usersProfiles
     *            The users profiles concerned by the statistic.
     *            
     * @return 
     * 	the average duration time the connections made on the portal.<br/>
     *         <code>null</code> if no statistic has been retrieved.
     */
    public Float findMonthlyConnectionsAverageDurationByProfiles(String establishmentUai,
            Date firstMonthDay, List<String> usersProfiles);
    
    /**
     * Retrieves the number of connections made on the portal for the specified users profiles
     * <code>usersProfiles</code> and the specified establishments UAI <code>establishmentsUai</code>.<br/>
     * This number of connections only concerns the month beggining with the day <code>firstMonthDay</code>.
     * 
     * @param establishmentsUai
     *            The UAI of the establishments concerned by the statistic.
     * @param firstMonthDay
     *            The first day of the month associated to the statistic.
     * @param usersProfiles
     *            The users profiles concerned by the statistic.
     * @return 
     * 	the number of connections made on the portal.<br/>
     *         <code>null</code> if no statistic has been retrieved.
     */
    public Integer findMonthlyNumConnectionsByProfiles(List<String> establishmentsUai, Date firstMonthDay,
	    List<String> usersProfiles);
    
    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal of the specified <code>establishmentsUai</code>.
     * This number of connections only concerns the month beggining with the day <code>firstMonthDay</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param firstMonthDay
     *            The first day of the month associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findMonthlyNumVisitorsAboveTreshold(List<String> establishmentsUai, Date firstMonthDay, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal, and who has one of the specified 
     * <code>userProfile</code> the specified <code>establishmentsUai</code>.
     * This number of connections only concerns the month beggining with the day <code>firstMonthDay</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param usersProfiles
     * 			The users profiles of the statistic to retrieve.
     * @param firstMonthDay
     *            The first day of the month associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findMonthlyNumVisitorsAboveTresholdByProfiles(List<String> establishmentsUai, Date firstMonthDay,
	    List<String> usersProfiles, Integer treshold);

    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal of the specified <code>establishmentsUai</code>.
     * This number of connections only concerns the month beggining with the day <code>firstMonthDay</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param firstMonthDay
     *            		The first day of the month associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findMonthlyNumVisitorsBelowTreshold(List<String> establishmentsUai, Date firstMonthDay, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal, and who has one of the specified 
     * <code>usersProfiles</code> the specified <code>establishmentsUai</code>.
     * This number of connections only concerns the month beggining with the day <code>firstMonthDay</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param usersProfiles
     * 			The users profiles of the statistic to retrieve.
     * @param firstMonthDay
     *            The first day of the month associated to the statistic.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the portal.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findMonthlyNumVisitorsBelowTresholdByProfiles(List<String> establishmentUai, Date firstMonthDay,
	    List<String> usersProfiles, Integer treshold);

}
