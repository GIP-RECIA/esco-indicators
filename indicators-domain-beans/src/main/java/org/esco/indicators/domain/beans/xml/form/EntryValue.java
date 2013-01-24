/**
 * 
 */
package org.esco.indicators.domain.beans.xml.form;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

/**
 * Class representing the possible value of an entry.
 * 
 * @since  2012/06/18
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@XmlType(name = "EntryValueType")
public class EntryValue {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EntryValue.class);

    /** Parent of the entry value */
    private EntryForm parentEntryForm;
    
    /** Identifier of the value */
    private String name;
    
    /** Key of the value in the JSP */
    private String jspKey;
    
    /** Key of the value for the i18n translation */
    private String i18nKey;
    
    /** Boolean indicating if the entry value is disable by default in the user view */
    private boolean disabledByDefault;
    
    /** Entry values to activate/desactivate when this entry value is selected in the user view */
    private WhenActivatedEvent whenActivatedEvent;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link EntryValue} class.
     */
    public EntryValue() {
	super();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    
    /**
     * Gets the entry form which is the parent of this entry value.
     * 
     * @return 
     * 	the parent of this entry value
     */
    public EntryForm getParentEntryForm() {
        return parentEntryForm;
    }

    /**
     * Sets the parent entry form of this entry value.
     * 
     * @param parentEntryForm
     * 			The parent entry form of this entry value.
     */
    public void setParentEntryForm(EntryForm parentEntryForm) {
        this.parentEntryForm = parentEntryForm;
    }
    
    /**
     * Gets the name of the value.
     * 
     * @return 
     * 	the name of the value.
     */
    @XmlAttribute(name = "name")
    @XmlID
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the value.
     * 
     * @param name 
     * 			The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the JSP key of the value.
     * 
     * @return 
     * 	the jsp key of the value.
     */
    @XmlElement(name = "jsp-key")
    public String getJspKey() {
        return jspKey;
    }

    /**
     * Sets the JSP key of the value.
     * 
     * @param jspKey the jspKey to set
     */
    public void setJspKey(String jspKey) {
        this.jspKey = jspKey;
    }

    /**
     * Gets the i18n key of the value.
     * 
     * @return 
     * 	the i18n key of the value.
     */
    @XmlElement(name = "i18n-key")
    public String getI18nKey() {
        return i18nKey;
    }

    /**
     * Sets the i18n key of the value.
     * 
     * @param i18nKey 
     * 			the i18n key to set.
     */
    public void setI18nKey(String i18nKey) {
        this.i18nKey = i18nKey;
    }
    
    /**
     * Indicates if the entry value is disable by default in the user view.
     * 
     * @return 
     * 	the disable state of the entry value in the user view.
     */
    @XmlElement(name = "disable-by-default")
    public boolean isDisabledByDefault() {
        return disabledByDefault;
    }

    /**
     * Sets the disable state of the entry value in the user view.
     * 
     * @param disabled 
     * 			The disable state to set.
     */
    public void setDisabledByDefault(boolean disabled) {
        this.disabledByDefault = disabled;
    }
    
    /**
     * Gets the entry values to activate / desactivate when this entry value is activated (checked / selected) in the user view.
     * 
     * @return
     * 	the entry values to activate / desactivate when this entry value is activated in the user view.
     */
    @XmlElement(name = "when-activated-event")
    public WhenActivatedEvent getWhenActivatedEvent() {
        return whenActivatedEvent;
    }

    /**
     * Sets the entry values to activate / desactivate when this entry value is activated (checked / selected) in the user view.
     * 
     * @param whenActivatedEvent 
     * 			The whenActivatedEvent to set
     */
    public void setWhenActivatedEvent(WhenActivatedEvent whenActivatedEvent) {
        this.whenActivatedEvent = whenActivatedEvent;
    }

    /**
     * Gets the entry values to disable when this entry value is activated.
     * 
     * @return
     * 	the entry values to disable when this entry value is activated.<br/>
     * 	an empty list if there is no entry values to disable.
     */
    public List<EntryValue> getEntryValuesToDisable() {
	// Retrieval of the entry values references to disable 
	List<EntryValueRef> entryValueRefs = getWhenActivatedEvent().getEntryValuesToDisable();
	
	// Retrieval of the entry values by references
	List<EntryValue> entryValues = new ArrayList<EntryValue>();
	entryValues.addAll(getEntryValuesByRefs(entryValueRefs));
	
	return entryValues;
    }

    /**
     * Gets the entry values to enable when this entry value is activated.
     * 
     * @return
     * 	the entry values to enable when this entry value is activated.<br/>
     * 	an empty list if there is no entry values to enable.
     */
    public List<EntryValue> getEntryValuesToEnable() {
	// Retrieval of the entry values references to enable
	List<EntryValueRef> entryValueRefs = getWhenActivatedEvent().getEntryValuesToEnable();
	
	// Retrieval of the entry values by references
	List<EntryValue> entryValues = new ArrayList<EntryValue>();
	entryValues.addAll(getEntryValuesByRefs(entryValueRefs));
	
	return entryValues;
    }
    
    /**
     * Gets the county number to filter when this entry value is activated.
     * 
     * @return 
     * 	the county number to filter.
     */
    public String getCountyNumberToFilter() {
	List<String> numbers =  getWhenActivatedEvent().getFiltersCountyNumber();
	return (numbers == null ? null : numbers.get(0));
    }
    
    /**
     * Gets the county numbers to filter when this entry value is activated.
     * 
     * @return 
     * 	the county numbers to filter.
     */
    public List<String> getCountyNumbersToFilter() {
	return getWhenActivatedEvent().getFiltersCountyNumber();
    }
    
    /**
     * Gets the establishment type to filter when this entry value is activated.
     * 
     * @return 
     * 	the establishment type to filter.
     */
    public String getEstablishmentTypeToFilter() {
	return getWhenActivatedEvent().getFilterEstablishmentType();
    }
    
    /**
     * Gets the service to filter when this entry value has been selected.
     * 
     * @return 
     * 	the establishment type to filter.
     */
    public String getServiceToFilter() {
	return getWhenActivatedEvent().getFilterService();
    }
    
    /**
     * Gets the user profile to filter when this entry value has been selected.
     * 
     * @return 
     * 	the establishment type to filter.
     */
    public String getUserProfileToFilter() {
	return getWhenActivatedEvent().getFilterUserProfile();
    }
    
    
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (disabledByDefault ? 1231 : 1237);
	result = prime * result + ((i18nKey == null) ? 0 : i18nKey.hashCode());
	result = prime * result + ((jspKey == null) ? 0 : jspKey.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((parentEntryForm == null) ? 0 : parentEntryForm.hashCode());
	result = prime * result + ((whenActivatedEvent == null) ? 0 : whenActivatedEvent.hashCode());
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
	EntryValue other = (EntryValue) obj;
	if (disabledByDefault != other.disabledByDefault)
	    return false;
	if (i18nKey == null) {
	    if (other.i18nKey != null)
		return false;
	} else if (!i18nKey.equals(other.i18nKey))
	    return false;
	if (jspKey == null) {
	    if (other.jspKey != null)
		return false;
	} else if (!jspKey.equals(other.jspKey))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (parentEntryForm == null) {
	    if (other.parentEntryForm != null)
		return false;
	} else if (!parentEntryForm.equals(other.parentEntryForm))
	    return false;
	if (whenActivatedEvent == null) {
	    if (other.whenActivatedEvent != null)
		return false;
	} else if (!whenActivatedEvent.equals(other.whenActivatedEvent))
	    return false;
	return true;
    }
    

    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Gets the entry values referenced by the entry values references.
     * 
     * @param entryValueRefs
     * 			The entry values references.
     * @return
     * 	the list of entry values referenced.<br/>
     * 	an empty list if no entry value has been retrieved.
     */
    private List<EntryValue> getEntryValuesByRefs(List<EntryValueRef> entryValueRefs) {
	// Final result
	List<EntryValue> entryValues = new ArrayList<EntryValue>();
	
	// If there is no entry values references
	if(entryValueRefs == null) {
	    return entryValues;
	}
	
	// Else
	for (EntryValueRef entryValueRef : entryValueRefs) {
	    entryValues.add(entryValueRef.getEntryValue());
	}
	
	return entryValues;
    }

    
      //------------------------------------------------------------------------------ STATIC METHODS
}
