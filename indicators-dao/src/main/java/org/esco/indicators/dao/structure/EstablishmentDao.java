/**
 * 
 */
package org.esco.indicators.dao.structure;

import java.util.Set;

import org.esco.indicators.domain.beans.structure.Establishment;

/**
 * Interface providing functions to access establishments.
 * 
 * @since : 2012/05/25
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface EstablishmentDao {
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Retrieves the establishment, in the statistics database, associated to the specified 
     * <code>uai</code>.
     * @param uai
     * 			The UAI of the establishment to retrieve.
     * @return 
     * 	the establishment present in the statistics database with the specified UAI. <br/>
     * 	<code>null</code> if no establishment has been retrieved.
     */
    public Establishment findEstablishmentByUai(String uai);
    
    /**
     * Retrieves the establishements, in the statistics database, that are geographically localised
     * in the county (associated to the <code>countyNumber</code>)
     * @param countyNumber
     * 			The number of the county.
     * @return 
     * 	a set containing the establishments geographically localised in the specified 
     * 	county.
     */
    public Set<Establishment> findEstablishmentsByCountyNumber(Integer countyNumber);
    
    /**
     * Retrieves the establishments, in the statistics database, having the same type as the 
     * specified <code>type</code>.
     * @param type
     * 			The type of establishment.
     * @return
     * 	a set containing the establishments having a type equal to the specified <code>type</code>.
     */
    public Set<Establishment> findEstablishmentsByType(String type);
}
