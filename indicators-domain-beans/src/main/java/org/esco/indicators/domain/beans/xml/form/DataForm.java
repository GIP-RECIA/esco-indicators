/**
 * 
 */
package org.esco.indicators.domain.beans.xml.form;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

/**
 * Class providing the data forms of the application.
 * 
 * @since  2012/06/18
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@XmlRootElement(name = "data-form")
public class DataForm {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(DataForm.class);

    /** List of the entries of the form */
    private List<EntryForm> entriesForm;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link DataForm} class.
     */
    private DataForm() {
	super();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    
    /**
     * Gets the entries of the form
     * 
     * @return
     * 	The entries of the form.
     */
    @XmlElement(name = "entry-form")
    public List<EntryForm> getEntriesForm() {
	return entriesForm;
    }

    /**
     * Sets the entries of the form.
     * 
     * @param entriesForm
     * 			 The entries to set.
     */
    public void setEntriesForm(List<EntryForm> entriesForm) {
        this.entriesForm = entriesForm;
    }
    

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((entriesForm == null) ? 0 : entriesForm.hashCode());
	return result;
    }

    
    //------------------------------------------------------------------------------ PUBLIC METHODS
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
	DataForm other = (DataForm) obj;
	if (entriesForm == null) {
	    if (other.entriesForm != null)
		return false;
	} else if (!entriesForm.equals(other.entriesForm))
	    return false;
	return true;
    }
    

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
