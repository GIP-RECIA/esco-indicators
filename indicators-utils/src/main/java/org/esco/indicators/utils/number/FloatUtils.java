/**
 * 
 */
package org.esco.indicators.utils.number;

import org.apache.log4j.Logger;

/**
 * Class providing utils functions for manipulating Float.
 * 
 * @since  2012/07/26
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class FloatUtils {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(FloatUtils.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Computes and returns the average calculated from the
     * specified values and weighting.<br/>
     * The first value is associated to the first weighting, while the second value is 
     * associated to the second weighting.
     * 
     * @param firstValue
     * 			The first value.
     * @param firstWeighting
     * 			The weighting associated to the first value.
     * @param secondValue
     * 			The second value.
     * @param secondWeighting
     * 			The weighting associated to the second value.
     * 
     * @return
     * 	the calculated average.<br/>
     * 	the float 0, if an error occured during the computation.
     */
    public static Float calculateAverage(Float firstValue, Integer firstWeighting, Float secondValue, Integer secondWeighting) {
	// Calculates the total weighting
	Integer totalWeighting = firstWeighting + secondWeighting;
	// If the total weighting is equal to zero
	if(totalWeighting.equals(0)) {
	    LOGGER.warn("The average cannot be calculated because the total weighting is equal to zero.");
	    return new Float(0);
	}
	// Else
	Float average;
	try {
	    // Calculates weighted values
	    Float firstWeightedValue = firstValue * firstWeighting;
	    Float secondWeightedValue = secondValue * secondWeighting;
	    // Calculates the average
	    average = (firstWeightedValue + secondWeightedValue) / totalWeighting;
	} catch (NullPointerException e) {
	    LOGGER.warn("The average cannot be calculated because the computation thrown an exception : " + e.getMessage());
	    return new Float(0);
	}
	return average;
    }
    
    /**
     * Computes and returns the percentage calculated from the
     * specified numerator and denominator.<br/>
     * 
     * The computed percentage is a {@link Float} contained in the range : [0,100].<br/>
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
    public static Float calculatePercentage(Integer numerator, Integer denominator) {
	// If the denominator is equal to zero
	if(denominator.equals(0)) {
	    LOGGER.warn("The percentage cannot be calculated because the denominator is equal to zero.");
	    return new Float(0);
	}
	// Else
	Float percent;
	try {
	    Float numeratorFloat = Float.valueOf(numerator);
	    Float denominatorFloat = Float.valueOf(denominator);
	    percent = numeratorFloat / denominatorFloat;
	} catch (NullPointerException e) {
	    LOGGER.warn("The percentage cannot be calculated because the numerator / denominator is undefined.");
	    return new Float(0);
	}
	return (percent * 100);
    }
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
