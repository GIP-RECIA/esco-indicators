/**
 * 
 */
package org.esco.indicators.domain.beans.permission;

import java.util.ArrayList;
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
    /**
     * Default constructor of the {@link GenericFilter} class.
     */
    public GenericFilter() {
	super();
	propertiesNamesAndValues = new HashMap<String, List<String>>();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
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
     * Associates properties values to a property name.<br/>
     * If the property name was already associated to properties values, the new properties values will be added to the previous ones.<br/>
     * In other cases, the property name will be associated to the new properties values.
     * 
     * @param propertyName
     * 			The name of the property to filter.
     * @param propertyValues
     * 			The authorized property values to associate to the property name.
     */
    public void addPropertyValues(String propertyName, List<String> propertyValues) {
	// Checks if the property name is  already associated to values
	List<String> currentValues = getPropertyValues(propertyName);
	
	// If there is no value associated to the property name
	if(currentValues == null) {
	    currentValues = new ArrayList<String>();
	}
	
	// Add the new values to the current ones
	currentValues.addAll(propertyValues);
	
	// Associates the new values to the property name
	propertiesNamesAndValues.put(propertyName, currentValues);
    }


    
    /**
     * Indicates if the filter is empty.
     * 
     * @return
     * 	<code>true</code> if the filter is empty.<br/>
     * 	<code>false</code> in other cases.
     */
    public boolean isEmpty() {
	return propertiesNamesAndValues.isEmpty();
    }

    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((propertiesNamesAndValues == null) ? 0 : propertiesNamesAndValues.hashCode());
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	GenericFilter other = (GenericFilter) obj;
	if (propertiesNamesAndValues == null) {
	    if (other.propertiesNamesAndValues != null)
		return false;
	} else if (!propertiesNamesAndValues.equals(other.propertiesNamesAndValues))
	    return false;
	return true;
    }
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
