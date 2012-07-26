/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.util.Date;

/**
 * Interface providing functions to access statistical data on the wantedServices connections.
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
     * @param serviceName
     * 			The name of the service concerned by the statistic.
     * @param userProfile
     * 			The profile of the user associated to the statistic.
     * @return
     * 	the number of connections found through the statistics.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findDailyNumConnectionsByProfile(Date day, String establishmentUai, String serviceName, String userProfile);
    
    ///////////////////////////////////////////////////////
    // WEEKLY / MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    
    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the service, and who has the specified 
     * <code>userProfile</code> in the specified <code>establishmentUai</code>.
     * This number of connections only concerns the period beginning with the day <code>startDay</code> and ending with the day <code>endDay</code>.<br/>
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param startDay
     * 			The start day of the period associated to the statistic.
     * @param endDay
     * 			The end day of the period associated to the statistic.
     * @param serviceName
     * 			The name of the service of the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * @param year
     * 			The year of the statistic to retrieve.
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the service during the specified period.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public Integer findNumVisitorsAboveTreshold(String establishmentUai, Date startDay, Date endDay, String serviceName, String userProfile, Integer treshold);
    
}
