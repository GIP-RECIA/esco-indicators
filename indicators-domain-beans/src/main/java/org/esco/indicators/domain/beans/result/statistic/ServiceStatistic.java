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
public class ServiceStatistic extends PunctualAccountStatistic {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ServiceStatistic.class);
    
    /** Number of visits */
    private Integer numVisits;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link ServiceStatistic} class.
     * 
     * @param totalAccountNumber
     *            Number of total account.
     * @param activeAccountNumber
     *            Number of active account.
     * @param numVisitorsBelowTreshold
     *            Number of visitors having made a number of visits lesser than a treshold.
     * @param numVisitorsAboveTreshold
     *            Number of visitors having made a number of visits greater than a treshold.
     * @param numVisits
     * 	   Number of visits.
     */
    public ServiceStatistic(Integer totalAccountNumber, Integer activeAccountNumber,
	    Integer numVisitorsBelowTreshold, Integer numVisitorsAboveTreshold, Integer numVisits) {
	super(totalAccountNumber, activeAccountNumber, numVisitorsBelowTreshold, numVisitorsAboveTreshold);
	this.numVisits = numVisits;
    }


    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the number of visits.
     * 
     * @return 
     * 	the number of visits
     */
    public Integer getNumVisits() {
        return numVisits;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
