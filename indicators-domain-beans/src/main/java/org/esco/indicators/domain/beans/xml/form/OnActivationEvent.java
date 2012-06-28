/**
 * 
 */
package org.esco.indicators.domain.beans.xml.form;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

/**
 * Class representing the actions to execute when the associated {@link EntryValue} becomes activated (selected / checked).<br/>
 * These actions consist in :
 * <ul>
 * 	<li>Activating (checking / selecting) entry values in the user view</li>
 * 	<li>Desactivating (unchecking / unselecting) entry values in the user view</li>
 * </ul>
 * @since  2012/06/21
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@XmlType(name = "OnSelectionEventType")
public class OnActivationEvent {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(OnActivationEvent.class);
    
    /** The list of the entry values to activate on a selection event */
    private List<EntryValueRef> entryValuesToActivate;
    
    /** The list of the entry values to desactivate on a selection event */
    private List<EntryValueRef> entryValuesToDesactivate;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the references of the entry values to activate.
     * 
     * @return
     * 	the references of the entry values to activate
     */
    @XmlElement(name = "activate-entry-value")
    public List<EntryValueRef> getEntryValuesToActivate() {
        return entryValuesToActivate;
    }

    /**
     * Sets the references of the entry values to activate.
     * 
     * @param entryValuesToActivate
     * 			 The references of the entry values to activate
     */
    public void setEntryValuesToActivate(List<EntryValueRef> entryValuesToActivate) {
        this.entryValuesToActivate = entryValuesToActivate;
    }

    /**
     * Gets the references of the entry values to desactivate.
     * 
     * @return
     * 	the references of the entry values to desactivate
     */
    @XmlElement(name = "desactivate-entry-value")
    public List<EntryValueRef> getEntryValuesToDesactivate() {
        return entryValuesToDesactivate;
    }
    
    /**
     * Sets the references of the entry values to desactivate.
     * 
     * @param entryValuesToDesactivate
     * 			 The references of the entry values to desactivate
     */
    public void setEntryValuesToDesactivate(List<EntryValueRef> entryValuesToDesactivate) {
        this.entryValuesToDesactivate = entryValuesToDesactivate;
    }

    
    //------------------------------------------------------------------------------ PUBLIC METHODS

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((entryValuesToActivate == null) ? 0 : entryValuesToActivate.hashCode());
	result = prime * result
		+ ((entryValuesToDesactivate == null) ? 0 : entryValuesToDesactivate.hashCode());
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
	OnActivationEvent other = (OnActivationEvent) obj;
	if (entryValuesToActivate == null) {
	    if (other.entryValuesToActivate != null)
		return false;
	} else if (!entryValuesToActivate.equals(other.entryValuesToActivate))
	    return false;
	if (entryValuesToDesactivate == null) {
	    if (other.entryValuesToDesactivate != null)
		return false;
	} else if (!entryValuesToDesactivate.equals(other.entryValuesToDesactivate))
	    return false;
	return true;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
