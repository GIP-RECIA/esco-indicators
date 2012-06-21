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
 * Class representing an entry value to enable on the user view.<br/>
 * This class provides reference to the {@link EntryValue} object.
 * 
 * @since  2012/06/21
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EnableEntryValue {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EnableEntryValue.class);
    
    /** Reference to the entry value to enable in the user view */
    EntryValue entryValueToEnable;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the entry value to enable in the user view.
     * 
     * @return 
     * 	the entry value to enable in the user view.
     */
    @XmlAttribute(name= "entry-value")
    @XmlIDREF
    public EntryValue getEntryValue() {
        return entryValueToEnable;
    }

    /**
     * Sets the entry value to enable in the user view.
     * 
     * @param entryValueToEnable 
     * 			The entry value to enable in the user view.
     */
    public void setEntryValue(EntryValue entryValueToEnable) {
        this.entryValueToEnable = entryValueToEnable;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
