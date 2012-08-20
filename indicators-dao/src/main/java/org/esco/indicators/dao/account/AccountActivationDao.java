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
     * Retrieves the number of activated accounts in the specified establishments between the start date (<code>startDate</code>) and the end date (<code>endDate</code>).<br/>
     * 
     * @param establishmentsUai
     * 			UAI of the establishments.
     * @param startDate
     * 			The start date of account activation.
     * @param endDate
     * 			The end date of account activation.
     * 
     * @return
     * 	the number of actived accounts in the establishment, during the specified period.<br/>
     * 	<code>null</code> if no data has been retrieved.
     */
    public Integer findNumActivatedAccounts(List<String> establishmentsUai, Date startDate, Date endDate);
    
    /**
     * Retrieves the number of activated accounts in the specified establishments between the start date (<code>startDate</code>) and the end date (<code>endDate</code>).<br/>
     * These activated accounts have to be associated to one of the specified users profiles.
     * 
     * @param establishmentsUai
     * 			UAI of the establishments.
     * @param usersProfiles
     * 			The profiles of the users.
     * @param startDate
     * 			The start date of account activation.
     * @param endDate
     * 			The end date of account activation.
     * 
     * @return
     * 	the number of actived accounts in the establishments, during the specified period.<br/>
     * 	<code>null</code> if no data has been retrieved.
     */
    public Integer findNumActivatedAccountsForProfiles(List<String> establishmentsUai, List<String> usersProfiles, Date startDate, Date endDate);
    
}
