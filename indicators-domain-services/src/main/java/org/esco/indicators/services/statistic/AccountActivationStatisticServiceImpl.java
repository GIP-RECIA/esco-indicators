/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.account.AccountActivationDao;
import org.esco.indicators.domain.beans.account.AccountActivation;
import org.esco.indicators.utils.date.DateUtils;

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
     * @see org.esco.indicators.services.statistic.AccountActivationStatisticService#findWeeklyNumActivatedAccounts(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumActivatedAccounts(String establishmentUai, Integer week, Integer year) {
	// Get the SQL dates corresponding to the first (and last) days of the week for the year
	Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
	Date lastWeekDay = DateUtils.addDays(firstWeekDay, 6);
	
	// Get the number of activated accounts for this week
	Integer numActivatedAccounts = accountActivationDao.findNumActivatedAccountsBetween(establishmentUai, firstWeekDay, lastWeekDay);
	numActivatedAccounts = (numActivatedAccounts == null ? 0 : numActivatedAccounts);
	
	return numActivatedAccounts;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
