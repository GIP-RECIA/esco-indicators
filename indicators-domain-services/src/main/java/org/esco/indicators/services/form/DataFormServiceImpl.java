/**
 * 
 */
package org.esco.indicators.services.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.esupportail.commons.services.i18n.I18nService;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.syndication.feed.atom.Entry;

/**
 * Implementation of the {@link DataFormService} interface.
 * 
 * @since 2012/06/19
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class DataFormServiceImpl implements DataFormService {
    // ---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(DataFormServiceImpl.class);

    /** Provider of the data form contained in a XML file (see {@link DataFormProvider}) */
    private DataFormProvider dataFormProvider;

    // -------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link DataFormServiceImpl} class.
     */
    private DataFormServiceImpl() {
	super();
    }

    // --------------------------------------------------------------------------- GETTERS / SETTERS

    // ------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getDisabledJspKeysByDefault()
     */
    @Override
    public List<String> getJspKeysDisabledByDefault() {
	init();
	// Final result
	List<String> jspKeys = new ArrayList<String>();
	
	// Gets the disabled entry values
	List<EntryValue> entries = dataFormProvider.getEntryValuesDisabledByDefault();
	for (EntryValue entryValue : entries) {
	    jspKeys.add(entryValue.getJspKey());
	}
	
	return jspKeys;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getJspKeysToDisable(java.util.List)
     */
    @Override
    public List<String> getJspKeysToDisable(List<String> checkedJspKeys) {
	init();
	
	// Retrieval of the checked entry values
	List<EntryValue> checkedEntries = new ArrayList<EntryValue>();
	for (String jspKey : checkedJspKeys) {
	    EntryValue entryValue = dataFormProvider.getEntryValueByJspKey(jspKey);
	    if(entryValue != null) {
		checkedEntries.add(entryValue);
	    }
	}
	
	// Retrieval of the JSP keys to disable
	List<String> jspKeysToDisable = new ArrayList<String>();
	for (EntryValue checkedEntry : checkedEntries) {
	    List<EntryValue> entriesToDisable = checkedEntry.getEntryValuesToDisable();
	    jspKeysToDisable.addAll(getJspKeys(entriesToDisable));
	}
	
	return jspKeysToDisable;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.esco.indicators.services.form.DataFormService#getEntryValues(java.lang.String)
     */
    @Override
    public List<EntryValue> getEntryValues(String entryName) {
	init();
	
	List<EntryValue> entryValues = new ArrayList<EntryValue>();
	entryValues.addAll(dataFormProvider.getEntryValues(entryName));
	
	// If no values has been retrieved for this entry
	if (entryValues.isEmpty()) {
	    LOGGER.warn("No value has been retrieved for the entry : [" + entryName + "] in the file : ["
		    + DataFormProvider.getDataFormFileUrl() + "]");
	}
	
	return entryValues;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#isKnown(java.lang.String)
     */
    @Override
    public boolean isKnown(String jspKey) {
	init();
	
	// Try to retrieve the entry value associated to the JSP key
	EntryValue entryValue = dataFormProvider.getEntryValueByJspKey(jspKey);
	return (entryValue != null);
    }
    
    // ----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Initializes the {@link DataFormServiceImpl}.
     */
    private void init() {
	if (dataFormProvider == null) {
	    dataFormProvider = DataFormProvider.getInstance();
	}
    }
    
    /**
     * Gets the JSP keys of the specified entry values.
     * 
     * @param entryValues
     * 			The entry values associated to a JSP key.
     * @return
     * 	the list of the JSP keys associated to the entry values.<br/>
     * 	an empty list if no JSP key has been retrived.
     */
    private List<String> getJspKeys(List<EntryValue> entryValues) {
	List<String> jspKeys = new ArrayList<String>();
	for (EntryValue entryValue : entryValues) {
	    jspKeys.add(entryValue.getJspKey());
	}
	return jspKeys;
    }

    // ------------------------------------------------------------------------------ STATIC METHODS
}
