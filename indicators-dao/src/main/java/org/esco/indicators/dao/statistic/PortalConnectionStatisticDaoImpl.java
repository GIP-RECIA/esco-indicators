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
    
    @Override
    public Float findWeeklyConnectionsAverageDurationByProfiles(String establishmentUai, Date firstWeekDay,
            List<String> usersProfiles) {
	// Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findConnectionsAverageDurationByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfileList", usersProfiles);
	
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
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyNumConnectionsByProfiles(java.util.List, java.sql.Date, java.util.List)
     */
    @Override
    public Integer findWeeklyNumConnectionsByProfiles(List<String> establishmentsUai, Date firstWeekDay,
	    List<String> usersProfiles) {
	// Final result : number of connections
	Integer numConnections = 0;
	
	// Computation of the number of connections
	List<WeeklyPortalConnectionStatistic> statistics = findWeeklyStatisticsByProfiles(establishmentsUai, firstWeekDay, usersProfiles);
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
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyNumVisitorsAboveTresholdByProfiles(java.util.List, java.sql.Date, java.util.List, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTresholdByProfiles(List<String> establishmentsUai, Date firstWeekDay,
            List<String> usersProfiles, Integer treshold) {
        // Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findNumVisitorsAboveTresholdByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfileList", usersProfiles);
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
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findWeeklyNumVisitorsBelowTresholdByProfiles(java.util.List, java.sql.Date, java.util.List, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsBelowTresholdByProfiles(List<String> establishmentsUai, Date firstWeekDay,
            List<String> usersProfiles, Integer treshold) {
        // Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findNumVisitorsBelowTresholdByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfileList", usersProfiles);
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
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findMonthlyConnectionsAverageDurationByProfiles(java.lang.String, java.sql.Date, java.util.List)
     */
    @Override
    public Float findMonthlyConnectionsAverageDurationByProfiles(String establishmentUai,
            Date firstMonthDay, List<String> usersProfiles) {
	// Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findConnectionsAverageDurationByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfileList", usersProfiles);
	
	// Retrieval of the result
	Double result = (Double) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Float averageDurationTime = (result != null ? result.floatValue() : null);
	
	return averageDurationTime;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findMonthlyNumConnectionsByProfiles(java.util.List, java.sql.Date, java.util.List)
     */
    @Override
    public Integer findMonthlyNumConnectionsByProfiles(List<String> establishmentsUai, Date firstMonthDay,
	    List<String> usersProfiles) {
	// Final result : number of connections
	Integer numConnections = 0;
	
	// Computation of the number of connections
	List<MonthlyPortalConnectionStatistic> statistics = findMonthlyStatisticsByProfiles(establishmentsUai, firstMonthDay, usersProfiles);
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
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findMonthlyNumVisitorsAboveTresholdByProfiles(java.util.List, java.sql.Date, java.util.List, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsAboveTresholdByProfiles(List<String> establishmentsUai, Date firstMonthDay,
            List<String> usersProfiles, Integer treshold) {
        // Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findNumVisitorsAboveTresholdByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfileList", usersProfiles);
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
     * @see org.esco.indicators.dao.statistic.PortalConnectionStatisticDao#findMonthlyNumVisitorsBelowTresholdByProfiles(java.util.List, java.sql.Date, java.util.List, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsBelowTresholdByProfiles(List<String> establishmentUai, Date firstMonthDay,
            List<String> usersProfiles, Integer treshold) {
        // Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findNumVisitorsBelowTresholdByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfileList", usersProfiles);
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
     * @param usersProfiles
     *            The users profiles concerned by the statistics.
     * @return 
     * 	the list of statistics on the connections made on the portal.<br/>
     *         an empty list if no statistic has been retrieved.
     */
    private List<WeeklyPortalConnectionStatistic> findWeeklyStatisticsByProfiles(List<String> establishmentsUai, Date firstWeekDay,
	    List<String> usersProfiles) {
	// Name of the query to execute
	String namedQuery = "WeeklyPortalConnectionStatistic.findStatisticsByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfileList", usersProfiles);
	
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
     * Retrieves the statistics on connections made on the portal for the specified users profiles
     * <code>usersProfiles</code> and the specified establishments UAI <code>establishmentsUai</code>.<br/>
     * This statistics only concerns the month beggining with the day <code>firstMonthDay</code>.<br/>
     * 
     * @param establishmentsUai
     *            The UAI of the establishments concerned by the statistics.
     * @param firstMonthDay
     *            The first day of the week associated to the statistics.
     * @param usersProfiles
     *            The users profiles concerned by the statistics.
     * @return 
     * 	the list of statistics on the connections made on the portal.<br/>
     *         an empty list if no statistic has been retrieved.
     */
    private List<MonthlyPortalConnectionStatistic> findMonthlyStatisticsByProfiles(List<String> establishmentsUai, Date firstMonthDay,
	    List<String> usersProfiles) {
	// Name of the query to execute
	String namedQuery = "MonthlyPortalConnectionStatistic.findStatisticsByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfileList", usersProfiles);
	
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
