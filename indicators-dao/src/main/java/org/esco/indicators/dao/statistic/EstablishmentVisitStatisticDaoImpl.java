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

/**
 * Implementation of the {@link EstablishmentVisitStatisticDao} interface.
 * 
 * @since : 31/05/2012
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
	// Creation of the query and setting of the parameters
	Query query = entityManager.createNamedQuery("EstablishmentVisitStatistic.Daily.findVisitStatistic");
	query.setParameter("day", day);
	query.setParameter("establishmentType", establishmentType);
	query.setParameter("establishmentUai", establishmentUai);
	query.setParameter("typeStat", typeStat);

	
	// Try to retrieve the daily statistic
	EstablishmentVisitStatistic establishmentVisitStatistic = null;
	try {
	    establishmentVisitStatistic = (EstablishmentVisitStatistic) query.getSingleResult();
	} catch (NoResultException e) {
	    LOGGER.debug("No daily statistic of the type : [" + typeStat + "] has been found for the day : ["
		    + day.toString() + "] for the establishment UAI : [" + establishmentUai
		    + "] with the establishment type : [" + establishmentType + "]");
	} catch (NonUniqueResultException e) {
	    LOGGER.warn("More than one daily statistic of the type : [" + typeStat + "] has been found for the day : ["
		    + day.toString() + "] for the establishment UAI : [" + establishmentUai
		    + "] with the establishment type : [" + establishmentType + "]");
	} catch (IllegalStateException e) {
	    LOGGER.error("An error occured during the retrieval of the daily statistic of the type : ["
		    + typeStat + "] has been found for the day : [" + day.toString()
		    + "] for the establishment UAI : [" + establishmentUai
		    + "] with the establishment type : [" + establishmentType + "]");
	}
	
	return establishmentVisitStatistic;
    }


    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
