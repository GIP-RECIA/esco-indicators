/**
 * 
 */
package org.esco.indicators.dao.statistic;


import java.sql.Date;
import java.util.List;

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
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findWeeklyConnectionsAverageDurationByProfile(java.lang.String, java.sql.Date, java.lang.String)
     */
    @Override
    public Float findWeeklyConnectionsAverageDurationByProfile(String establishmentUai, Date firstWeekDay,
            String userProfile) {
	// Name of the query to execute
	String namedQuery = "EspecialWeeklyPortalConnectionStatistic.findConnectionsAverageDurationByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfile", userProfile);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Float averageDurationTime = (result != null ? result.floatValue() : 0);
	
	return averageDurationTime;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findWeeklyNumConnections(java.util.List, java.sql.Date)
     */
    @Override
    public Integer findWeeklyNumConnections(List<String> establishmentsUai, Date firstWeekDay) {
	// Name of the query to execute
	String namedQuery = "EspecialWeeklyPortalConnectionStatistic.findNumConnections";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numConnections = (result != null ? result.intValue() : 0);
	
	return numConnections;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findWeeklyNumConnectionsByProfile(java.util.List, java.sql.Date, java.lang.String)
     */
    @Override
    public Integer findWeeklyNumConnectionsByProfile(List<String> establishmentsUai, Date firstWeekDay,
	    String userProfile) {
	// Name of the query to execute
	String namedQuery = "EspecialWeeklyPortalConnectionStatistic.findNumConnectionsByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfile", userProfile);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numConnections = (result != null ? result.intValue() : 0);
	
	return numConnections;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findWeeklyNumVisitorsAboveTreshold(java.lang.String, java.sql.Date, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTreshold(List<String> establishmentsUai, Date firstWeekDay,
            Integer treshold) {
	// Name of the query to execute
	String namedQuery = "EspecialWeeklyPortalConnectionStatistic.findNumVisitorsAboveTreshold";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : 0);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findWeeklyNumVisitorsAboveTresholdByProfile(java.util.List, java.sql.Date, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTresholdByProfile(List<String> establishmentsUai, Date firstWeekDay,
            String userProfile, Integer treshold) {
	// Name of the query to execute
	String namedQuery = "EspecialWeeklyPortalConnectionStatistic.findNumVisitorsAboveTresholdByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfile", userProfile);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : 0);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findWeeklyNumVisitorsBelowTreshold(java.lang.String, java.sql.Date, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsBelowTreshold(List<String> establishmentsUai, Date firstWeekDay,
            Integer treshold) {
	// Name of the query to execute
	String namedQuery = "EspecialWeeklyPortalConnectionStatistic.findNumVisitorsBelowTreshold";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : 0);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findWeeklyNumVisitorsBelowTreshold(java.lang.String, java.util.Date, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsBelowTresholdByProfile(List<String> establishmentsUai, Date firstWeekDay,
	    String userProfile, Integer treshold) {
	// Name of the query to execute
	String namedQuery = "EspecialWeeklyPortalConnectionStatistic.findNumVisitorsBelowTresholdByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfile", userProfile);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : 0);
	
	return numVisitors;
    }
    

    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findMonthlyConnectionsAverageDurationByProfile(java.lang.String, java.sql.Date, java.lang.String)
     */
    @Override
    public Float findMonthlyConnectionsAverageDurationByProfile(String establishmentUai, Date firstMonthDay,
            String userProfile) {
	// Name of the query to execute
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findConnectionsAverageDurationByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfile", userProfile);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Float averageDurationTime = (result != null ? result.floatValue() : 0);
	
	return averageDurationTime;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findMonthlyNumConnections(java.lang.String, java.sql.Date)
     */
    @Override
    public Integer findMonthlyNumConnections(String establishmentUai, Date firstMonthDay) {
	// Name of the query to execute
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findNumConnectionsByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstMonthDay", firstMonthDay);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numConnections = (result != null ? result.intValue() : 0);
	
	return numConnections;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findMonthlyNumConnectionsByProfile(java.util.List, java.sql.Date, java.lang.String)
     */
    @Override
    public Integer findMonthlyNumConnectionsByProfile(List<String> establishmentsUai, Date firstMonthDay,
	    String userProfile) {
	// Name of the query to execute
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findNumConnectionsByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfile", userProfile);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numConnections = (result != null ? result.intValue() : 0);
	
	return numConnections;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findMonthlyNumVisitorsAboveTreshold(java.lang.String, java.sql.Date, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsAboveTreshold(List<String> establishmentsUai, Date firstMonthDay,
            Integer treshold) {
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findNumVisitorsAboveTreshold";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : 0);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findMonthlyNumVisitorsAboveTreshold(java.lang.String, java.util.Date, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsAboveTresholdByProfile(List<String> establishmentsUai, Date firstMonthDay,
	    String userProfile, Integer treshold) {
	// Name of the query to execute
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findNumVisitorsAboveTresholdByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfile", userProfile);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : 0);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findMonthlyNumVisitorsBelowTreshold(java.lang.String, java.sql.Date, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsBelowTreshold(List<String> establishmentsUai, Date firstMonthDay,
            Integer treshold) {
	// Name of the query to execute
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findNumVisitorsBelowTreshold";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : 0);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findMonthlyNumVisitorsBelowTreshold(java.lang.String, java.util.Date, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsBelowTresholdByProfile(List<String> establishmentsUai, Date firstMonthDay,
	    String userProfile, Integer treshold) {
	// Name of the query to execute
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findNumVisitorsBelowTresholdByProfile";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfile", userProfile);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : 0);
	
	return numVisitors;
    }


    // ----------------------------------------------------------------------------- PRIVATE METHODS

    // ------------------------------------------------------------------------------ STATIC METHODS
}
