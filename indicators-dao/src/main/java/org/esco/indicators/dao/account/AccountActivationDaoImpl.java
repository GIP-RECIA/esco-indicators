/**
 * 
 */
package org.esco.indicators.dao.account;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.account.AccountActivation;
import org.esco.indicators.utils.dao.Parameters;
import org.esco.indicators.utils.dao.QueryManager;

/**
 * Implementation of the {@link AccountActivationDao} interface.
 * 
 * @since  2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class AccountActivationDaoImpl implements AccountActivationDao {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(AccountActivationDaoImpl.class);

    /** JPA Entity manager */
    @PersistenceContext
    private EntityManager entityManager;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link AccountActivationDaoImpl} class.
     */
    public AccountActivationDaoImpl() {
	super();
    }


    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.account.AccountActivationDao#findActivatedAccountsBetween(Date, Date)
     */
    @Override
    public List<AccountActivation> findActivatedAccountsBetween(Date startDate, Date endDate) {
	// Name of the query to execute
	String namedQuery = "AccountActivation.findActivatedAccountsBetween";
	
	// Parameters setting
	Parameters parameters = new Parameters();
	parameters.put("activationStart", startDate);
	parameters.put("activationEnd", endDate);
	
	// Execution of the query
	List<AccountActivation> result = new ArrayList<AccountActivation>();
	List<Object> accountActivations = QueryManager.getResultList(entityManager, namedQuery, parameters);
	for (Object accountActivation : accountActivations) {
	    result.add((AccountActivation) accountActivation);
	}
	
	return result;
    }
    
    
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
