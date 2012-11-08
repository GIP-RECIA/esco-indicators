/**
 * 
 */
package org.esco.indicators.domain.beans.result;

import org.apache.log4j.Logger;

/**
 * Class representing the statistic about the visits.<br/>
 * This class can provide the follwing informations :
 * <ul>
 * 	<li>The number of visits</li>
 * 	<li>The average duration time of the visits</li>
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
    
    /** Average duration time of the visits */
    private Float averageDurationTime;

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
    
    /**
     * Constructor of the {@link StatisticOnVisits} class.
     * 
     * @param numVisits
     * 			The number of visits.
     * @param averageDurationTime
     * 			The average duration time of the visits.
     */
    public StatisticOnVisits(Integer numVisits, Float averageDurationTime) {
	super();
	this.numVisits = numVisits;
	this.averageDurationTime = averageDurationTime;
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

    /**
     * Gets the average duration time of the visits.
     * 
     * @return 
     * 	the average duration time of the visits.
     */
    public Float getAverageDurationTime() {
        return averageDurationTime;
    }

    /**
     * Sets the average duration time of the visits.
     * 
     * @param averageDurationTime
     * 			The average duration time of the visits to set.
     */
    public void setAverageDurationTime(Float averageDurationTime) {
        this.averageDurationTime = averageDurationTime;
    }
    
    
    

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
