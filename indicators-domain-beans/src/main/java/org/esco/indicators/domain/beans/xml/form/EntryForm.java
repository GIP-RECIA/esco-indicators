/**
 * 
 */
package org.esco.indicators.domain.beans.xml.form;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

/**
 * Class representing the entry of the forms of the application.
 * 
 * @since 2012/06/18 
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@XmlType(name = "EntryFormType")
public class EntryForm {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EntryForm.class);

    /** Identifier of the entry */
    private String name;
    
    /** List of the possible values of the entry form */
    private List<EntryValue> entryValues;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link EntryForm} class.
     */
    public EntryForm() {
	super();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    
    /**
     * Gets the possible values of the entry field.
     * 
     * @return
     * 	the possible values of the entry.
     */
    @XmlElement(name = "entry-value")
    public List<EntryValue> getEntryValues() {
	return entryValues;
    }
    
    

    /**
     * Sets the possible values of the entry field.
     * 
     * @param entryValues 
     * 			The  possible values of the entry to set.
     */
    public void setEntryValues(List<EntryValue> entryValues) {
        this.entryValues = entryValues;
    }

    /**
     * Gets the name of the entry.
     * 
     * @return 
     * 	the entry name.
     */
    @XmlAttribute(name = "name")
    @XmlID
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the entry.
     * 
     * @param name 
     * 	the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }


    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((entryValues == null) ? 0 : entryValues.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	EntryForm other = (EntryForm) obj;
	if (entryValues == null) {
	    if (other.entryValues != null)
		return false;
	} else if (!entryValues.equals(other.entryValues))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }
    

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
