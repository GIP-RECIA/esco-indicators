/**
 * 
 */
package org.esco.indicators.domain.beans.permission;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import junit.framework.Assert;

import org.esco.indicators.domain.beans.xml.permission.GroupPermission;
import org.esco.indicators.domain.beans.xml.permission.GroupsPermissions;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests of the {@link GroupsPermissions} class.<br/>
 * 
 * This test is used to see if a well-formed XML containing groups permissions is correctly mapped into this class.<br/>
 * 
 * The test files used are : 
 * <ul>
 * 	<li>src/test/resources/groups-permissions-test.xml<li>
 * </ul>
 * 
 * @since 2012/06/07
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class GroupsPermissionsTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** JAXB Context for XML mapping */
    private JAXBContext jaxbContext;
    
    /** Groups permissions defined into the XML file : src/test/resources/groups-permissions-test.xml */
    private GroupsPermissions groupsPermissions;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Initializes the context for running the tests.
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	// Construction of the context with the "root" class
	jaxbContext = JAXBContext.newInstance(GroupsPermissions.class);
	
	// Creation of the unmarshaller
	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
	// Mapping of the XML into the class
	File groupsPermissionsFile = new File("src/test/resources/groups-permissions-test.xml");
	
	groupsPermissions = (GroupsPermissions) unmarshaller.unmarshal(groupsPermissionsFile);
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Test method for {@link org.esco.indicators.domain.beans.xml.permission.GroupsPermissions#getGroupsPermissions()}.
     * 
     * Tests if there is the correct number of group permissions.
     * 
     */
    @Test
    public void testGetGroupsPermissions1() {
	// Expected result : number of group permissions
	Integer expected = 2;
	
	// Actual result
	Integer actual = groupsPermissions.getGroupsPermissions().size();
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.xml.permission.GroupsPermissions#getGroupsPermissions()}.
     * 
     * Tests if the group permission patterns are the expected ones.
     * 
     */
    @Test
    public void testGetGroupsPermissions2() {
	// Expected result : patterns
	Set<String> expected = new HashSet<String>();
	expected.add("%TYPE_ESTAB%:admin:indicateurs:central");
	expected.add("%TYPE_ESTAB%:admin:indicateurs:local_admin:%UAI%");
	
	// Actual result
	Set<String> actual = new HashSet<String>();
	List<GroupPermission> groups = groupsPermissions.getGroupsPermissions();
	for (GroupPermission groupPermission : groups) {
	    actual.add(groupPermission.getMatchPattern());
	}
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.xml.permission.GroupsPermissions#getGroupsPermissions()}.
     * 
     * Tests if the permissions names are the expected ones.
     * 
     */
    @Test
    public void testGetGroupsPermissions3() {
	// Expected result : permissions names
	Set<String> expected = new HashSet<String>();
	expected.add("%TYPE_ESTAB%");
	expected.add("LOCAL");
	
	// Actual result
	Set<String> actual = new HashSet<String>();
	List<GroupPermission> groups = groupsPermissions.getGroupsPermissions();
	for (GroupPermission groupPermission : groups) {
	    actual.add(groupPermission.getPermissionName());
	}
	
	// Test
	Assert.assertEquals(expected, actual);
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS


    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
