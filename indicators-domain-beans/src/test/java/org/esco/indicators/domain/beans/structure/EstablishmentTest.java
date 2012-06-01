/**
 * 
 */
package org.esco.indicators.domain.beans.structure;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @since : 
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Test method for {@link org.esco.indicators.domain.beans.structure.Establishment#Establishment(java.lang.Integer, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testEstablishment1() {
	String escouai = "0123456A";
	Integer countyNumber = new Integer(45);
	String type = "lycee";
	
	Establishment establishment = new Establishment(countyNumber, escouai, type);
	
	assertTrue(establishment.getUai().equals(escouai));
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.structure.Establishment#Establishment(java.lang.Integer, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testEstablishment2() {
	String escouai = "0123456A";
	Integer countyNumber = new Integer(45);
	String type = "lycee";
	
	Establishment establishment = new Establishment(countyNumber, escouai, type);
	
	assertTrue(establishment.getCountyNumber().equals(countyNumber));
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.structure.Establishment#Establishment(java.lang.Integer, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testEstablishment3() {
	String escouai = "0123456A";
	Integer countyNumber = new Integer(45);
	String type = "lycee";
	
	Establishment establishment = new Establishment(countyNumber, escouai, type);
	
	assertTrue(establishment.getType().equals(type));
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
