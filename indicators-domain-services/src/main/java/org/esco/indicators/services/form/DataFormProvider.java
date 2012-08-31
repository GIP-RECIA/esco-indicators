/**
 * 
 */
package org.esco.indicators.services.form;

import java.util.List;

import org.esco.indicators.domain.beans.xml.form.EntryForm;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.springframework.core.io.Resource;

/**
 * Service providing the content data of a form.
 * 
 * @since  2012/08/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface DataFormProvider {
    /**
     * Gets the file containing the data form.
     * 
     * @return 
     * 	the file containing the data form.
     */
    public Resource getDataFormFile();
    
    /**
    * Gets the entries of the form.
    * 
    * @return
    * 	The entries of the form.
    */
   public List<EntryForm> getEntriesForm();
   
   /**
    * Gets the names of the entries of the form.
    * 
    * @return
    * 	The names of the entries of the form.<br/>
    * 	An empty list if no is retrieved.
    */
   public List<String> getEntriesNames();
   
   /**
    * Gets the entry value associated with the specified JSP key.
    * 
    * @param jspKey
    * 			The JSP key of the entry value.
    * @return
    * 	The first entry value found having the specified JSP Key.<br/>
    * 	<code>null</code> if no entry value has been retrieved.
    */
   public EntryValue getEntryValueByJspKey(String jspKey);
   
   /**
    * Gets the possible values for the entry having the specified <code>name</code>.
    * 
    * @param entryName
    * 			The name of the entry
    * @return
    * 	the possible values for the entry.<br/>
    * 	an empty list if no values has been retrieved.
    */
   public List<EntryValue> getEntryValues(String entryName);
   
   /**
    * Gets the entry values that are disabled by default in the user view.
    * 
    * @return
    * 	the entry values that are disabled by default in the user view.
    */
   public List<EntryValue> getEntryValuesDisabledByDefault();
   
   /**
    * Gets the entry values that are enabled by default in the user view.
    * 
    * @return
    * 	the entry values that are enabled by default in the user view.
    */
   public List<EntryValue> getEntryValuesEnabledByDefault();

   /**
    * Reloads the data present in the file containing the data form descriptions.
    */
   public void reloadDataForm();

}
