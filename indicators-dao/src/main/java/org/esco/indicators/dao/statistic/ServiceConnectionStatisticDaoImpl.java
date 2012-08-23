/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    ///////////////////////////////////////////////////////
    // DAILY STATISTICS
    ///////////////////////////////////////////////////////
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.ServiceConnectionStatisticDao#findDailyNumConnectionsByProfile(java.util.Date, java.lang.String, java.util.List, java.lang.String)
     */
    @Override
    public Integer findDailyNumConnectionsByProfile(Date day, String establishmentUai,
	    List<String> servicesNames, String userProfile) {
	// Name of the query to execute
	String namedQuery = "ServiceConnectionStatistic.Daily.findNumConnectionsByProfile";
	
	// Parameters
	Parameters parameters = new Parameters();
	parameters.put("day", day);
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("serviceNameList", servicesNames);
	parameters.put("userProfile", userProfile);

	// Retrieval of the daily statistic
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numConnections = (result != null ? result.intValue() : null);

	return numConnections;
    }

    ///////////////////////////////////////////////////////
    // WEEKLY / MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.ServiceConnectionStatisticDao#findNumVisitorsAboveTreshold(java.util.List, java.util.Date, java.util.Date, java.util.List, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer findNumVisitorsAboveTreshold(List<String> establishmentsUai, Date startDay,
	    Date endDay, List<String> servicesNames, String userProfile, Integer treshold) {
	// Name of the query to execute
	String namedQuery = "ServiceConnectionStatistic.findNumVisitorsAboveTreshold";
	
	// Parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("startDate", startDay);
	parameters.put("endDate", endDay);
	parameters.put("serviceNameList", servicesNames);
	parameters.put("userProfile", userProfile);
	parameters.put("treshold", treshold);

	// Retrieval of the statistic
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : null);

	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.ServiceConnectionStatisticDao#findNumVisitorsBelowTreshold(java.lang.String, java.util.Date, java.util.Date, java.util.List, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer findNumVisitorsBelowTreshold(List<String> establishmentsUai, Date startDay, Date endDay,
            List<String> servicesNames, String userProfile, Integer treshold) {
	// Name of the query to execute
	String namedQuery = "ServiceConnectionStatistic.findNumVisitorsBelowTreshold";
	
	// Parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("startDate", startDay);
	parameters.put("endDate", endDay);
	parameters.put("serviceNameList", servicesNames);
	parameters.put("userProfile", userProfile);
	parameters.put("treshold", treshold);

	// Retrieval of the statistic
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : null);

	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.ServiceConnectionStatisticDao#findNumVisits(java.lang.String, java.util.Date, java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public Integer findNumVisits(List<String> establishmentsUai, Date startDay, Date endDay, List<String> servicesNames,
	    String userProfile) {
	// Name of the query to execute
	String namedQuery = "ServiceConnectionStatistic.findNumVisits";
	
	// Parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("startDate", startDay);
	parameters.put("endDate", endDay);
	parameters.put("serviceNameList", servicesNames);
	parameters.put("userProfile", userProfile);

	// Retrieval of the statistic
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisits = (result != null ? result.intValue() : null);
	
	return numVisits;
    }

    // ----------------------------------------------------------------------------- PRIVATE METHODS
    
    // ------------------------------------------------------------------------------ STATIC METHODS
}
