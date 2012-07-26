/**
 * 
 */
package org.esco.indicators.domain.beans.result.statistic;

import org.apache.log4j.Logger;

/**
 * Class representing the statistics data present into the result page concerning the services.<br/>
 * This result page can display punctual statistics data, or periodic statistics data.<br/>
 * Indeed, this page displays statistics for a given period, or for multiple periods.
 * 
 * @since  2012/07/26
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ServiceStatistic {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ServiceStatistic.class);
    
    /** Statistics concerning the visitors */
    private StatisticOnVisitors statisticOnVisitors;
    
    /** Number of visits */
    private StatisticOnVisits statisticOnVisits;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the  {@link ServiceStatistic} class.
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
    public ServiceStatistic(Integer totalAccountNumber, Integer activeAccountNumber, Integer numVisitorsBelowTreshold, Integer numVisitorsAboveTreshold, Integer numVisits) {
	super();
	this.statisticOnVisitors = new StatisticOnVisitors(numVisitorsBelowTreshold, numVisitorsAboveTreshold, totalAccountNumber);
	this.statisticOnVisits = new StatisticOnVisits(numVisits);
    }


    //--------------------------------------------------------------------------- GETTERS / SETTERS
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
     * 	the number of visits
     */
    public Integer getNumVisits() {
        return statisticOnVisits.getNumVisits();
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

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
