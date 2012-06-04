/**
 * 
 */
package org.esco.indicators.services.structure;

import java.util.Set;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.structure.EstablishmentDao;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.ldap.LdapEstablishmentService;

/**
 * Implementation of the {@link EstablishmentService} interface.
 * 
 * @since 2012/05/26
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentServiceImpl implements EstablishmentService {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentServiceImpl.class);
    
    /** Establishement DAO */
    private EstablishmentDao establishmentDao;
    
    /** LDAP establishment service */
    private LdapEstablishmentService ldapEstablishmentService;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    /**
     * Gets the establishment DAO used to access data.
     * @return
     * 	the estbalishment dao
     */
    public EstablishmentDao getEstablishmentDao() {
        return establishmentDao;
    }

    /**
     * Sets the establishment DAO used to access data.
     * @param establishmentDao
     * 			The establishment DAO to set.
     */
    public void setEstablishmentDao(EstablishmentDao establishmentDao) {
        this.establishmentDao = establishmentDao;
    }

    /**
     * Gets the LDAP service used to access LDAP establishments data.
     * 
     * @return
     * 	the LDAP establishment service used to access LDAP data.
     */
    public LdapEstablishmentService getLdapEstablishmentService() {
        return ldapEstablishmentService;
    }

    /**
     * Sets the LDAP service used to access LDAP establishments data.
     * 
     * @param ldapEstablishmentService
     * 			The LDAP establishment service to set.
     */
    public void setLdapEstablishmentService(LdapEstablishmentService ldapEstablishmentService) {
        this.ldapEstablishmentService = ldapEstablishmentService;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.structure.EstablishmentService#findEstablishmentByUai(java.lang.String)
     */
    @Override
    public Establishment findEstablishmentByUai(String uai) {
	Establishment establishment = establishmentDao.findEstablishmentByUai(uai);
	fillEstablishmentName(establishment);
	return establishment;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByCountyNumber(java.lang.Integer)
     */
    @Override
    public Set<Establishment> findEstablishmentsByCountyNumber(Integer countyNumber) {
	Set<Establishment> establishments = establishmentDao.findEstablishmentsByCountyNumber(countyNumber);
	for (Establishment establishment : establishments) {
	    fillEstablishmentName(establishment);
	}
	return establishments;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByType(java.lang.String)
     */
    @Override
    public Set<Establishment> findEstablishmentsByType(String type) {
	Set<Establishment> establishments = establishmentDao.findEstablishmentsByType(type);
	for (Establishment establishment : establishments) {
	    fillEstablishmentName(establishment);
	}
	return establishments;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    /**
     * Retrieves the name of an establishment by its UAI.
     * 
     * @param uai
     * 		The UAI of the concerned establishment.
     * @return
     * 		the name of the establishment.<br/>
     * 		an empty <code>String</code> if no name has been found.
     */
    private String findEstablishmentName(String uai) {
	return ldapEstablishmentService.findEstablishmentName(uai);
    }
    
    /**
     * Fills the name of the specified <code>establishment</code>.
     * 
     * @param establishment
     * 			The establishment to fill.
     */
    private void fillEstablishmentName(Establishment establishment) {
	// Fill the name if the establishment is not null and has a UAI
	if(establishment != null) {
	    String uai = establishment.getUai();
	    if(uai != null && !uai.isEmpty()) {
		String name = findEstablishmentName(uai);
		establishment.setName(name);
	    }
	}
    }
    //------------------------------------------------------------------------------ STATIC METHODS
}
