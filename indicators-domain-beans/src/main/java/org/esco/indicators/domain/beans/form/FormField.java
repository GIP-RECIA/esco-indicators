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
 * 	<li>A path : used for the mapping between user form and form object</li>
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
    
    /** Name of the object */
    private String path;
    
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
    
    /**
     * 
     * Constructor of the {@link FormField} class.
     * 
     * @param label
     * 			The label of the object.
     * @param value
     * 			The value of the object.
     * @param path
     * 			The path of the object.
     */
    public FormField(String label, String value, String name) {
	super();
	this.label = label;
	this.value = value;
	this.path = name;
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
    
    /**
     * Gets the path.
     * 
     * @return 
     * 	the path
     */
    public String getPath() {
        return path;
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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (disabled ? 1231 : 1237);
	result = prime * result + ((label == null) ? 0 : label.hashCode());
	result = prime * result + ((path == null) ? 0 : path.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
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
	FormField other = (FormField) obj;
	if (disabled != other.disabled)
	    return false;
	if (label == null) {
	    if (other.label != null)
		return false;
	} else if (!label.equals(other.label))
	    return false;
	if (path == null) {
	    if (other.path != null)
		return false;
	} else if (!path.equals(other.path))
	    return false;
	if (value == null) {
	    if (other.value != null)
		return false;
	} else if (!value.equals(other.value))
	    return false;
	return true;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
