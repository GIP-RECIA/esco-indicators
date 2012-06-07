/**
 * 
 */
package org.esco.indicators.dao.profile;

import java.sql.Date;
import java.util.List;

import org.esco.indicators.domain.beans.profile.ProfileLink;

/**
 * Interface providing functions to access data on the links between users and profiles into establishments.
 * 
 * @since 2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface ProfileLinkDao {

    /**
     * Retrieves the links which are associated to the specified establishment (<code>establishmentUai</code>)
     * and associated to the specified user profile (<code>userProfile</code>).<br/>
     * These links have to be valids on the specified period, i.e., they have to start before the specified
     * <code>startDate</code> and they have to end after the specified <code>endDate</code>.
     * 
     * @param startDate
     * 			The maximum creation date of the link.
     * @param endDate
     * 			The minimum destruction date of the link.
     * @param userProfile
     * 			The user profile associated to the link.
     * @param establishmentUai
     * 			The UAI of the establishment associated to the link.
     * @return
     * 	the list of the profile links which are valids on the specified period.<br/>
     * 	an empty list if no profile links has been retrieved.
     */
    public List<ProfileLink> findProfileLinksBetween(Date startDate, Date endDate, String userProfile,
	    String establishmentUai);

}
