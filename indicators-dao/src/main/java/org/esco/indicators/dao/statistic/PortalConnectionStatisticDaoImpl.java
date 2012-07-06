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
import org.esco.indicators.domain.beans.statistic.MonthlyPortalConnectionStatistic;
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
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyNumVisitorsAboveTreshold(java.lang.String, java.util.Date, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTreshold(String establishmentUai, Date firstWeekDay,
            String userProfile, Integer treshold) {
        // Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findNumVisitorsAboveTreshold";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfile", userProfile);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : null);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyNumVisitorsBelowTreshold(java.lang.String, java.util.Date, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsBelowTreshold(String establishmentUai, Date firstWeekDay,
            String userProfile, Integer treshold) {
        // Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findNumVisitorsBelowTreshold";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfile", userProfile);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : null);
	
	return numVisitors;
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
	// Final result : number of connections
	Integer numConnections = 0;
	
	// Computation of the number of connections
	List<MonthlyPortalConnectionStatistic> statistics = findMonthlyStatisticsByProfile(establishmentUai, firstMonthDay, userProfile);
	for (MonthlyPortalConnectionStatistic statistic : statistics) {
	    Integer numUsers = statistic.getNumUsers();
	    Integer numConn = statistic.getNumConnections();
	    numConnections += numConn * numUsers;
	}
	
	return numConnections;
    }

      /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findMonthlyNumVisitorsAboveTreshold(java.lang.String, java.util.Date, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsAboveTreshold(String establishmentUai, Date firstMonthDay,
            String userProfile, Integer treshold) {
        // Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findNumVisitorsAboveTreshold";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfile", userProfile);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : null);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findMonthlyNumVisitorsBelowTreshold(java.lang.String, java.util.Date, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsBelowTreshold(String establishmentUai, Date firstMonthDay,
            String userProfile, Integer treshold) {
        // Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findNumVisitorsBelowTreshold";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfile", userProfile);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : null);
	
	return numVisitors;
    }
    
    // ----------------------------------------------------------------------------- PRIVATE METHODS



    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    /**
     * Retrieves the statistics on connections made on the portal for the specified user profile
     * <code>userprofile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This statistics only concerns the week beggining with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentUai
     *            The UAI of the establishment concerned by the statistics.
     * @param firstWeekDay
     *            The first day of the week associated to the statistics.
     * @param userProfile
     *            The user profile concerned by the statistics.
     * @return 
     * 	the list of statistics on the connections made on the portal.<br/>
     *         an empty list if no statistic has been retrieved.
     */
    private List<WeeklyPortalConnectionStatistic> findWeeklyStatisticsByProfile(String establishmentUai, Date firstWeekDay,
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
    /**
     * Retrieves the statistics on connections made on the portal for the specified user profile
     * <code>userprofile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This statistics only concerns the month beggining with the day <code>firstMonthDay</code>.<br/>
     * 
     * @param establishmentUai
     *            The UAI of the establishment concerned by the statistics.
     * @param firstMonthDay
     *            The first day of the week associated to the statistics.
     * @param userProfile
     *            The user profile concerned by the statistics.
     * @return 
     * 	the list of statistics on the connections made on the portal.<br/>
     *         an empty list if no statistic has been retrieved.
     */
    private List<MonthlyPortalConnectionStatistic> findMonthlyStatisticsByProfile(String establishmentUai, Date firstMonthDay,
	    String userProfile) {
	// Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findStatisticsByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfile", userProfile);
	
	// Retrieval of the result
	List<MonthlyPortalConnectionStatistic> result = new ArrayList<MonthlyPortalConnectionStatistic>();
	List<Object> queryResult = QueryManager.getResultList(entityManager, namedQuery, parameters);
	for (Object object : queryResult) {
	    result.add( (MonthlyPortalConnectionStatistic) object );
	}
	
	return result;
    }
    
    // ------------------------------------------------------------------------------ STATIC METHODS
}
