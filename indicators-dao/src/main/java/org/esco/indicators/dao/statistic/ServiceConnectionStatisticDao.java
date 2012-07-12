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
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
}
