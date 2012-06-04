/**
 * 
 */
package org.esco.indicators.domain.beans.people;

import static org.junit.Assert.*;

import org.esco.indicators.domain.beans.structure.Establishment;
import org.junit.Test;

/**
 * @since
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentUserTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Test method for {@link org.esco.indicators.domain.beans.people.EstablishmentUser#linkEstablishment(org.esco.indicators.domain.beans.structure.Establishment)}.
     */
    @Test
    public void testLinkEstablishment1() {
	EstablishmentUser establishmentUser = new EstablishmentUser();
	
	Establishment establishment = new Establishment(45, "0360111A", "lycee");
	establishmentUser.linkEstablishment(establishment);
	
	assertTrue(establishmentUser.getLinkedEstablishments().contains(establishment));
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.people.EstablishmentUser#linkEstablishment(org.esco.indicators.domain.beans.structure.Establishment)}.
     */
    @Test
    public void testLinkEstablishment2() {
	EstablishmentUser establishmentUser = new EstablishmentUser();
	
	Establishment establishment = new Establishment(45, "0360111A", "lycee");
	establishmentUser.linkEstablishment(establishment);
	
	Establishment establishment2 = new Establishment(41, "0456781AB", "college");
	establishmentUser.linkEstablishment(establishment2);
	
	assertTrue(establishmentUser.getLinkedEstablishments().size() == 2);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
