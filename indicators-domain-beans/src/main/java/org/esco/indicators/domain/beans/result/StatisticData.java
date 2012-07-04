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
    private String totalAccountNumber;

    /** Active account number */
    private String activeAccountNumber;

    /** Number of visitors that realize a number of visits lesser than a treshold */
    private String numVisitorsBelowTreshold;

    /** Number of visitors that realize a number of visits greater than a treshold */
    private String numVisitorsAboveTreshold;

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
    public StatisticData(String totalAccountNumber, String activeAccountNumber,
	    String numVisitorsBelowTreshold, String numVisitorsAboveTreshold) {
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
    public String getTotalAccountNumber() {
	return totalAccountNumber;
    }

    /**
     * Gets the number of active account.
     * 
     * @return the number of active account.
     */
    public String getActiveAccountNumber() {
	return activeAccountNumber;
    }

    /**
     * Gets the number of visitors having made a number of visits lesser than a treshold.
     * 
     * @return the number of visitors having made a number of visits lesser than a treshold.
     */
    public String getNumVisitorsBelowTreshold() {
	return numVisitorsBelowTreshold;
    }

    /**
     * Gets the number of visitors having made a number of visits greater than a treshold.
     * 
     * @return the number of visitors having made a number of visits greater than a treshold.
     */
    public String getNumVisitorsAboveTreshold() {
	return numVisitorsAboveTreshold;
    }

    /**
     * Gets the percentage of active account regarding to the total number of account.
     * 
     * @return
     * 	 the percentage of active account regarding to the total number of account.
     */
    public String getPercentageActiveAccount() {
	return calculatePercentage(activeAccountNumber, totalAccountNumber);
    }
    
    /**
     * Gets the percentage of visitors having made a number of visits greater than a treshold.
     * 
     * @return the percentage of visitors having made a number of visits greater than a treshold.
     */
    public String getPercentageNumVisitorsAboveTreshold() {
	return calculatePercentage(numVisitorsAboveTreshold, totalAccountNumber);
    }
    
    /**
     * Gets the percentage  of visitors having made a number of visits lesser than a treshold.
     * 
     * @return the percentage of visitors having made a number of visits lesser than a treshold.
     */
    public String getPercentageNumVisitorsBelowTreshold() {
	return calculatePercentage(numVisitorsBelowTreshold, totalAccountNumber);
    }
    
    // ------------------------------------------------------------------------------ PUBLIC METHODS

    // ----------------------------------------------------------------------------- PRIVATE METHODS
    
    // ------------------------------------------------------------------------------ STATIC METHODS
    /**
     * Computes and returns the percentage (<code>String</code>) calculated from the
     * specified numerator  (<code>String</code>) and denominator  (<code>String</code>).
     * 
     * @param numerator
     * 			The string representing the numerator.
     * @param denominator
     * 			The string representing the denominator.
     * 
     * @return
     * 	the string representing the percentage.<br/>
     * 	the string "0", if an error occured during the computation.
     */
    private static String calculatePercentage(String numerator, String denominator) {
	Float percent;
	try {
	    Float numeratorFloat = Float.valueOf(numerator);
	    Float denominatorFloat = Float.valueOf(denominator);
	    percent = numeratorFloat / denominatorFloat;
	} catch (NullPointerException e) {
	    LOGGER.warn("The percentage cannot be calculated because the numerator / denominator is undefined.");
	    return "0";
	}
	return percent.toString();
    }
}
