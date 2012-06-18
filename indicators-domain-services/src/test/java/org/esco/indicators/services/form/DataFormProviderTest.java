/**
 * 
 */
package org.esco.indicators.services.form;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests of the {@link DataFormProvider} class.
 * 
 * @since  2012/06/18
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class DataFormProviderTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** {@link DataFormProvider} under tests */
    private DataFormProvider dataFormProvider;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Initialization of the provider for the tests.
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	dataFormProvider = DataFormProvider.getInstance();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Test method for {@link org.esco.indicators.utils.web.DataFormProvider#getEntriesForm()}.
     * 
     * Tests if the number of entries is the expected one.
     */
    @Test
    public void testGetEntriesForm() {
	// Expected result
	Integer expected = 2;
	
	// Actual result
	Integer actual = dataFormProvider.getEntriesForm().size();
	
	// Test
	Assert.assertEquals(expected, actual);
    }

    /**
     * Test method for {@link org.esco.indicators.utils.web.DataFormProvider#getEntriesNames()}.
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
	List<String> actual = dataFormProvider.getEntriesNames();
	
	// Test
	Assert.assertEquals(expected, actual);
    }

    /**
     * Test method for {@link org.esco.indicators.utils.web.DataFormProvider#getEntryValues(java.lang.String)}.
     * 
     * Tests if the possible values of an entry are the expected ones.
     */
    @Test
    public void testGetEntryValues() {
	// Expected result
	List<EntryValue> expected = new ArrayList<EntryValue>();
	
	EntryValue attendance = new EntryValue();
	attendance.setName("attendance");
	attendance.setJspKey("monitoringType.attendance");
	attendance.setI18nKey("form.monitoringType.attendance");
	expected.add(attendance);
	
	EntryValue monitoring = new EntryValue();
	monitoring.setName("monitoringAttendance");
	monitoring.setJspKey("monitoringType.monitoringAttendance");
	monitoring.setI18nKey("form.monitoringType.monitoringAttendance");
	expected.add(monitoring);
	
	// Actual result
	List<EntryValue> actual = dataFormProvider.getEntryValues("monitoringType");
	
	// Test
	Assert.assertEquals(expected, actual);
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
