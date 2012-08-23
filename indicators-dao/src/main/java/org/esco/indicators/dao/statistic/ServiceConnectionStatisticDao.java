/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.util.Date;
import java.util.List;

/**
 * Interface providing functions to access statistical data on the services connections.
 * 
 * @since 2012/05/31
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface ServiceConnectionStatisticDao extends StatisticDao {

    ///////////////////////////////////////////////////////
    // DAILY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves the number of connections to the service <code>serviceName</code> for the given <code>day</code>.<br/>
     * The statistic has to be associated to the specified <code>establishmentUai</code> and the user profile <code>userProfile</code>
     * 
     * @param day
     * 			The day of the statistic.
     * @param establishmentUai
     * 			The UAI of the establishment associated to the the statistic.
     * @param servicesNames
     * 			The names of the services concerned by the statistic.
     * @param userProfile
     * 			The profile of the user associated to the statistic.
     * @return
     * 	the number of connections found through the statistics.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findDailyNumConnectionsByProfile(Date day, String establishmentUai, List<String> servicesNames, String userProfile);
    
    ///////////////////////////////////////////////////////
    // WEEKLY / MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    
    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on one of the services, and who has the specified 
     * <code>userProfile</code> in the specified <code>establishmentsUai</code>.<br/>
     * This number of connections only concerns the period beginning with the day <code>startDay</code> and ending with the day <code>endDay</code>.<br/>
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param startDay
     * 			The start day of the period associated to the statistic.
     * @param endDay
     * 			The end day of the period associated to the statistic.
     * @param servicesNames
     * 			The names of the services of the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on one of the services during the specified period.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findNumVisitorsAboveTreshold(List<String> establishmentsUai, Date startDay, Date endDay, List<String> servicesNames, String userProfile, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the service, and who has the specified 
     * <code>userProfile</code> in the specified <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the period beginning with the day <code>startDay</code> and ending with the day <code>endDay</code>.<br/>
     * 
     * @param establishmentsUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param startDay
     * 			The start day of the period associated to the statistic.
     * @param endDay
     * 			The end day of the period associated to the statistic.
     * @param servicesNames
     * 			The names of the services of the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the service during the specified period.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findNumVisitorsBelowTreshold(List<String> establishmentsUai, Date startDay, Date endDay, List<String> servicesNames, String userProfile, Integer treshold);
    
    /**
     * Retrieves the number of visits made on the service for the specified <code>userProfile</code> in the specified <code>establishmentUai</code>.<br/>
     * This number of visits only concerns the period beginning with the day <code>startDay</code> and ending with the day <code>endDay</code>.<br/>
     * 
     * @param establishmentsUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param startDay
     * 			The start day of the period associated to the statistic.
     * @param endDay
     * 			The end day of the period associated to the statistic.
     * @param servicesNames
     * 			The names of the services of the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * 
     * @return
     * 	the number of visits made on the service during the specified period.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findNumVisits(List<String> establishmentsUai, Date startDay, Date endDay, List<String> servicesNames, String userProfile);

}
