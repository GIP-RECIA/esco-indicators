/**
 * 
 */
package org.esco.indicators.domain.beans.permission;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit tests for the {@link EstablishmentViewPermission} class.
 * 
 * @since  2012/10/03
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class EstablishmentViewPermissionTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** The element under test */
    @Autowired
    private EstablishmentViewPermission establishmentViewPermission;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Tests if the attributes names are the expected ones.
     */
    @Test
    public void testGetAttributesNames() {
	// Expected result
	Set<String> expected = new HashSet<String>();
	expected.add("uai");
	expected.add("type");
	
	// Actual result
	Set<String> actual = establishmentViewPermission.getAttributesNames();
	
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests if the pattern to match is the expected one.
     */
    @Test
    public void testGetPatternToMatch() {
	// Expected result
	String expected = "establishment:administrator:([0-9]+)";
	
	// Actual result
	String actual = establishmentViewPermission.getPatternToMatch();
	
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests if the values associated to the attributes names are the expected ones.
     */
    @Test
    public void testGetValuesByAttribute1() {
	// Expected result
	List<String> expected = new ArrayList<String>();
	expected.add("id_$1");
	expected.add("short_id_$1");
	
	// Actual result
	List<String> actual = establishmentViewPermission.getPropertyValues("uai");
	
	
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests if the values associated to the attributes names are the expected ones.
     */
    @Test
    public void testGetValuesByAttribute2() {
	// Expected result
	List<String> expected = new ArrayList<String>();
	expected.add("elementary");
	expected.add("college");
	expected.add("university");
	
	// Actual result
	List<String> actual = establishmentViewPermission.getPropertyValues("type");
	
	
	Assert.assertEquals(expected, actual);
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
