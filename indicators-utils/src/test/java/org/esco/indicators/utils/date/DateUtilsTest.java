/**
 * 
 */
package org.esco.indicators.utils.date;

import static org.junit.Assert.*;

import java.sql.Date;

import junit.framework.Assert;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.junit.Test;

/**
 * @since  
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class DateUtilsTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Test method for {@link org.esco.indicators.utils.date.DateUtils#toSqlDate(java.lang.String)}.
     */
    @Test
    public void testToSqlDate() {
	// Expected result
	Integer year = 2012;
	Integer month = 05;
	Integer day = 28;
	
	DateTime dateTime = new DateTime().withYear(year).withMonthOfYear(month).withDayOfMonth(day);
	DateMidnight dateMidnight = dateTime.toDateMidnight();
	Date expected = new Date(dateMidnight.toGregorianCalendar().getTimeInMillis());
	
	// Actual result
	String yearStr = year.toString();
	String monthStr = month.toString();
	String dayStr = day.toString();
	String dateStr = yearStr + "-" + monthStr + "-" + dayStr;
	Date actual =  DateUtils.toSqlDate(dateStr);
	
	// Test
	Assert.assertEquals(expected.getTime(), actual.getTime());
    }

    /**
     * Test method for {@link org.esco.indicators.utils.date.DateUtils#getFirstMonthDay(Integer, Integer)}.
     * 
     * Tests if the method returns a date corresponding to the first day of the specified month number and year.
     */
    @Test
    public void testGetFirstMonthDay() {
	// Expected result
	Integer year = 2012;
	Integer month = 05;
	Integer day = 01;
	
	DateTime dateTime = new DateTime().withYear(year).withMonthOfYear(month).withDayOfMonth(day);
	DateMidnight dateMidnight = dateTime.toDateMidnight();
	Date expected = new Date(dateMidnight.toGregorianCalendar().getTimeInMillis());
	
	// Actual result
	Date actual = DateUtils.getFirstMonthDay(month, year);
	
	// Test
	Assert.assertEquals(expected.getTime(), actual.getTime());
    }
    
    /**
     * Test method for {@link org.esco.indicators.utils.date.DateUtils#getFirstWeekDay(java.lang.Integer, java.lang.Integer)}.
     * 
     * Tests if the method returns a date corresponding to the first day of the specified week number and year.
     */
    @Test
    public void testGetFirstWeekDay() {
	// Expected result
	Integer year = 2012;
	Integer month = 05;
	Integer day = 28;
	
	DateTime dateTime = new DateTime().withYear(year).withMonthOfYear(month).withDayOfMonth(day);
	DateMidnight dateMidnight = dateTime.toDateMidnight();
	Date expected = new Date(dateMidnight.toGregorianCalendar().getTimeInMillis());
	
	// Actual result
	Integer week = 22; // Week number having as first day : 2012-05-28
	Date actual = DateUtils.getFirstWeekDay(week, year);
	
	// Test
	Assert.assertEquals(expected.getTime(), actual.getTime());
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
