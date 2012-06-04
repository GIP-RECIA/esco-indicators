/**
 * 
 */
package org.esco.indicators.utils.date;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;



/**
 * Class providing date translation functions between different formats.
 * 
 * @since 2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class DateTranslator {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(DateTranslator.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Converts a string representing a date (yyyy-MM-dd) to a SQL date.
     * 
     * @param date
     * 			The string date in the format yyyy-MM-dd
     * @return
     * 	the SQL date corresponding to the specified <code>date</code>.<br/>
     * 	<code>null</code> if the date could not been converted.
     */
    public static Date toSqlDate(String date) {
	// Format of the specified date
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date day = null;
	
	// Try to get the SQL date
	try {
	    java.util.Date parsedDate = dateFormat.parse(date);
	    day = new Date(parsedDate.getTime());
	} catch (ParseException e) {
	    LOGGER.error("The specified date : [" + date + "] could not have been translated into a SQL"
	    		+ " date using the date format (yyyy-MM-dd)");
	}
	
	return day;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
