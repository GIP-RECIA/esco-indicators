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
     * Gets the JSP keys of the entry values that are disabled by default in the user view.
     * 
     * @return
     * 	the JSP keys of the entry values that are disabled by default in the user view.
     */
    public List<String> getJspKeysDisabledByDefault();
    
    /**
     * Gets the JSP keys of the entry values that have to be disabled regarding to the JSP keys
     * of the elements already checked.
     * 
     * @param checkedJspKeys
     * 			The JSP keys of the elements already checked.
     * 
     * @return
     * 	The JSP keys of the entry values that have to be disabled.
     */
    public List<String> getJspKeysToDisable(List<String> checkedJspKeys);
    
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
    
    
    /**
     * Indicates if a JSP key is known, or not, in the application.
     * 
     * @param jspKey
     * 			The JSP key to test.
     * @return
     * 	<code>true</code> if the application knows the JSP key.<br/>
     * 	<code>false</code> in other cases.
     */
    public boolean isKnown(String jspKey);
}
