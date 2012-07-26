/**
 * 
 */
package org.esco.indicators.domain.beans.result.statistic;

import org.apache.log4j.Logger;
import org.esco.indicators.utils.number.FloatUtils;


/**
 * Class representing the statistic about the accounts.<br/>
 * This class can provide the follwing informations :
 * <ul>
 * 	<li>The total number of accounts</li>
 * 	<li>The total number of activated accounts</li>
 * 	<li>The percentage of activated accounts regarding to the total number of accounts</li>
 * </ul>
 * 
 * @since  2012/07/26
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
class StatisticOnAccounts {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(StatisticOnAccounts.class);

    /** Total number of accounts */
    private Integer totalAccountNumber;

    /** Number of active accounts */
    private Integer activeAccountNumber;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link StatisticOnAccounts} class.
     * 
     * @param totalAccountNumber
     *            Number of total account.
     * @param activeAccountNumber
     *            Number of active account.
     */
    public StatisticOnAccounts(Integer totalAccountNumber, Integer activeAccountNumber) {
	super();
	this.totalAccountNumber = totalAccountNumber;
	this.activeAccountNumber = activeAccountNumber;
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the number of total account.
     * 
     * @return the number of total account.
     */
    public Integer getTotalAccountNumber() {
	return totalAccountNumber;
    }

    /**
     * Gets the number of active account.
     * 
     * @return the number of active account.
     */
    public Integer getActiveAccountNumber() {
	return activeAccountNumber;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Gets the percentage of active account regarding to the total number of account.
     * 
     * @return
     * 	 the percentage of active account regarding to the total number of account.
     */
    public Float getPercentageActiveAccount() {
	return FloatUtils.calculatePercentage(activeAccountNumber, totalAccountNumber);
    }
    
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
