/**
 * 
 */
package org.esco.indicators.services.permission;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests of the {@link PermissionService} interface.
 * 
 * @since  2012/10/03
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class PermissionServiceTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Permission service under tests */
    @Autowired
    private PermissionService establishmentPermissionService;


    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Test if the provided string matches as expected.
     */
    @Test
    public void testMatchAtLeastOnePermission() {
	// String to match
	String toMatch = "establishment:administrator:458";
	Assert.assertTrue(establishmentPermissionService.matchAtLeastOnePermission(toMatch));
    }
    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
