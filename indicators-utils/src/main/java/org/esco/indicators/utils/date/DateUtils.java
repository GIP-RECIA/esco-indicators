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
import org.esco.indicators.utils.classes.IntegerPair;
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
     * Gets the number of weeks in the specified <code>year</code>.
     * 
     * @param year
     * 			The year.
     * 
     * @return
     * 	the number of weeks in the year.
     */
    public static Integer getNumWeeksInYear(Integer year) {
	DateTime dateTime = new DateTime().withYear(year);
	return dateTime.weekOfWeekyear().getMaximumValue();
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
    
    /**
     * Splits the specified period into a list of pairs (month - year).<br/>
     * Each pair contains :
     * <ul>
     * 	<li>First value : a month number</li>
     * 	<li>Second value : a year</li>
     * </ul>
     * 
     * For instance :<br/>
     * 	With the following parameters :
     * 	<ul>
     * 		<li><code>startMonth</code> : 03 </li>
     * 		<li><code>startYear</code> : 2012 </li>
     * 		<li><code>endMonth</code> : 04 </li>
     * 		<li><code>endYear</code> : 2013 </li>
     * 	</ul>
     *  The return will be the following list of pairs : <br/>
     *  [ (03 - 2012) (04 - 2012) (05 - 2012) ... (03 - 2013) (04 - 2013) ]
     * 
     * @param startMonth
     * 			The number of the start month (in the start year).
     * @param startYear
     * 			The start year.
     * @param endMonth
     * 			The number of the end month (in the end year).
     * @param endYear
     * 			The end year.
     * 
     * @return
     * 	the list of months contained between the start and the end.<br/>
     * 	these months are put into pairs, each pair containing the month number and the year.
     */
    public static List<IntegerPair>  splitMonths(Integer startMonth, Integer startYear, Integer endMonth, Integer endYear) {
	// Final result
	List<IntegerPair> monthsAndYears = new ArrayList<IntegerPair>();

	// Gets the number of years between the start and the end
	Integer diffYears = endYear - startYear;
	Integer minMonth = startMonth;
	Integer maxMonth;
	
	// If the period is in the same year
	if(diffYears == 0) {
	    maxMonth = endMonth;
	} else {
	    maxMonth = 12;
	}
	
	// Creation of the pairs (month - year)
    	for(int currentYear = startYear; currentYear <= endYear; currentYear++) {
        	for(int currentMonth = minMonth; currentMonth <= maxMonth; currentMonth++ ) {
        	    IntegerPair monthAndYear = new IntegerPair(currentMonth, currentYear);
        	    monthsAndYears.add(monthAndYear);
        	}
        	// Reset of the minimum / maximum week
        	minMonth = 1;
        	maxMonth = (currentYear + 1 == endYear) ? endMonth :  12;
	}

    	// Debug informations
	LOGGER.debug("The period starting with [month : " + startMonth + ", year : " + startYear
		+ "] and ending with [month : " + endMonth + ", year :" + endYear + "] has been split into : "
		+ monthsAndYears);
	
    	return monthsAndYears;
    }
    
    /**
     * Splits the specified period into a list of pairs (week - year).<br/>
     * Each pair contains :
     * <ul>
     * 	<li>First value : a week number</li>
     * 	<li>Second value : a year</li>
     * </ul>
     * 
     * For instance :<br/>
     * 	With the following parameters :
     * 	<ul>
     * 		<li><code>startWeek</code> : 21 </li>
     * 		<li><code>startYear</code> : 2012 </li>
     * 		<li><code>endWeek</code> : 15 </li>
     * 		<li><code>endYear</code> : 2013 </li>
     * 	</ul>
     *  The return will be the following list of pairs : <br/>
     *  [ (21 - 2012) (22 - 2012) (23 - 2012) ... (14 - 2013) (15 - 2013) ]
     * 
     * @param startWeek
     * 			The number of the start week (in the start year).
     * @param startYear
     * 			The start year.
     * @param endWeek
     * 			The number of the end week (in the end year).
     * @param endYear
     * 			The end year.
     * 
     * @return
     * 	the list of weeks contained between the start and the end.<br/>
     * 	these weeks are put into pairs, each pair containing the week number and the year.
     */
    public static List<IntegerPair>  splitWeeks(Integer startWeek, Integer startYear, Integer endWeek, Integer endYear) {
	// Final result
	List<IntegerPair> weeksAndYears = new ArrayList<IntegerPair>();

	// Gets the number of years between the start and the end
	Integer diffYears = endYear - startYear;
	Integer minWeek = startWeek;
	Integer maxWeek;
	
	// If the period is in the same year
	if(diffYears == 0) {
	    maxWeek = endWeek;
	} else {
	    maxWeek = DateUtils.getNumWeeksInYear(startYear);
	}
	
	// Creation of the pairs (week - year)
    	for(int currentYear = startYear; currentYear <= endYear; currentYear++) {
        	for(int currentWeek = minWeek; currentWeek <= maxWeek; currentWeek++ ) {
        	    IntegerPair weekAndYear = new IntegerPair(currentWeek, currentYear);
        	    weeksAndYears.add(weekAndYear);
        	}
        	// Reset of the minimum / maximum week
        	minWeek = 1;
        	maxWeek = (currentYear + 1 == endYear) ? endWeek :  DateUtils.getNumWeeksInYear(currentYear + 1);
	}

    	// Debug informations
	LOGGER.debug("The period starting with [week : " + startWeek + ", year : " + startYear
		+ "] and ending with [week : " + endWeek + ", year :" + endYear + "] has been split into : "
		+ weeksAndYears);
    	
    	return weeksAndYears;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
