/**
 * 
 */
package org.esco.indicators.domain.beans.result;

import org.apache.log4j.Logger;

/**
 * Class representing the statistics data present into the result page concerning the services.
 * 
 * @since  2012/07/26
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ServiceStatisticData extends BasicStatisticData {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ServiceStatisticData.class);
    
    /** Number of visits */
    private Integer numVisits;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link ServiceStatisticData} class.
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
    public ServiceStatisticData(Integer totalAccountNumber, Integer activeAccountNumber,
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
