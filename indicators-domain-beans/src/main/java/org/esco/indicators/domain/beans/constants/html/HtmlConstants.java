/**
 * 
 */
package org.esco.indicators.domain.beans.constants.html;

/**
 * Constants concerning the HTML content.
 * 
 * @since 2012/07/03
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class HtmlConstants {

    ///////////////////////////////////////////////////////
    // Tags attributes
    ///////////////////////////////////////////////////////
    
    /** Colspan */
    public static final String TAG_ATTR_PATTERN_COLSPAN = "%COL_SPAN%";
    
    ///////////////////////////////////////////////////////
    // Opening tags
    ///////////////////////////////////////////////////////
    
    /** Data */
    public static final String OPEN_TAG_DATA = "<td>";

    /** Header */
    public static final String OPEN_TAG_HEADER = "<th colspan=\"" + TAG_ATTR_PATTERN_COLSPAN + "\">";
    
    /** Row */
    public static final String OPEN_TAG_ROW = "<tr>";
    
    /** Table */
    public final static String OPEN_TAG_TABLE = "<table>";
	   

    ///////////////////////////////////////////////////////
    // Closing  tags
    ///////////////////////////////////////////////////////    
    
    /** Data */
    public static final String CLOSE_TAG_DATA = "</td>";
    
    /** Header */
    public static final String CLOSE_TAG_HEADER = "</th>";
    
    /** Row */
    public static final String CLOSE_TAG_ROW = "</tr>";
    
    /** Table */
    public final static String CLOSE_TAG_TABLE = "</table>";
    
}
