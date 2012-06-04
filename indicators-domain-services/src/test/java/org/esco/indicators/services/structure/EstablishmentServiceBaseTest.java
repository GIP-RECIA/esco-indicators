/**
 * 
 */
package org.esco.indicators.services.structure;

import java.util.HashMap;
import java.util.Map;

import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.utils.constants.EstablishmentConstants;
import org.junit.Before;
import org.junit.Test;

/**
 * Class used to centralize the initialization of the data for the tests on 
 * the {@link EstablishmentService}.
 * 
 * @since 2012/05/31
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentServiceBaseTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** 
     * Map of tested establishments.
     * 	Key of the map : UAI of the establishment.
     * 	Value of the map : the establishment associated to the UAI.
     */
    protected Map<String, Establishment> testedEstablishments = null;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    
    /**
     * Method launched before the execution of the unit tests.<br/>
     * Initializes the map containing the tested establishments.<br/>
     * The tested establishments are establishments that are presents into the test database.<br/>
     * The content of this database can be found in the file : src/test/resources/import.sql
     */
    @Before
    public void setUp() {
	/////////////////////////////////////////////////////////////
	// TESTED ESTABLISHMENTS
	/////////////////////////////////////////////////////////////
	
	// Initialization of the tested establishments map
	testedEstablishments = new HashMap<String, Establishment>();
	
	///////////////////////////////
	// Establishments types
	///////////////////////////////
	String typeCFA = EstablishmentConstants.ESTABLISHMENT_TYPE_CFA;
	String typeLEGT = EstablishmentConstants.ESTABLISHMENT_TYPE_LEGT;
	String typeLA = EstablishmentConstants.ESTABLISHMENT_TYPE_LA;
	
	///////////////////////////////
	// Establishments county 
	// numbers
	///////////////////////////////
	Integer county36 = 36;
	Integer county37 = 37;
	Integer county45 = 45;
	
	///////////////////////////////
	// CFA
	///////////////////////////////
	String name = "CFA TEST 45";
	String uai = "0453456A";
	Establishment establishment = new Establishment(county45, uai, typeCFA);
	establishment.setName(name);
	testedEstablishments.put(uai, establishment);
	
	name = "CFA TEST 36";
	uai = "0362903S";
	establishment = new Establishment(county36, uai, typeCFA);
	establishment.setName(name);
	testedEstablishments.put(uai, establishment);
	
	///////////////////////////////
	// LYCEES
	///////////////////////////////
	name = "LYCEE TEST 45";
	uai = "0456782B";
	establishment = new Establishment(county45, uai, typeLEGT);
	establishment.setName(name);
	testedEstablishments.put(uai, establishment);
	
	///////////////////////////////
	// LA
	///////////////////////////////
	name = "LA TEST 37";
	uai = "0376543R";
	establishment = new Establishment(county37, uai, typeLA);
	establishment.setName(name);
	testedEstablishments.put(uai, establishment);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    /**
     * Test used to avoid error at compilation time.
     */
    @Test
    public void testNoError() {
	// Fake test used to avoid error at compilation time
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
