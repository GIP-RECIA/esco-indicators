/**
 * 
 */
package org.esco.indicators.domain.beans.permission;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
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
public class GenericFilter implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Auto generated UID */
    private static final long serialVersionUID = -3652294729160262603L;
    
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(GenericFilter.class);

    /** The properties names and values used to filter the {@link ReflexiveObject}s */
    private HashMap<String, Set<String>> propertiesNamesAndValues;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link GenericFilter} class.
     */
    public GenericFilter() {
	super();
	propertiesNamesAndValues = new HashMap<String, Set<String>>();
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
     * Gets all the properties names and their associated values.
     * 
     * @return 
     * 	the properties names and values.
     */
    public HashMap<String, Set<String>> getPropertiesNamesAndValues() {
        return propertiesNamesAndValues;
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
    public Set<String> getPropertyValues(String propertyName) {
	return propertiesNamesAndValues.get(propertyName);
    }
    
    /**
     * Sets the properties names and values which are used to filter {@link ReflexiveObject}s.
     * 
     * @param propertiesNamesAndValues 
     * 				the properties names, and their associated values, to set.
     */
    public void setPropertiesNamesAndValues(HashMap<String, Set<String>> propertiesNamesAndValues) {
        this.propertiesNamesAndValues = propertiesNamesAndValues;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    /**
     * Associates properties values to a property name.<br/>
     * If the property name was already associated to properties values, the new properties values will be added to the previous ones.<br/>
     * In other cases, the property name will be associated to the new properties values.</br>
     * 
     * 
     * @param propertyName
     * 			The name of the property to filter.
     * @param propertyValues
     * 			The property values to associate to the property name.
     */
    public void addPropertyValues(String propertyName, Set<String> propertyValues) {
	// Checks if the property name is  already associated to values
	Set<String> currentValues = getPropertyValues(propertyName);
	
	// If there is no value associated to the property name
	if(currentValues == null) {
	    currentValues = new HashSet<String>();
	}
	
	// Add the new values to the current ones
	currentValues.addAll(propertyValues);
	
	// Associates the new values to the property name
	propertiesNamesAndValues.put(propertyName, currentValues);
    }

    /**
     * Fuses this filter with the given one.<br/>
     * All the properties names and values of the other filter are inserted into this filter.<br/>
     * If some properties names of the other filter are already present into this filter, then the properties values of the two filters are combined into on set of properties values.
     * 
     * @param anotherFilter
     * 			The filter to fuse with this filter.
     */
    public void fuseWith(GenericFilter anotherFilter) {
	// Gets the properties names of the other filter
	Set<String> propertiesNames = anotherFilter.getPropertiesNames();
	for (String propertyName : propertiesNames) {
	    // Gets the associated values
	    Set<String> propertyValues = anotherFilter.getPropertyValues(propertyName);
	    this.addPropertyValues(propertyName, propertyValues);
	}
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GenericFilter [propertiesNamesAndValues=" + propertiesNamesAndValues + "]";
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
