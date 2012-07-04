/**
 * 
 */
package org.esco.indicators.domain.beans.xml.form;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

/**
 * Class representing the actions and filters to execute when the associated {@link EntryValue} is activated (checked / selected).<br/>
 * These actions consist in :
 * <ul>
 * 	<li>Enabling entry values in the user view</li>
 * 	<li>Disabling entry values in the user view</li>
 * </ul>
 * These filters consist in :
 * <ul>
 * 	<li>Filtering the county number of the establishments list in the user view</li>
 * 	<li>Filtering the establishment type of the establishments lis in the user viewt</li>
 * </ul>
 * @since  2012/06/28
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@XmlType(name = "WhenActivatedEventType")
public class WhenActivatedEvent {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(WhenActivatedEvent.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    /** The list of entry values that must be disabled when the entry value is selected */
    private List<EntryValueRef> entryValuesToDisable;
    
    /** The list of entry values that must be enabled when the entry value is selected */
    private List<EntryValueRef> entryValuesToEnable;
    
    /** The county number to filter */
    private Integer filterCountyNumber;
    
    /** The establishment type to filter */
    private String filterEstablishmentType;
    
    /** The user profile to filter */
    private String filterUserProfile;
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the entry values to disable.
     * 
     * @return the entryValuesToDisable
     */
    @XmlElement(name = "disable-entry-value")
    public List<EntryValueRef> getEntryValuesToDisable() {
        return entryValuesToDisable;
    }

    /**
     * Sets the entry value to disable.
     * 
     * @param entryValuesToDisable the entryValuesToDisable to set
     */
    public void setEntryValuesToDisable(List<EntryValueRef> entryValuesToDisable) {
        this.entryValuesToDisable = entryValuesToDisable;
    }

    /**
     * Gets the entry values to enable.
     * 
     * @return the entryValuesToEnable
     */
    @XmlElement(name = "enable-entry-value")
    public List<EntryValueRef> getEntryValuesToEnable() {
        return entryValuesToEnable;
    }

    /**
     * Sets the entry values to enable.
     * 
     * @param entryValuesToEnable the entryValuesToEnable to set
     */
    public void setEntryValuesToEnable(List<EntryValueRef> entryValuesToEnable) {
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

    /**
     * Gets the user profile to filter.
     * 
     * @return 
     * 	the user profile to filter.
     */
    @XmlElement(name = "filter-user-profile")
    public String getFilterUserProfile() {
        return filterUserProfile;
    }

    /**
     * Sets the user profile to filter.
     * 
     * @param filterUserProfile 
     * 			the user profile (to filter) to set.
     */
    public void setFilterUserProfile(String filterUserProfile) {
        this.filterUserProfile = filterUserProfile;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((entryValuesToDisable == null) ? 0 : entryValuesToDisable.hashCode());
	result = prime * result + ((entryValuesToEnable == null) ? 0 : entryValuesToEnable.hashCode());
	result = prime * result + ((filterCountyNumber == null) ? 0 : filterCountyNumber.hashCode());
	result = prime * result
		+ ((filterEstablishmentType == null) ? 0 : filterEstablishmentType.hashCode());
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
	WhenActivatedEvent other = (WhenActivatedEvent) obj;
	if (entryValuesToDisable == null) {
	    if (other.entryValuesToDisable != null)
		return false;
	} else if (!entryValuesToDisable.equals(other.entryValuesToDisable))
	    return false;
	if (entryValuesToEnable == null) {
	    if (other.entryValuesToEnable != null)
		return false;
	} else if (!entryValuesToEnable.equals(other.entryValuesToEnable))
	    return false;
	if (filterCountyNumber == null) {
	    if (other.filterCountyNumber != null)
		return false;
	} else if (!filterCountyNumber.equals(other.filterCountyNumber))
	    return false;
	if (filterEstablishmentType == null) {
	    if (other.filterEstablishmentType != null)
		return false;
	} else if (!filterEstablishmentType.equals(other.filterEstablishmentType))
	    return false;
	return true;
    }
    
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
