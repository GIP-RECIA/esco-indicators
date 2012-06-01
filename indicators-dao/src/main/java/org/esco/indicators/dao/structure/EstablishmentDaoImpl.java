/**
 * 
 */
package org.esco.indicators.dao.structure;

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
 * @since : 2012/05/25
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentDaoImpl extends AbstractGenericJPADaoService implements EstablishmentDao {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentDaoImpl.class);

    /** JPA Entity manager */
    @PersistenceContext
    private EntityManager entityManager;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the EstablishmentDaoImpl class
     */
    public EstablishmentDaoImpl() {
	super();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the entity manager used by the EstablishmentDaoImpl.
     * 
     * @param entityManager
     * 			The entity manager to set.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	// TODO Auto-generated method stub
	
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.structure.EstablishmentDao#getEstablishmentByUai(java.lang.String)
     */
    @Override
    public Establishment findEstablishmentByUai(String uai) {
	// Create the query and sets the parameters
	Query query = entityManager.createNamedQuery("findEstablishmentByUai");
	query.setParameter("uai", uai);
	
	// Try to retrieve the establishment associated to the uai
	Establishment establishment = null;
	try {
	    establishment = (Establishment) query.getSingleResult();
	} catch (NoResultException e) {
	    LOGGER.debug("No establishment has been found with the UAI : " + uai);
	} catch (NonUniqueResultException e) {
	    LOGGER.warn("More than one establishment has been found with the UAI : " + uai);
	} catch (IllegalStateException e) {
	    LOGGER.error("An error occured during the retrieval of the establishment with the UAI : " + uai);
	}
	
	return establishment;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.structure.EstablishmentDao#getEstablishmentsByCountyNumber(java.lang.String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public Set<Establishment> findEstablishmentsByCountyNumber(Integer countyNumber) {
	// Create the query and sets the parameters
	Query query = entityManager.createNamedQuery("findEstablishmentsByCountyNumber");
	query.setParameter("countyNumber", countyNumber);
	
	// Try to retrieve the establishments associated to the county number
	Set<Establishment> establishments = new HashSet<Establishment>();
	try {
	    List<Establishment> result =query.getResultList();
	    establishments.addAll(result);
	} catch (IllegalStateException e) {
	    LOGGER.error("An error occured during the retrieval of the establishments with the county number : " + countyNumber);
	}
	
	return establishments;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.structure.EstablishmentDao#getEstablishmentsByType(java.lang.String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public Set<Establishment> findEstablishmentsByType(String type) {
	// Create the query and sets the parameters
	Query query = entityManager.createNamedQuery("findEstablishmentsByType");
	query.setParameter("type", type);
	
	// Try to retrieve the establishments associated to the type
	Set<Establishment> establishments = new HashSet<Establishment>();
	try {
	    List<Establishment> result =query.getResultList();
	    establishments.addAll(result);
	} catch (IllegalStateException e) {
	    LOGGER.error("An error occured during the retrieval of the establishments with the type : " + type);
	}
	
	return establishments;
    }

    //--------------------------------------------------------------------------- PROTECTED METHODS
    /* (non-Javadoc)
     * @see org.esupportail.commons.dao.AbstractGenericJPADaoService#getEntityManager()
     */
    @Override
    protected EntityManager getEntityManager() {
	return entityManager;
    }
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
