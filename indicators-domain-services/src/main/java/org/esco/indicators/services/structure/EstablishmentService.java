/**
 * 
 */
package org.esco.indicators.services.structure;

import java.util.Set;

import org.esco.indicators.domain.beans.structure.Establishment;

/**
 * Interface providing services for access establishments data.
 * 
 * @since 2012/05/29
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface EstablishmentService {
    
    /**
     * Retrieves the establishment, in database, associated to the specified <code>uai</code>.
     * 
     * @param uai
     * 			The UAI of the searched establishment.
     * @return
     * 	the establishement associated to the <code>uai</code> if  it has been retrieved.<br/>
     * 	<code>null</code> if no establishment has been retrieved.
     */
    public Establishment findEstablishmentByUai(String uai);
    
    /**
     * Retrieves the establishments, in database, associated to the specified <code>countyNumber</code>.
     * 
     * @param countyNumber
     * 			The county number of the searched establishments.
     * @return
     * 	the <code>Set</code> of establishments associated to the specified <code>countyNumber</code>. <br/>
     * 	an empty <code>Set</code> if no estbalishment has been retrieved.
     */
    public Set<Establishment> findEstablishmentsByCountyNumber(Integer countyNumber);
    
    /**
     * Retrieves  the establishments, in database, associated to the specified <code>type</code>.
     * 
     * @param type
     * 			The type of the searched establishments.
     * @return
     * 	the <code>Set</code> of establishments associated to the specified <code>type</code>. <br/>
     * 	an empty <code>Set</code> if no estbalishment has been retrieved.
     */
    public Set<Establishment> findEstablishmentsByType(String type);

}
