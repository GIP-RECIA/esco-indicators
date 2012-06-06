/**
 * 
 */
package org.esco.indicators.utils.date;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;



/**
 * Class providing date translation functions between different formats.
 * 
 * @since 2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class DateUtils {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(DateUtils.class);

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
    
    /**
     * Gets the SQL date corresponding to the specified <code>month</code> of the <code>year</code>.<br/>
     * The constructed date corresponds to the first day of the specified <code>month</code> of the specified <code>year</code>.
     * 
     * @param month
     * 			The month number.
     * @param year
     * 			The year.
     * @return
     * 	the SQL date corresponding to the first day of the specified <code>month</code> of the specified <code>year</code>.
     */
    public static Date getFirstMonthDay(Integer month, Integer year) {
	// Constructs the date time for the specified year and month
	DateTime dateTime = new DateTime().withYear(year).withMonthOfYear(month);

	// Sets the day to the first day of the month
	dateTime = dateTime.withDayOfMonth(1);
	
	// Constructs the equivalent SQL date
	DateMidnight dateMidnight = dateTime.toDateMidnight();
	long time = dateMidnight.toGregorianCalendar().getTimeInMillis();
	Date date = new Date(time);
	
	return date;
    }
    
    /**
     * Gets the SQL date corresponding to the specified <code>week</code> of the <code>year</code>.<br/>
     * The constructed date corresponds to the first day of the specified <code>week</code> of the specified <code>year</code>.
     * 
     * @param week
     * 			The week number.
     * @param year
     * 			The year.
     * @return
     * 	the SQL date corresponding to the first day of the <code>week</code> of the <code>year</code>.
     */
    public static Date getFirstWeekDay(Integer week, Integer year) {
	// Constructs the date time for the specified year and week
	DateTime dateTime = new DateTime().withWeekyear(year).withWeekOfWeekyear(week);

	// Sets the day to the first day of the week
	dateTime = dateTime.withDayOfWeek(1);
	
	// Constructs the equivalent SQL date
	DateMidnight dateMidnight = dateTime.toDateMidnight();
	long time = dateMidnight.toGregorianCalendar().getTimeInMillis();
	Date date = new Date(time);
	
	return date;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
