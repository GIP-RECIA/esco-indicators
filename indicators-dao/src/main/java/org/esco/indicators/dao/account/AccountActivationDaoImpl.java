/**
 * 
 */
package org.esco.indicators.dao.account;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
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
     * @see org.esco.indicators.dao.account.AccountActivationDao#findNumActivatedAccounts(java.lang.String, java.util.Date, java.util.Date)
     */
    @Override
    public Integer findNumActivatedAccounts(String establishmentUai, Date startDate, Date endDate) {
	// Name of the query to execute
	String namedQuery = "AccountActivation.findNumActivatedAccounts";
	
	// Parameters setting
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("activationStart", startDate);
	parameters.put("activationEnd", endDate);
	
	// Execution of the query
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numActivatedAccounts = (result != null ? result.intValue() : null);
	
	return numActivatedAccounts;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.account.AccountActivationDao#findNumActivatedAccountsBetween(java.lang.String, java.lang.String, java.util.Date, java.util.Date)
     */
    @Override
    public Integer findNumActivatedAccountsForProfiles(String establishmentUai, List<String> usersProfiles, Date startDate, Date endDate) {
	// Name of the query to execute
	String namedQuery = "AccountActivation.findNumActivatedAccountsForProfiles";
	
	// Parameters setting
	Parameters parameters = new Parameters();
	parameters.put("establishmentUai", establishmentUai);
	parameters.put("userProfileList", usersProfiles);
	parameters.put("activationStart", startDate);
	parameters.put("activationEnd", endDate);
	
	// Execution of the query
	Long result = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer numActivatedAccounts = (result != null ? result.intValue() : null);
	
	return numActivatedAccounts;
    }
    
    
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
