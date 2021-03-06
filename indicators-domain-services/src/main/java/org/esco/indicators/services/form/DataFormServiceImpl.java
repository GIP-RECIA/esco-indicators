/**
 * 
 */
package org.esco.indicators.services.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.xml.form.EntryValue;

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

    /** Provider of the data form contained in a XML file (see {@link DataFormProviderImpl}) */
    private DataFormProvider dataFormProvider;

    // -------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link DataFormServiceImpl} class.
     */
    public DataFormServiceImpl() {
	super();
    }

    // --------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the data form provider.
     * 
     * @param dataFormProvider 
     * 			The data form provider to set.
     */
    public void setDataFormProvider(DataFormProvider dataFormProvider) {
        this.dataFormProvider = dataFormProvider;
    }
    
    
    // ------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getDisabledJspKeysByDefault()
     */
    @Override
    public List<String> getJspKeysDisabledByDefault() {
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
     * @see org.esco.indicators.services.form.DataFormService#getJspKeysEnabledByDefault()
     */
    @Override
    public List<String> getJspKeysEnabledByDefault() {
	// Final result
	List<String> jspKeys = new ArrayList<String>();
	
	// Gets the disabled entry values
	List<EntryValue> entries = dataFormProvider.getEntryValuesEnabledByDefault();
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
	// Retrieval of the checked entry values
	List<EntryValue> checkedEntries = getEntryValuesByJspKeys(checkedJspKeys);
	
	// Retrieval of the JSP keys to disable
	List<String> jspKeysToDisable = new ArrayList<String>();
	for (EntryValue checkedEntry : checkedEntries) {
	    List<EntryValue> entriesToDisable = checkedEntry.getEntryValuesToDisable();
	    jspKeysToDisable.addAll(getJspKeys(entriesToDisable));
	}
	
	return jspKeysToDisable;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getJspKeysToEnable(java.util.List)
     */
    @Override
    public List<String> getJspKeysToEnable(List<String> checkedJspKeys) {
	// Retrieval of the checked entry values
	List<EntryValue> checkedEntries = getEntryValuesByJspKeys(checkedJspKeys);
	
	// Retrieval of the JSP keys to enable
	List<String> jspKeysToEnable = new ArrayList<String>();
	for (EntryValue checkedEntry : checkedEntries) {
	    List<EntryValue> entriesToEnable = checkedEntry.getEntryValuesToEnable();
	    jspKeysToEnable.addAll(getJspKeys(entriesToEnable));
	}
		
        return jspKeysToEnable;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.esco.indicators.services.form.DataFormService#getEntryValues(java.lang.String)
     */
    @Override
    public List<EntryValue> getEntryValues(String entryName) {
	List<EntryValue> entryValues = new ArrayList<EntryValue>();
	entryValues.addAll(dataFormProvider.getEntryValues(entryName));
	
	// If no values has been retrieved for this entry
	if (entryValues.isEmpty()) {
	    LOGGER.warn("No value has been retrieved for the entry : [" + entryName + "] in the file : ["
		    + dataFormProvider.getDataFormFile() + "]");
	}
	
	return entryValues;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getCountyNumberToFilter(java.lang.String)
     */
    @Override
    public String getCountyNumberToFilter(String jspKey) {
       // Retrieval of the entry value
       EntryValue entryValue = dataFormProvider.getEntryValueByJspKey(jspKey);
        return entryValue.getCountyNumberToFilter();
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getCountyNumbersToFilter(java.lang.String)
     */
    @Override
    public List<String> getCountyNumbersToFilter(String jspKey) {
	// Retrieval of the entry value
	EntryValue entryValue = dataFormProvider.getEntryValueByJspKey(jspKey);
	return entryValue.getCountyNumbersToFilter();
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getCountyNumbersToFilter(java.util.List)
     */
    @Override
    public List<String> getCountyNumbersToFilter(List<String> checkedJspKeys) {
	// Final result
	List<String> countyNumbers = new ArrayList<String>();
	
	// Retrieval of the county numbers to filter
	for (String jspKey : checkedJspKeys) {
	    String countyNumber = getCountyNumberToFilter(jspKey);
	    if(countyNumber != null) {
		countyNumbers.add(countyNumber);
	    }
	}
	
	return countyNumbers;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getEntryName(java.lang.String)
     */
    @Override
    public String getEntryName(String jspKey) {
	       // Retrieval of the entry name
	       EntryValue entryValue = dataFormProvider.getEntryValueByJspKey(jspKey);
	       String entryName = ( entryValue == null ? null : entryValue.getName() );
	       if(entryName == null ) {
	           LOGGER.warn("No entry name associated to the JSP key : [" + jspKey +"]");
	       }
	        return entryName;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getEstablishmentTypeToFilter(java.lang.String)
     */
    @Override
    public String getEstablishmentTypeToFilter(String jspKey) {
       // Retrieval of the entry value
       EntryValue entryValue = dataFormProvider.getEntryValueByJspKey(jspKey);
       
        return entryValue.getEstablishmentTypeToFilter();
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getEstablishmentsTypesToFilter(java.util.List)
     */
    @Override
    public List<String> getEstablishmentsTypesToFilter(List<String> checkedJspKeys) {
	// Final result
	List<String> establishmentsTypes = new ArrayList<String>();
	
	// Retrieval of the establishments types to filter
	for (String jspKey : checkedJspKeys) {
	    String establishmentType = getEstablishmentTypeToFilter(jspKey);
	    if(establishmentType != null) {
		establishmentsTypes.add(establishmentType);
	    }
	}
	
	return establishmentsTypes;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getI18nKey(java.lang.String)
     */
    @Override
    public String getI18nKey(String jspKey) {
       // Retrieval of the entry value
       EntryValue entryValue = dataFormProvider.getEntryValueByJspKey(jspKey);
       String i18n = ( entryValue == null ? "" : entryValue.getI18nKey() );
       if(i18n == "" ) {
           LOGGER.warn("No i18n key associated to the JSP key : [" + jspKey +"]");
       }
        return i18n;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getServiceToFilter(java.lang.String)
     */
    @Override
    public String getServiceToFilter(String jspKey) {
	// Retrieval of the entry value
	EntryValue entryValue = dataFormProvider.getEntryValueByJspKey(jspKey);
	return entryValue.getServiceToFilter();
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getServicesToFilter(java.util.List)
     */
    @Override
    public List<String> getServicesToFilter(List<String> checkedJspKeys) {
        // Final result
	List<String> services = new ArrayList<String>();
	
	// Retrieval of the services to filter
	for (String jspKey : checkedJspKeys) {
	    String service = getServiceToFilter(jspKey);
	    if(service != null) {
		services.add(service);
	    }
	}
        return services;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getUsersProfilesToFilter(java.lang.String)
     */
    @Override
    public List<String> getUsersProfilesToFilter(String jspKey) {
	// Retrieval of the entry value
	EntryValue entryValue = dataFormProvider.getEntryValueByJspKey(jspKey);
	return entryValue.getUsersProfilesToFilter();
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#getUsersProfilesToFilter(java.util.List)
     */
    @Override
    public List<String> getUsersProfilesToFilter(List<String> checkedJspKeys) {
	// Final result
	List<String> usersProfiles = new ArrayList<String>();
	
	// Retrieval of the establishments types to filter
	for (String jspKey : checkedJspKeys) {
	    List<String> profiles = getUsersProfilesToFilter(jspKey);
	    if(profiles != null) {
		usersProfiles.addAll(profiles);
	    }
	}
	
	return usersProfiles;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#hasInfluenceOnEstablishmentsList(java.lang.String)
     */
    @Override
    public boolean hasInfluenceOnEstablishmentsList(String jspKey) {
        // Retrieval of the entry value
        EntryValue entryValue = dataFormProvider.getEntryValueByJspKey(jspKey);
        
        // Retrieval of the possible filters to apply when the entry value is selected
        String countyNumberToFilter = entryValue.getCountyNumberToFilter();
        String establishmentTypeToFilter = entryValue.getEstablishmentTypeToFilter();
        
        return (countyNumberToFilter != null || establishmentTypeToFilter != null);
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormService#isKnown(java.lang.String)
     */
    @Override
    public boolean isKnown(String jspKey) {
	// Try to retrieve the entry value associated to the JSP key
	EntryValue entryValue = dataFormProvider.getEntryValueByJspKey(jspKey);
	return (entryValue != null);
    }
    
    // ----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Gets the entry values associated to the JSP keys.
     * 
     * @param jspKeys
     * 			The JSP keys associated to entry values.
     * 
     * @return
     * 	the list of entry values associated to the JSP keys.
     */
    private List<EntryValue> getEntryValuesByJspKeys(List<String> jspKeys) {
	List<EntryValue> entryValues = new ArrayList<EntryValue>();
	for (String jspKey : jspKeys) {
	    EntryValue entryValue = dataFormProvider.getEntryValueByJspKey(jspKey);
	    if(entryValue != null) {
		entryValues.add(entryValue);
	    }
	}
	return entryValues;
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
