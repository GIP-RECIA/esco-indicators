/**
 * 
 */
package org.esco.indicators.domain.beans.result.statistic;

import org.apache.log4j.Logger;
import org.esco.indicators.utils.number.FloatUtils;

/**
 * Class representing the statistic about the visitors.<br/>
 * This class can provide the follwing informations :
 * <ul>
 * 	<li>The number of visitors that made less visits than a defined treshold</li>
 * 	<li>The number of visitors that made more visits than a defined treshold</li>
 * 	<li>The percentage of visitors that made less visits than a defined treshold (regarding to the total number of accounts)</li>
 * 	<li>The percentage of visitors that made more visits than a defined treshold (regarding to the total number of accounts)</li>
 * </ul>
 * 
 * @since  2012/07/26
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
class StatisticOnVisitors {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(StatisticOnVisitors.class);

    /** Number of visitors that realize a number of visits lesser than a treshold */
    private Integer numVisitorsBelowTreshold;

    /** Number of visitors that realize a number of visits greater than a treshold */
    private Integer numVisitorsAboveTreshold;
    
    /** Total number of accounts */
    private Integer totalAccountNumber;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link StatisticOnVisitors} class.
     * 
     * @param numVisitorsBelowTreshold
     *            Number of visitors having made a number of visits lesser than a treshold.
     * @param numVisitorsAboveTreshold
     *            Number of visitors having made a number of visits greater than a treshold.
     * @param totalAccountNumber
     *            Total number of accounts.
     */
    public StatisticOnVisitors(Integer numVisitorsBelowTreshold, Integer numVisitorsAboveTreshold,
	    Integer totalAccountNumber) {
	super();
	this.numVisitorsBelowTreshold = numVisitorsBelowTreshold;
	this.numVisitorsAboveTreshold = numVisitorsAboveTreshold;
	this.totalAccountNumber = totalAccountNumber;
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the number of visitors having made a number of visits lesser than a treshold.
     * 
     * @return the number of visitors having made a number of visits lesser than a treshold.
     */
    public Integer getNumVisitorsBelowTreshold() {
	return numVisitorsBelowTreshold;
    }

    /**
     * Gets the number of visitors having made a number of visits greater than a treshold.
     * 
     * @return the number of visitors having made a number of visits greater than a treshold.
     */
    public Integer getNumVisitorsAboveTreshold() {
	return numVisitorsAboveTreshold;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Gets the percentage of visitors having made a number of visits greater than a treshold.
     * 
     * @return the percentage of visitors having made a number of visits greater than a treshold.
     */
    public Float getPercentageNumVisitorsAboveTreshold() {
	return FloatUtils.calculatePercentage(numVisitorsAboveTreshold, totalAccountNumber);
    }
    
    /**
     * Gets the percentage  of visitors having made a number of visits lesser than a treshold.
     * 
     * @return the percentage of visitors having made a number of visits lesser than a treshold.
     */
    public Float getPercentageNumVisitorsBelowTreshold() {
	return FloatUtils.calculatePercentage(numVisitorsBelowTreshold, totalAccountNumber);
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
