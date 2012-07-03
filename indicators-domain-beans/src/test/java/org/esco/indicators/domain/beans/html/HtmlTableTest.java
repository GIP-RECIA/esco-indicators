/**
 * 
 */
package org.esco.indicators.domain.beans.html;

import static org.junit.Assert.fail;

import javax.swing.text.html.HTML;

import junit.framework.Assert;

import org.esco.indicators.domain.beans.constants.html.HtmlConstants;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 * Tests on the {@link HtmlTable} class.
 * 
 * @since  2012/07/03
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class HtmlTableTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** The {@link HtmlTable} under tests */
    private HtmlTable table;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Initializes the HTML table for running the tests.
     * 
     */
    @Before
    public void setUp() {
	table = new HtmlTable();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Test method for {@link org.esco.indicators.domain.beans.html.HtmlTable#getHtmlData()}.
     */
    @Test
    public void testGetHtmlData() {
	// Used data
	String data1 = "Data 1";
	String data2 = "Data 2";
	String data3 = "Data 3";
	
	String data12 = "Data 12";
	String data22 = "Data 22";
	
	// Expected result
	String expected =
		HtmlConstants.OPEN_TAG_ROW
			+ HtmlConstants.OPEN_TAG_DATA
				+ data1
			+ HtmlConstants.CLOSE_TAG_DATA
    			+ HtmlConstants.OPEN_TAG_DATA
        			+ data2
        		+ HtmlConstants.CLOSE_TAG_DATA
    			+ HtmlConstants.OPEN_TAG_DATA
    				+ data3
    			+ HtmlConstants.CLOSE_TAG_DATA
		+ HtmlConstants.CLOSE_TAG_ROW
		+ HtmlConstants.OPEN_TAG_ROW
		+ HtmlConstants.OPEN_TAG_DATA
			+ data12
		+ HtmlConstants.CLOSE_TAG_DATA
			+ HtmlConstants.OPEN_TAG_DATA
			+ data22
		+ HtmlConstants.CLOSE_TAG_DATA
	+ HtmlConstants.CLOSE_TAG_ROW;
	
	// Actual result
	HtmlTableDataRow row1 = new HtmlTableDataRow();
	row1.addData(data1);
	row1.addData(data2);
	row1.addData(data3);
	
	HtmlTableDataRow row2 = new HtmlTableDataRow();
	row2.addData(data12);
	row2.addData(data22);
	
	table.addDataRow(row1);
	table.addDataRow(row2);
	
	String actual = table.getHtmlData();
	
	// Test
	Assert.assertEquals(expected, actual);
    }

    /**
     * Test method for {@link org.esco.indicators.domain.beans.html.HtmlTable#getHtmlHeader()}.
     */
    @Test
    public void testGetHtmlHeader() {
	// Used data
	String headerName1 = "Header name 1";
	String headerName2 = "Header name 2";
	
	String headerName21 = "Header name 21";
	String headerName22 = "Header name 22";
	String headerName23 = "Header name 23";
	
	// Expected result
	String expected = 
		HtmlConstants.OPEN_TAG_ROW 
        		+ HtmlConstants.OPEN_TAG_HEADER
        			+ headerName1
        		+ HtmlConstants.CLOSE_TAG_HEADER;
	expected = StringUtils.replace(expected, HtmlConstants.TAG_ATTR_PATTERN_COLSPAN, "1");
	
	expected +=
        		HtmlConstants.OPEN_TAG_HEADER
        			+ headerName2
    			+ HtmlConstants.CLOSE_TAG_HEADER
		+ HtmlConstants.CLOSE_TAG_ROW;
	expected = StringUtils.replace(expected, HtmlConstants.TAG_ATTR_PATTERN_COLSPAN, "3");
	
	expected +=
		HtmlConstants.OPEN_TAG_ROW
        		+ HtmlConstants.OPEN_TAG_HEADER
        			+ ""
        		+ HtmlConstants.CLOSE_TAG_HEADER;
        	expected = StringUtils.replace(expected, HtmlConstants.TAG_ATTR_PATTERN_COLSPAN, "1");
        	
    	expected +=
            		HtmlConstants.OPEN_TAG_HEADER
            			+ headerName21
            		+ HtmlConstants.CLOSE_TAG_HEADER;
            	expected = StringUtils.replace(expected, HtmlConstants.TAG_ATTR_PATTERN_COLSPAN, "1");        	
            	
    	expected +=
            		HtmlConstants.OPEN_TAG_HEADER
            			+ headerName22
            		+ HtmlConstants.CLOSE_TAG_HEADER;
            	expected = StringUtils.replace(expected, HtmlConstants.TAG_ATTR_PATTERN_COLSPAN, "1");        	          	
            	
    	expected +=
            		HtmlConstants.OPEN_TAG_HEADER
            			+ headerName23
            		+ HtmlConstants.CLOSE_TAG_HEADER
    		+ HtmlConstants.CLOSE_TAG_ROW;
    	expected = StringUtils.replace(expected, HtmlConstants.TAG_ATTR_PATTERN_COLSPAN, "1");
    	
    	
    	// Actual result
    	HtmlTableHeader tableHeader21 = new HtmlTableHeader(headerName21);
    	HtmlTableHeader tableHeader22 = new HtmlTableHeader(headerName22);
    	HtmlTableHeader tableHeader23 = new HtmlTableHeader(headerName23);
    	
    	HtmlTableHeader tableHeader1 = new HtmlTableHeader(headerName1);
    	HtmlTableHeader tableHeader2 = new HtmlTableHeader(headerName2);
    	tableHeader2.addChild(tableHeader21);
    	tableHeader2.addChild(tableHeader22);
    	tableHeader2.addChild(tableHeader23);
    	
    	table.addHeader(tableHeader1);
    	table.addHeader(tableHeader2);
    	
    	String actual = table.getHtmlHeader();
    	
    	// Test
    	Assert.assertEquals(expected, actual);
    }


    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
