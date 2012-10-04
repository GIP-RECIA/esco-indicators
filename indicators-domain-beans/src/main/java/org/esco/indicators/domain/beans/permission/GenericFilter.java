/**
 * 
 */
package org.esco.indicators.domain.beans.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.reflexive.ReflexiveObject;

/**
 * Class representing a generic filter.<br/>
 *  A {@link GenericFilter} is used to be applied on an {@link ReflexiveObject} in order to know if this object is validated by the filter, or not.
 *  
 * @since  2012/10/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class GenericFilter {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(GenericFilter.class);

    /** The properties names and values used to filter the {@link ReflexiveObject}s */
    private HashMap<String, List<String>> propertiesNamesAndValues;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the properties names and values which are used to filter {@link ReflexiveObject}s.
     * 
     * @param propertiesNamesAndValues 
     * 				the properties names, and their associated values, to set.
     */
    public void setPropertiesNamesAndValues(HashMap<String, List<String>> propertiesNamesAndValues) {
        this.propertiesNamesAndValues = propertiesNamesAndValues;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    

    /**
     * Gets all the properties names that can be used to filter.
     * 
     * @return
     * 	the available properties names which can be used to filter.
     */
    public Set<String> getPropertiesNames() {
	return propertiesNamesAndValues.keySet();
    }
    
    /**
     * Gets the properties values, associated to the property name, which are used to filter.<br/>
     * 
     * @param propertyName
     * 			The name of the property to filter.
     * 
     * @return
     * 	the properties values associate to the property name.
     */
    public List<String> getPropertyValues(String propertyName) {
	return propertiesNamesAndValues.get(propertyName);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
