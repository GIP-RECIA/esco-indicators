/**
 * 
 */
package org.esco.indicators.services.form;

import java.util.List;
import java.util.Locale;

import org.esco.indicators.domain.beans.xml.form.EntryValue;

/**
 * Service providing the data to put in the application forms.
 * 
 * @since  2012/06/18
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface DataFormService {
    
    /**
     * Gets the possible values (see {@link EntryValue}) of the data form entry having the name <code>entryName</code>.
     * 
     * @param entryName
     * 			The name of the data form entry.
     * @return
     * 	the list of the possible values taken by the specified data form entry.<br/>
     * 	an empty list if no possible value has been retrieved.
     */
    public List<EntryValue> getEntryValues(String entryName);
    
}
