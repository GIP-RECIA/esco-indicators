/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.account.AccountActivationDao;
import org.esco.indicators.domain.beans.account.AccountActivation;

/**
 * Implementation of the {@link AccountActivationStatisticService} interface.
 * 
 * @since  2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class AccountActivationStatisticServiceImpl implements AccountActivationStatisticService {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(AccountActivationStatisticServiceImpl.class);
    
    /** DAO providing access to account activations data */
    private AccountActivationDao accountActivationDao;


    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link AccountActivationStatisticServiceImpl} class. 
     */
    public AccountActivationStatisticServiceImpl() {
	super();
    }


    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the DAO providing access to account activations data.
     * 
     * @return
     * 	 the DAO providing access to account activations data.
     */
    public AccountActivationDao getAccountActivationDao() {
        return accountActivationDao;
    }


    /**
     * Sets the DAO providing access to account activations data.
     * 
     * @param accountActivationDao 
     * 				The DAO, providing access to account activations data, to set.
     */
    public void setAccountActivationDao(AccountActivationDao accountActivationDao) {
        this.accountActivationDao = accountActivationDao;
    }


    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.AccountActivationStatisticService#findNumActivatedAccountsBetween(java.sql.Date, java.sql.Date)
     */
    @Override
    public Integer findNumActivatedAccountsBetween(Date startDate, Date endDate) {
	// Gets the activated account of this period
	List<AccountActivation> accountActivations = accountActivationDao.findActivatedAccountsBetween(startDate, endDate);
	
	return accountActivations.size();
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
