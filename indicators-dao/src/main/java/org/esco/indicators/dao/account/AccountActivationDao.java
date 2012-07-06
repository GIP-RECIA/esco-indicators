/**
 * 
 */
package org.esco.indicators.dao.account;

import java.util.Date;
import java.util.List;

import org.esco.indicators.dao.statistic.StatisticDao;
import org.esco.indicators.domain.beans.account.AccountActivation;

/**
 * Interface providing functions to access statistical data on the account activations. 
 * 
 * @since 2012/05/31
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface AccountActivationDao extends StatisticDao {
    
    /**
     * Retrieves the number of activated accounts int the specified establishment between the start date (<code>startDate</code>) and the end date (<code>endDate</code>).
     * 
     * @param establishmentUai
     * 			UAI of the establishment.
     * @param startDate
     * 			The start date of account activation.
     * @param endDate
     * 			The end date of account activation.
     * 
     * @return
     * 	the number of actived accounts in the establishment, during the specified period.<br/>
     * 	<code>null</code> if no data has been retrieved.
     */
    public Integer findNumActivatedAccountsBetween(String establishmentUai, Date startDate, Date endDate);
    
}
