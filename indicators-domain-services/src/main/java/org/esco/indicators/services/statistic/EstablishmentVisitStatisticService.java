/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;

import org.esco.indicators.domain.beans.statistic.EstablishmentVisitStatistic;

/**
 * Interface providing functions to access statistical data on the visits / visitors 
 * of an estbalishment.
 * 
 * @since 2012/06/01
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
    /**
     * Retrieves the number of visits made  on the <code>establishmentUai</code> portal.<br/>
     * This number of visits only concerns the week having the number <code>week</code> in the year <code>year</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @return
     * 	the number of visits made on the establishment portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findEstablishmentWeeklyNumVisits(String establishmentUai, Integer week, Integer year);
    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves the number of visits made  on the <code>establishmentUai</code> portal.<br/>
     * This number of visits only concerns the month having the number <code>month</code> in the year <code>year</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param month
     * 			The month number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @return
     * 	the number of visits made on the establishment portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findEstablishmentMonthlyNumVisits(String establishmentUai, Integer month, Integer year);
    
}
