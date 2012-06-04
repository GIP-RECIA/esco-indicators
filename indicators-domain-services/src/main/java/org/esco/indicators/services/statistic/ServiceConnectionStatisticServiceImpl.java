/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.util.Date;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.statistic.ServiceConnectionStatisticDao;
import org.esco.indicators.domain.beans.statistic.ServiceConnectionStatistic;

/**
 * Implementation of the {@link ServiceConnectionStatisticService} interface.
 * 
 * @since : 2012/06/04
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
     * Gets the DAO providing access to statistical data on the services connections.
     * 
     * @return 
     * 	the DAO providing access to statistical data on the services connections.
     */
    public ServiceConnectionStatisticDao getServiceConnectionStatisticDao() {
        return serviceConnectionStatisticDao;
    }

    /**
     * Sets the DAO providing access to statistical data on the services connections.
     * 
     * @param serviceConnectionStatisticDao 
     * 			The DAO to set.
     */
    public void setServiceConnectionStatisticDao(ServiceConnectionStatisticDao serviceConnectionStatisticDao) {
        this.serviceConnectionStatisticDao = serviceConnectionStatisticDao;
    }


    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.ServiceConnectionStatisticService#findDailyNumConnectionsByProfile(java.util.Date, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Integer findDailyNumConnectionsByProfile(Date day, String establishmentUai,
	    String serviceName, String userProfile) {
	return serviceConnectionStatisticDao.findDailyNumConnectionsByProfile(day, establishmentUai, serviceName, userProfile);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
