/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;

import junit.framework.Assert;

import org.esco.indicators.utils.date.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests of the {@link AccountActivationStatisticService} interface. <br/>
 * <br/>
 * 
 * These tests are based on a HSQL database. <br/>
 * This database is (re)created for each test. The data injected in this database are described in the file :
 * src/test/resources/import.sql
 * 
 * 
 * @since 2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class AccountActivationStatisticServiceTest {
    // ---------------------------------------------------------------------------------- ATTRIBUTES
    /**
     * {@link AccountActivationStatisticService} interface providing access to statistical data on accounts
     * activations
     */
    @Autowired
    private AccountActivationStatisticService accountActivationStatisticService;

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    // --------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Test method for
     * {@link org.esco.indicators.services.statistic.AccountActivationStatisticService#findNumActivatedAccountsBetween(java.sql.Date, java.sql.Date)}
     * <br/>
     * 	
     * Tests if the number of activated accounts is the expected one.
     */
    @Test
    public void testFindNumActivatedAccountsBetween1() {
	// Expected result
	Integer expected = 3;
	
	// Actual result
	Date startDate = DateUtils.toSqlDate("01/02/2012", DateUtils.DATE_FORMAT_FR);
	Date endDate = DateUtils.toSqlDate("04/02/2012", DateUtils.DATE_FORMAT_FR);
	Integer actual  = accountActivationStatisticService.findNumActivatedAccountsBetween(startDate, endDate);
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for
     * {@link org.esco.indicators.services.statistic.AccountActivationStatisticService#findNumActivatedAccountsBetween(java.sql.Date, java.sql.Date)}
     * <br/>
     * 	
     * Tests if the number of activated accounts is the expected one.
     */
    @Test
    public void testFindNumActivatedAccountsBetween2() {
	// Expected result
	Integer expected = 2;
	
	// Actual result
	Date startDate = DateUtils.toSqlDate("20/01/2012", DateUtils.DATE_FORMAT_FR);
	Date endDate = DateUtils.toSqlDate("03/02/2012",  DateUtils.DATE_FORMAT_FR);
	Integer actual  = accountActivationStatisticService.findNumActivatedAccountsBetween(startDate, endDate);
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for
     * {@link org.esco.indicators.services.statistic.AccountActivationStatisticService#findNumActivatedAccountsBetween(java.sql.Date, java.sql.Date)}
     * <br/>
     * 	
     * Tests if the number of activated accounts is the expected one.
     */
    @Test
    public void testFindNumActivatedAccountsBetween3() {
	// Expected result
	Integer expected = 0;
	
	// Actual result
	Date startDate = DateUtils.toSqlDate("01/01/2000", DateUtils.DATE_FORMAT_FR);
	Date endDate = DateUtils.toSqlDate("01/01/2999", DateUtils.DATE_FORMAT_FR);
	Integer actual  = accountActivationStatisticService.findNumActivatedAccountsBetween(startDate, endDate);
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    // ------------------------------------------------------------------------------ PUBLIC METHODS

    // ----------------------------------------------------------------------------- PRIVATE METHODS

    // ------------------------------------------------------------------------------ STATIC METHODS
}
