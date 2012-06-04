/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.util.Date;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.statistic.EstablishmentVisitStatisticDao;
import org.esco.indicators.domain.beans.statistic.EstablishmentVisitStatistic;
import org.esco.indicators.utils.constants.StatisticConstants;

/**
 * Implementation of the {@link EstablishmentVisitStatisticService} interface.
 * 
 * @since 2012/06/01
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentVisitStatisticServiceImpl implements EstablishmentVisitStatisticService {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentVisitStatisticServiceImpl.class);

    /** {@link EstablishmentVisitStatisticDao} providing access to statistical data. */
    private EstablishmentVisitStatisticDao establishmentVisitStatisticDao;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the EstablishmentVisitStatisticServiceImpl class.
     */
    public EstablishmentVisitStatisticServiceImpl() {
	super();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the DAO providing access to the statistical data on the visits / visitors of an establishment.
     * 
     * @return the DAO providing access to statisical data.
     */
    public EstablishmentVisitStatisticDao getEstablishmentVisitStatisticDao() {
	return establishmentVisitStatisticDao;
    }

    /**
     * Sets the DAO providing access to the statistical data on the visits / visitors of an establishment.
     * 
     * @param establishmentVisitStatisticDao
     *            the DAO, providing access to statisical data, to set.
     */
    public void setEstablishmentVisitStatisticDao(
	    EstablishmentVisitStatisticDao establishmentVisitStatisticDao) {
	this.establishmentVisitStatisticDao = establishmentVisitStatisticDao;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.EstablishmentVisitStatisticService#findDailyStatistic(java.lang.String, java.util.Date)
     */
    @Override
    public EstablishmentVisitStatistic findEstablishmentDailyStatistic(Date day, String establishmentType, String establishmentUai) {
	// The type of statistic required : Statistic concerning only one establishment 
	String typeStat = StatisticConstants.TYPE_STAT_ESTABLISHMENT;
	return establishmentVisitStatisticDao.findDailyStatistic(day, establishmentType, establishmentUai, typeStat);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
