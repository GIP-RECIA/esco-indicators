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

    // ----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Initializes the {@link DataFormServiceImpl}.
     */
    private void init() {
	if (dataFormProvider == null) {
	    dataFormProvider = DataFormProvider.getInstance();
	}
    }

    // ------------------------------------------------------------------------------ STATIC METHODS
}
