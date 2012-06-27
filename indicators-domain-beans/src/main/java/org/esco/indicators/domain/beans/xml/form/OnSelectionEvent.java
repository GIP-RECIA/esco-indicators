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
    private List<DisableEntryValue> entryValuesToDisable;
    
    /** The list of entry values that must be enabled when the entry value is selected */
    private List<EnableEntryValue> entryValuesToEnable;
    
    /** The county number to filter */
    private Integer filterCountyNumber;
    
    /** The establishment type to filter */
    private String filterEstablishmentType;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the entry values to disable.
     * 
     * @return the entryValuesToDisable
     */
    @XmlElement(name = "disable-entry-value")
    public List<DisableEntryValue> getEntryValuesToDisable() {
        return entryValuesToDisable;
    }

    /**
     * Sets the entry value to disable.
     * 
     * @param entryValuesToDisable the entryValuesToDisable to set
     */
    public void setEntryValuesToDisable(List<DisableEntryValue> entryValuesToDisable) {
        this.entryValuesToDisable = entryValuesToDisable;
    }

    /**
     * Gets the entry values to enable.
     * 
     * @return the entryValuesToEnable
     */
    @XmlElement(name = "enable-entry-value")
    public List<EnableEntryValue> getEntryValuesToEnable() {
        return entryValuesToEnable;
    }

    /**
     * Sets the entry values to enable.
     * 
     * @param entryValuesToEnable the entryValuesToEnable to set
     */
    public void setEntryValuesToEnable(List<EnableEntryValue> entryValuesToEnable) {
        this.entryValuesToEnable = entryValuesToEnable;
    }

    /**
     * Gets the county number to filter.
     * 
     * @return 
     * 	the county number to filter.
     */
    @XmlElement(name = "filter-county-number")
    public Integer getFilterCountyNumber() {
        return filterCountyNumber;
    }

    /**
     * Sets the county number to filter.
     * 
     * @param filterCountyNumber 
     * 			The county number (to filter) to set.
     */
    public void setFilterCountyNumber(Integer filterCountyNumber) {
        this.filterCountyNumber = filterCountyNumber;
    }

    /**
     * Gets the establishment type to filter.
     * 
     * @return 
     * 	the establishment type to filter
     */
    @XmlElement(name = "filter-establishment-type")
    public String getFilterEstablishmentType() {
        return filterEstablishmentType;
    }

    /**
     * Sets the establishment type to filter.
     * 
     * @param filterEstablishmentType 
     * 			The establishment type (to filter) to set.
     */
    public void setFilterEstablishmentType(String filterEstablishmentType) {
        this.filterEstablishmentType = filterEstablishmentType;
    }

    
    
    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
