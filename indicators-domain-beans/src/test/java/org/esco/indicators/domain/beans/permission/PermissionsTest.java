/**
 * 
 */
package org.esco.indicators.domain.beans.permission;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests of the {@link Permissions} class.<br/>
 * 
 * This test is used to see if a well-formed XML containing permissions is correctly mapped into this class.<br/>
 * 
 * The test files used are : 
 * <ul>
 * 	<li>src/test/resources/permissions-test.xml<li>
 * </ul>
 * 
 * @since 2012/06/07
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class PermissionsTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** JAXB Context for XML mapping */
    private JAXBContext jaxbContext;
    
    /** Groups permissions defined into the XML file : src/test/resources/permissions-test.xml */
    private Permissions permissions;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Initializes the context for running the tests.
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	// Construction of the context with the "root" class
	jaxbContext = JAXBContext.newInstance(Permissions.class);
	
	// Creation of the unmarshaller
	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
	// Mapping of the XML into the class
	File permissionsFile = new File("src/test/resources/permissions-test.xml");
	
	permissions = (Permissions) unmarshaller.unmarshal(permissionsFile);
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Test method for {@link org.esco.indicators.domain.beans.permission.Permissions#getPermissions()}.
     * 
     * Tests if there is the expected number of permissions.
     */
    @Test
    public void testGetPermissions1() {
	// Expected result : number of permissions
	Integer expected = 3;
	
	// Actual result
	Integer actual = permissions.getPermissions().size();
	
	// Test 
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.permission.Permissions#getPermissions()}.
     * 
     * Tests the permissions names.
     */
    @Test
    public void testGetPermissions2() {
	// Expected result : permissions names
	Set<String> expected = new HashSet<String>();
	expected.add("CFA");
	expected.add("COLL37");
	expected.add("LOCAL");
	
	
	// Actual result
	Set<String> actual = new HashSet<String>();
	List<Permission> permissionList = permissions.getPermissions();
	for (Permission permission : permissionList) {
	    actual.add(permission.getName());
	}
	
	// Test 
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.permission.Permissions#getPermissions()}.
     * 
     * Tests the establishments types associated to the permissions.
     */
    @Test
    public void testGetPermissions3() {
	// Expected result : establishments types
	Set<String> expected = new HashSet<String>();
	expected.add("CFA");
	expected.add("COLLEGE");
	expected.add(null);
	
	// Actual result
	Set<String> actual = new HashSet<String>();
	List<Permission> permissionList = permissions.getPermissions();
	for (Permission permission : permissionList) {
	    actual.add(permission.getEstablishmentType());
	}
	
	// Test 
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.permission.Permissions#getPermissions()}.
     * 
     * Tests if the counties are the expected ones.
     */
    @Test
    public void testGetPermissions4() {
	// Expected result : counties number
	Set< List<Integer> > expected = new HashSet< List<Integer> >();
	
	List<Integer> list = new ArrayList<Integer>();
	list.add(28);
	list.add(36);
	list.add(37);
	list.add(41);
	list.add(45);
	expected.add(list );

	List<Integer> list2 = new ArrayList<Integer>();
	list2.add(37);
	expected.add(list2);
	
	// Actual result
	Set< List<Integer> > actual = new HashSet< List<Integer> >();
	List<Permission> permissionList = permissions.getPermissions();
	for (Permission permission : permissionList) {
	    Counties counties = permission.getCounties();
	    if(counties != null) {
		actual.add(counties.getCounties());
	    }
	}
	
	// Test 
	Assert.assertEquals(expected, actual);
    }


    
    
    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
