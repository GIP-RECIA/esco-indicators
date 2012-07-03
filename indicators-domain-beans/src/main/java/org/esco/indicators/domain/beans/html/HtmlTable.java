/**
 * 
 */
package org.esco.indicators.domain.beans.html;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.constants.html.HtmlConstants;
import org.postgresql.util.GT;
import org.springframework.util.StringUtils;

/**
 * Class representing an HTML table.<br/>
 * This calss provides all the functions to display its content under an HTML style.
 * 
 * @since  2012/07/03
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class HtmlTable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(HtmlTable.class);

    /** List of the headers of the table */
    private List<HtmlTableHeader> headers;
    
    /** List of the data rows of the table */
    private List<HtmlTableDataRow> dataRows;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link HtmlTable} class.
     */
    public HtmlTable() {
	super();
	this.headers = new ArrayList<HtmlTableHeader>();
	this.dataRows = new ArrayList<HtmlTableDataRow>();
    } 

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS

    /**
     * Gets the HTML code used to display the data of the table.
     * 
     * @return
     * 	the HTML code used to display the data of the table.
     */
    public String getHtmlData() {
	// Final HTML result
	StringBuffer htmlContent = new StringBuffer();
	
	// Appending of the data
	for (HtmlTableDataRow htmlTableDataRow : dataRows) {
	    htmlContent.append(dataRowToHtmlRow(htmlTableDataRow));
	}

	return htmlContent.toString();
    }
    
    /**
     * Gets the HTML code used to diplsay the header(s) of the table.
     * 
     * @return
     * 	the HTML code used to diplsay the header(s) of the table.
     */
    public String getHtmlHeader() {
	// Final HTML result
	StringBuffer htmlContent = new StringBuffer();
	
	// Appending of the top level headers
	htmlContent.append(headersToHtmlRow(headers));
	
	// Appending of the descendant level headers
	List<HtmlTableHeader> children = getChildren(headers);
	while(!children.isEmpty()) {
	    htmlContent.append(headersToHtmlRow(children));
	    children = getChildren(children);
	}
	
	return htmlContent.toString();
    }
    
    /**
     * Adds a data row to this table.
     * 
     * @param dataRow
     * 			The data row to add.
     */
    public void addDataRow(HtmlTableDataRow dataRow) {
	dataRows.add(dataRow);
    }
    
    /**
     * Adds a table header to this table.
     * 
     * @param header
     * 			The table header to add.
     */
    public void addHeader(HtmlTableHeader header) {
	headers.add(header);
    }
    
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    /**
     * Gets the children of the specified headers.
     * 
     * @param headerList
     * 			The headers that may have chidren.
     * 
     * @return
     * 	the children of the specified headers.<br/>
     * 	an empty list if no child has been retrieved.
     */
    private List<HtmlTableHeader> getChildren(List<HtmlTableHeader> headerList) {
	// Final result
	List<HtmlTableHeader> children = new ArrayList<HtmlTableHeader>();
	
	// Variable used to count the number of empty children
	int emptyChildren = 0;
	
	// Gets the children of all headers in the list
	for (HtmlTableHeader htmlTableHeader : headerList) {
	    List<HtmlTableHeader> headerChildren = htmlTableHeader.getChildren();
	    // If the current header has no children 
	    if(headerChildren.isEmpty()) {
		// Add an empty child to put in the header row below the current header
		children.add(new HtmlTableHeader(""));
		emptyChildren++;
	    }
	    children.addAll(headerChildren);
	}
	
	// If all the children retrieved are empty, it indicates that there is no more children
	// of the headers into the headerList
	if(children.size() == emptyChildren) {
	    return (new ArrayList<HtmlTableHeader>());
	}
	
	return children;
    }
    
    /**
     * Gets the HTML code to display the specified data row.
     * 
     * @param dataRow
     * 			The data row to display in a HTML content.
     * 
     * @return
     * 	the HTML used to display the data row.
     */
    private String dataRowToHtmlRow(HtmlTableDataRow dataRow) {
	// Final result
	StringBuffer htmlContent = new StringBuffer();
	
	// Opening the data row
	htmlContent.append(HtmlConstants.OPEN_TAG_ROW);
	
	// Appending of all data
	List<String> dataList = dataRow.getData();
	for (String data : dataList) {
	    // Opening of the data
	    htmlContent.append(HtmlConstants.OPEN_TAG_DATA);
	    // Insertion of the data content
	    htmlContent.append(data);
	    // Closing of the data
	    htmlContent.append(HtmlConstants.CLOSE_TAG_DATA);
	}
	
	// Closing the data row
	htmlContent.append(HtmlConstants.CLOSE_TAG_ROW);
	
	return htmlContent.toString();
    }
    
    /**
     * Gets the HTML code to display the specified headers.
     * 
     * @param headerList
     * 			The headers to display in a HTML content.
     * 
     * @return
     * 	the HTML used to display the headers.
     */
    private String headersToHtmlRow(List<HtmlTableHeader> headerList) {
	// Final result
	StringBuffer htmlContent = new StringBuffer();
	
	// Opening the header row
	htmlContent.append(HtmlConstants.OPEN_TAG_ROW);
	
	for (HtmlTableHeader htmlTableHeader : headerList) {
	    // Opening the header
	    htmlContent.append(HtmlConstants.OPEN_TAG_HEADER);
	    // Replacement of the colspan attribute
	    htmlContent = replacePattern(htmlContent, HtmlConstants.TAG_ATTR_PATTERN_COLSPAN, htmlTableHeader.getColSpan().toString());
	    // Insertion of the header name
	    htmlContent.append(htmlTableHeader.getName());
	    // Closing the header
	    htmlContent.append(HtmlConstants.CLOSE_TAG_HEADER);
	}
	
	// Closing the header row
	htmlContent.append(HtmlConstants.CLOSE_TAG_ROW);
	
	return htmlContent.toString();
    }
    
    /**
     * Replaces all the occurences of the specified <code>pattern</code> by the specified <code>value</code>
     * in the specified <code>inBuffer</code>.
     * 
     * @param inBuffer
     * 			The buffer containing the pattern to replace.
     * @param pattern
     * 			The pattern to replace.
     * @param value
     * 			The value used to replace the pattern.
     * 
     * @return
     * 	the string buffer having all the pattern occurences replaced by the value.
     */
    private StringBuffer replacePattern(StringBuffer inBuffer, String pattern, String value) {
	// Replacement of the attribute with its value
	String replacementStr = StringUtils.replace(inBuffer.toString(), pattern, value);
	
	return (new StringBuffer(replacementStr));
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
