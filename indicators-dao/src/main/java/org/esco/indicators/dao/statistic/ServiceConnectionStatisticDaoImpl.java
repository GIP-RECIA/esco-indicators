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
import org.esco.indicators.domain.beans.statistic.ServiceConnectionStatistic;
import org.esco.indicators.utils.dao.Parameters;
import org.esco.indicators.utils.dao.QueryManager;

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
	// Name of the query to execute
	String namedQuery = "ServiceConnectionStatistic.Daily.findNumConnectionsByProfile";
	
	// Parameters
	Parameters parameters = new Parameters();
	parameters.put("day", day);
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("serviceName", serviceName);
	parameters.put("userProfile", userProfile);

	// Retrieval of the daily statistic
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numConnections = (result != null ? result.intValue() : null);

	return numConnections;
    }

    // ----------------------------------------------------------------------------- PRIVATE METHODS
    
    // ------------------------------------------------------------------------------ STATIC METHODS
}
