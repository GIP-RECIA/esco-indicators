/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.statistic.ServiceConnectionStatisticDao;
import org.esco.indicators.utils.date.DateUtils;

/**
 * Implementation of the {@link ServiceConnectionStatisticService} interface.
 * 
 * @since 2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ServiceConnectionStatisticServiceImpl implements ServiceConnectionStatisticService {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ServiceConnectionStatisticServiceImpl.class);

    /** DAO providing access to statistical data */
    private ServiceConnectionStatisticDao serviceConnectionStatisticDao;

    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link ServiceConnectionStatisticServiceImpl} class.
     */
    public ServiceConnectionStatisticServiceImpl() {
	super();
    }
    
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the DAO providing access to statistical data on the wantedServices connections.
     * 
     * @return 
     * 	the DAO providing access to statistical data on the wantedServices connections.
     */
    public ServiceConnectionStatisticDao getServiceConnectionStatisticDao() {
        return serviceConnectionStatisticDao;
    }

    /**
     * Sets the DAO providing access to statistical data on the wantedServices connections.
     * 
     * @param serviceConnectionStatisticDao 
     * 			The DAO to set.
     */
    public void setServiceConnectionStatisticDao(ServiceConnectionStatisticDao serviceConnectionStatisticDao) {
        this.serviceConnectionStatisticDao = serviceConnectionStatisticDao;
    }


    //------------------------------------------------------------------------------ PUBLIC METHODS
    ///////////////////////////////////////////////////////
    // DAILY STATISTICS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.ServiceConnectionStatisticService#findDailyNumConnectionsByProfile(java.util.Date, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Integer findDailyNumConnectionsByProfile(java.util.Date day, String establishmentUai,
	    List<String> servicesNames, String userProfile) {
	return serviceConnectionStatisticDao.findDailyNumConnectionsByProfile(day, establishmentUai, servicesNames, userProfile);
    }

    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.ServiceConnectionStatisticService#findWeeklyNumVisitorsAboveTreshold(java.lang.String, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsAboveTreshold(List<String> establishmentsUai, List<String> services,
	    String userProfile, Integer treshold, Integer week, Integer year) {
	// Gets the start day and end day of the week
	Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
	Date lastWeekDay = DateUtils.addDays(firstWeekDay, 6);
	
	// Number of visitors
	Integer numVisitors = serviceConnectionStatisticDao.findNumVisitorsAboveTreshold(establishmentsUai, firstWeekDay, lastWeekDay, services, userProfile, treshold); 
	numVisitors = (numVisitors == null ? 0 : numVisitors);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.ServiceConnectionStatisticService#findWeeklyNumVisitorsBelowTreshold(java.lang.String, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisitorsBelowTreshold(List<String> establishmentsUai, List<String> services,
	    String userProfile, Integer treshold, Integer week, Integer year) {
	// Gets the start day and end day of the week
	Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
	Date lastWeekDay = DateUtils.addDays(firstWeekDay, 6);
	
	// Number of visitors
	Integer numVisitors = serviceConnectionStatisticDao.findNumVisitorsBelowTreshold(establishmentsUai, firstWeekDay, lastWeekDay, services, userProfile, treshold); 
	numVisitors = (numVisitors == null ? 0 : numVisitors);
	
	return numVisitors;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.ServiceConnectionStatisticService#findWeeklyNumVisits(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findWeeklyNumVisits(List<String> establishmentUai, List<String> services, String userProfile,
	    Integer week, Integer year) {
	// Gets the start day and end day of the week
	Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
	Date lastWeekDay = DateUtils.addDays(firstWeekDay, 6);
	
	// Number of visits
	Integer numVisits = serviceConnectionStatisticDao.findNumVisits(establishmentUai, firstWeekDay, lastWeekDay, services, userProfile);
	numVisits = (numVisits == null ? 0 : numVisits);
	
	return numVisits;
    }

    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.ServiceConnectionStatisticService#findMonthlyNumVisitorsAboveTreshold(java.util.List, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsAboveTreshold(List<String> establishmentsUai, List<String> services, String userProfile, Integer treshold, Integer month, Integer year) {
	// Gets the start day and end day of the month
	Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
	Date lastMonthDay = DateUtils.getLastMonthDay(month, year);
	
	// Number of visitors
	Integer numVisitors = serviceConnectionStatisticDao.findNumVisitorsAboveTreshold(establishmentsUai, firstMonthDay, lastMonthDay, services, userProfile, treshold); 
	numVisitors = (numVisitors == null ? 0 : numVisitors);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.ServiceConnectionStatisticService#findMonthlyNumVisitorsBelowTreshold(java.util.List, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisitorsBelowTreshold(List<String> establishmentsUai, List<String> services,
	    String userProfile, Integer treshold, Integer month, Integer year) {
	// Gets the start day and end day of the month
	Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
	Date lastMonthDay = DateUtils.getLastMonthDay(month, year);
	
	// Number of visitors
	Integer numVisitors = serviceConnectionStatisticDao.findNumVisitorsBelowTreshold(establishmentsUai, firstMonthDay, lastMonthDay, services, userProfile, treshold); 
	numVisitors = (numVisitors == null ? 0 : numVisitors);
	
	return numVisitors;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.ServiceConnectionStatisticService#findMonthlyNumVisits(java.util.List, java.util.List, java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findMonthlyNumVisits(List<String> establishmentsUai, List<String> services, String userProfile,
	    Integer month, Integer year) {
	// Gets the start day and end day of the month
	Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
	Date lastMonthDay = DateUtils.getLastMonthDay(month, year);
	
	// Number of visits
	Integer numVisits = serviceConnectionStatisticDao.findNumVisits(establishmentsUai, firstMonthDay, lastMonthDay, services, userProfile);
	numVisits = (numVisits == null ? 0 : numVisits);
	
	return numVisits;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
