/**
 * 
 */
package org.esco.indicators.domain.beans.form;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * Class representing a form field containing set of informations to put into a form.<br/>
 * The contained informations are :
 * <ul>
 * 	<li>A label : used for the display into the form</li>
 * 	<li>A value : used for the identification into the form</li>
 * 	<li>A boolean : used for indicating if the value is disabled (or not) into the form</li>
 * </ul>
 * 
 * @since  2012/06/19
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class FormField {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(FormField.class);

    /** Label of the object */
    private String label;
    
    /** Value of the object */
    private String value;
    
    /** Boolean indicating if the field is disabled */
    private boolean disabled;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link FormField} class.
     * 
     * @param label
     * 			The label of the object.
     * @param value
     * 			The value of the object.
     */
    public FormField(String label, String value) {
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


    /**
     * Indicates if the field is disabled.
     * 
     * @return 
     * 	<code>true</code> if the field is disabled.<br/>
     * 	<code>false</code> in other cases.
     */
    public boolean isDisabled() {
        return disabled;
    }
    
    

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Enables the field into the form.
     */
    public void enable() {
	disabled = false;
    }
    
    /**
     * Disables the field into the form.
     */
    public void disable() {
	disabled = true;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
