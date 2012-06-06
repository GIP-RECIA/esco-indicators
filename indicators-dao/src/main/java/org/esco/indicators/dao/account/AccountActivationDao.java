/**
 * 
 */
package org.esco.indicators.dao.account;

import java.sql.Date;
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
     * Retrieves the activated accounts between the start date (<code>startDate</code>) and the end date (<code>endDate</code>).
     * 
     * @param startDate
     * 			The start date of account activation.
     * @param endDate
     * 			The end date of account activation.
     * 
     * @return
     * 	the list of actived accounts, during the specified period, retrieved.<br/>
     * 	an empty list if no data has been retrieved.
     */
    public List<AccountActivation> findActivatedAccountsBetween(Date startDate, Date endDate);
    
}
