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

/**
 * Implementation of the {@link PortalConnectionStatisticDao} interface.
 * 
 * @since 2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class PortalConnectionStatisticDaoImpl implements PortalConnectionStatisticDao {
    // ---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PortalConnectionStatisticDaoImpl.class);

    /** JPA Entity manager */
    @PersistenceContext
    private EntityManager entityManager;

    // -------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link PortalConnectionStatisticDaoImpl} class.
     */
    public PortalConnectionStatisticDaoImpl() {
	super();
    }

    // --------------------------------------------------------------------------- GETTERS / SETTERS

    // ------------------------------------------------------------------------------ PUBLIC METHODS
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyNumConnectionsByProfile(java
     * .lang.String, java.util.Date, java.lang.String)
     */
    @Override
    public Integer findWeeklyNumConnectionsByProfile(String establishmentUai, Date firstWeekDay,
	    String userProfile) {
	// Creation of the query and setting of the parameter
	Query query = entityManager
		.createNamedQuery("WeeklyPortalConnectionStatistic.findNumConnectionsByProfile");
	query.setParameter("establishmentUai", establishmentUai);
	query.setParameter("firstWeekDay", firstWeekDay);
	query.setParameter("userProfile", userProfile);

	// Retrieval of the daily statistic
	Integer numConnections = null;
	try {
	    Long result = (Long) query.getSingleResult();
	    numConnections = (result != null ? result.intValue() : null);
	} catch (NoResultException e) {
	    LOGGER.debug("No daily statistic of has been found for the week beggining with day : ["
		    + firstWeekDay.toString() + "] for the establishment UAI : [" + establishmentUai
		    + "] for the user profile [" + userProfile + "]");
	} catch (NonUniqueResultException e) {
	    LOGGER.warn("More than one daily statistic has been found for the week beggining with day : ["
		    + firstWeekDay.toString() + "] for the establishment UAI : [" + establishmentUai
		    + "] for the user profile [" + userProfile + "]");
	} catch (IllegalStateException e) {
	    LOGGER.error("An error occured during the retrieval of the daily statistic for the week beggining with day : ["
		    + firstWeekDay.toString()
		    + "] for the establishment UAI : ["
		    + establishmentUai
		    + "] for the user profile [" + userProfile + "]");
	}

	return numConnections;
    }

    // ----------------------------------------------------------------------------- PRIVATE METHODS

    // ------------------------------------------------------------------------------ STATIC METHODS
}
