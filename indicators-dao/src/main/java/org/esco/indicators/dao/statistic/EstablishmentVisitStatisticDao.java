/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.util.Date;

import org.esco.indicators.domain.beans.statistic.EstablishmentVisitStatistic;

/**
 * Interface providing functions to access statistical data on visits and visitors 
 * of an establishment.
 * 
 * @since : 31/05/2012
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface EstablishmentVisitStatisticDao extends StatisticDao {

    ///////////////////////////////////////////////////////
    // DAILY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves a daily statistic associated to the specified <code>establishmentUai</code>,
     * <code>establishmentType</code> and for the given <code>day</code>.
     * Moreover, the statistic has to have the specified type <code>typeStat</code>.
     * 
     * @param day
     * 			The day of the statistic.
     * @param establishmentType
     * 			The type of the establishment concerned by the statistic.
     * @param establishmentUai
     * 			The UAI of the establishment concerned by the statistic.
     * @param typeStat
     * 			The type of the statistic.
     * @return
     * 	the daily statistic.<br/>
     * 	<code>null</code> if no statistic has been retrieved.
     */
    public EstablishmentVisitStatistic findDailyStatistic(Date day, String establishmentType, String establishmentUai, String typeStat);
    
    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
}
