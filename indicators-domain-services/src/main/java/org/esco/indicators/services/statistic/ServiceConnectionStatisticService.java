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
    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the <code>service</code>, and who has the specified 
     * <code>userProfile</code> in the specified <code>establishmentUai</code>.<br/>
     * This number of visitors only concerns the week having the number <code>week</code> in the year <code>year</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param service
     * 			The service associated to the statistic to retireve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the service.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsAboveTreshold(String establishmentUai, String service, String userProfile, Integer treshold, Integer week, Integer year);

    /**
     * Retrieves the number of visits made on the <code>service</code> for the specified <code>userProfile</code> in the specified <code>establishmentUai</code>.<br/>
     * This number of visits only concerns the week having the number <code>week</code> in the year <code>year</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param service
     * 			The service associated to the statistic to retireve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	the number of visits made on the service.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisits(String establishmentUai, String service, String userProfile, Integer week, Integer year);
    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
}
