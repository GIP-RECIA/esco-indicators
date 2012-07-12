/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.util.Date;

import org.esco.indicators.domain.beans.statistic.EstablishmentVisitStatistic;
import org.esco.indicators.domain.beans.statistic.ServiceConnectionStatistic;

/**
 * Interface providing access to statistical data on the wantedServices connections.
 * 
 * @since 2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface ServiceConnectionStatisticService {

    ///////////////////////////////////////////////////////
    // DAILY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves the number of connections made on the service <code>serviceName</code> for the given <code>day</code>.<br/>
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
     * 	the number of connectionsmade on the service.<br/>
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
