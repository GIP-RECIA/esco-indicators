/**
 * 
 */
package org.esco.indicators.services.permission;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.esco.indicators.domain.beans.permission.GenericFilter;
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
    
    /**
     * Test if generic filter created, from an establishment permission and a string to match, is the expected one.
     */
    @Test
    public void testGetPermissionFilter1() {
	// Expected result
	GenericFilter expected = new GenericFilter();
	
	String propertyName1 = "uai";
	Set<String> propertiesValues1 = new HashSet<String>();
	propertiesValues1.add("id_187");
	propertiesValues1.add("short_id_187");
	expected.addPropertyValues(propertyName1, propertiesValues1);
	
	String propertyName2 = "type";
	Set<String> propertiesValues2 = new HashSet<String>();
	propertiesValues2.add("elementary");
	propertiesValues2.add("college");
	propertiesValues2.add("university");
	expected.addPropertyValues(propertyName2, propertiesValues2);
	
	// String that will be tested
	String testString = "establishment:administrator:187";
	
	// Actual result
	GenericFilter actual = establishmentPermissionService.getPermissionFilter(testString);
	
	// Tests
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test if generic filter created, from an establishment permission and a string to match, is the expected one.
     */
    @Test
    public void testGetPermissionFilter2() {
	// Expected result
	GenericFilter expected = new GenericFilter();
	
	String propertyName1 = "uai";
	Set<String> propertiesValues1 = new HashSet<String>();
	propertiesValues1.add("uai_412");
	propertiesValues1.add("rne_412");
	expected.addPropertyValues(propertyName1, propertiesValues1);
	
	String propertyName2 = "name";
	Set<String> propertiesValues2 = new HashSet<String>();
	propertiesValues2.add("name:name_national:complete");
	propertiesValues2.add("name:short_412:uncomplete");
	expected.addPropertyValues(propertyName2, propertiesValues2);
	
	// String that will be tested
	String testString = "college:teacher:establishment_national_412";
	
	// Actual result
	GenericFilter actual = establishmentPermissionService.getPermissionFilter(testString);
	
	// Tests
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test if generic filter created, from an establishment permission and a string to match, is the expected one.<br/>
     * Test with accented accent.
     */
    @Test
    public void testGetPermissionFilter3() {
	// Expected result
	GenericFilter expected = new GenericFilter();
	
	String propertyName1 = "uai";
	Set<String> propertiesValues1 = new HashSet<String>();
	propertiesValues1.add("uai_412");
	propertiesValues1.add("rne_412");
	expected.addPropertyValues(propertyName1, propertiesValues1);
	
	String propertyName2 = "name";
	Set<String> propertiesValues2 = new HashSet<String>();
	propertiesValues2.add("name:name_établissement élève:complete");
	propertiesValues2.add("name:short_412:uncomplete");
	expected.addPropertyValues(propertyName2, propertiesValues2);
	
	// String that will be tested
	String testString = "college:teacher:establishment_établissement élève_412";
	
	// Actual result
	GenericFilter actual = establishmentPermissionService.getPermissionFilter(testString);
	
	// Tests
	Assert.assertEquals(expected, actual);
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
