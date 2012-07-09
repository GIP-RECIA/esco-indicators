/**
 * 
 */
package org.esco.indicators.domain.beans.result;

import org.apache.log4j.Logger;

/**
 * Class representing the statistics data present into a result page.
 * 
 * @since 2012/07/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class StatisticData {
    // ---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(StatisticData.class);

    /** Total account number */
    private Integer totalAccountNumber;

    /** Active account number */
    private Integer activeAccountNumber;

    /** Number of visitors that realize a number of visits lesser than a treshold */
    private Integer numVisitorsBelowTreshold;

    /** Number of visitors that realize a number of visits greater than a treshold */
    private Integer numVisitorsAboveTreshold;

    // -------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link StatisticData} class.
     * 
     * @param totalAccountNumber
     *            Number of total account.
     * @param activeAccountNumber
     *            Number of active account.
     * @param numVisitorsBelowTreshold
     *            Number of visitors having made a number of visits lesser than a treshold.
     * @param numVisitorsAboveTreshold
     *            Number of visitors having made a number of visits greater than a treshold.
     */
    public StatisticData(Integer totalAccountNumber, Integer activeAccountNumber,
	    Integer numVisitorsBelowTreshold, Integer numVisitorsAboveTreshold) {
	super();
	this.totalAccountNumber = totalAccountNumber;
	this.activeAccountNumber = activeAccountNumber;
	this.numVisitorsBelowTreshold = numVisitorsBelowTreshold;
	this.numVisitorsAboveTreshold = numVisitorsAboveTreshold;
    }

    // --------------------------------------------------------------------------- GETTERS / SETTERS
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

    /**
     * Gets the percentage of active account regarding to the total number of account.
     * 
     * @return
     * 	 the percentage of active account regarding to the total number of account.
     */
    public Float getPercentageActiveAccount() {
	return calculatePercentage(activeAccountNumber, totalAccountNumber);
    }
    
    /**
     * Gets the percentage of visitors having made a number of visits greater than a treshold.
     * 
     * @return the percentage of visitors having made a number of visits greater than a treshold.
     */
    public Float getPercentageNumVisitorsAboveTreshold() {
	return calculatePercentage(numVisitorsAboveTreshold, totalAccountNumber);
    }
    
    /**
     * Gets the percentage  of visitors having made a number of visits lesser than a treshold.
     * 
     * @return the percentage of visitors having made a number of visits lesser than a treshold.
     */
    public Float getPercentageNumVisitorsBelowTreshold() {
	return calculatePercentage(numVisitorsBelowTreshold, totalAccountNumber);
    }
    
    // ------------------------------------------------------------------------------ PUBLIC METHODS

    // ----------------------------------------------------------------------------- PRIVATE METHODS
    
    // ------------------------------------------------------------------------------ STATIC METHODS
    /**
     * Computes and returns the percentage calculated from the
     * specified numerator and denominator.
     * 
     * @param numerator
     * 			The numerator.
     * @param denominator
     * 			The denominator.
     * 
     * @return
     * 	the percentage.<br/>
     * 	the float 0, if an error occured during the computation.
     */
    private static Float calculatePercentage(Integer numerator, Integer denominator) {
	Float percent;
	try {
	    Float numeratorFloat = Float.valueOf(numerator);
	    Float denominatorFloat = Float.valueOf(denominator);
	    percent = numeratorFloat / denominatorFloat;
	} catch (NullPointerException e) {
	    LOGGER.warn("The percentage cannot be calculated because the numerator / denominator is undefined.");
	    return new Float(0);
	}
	return percent;
    }
}
