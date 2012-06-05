/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.statistic.WeeklyPortalConnectionStatistic;
import org.esco.indicators.utils.dao.Parameters;
import org.esco.indicators.utils.dao.QueryManager;

/**
 * Implementation of the {@link PortalConnectionStatisticDao} interface.
 * 
 * @since 2012/06/05
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
	// Final result : number of connections
	Integer numConnections = 0;
	
	// Computation of the number of connections
	List<WeeklyPortalConnectionStatistic> statistics = findWeeklyStatisticsByProfile(establishmentUai, firstWeekDay, userProfile);
	for (WeeklyPortalConnectionStatistic statistic : statistics) {
	    Integer numUsers = statistic.getNumUsers();
	    Integer numConn = statistic.getNumConnections();
	    numConnections += numConn * numUsers;
	}
	
	return numConnections;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyNumConnectionsByProfile(java
     * .lang.String, java.util.Date, java.lang.String)
     */
    @Override
    public List<WeeklyPortalConnectionStatistic> findWeeklyStatisticsByProfile(String establishmentUai, Date firstWeekDay,
	    String userProfile) {
	// Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findStatisticsByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfile", userProfile);
	
	// Retrieval of the result
	List<WeeklyPortalConnectionStatistic> result = new ArrayList<WeeklyPortalConnectionStatistic>();
	List<Object> queryResult = QueryManager.getResultList(entityManager, namedQuery, parameters);
	for (Object object : queryResult) {
	    result.add( (WeeklyPortalConnectionStatistic) object );
	}
	
	return result;
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
	String namedQuery = "MonthlyPortalConnectionStatistic.findNumConnectionsByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfile", userProfile);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numConnections = (result != null ? result.intValue() : 0);
	
	return numConnections;
    }

    // ----------------------------------------------------------------------------- PRIVATE METHODS

    // ------------------------------------------------------------------------------ STATIC METHODS
}
