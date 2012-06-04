/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.statistic.EstablishmentVisitStatistic;
import org.esco.indicators.domain.beans.statistic.ServiceConnectionStatistic;
import org.springframework.util.NumberUtils;

/**
 * Implementation of the {@link ServiceConnectionStatistic} interface.
 * 
 * @since 2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ServiceConnectionStatisticDaoImpl implements ServiceConnectionStatisticDao {
    // ---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ServiceConnectionStatisticDaoImpl.class);

    /** JPA Entity manager */
    @PersistenceContext
    private EntityManager entityManager;

    // -------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link ServiceConnectionStatisticDaoImpl} class.
     */
    public ServiceConnectionStatisticDaoImpl() {
	super();
    }

    // --------------------------------------------------------------------------- GETTERS / SETTERS

    // ------------------------------------------------------------------------------ PUBLIC METHODS
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.esco.indicators.dao.statistic.ServiceConnectionStatisticDao#findDailyNumConnections(java.util.Date,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Integer findDailyNumConnectionsByProfile(Date day, String establishmentUai,
	    String serviceName, String userProfile) {
	// Creation of the query and setting of the parameters
	Query query = entityManager
		.createNamedQuery("ServiceConnectionStatistic.Daily.findNumConnectionsByProfile");
	query.setParameter("day", day);
	query.setParameter("establishmentUai", establishmentUai);
	query.setParameter("serviceName", serviceName);
	query.setParameter("userProfile", userProfile);

	// Retrieval of the daily statistic
	Integer numConnections = null;
	try {
	    Long result = (Long) query.getSingleResult();
	    numConnections = (result != null ? result.intValue() : null);
	} catch (NoResultException e) {
	    LOGGER.debug("No daily statistic of has been found for the day : [" + day.toString()
		    + "] for the establishment UAI : [" + establishmentUai + "] for the service name : ["
		    + serviceName + "] for the user profile [" + userProfile + "]");
	} catch (NonUniqueResultException e) {
	    LOGGER.warn("More than one daily statistic has been found for the day : [" + day.toString()
		    + "] for the establishment UAI : [" + establishmentUai + "] for the service name : ["
		    + serviceName + "] for the user profile [" + userProfile + "]");
	} catch (IllegalStateException e) {
	    LOGGER.error("An error occured during the retrieval of the daily statistic for the day : ["
		    + day.toString() + "] for the establishment UAI : [" + establishmentUai
		    + "] for the service name : [" + serviceName + "] for the user profile [" + userProfile
		    + "]");
	}

	return numConnections;
    }

    // ----------------------------------------------------------------------------- PRIVATE METHODS

    // ------------------------------------------------------------------------------ STATIC METHODS
}
