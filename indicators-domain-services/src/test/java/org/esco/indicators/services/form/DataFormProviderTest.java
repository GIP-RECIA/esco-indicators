/**
 * 
 */
package org.esco.indicators.services.form;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests of the {@link DataFormProviderImpl} class.
 * 
 * @since  2012/06/18
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class DataFormProviderTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** {@link DataFormProviderImpl} under tests */
    @Autowired
    private DataFormProvider dataAccountFormProvider;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Test method for {@link org.esco.indicators.utils.PermissionProvider.DataFormProvider#getEntriesForm()}.
     * 
     * Tests if the number of entries is the expected one.
     */
    @Test
    public void testGetEntriesForm() {
	// Expected result
	Integer expected = 2;
	
	// Actual result
	Integer actual = dataAccountFormProvider.getEntriesForm().size();
	
	// Test
	Assert.assertEquals(expected, actual);
    }

    /**
     * Test method for {@link org.esco.indicators.utils.PermissionProvider.DataFormProvider#getEntriesNames()}.
     * 
     * Tests if the names of the entries are the expected ones.
     */
    @Test
    public void testGetEntriesNames() {
	// Expected result
	List<String> expected = new ArrayList<String>();
	expected.add("monitoringType");
	expected.add("establishmentType");
	
	// Actual result
	List<String> actual = dataAccountFormProvider.getEntriesNames();
	
	// Test
	Assert.assertEquals(expected, actual);
    }

    /**
     * Test method for {@link org.esco.indicators.utils.PermissionProvider.DataFormProvider#getEntryValues(java.lang.String)}.
     * 
     * Tests if the possible values names of an entry are the expected ones.
     */
    @Test
    public void testGetEntryValues() {
	// Expected result
	List<String> expected = new ArrayList<String>();
	expected.add("attendance");
	expected.add("monitoringAttendance");
	
	// Actual result
	List<String> actual = new ArrayList<String>();
	List<EntryValue> entryValues = dataAccountFormProvider.getEntryValues("monitoringType");
	for (EntryValue entryValue : entryValues) {
	    actual.add(entryValue.getName());
	}
	
	// Test
	Assert.assertEquals(expected, actual);
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
