/**
 * 
 */
package org.esco.indicators.utils.date;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Months;



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

    /** Date format */
    public static final String DATE_FORMAT_FR = "dd/MM/yyyy";
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    /**
     * Adds the specified number of days to the specified date.
     * 
     * @param initialDate
     * 			Date to increment.
     * @param days
     * 			Number of days to addition to the initial date.
     * 
     * @return
     * 	the inital date incremented by the number of days.
     */
    public static Date addDays(Date initialDate, Integer days) {
	// Get the date time
	DateTime dateTime = new DateTime(initialDate.getTime());
	
	// Addition of the days
	dateTime = dateTime.plusDays(days);
	
	// Conversion to SQL date
	Date sqlDate = new Date(dateTime.getMillis());
	
	return sqlDate;
    }
    
    /**
     * Converts a string representing a date to a SQL date.
     * 
     * @param date
     * 			The string date in the specified format
     * @param format 
     * @return
     * 	the SQL date corresponding to the specified <code>date</code>.<br/>
     * 	<code>null</code> if the date could not been converted.
     */
    public static Date toSqlDate(String date, String format) {
	// Format of the specified date
	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	Date day = null;
	
	// Try to get the SQL date
	try {
	    java.util.Date parsedDate = dateFormat.parse(date);
	    day = new Date(parsedDate.getTime());
	} catch (ParseException e) {
	    LOGGER.error("The specified date : [" + date + "] could not have been translated into a SQL"
	    		+ " date using the date format : [" + format + "]");
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
    
    /**
     * Gets the SQL date corresponding to the specified <code>month</code> of the <code>year</code>.<br/>
     * The constructed date corresponds to the last day of the specified <code>month</code> of the specified <code>year</code>.
     * 
     * @param month
     * 			The month number.
     * @param year
     * 			The year.
     * @return
     * 	the SQL date corresponding to the last day of the specified <code>month</code> of the specified <code>year</code>.
     */
    public static Date getLastMonthDay(Integer month, Integer year) {
	// Constructs the date time for the specified year and month
	DateTime dateTime = new DateTime().withYear(year).withMonthOfYear(month);

	// Sets the day to the first day of the month
	dateTime = dateTime.dayOfMonth().withMaximumValue();
	
	// Constructs the equivalent SQL date
	DateMidnight dateMidnight = dateTime.toDateMidnight();
	long time = dateMidnight.toGregorianCalendar().getTimeInMillis();
	Date date = new Date(time);
	
	return date;
    }
    
    /**
     * Returns the month (in the year) of a date.
     * 
     * @param date
     * 			The date containing the month.
     * 
     * @return
     * 	the month of the specified date.
     */
    public static Integer getMonthOfYear(java.util.Date date) {
	DateTime dateTime = new DateTime(date);
	return dateTime.getMonthOfYear();
    }
    
    /**
     * Returns the year of a date.
     * 
     * @param date
     * 			The date containing the year.
     * 
     * @return
     * 	the year of the specified date.
     */
    public static Integer getYear(java.util.Date date) {
	DateTime dateTime = new DateTime(date);
	return dateTime.getYear();
    }
    
    /**
     * Returns the week (in the year) of a date.
     * 
     * @param date
     * 			The date containing the week.
     * 
     * @return
     * 	the week of the specified date.
     */
    public static Integer getWeekOfYear(java.util.Date date) {
	DateTime dateTime = new DateTime(date);
	return dateTime.getWeekOfWeekyear();
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
