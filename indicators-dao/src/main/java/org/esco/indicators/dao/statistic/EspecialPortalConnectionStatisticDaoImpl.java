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
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findWeeklyConnectionsAverageDurationByProfiles(java.lang.String, java.sql.Date, java.util.List)
     */
    @Override
    public Float findWeeklyConnectionsAverageDurationByProfiles(String establishmentUai, Date firstWeekDay,
            List<String> usersProfiles) {
	// Name of the query to execute
	String namedQuery = "EspecialWeeklyPortalConnectionStatistic.findConnectionsAverageDurationByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfileList", usersProfiles);
	
	// Retrieval of the result
	Double result = (Double) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
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
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findWeeklyNumConnectionsByProfiles(java.util.List, java.sql.Date, java.util.List)
     */
    @Override
    public Integer findWeeklyNumConnectionsByProfiles(List<String> establishmentsUai, Date firstWeekDay,
	    List<String> usersProfiles) {
	// Name of the query to execute
	String namedQuery = "EspecialWeeklyPortalConnectionStatistic.findNumConnectionsByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfileList", usersProfiles);
	
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
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findWeeklyNumVisitorsAboveTresholdByProfiles(java.util.List, java.sql.Date, java.util.List, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTresholdByProfiles(List<String> establishmentsUai, Date firstWeekDay,
            List<String> usersProfiles, Integer treshold) {
	// Name of the query to execute
	String namedQuery = "EspecialWeeklyPortalConnectionStatistic.findNumVisitorsAboveTresholdByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfileList", usersProfiles);
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

    @Override
    public Integer findWeeklyNumVisitorsBelowTresholdByProfiles(List<String> establishmentsUai, Date firstWeekDay,
	    List<String> usersProfiles, Integer treshold) {
	// Name of the query to execute
	String namedQuery = "EspecialWeeklyPortalConnectionStatistic.findNumVisitorsBelowTresholdByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstWeekDay", firstWeekDay);
	parameters.put("userProfileList", usersProfiles);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : 0);
	
	return numVisitors;
    }
    

    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    @Override
    public Float findMonthlyConnectionsAverageDurationByProfiles(String establishmentUai, Date firstMonthDay,
            List<String> usersProfiles) {
	// Name of the query to execute
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findConnectionsAverageDurationByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfileList", usersProfiles);
	
	// Retrieval of the result
	Double result = (Double) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Float averageDurationTime = (result != null ? result.floatValue() : 0);
	
	return averageDurationTime;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findMonthlyNumConnections(java.lang.String, java.sql.Date)
     */
    @Override
    public Integer findMonthlyNumConnections(String establishmentUai, Date firstMonthDay) {
	// Name of the query to execute
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findNumConnectionsByProfiles";
	
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
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findMonthlyNumConnectionsByProfiles(java.util.List, java.sql.Date, java.util.List)
     */
    @Override
    public Integer findMonthlyNumConnectionsByProfiles(List<String> establishmentsUai, Date firstMonthDay,
	    List<String> usersProfiles) {
	// Name of the query to execute
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findNumConnectionsByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfileList", usersProfiles);
	
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
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findMonthlyNumVisitorsAboveTresholdByProfiles(java.util.List, java.sql.Date, java.util.List, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsAboveTresholdByProfiles(List<String> establishmentsUai, Date firstMonthDay,
	    List<String> usersProfiles, Integer treshold) {
	// Name of the query to execute
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findNumVisitorsAboveTresholdByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfileList", usersProfiles);
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
     * @see org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao#findMonthlyNumVisitorsBelowTresholdByProfiles(java.util.List, java.sql.Date, java.util.List, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsBelowTresholdByProfiles(List<String> establishmentsUai, Date firstMonthDay,
	    List<String> usersProfiles, Integer treshold) {
	// Name of the query to execute
	String namedQuery = "EspecialMonthlyPortalConnectionStatistic.findNumVisitorsBelowTresholdByProfiles";
	
	// Setting of the parameters
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("firstMonthDay", firstMonthDay);
	parameters.put("userProfileList", usersProfiles);
	parameters.put("treshold", treshold);
	
	// Retrieval of the result
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numVisitors = (result != null ? result.intValue() : 0);
	
	return numVisitors;
    }


    // ----------------------------------------------------------------------------- PRIVATE METHODS

    // ------------------------------------------------------------------------------ STATIC METHODS
}
