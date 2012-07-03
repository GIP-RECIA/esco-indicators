/**
 * 
 */
package org.esco.indicators.domain.beans.html;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Class representing a data row of a HTML table.
 * 
 * @since  2012/07/03
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class HtmlTableDataRow {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(HtmlTableDataRow.class);

    /** List of the data in the row */
    List<String> data;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link HtmlTableDataRow} class.
     */
    public HtmlTableDataRow() {
	super();
	this.data = new ArrayList<String>();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS

    /**
     * Gets the list of data of the row.
     * 
     * @return 
     * 		the data list of the row.
     */
    public List<String> getData() {
        return data;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    /**
     * Adds a data to the data list of this row.
     * 
     * @param data
     * 			The data to add.
     */
    @SuppressWarnings("hiding")
    public void addData( String data) {
	this.data.add(data);
    }





    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
