/**
 * 
 */
package org.esco.indicators.domain.beans.util;

import org.apache.log4j.Logger;

/**
 * Class representing a generic pair of values.
 * 
 * @param <A> Represents the type of the first value of the pair.
 * @param <B> Represents the type of the second value of the pair.
 * 
 * @since  2012/07/10
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class Pair<A, B> {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(Pair.class);

    /** First value */
    private A first;
    
    /** Second value */
    private B second;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link Pair} class.
     * 
     * @param first
     * 			The first value of the pair.
     * @param second
     * 			The second value of the pair.
     */
    public Pair(A first, B second) {
	super();
	this.first = first;
	this.second = second;
    }

    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the first value of the pair.
     * 
     * @return 
     * 	the first value of the pair.
     */
    public A getFirst() {
        return first;
    }

    /**
     * Gets the second value of the pair.
     * 
     * @return 
     * 	the second value of the pair.
     */
    public B getSecond() {
        return second;
    }



    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((first == null) ? 0 : first.hashCode());
	result = prime * result + ((second == null) ? 0 : second.hashCode());
	return result;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Pair other = (Pair) obj;
	if (first == null) {
	    if (other.first != null)
		return false;
	} else if (!first.equals(other.first))
	    return false;
	if (second == null) {
	    if (other.second != null)
		return false;
	} else if (!second.equals(other.second))
	    return false;
	return true;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
