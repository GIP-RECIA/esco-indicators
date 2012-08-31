/**
 * 
 */
package org.esco.indicators.services.permission;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.esco.indicators.domain.beans.xml.permission.Counties;
import org.esco.indicators.domain.beans.xml.permission.GroupPermission;
import org.esco.indicators.domain.beans.xml.permission.Permission;
import org.esco.indicators.domain.beans.xml.permission.Permissions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests of the {@link PermissionProviderImpl} class.
 * 
 * @since  2012/06/07
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class PermissionUtilsTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** {@link PermissionProviderImpl} under tests */
    @Autowired
    private PermissionProvider permissionProvider;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Test method for {@link org.esco.indicators.services.permission.PermissionProviderImpl#getPermissions()}.
     * 
     * Tests if the number of permissions is the expected one.
     */
    @Test
    public void testGetPermissions1() {
	// Expected result
	Integer expected = 3;
	
	// Actual result
	Permissions permissions =  permissionProvider.getPermissions();
	List<Permission> permissionList = permissions.getPermissions();
	Integer actual =permissionList.size();
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.permission.PermissionProviderImpl#getPermissions()}.
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
	List<Permission> permissionList = permissionProvider.getPermissions().getPermissions();
	for (Permission permission : permissionList) {
	    actual.add(permission.getName());
	}
	
	// Test 
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.permission.PermissionProviderImpl#getPermissions()}.
     * 
     * Tests the establishment types associated to the permissions.
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
	List<Permission> permissionList = permissionProvider.getPermissions().getPermissions();
	for (Permission permission : permissionList) {
	    actual.add(permission.getEstablishmentType());
	}
	
	// Test 
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.permission.PermissionProviderImpl#getPermissions()}.
     * 
     * Tests the counties number associated to the permissions.
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
	List<Permission> permissionList = permissionProvider.getPermissions().getPermissions();
	for (Permission permission : permissionList) {
	    Counties counties = permission.getCounties();
	    if(counties != null) {
		actual.add(counties.getCounties());
	    }
	}
	
	// Test 
	Assert.assertEquals(expected, actual);
    }

    /**
     * Test method for {@link org.esco.indicators.services.permission.PermissionProviderImpl#getGroupsPermissions()}.
     * 
     * Tests if the number of groups permissions is the expected one.
     */
    @Test
    public void testGetGroupsPermissions1() {
	// Expected result : number of group permissions
	Integer expected = 2;
	
	// Actual result
	Integer actual = permissionProvider.getGroupsPermissions().getGroupsPermissions().size();
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.permission.PermissionProviderImpl#getGroupsPermissions()}.
     * 
     * Tests if the group permission patterns are the expected ones.
     */
    @Test
    public void testGetGroupsPermissions2() {
	// Expected result : patterns
	Set<String> expected = new HashSet<String>();
	expected.add("%TYPE_ESTAB%:admin:indicateurs:central");
	expected.add("%TYPE_ESTAB%:admin:indicateurs:local_admin:%UAI%");
	
	// Actual result
	Set<String> actual = new HashSet<String>();
	List<GroupPermission> groups = permissionProvider.getGroupsPermissions().getGroupsPermissions();
	for (GroupPermission groupPermission : groups) {
	    actual.add(groupPermission.getMatchPattern());
	}
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.permission.PermissionProviderImpl#getGroupsPermissions()}.
     * 
     * Tests if the permissions names are the expected ones.
     */
    @Test
    public void testGetGroupsPermissions3() {
	// Expected result : permissions names
	Set<String> expected = new HashSet<String>();
	expected.add("%TYPE_ESTAB%");
	expected.add("LOCAL");
	
	// Actual result
	Set<String> actual = new HashSet<String>();
	List<GroupPermission> groups = permissionProvider.getGroupsPermissions().getGroupsPermissions();
	for (GroupPermission groupPermission : groups) {
	    actual.add(groupPermission.getPermissionName());
	}
	
	// Test
	Assert.assertEquals(expected, actual);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
