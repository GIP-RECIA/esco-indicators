/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.statistic.PortalConnectionStatisticDao;

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
     * Gets the DAO providing access to statistical data on the portal connections.
     * 
     * @return 
     * 	the DAO providing access to statistical data on the portal connections.
     */
    public PortalConnectionStatisticDao getPortalConnectionStatisticDao() {
        return portalConnectionStatisticDao;
    }

    /**
     * Sets the DAO providing access to statistical data on the portal connections.
     * 
     * @param portalConnectionStatisticDao 
     * 			The DAO to set.
     */
    public void setPortalConnectionStatisticDao(PortalConnectionStatisticDao portalConnectionStatisticDao) {
        this.portalConnectionStatisticDao = portalConnectionStatisticDao;
    }


    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.PortalConnectionStatisticService#findWeeklyNumConnectionsByProfile(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Integer findWeeklyNumConnectionsByProfile(String establishmentUai, Date firstWeekDay,
	    String userProfile) {
	return portalConnectionStatisticDao.findWeeklyNumConnectionsByProfile(establishmentUai, firstWeekDay, userProfile);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
