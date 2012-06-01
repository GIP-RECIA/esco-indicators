/**
 * 
 */
package org.esco.indicators.services.structure;


import junit.framework.Assert;

import org.easymock.EasyMock;
import org.esco.indicators.dao.structure.EstablishmentDao;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.ldap.LdapEstablishmentService;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests of the EstablishmentService interface. <br/>
 * These tests are realized using mock objects in order to simulate classes/interfaces behaviors.
 * 
 * @since : 26/05/2012 
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentServiceImplMockTest extends EstablishmentServiceBaseTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** {@link EstablishmentServiceImpl} using the <code>establishmentDao</code> to access data */
    private EstablishmentServiceImpl establishmentServiceImpl;
    
    /** {@link EstablishmentDao} mock, used to simulate database access */
    private EstablishmentDao mockEstablishmentDao;
    
    /** {@link LdapEstablishmentService} mock, used to simulate LDAP access */
    private LdapEstablishmentService mockLdapEstablishmentService;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Construcor
     */
    public EstablishmentServiceImplMockTest() {
	super();
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.structure.EstablishmentServiceBaseTest#setUp()
     */
    @Before
    @Override
    public void setUp() {
	super.setUp();
	
	// Initialization of the mock objects
	mockEstablishmentDao = EasyMock.createMock(EstablishmentDao.class);
	mockLdapEstablishmentService = EasyMock.createMock(LdapEstablishmentService.class);
	
	establishmentServiceImpl = new EstablishmentServiceImpl();
	establishmentServiceImpl.setEstablishmentDao(mockEstablishmentDao);
	establishmentServiceImpl.setLdapEstablishmentService(mockLdapEstablishmentService);
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Test method for {@link org.esco.indicators.services.structure.EstablishmentService#findEstablishmentByUai(java.lang.String)}.
     */
    @Test
    public void testFindEstablishmentByUai() {
	// Expected establishment
	String uai = "0453456A";
	Establishment establishment = testedEstablishments.get(uai);
	
	// Setting the DAO mock beahvior 
	// The mock will return the correct establishment
	EasyMock.expect(mockEstablishmentDao.findEstablishmentByUai(uai)).andReturn(establishment);
	EasyMock.replay(mockEstablishmentDao);
	
	// Setting the LDAP service mock behavior
	// The mock will return the correct establishment name
	EasyMock.expect(mockLdapEstablishmentService.findEstablishmentName(uai)).andReturn(establishment.getName());
	EasyMock.replay(mockLdapEstablishmentService);
	
	// Test
	Establishment actual = establishmentServiceImpl.findEstablishmentByUai(uai);
	Assert.assertEquals(establishment, actual);
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
