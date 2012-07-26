/**
 * 
 */
package org.esco.indicators.utils.classes;


import org.apache.log4j.Logger;

/**
 * Class representing a pair of <code>Integer</code> values.
 * 
 * @since  2012/07/10
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class IntegerPair extends Pair<Integer,Integer> {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(IntegerPair.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link IntegerPair} class.
     * 
     * @param first
     * 			The first value of the pair.
     * @param second
     * 			The second value of the pair.
     */
    public IntegerPair(Integer first, Integer second) {
	super(first, second);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
