/**
 * 
 */
package org.esco.indicators.services.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.esco.indicators.dao.structure.EstablishmentDao;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.ldap.LdapEstablishmentService;
import org.esco.indicators.utils.constants.structure.EstablishmentConstants;

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

    //------------------------------------------------------------------------------ STATIC METHODS
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByUai(java.lang.String)
     */
    @Override
    public List<Establishment> findEstablishmentsByUais(List<String> uais) {
	// Final result
	List<Establishment> establishments = new ArrayList<Establishment>();
	
	// Retrieval of the establishments by their UAI
	for (String uai : uais) {
	    Establishment establishment = findEstablishmentByUai(uai);
	    if(establishment != null) {
		establishments.add(establishment);
	    }
	}
	
	return establishments;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByCountyNumber(java.lang.Integer)
     */
    @Override
    public List<Establishment> findEstablishmentsByCountyNumber(String countyNumber) {
	List<Establishment> establishments = establishmentDao.findEstablishmentsByCountyNumber(countyNumber);
	fillEstablishmentsNames(establishments);
	return establishments;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByCountyNumbersAndTypes(java.util.List, java.util.List)
     */
    @Override
    public List<Establishment> findEstablishmentsByCountyNumbersAndTypes(List<String> countyNumbers,
	    List<String> types) {
	// Final result
	List<Establishment> establishments = new ArrayList<Establishment>();
	
	// If at least one county number, and one type are provided
	if(!countyNumbers.isEmpty() && !types.isEmpty()) {
	    LOGGER.debug("At least one country number, and one establishment type, have been provided.");
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
	fillEstablishmentsNames(establishments);
	
	return establishments;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByPropertiesNamesAndValues(java.util.HashMap)
     */
    @Override
    public List<Establishment> findEstablishmentsByPropertiesNamesAndValues(
            HashMap<String, Set<String>> propertiesNamesAndValues) {
	 // Final result
	Set<Establishment> establishments = new HashSet<Establishment>();
	
	// Retrieves the establishments by properties names and values
	for (String propertyName : propertiesNamesAndValues.keySet()) {
	    Set<String> values = propertiesNamesAndValues.get(propertyName);
	    establishments.addAll(findEstablishmentsByPropertyNameAndValues(propertyName, values));
	}
	
	// Fill the names of the establishments
	List<Establishment> establishmentsList = new ArrayList<Establishment>(establishments);
	fillEstablishmentsNames(establishmentsList);
	
	return establishmentsList;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsByType(java.lang.String)
     */
    @Override
    public List<Establishment> findEstablishmentsByType(String type) {
        List<Establishment> establishments = establishmentDao.findEstablishmentsByType(type);
        fillEstablishmentsNames(establishments);
        return establishments;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.structure.EstablishmentService#findEstablishmentsUaiByCounty(java.lang.String, java.util.List)
     */
    @Override
    public List<String> findEstablishmentsUaiByCounty(String countyNumber, List<String> establishmentsTypes) {
        // Final result
        List<String> establishmentsUai = new ArrayList<String>();
        
        // New list for the county number
        List<String> countyNumbers = new ArrayList<String>();
        countyNumbers.add(countyNumber);
        
        // Retrieval of the establishments of the county
        List<Establishment> establishments = findEstablishmentsByCountyNumbersAndTypes(countyNumbers, establishmentsTypes);
        for (Establishment establishment : establishments) {
            establishmentsUai.add(establishment.getUai());
        }
        // Debug message
        LOGGER.debug("The establishments UAIs for the county [" + countyNumber +"]  and the types " + establishmentsTypes + " are " + establishmentsUai );
    
        return establishmentsUai;
    }
    
    //------------------------------------------------------------------------------ STATIC METHODS
    
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    /**
     * Retieves the establishments by property name and values.<br/>
     * The behavior is similar to {@link EstablishmentServiceImpl#findEstablishmentsByPropertiesNamesAndValues(HashMap)}.
     * 
     * @param propertyName
     * 			The name of the property used to retrieved the establishments.
     * @param propertyValues
     * 			The values of the property used to retrieved the establisments.
     * 
     * @return
     * 	the establishments having at least one property value associated to the property name.<br/>
     * 	An empty set if no establishments has been retrieved.
     */
    private Set<Establishment> findEstablishmentsByPropertyNameAndValues(
	    String propertyName, Set<String> propertyValues) {
	// Final result
	Set<Establishment> establishments = new HashSet<Establishment>();
	
	// Put the values into a list
	List<String> valuesList = new ArrayList<String>(propertyValues);
	
	// Retrieves the establishments by property
	List<Establishment> retrievedEstbalishments = new ArrayList<Establishment>();
	if(propertyName.equalsIgnoreCase(EstablishmentConstants.ESTAB_PROPERTY_COUNTY_NUMBER)) {
	    retrievedEstbalishments = establishmentDao.findEstablishmentsByCountyNumbers(valuesList);
	} else if (propertyName.equalsIgnoreCase(EstablishmentConstants.ESTAB_PROPERTY_TYPE)) {
	    retrievedEstbalishments = establishmentDao.findEstablishmentsByTypes(valuesList);
	} else if (propertyName.equalsIgnoreCase(EstablishmentConstants.ESTAB_PROPERTY_UAI)) {
	    retrievedEstbalishments = establishmentDao.findEstablishmentByUais(valuesList);
	} else {
	    // If the property name is unknown
	    LOGGER.warn("The establishment property [" + propertyName + "] is unknown and is cannot be used for retrieving establishments");
	}

	 // Put the establishments into the set for removing duplicated entries
	establishments.addAll(retrievedEstbalishments);
	return establishments;
    }

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
    
    /**
     * Fills the names of the specified <code>establishments</code>.
     * 
     * @param establishments
     * 			The establishments to fill.
     */
    private void fillEstablishmentsNames(List<Establishment> establishments) {
	// Fill the name of each establishment
	for (Establishment establishment : establishments) {
	    fillEstablishmentName(establishment);
	}
    }
    //------------------------------------------------------------------------------ STATIC METHODS
}
