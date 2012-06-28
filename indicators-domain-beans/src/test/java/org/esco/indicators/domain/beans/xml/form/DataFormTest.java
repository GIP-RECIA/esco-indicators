/**
 * 
 */
package org.esco.indicators.domain.beans.xml.form;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import junit.framework.Assert;

import org.apache.commons.collections.ListUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @since  
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class DataFormTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** JAXB Context for XML mapping */
    private JAXBContext jaxbContext;
    
    /** Groups permissions defined into the XML file : src/test/resources/groups-permissions-test.xml */
    private DataForm dataForm;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Initializes the context for running the tests.
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	// Construction of the context with the "root" class
	jaxbContext = JAXBContext.newInstance(DataForm.class);
	
	// Creation of the unmarshaller
	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
	// Mapping of the XML into the class
	File dataFormFile = new File("src/test/resources/data-form-test.xml");
	
	dataForm = (DataForm) unmarshaller.unmarshal(dataFormFile);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.xml.form.DataForm#getEntriesForm()}.
     * 
     * Test if the number of form entries is the expected one.
     */
    @Test
    public void testGetEntriesForm1() {
	// Expected result
	Integer expected = 2;
	
	// Actual result
	Integer actual = dataForm.getEntriesForm().size();
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.xml.form.DataForm#getEntriesForm()}.
     * 
     * Test if the name of the form entries are the expected ones.
     */
    @Test
    public void testGetEntriesForm2() {
	// Expected result
	List<String> expected =  new ArrayList<String>();
	expected.add("monitoringType");
	expected.add("establishmentType");
	
	// Actual result
	List<String> actual = new ArrayList<String>();
	for (EntryForm entry : dataForm.getEntriesForm()) {
	    actual.add(entry.getName());
	}
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.xml.form.DataForm#getEntriesForm()}.
     * 
     * Test if the number of entry values is the expected one.
     */
    @Test
    public void testGetEntriesForm3() {
	// Expected result
	Integer expected = 5;
	
	// Actual result
	Integer actual = 0;
	for (EntryForm entry : dataForm.getEntriesForm()) {
	    actual += entry.getEntryValues().size();
	}
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.xml.form.DataForm#getEntriesForm()}.
     * 
     * Test if the name of the entry values are the expected ones.
     */
    @Test
    public void testGetEntriesForm4() {
	// Expected result
	List<String> expected =  new ArrayList<String>();
	expected.add("attendance");
	expected.add("monitoringAttendance");
	expected.add("CFA");
	expected.add("LA");
	expected.add("LEGT");
	
	// Actual result
	List<String> actual = new ArrayList<String>();
	for (EntryValue entryValue : dataForm.getAllEntryValues()) {
		actual.add(entryValue.getName());
	}
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.xml.form.DataForm#getEntriesForm()}.
     * 
     * Test if the disable states are the expected one.<br/>
     */
    @Test
    public void testGetEntriesForm5() {
	for (EntryValue entryValue : dataForm.getAllEntryValues()) {
		if(
			entryValue.getName().equals("attendance")
			|| entryValue.getName().equals("monitoringAttendance")
			|| entryValue.getName().equals("CFA")
			|| entryValue.getName().equals("LA")
		) {
		 // Test
		    Assert.assertFalse(entryValue.isDisabledByDefault());
		} else {
		 // Test
		    Assert.assertTrue(entryValue.isDisabledByDefault());
		}
	}
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.xml.form.DataForm#getEntriesForm()}.
     * 
     * Test if the number of referenced entry values is the expected ones.<br/>
     */
    @Test
    public void testGetEntriesForm6() {
	// Expected result
	Integer expected = 3;
	
	// Actual result
	Integer actual = 0;
	
	for (EntryValue entryValue : dataForm.getAllEntryValues()) {
		List<EntryValueRef> refsToDisable = entryValue.getWhenActivatedEvent().getEntryValuesToDisable();
		List<EntryValueRef> refsToEnable = entryValue.getWhenActivatedEvent().getEntryValuesToEnable();
		actual += (refsToDisable == null ? 0 : refsToDisable.size());
		actual += (refsToEnable == null ? 0 : refsToEnable.size());
	}
	
	// Test
	Assert.assertEquals(expected, actual);
    }
    
    /**
    * Test method for {@link org.esco.indicators.domain.beans.xml.form.DataForm#getEntriesForm()}.
    * 
    * Test if the names of the entry values to disable are the expected ones.
    */
   @Test
   public void testGetEntriesForm7() {
	///////////////////////////////
	// Expected result
	///////////////////////////////
	List<String> expected =  new ArrayList<String>();
	expected.add("CFA");
	expected.add("LA");
	
	///////////////////////////////
	// Actual result
	///////////////////////////////
	List<String> actual =  new ArrayList<String>();
	List<EntryValueRef> refsToDisable = new ArrayList<EntryValueRef>();
	
	// Iteration on the entry form
	    for(EntryValue entryValue :dataForm.getAllEntryValues()) {
		List<EntryValueRef> disableEV = entryValue.getWhenActivatedEvent().getEntryValuesToDisable();
		 if (disableEV != null) {
		     refsToDisable.addAll(disableEV);
		 }
	    }
	
	// Get the names of the entry values to disable
	for (EntryValueRef entryValueRef : refsToDisable) {
	    actual.add(entryValueRef.getEntryValue().getName());
	}
	
	///////////////////////////////
	// Test
	///////////////////////////////
	Assert.assertEquals(expected, actual);
   }
   
   /**
   * Test method for {@link org.esco.indicators.domain.beans.xml.form.DataForm#getEntriesForm()}.
   * 
   * Test if the names of the entry values to enable are the expected ones.
   */
  @Test
  public void testGetEntriesForm8() {
	///////////////////////////////
	// Expected result
	///////////////////////////////
	List<String> expected =  new ArrayList<String>();
	expected.add("LEGT");
	
	///////////////////////////////
	// Actual result
	///////////////////////////////
	List<String> actual =  new ArrayList<String>();
	List<EntryValueRef> refsToEnable = new ArrayList<EntryValueRef>();
	
	// Iteration on the entry form
	for (EntryValue entryValue : dataForm.getAllEntryValues()) {
	    List<EntryValueRef> enableEV = entryValue.getWhenActivatedEvent().getEntryValuesToEnable();
	    if (enableEV != null) {
		refsToEnable.addAll(enableEV);
	    }
	}
	
	// Get the names of the entry values to enable
	for (EntryValueRef enableEntryValue : refsToEnable) {
	    actual.add(enableEntryValue.getEntryValue().getName());
	}
	
	///////////////////////////////
	// Test
	///////////////////////////////
	Assert.assertEquals(expected, actual);
  }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
