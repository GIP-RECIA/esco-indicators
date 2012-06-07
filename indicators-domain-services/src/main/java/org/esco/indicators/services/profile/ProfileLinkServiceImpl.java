/**
 * 
 */
package org.esco.indicators.services.profile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.dao.profile.ProfileLinkDao;
import org.esco.indicators.domain.beans.profile.ProfileLink;

/**
 * Implementation of the {@link ProfileLinkService} interface.
 * 
 * @since 2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ProfileLinkServiceImpl implements ProfileLinkService {
    // ---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ProfileLinkServiceImpl.class);

    /** DAO providing access to the links between users / profiles / establishements */
    private ProfileLinkDao profileLinkDao;

    // -------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link ProfileLinkServiceImpl} class.
     */
    public ProfileLinkServiceImpl() {
	super();
    }

    // --------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the DAO providing access to the links between users / profiles / establishements.
     * 
     * @return the DAO providing access to the links between users / profiles / establishements.
     */
    public ProfileLinkDao getProfileLinkDao() {
	return profileLinkDao;
    }

    /**
     * Sets the DAO providing access to the links between users / profiles / establishements.
     * 
     * @param profileLinkDao
     *            the DAO to set.
     */
    public void setProfileLinkDao(ProfileLinkDao profileLinkDao) {
	this.profileLinkDao = profileLinkDao;
    }

    // ------------------------------------------------------------------------------ PUBLIC METHODS
    /*
     * (non-Javadoc)
     * 
     * @see org.esco.indicators.services.profile.ProfileLinkService#findUidsByProfile(java.lang.String,
     * java.lang.String, java.sql.Date, java.sql.Date)
     */
    @Override
    public List<String> findUidsByProfile(String userProfile, String establishmentUai, Date startDate,
	    Date endDate) {
	// Gets the links that are valid on the period and that are lassociated to the user profile and the
	// establishement
	List<ProfileLink> profileLinks = profileLinkDao.findProfileLinksBetween(startDate, endDate, userProfile, establishmentUai);
	
	// Gets the UIDs of the users
	List<String> uids = new ArrayList<String>();
	for (ProfileLink profileLink : profileLinks) {
	    uids.add(profileLink.getUserUid());
	}
	
	return uids;
    }

    // ----------------------------------------------------------------------------- PRIVATE METHODS

    // ------------------------------------------------------------------------------ STATIC METHODS
}
