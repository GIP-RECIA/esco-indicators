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
     * {@link org.esco.indicators.services.statistic.AccountActivationStatisticService#findWeeklyNumActivatedAccountsForProfile(String, String, Integer, Integer)}
     * <br/>
     * 	
     * Tests if the number of activated accounts is the expected one.
     */
    @Test
    public void testFindWeeklyNumActivatedAccountsForProfile1() {
	// Expected result : 1 activated account
	Integer expected = 1;
	
	// Actual result
	String establishmentUai = "0453456A";
	String userProfile = "Teacher";
	Integer week = 12;
	Integer year = 2011;
	Integer actual  = accountActivationStatisticService.findWeeklyNumActivatedAccountsForProfile(establishmentUai, userProfile, week, year);
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for
     * {@link org.esco.indicators.services.statistic.AccountActivationStatisticService#findWeeklyNumActivatedAccountsForProfile(String, String, Integer, Integer)}
     * <br/>
     * 	
     * Tests if the number of activated accounts is the expected one.
     */
    @Test
    public void testFindWeeklyNumActivatedAccountsForProfile2() {
	// Expected result : 3 activated accounts
	Integer expected = 3;
	
	// Actual result
	String establishmentUai = "0453456A";
	String userProfile = "Teacher";
	Integer week = 6;
	Integer year = 2012;
	Integer actual  = accountActivationStatisticService.findWeeklyNumActivatedAccountsForProfile(establishmentUai, userProfile, week, year);
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for
      * {@link org.esco.indicators.services.statistic.AccountActivationStatisticService#findWeeklyNumActivatedAccountsForProfile(String, String, Integer, Integer)}
      * <br/>
     * 	
     * Tests if the number of activated accounts is the expected one.
     */
    @Test
    public void testFindWeeklyNumActivatedAccountsForProfile3() {
	// Expected result : 0 activated account
	Integer expected = 0;
	
	// Actual result
	String establishmentUai = "0453456A";
	String userProfile = "Teacher";
	Integer week = 6;
	Integer year = 2002;
	Integer actual  = accountActivationStatisticService.findWeeklyNumActivatedAccountsForProfile(establishmentUai, userProfile, week, year);
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for
     * {@link org.esco.indicators.services.statistic.AccountActivationStatisticService#findWeeklyNumActivatedAccountsForProfile(String, String, Integer, Integer)}
     * <br/>
     * 	
     * Tests if the number of activated accounts is the expected one.
     */
    @Test
    public void testFindWeeklyNumActivatedAccountsForProfile4() {
	// Expected result : 0 activated account
	Integer expected = 0;
	
	// Actual result
	String establishmentUai = "0888888A";
	String userProfile = "FakeProfile";
	Integer week = 6;
	Integer year = 2012;
	Integer actual  = accountActivationStatisticService.findWeeklyNumActivatedAccountsForProfile(establishmentUai, userProfile, week, year);
	
	// Test
	Assert.assertEquals(expected, actual);
    }    
    
    // ------------------------------------------------------------------------------ PUBLIC METHODS

    // ----------------------------------------------------------------------------- PRIVATE METHODS

    // ------------------------------------------------------------------------------ STATIC METHODS
}
