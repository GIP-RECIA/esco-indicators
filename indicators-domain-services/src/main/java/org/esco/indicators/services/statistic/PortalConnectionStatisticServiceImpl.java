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
    public Integer findWeeklyNumConnectionsByProfile(String establishmentUai, Date firstWeekDay,
	    String userProfile) {
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

    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findMonthlyNumConnectionsByProfile(java.lang.String, java.sql.Date, java.lang.String)
     */
    @Override
    public Integer findMonthlyNumConnectionsByProfile(String establishmentUai, Date firstMonthDay,
	    String userProfile) {
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

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
    
}
