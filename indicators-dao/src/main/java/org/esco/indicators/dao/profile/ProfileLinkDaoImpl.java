/**
 * 
 */
package org.esco.indicators.dao.profile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.profile.ProfileLink;
import org.esco.indicators.utils.dao.Parameters;
import org.esco.indicators.utils.dao.QueryManager;

/**
 * Implementation of the {@link ProfileLinkDao} interface.
 * 
 * @since  2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ProfileLinkDaoImpl implements ProfileLinkDao {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ProfileLinkDaoImpl.class);

    /** JPA Entity manager */
    @PersistenceContext
    private EntityManager entityManager;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link ProfileLinkDaoImpl} class.
     */
    public ProfileLinkDaoImpl() {
	super();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.dao.profile.ProfileLinkDao#findProfileLinksBetween(java.util.Date, java.util.Date, java.lang.String, java.lang.String)
     */
    @Override
    public List<ProfileLink> findProfileLinksBetween(String establishmentUai, String userProfile, Date startDate,
	    Date endDate) {
	// Name of the query to execute
	String namedQuery = "ProfileLink.findProfileLinksBetween";
	
	// Parameters setting
	Parameters parameters = new Parameters();
	parameters.put("linkStart", startDate);
	parameters.put("linkEnd", endDate);
	parameters.put("userProfile", userProfile);
	parameters.put("establishmentUai", establishmentUai);
	
	// Execution of the query
	List<Object> profileLinks = QueryManager.getResultList(entityManager, namedQuery, parameters);
	
	// Result
	List<ProfileLink> result = new ArrayList<ProfileLink>();
	for (Object profileLink : profileLinks) {
	    result.add((ProfileLink) profileLink);
	}
	
	return result;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.profile.ProfileLinkDao#findTotalNumLinkedAccounts(java.lang.String, java.sql.Date, java.sql.Date)
     */
    @Override
    public Integer findTotalNumLinkedAccounts(List<String> establishmentsUai, Date startDate, Date endDate) {
	// Name of the query to execute
	String namedQuery = "ProfileLink.findTotalNumLinkedAccounts";
	
	// Parameters setting
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("linkStart", startDate);
	parameters.put("linkEnd", endDate);
	
	// Execution of the query
	Long queryResult = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer totalNumLinkedAccounts = (queryResult == null ? null : queryResult.intValue());
	
	return totalNumLinkedAccounts;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.dao.profile.ProfileLinkDao#findTotalNumLinkedAccountsForProfiles(java.util.List, java.util.List, java.sql.Date, java.sql.Date)
     */
    @Override
    public Integer findTotalNumLinkedAccountsForProfiles(List<String> establishmentsUai, List<String> usesrProfiles, Date startDate,
	    Date endDate) {
	// Name of the query to execute
	String namedQuery = "ProfileLink.findTotalNumLinkedAccountsForProfile";
	
	// Parameters setting
	Parameters parameters = new Parameters();
	parameters.put("establishmentUaiList", establishmentsUai);
	parameters.put("userProfileList", usesrProfiles);
	parameters.put("linkStart", startDate);
	parameters.put("linkEnd", endDate);
	
	// Execution of the query
	Long queryResult = (Long) QueryManager.getSingleResult(entityManager, namedQuery, parameters);
	Integer totalNumLinkedAccounts = (queryResult == null ? null : queryResult.intValue());
	
	return totalNumLinkedAccounts;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
