/**
 * 
 */
package org.esco.indicators.services.statistic;

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
 * Tests of the {@link ServiceConnectionStatisticService} interface. <br/><br/>
 * 
 * These tests are based on a HSQL database. <br/>
 * This database is (re)created for each test.
 * The data injected in this database are described in the file : src/test/resources/import.sql
 * 
 * @since 2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class ServiceConnectionStatisticServiceTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** {@link ServiceConnectionStatisticService} service, under tests, used to access statistical data */
    @Autowired
    private ServiceConnectionStatisticService serviceConnectionStatisticService;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Test method for {@link org.esco.indicators.services.statistic.ServiceConnectionStatisticServiceImpl#findDailyNumConnectionsByProfile(java.util.Date, java.lang.String, java.lang.String, java.lang.String)}.
     * 
     * Tests if the retrieved number of connections is the expected one.
     */
    @Test
    public void testFindDailyNumConnectionsByProfile() {
	// Expected number of connections
	Integer expected = 57;
	
	// Actual result
	String establishmentUai = "0453456A";
	
	String userProfile = "Teacher";
	
	String serviceName = "Teacher service";
	List<String> services = new ArrayList<String>();
	services.add(serviceName);
	
	Date day = DateUtils.toSqlDate("28/05/2012", DateUtils.DATE_FORMAT_FR);
	
	Integer actual = serviceConnectionStatisticService.findDailyNumConnectionsByProfile(day,
		establishmentUai, services, userProfile);
	
	Assert.assertEquals(expected, actual);

    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
