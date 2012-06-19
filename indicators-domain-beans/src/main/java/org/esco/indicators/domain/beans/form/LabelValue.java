/**
 * 
 */
package org.esco.indicators.domain.beans.form;

import org.apache.log4j.Logger;

/**
 * Class representing a pair of information to put into a form.<br/>
 * The pair contains :
 * <ul>
 * 	<li>A label : used for the display into the form</li>
 * 	<li>A value : used for the identification into the form</li>
 * </ul>
 * 
 * @since  2012/06/19
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class LabelValue {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(LabelValue.class);

    /** Label of the object */
    private String label;
    
    /** Value of the object */
    private String value;

    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link LabelValue} class.
     * 
     * @param label
     * 			The label of the object.
     * @param value
     * 			The value of the object.
     */
    public LabelValue(String label, String value) {
	super();
	this.label = label;
	this.value = value;
    }
    
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the label.
     * 
     * @return 
     * 	the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the value.
     * 
     * @return 
     * 	the value
     */
    public String getValue() {
        return value;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
