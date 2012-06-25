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

    /** Identifier of the value */
    private String name;
    
    /** Key of the value in the JSP */
    private String jspKey;
    
    /** Key of the value for the i18n translation */
    private String i18nKey;
    
    /** Boolean indicating if the entry value is disable by default in the user view */
    private boolean disabledByDefault;
    
    /** Entry values to enable/disable when this entry value is selected in the suer view */
    private OnSelectionEvent onSelectionEvent;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link EntryValue} class.
     */
    public EntryValue() {
	super();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
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
     * Gets the entry values to enable / disable when this entry value is selected in the user view.
     * 
     * @return 
     * 	the entry values to enable / disable when this entry value is selected in the user view.
     */
    @XmlElement(name = "on-selection-event")
    public OnSelectionEvent getOnSelectionEvent() {
        return onSelectionEvent;
    }

    /**
     * Sets the entry values to enable / disable when this entry value is selected in the user view.
     * 
     * @param onSelectionEvent 
     * 			the onSelectionEvent to set
     */
    public void setOnSelectionEvent(OnSelectionEvent onSelectionEvent) {
        this.onSelectionEvent = onSelectionEvent;
    }

    /**
     * Gets the entry values to disable when this entry value is selected.
     * 
     * @return
     * 	the entry values to disable when this entry value is selected.<br/>
     * 	an empty list if there is no entry values to disable.
     */
    public List<EntryValue> getEntryValuesToDisable() {
	// Gets the disable entry values
	List<DisableEntryValue> disableEntryValues = getOnSelectionEvent().getEntryValuesToDisable();
	
	// If no disableEntryValues has been retrieved
	List<EntryValue> entryValues = new ArrayList<EntryValue>();
	if(disableEntryValues == null) {
	    return entryValues;
	}
	
	// If some disableEntryValues has been retrieved, gets the corresponding entryValues
	for (DisableEntryValue disableEntryValue : disableEntryValues) {
	    entryValues.add(disableEntryValue.getEntryValue());
	}
	
	return entryValues;
    }
    
    /**
     * Gets the entry values to enable when this entry value is selected.
     * 
     * @return
     * 	the entry values to enable when this entry value is selected.<br/>
     * 	an empty list if there is no entry values to enable.
     */
    public List<EntryValue> getEntryValuesToEnable() {
	// Gets the enable entry values
	List<EnableEntryValue> enableEntryValues = getOnSelectionEvent().getEntryValuesToEnable();
	
	// Gets the corresponding entry values
	List<EntryValue> entryValues = new ArrayList<EntryValue>();
	for (EnableEntryValue enableEntryValue : enableEntryValues) {
	    entryValues.add(enableEntryValue.getEntryValue());
	}
	
	return entryValues;
    }
    
 

    //----------------------------------------------------------------------------- PRIVATE METHODS
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
	result = prime * result + ((onSelectionEvent == null) ? 0 : onSelectionEvent.hashCode());
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
	if (onSelectionEvent == null) {
	    if (other.onSelectionEvent != null)
		return false;
	} else if (!onSelectionEvent.equals(other.onSelectionEvent))
	    return false;
	return true;
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
