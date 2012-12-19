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
     * Gets the JSP keys of the entry values that are enabled by default in the user view.
     * 
     * @return
     * 	the JSP keys of the entry values that are enabled by default in the user view.
     */
    public List<String> getJspKeysEnabledByDefault();
    
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
     * Gets the JSP keys of the entry values that have to be enabled regarding to the JSP keys
     * of the elements already checked.
     * 
     * @param checkedJspKeys
     * 			The JSP keys of the elements already checked.
     * 
     * @return
     * 	The JSP keys of the entry values that have to be enabled.
     */
    public List<String> getJspKeysToEnable(List<String> checkedJspKeys);
    
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
     * Gets the name of the entry associated to the jsp key.
     * 
     * @param jspKey
     * 			The jsp key associated to the entry.
     * @return
     * 	the name of the entry associated to the jsp key.<br/>
     * 	<code>null</code> of no entry name has been retrieved.
     */
    public String getEntryName(String jspKey);
    
    /**
     * Gets the county number to filter when the specified JSP key is checked in the user view.
     * 
     * @param jspKey
     * 			The JSP key to test.
     * @return
     * 	the county number to filter.<br/>
     * 	<code>null</code> if no county number has to be filtered.
     */
    public String getCountyNumberToFilter(String jspKey);
    
    /**
     * Gets the county numbers to filter when the specified JSP key is checked in the user view.
     * 
     * @param jspKey
     * 			The JSP key to test.
     * @return
     * 	the county numbers to filter.<br/>
     * 	<code>null</code> if no county number has to be filtered.
     */
    public  List<String> getCountyNumbersToFilter(String jspKey);
    
    /**
     * Gets the county numbers to filter when the specified JSP keys are checked in the user view.
     * 
     * @param checkedJspKeys
     * 			The JSP keys that are checked in the user view.
     * 
     * @return
     * 	the list of the county numbers to filter.<br/>
     * 	an empty list if no county number has to be filtered.
     */
    public List<String> getCountyNumbersToFilter(List<String> checkedJspKeys);    
    
    /**
     * Gets the establishment type to filter when the specified JSP key is checked in the user view.
     * 
     * @param jspKey
     * 			The JSP key to test.
     * @return
     * 	the establishement type to filter.<br/>
     * 	<code>null</code> if no establishment type has to be filtered.
     */
    public String getEstablishmentTypeToFilter(String jspKey);
    
    /**
     * Gets the establishments types to filter when the specified JSP keys are checked in the user view.
     * 
     * @param checkedJspKeys
     * 			The JSP keys that are checked in the user view.
     * 
     * @return
     * 	the list of the establishment types to filter.<br/>
     * 	an empty list if no establishment type has to be filtered.
     */
    public List<String> getEstablishmentsTypesToFilter(List<String> checkedJspKeys);    
    
    /**
     * Gets the service to filter when the specified JSP key is checked in the user view.
     * 
     * @param jspKey
     * 			The JSP keys that is checked in the user view.
     * 
     * @return
     * 	the service to filter.<br/>
     * 	<code>null</code> if no service has to be filtered.
     */
    public String getServiceToFilter(String jspKey);
    
    /**
     * Gets the services to filter when the specified JSP keys are checked in the user view.
     * 
     * @param checkedJspKeys
     * 			The JSP keys that are checked in the user view.
     * 
     * @return
     * 	the list of the services to filter.<br/>
     * 	an empty list if no service has to be filtered.
     */
    public List<String> getServicesToFilter(List<String> checkedJspKeys);
    
    /**
     * Gets the user profile to filter when the specified JSP key is checked in the user view.
     * 
     * @param jspKey
     * 			The JSP key to test.
     * @return
     * 	the user profile to filter.<br/>
     * 	<code>null</code> if no user profile has to be filtered.
     */
    public String getUserProfileToFilter(String jspKey);
    
    /**
     * Gets the users profiles to filter when the specified JSP keys are checked in the user view.
     * 
     * @param checkedJspKeys
     * 			The JSP keys that are checked in the user view.
     * 
     * @return
     * 	the list of the users profiles to filter.<br/>
     * 	an empty list if no user profile has to be filtered.
     */
    public List<String> getUsersProfilesToFilter(List<String> checkedJspKeys);    
    
    /**
     * Gets the i18n key associated to the specified JSP key.
     * 
     * @param jspKey
     * 			The JSP key associated with the searched i18n key.
     * 
     * @return
     * 	the i18n key associated to the specified JSP key.<br/>
     * 	an empty string if no i18n key has been retrieved.
     */
    public String getI18nKey(String jspKey);
    
    /**
     * Indicates if a JSP key has an influence on the establishments list when its state changes in the user view.<br/>
     * When a JSP key (having an influence on the establishements list) changes in the user view, the establishements list has to be updated.
     * 
     * @param jspKey
     * 			The JSP key to test.
     * 
     * @return
     * 	<code>true</code> if the JSP key has an influence on the establishements list.<br/>
     * 	<code>false</code> in other cases.
     */
    public boolean hasInfluenceOnEstablishmentsList(String jspKey);
    
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
