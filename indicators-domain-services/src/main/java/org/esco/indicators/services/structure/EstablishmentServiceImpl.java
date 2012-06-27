/**
 * 
 */
package org.esco.indicators.services.structure;

import java.util.HashSet;
import java.util.List;
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
     * @see org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByCountyNumbersAndTypes(java.util.List, java.util.List)
     */
    @Override
    public Set<Establishment> findEstablishmentsByCountyNumbersAndTypes(List<Integer> countyNumbers,
	    List<String> types) {
	// Final result
	Set<Establishment> establishments = new HashSet<Establishment>();
	
	// If at least one county number, and one type are provided
	if(!countyNumbers.isEmpty() && !types.isEmpty()) {
	    LOGGER.debug("At least one country number, and one establishment type, have been provided..");
	    establishments = establishmentDao.findEstablishmentsByCountyNumbersAndTypes(countyNumbers, types);
	} 
	// If no county number is provided, and at least one type is provided
	else if (countyNumbers.isEmpty() && !types.isEmpty()) {
	    LOGGER.debug("No county number has been given for filtering establishments. Establishments will only be filtered by types.");
	    establishments = establishmentDao.findEstablishmentsByTypes(types);
	}
	// If at least one county number is provided, and no type is provided
	else if (!countyNumbers.isEmpty() && types.isEmpty()) {
	    LOGGER.debug("No establishment type has been given for filtering establishments. Establishments will only be filtered by county numbers.");
	    establishments = establishmentDao.findEstablishmentsByCountyNumbers(countyNumbers);
	}
	// If no country number, and no type are provided
	else {
	    LOGGER.warn("Neither country number nor establishment type has been provided.");
	}

	// Filling the names of the establishments
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
