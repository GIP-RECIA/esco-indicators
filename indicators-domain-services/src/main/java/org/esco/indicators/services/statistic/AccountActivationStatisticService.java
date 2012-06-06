/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;

/**
 * Interface providing access statistical data on account activations.
 * 
 * @since 2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface AccountActivationStatisticService {

    /**
     * Retrieves the number of activated accounts in the period beginning with the specified
     * <code>startDate</code> and ending with the specified <code>endDate</code>.
     * 
     * @param startDate
     * 			The start date of the period.
     * @param endDate
     * 			The end date of the period.
     * @return
     * 	The number of activated accounts on the specified period.<br/>
     * 	The number 0 if no data has been retrieved in this period.
     */
    public Integer findNumActivatedAccountsBetween(Date startDate, Date endDate);

}
