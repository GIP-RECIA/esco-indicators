package org.esco.indicators.domain.beans.people;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.structure.Establishment;

/**
 * Class representing a user who belongs to an establishment.
 * An establishment can be a college, a university,...
 * 
 * @since 2012/05/24
 * @author Frapin Kevin <kevin.frapin@recia.fr>
 */
public class EstablishmentUser extends User {
    
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /**  Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentUser.class);

    /** List of the establishments linked to the user */
    private Set<Establishment> linkedEstablishments;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    
    /**
     * Gets the set of the establishments linked to the user.
     * @return the set of establishments linked to the user.
     */
    public Set<Establishment> getLinkedEstablishments() {
	return linkedEstablishments;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Links an establishment to the user.
     * 
     * @param establishment
     */
    public void linkEstablishment(Establishment establishment) {
	// Create an empty set of establishments if necessary
	Set<Establishment> currentEstablishments = getLinkedEstablishments();
	if(currentEstablishments == null) {
	    currentEstablishments = new HashSet<Establishment>();
	    setLinkedEstablishments(currentEstablishments);
	}
	
	// Link the user to the establishment
	currentEstablishments.add(establishment);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Sets the set of establishments linked to the user.
     * @param establishments
     * 			The set of establishments to link to the user.
     */
    private void setLinkedEstablishments(Set<Establishment> establishments) {
        this.linkedEstablishments = establishments;
    }
    

    //------------------------------------------------------------------------------ STATIC METHODS
}
