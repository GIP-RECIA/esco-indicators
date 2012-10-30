/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esco.indicators.services.auth;

import java.util.List;

import org.esco.indicators.domain.beans.people.User;
import org.esco.indicators.domain.beans.permission.GenericFilter;
import org.esco.indicators.domain.beans.structure.Establishment;

/**
 * Interface providing function to retrieves informations on the authenticated users.
 * 
 * @since  2012/10/09
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface Authenticator {
	/**
	 * Gets the establishments that the user is allowed to see.<br/>
	 * These establishments are retrieved regarding to the user establishment filter.
	 * 
	 * @return
	 * 		the list of allowed establishments (This list can be empty).<br/>
	 * 		<code>null</code> if the user is not authenticated.
	 */
	public List<Establishment> getAllowedEstablishments();
    
	/**
	 * Gets the authenticated user.
	 * 
	 * @return 
	 * 		the authenticated user.<br/>
	 * 		<code>null</code> if the user is not authenticated.
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
	
	/**
	 * Indicates if the authenticated user has the permission to see informations
	 * on the establishment having the given UAI.<br/>
	 * 
	 * The establishment filter is used to know if the authenticated user has
	 * the permission, or not.
	 * 
	 * @param establishmentUAI
	 * 			The UAI of the establishment.
	 * 
	 * @return
	 * 	<code>true</code> if the authenticated user has the right to see informations on the establishment.<br/>
	 * 	<code>false</code> in other cases.
	 */
	public boolean hasPermissionOnEstablishment(String establishmentUAI);
	
	/**
	 * Indicates if the authenticated user is a super user or not regarding to the establishment filter.
	 * 
	 * @return
	 * 		<code>true</code> if the authenticated user is a super user.<br/>
	 * 		<code>false</code> in other cases.
	 */
	public boolean isSuperUser();
	
}