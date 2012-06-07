/**
 * 
 */
package org.esco.indicators.services.profile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.esco.indicators.utils.date.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests of the {@link ProfileLinkService} interface.<br/>
 * <br/>
 * 
 * These tests are based on a HSQL database. <br/>
 * This database is (re)created for each test. The data injected in this database are described in the file :
 * src/test/resources/import.sql
 * 
 * 
 * @since 2012/06/07
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class ProfileLinkServiceTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    
    /** {@link ProfileLinkService} providing access to statistical data on the users / profiles links */
    @Autowired
    private ProfileLinkService profileLinkService;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Test method for {@link org.esco.indicators.services.profile.ProfileLinkService#findUidsByProfile(java.lang.String, java.lang.String, java.sql.Date, java.sql.Date)}.
     * 
     * Tests if the retrieved UIDs are the expected one.
     */
    @Test
    public void testFindUidsByProfile1() {
	// Expected result
	List<String> expected = new ArrayList<String>();
	expected.add("F1234567X");
	expected.add("F8912345R");
	expected.add("F8654397U");
	
	// Actual result
	Date startDate = DateUtils.toSqlDate("2012-02-01");
	Date endDate = DateUtils.toSqlDate("2012-07-13");
	String userProfile = "Teacher";
	String establishmentUai = "0453456A";
	List<String> actual = profileLinkService.findUidsByProfile(userProfile, establishmentUai, startDate, endDate);
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.profile.ProfileLinkService#findUidsByProfile(java.lang.String, java.lang.String, java.sql.Date, java.sql.Date)}.
     * 
     * Tests if the retrieved UIDs are the expected one.
     */
    @Test
    public void testFindUidsByProfile2() {
	// Expected result
	List<String> expected = new ArrayList<String>();
	expected.add("F8912345R");
	expected.add("F8654397U");
	
	// Actual result
	Date startDate = DateUtils.toSqlDate("2012-02-01");
	Date endDate = DateUtils.toSqlDate("2012-07-14");
	String userProfile = "Teacher";
	String establishmentUai = "0453456A";
	List<String> actual = profileLinkService.findUidsByProfile(userProfile, establishmentUai, startDate, endDate);
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.profile.ProfileLinkService#findUidsByProfile(java.lang.String, java.lang.String, java.sql.Date, java.sql.Date)}.
     * 
     * Tests if there is no retrieved UIDs.
     */
    @Test
    public void testFindUidsByProfile3() {
	// Expected result
	List<String> expected = new ArrayList<String>();
	
	// Actual result
	Date startDate = DateUtils.toSqlDate("2011-03-17");
	Date endDate = DateUtils.toSqlDate("2012-07-14");
	String userProfile = "Teacher";
	String establishmentUai = "0453456A";
	List<String> actual = profileLinkService.findUidsByProfile(userProfile, establishmentUai, startDate, endDate);
	
	// Test
	Assert.assertEquals(expected, actual);
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
