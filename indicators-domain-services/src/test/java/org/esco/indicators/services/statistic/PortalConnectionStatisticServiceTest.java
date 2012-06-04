/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;

import junit.framework.Assert;

import org.esco.indicators.utils.date.DateTranslator;
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
     * Test method for {@link org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumConnectionsByProfile(java.lang.String, java.sql.Date, java.lang.String)}.<br/>
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
	// Expected weekly number of connections
	Integer expected = 20;
	
	// Actual result
	String establishmentUai = "0453456A";
	Date firstWeekDay = DateTranslator.toSqlDate("2012-05-28");
	String userProfile = "Teacher";
	Integer actual = portalConnectionStatisticService.findWeeklyNumConnectionsByProfile(establishmentUai, firstWeekDay, userProfile);
	
	Assert.assertEquals(expected, actual);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
