/**
 * 
 */
package org.esco.indicators.services.form;

import static org.junit.Assert.fail;

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
 * Tests of the {@link DataFormService} interface.
 * @since  2012/06/25
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class DataFormServiceTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** {@link DataFormService} under tests */
    @Autowired
    private DataFormService dataAccountFormService;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Test method for {@link org.esco.indicators.services.form.DataFormService#getJspKeysDisabledByDefault()}.
     * 
     * Tests if the JSP keys disabled by default are the expected ones.
     */
    @Test
    public void testGetJspKeysDisabledByDefault() {
	// Expected result
	List<String> expected = new ArrayList<String>();
	 expected.add("establishmentType.LEGT");
	 
	 // Actual result
	 List<String> actual = dataAccountFormService.getJspKeysDisabledByDefault();
	 
	 // Test
	 Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.form.DataFormService#getJspKeysEnabledByDefault()}.
     * 
     * Tests if the JSP keys enabled by default are the expected ones.
     */
    @Test
    public void testGetJspKeysEnabledByDefault() {
	// Expected result
	List<String> expected = new ArrayList<String>();
	 expected.add("monitoringType.attendance");
	 expected.add("monitoringType.monitoringAttendance");
	 expected.add("establishmentType.CFA");
	 expected.add("establishmentType.LA");
	 
	 // Actual result
	 List<String> actual = dataAccountFormService.getJspKeysEnabledByDefault();
	 
	 // Test
	 Assert.assertEquals(expected, actual);
    }

    /**
     * Test method for {@link org.esco.indicators.services.form.DataFormService#getJspKeysToDisable(java.util.List)}.
     * 
     * Tests if the JSP keys to disable (when some JSP keys are checked in the user view) are the expected ones.
     */
    @Test
    public void testGetJspKeysToDisable() {
	///////////////////////////////
	// Expected result
	///////////////////////////////
	List<String> expected = new ArrayList<String>();
	expected.add("establishmentType.CFA");
	expected.add("establishmentType.LA");
	
	///////////////////////////////
	// Actual result
	///////////////////////////////
	List<String> checkedJspKeys = new ArrayList<String>();
	checkedJspKeys.add("monitoringType.monitoringAttendance");
	checkedJspKeys.add("establishmentType.CFA");

	List<String> actual = dataAccountFormService.getJspKeysToDisable(checkedJspKeys);
	
	///////////////////////////////
	// Test
	///////////////////////////////
	Assert.assertEquals(expected, actual);
    }

    /**
     * Test method for {@link org.esco.indicators.services.form.DataFormService#getEntryValues(java.lang.String)}.
     * 
     * Tests if the entry value names of a form entry are the expected ones.
     */
    @Test
    public void testGetEntryValues() {
	// Expected result
	List<String> expected = new ArrayList<String>();
	expected.add("attendance");
	expected.add("monitoringAttendance");
	
	// Actual result
	List<EntryValue> entryValues = dataAccountFormService.getEntryValues("monitoringType");
	List<String> actual = new ArrayList<String>();
	for (EntryValue entryValue : entryValues) {
	    actual.add(entryValue.getName());
	}
	
	// Test
	Assert.assertEquals(expected, actual);
    }

    /**
     * Test method for {@link org.esco.indicators.services.form.DataFormService#isKnown(java.lang.String)}.
     * 
     * Tests if the application is able to indicate that a JSP is known.
     */
    @Test
    public void testIsKnown1() {
	// Tests if the JSP key is known
	boolean isKnown = dataAccountFormService.isKnown("establishmentType.CFA");
	Assert.assertTrue(isKnown);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.form.DataFormService#isKnown(java.lang.String)}.
     * 
     * Tests if the application is able to indicate that a JSP is unknown.
     */
    @Test
    public void testIsKnown2() {
	// Tests if the JSP key is known
	boolean isKnown = dataAccountFormService.isKnown("establishmentType.FAKE");
	Assert.assertFalse(isKnown);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
