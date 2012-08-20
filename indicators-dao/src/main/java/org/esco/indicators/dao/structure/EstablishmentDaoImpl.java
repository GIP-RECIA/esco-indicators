/**
 * 
 */
package org.esco.indicators.dao.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esupportail.commons.dao.AbstractGenericJPADaoService;

/**
 * Implementation of the EstablishmentDao interface.
 * 
 * @since 2012/05/25
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentDaoImpl extends AbstractGenericJPADaoService implements EstablishmentDao {
    // ---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentDaoImpl.class);

    /** JPA Entity manager */
    @PersistenceContext
    private EntityManager entityManager;

    // -------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the EstablishmentDaoImpl class
     */
    public EstablishmentDaoImpl() {
	super();
    }

    // --------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the entity manager used by the EstablishmentDaoImpl.
     * 
     * @param entityManager
     *            The entity manager to set.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
	this.entityManager = entityManager;
    }

    // ------------------------------------------------------------------------------ PUBLIC METHODS
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.esco.indicators.dao.structure.EstablishmentDao#getEstablishmentByUai(java.lang.String)
     */
    @Override
    public Establishment findEstablishmentByUai(String uai) {
	// Create the query and sets the parameters
	Query query = entityManager.createNamedQuery("Establishment.findByUai");
	query.setParameter("uai", uai);

	// Try to retrieve the establishment associated to the uai
	Establishment establishment = null;
	try {
	    establishment = (Establishment) query.getSingleResult();
	} catch (NoResultException e) {
	    LOGGER.warn("No establishment has been found with the UAI : " + uai);
	} catch (NonUniqueResultException e) {
	    LOGGER.warn("More than one establishment has been found with the UAI : " + uai);
	} catch (IllegalStateException e) {
	    LOGGER.error("An error occured during the retrieval of the establishment with the UAI : " + uai);
	}

	return establishment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.esco.indicators.dao.structure.EstablishmentDao#getEstablishmentsByCountyNumber(java.lang.String)
     */
    @Override
    public List<Establishment> findEstablishmentsByCountyNumber(String countyNumber) {
	// Creation of the list of county numbers
	List<String> countyNumbers = new ArrayList<String>();
	countyNumbers.add(countyNumber);
	
	return findEstablishmentsByCountyNumbers(countyNumbers);
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.structure.EstablishmentDao#findEstablishmentsByCountyNumbers(java.util.List)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Establishment> findEstablishmentsByCountyNumbers(List<String> countyNumbers) {
	// Create the query and sets the parameters
	Query query = entityManager.createNamedQuery("Establishment.findByCountyNumbers");
	query.setParameter("countyNumberList", countyNumbers);

	// Try to retrieve the establishments associated to the county numbers
	List<Establishment> establishments = new ArrayList<Establishment>();
	try {
	    List<Establishment> result = query.getResultList();
	    establishments.addAll(result);
	} catch (IllegalStateException e) {
	    LOGGER.error("An error occured during the retrieval of the establishments with the county numbers : "
		    + countyNumbers);
	}

	return establishments;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.esco.indicators.dao.structure.EstablishmentDao#findEstablishmentsByCountyNumbersAndTypes(java.util
     * .List, java.util.List)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Establishment> findEstablishmentsByCountyNumbersAndTypes(List<String> countyNumbers,
	    List<String> types) {
	// Create the query and sets the parameters
	Query query = entityManager.createNamedQuery("Establishment.findByCountyNumbersAndTypes");
	query.setParameter("countyNumberList", countyNumbers);
	query.setParameter("typeList", types);

	// Try to retrieve the establishments
	List<Establishment> establishments = new ArrayList<Establishment>();
	try {
	    List<Establishment> result = query.getResultList();
	    establishments.addAll(result);
	} catch (IllegalStateException e) {
	    LOGGER.error("An error occured during the retrieval of the establishments with the county numbers : "
		    + countyNumbers + " and the types : " + types);
	}

	return establishments;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.esco.indicators.dao.structure.EstablishmentDao#getEstablishmentsByType(java.lang.String)
     */
    @Override
    public List<Establishment> findEstablishmentsByType(String type) {
	// Creation of the list of the establishments types
	List<String> types = new ArrayList<String>();
	types.add(type);
	
	return findEstablishmentsByTypes(types);
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.structure.EstablishmentDao#findEstablishmentsByTypes(java.util.List)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Establishment> findEstablishmentsByTypes(List<String> types) {
	// Create the query and sets the parameters
	Query query = entityManager.createNamedQuery("Establishment.findByTypes");
	query.setParameter("typeList", types);

	// Try to retrieve the establishments associated to the type
	List<Establishment> establishments = new ArrayList<Establishment>();
	try {
	    List<Establishment> result = query.getResultList();
	    establishments.addAll(result);
	} catch (IllegalStateException e) {
	    LOGGER.error("An error occured during the retrieval of the establishments with the types : "
		    + types);
	}

	return establishments;
    }

    // --------------------------------------------------------------------------- PROTECTED METHODS
    /*
     * (non-Javadoc)
     * 
     * @see org.esupportail.commons.dao.AbstractGenericJPADaoService#getEntityManager()
     */
    @Override
    protected EntityManager getEntityManager() {
	return entityManager;
    }
    // ----------------------------------------------------------------------------- PRIVATE METHODS

    // ------------------------------------------------------------------------------ STATIC METHODS
}
