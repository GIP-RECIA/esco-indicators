/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao;
import org.esco.indicators.dao.statistic.PortalConnectionStatisticDao;
import org.esco.indicators.domain.beans.statistic.WeeklyPortalConnectionStatistic;
import org.esco.indicators.utils.date.DateUtils;
import org.esco.indicators.utils.number.FloatUtils;

/**
 * Implementation of the {@link PortalConnectionStatisticService} interface.
 * 
 * @since  2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class PortalConnectionStatisticServiceImpl implements PortalConnectionStatisticService {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(PortalConnectionStatisticServiceImpl.class);

    /** 
     * DAO providing access to statistical data on the portal connections for especial users.<br/> 
     * This DAO also provides an access to the statistical data on the portal connections 
     * for the current week / month.
     */
    private EspecialPortalConnectionStatisticDao especialPortalConnectionStatisticDao;
    
    /** 
     * DAO providing access to statistical data on the portal connections for non-especial users.<br/>
     * This DAO only provides access to the statistical data on the portal connections 
     * for the past weeks / months.
     */
    private PortalConnectionStatisticDao portalConnectionStatisticDao;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link PortalConnectionStatisticServiceImpl} class.
     */
    public PortalConnectionStatisticServiceImpl() {
	super();
    }

    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the DAO providing access to statistical data on the portal connections.<br/>
     * DAO used for especial past data and current data (current week / month).
     * 
     * @return 
     * 	the DAO providing access to statistical data on the portal connections.
     */
    public EspecialPortalConnectionStatisticDao getEspecialPortalConnectionStatisticDao() {
        return especialPortalConnectionStatisticDao;
    }

    /**
     * Sets the DAO providing access to statistical data on the portal connections.<br/>
     * DAO used for especial past data and current data (current week / month).
     * 
     * @param especialPortalConnectionStatisticDao 
     * 			The DAO to set.
     */
    public void setEspecialPortalConnectionStatisticDao(EspecialPortalConnectionStatisticDao especialPortalConnectionStatisticDao) {
        this.especialPortalConnectionStatisticDao = especialPortalConnectionStatisticDao;
    }
    
    
    /**
     * Gets the DAO providing access to statistical data on the portal connections.<br/>
     * DAO used for non-especial past data.
     * 
     * @return
     * 	the DAO providing access to statistical data on the portal connections.
     */
    public PortalConnectionStatisticDao getPortalConnectionStatisticDao() {
        return portalConnectionStatisticDao;
    }


    /**
     * Sets the DAO providing access to statistical data on the portal connections.<br/>
     * DAO used for non-especial past data.
     * 
     * @param portalConnectionStatisticDao 
     * 			The DAO to set.
     */
    public void setPortalConnectionStatisticDao(PortalConnectionStatisticDao portalConnectionStatisticDao) {
        this.portalConnectionStatisticDao = portalConnectionStatisticDao;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyConnectionsAverageDurationByProfiles(java.lang.String, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Float findWeeklyConnectionsAverageDurationByProfiles(String establishmentUai, List<String> usersProfiles,
            Integer week, Integer year) {
	// Get the SQL date corresponding to the first day of the week for the year
	Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
	
	// Retrieval of the average duration time of connections for the especial users
	Float especialAverageDurationTime = especialPortalConnectionStatisticDao
		.findWeeklyConnectionsAverageDurationByProfiles(establishmentUai, firstWeekDay, usersProfiles);
	especialAverageDurationTime = (especialAverageDurationTime != null ? especialAverageDurationTime : 0 );
	
	// Average duration time of connections for the normal users
	Float normalAverageDurationTime = portalConnectionStatisticDao
		.findWeeklyConnectionsAverageDurationByProfiles(establishmentUai, firstWeekDay, usersProfiles);
	normalAverageDurationTime = (normalAverageDurationTime != null ? normalAverageDurationTime : 0 );
	
	// Pus the establishment Uai into a list for the methods calls
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	
	// Retrieval of the number of connections for the especials users
	Integer especialNumConnections = especialPortalConnectionStatisticDao.findWeeklyNumConnectionsByProfiles(establishmentsUai, firstWeekDay, usersProfiles);
	especialNumConnections = (especialNumConnections != null ? especialNumConnections : 0 );
	
	// Retrieval of the number of connections for the normal users
	Integer normalNumConnections = portalConnectionStatisticDao.findWeeklyNumConnectionsByProfiles(establishmentsUai, firstWeekDay, usersProfiles);
	normalNumConnections = (normalNumConnections != null ? normalNumConnections : 0 );
	
	// Final average time
	Float averageDuration = FloatUtils.calculateAverage(especialAverageDurationTime, especialNumConnections, normalAverageDurationTime, normalNumConnections);
	return averageDuration;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumConnectionsByProfiles(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumConnectionsByProfiles(List<String> establishmentsUai, List<String> usersProfiles,
	    Integer week, Integer year) {
	// Get the SQL date corresponding to the first day of the week for the year
	Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
	
	// Number of connections for the especial users
	Integer especialNumConnections = especialPortalConnectionStatisticDao
		.findWeeklyNumConnectionsByProfiles(establishmentsUai, firstWeekDay, usersProfiles);
	
	// Number of connections for the normal users
	Integer normalNumConnections = portalConnectionStatisticDao
		.findWeeklyNumConnectionsByProfiles(establishmentsUai, firstWeekDay, usersProfiles);
	
	// Final number of connections
	Integer numConnections = especialNumConnections + normalNumConnections;
	
	return numConnections;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumVisitorsAboveTreshold(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTreshold(List<String> establishmentsUai, Integer week, Integer year,
            Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findWeeklyNumVisitorsAboveTreshold(establishmentsUai, firstWeekDay, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findWeeklyNumVisitorsAboveTreshold(establishmentsUai, firstWeekDay, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumVisitorsAboveTresholdByProfiles(java.lang.String, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTresholdByProfiles(String establishmentUai, List<String> usersProfiles,
            Integer week, Integer year, Integer treshold) {
        // Put the establishment UAI into a list
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
        
        return findWeeklyNumVisitorsAboveTresholdByProfiles(establishmentsUai, usersProfiles, week, year, treshold);
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumVisitorsAboveTresholdByProfiles(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTresholdByProfiles(List<String> establishmentsUai,
            List<String> usersProfiles, Integer week, Integer year, Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findWeeklyNumVisitorsAboveTresholdByProfiles(establishmentsUai, firstWeekDay, usersProfiles, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findWeeklyNumVisitorsAboveTresholdByProfiles(establishmentsUai, firstWeekDay, usersProfiles, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumVisitorsBelowTreshold(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsBelowTreshold(List<String> establishmentsUai, Integer week, Integer year,
            Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findWeeklyNumVisitorsBelowTreshold(establishmentsUai, firstWeekDay, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findWeeklyNumVisitorsBelowTreshold(establishmentsUai, firstWeekDay, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumVisitorsBelowTresholdByProfiles(java.lang.String, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsBelowTresholdByProfiles(String establishmentUai, List<String> usersProfiles,
            Integer week, Integer year, Integer treshold) {
        // Put the establishment UAI into a list
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	
        return findWeeklyNumVisitorsBelowTresholdByProfiles(establishmentsUai, usersProfiles, week, year, treshold);
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumVisitorsBelowTresholdByProfiles(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsBelowTresholdByProfiles(List<String> establishmentsUai,
            List<String> usersProfiles, Integer week, Integer year, Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findWeeklyNumVisitorsBelowTresholdByProfiles(establishmentsUai, firstWeekDay, usersProfiles, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findWeeklyNumVisitorsBelowTresholdByProfiles(establishmentsUai, firstWeekDay, usersProfiles, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }

    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyConnectionsAverageDurationByProfiles(java.lang.String, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Float findMonthlyConnectionsAverageDurationByProfiles(String establishmentUai, List<String> usersProfiles,
            Integer month, Integer year) {
	// Get the SQL date corresponding to the first day of the month for the year
	Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
	
	// Retrieval of the average duration time of connections for the especial users
	Float especialAverageDurationTime = especialPortalConnectionStatisticDao
		.findMonthlyConnectionsAverageDurationByProfiles(establishmentUai, firstMonthDay, usersProfiles);
	especialAverageDurationTime = (especialAverageDurationTime != null ? especialAverageDurationTime : 0 );
	
	// Average duration time of connections for the normal users
	Float normalAverageDurationTime = portalConnectionStatisticDao
		.findMonthlyConnectionsAverageDurationByProfiles(establishmentUai, firstMonthDay, usersProfiles);
	normalAverageDurationTime = (normalAverageDurationTime != null ? normalAverageDurationTime : 0 );
	
	// Pus the establishment Uai into a list for the methods calls
	List<String> establishmentsUai = new ArrayList<String>();
	establishmentsUai.add(establishmentUai);
	
	// Retrieval of the number of connections for the especials users
	Integer especialNumConnections = especialPortalConnectionStatisticDao.findMonthlyNumConnectionsByProfiles(establishmentsUai, firstMonthDay, usersProfiles);
	especialNumConnections = (especialNumConnections != null ? especialNumConnections : 0 );
	
	// Retrieval of the number of connections for the normal users
	Integer normalNumConnections = portalConnectionStatisticDao.findMonthlyNumConnectionsByProfiles(establishmentsUai, firstMonthDay, usersProfiles);
	normalNumConnections = (normalNumConnections != null ? normalNumConnections : 0 );
	
	// Final average time
	Float averageDuration = FloatUtils.calculateAverage(especialAverageDurationTime, especialNumConnections, normalAverageDurationTime, normalNumConnections);
	return averageDuration;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyNumConnectionsByProfiles(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumConnectionsByProfiles(List<String> establishmentsUai, List<String> usersProfiles,
	    Integer month, Integer year) {
	// Get the SQL date corresponding to the first day of the month for the year
	Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
	
	// Number of connections for the especial users
	Integer especialNumConnections = especialPortalConnectionStatisticDao
		.findMonthlyNumConnectionsByProfiles(establishmentsUai, firstMonthDay, usersProfiles);
	
	// Number of connections for the normal users
	Integer normalNumConnections = portalConnectionStatisticDao
		.findMonthlyNumConnectionsByProfiles(establishmentsUai, firstMonthDay, usersProfiles);
	
	// Final number of connections
	Integer numConnections = especialNumConnections + normalNumConnections;
	
	return numConnections;
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyNumVisitorsAboveTreshold(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsAboveTreshold(List<String> establishmentsUai, Integer month, Integer year,
            Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findMonthlyNumVisitorsAboveTreshold(establishmentsUai, firstMonthDay, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findMonthlyNumVisitorsAboveTreshold(establishmentsUai, firstMonthDay, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyNumVisitorsAboveTresholdByProfiles(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsAboveTresholdByProfiles(List<String> establishmentsUai, List<String> usersProfiles,
	    Integer month, Integer year, Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findMonthlyNumVisitorsAboveTresholdByProfiles(establishmentsUai, firstMonthDay, usersProfiles, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findMonthlyNumVisitorsAboveTresholdByProfiles(establishmentsUai, firstMonthDay, usersProfiles, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyNumVisitorsBelowTreshold(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsBelowTreshold(List<String> establishmentsUai, Integer month, Integer year,
            Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findMonthlyNumVisitorsBelowTreshold(establishmentsUai, firstMonthDay, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findMonthlyNumVisitorsBelowTreshold(establishmentsUai, firstMonthDay, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyNumVisitorsBelowTresholdByProfiles(java.util.List, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsBelowTresholdByProfiles(List<String> establishmentsUai, List<String> usersProfiles,
	    Integer month, Integer year, Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findMonthlyNumVisitorsBelowTresholdByProfiles(establishmentsUai, firstMonthDay, usersProfiles, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findMonthlyNumVisitorsBelowTresholdByProfiles(establishmentsUai, firstMonthDay, usersProfiles, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of connections
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
    
}
