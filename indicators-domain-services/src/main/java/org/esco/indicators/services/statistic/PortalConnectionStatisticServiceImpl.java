/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.statistic.EspecialPortalConnectionStatisticDao;

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

    /** DAO providing access to statistical data on the portal connections */
    private EspecialPortalConnectionStatisticDao especialPortalConnectionStatisticDao;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link PortalConnectionStatisticServiceImpl} class.
     */
    public PortalConnectionStatisticServiceImpl() {
	super();
    }

    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the DAO providing access to statistical data on the portal connections.
     * 
     * @return 
     * 	the DAO providing access to statistical data on the portal connections.
     */
    public EspecialPortalConnectionStatisticDao getEspecialPortalConnectionStatisticDao() {
        return especialPortalConnectionStatisticDao;
    }

    /**
     * Sets the DAO providing access to statistical data on the portal connections.
     * 
     * @param especialPortalConnectionStatisticDao 
     * 			The DAO to set.
     */
    public void setEspecialPortalConnectionStatisticDao(EspecialPortalConnectionStatisticDao especialPortalConnectionStatisticDao) {
        this.especialPortalConnectionStatisticDao = especialPortalConnectionStatisticDao;
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
	return especialPortalConnectionStatisticDao.findWeeklyNumConnectionsByProfile(establishmentUai, firstWeekDay, userProfile);
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
	return especialPortalConnectionStatisticDao.findMonthlyNumConnectionsByProfile(establishmentUai, firstMonthDay, userProfile);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
