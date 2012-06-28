/**
 * 
 */
package org.esco.indicators.domain.beans.xml.form;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttachmentRef;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;

import org.apache.log4j.Logger;

/**
 * Class representing an {@link EntryValue}  reference.<br/>
 * 
 * @since  2012/06/21
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EntryValueRef {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EntryValueRef.class);
    
    /** Reference to the entry value to enable in the user view */
    EntryValue referencedEntryValue;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the referenced entry value.
     * 
     * @return 
     * 	the referenced entry value.
     */
    @XmlAttribute(name= "entry-value")
    @XmlIDREF
    public EntryValue getEntryValue() {
        return referencedEntryValue;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Sets the referenced entry value.
     * 
     * @param referencedEntryValue
     * 			The referenced entry value to set.
     */
    public void setEntryValue(EntryValue referencedEntryValue) {
        this.referencedEntryValue = referencedEntryValue;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((referencedEntryValue == null) ? 0 : referencedEntryValue.hashCode());
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
	EntryValueRef other = (EntryValueRef) obj;
	if (referencedEntryValue == null) {
	    if (other.referencedEntryValue != null)
		return false;
	} else if (!referencedEntryValue.equals(other.referencedEntryValue))
	    return false;
	return true;
    }    

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
