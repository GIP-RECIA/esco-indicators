/**
 * 
 */
package org.esco.indicators.services.ldap;

import java.util.List;

import org.apache.log4j.Logger;
import org.esupportail.commons.services.ldap.LdapEntity;
import org.esupportail.commons.services.ldap.LdapException;
import org.esupportail.commons.services.ldap.SimpleLdapEntityServiceImpl;

/**
 * Implementation of the {@link LdapEstablishmentService} interface.
 * 
 * @since 2012/05/31
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class LdapEstablishmentServiceImpl extends SimpleLdapEntityServiceImpl  implements LdapEstablishmentService {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(LdapEstablishmentServiceImpl.class);
    
    /** Serial version UID */
    private static final long serialVersionUID = 3107267564023750290L;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.services.ldap.LdapEstablishmentService#findEstablishmentName(java.lang.String)
     */
    @Override
    // TODO : Use a configurable UAI filter ?
    public String findEstablishmentName(String uai) {
	// Make the filter
	String filter = "(ESCOUAI=" + uai + ")";
	String establishmentName = "";
	
	// Try to retrieve the name of the establishment
	try {
	    List<LdapEntity> ldapEntities =  getLdapEntitiesFromFilter(filter);	
	    if(ldapEntities != null && ldapEntities.size() == 1) {
		    establishmentName = ldapEntities.get(0).getAttribute("ENTStructureNomCourant");
	    }
	} catch(LdapException e) {
	    LOGGER.error("A LDAP exception has been thrown : " + e.getMessage());
	}
	
	return establishmentName;
    }
    
    

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
