/**
 * 
 */
package org.esco.indicators.dao.statistic;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.statistic.EstablishmentVisitStatistic;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.utils.dao.Parameters;
import org.esco.indicators.utils.dao.QueryManager;

/**
 * Implementation of the {@link EstablishmentVisitStatisticDao} interface.
 * 
 * @since 2012/05/31
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentVisitStatisticDaoImpl implements EstablishmentVisitStatisticDao {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentVisitStatisticDaoImpl.class);

    /** JPA Entity manager */
    @PersistenceContext
    private EntityManager entityManager;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the EstablishmentVisitStatisticDaoImpl class
     */
    public EstablishmentVisitStatisticDaoImpl() {
	super();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    ///////////////////////////////////////////////////////
    // DAILY STATISTICS
    ///////////////////////////////////////////////////////
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EstablishmentVisitStatisticDao#findDailyStatistic(java.lang.String, java.util.Date)
     */
    @Override
    public EstablishmentVisitStatistic findDailyStatistic(Date day, String establishmentType, String establishmentUai, String typeStat) {
	// Name of the query to execute
	String namedQuery = "EstablishmentVisitStatistic.Daily.findVisitStatistic";

	// Parameters of the query
	Parameters parameters = new Parameters();
	parameters.put("day", day);
	parameters.put("establishmentType", establishmentType);
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("typeStat", typeStat);

	
	// Try to retrieve the daily statistic
	EstablishmentVisitStatistic establishmentVisitStatistic = (EstablishmentVisitStatistic) QueryManager
		.getSingleResult(entityManager, namedQuery, parameters);	
	
	return establishmentVisitStatistic;
    }

    ///////////////////////////////////////////////////////
    // WEEKLY / MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.statistic.EstablishmentVisitStatisticDao#findWeeklyNumVisits(java.lang.String, java.util.Date, java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public Integer findNumVisits(String establishmentUai, Date startDay, Date endDay,
	    String establishmentType, String typeStat) {
	// Name of the query to execute
	String namedQuery = "EstablishmentVisitStatistic.findNumVisits";

	// Parameters of the query
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("startDate", startDay);
	parameters.put("endDate", endDay);
	parameters.put("establishmentType", establishmentType);
	parameters.put("typeStat", typeStat);
	
	// Try to retrieve the daily statistic
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);	
	Integer numVisits = (result == null ? null : result.intValue());
	
	return numVisits;
    }


    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
