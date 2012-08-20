/**
 * 
 */
package org.esco.indicators.services.structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.utils.constants.db.EstablishmentConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests of the {@link EstablishmentService} interface. <br/><br/>
 * 
 * These tests are based on a HSQL database. <br/>
 * This database is (re)created for each test.
 * The data injected in this database are described in the file : src/test/resources/import.sql
 * 
 * @since 2012/05/26
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")


public class EstablishmentServiceTest extends EstablishmentServiceBaseTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** {@link EstablishmentService} service, under tests, used to access establishments data */
    @Autowired
    private EstablishmentService establishmentService;
    
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.structure.EstablishmentServiceBaseTest#setUp()
     */
    @Before
    @Override
    public void setUp() {
	super.setUp();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentByUai(java.lang.String)}.
     * 
     * Tests if the retrieved establishment has the good UAI.
     */
    @Test
    public void testFindEstablishmentByUai1() {
	// Expected result
	String uai = "0453456A";
	Establishment expected = testedEstablishments.get(uai);
	
	// Actual result
	Establishment actual = establishmentService.findEstablishmentByUai(uai);
	
	// Test
	Assert.assertEquals(expected.getUai(), actual.getUai());
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentByUai(java.lang.String)}.
     * 
     * Tests if the retrieved establishment has the good type.
     */
    @Test
    public void testFindEstablishmentByUai2() {
	// Expected result
	String uai = "0453456A";
	Establishment expected = testedEstablishments.get(uai);
	
	// Actual result
	Establishment actual = establishmentService.findEstablishmentByUai(uai);
	
	// Test
	Assert.assertEquals(expected.getType(), actual.getType());
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentByUai(java.lang.String)}.
     * 
     * Tests if the retrieved establishment has the good county number.
     */
    @Test
    public void testFindEstablishmentByUai3() {
	// Expected result
	String uai = "0453456A";
	Establishment expected = testedEstablishments.get(uai);
	
	// Actual result
	Establishment actual = establishmentService.findEstablishmentByUai(uai);
	
	// Test
	Assert.assertEquals(expected.getCountyNumber(), actual.getCountyNumber());
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByCountyNumber(String)}.
     * 
     * Tests if the retrieved establishments has the good UAIs.
     */
    @Test
    public void testFindEstablishmentByCountyNumber1() {
	// Expected establishments
	String uai1 = "0453456A";
	String uai2 = "0456782B";
	
	List<Establishment> expectedEstablishments = new ArrayList<Establishment>();
	expectedEstablishments.add(testedEstablishments.get(uai1));
	expectedEstablishments.add(testedEstablishments.get(uai2));
	
	List<String> expectedUais = extractUais(expectedEstablishments);
	
	// Actual result
	String countyNumber = "45";
	List<Establishment> actualEstablishments = establishmentService.findEstablishmentsByCountyNumber(countyNumber);
	
	List<String> actualUais = extractUais(actualEstablishments);
	
	// Test
	Assert.assertEquals(expectedUais, actualUais);
    }
    
    
    /**
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByCountyNumber(String)}.
     * 
     * Tests if the retrieved establishments has the good types.
     */
    @Test
    public void testFindEstablishmentByCountyNumber2() {
	// Expected establishments
	String uai1 = "0453456A";
	String uai2 = "0456782B";
	
	List<Establishment> expectedEstablishments = new ArrayList<Establishment>();
	expectedEstablishments.add(testedEstablishments.get(uai1));
	expectedEstablishments.add(testedEstablishments.get(uai2));
	
	List<String> expectedTypes = extractTypes(expectedEstablishments);
	
	// Actual result
	String countyNumber = "45";
	List<Establishment> actualEstablishments = establishmentService.findEstablishmentsByCountyNumber(countyNumber);
	
	List<String> actualTypes = extractTypes(actualEstablishments);
	
	// Test
	Assert.assertEquals(expectedTypes, actualTypes);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByCountyNumber(String)}.
     * 
     * Tests if the retrieved establishments has the good county numbers.
     */
    @Test
    public void testFindEstablishmentByCountyNumber3() {
	// Expected establishments
	String uai1 = "0453456A";
	String uai2 = "0456782B";
	
	List<Establishment> expectedEstablishments = new ArrayList<Establishment>();
	expectedEstablishments.add(testedEstablishments.get(uai1));
	expectedEstablishments.add(testedEstablishments.get(uai2));
	
	List<String> expectedCountyNumbers = extractCountyNumbers(expectedEstablishments);
	
	// Actual result
	String countyNumber = "45";
	List<Establishment> actualEstablishments = establishmentService.findEstablishmentsByCountyNumber(countyNumber);
	
	List<String> actualCountyNumbers = extractCountyNumbers(actualEstablishments);
	
	// Test
	Assert.assertEquals(expectedCountyNumbers, actualCountyNumbers);
    }
    
    
    /**
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByType(String)}.
     * 
     * Tests if the retrieved establishments has the good UAIs.
     */
    @Test
    public void testFindEstablishmentByType1() {
	// Expected establishments
	String uai1 = "0453456A";
	String uai2 = "0362903S";
	
	List<Establishment> expectedEstablishments = new ArrayList<Establishment>();
	expectedEstablishments.add(testedEstablishments.get(uai1));
	expectedEstablishments.add(testedEstablishments.get(uai2));
	
	List<String> expectedUais = extractUais(expectedEstablishments);
	
	// Actual result
	List<Establishment> actualEstablishments = establishmentService
		.findEstablishmentsByType(EstablishmentConstants.ESTABLISHMENT_TYPE_CFA);

	List<String> actualUais = extractUais(actualEstablishments);
	
	// Test
	Assert.assertTrue(expectedUais.containsAll(actualUais));
	Assert.assertTrue(actualUais.containsAll(expectedUais));
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByType(String)}.
     * 
     * Tests if the retrieved establishments has the good types.
     */
    @Test
    public void testFindEstablishmentByType2() {
	// Expected establishments
	String uai1 = "0453456A";
	String uai2 = "0362903S";
	
	List<Establishment> expectedEstablishments = new ArrayList<Establishment>();
	expectedEstablishments.add(testedEstablishments.get(uai1));
	expectedEstablishments.add(testedEstablishments.get(uai2));
	
	List<String> expectedTypes = extractTypes(expectedEstablishments);
	
	// Actual result
	String type = "CFA";
	List<Establishment> actualEstablishments = establishmentService.findEstablishmentsByType(type);
	
	List<String> actualTypes = extractTypes(actualEstablishments);
	
	// Test
	Assert.assertTrue(expectedTypes.containsAll(actualTypes));
	Assert.assertTrue(actualTypes.containsAll(expectedTypes));
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByType(String)}.
     * 
     * Tests if the retrieved establishments has the good county numbers.
     */
    @Test
    public void testFindEstablishmentByType3() {
	// Expected establishments
	String uai1 = "0453456A";
	String uai2 = "0362903S";
	
	List<Establishment> expectedEstablishments = new ArrayList<Establishment>();
	expectedEstablishments.add(testedEstablishments.get(uai1));
	expectedEstablishments.add(testedEstablishments.get(uai2));
	
	List<String> expectedCountyNumbers = extractCountyNumbers(expectedEstablishments);
	
	// Actual result
	String type = "CFA";
	List<Establishment> actualEstablishments = establishmentService.findEstablishmentsByType(type);
	
	List<String> actualCountyNumbers = extractCountyNumbers(actualEstablishments);
	
	// Test
	Assert.assertTrue(expectedCountyNumbers.containsAll(actualCountyNumbers));
	Assert.assertTrue(actualCountyNumbers.containsAll(expectedCountyNumbers));
    }
    

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    /**
     * Extracts all the county numbers  of the specified <code>List</code> of <code>establishments</code>.
     * 
     * @param establishments
     * 			The concerned establishments.
     * @return
     * 	the <code>List</code> contianing the county numbers of the establishments.<br/>
     * 	an empty <code>List</code> if no county numbers has been extracted.
     */
    private List<String> extractCountyNumbers(List<Establishment> establishments) {
	List<String> countyNumbers = new ArrayList<String>();
	for (Establishment establishment : establishments) {
	    countyNumbers.add(establishment.getCountyNumber());
	}
	return countyNumbers;
    }
    
    /**
     * Extracts all the types  of the specified <code>List</code> of <code>establishments</code>.
     * 
     * @param establishments
     * 			The concerned establishments.
     * @return
     * 	the <code>List</code> contianing the types of the establishments.<br/>
     * 	an empty <code>List</code> if no types has been extracted.
     */
    private List<String> extractTypes(List<Establishment> establishments) {
	List<String> types = new ArrayList<String>();
	for (Establishment establishment : establishments) {
	    types.add(establishment.getType());
	}
	return types;
    }
    
    /**
     * Extracts all the UAIs of the specified <code>List</code> of <code>establishments</code>.
     * 
     * @param establishments
     * 			The concerned establishments.
     * @return
     * 	the <code>List</code> contianing the UAIs of the establishments.<br/>
     * 	an empty <code>List</code> if no UAIs has been extracted.
     */
    private List<String> extractUais(List<Establishment> establishments) {
	List<String> uais = new ArrayList<String>();
	for (Establishment establishment : establishments) {
	    uais.add(establishment.getUai());
	}
	return uais;
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
