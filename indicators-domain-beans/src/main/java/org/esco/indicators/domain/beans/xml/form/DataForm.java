/**
 * 
 */
package org.esco.indicators.domain.beans.xml.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

/**
 * Class providing the data forms of the application.
 * 
 * @since  2012/06/18
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@XmlRootElement(name = "data-form")
public class DataForm {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(DataForm.class);

    /** List of the entries of the form */
    private List<EntryForm> entriesForm;

    /** List of all the entry values present into the form */
    private List<EntryValue> allEntryValues;
    
    /** List of the entry values disabled by default in the user view */
    private List<EntryValue> disabledByDefault;
    
    /** List of the entry values enabled by default in the user view */
    private List<EntryValue> enabledByDefault;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link DataForm} class.
     */
    private DataForm() {
	super();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    
    /**
     * Gets the entries of the form
     * 
     * @return
     * 	The entries of the form.
     */
    @XmlElement(name = "entry-form")
    public List<EntryForm> getEntriesForm() {
	return entriesForm;
    }

    /**
     * Sets the entries of the form.
     * 
     * @param entriesForm
     * 			 The entries to set.
     */
    public void setEntriesForm(List<EntryForm> entriesForm) {
        this.entriesForm = entriesForm;
    }
    

    /**
     * Gets all the entry values presents in the form.
     * 
     * @return
     * 	 the entry values presents in the form.
     */
    public List<EntryValue> getAllEntryValues() {
	if(allEntryValues == null) {
	    allEntryValues = retrieveAllEntryValues();
	}
	return allEntryValues;
    }


    /**
     * Gets the entry values that are disabled by default in the user view.
     * 
     * @return
     * 	the entry values that are disabled by default in the user view.
     */
    public List<EntryValue> getEntryValuesDisabledByDefault() {
	if(disabledByDefault == null) {
	    disabledByDefault = retrieveEntryValuesDisabledByDefault();
	}
	return disabledByDefault;
    }
    
    /**
     * Gets the entry values that are enabled by default in the user view.
     * 
     * @return
     * 	the entry values that are enabled by default in the user view.
     */
    public List<EntryValue> getEntryValuesEnabledByDefault() {
	if(enabledByDefault == null) {
	    enabledByDefault = retrieveEntryValuesEnabledByDefault();
	}
	return enabledByDefault;
    }
    
    
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
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
	DataForm other = (DataForm) obj;
	if (entriesForm == null) {
	    if (other.entriesForm != null)
		return false;
	} else if (!entriesForm.equals(other.entriesForm))
	    return false;
	return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((entriesForm == null) ? 0 : entriesForm.hashCode());
	return result;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    /**
     * Retrieval of all the entry values of the form.<br/>
     * This retrieval is made by searching the values through all the form.
     * 
     * @return
     * 	all the entry values of the form.<br/>
     * 	an empty list if no entry values 
     */
    private List<EntryValue> retrieveAllEntryValues() {
	// Final result
	List<EntryValue> allEntries = new ArrayList<EntryValue>();
	
	// Search through the whole form
	for (EntryForm entryForm : getEntriesForm()) {
	    allEntries.addAll(entryForm.getEntryValues());
	}
	
	return allEntries;
    }
    
    /**
     * Retrieval of the value entries disabled by default by searching through the all data form.
     * 
     * @return
     * 	the entry values disabled by defaut in the data form.
     */
    private List<EntryValue> retrieveEntryValuesDisabledByDefault() {
	// Final result
	List<EntryValue> disabledEntriesByDefault = new ArrayList<EntryValue>();
	
	// Gets all the disabled entries by default
	for (EntryValue entryValue : getAllEntryValues()) {
	    if(entryValue.isDisabledByDefault()) {
		disabledEntriesByDefault.add(entryValue);
	    }
	}
	
	return disabledEntriesByDefault;
    }
    
    /**
     * Retrieval of the value entries enabled by default by searching through the all data form.
     * 
     * @return
     * 	the entry values enabled by defaut in the data form.
     */
    private List<EntryValue> retrieveEntryValuesEnabledByDefault() {
	// Final result
	List<EntryValue> enabledEntriesByDefault = new ArrayList<EntryValue>();
	
	// Gets all the enabled entries by default
	for (EntryValue entryValue : getAllEntryValues()) {
	    if(!entryValue.isDisabledByDefault()) {
		enabledEntriesByDefault.add(entryValue);
	    }
	}
	
	return enabledEntriesByDefault;
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
