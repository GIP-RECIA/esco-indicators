/**
 * 
 */
package org.esco.indicators.services.structure;

import java.util.HashSet;
import java.util.Set;

import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.utils.constants.EstablishmentConstants;
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
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByCountyNumber(Integer)}.
     * 
     * Tests if the retrieved establishments has the good UAIs.
     */
    @Test
    public void testFindEstablishmentByCountyNumber1() {
	// Expected establishments
	String uai1 = "0453456A";
	String uai2 = "0456782B";
	
	Set<Establishment> expectedEstablishments = new HashSet<Establishment>();
	expectedEstablishments.add(testedEstablishments.get(uai1));
	expectedEstablishments.add(testedEstablishments.get(uai2));
	
	Set<String> expectedUais = extractUais(expectedEstablishments);
	
	// Actual result
	Integer countyNumber = 45;
	Set<Establishment> actualEstablishments = establishmentService.findEstablishmentsByCountyNumber(countyNumber);
	
	Set<String> actualUais = extractUais(actualEstablishments);
	
	// Test
	Assert.assertEquals(expectedUais, actualUais);
    }
    
    
    /**
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByCountyNumber(Integer)}.
     * 
     * Tests if the retrieved establishments has the good types.
     */
    @Test
    public void testFindEstablishmentByCountyNumber2() {
	// Expected establishments
	String uai1 = "0453456A";
	String uai2 = "0456782B";
	
	Set<Establishment> expectedEstablishments = new HashSet<Establishment>();
	expectedEstablishments.add(testedEstablishments.get(uai1));
	expectedEstablishments.add(testedEstablishments.get(uai2));
	
	Set<String> expectedTypes = extractTypes(expectedEstablishments);
	
	// Actual result
	Integer countyNumber = 45;
	Set<Establishment> actualEstablishments = establishmentService.findEstablishmentsByCountyNumber(countyNumber);
	
	Set<String> actualTypes = extractTypes(actualEstablishments);
	
	// Test
	Assert.assertEquals(expectedTypes, actualTypes);
    }
    
    /**
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByCountyNumber(Integer)}.
     * 
     * Tests if the retrieved establishments has the good county numbers.
     */
    @Test
    public void testFindEstablishmentByCountyNumber3() {
	// Expected establishments
	String uai1 = "0453456A";
	String uai2 = "0456782B";
	
	Set<Establishment> expectedEstablishments = new HashSet<Establishment>();
	expectedEstablishments.add(testedEstablishments.get(uai1));
	expectedEstablishments.add(testedEstablishments.get(uai2));
	
	Set<Integer> expectedCountyNumbers = extractCountyNumbers(expectedEstablishments);
	
	// Actual result
	Integer countyNumber = 45;
	Set<Establishment> actualEstablishments = establishmentService.findEstablishmentsByCountyNumber(countyNumber);
	
	Set<Integer> actualCountyNumbers = extractCountyNumbers(actualEstablishments);
	
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
	
	Set<Establishment> expectedEstablishments = new HashSet<Establishment>();
	expectedEstablishments.add(testedEstablishments.get(uai1));
	expectedEstablishments.add(testedEstablishments.get(uai2));
	
	Set<String> expectedUais = extractUais(expectedEstablishments);
	
	// Actual result
	Set<Establishment> actualEstablishments = establishmentService
		.findEstablishmentsByType(EstablishmentConstants.ESTABLISHMENT_TYPE_CFA);

	Set<String> actualUais = extractUais(actualEstablishments);
	
	// Test
	Assert.assertEquals(expectedUais, actualUais);
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
	
	Set<Establishment> expectedEstablishments = new HashSet<Establishment>();
	expectedEstablishments.add(testedEstablishments.get(uai1));
	expectedEstablishments.add(testedEstablishments.get(uai2));
	
	Set<String> expectedTypes = extractTypes(expectedEstablishments);
	
	// Actual result
	String type = "CFA";
	Set<Establishment> actualEstablishments = establishmentService.findEstablishmentsByType(type);
	
	Set<String> actualTypes = extractTypes(actualEstablishments);
	
	// Test
	Assert.assertEquals(expectedTypes, actualTypes);
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
	
	Set<Establishment> expectedEstablishments = new HashSet<Establishment>();
	expectedEstablishments.add(testedEstablishments.get(uai1));
	expectedEstablishments.add(testedEstablishments.get(uai2));
	
	Set<Integer> expectedCountyNumbers = extractCountyNumbers(expectedEstablishments);
	
	// Actual result
	String type = "CFA";
	Set<Establishment> actualEstablishments = establishmentService.findEstablishmentsByType(type);
	
	Set<Integer> actualCountyNumbers = extractCountyNumbers(actualEstablishments);
	
	// Test
	Assert.assertEquals(expectedCountyNumbers, actualCountyNumbers);
    }
    

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    /**
     * Extracts all the county numbers  of the specified <code>Set</code> of <code>establishments</code>.
     * 
     * @param establishments
     * 			The concerned establishments.
     * @return
     * 	the <code>Set</code> contianing the county numbers of the establishments.<br/>
     * 	an empty <code>Set</code> if no county numbers has been extracted.
     */
    private Set<Integer> extractCountyNumbers(Set<Establishment> establishments) {
	Set<Integer> countyNumbers = new HashSet<Integer>();
	for (Establishment establishment : establishments) {
	    countyNumbers.add(establishment.getCountyNumber());
	}
	return countyNumbers;
    }
    
    /**
     * Extracts all the types  of the specified <code>Set</code> of <code>establishments</code>.
     * 
     * @param establishments
     * 			The concerned establishments.
     * @return
     * 	the <code>Set</code> contianing the types of the establishments.<br/>
     * 	an empty <code>Set</code> if no types has been extracted.
     */
    private Set<String> extractTypes(Set<Establishment> establishments) {
	Set<String> types = new HashSet<String>();
	for (Establishment establishment : establishments) {
	    types.add(establishment.getType());
	}
	return types;
    }
    
    /**
     * Extracts all the UAIs of the specified <code>Set</code> of <code>establishments</code>.
     * 
     * @param establishments
     * 			The concerned establishments.
     * @return
     * 	the <code>Set</code> contianing the UAIs of the establishments.<br/>
     * 	an empty <code>Set</code> if no UAIs has been extracted.
     */
    private Set<String> extractUais(Set<Establishment> establishments) {
	Set<String> uais = new HashSet<String>();
	for (Establishment establishment : establishments) {
	    uais.add(establishment.getUai());
	}
	return uais;
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
