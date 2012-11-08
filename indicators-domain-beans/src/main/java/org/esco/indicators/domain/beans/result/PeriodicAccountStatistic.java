/**
 * 
 */
package org.esco.indicators.domain.beans.result;

import org.apache.log4j.Logger;

/** 
 * Class representing the statistics data present into the result page concerning the accounts.<br/>
 * <ul>
 * 	<li>The total number of accounts</li>
 * 	<li>The total number of activated accounts</li>
 * 	<li>The total number of vistits made by the visitors</li>
 * 	<li>The percentage of activated accounts regarding to the total number of accounts</li>
 * 	<li>The number of visitors that made less visits than a defined treshold</li>
 * 	<li>The number of visitors that made more visits than a defined treshold</li>
 * 	<li>The percentage of visitors that made less visits than a defined treshold (regarding to the total number of accounts)</li>
 * 	<li>The percentage of visitors that made more visits than a defined treshold (regarding to the total number of accounts)</li>
 * </ul>
 * 
 * @since  2012/07/26
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class PeriodicAccountStatistic {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PeriodicAccountStatistic.class);

    /** Statistic concerning the accounts */
    private StatisticOnAccounts statisticOnAccounts;
    
    /** Statistics concerning the visitors */
    private StatisticOnVisitors statisticOnVisitors;
    
    /** Statistics concerning the visits */
    private StatisticOnVisits statisticOnVisits;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link PeriodicAccountStatistic} class.
     * 
     * @param totalAccountNumber
     *            Total number of accounts.
     * @param activeAccountNumber
     *           Number of active accounts.
     * @param numVisitorsBelowTreshold
     *            Number of visitors having made a number of visits lesser than a treshold.
     * @param numVisitorsAboveTreshold
     *            Number of visitors having made a number of visits greater than a treshold.
     *  @param numVisits
     *  	  Number of visits.
     */
    public PeriodicAccountStatistic(Integer totalAccountNumber, Integer activeAccountNumber, Integer numVisitorsBelowTreshold, Integer numVisitorsAboveTreshold, Integer numVisits) {
	super();
	this.statisticOnAccounts = new StatisticOnAccounts(totalAccountNumber, activeAccountNumber);
	this.statisticOnVisitors = new StatisticOnVisitors(numVisitorsBelowTreshold, numVisitorsAboveTreshold, totalAccountNumber);
	this.statisticOnVisits = new StatisticOnVisits(numVisits);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS    
    
    /**
     * Gets the average duration time of the visits.
     * 
     * @return average duration time of the visits.
     */
    public Float getAverageDurationTime() {
        return statisticOnVisits.getAverageDurationTime();
    }
    
    /**
     * Sets the average duration time of the visits.
     * 
     * @param averageDurationTime
     * 			The average duration time of the visits to set.
     */
    public void setAverageDurationTime(Float averageDurationTime) {
        statisticOnVisits.setAverageDurationTime(averageDurationTime);
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
     * Gets the number of visitors having made a number of visits lesser than a treshold.
     * 
     * @return the number of visitors having made a number of visits lesser than a treshold.
     */
    public Integer getNumVisitorsBelowTreshold() {
	return statisticOnVisitors.getNumVisitorsBelowTreshold();
    }

    /**
     * Gets the number of visitors having made a number of visits greater than a treshold.
     * 
     * @return the number of visitors having made a number of visits greater than a treshold.
     */
    public Integer getNumVisitorsAboveTreshold() {
	return statisticOnVisitors.getNumVisitorsAboveTreshold();
    }

    /**
     * Gets the number of visits.
     * 
     * @return 
     * 	the number of visits.
     */
    public Integer getNumVisits() {
        return statisticOnVisits.getNumVisits();
    }

    /**
     * Gets the percentage of active accounts.
     * 
     * @return
     * 	the percentage of active accounts.
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
    
    /**
     * Gets the number of total account.
     * 
     * @return the number of total account.
     */
    public Integer getTotalAccountNumber() {
	return statisticOnAccounts.getTotalAccountNumber();
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PeriodicAccountStatistic [getActiveAccountNumber()=" + getActiveAccountNumber()
		+ ", getNumVisitorsBelowTreshold()=" + getNumVisitorsBelowTreshold()
		+ ", getNumVisitorsAboveTreshold()=" + getNumVisitorsAboveTreshold() + ", getNumVisits()="
		+ getNumVisits() + ", getPercentageActiveAccount()=" + getPercentageActiveAccount()
		+ ", getPercentageNumVisitorsAboveTreshold()=" + getPercentageNumVisitorsAboveTreshold()
		+ ", getPercentageNumVisitorsBelowTreshold()=" + getPercentageNumVisitorsBelowTreshold()
		+ ", getTotalAccountNumber()=" + getTotalAccountNumber() + "]";
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
