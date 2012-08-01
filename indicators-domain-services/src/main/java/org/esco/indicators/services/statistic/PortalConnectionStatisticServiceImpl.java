/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao;
import org.esco.indicators.dao.statistic.PortalConnectionStatisticDao;
import org.esco.indicators.domain.beans.statistic.WeeklyPortalConnectionStatistic;
import org.esco.indicators.utils.date.DateUtils;

/**
 * Implementation of the {@link PortalConnectionStatisticService} interface.
 * 
 * @since  2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
/**
 * @since  
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
    // DAILY STATISTICS
    ///////////////////////////////////////////////////////
    
    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumConnectionsByProfile(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Integer findWeeklyNumConnectionsByProfile(String establishmentUai, String userProfile,
	    Integer week, Integer year) {
	// Get the SQL date corresponding to the first day of the week for the year
	Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
	
	// Number of connections for the especial users
	Integer especialNumConnections = especialPortalConnectionStatisticDao
		.findWeeklyNumConnectionsByProfile(establishmentUai, firstWeekDay, userProfile);
	
	// Number of connections for the normal users
	Integer normalNumConnections = portalConnectionStatisticDao
		.findWeeklyNumConnectionsByProfile(establishmentUai, firstWeekDay, userProfile);
	
	// Final number of connections
	Integer numConnections = especialNumConnections + normalNumConnections;
	
	return numConnections;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumVisitorsAboveTreshold(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTreshold(String establishmentUai, Integer week, Integer year,
            Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findWeeklyNumVisitorsAboveTreshold(establishmentUai, firstWeekDay, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findWeeklyNumVisitorsAboveTreshold(establishmentUai, firstWeekDay, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumVisitorsAboveTreshold(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTresholdByProfile(String establishmentUai, String userProfile,
            Integer week, Integer year, Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findWeeklyNumVisitorsAboveTresholdByProfile(establishmentUai, firstWeekDay, userProfile, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findWeeklyNumVisitorsAboveTresholdByProfile(establishmentUai, firstWeekDay, userProfile, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumVisitorsBelowTreshold(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsBelowTreshold(String establishmentUai, Integer week, Integer year,
            Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findWeeklyNumVisitorsBelowTreshold(establishmentUai, firstWeekDay, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findWeeklyNumVisitorsBelowTreshold(establishmentUai, firstWeekDay, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumConnectionsBelowTreshold(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsBelowTresholdByProfile(String establishmentUai, String userProfile,
            Integer week, Integer year, Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findWeeklyNumVisitorsBelowTresholdByProfile(establishmentUai, firstWeekDay, userProfile, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findWeeklyNumVisitorsBelowTresholdByProfile(establishmentUai, firstWeekDay, userProfile, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }


    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyNumConnectionsByProfile(java.lang.String, java.sql.Date, java.lang.String)
     */
    @Override
    public Integer findMonthlyNumConnectionsByProfile(String establishmentUai, String userProfile,
	    Integer month, Integer year) {
	// Get the SQL date corresponding to the first day of the month for the year
	Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
	
	// Number of connections for the especial users
	Integer especialNumConnections = especialPortalConnectionStatisticDao
		.findMonthlyNumConnectionsByProfile(establishmentUai, firstMonthDay, userProfile);
	
	// Number of connections for the normal users
	Integer normalNumConnections = portalConnectionStatisticDao
		.findMonthlyNumConnectionsByProfile(establishmentUai, firstMonthDay, userProfile);
	
	// Final number of connections
	Integer numConnections = especialNumConnections + normalNumConnections;
	
	return numConnections;
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyNumVisitorsAboveTreshold(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsAboveTreshold(String establishmentUai, Integer month, Integer year,
            Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findMonthlyNumVisitorsAboveTreshold(establishmentUai, firstMonthDay, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findMonthlyNumVisitorsAboveTreshold(establishmentUai, firstMonthDay, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }


    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyNumVisitorsAboveTreshold(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsAboveTresholdByProfile(String establishmentUai, String userProfile,
	    Integer month, Integer year, Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findMonthlyNumVisitorsAboveTresholdByProfile(establishmentUai, firstMonthDay, userProfile, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findMonthlyNumVisitorsAboveTresholdByProfile(establishmentUai, firstMonthDay, userProfile, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyNumVisitorsBelowTreshold(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsBelowTreshold(String establishmentUai, Integer month, Integer year,
            Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findMonthlyNumVisitorsBelowTreshold(establishmentUai, firstMonthDay, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findMonthlyNumVisitorsBelowTreshold(establishmentUai, firstMonthDay, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of visitors
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyNumVisitorsBelowTreshold(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsBelowTresholdByProfile(String establishmentUai, String userProfile,
	    Integer month, Integer year, Integer treshold) {
        // Get the SQL date corresponding to the first day of the week for the year
        Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
        
        // Number of visitors for the especial users
        Integer especialNumVisitors = especialPortalConnectionStatisticDao
        	.findMonthlyNumVisitorsBelowTresholdByProfile(establishmentUai, firstMonthDay, userProfile, treshold);
        especialNumVisitors = (especialNumVisitors == null ? 0 : especialNumVisitors);
        
        // Number of visitors for the normal users
        Integer normalNumVisitors = portalConnectionStatisticDao
        	.findMonthlyNumVisitorsBelowTresholdByProfile(establishmentUai, firstMonthDay, userProfile, treshold);
        normalNumVisitors = (normalNumVisitors == null ? 0 : normalNumVisitors);
        
        // Final number of connections
        Integer numVisitors = especialNumVisitors + normalNumVisitors;
        
        return numVisitors;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
    
}
