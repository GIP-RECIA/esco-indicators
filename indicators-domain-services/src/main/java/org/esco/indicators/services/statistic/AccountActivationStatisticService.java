/**
 * 
 */
package org.esco.indicators.services.statistic;


/**
 * Interface providing access statistical data on account activations.
 * 
 * @since 2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface AccountActivationStatisticService {

    /**
     * Retrieves the number of activated accounts in the specified establishment for the specified <code>week</code>
     * of the specified <code>year</code>.
     *  
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     * 	The number of activated accounts in the establishment in the week.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findWeeklyNumActivatedAccounts(String establishmentUai, Integer week, Integer year);
    
}
