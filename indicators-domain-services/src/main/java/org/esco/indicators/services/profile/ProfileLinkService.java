/**
 * 
 */
package org.esco.indicators.services.profile;

import java.sql.Date;
import java.util.List;

/**
 * Interface providing functions to access the links between users and profiles into establishements.
 * 
 * @since 2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface ProfileLinkService {

    /**
     * Retrieves the UIDs of the users which have the specified profile (<code>userProfile</code>) into the
     * establishment (<code>establishmentUai</code>) on the specified period.<br/>
     * The period is delimited by the specified <code>startDate</code> and <code>endDate</code>.<br/>
     * 
     * @param userProfile
     * 			The user profile of the link.
     * @param establishmentUai
     * 			The establishment UAI of the link.
     * @param startDate
     * 			Beginning of link validity.
     * @param endDate
     * 			End of link validity.
     * @return
     * 	the list containing the UIDs of the users.<br/>
     * 	an empty list if no UID has been retrieved.
     */
    public List<String> findUidsByProfile(String userProfile, String establishmentUai, Date startDate,
	    Date endDate);

}
