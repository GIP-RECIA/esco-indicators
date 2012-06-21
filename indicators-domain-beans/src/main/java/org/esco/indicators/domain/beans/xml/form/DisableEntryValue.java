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
public class DisableEntryValue {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(DisableEntryValue.class);
    
    /** Reference to the entry value to enable in the user view */
    EntryValue entryValueToDisable;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the entry value to disable in the user view.
     * 
     * @return 
     * 	the entry value to disable.
     */
    @XmlAttribute(name= "entry-value")
    @XmlIDREF
    public EntryValue getEntryValue() {
        return entryValueToDisable;
    }

    /**
     * Sets the entry value to disable in the user view.
     * 
     * @param entryValueToDisable
     * 			The entry value to disable to set.
     */
    public void setEntryValue(EntryValue entryValueToDisable) {
        this.entryValueToDisable = entryValueToDisable;
    }
    
    

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
