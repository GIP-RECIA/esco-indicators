/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.esco.indicators.utils.dao.Parameters;
import org.esco.indicators.utils.dao.QueryManager;

/**
 * Implementation of the {@link EspecialPortalConnectionStatisticDao} interface.
 * 
 * @since 2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EspecialPortalConnectionStatisticDaoImpl implements EspecialPortalConnectionStatisticDao {
    // ---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EspecialPortalConnectionStatisticDaoImpl.class);

    
    /** JPA Entity manager */
    @PersistenceContext
    private EntityManager entityManager;

    // -------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link EspecialPortalConnectionStatisticDaoImpl} class.
     */
    public EspecialPortalConnectionStatisticDaoImpl() {
	super();
    }

    // --------------------------------------------------------------------------- GETTERS / SETTERS

    // ------------------------------------------------------------------------------ PUBLIC METHODS
    ///////////////////////////////////////////////////////
    // DAILY STATISTICS
    ///////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    
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
	// Name of the query to execute
	String namedQuery = "EspecialWeeklyPortalConnectionStatistic.findNumConnectionsByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfile", userProfile);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numConnections = (result != null ? result.intValue() : null);
	
	return numConnections;
    }

   

    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findMonthlyNumConnectionsByProfile(java.lang.String, java.util.Date, java.lang.String)
     */
    @Override
    public Integer findMonthlyNumConnectionsByProfile(String establishmentUai, Date firstMonthDay,
	    String userProfile) {
	// Name of the query to execute
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findNumConnectionsByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfile", userProfile);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numConnections = (result != null ? result.intValue() : null);
	
	return numConnections;
    }

    // ----------------------------------------------------------------------------- PRIVATE METHODS

    // ------------------------------------------------------------------------------ STATIC METHODS
}
