/**
 * 
 */
package org.esco.indicators.domain.beans.html;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Class representing the header of a HTML table.
 * 
 * @since  2012/07/03
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class HtmlTableHeader {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(HtmlTableHeader.class);

    /** Name of the header */
    private String name;
    
    /** List of the childern headers */
    private List<HtmlTableHeader> children;

    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of {@link HtmlTableHeader} class.
     * 
     * @param name
     * 			The name of the header.
     */
    public HtmlTableHeader(String name) {
	super();
	this.name = name;
	this.children = new ArrayList<HtmlTableHeader>();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    
    /**
     * Gets the children headers.
     * 
     * @return 
     * 	the children of the headers.
     */
    public List<HtmlTableHeader> getChildren() {
        return children;
    }
    
    /**
     * Gets the number of columns span by the header.
     * 
     * @return
     * 	the number of columns span by the header.
     */
    public Integer getColSpan() {
	int numChildren = children.size();
	return ( numChildren == 0 ? 1 : numChildren );
    }
    
    /**
     * Gets the name of the header.
     * 
     * @return 
     * 	the name of the header.
     */
    public String getName() {
        return name;
    }


    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Adds an HTML table header as a child to this HTML table header.
     * 
     * @param childHeader
     * 			The HTML table header child to add.
     */
    public void addChild(HtmlTableHeader childHeader) {
	children.add(childHeader);
    }


    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
