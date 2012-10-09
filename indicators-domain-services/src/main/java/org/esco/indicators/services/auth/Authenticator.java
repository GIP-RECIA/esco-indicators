/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esco.indicators.services.auth;

import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.domain.beans.permission.GenericFilter;

/**
 * Interface providing function to retrieves informations on the authenticated users.
 * 
 * @since  2012/10/09
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface Authenticator {

	/**
	 * Gets the authenticated user.
	 * 
	 * @return 
	 * 		the authenticated user.<br/>
	 * 		<code>null</code> if the user is not authenticated.
	 * 
	 * @throws Exception 
	 */
	public User getUser();
	
	/**
	 * Gets the establishment filter associated to the authenticated user.<br/>
	 * This filter is used to know which establishments can be viewed by the user.
	 * 
	 * @return
	 * 		the establishment filter.<br/>
	 * 		<code>null</code> if the user is not authenticated.
	 */
	public GenericFilter getEstablishmentFilter();
	
}