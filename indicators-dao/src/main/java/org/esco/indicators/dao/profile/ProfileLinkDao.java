/**
 * 
 */
package org.esco.indicators.dao.profile;

import java.util.Date;
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
     * @param establishmentUai
     * 			The UAI of the establishment associated to the link.
     * @param userProfile
     * 			The user profile associated to the link.
     * @param startDate
     * 			The maximum creation date of the link.
     * @param endDate
     * 			The minimum destruction date of the link.
     * 
     * @return
     * 	the list of the profile links which are valids on the specified period.<br/>
     * 	an empty list if no profile links has been retrieved.
     */
    public List<ProfileLink> findProfileLinksBetween(String establishmentUai, String userProfile, Date startDate,
	    Date endDate);

    /**
     * Retrieves the total number of accounts that were linked to the establishment in the period (delimited by the start date and end date).<br/>
     * The accounts have to be associated to the specified user profile.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment.
     * @param userProfile
     * 			The user profile.
     * @param startDate
     * 			The start date of the period.
     * @param endDate
     * 			The end date of the period.
     * 
     * @return
     * 	the total number of accounts linked to the establishment in the specified period.<br/>
     * 	<code>null</code> if no data has been retrieved.
     */
    public Integer findTotalNumLinkedAccountsForProfile(String establishmentUai, String userProfile, Date startDate, Date endDate);
}
