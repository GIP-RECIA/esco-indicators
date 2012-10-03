package org.esco.indicators.services.domain;

import java.io.Serializable;

import org.esco.indicators.domain.beans.people.User;

/**
 * Interface providing functions to retrieve informations on the domain.
 * 
 * @since  2012/10/02
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface DomainService extends Serializable {
	
	/**
	 * Get the user associated to the uid.
	 * 
	 * @param uid
	 * 			The UID of the user.
	 * 
	 * @return 
	 * 		the user assocuiated to the UID.
	 */
	public User getUser(String uid);

}
