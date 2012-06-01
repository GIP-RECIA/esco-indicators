/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.util.Date;

import org.esco.indicators.domain.beans.statistic.EstablishmentVisitStatistic;

/**
 * Interface providing functions to access statistical data on the visits / visitors 
 * of an estbalishment.
 * 
 * @since : 01/06/2012 
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface EstablishmentVisitStatisticService {

    ///////////////////////////////////////////////////////
    // DAILY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves a daily statistic associated to the specified <code>establishmentUai</code>
     * and for the given <code>day</code>.
     * 
     * @param day
     * 			The day of the statistic.
     * @param establishmentType
     * 			The type of the establishment concerned by the statistic.
     * @param establishmentUai
     * 			The UAI of the establishment concerned by the statistic.
     * @return
     * 	the daily statistic.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public EstablishmentVisitStatistic findEstablishmentDailyStatistic(Date day, String establishmentType, String establishmentUai);
    
    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    
}
