/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.statistic.EstablishmentVisitStatisticDao;
import org.esco.indicators.dao.structure.EstablishmentDao;
import org.esco.indicators.domain.beans.statistic.EstablishmentVisitStatistic;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.utils.constants.db.StatisticConstants;
import org.esco.indicators.utils.date.DateUtils;

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

    /** {@link EstablishmentDao} providing access to establishment data. */
    private EstablishmentDao establishmentDao;
    
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
    

    /**
     * Sets the DAO providing access to establishment data.
     * 
     * @param establishmentDao 
     * 			the DAO, providing access to establishment data, to set.
     */
    public void setEstablishmentDao(EstablishmentDao establishmentDao) {
        this.establishmentDao = establishmentDao;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    ///////////////////////////////////////////////////////
    // DAILY STATISTICS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.EstablishmentVisitStatisticService#findDailyStatistic(java.lang.String, java.util.Date)
     */
    @Override
    public EstablishmentVisitStatistic findEstablishmentDailyStatistic(Date day, String establishmentType, String establishmentUai) {
	// The type of statistic required : Statistic concerning only one establishment 
	String typeStat = StatisticConstants.TYPE_STAT_ESTABLISHMENT;
	return establishmentVisitStatisticDao.findDailyStatistic(day, establishmentType, establishmentUai, typeStat);
    }

    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.EstablishmentVisitStatisticService#findCountyWeeklyNumVisits(java.lang.String, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findCountyWeeklyNumVisits(String countyNumber, List<String> establishmentsTypes,
            Integer week, Integer year) {
	// The type of statistic required : Statistic concerning only one establishment 
	String typeStat = StatisticConstants.TYPE_STAT_SUM_ONE_COUNTY_ONE_ESTABLISHMENT_TYPE;
	
	// Gets the first and last days of the week
	Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
	Date lastWeekDay = DateUtils.addDays(firstWeekDay, 6);
	
	return findNumVisitsByCountyAndTypes(countyNumber, establishmentsTypes, typeStat, firstWeekDay, lastWeekDay);
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.EstablishmentVisitStatisticService#findWeeklyNumVisits(java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findEstablishmentWeeklyNumVisits(String establishmentUai, Integer week, Integer year) {
	// The type of statistic required : Statistic concerning only one establishment 
	String typeStat = StatisticConstants.TYPE_STAT_ESTABLISHMENT;
	
	// The type of the establishment
	Establishment establishment = establishmentDao.findEstablishmentByUai(establishmentUai);
	if(establishment == null) {
	    LOGGER.warn("The weekly number of visits can be retrieved, because there is no establishment associated to the UAI : [" + establishmentUai+ "]");
	    return 0;
	}
	String establishmentType = establishment.getType();
	
	// Gets the first and last days of the week
	Date firstWeekDay = DateUtils.getFirstWeekDay(week, year);
	Date lastWeekDay = DateUtils.addDays(firstWeekDay, 6);
	
	// Gets the number of visits
	Integer numVisits = establishmentVisitStatisticDao.findNumVisits(establishmentUai, firstWeekDay, lastWeekDay, establishmentType, typeStat);
	numVisits = (numVisits == null ? 0 : numVisits);
	
	return numVisits;
    }


    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.EstablishmentVisitStatisticService#findCountyMonthlyNumVisits(java.lang.String, java.util.List, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findCountyMonthlyNumVisits(String countyNumber, List<String> establishmentsTypes,
            Integer month, Integer year) {
	// The type of statistic required : Statistic concerning only one establishment 
	String typeStat = StatisticConstants.TYPE_STAT_SUM_ONE_COUNTY_ONE_ESTABLISHMENT_TYPE;
	
	// Gets the first and last days of the month
	Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
	Date lastMonthDay = DateUtils.getLastMonthDay(month, year);
	
	return findNumVisitsByCountyAndTypes(countyNumber, establishmentsTypes, typeStat, firstMonthDay, lastMonthDay);
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.services.statistic.EstablishmentVisitStatisticService#findMonthlyNumVisits(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Integer findEstablishmentMonthlyNumVisits(String establishmentUai, Integer month, Integer year) {
	// The type of statistic required : Statistic concerning only one establishment 
	String typeStat = StatisticConstants.TYPE_STAT_ESTABLISHMENT;
	
	// The type of the establishment
	Establishment establishment = establishmentDao.findEstablishmentByUai(establishmentUai);
	if(establishment == null) {
	    LOGGER.warn("The monthly number of visits can be retrieved, because there is no establishment associated to the UAI : [" + establishmentUai+ "]");
	    return 0;
	}
	String establishmentType = establishment.getType();
	
	// Gets the first and last days of the month
	Date firstMonthDay = DateUtils.getFirstMonthDay(month, year);
	Date lastMonthDay = DateUtils.getLastMonthDay(month, year);
	
	// Gets the number of visits
	Integer numVisits = establishmentVisitStatisticDao.findNumVisits(establishmentUai, firstMonthDay, lastMonthDay, establishmentType, typeStat);
	numVisits = (numVisits == null ? 0 : numVisits);
	
	return numVisits;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Retrieves a the number of visits made on the establishments located in the specified <code>countyNumber</code> having a type contained in the specified  <code>establishmentsTypes</code> .<br/>
     * This statistic only concerns the period contained between the specified <code>startDay</code> and <code>endDay</code>.<br/>
     * Moreover, the statistic used to retrieve the number of visits has to have the specified type <code>typeStat</code>.
     * 
     * @param countyNumber
     * 			The county number associated to the establishments.
     * @param establishmentsTypes
     * 			The establishments types.
     * @param startDate
     * 			The start day of the statistic to retrieve.
     * @param endDate
     * 			The end day of the statistic to retrieve.
     * @param typeStat
     * 			The type of the statistic.
     * 
     * @return
     * 	the number of visits retrieved.<br/>
     * 	<code>0</code> if no statistic has been retrieved.
     */
    private Integer findNumVisitsByCountyAndTypes(String countyNumber, List<String> establishmentsTypes,
	    String typeStat, Date startDate, Date endDate) {
	Integer numVisits = establishmentVisitStatisticDao.findNumVisitsByCountyAndTypes(countyNumber, establishmentsTypes, startDate, endDate, typeStat);
	numVisits = (numVisits == null ? 0 : numVisits);
	return numVisits;
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
