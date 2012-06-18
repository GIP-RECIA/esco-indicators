/**
 * 
 */
package org.esco.indicators.utils.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.xml.form.DataForm;
import org.esco.indicators.domain.beans.xml.form.EntryForm;
import org.esco.indicators.domain.beans.xml.form.EntryValue;

/**
 * Class providing functions to access the data that have to be displayed in the forms of the application.
 * 
 * @since  2012/06/18
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class DataFormProvider {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(DataFormProvider.class);

    /** URL of the file containing the data form */
    private static String dataFormFileUrl;
    
    /** Singleton of the class */
    private static DataFormProvider instance;
    
    /** Data form extracted from the above file */
    private DataForm dataForm;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default contructor of the {@link DataFormProvider} class.
     */
    private DataFormProvider() {
	super();
	feedDataForm();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the data form.
     * 
     * @param dataForm 
     * 			The data form to set.
     */
    private void setDataForm(DataForm dataForm) {
        this.dataForm = dataForm;
    }
    
    
    /**
     * Gets the entries of the form.
     * 
     * @return
     * 	The entries of the form.
     */
    public List<EntryForm> getEntriesForm() {
	return dataForm.getEntriesForm();
    }
    
    /**
     * Gets the names of the entries of the form.
     * 
     * @return
     * 	The names of the entries of the form.<br/>
     * 	An empty list if no is retrieved.
     */
    public List<String> getEntriesNames() {
	List<EntryForm> entries = getEntriesForm();
	List<String> names = new ArrayList<String>();
	for (EntryForm entry : entries) {
	    names.add(entry.getName());
	}
	return names;
    }
    
    /**
     * Gets the possible values for the entry having the specified <code>name</code>.
     * 
     * @param entryName
     * 			The name of the entry
     * @return
     * 	the possible values for the entry.<br/>
     * 	an empty list if no values has been retrieved.
     */
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
    
    //-------------------------------------------------------------------- STATIC GETTERS / SETTERS
    /**
     * Gets the data forms loaded from the file containing the data descriptions.
     * 
     * @return
     * 	the data forms loaded from the file containing the data descriptions.
     */
    public static DataFormProvider getInstance() {
	if(instance == null) {
	    instance = new DataFormProvider();
	}
	return instance;
    }


    /**
     * Sets the URL of the file containing the data form.
     * 
     * @param dataFormFileUrl 
     * 			The URL of the file containing the data form.
     */
    public static void setDataFormFileUrl(String dataFormFileUrl) {
        DataFormProvider.dataFormFileUrl = dataFormFileUrl;
    }
    
    
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    /**
     * Reloads the data present in the file containing the data form descriptions.
     */
    public void reloadDataForm() {
	feedDataForm();
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    /**
     * Feeds the data form by loading the content of the file set via the setDataFormFileUrl method.
     */
    private void feedDataForm() {
		try {
	        	// Construction of the context with the "root" classes
	        	JAXBContext jaxbContext = JAXBContext.newInstance(DataForm.class);
	        	
	        	// Creation of the unmarshaller
	        	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        	
	        	// Mapping of the XML files into the classes
	        	File dataFormFile = new File(dataFormFileUrl);
	        	
	        	DataForm data = (DataForm) unmarshaller.unmarshal(dataFormFile);
	        	
	        	// Feeding of the container
	        	setDataForm(data);
		} catch (JAXBException e) {
		    LOGGER.error("An error occured during the mapping of XML data form file : " + e.getMessage());
		}
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
