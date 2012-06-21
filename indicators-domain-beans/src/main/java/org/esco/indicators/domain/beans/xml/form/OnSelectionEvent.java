/**
 * 
 */
package org.esco.indicators.domain.beans.xml.form;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

import org.apache.log4j.Logger;

/**
 * Class representing the actions to execute when the associated {@link EntryValue} is selected.<br/>
 * These actions consist in :
 * <ul>
 * 	<li>Disabling entry values in the user view</li>
 * 	<li>Enabling entry values in the user view</li>
 * </ul>
 * @since  2012/06/21
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class OnSelectionEvent {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(OnSelectionEvent.class);
    
    /** The list of entry values that must be disabled when the entry value is selected */
    List<DisableEntryValue> entryValuesToDisable;
    
    /** The list of entry values that must be enabled when the entry value is selected */
    List<EnableEntryValue> entryValuesToEnable;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * @return the entryValuesToDisable
     */
    @XmlElement(name = "disable-entry-value")
    public List<DisableEntryValue> getEntryValuesToDisable() {
	// Retrieval of the entryValues referenced by the disableEntryValues
	List<EntryValue> entryValues = new ArrayList<EntryValue>();
	
	if(entryValuesToEnable != null) {
        	for (DisableEntryValue disableEntryValue : entryValuesToDisable) {
        	    entryValues.add(disableEntryValue.getEntryValue());
        	}
	}
	
        return entryValuesToDisable;
    }

    /**
     * @param entryValuesToDisable the entryValuesToDisable to set
     */
    public void setEntryValuesToDisable(List<DisableEntryValue> entryValuesToDisable) {
        this.entryValuesToDisable = entryValuesToDisable;
    }

    /**
     * @return the entryValuesToEnable
     */
    @XmlElement(name = "enable-entry-value")
    public List<EnableEntryValue> getEntryValuesToEnable() {
	// Retrieval of the entryValues referenced by the enableEntryValues
	List<EntryValue> entryValues = new ArrayList<EntryValue>();
	
	if(entryValuesToEnable != null) {
    		for (EnableEntryValue enableEntryValue : entryValuesToEnable) {
    		    entryValues.add(enableEntryValue.getEntryValue());
    		}
	}
	
        return entryValuesToEnable;
    }

    /**
     * @param entryValuesToEnable the entryValuesToEnable to set
     */
    public void setEntryValuesToEnable(List<EnableEntryValue> entryValuesToEnable) {
        this.entryValuesToEnable = entryValuesToEnable;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
