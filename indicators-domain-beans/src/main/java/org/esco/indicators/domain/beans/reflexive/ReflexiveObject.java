/**
 * 
 */
package org.esco.indicators.domain.beans.reflexive;

/**
 * Interface representing an object which is able to provide informations on its properties.
 * 
 * @since  2012/10/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface ReflexiveObject {
    /**
     * Retrieves the value of a property of this object by his name.
     * 
     * @param propertyName
     * 			The name of the object property.
     * 
     * @return
     * 	the value associated to the property if the property has been found.<br/>
     * 	<code>null</code> in other cases.
     */
    public Object getPropertyValue(String propertyName);
}
