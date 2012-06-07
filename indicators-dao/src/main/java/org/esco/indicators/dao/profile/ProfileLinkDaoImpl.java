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
     * @see org.esco.indicators.dao.profile.ProfileLinkDao#findProfileLinksBetween(java.sql.Date, java.sql.Date, java.lang.String, java.lang.String)
     */
    @Override
    public List<ProfileLink> findProfileLinksBetween(Date startDate, Date endDate, String userProfile,
	    String establishmentUai) {
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

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
