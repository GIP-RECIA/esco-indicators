/**
 * 
 */
package org.esco.indicators.utils.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.esco.indicators.utils.constants.SystemConstants;

/**
 * Class used to store the parameters of a query and their values.
 * 
 * @since  
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class Parameters extends HashMap<String, Object> {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(Parameters.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default construcotr of the {@link Parameters} class.
     */
    public Parameters() {
	super();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuffer messageBuffer = new StringBuffer("The parameters and values are : " + SystemConstants.NEW_LINE);
	for (String parameter : this.keySet()) {
	    Object value = this.get(parameter);
	    messageBuffer.append("Parameter : " + parameter + " -> Value : " + value + " " +SystemConstants.NEW_LINE);
	}
	return messageBuffer.toString();
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
