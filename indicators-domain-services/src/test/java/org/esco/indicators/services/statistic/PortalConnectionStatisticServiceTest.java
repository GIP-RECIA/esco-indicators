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
 * Tests of the {@link PortalConnectionStatisticService} interface.
 * 
 * @since  2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class PortalConnectionStatisticServiceTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES

    /** {@link PortalConnectionStatisticService} service, under tests, used to access statistical data */
    @Autowired
    private PortalConnectionStatisticService portalConnectionStatisticService;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Test method for {@link org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumConnectionsByProfile(java.lang.String, java.lang.String, Integer, Integer)}.<br/>
     *
     * Tests the number of connections on the portal for a specified :
     * <ul>
     * 	<li>establishment UAI</li>
     * 	<li>week (determined by the first day of the week)</li>
     * 	<li>user profile</li>
     * </ul>
     */
    @Test
    public void testFindWeeklyNumConnectionsByProfile() {
	///////////////////////////////////////////////////
	// Expected weekly number of connections
	///////////////////////////////////////////////////
	// Especial users : 12 + 8 connections
	// Normal users : (3x25) + (4x13) connections
	//-------------------------------------------------------------
	// TOTAL : 147 expected connections
	///////////////////////////////////////////////////
	Integer expected = 147;
	
	// Actual result
	Integer year = 2012;
	Integer weekNumber = 22;
	
	String establishmentUai = "0453456A";
	String userProfile = "Teacher";
	Integer actual = portalConnectionStatisticService.findWeeklyNumConnectionsByProfile(establishmentUai, userProfile, weekNumber, year);
	
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumVisitorsBelowTreshold(String, String, Integer, Integer, Integer)}.<br/>
     *
     * Tests the number of visitors on the portal for a specified :
     * <ul>
     * 	<li>establishment UAI</li>
     * 	<li>week (determined by the first day of the week)</li>
     * 	<li>user profile</li>
     * 	<li>num connections treshold</li>
     * </ul>
     */
    @Test
    public void testFindWeeklyNumVisitorsBelowTreshold1() {
	///////////////////////////////////////////////////
	// Expected weekly number of visitors
	///////////////////////////////////////////////////
	// Especial users : 1 visitor
	// Normal users : 13 + 25 = 38 visitors
	//-------------------------------------------------------------
	// TOTAL : 39  expected visitors
	///////////////////////////////////////////////////
	Integer expected = 39;
	
	// Actual result
	Integer year = 2012;
	Integer weekNumber = 22;
	String establishmentUai = "0453456A";
	String userProfile = "Teacher";
	Integer treshold = 8;
	
	Integer actual = portalConnectionStatisticService.findWeeklyNumVisitorsBelowTreshold(establishmentUai, userProfile, weekNumber, year, treshold);
	
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumVisitorsBelowTreshold(String, String, Integer, Integer, Integer)}.<br/>
     *
     * Tests the number of visitors on the portal for a specified :
     * <ul>
     * 	<li>establishment UAI</li>
     * 	<li>week (determined by the first day of the week)</li>
     * 	<li>user profile</li>
     * 	<li>num connections treshold</li>
     * </ul>
     */
    @Test
    public void testFindWeeklyNumVisitorsBelowTreshold2() {
	///////////////////////////////////////////////////
	// Expected weekly number of visitors
	///////////////////////////////////////////////////
	// Especial users : 2 visitors
	// Normal users : 13 + 25 = 38 visitors
	//-------------------------------------------------------------
	// TOTAL : 40  expected visitors
	///////////////////////////////////////////////////
	Integer expected = 40;
	
	// Actual result
	Integer year = 2012;
	Integer weekNumber = 22;
	String establishmentUai = "0453456A";
	String userProfile = "Teacher";
	Integer treshold = 12;
	
	Integer actual = portalConnectionStatisticService.findWeeklyNumVisitorsBelowTreshold(establishmentUai, userProfile, weekNumber, year, treshold);
	
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyNumConnectionsByProfile(String, String, Integer, Integer)}.<br/>
     *
     * Tests the number of connections on the portal for a specified :
     * <ul>
     * 	<li>establishment UAI</li>
     * 	<li>month (determined by the first day of the month)</li>
     * 	<li>user profile</li>
     * </ul>
     */
    @Test
    public void testFindMonthlyNumConnectionsByProfile(){
	///////////////////////////////////////////////////
	// Expected mothly number of connections
	///////////////////////////////////////////////////
	// Especial users : 128 + 234 connections
	// Normal users : (7x115) + (9x13) connections
	//-------------------------------------------------------------
	// TOTAL : 1284 expected connections
	///////////////////////////////////////////////////
	Integer expected = 1284;
	
	// Actual result
	String establishmentUai = "0453456A";
	Integer year = 2012;
	Integer month = 05;
	String userProfile = "Teacher";
	Integer actual = portalConnectionStatisticService.findMonthlyNumConnectionsByProfile(establishmentUai, userProfile, month, year);
	
	Assert.assertEquals(expected, actual);
    }
    
    

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
