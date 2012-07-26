/**
 * 
 */
package org.esco.indicators.domain.beans.result.statistic;

import org.apache.log4j.Logger;

/**
 * Class representing the statistic about the visits.<br/>
 * This class can provide the follwing informations :
 * <ul>
 * 	<li>The number of visits</li>
 * </ul>
 * 
 * @since  2012/07/26
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
class StatisticOnVisits {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(StatisticOnVisits.class);

    /** Number of visits */
    private Integer numVisits;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link StatisticOnVisits} class.
     * 
     * @param numVisits
     * 			The number of visits.
     */
    public StatisticOnVisits(Integer numVisits) {
	super();
	this.numVisits = numVisits;
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the number of visits.
     * 
     * @return 
     * 	the number of visits.
     */
    public Integer getNumVisits() {
        return numVisits;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
