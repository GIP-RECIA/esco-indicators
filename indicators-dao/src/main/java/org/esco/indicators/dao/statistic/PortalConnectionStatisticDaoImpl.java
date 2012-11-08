/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.sql.Date;
import java.util.ArrayList;
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
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyConnectionsAverageDurationByProfile(java.lang.String, java.sql.Date, java.lang.String)
     */
    @Override
    public Float findWeeklyConnectionsAverageDurationByProfile(String establishmentUai, Date firstWeekDay,
            String userProfile) {
	// Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findConnectionsAverageDurationByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfile", userProfile);
	
	// Retrieval of the result
	Double result = (Double) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Float averageDurationTime = (result != null ? result.floatValue() : null);
	
	return averageDurationTime;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyNumConnections(java.lang.String, java.sql.Date)
     */
    @Override
    public Integer findWeeklyNumConnections(String establishmentUai, Date firstWeekDay) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyNumConnectionsByProfile(java.util.List, java.sql.Date, java.lang.String)
     */
    @Override
    public Integer findWeeklyNumConnectionsByProfile(List<String> establishmentsUai, Date firstWeekDay,
	    String userProfile) {
	// Final result : number of connections
	Integer numConnections = 0;
	
	// Computation of the number of connections
	List<WeeklyPortalConnectionStatistic> statistics = findWeeklyStatisticsByProfile(establishmentsUai, firstWeekDay, userProfile);
	for (WeeklyPortalConnectionStatistic statistic : statistics) {
	    Integer numUsers = statistic.getNumUsers();
	    Integer numConn = statistic.getNumConnections();
	    numConnections += numConn * numUsers;
	}
	
	return numConnections;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyNumVisitorsAboveTreshold(java.lang.String, java.sql.Date, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTreshold(List<String> establishmentsUai, Date firstWeekDay,
            Integer treshold) {
        // Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findNumVisitorsAboveTreshold";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : null);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyNumVisitorsAboveTreshold(java.lang.String, java.util.Date, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTresholdByProfile(List<String> establishmentsUai, Date firstWeekDay,
            String userProfile, Integer treshold) {
        // Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findNumVisitorsAboveTresholdByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfile", userProfile);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : null);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyNumVisitorsBelowTreshold(java.lang.String, java.sql.Date, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsBelowTreshold(List<String> establishmentsUai, Date firstWeekDay,
            Integer treshold) {
        // Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findNumVisitorsBelowTreshold";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
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
    public Integer findWeeklyNumVisitorsBelowTresholdByProfile(List<String> establishmentsUai, Date firstWeekDay,
            String userProfile, Integer treshold) {
        // Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findNumVisitorsBelowTresholdByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
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
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findMonthlyConnectionsAverageDurationByProfile(java.lang.String, java.sql.Date, java.lang.String)
     */
    @Override
    public Float findMonthlyConnectionsAverageDurationByProfile(String establishmentUai,
            Date firstMonthDay, String userProfile) {
	// Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findConnectionsAverageDurationByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfile", userProfile);
	
	// Retrieval of the result
	Double result = (Double) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Float averageDurationTime = (result != null ? result.floatValue() : null);
	
	return averageDurationTime;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findMonthlyNumConnectionsByProfile(java.util.List, java.sql.Date, java.lang.String)
     */
    @Override
    public Integer findMonthlyNumConnectionsByProfile(List<String> establishmentsUai, Date firstMonthDay,
	    String userProfile) {
	// Final result : number of connections
	Integer numConnections = 0;
	
	// Computation of the number of connections
	List<MonthlyPortalConnectionStatistic> statistics = findMonthlyStatisticsByProfile(establishmentsUai, firstMonthDay, userProfile);
	for (MonthlyPortalConnectionStatistic statistic : statistics) {
	    Integer numUsers = statistic.getNumUsers();
	    Integer numConn = statistic.getNumConnections();
	    numConnections += numConn * numUsers;
	}
	
	return numConnections;
    }

      /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findMonthlyNumVisitorsAboveTreshold(java.lang.String, java.sql.Date, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsAboveTreshold(List<String> establishmentsUai, Date firstMonthDay,
            Integer treshold) {
        // Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findNumVisitorsAboveTreshold";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : null);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findMonthlyNumVisitorsAboveTreshold(java.lang.String, java.util.Date, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsAboveTresholdByProfile(List<String> establishmentsUai, Date firstMonthDay,
            String userProfile, Integer treshold) {
        // Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findNumVisitorsAboveTresholdByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfile", userProfile);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : null);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findMonthlyNumVisitorsBelowTreshold(java.lang.String, java.sql.Date, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsBelowTreshold(List<String> establishmentsUai, Date firstMonthDay,
            Integer treshold) {
        // Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findNumVisitorsBelowTreshold";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
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
    public Integer findMonthlyNumVisitorsBelowTresholdByProfile(List<String> establishmentUai, Date firstMonthDay,
            String userProfile, Integer treshold) {
        // Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findNumVisitorsBelowTresholdByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentUai);
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
     * <code>userprofile</code> and the specified establishments UAI <code>establishmentsUai</code>.<br/>
     * This statistics only concerns the week beggining with the day <code>firstWeekDay</code>.<br/>
     * 
     * @param establishmentsUai
     *            The UAI of the establishments concerned by the statistics.
     * @param firstWeekDay
     *            The first day of the week associated to the statistics.
     * @param userProfile
     *            The user profile concerned by the statistics.
     * @return 
     * 	the list of statistics on the connections made on the portal.<br/>
     *         an empty list if no statistic has been retrieved.
     */
    private List<WeeklyPortalConnectionStatistic> findWeeklyStatisticsByProfile(List<String> establishmentsUai, Date firstWeekDay,
	    String userProfile) {
	// Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findStatisticsByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
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
     * <code>userprofile</code> and the specified establishments UAI <code>establishmentsUai</code>.<br/>
     * This statistics only concerns the month beggining with the day <code>firstMonthDay</code>.<br/>
     * 
     * @param establishmentsUai
     *            The UAI of the establishments concerned by the statistics.
     * @param firstMonthDay
     *            The first day of the week associated to the statistics.
     * @param userProfile
     *            The user profile concerned by the statistics.
     * @return 
     * 	the list of statistics on the connections made on the portal.<br/>
     *         an empty list if no statistic has been retrieved.
     */
    private List<MonthlyPortalConnectionStatistic> findMonthlyStatisticsByProfile(List<String> establishmentsUai, Date firstMonthDay,
	    String userProfile) {
	// Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findStatisticsByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
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
