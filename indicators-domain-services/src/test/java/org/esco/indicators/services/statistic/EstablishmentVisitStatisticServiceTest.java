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
import org.esco.indicators.utils.constants.db.EstablishmentConstants;
import org.esco.indicators.utils.constants.db.StatisticConstants;
import org.esco.indicators.utils.date.DateUtils;
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
 * @since 2012/06/01
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
     * 
     * Tests if the statistic is correctly retrieved.
     */
    @Test
    public void testFindDailyStatistic1() {
	// Expected result
	Date day = DateUtils.toSqlDate("28/05/2012", DateUtils.DATE_FORMAT_FR);
	String establishmentUai = "0453456A";
	String establishmentType = EstablishmentConstants.ESTABLISHMENT_TYPE_CFA;
	String typeStat = StatisticConstants.TYPE_STAT_ESTABLISHMENT;
	
	EstablishmentVisitStatistic expected = new EstablishmentVisitStatistic(day, establishmentType, establishmentUai, typeStat);
	expected.setNumVisitors(60);
	expected.setNumVisits(450);
	
	// Actual result
	EstablishmentVisitStatistic actual = establishmentVisitStatisticService.findEstablishmentDailyStatistic(day, establishmentType, establishmentUai);
	
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.statistic.EstablishmentVisitStatisticService#findEstablishmentDailyStatistic(java.util.Date, String, java.lang.String)}.
     * 
     * Tests if no statistic is retrieved, as expected.
     */
    @Test
    public void testFindDailyStatistic2() {
	Date day = DateUtils.toSqlDate("31/12/2999", DateUtils.DATE_FORMAT_FR);
	String establishmentUai = "0453456A";
	String establishmentType = EstablishmentConstants.ESTABLISHMENT_TYPE_CFA;
	
	// Actual result
	EstablishmentVisitStatistic actual = establishmentVisitStatisticService.findEstablishmentDailyStatistic(day, establishmentType, establishmentUai);
	
	Assert.assertNull(actual);
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
