/**
 * 
 */
package org.esco.indicators.services.statistic;


import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.statistic.EstablishmentVisitStatistic;
import org.esco.indicators.utils.constants.StatisticConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests of the {@link EstablishmentVisitStatisticService} interface. <br/><br/>
 * 
 * These tests are based on a HSQL database. <br/>
 * This database is (re)created for each test.
 * The data injected in this database are described in the file : src/test/resources/import.sql
 * 
 * @since : 01/06/2012
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class EstablishmentVisitStatisticServiceTest  {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** {@link EstablishmentVisitStatisticService} service, under tests, used to access statistical data */
    @Autowired
    private EstablishmentVisitStatisticService establishmentVisitStatisticService;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Test method for {@link org.esco.indicators.services.statistic.EstablishmentVisitStatisticService#findEstablishmentDailyStatistic(java.util.Date, String, java.lang.String)}.
     */
    @Test
    public void testFindDailyStatistic() {
	// Expected result
	Date day = toSqlDate("2012-05-28");
	String establishmentUai = "0453456A";
	String establishmentType = "CFA";
	String typeStat = StatisticConstants.TYPE_STAT_ESTABLISHMENT;
	
	EstablishmentVisitStatistic expected = new EstablishmentVisitStatistic(day, establishmentType, establishmentUai, typeStat);
	expected.setNumVisitors(60);
	expected.setNumVisits(450);
	
	// Actual result
	EstablishmentVisitStatistic actual = establishmentVisitStatisticService.findEstablishmentDailyStatistic(day, establishmentType, establishmentUai);
	
	Assert.assertEquals(expected, actual);
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    /**
     * Converts a string representing a date (yyyy-MM-dd) to a SQL date.
     * 
     * @param date
     * 			The string date in the format yyyy-MM-dd
     * @return
     * 	the SQL date corresponding to the specified <code>date</code>.<br/>
     * 	<code>null</code> if the date could not been converted.
     */
    private Date toSqlDate(String date) {
	// Format of the specified date
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date day = null;
	
	// Try to get the SQL date
	try {
	    java.util.Date parsedDate = dateFormat.parse(date);
	    day = new Date(parsedDate.getTime());
	} catch (ParseException e) {
	    // Nothing to do
	}
	
	return day;
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
