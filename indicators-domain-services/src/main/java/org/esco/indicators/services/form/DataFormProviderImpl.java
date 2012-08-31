/**
 * 
 */
package org.esco.indicators.services.form;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.xml.form.DataForm;
import org.esco.indicators.domain.beans.xml.form.EntryForm;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

/**
 * Class providing functions to access the data that have to be displayed in the forms of the application.<br/>
 * These data are loaded from the file located at the URL : <code>dataFormFileUrl</code>.
 * 
 * @since  2012/06/18
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class DataFormProviderImpl implements DataFormProvider, InitializingBean {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(DataFormProviderImpl.class);

    /** File containing the data form */
    private Resource dataFormFile;
    
    /** Data form extracted from the above file */
    private DataForm dataForm;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Method called after the complete initialization of the bean.
     * 
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        feedDataForm();
    }
    
    /**
     * Default constructor of the {@link DataFormProviderImpl} class.
     */
    public DataFormProviderImpl() {
	super();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the data form.
     * 
     * @param dataForm 
     * 			The data form to set.
     */
    public void setDataForm(DataForm dataForm) {
        this.dataForm = dataForm;
    }
    
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormProvider#getDataFormFile()
     */
    @Override
    public Resource getDataFormFile() {
        return dataFormFile;
    }

    /**
     * Sets the data form file.
     * 
     * @param dataFormFile 
     * 			the data form file to set
     */
    public void setDataFormFile(Resource dataFormFile) {
        this.dataFormFile = dataFormFile;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormProvider#getEntriesForm()
     */
    @Override
    public List<EntryForm> getEntriesForm() {
	return dataForm.getEntriesForm();
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormProvider#getEntriesNames()
     */
    @Override
    public List<String> getEntriesNames() {
	List<EntryForm> entries = getEntriesForm();
	List<String> names = new ArrayList<String>();
	for (EntryForm entry : entries) {
	    names.add(entry.getName());
	}
	return names;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormProvider#getEntryValueByJspKey(java.lang.String)
     */
    @Override
    public EntryValue getEntryValueByJspKey(String jspKey) {
	// Retrieval of all the entries
	List<EntryValue> allEntryValues = dataForm.getAllEntryValues();
	
	// Retrieval of the entries having this JSP key
	List<EntryValue> foundEntries = new ArrayList<EntryValue>();
	for (EntryValue entryValue : allEntryValues) {
	    if(entryValue.getJspKey().equals(jspKey)) {
		foundEntries.add(entryValue);
	    }
	}
	
	// Test if there is no more than one entry with this JSP key
	if(foundEntries.isEmpty()) {
	    LOGGER.warn("No entry value associated to the JSP key : [" + jspKey + "]");
	    return null;
	} else if (foundEntries.size() > 1) {
	    LOGGER.warn("More than one entry value associated to the JSP key : [" + jspKey + "]");
	}
	 return foundEntries.get(0);
    }
    

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormProvider#getEntryValues(java.lang.String)
     */
    @Override
    public List<EntryValue> getEntryValues(String entryName) {
	List<EntryForm> entries = getEntriesForm();
	List<EntryValue> values = new ArrayList<EntryValue>();
	for (EntryForm entry : entries) {
	    if(entry.getName().equals(entryName)) {
		values.addAll(entry.getEntryValues());
	    }
	}
	return values;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormProvider#getEntryValuesDisabledByDefault()
     */
    @Override
    public List<EntryValue> getEntryValuesDisabledByDefault() {
	return dataForm.getEntryValuesDisabledByDefault();
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormProvider#getEntryValuesEnabledByDefault()
     */
    @Override
    public List<EntryValue> getEntryValuesEnabledByDefault() {
	return dataForm.getEntryValuesEnabledByDefault();
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.form.DataFormProvider#reloadDataForm()
     */
    @Override
    public void reloadDataForm() {
	feedDataForm();
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    /**
     * Feeds the data form by loading the content of the file set via the setDataFormFileUrl method.
     */
    private void feedDataForm() {
		// Debug infos
		LOGGER.debug("Loading of the data form  : " + dataFormFile );
		try {
	        	// Construction of the context with the "root" classes
	        	JAXBContext jaxbContext = JAXBContext.newInstance(DataForm.class);
	        	
	        	// Creation of the unmarshaller
	        	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        	
	        	// Mapping of the XML files into the classes
	        	File file = dataFormFile.getFile();
	        	
	        	DataForm data = (DataForm) unmarshaller.unmarshal(file);
	        	
	        	// Feeding of the container
	        	setDataForm(data);
		} catch(IOException e) {
		    LOGGER.error("An error occured during an I/O operation : " + e.getMessage());
		} catch (JAXBException e) {
		    LOGGER.error("An error occured during the mapping of XML data form file : " + e.getMessage());
		}
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
