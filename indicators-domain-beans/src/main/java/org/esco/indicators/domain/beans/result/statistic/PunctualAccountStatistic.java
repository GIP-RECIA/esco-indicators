/**
 * 
 */
package org.esco.indicators.domain.beans.result.statistic;

import org.apache.log4j.Logger;
import org.esco.indicators.utils.number.FloatUtils;

/**
 * Class representing the statistics data present into the result page concerning the accounts.<br/>
 * This result page only displays punctual statistics data, i.e. it displays statistics for a given period, not for multiple periods.<br/>
 * 
 * The statistics that can be provided by this class are :
 * <ul>
 * 	<li>The total number of accounts</li>
 * 	<li>The total number of activated accounts</li>
 * 	<li>The percentage of activated accounts regarding to the total number of accounts</li>
 * 	<li>The number of visitors that made less visits than a defined treshold</li>
 * 	<li>The number of visitors that made more visits than a defined treshold</li>
 * 	<li>The percentage of visitors that made less visits than a defined treshold (regarding to the total number of accounts)</li>
 * 	<li>The percentage of visitors that made more visits than a defined treshold (regarding to the total number of accounts)</li>
 * </ul>
 * @since 2012/07/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class PunctualAccountStatistic {
    // ---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PunctualAccountStatistic.class);

    /** Statistic concerning the accounts */
    private StatisticOnAccounts statisticOnAccounts;
    
    /** Statistics concerning the visitors */
    private StatisticOnVisitors statisticOnVisitors;

    // -------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link PunctualAccountStatistic} class.
     * 
     * @param totalAccountNumber
     *            Total number of accounts.
     * @param activeAccountNumber
     *           Number of active accounts.
     * @param numVisitorsBelowTreshold
     *            Number of visitors having made a number of visits lesser than a treshold.
     * @param numVisitorsAboveTreshold
     *            Number of visitors having made a number of visits greater than a treshold.
     */
    public PunctualAccountStatistic(Integer totalAccountNumber, Integer activeAccountNumber, Integer numVisitorsBelowTreshold, Integer numVisitorsAboveTreshold) {
	super();
	this.statisticOnAccounts = new StatisticOnAccounts(totalAccountNumber, activeAccountNumber);
	this.statisticOnVisitors = new StatisticOnVisitors(numVisitorsBelowTreshold, numVisitorsAboveTreshold, totalAccountNumber);
    }

    // --------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the number of total account.
     * 
     * @return the number of total account.
     */
    public Integer getTotalAccountNumber() {
	return statisticOnAccounts.getTotalAccountNumber();
    }

    /**
     * Gets the number of active account.
     * 
     * @return the number of active account.
     */
    public Integer getActiveAccountNumber() {
	return statisticOnAccounts.getActiveAccountNumber();
    }
    
    /**
     * Gets the percentage of active account regarding to the total number of account.
     * 
     * @return
     * 	 the percentage of active account regarding to the total number of account.
     */
    public Float getPercentageActiveAccount() {
	return statisticOnAccounts.getPercentageActiveAccount();
    }
    
    /**
     * Gets the percentage of visitors having made a number of visits greater than a treshold.
     * 
     * @return the percentage of visitors having made a number of visits greater than a treshold.
     */
    public Float getPercentageNumVisitorsAboveTreshold() {
	return statisticOnVisitors.getPercentageNumVisitorsAboveTreshold();
    }
    
    /**
     * Gets the percentage  of visitors having made a number of visits lesser than a treshold.
     * 
     * @return the percentage of visitors having made a number of visits lesser than a treshold.
     */
    public Float getPercentageNumVisitorsBelowTreshold() {
	return statisticOnVisitors.getPercentageNumVisitorsBelowTreshold();
    }

    
    // ------------------------------------------------------------------------------ PUBLIC METHODS

    // ----------------------------------------------------------------------------- PRIVATE METHODS
    
    // ------------------------------------------------------------------------------ STATIC METHODS

}
