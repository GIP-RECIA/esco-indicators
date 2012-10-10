/**
 * 
 */
package org.esco.indicators.dao.structure;

import java.util.List;
import java.util.Set;

import org.esco.indicators.domain.beans.structure.Establishment;

/**
 * Interface providing functions to access establishments.
 * 
 * @since 2012/05/25
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
    public List<Establishment> findEstablishmentsByCountyNumber(String countyNumber);
    
    /**
     * Retrieves the establishements, in the statistics database, that are geographically localised
     * in the counties (associated to the <code>countyNumbers</code>)
     * 
     * @param countyNumbers
     * 			The numbers of the counties.
     * 
     * @return 
     * 	a set containing the establishments geographically localised in the specified 
     * 	counties.
     */
    public List<Establishment> findEstablishmentsByCountyNumbers(List<String> countyNumbers);
    
    /**
     * Retrieves the establishements, in the statistics database, that are geographically localised
     * in the counties (associated to the <code>countyNumbers</code>) and that have the same type
     * as one of the specified <code>types</code>.
     * 
     * @param countyNumbers
     * 			The numbers of the counties.
     * 
     * @param types
     * 			The types of the establishments.
     * 
     * @return 
     * 	a list containing the establishments geographically localised in the specified 
     * 	county numbers and with a type among the specified ones.
     */
    public List<Establishment> findEstablishmentsByCountyNumbersAndTypes(List<String> countyNumbers, List<String> types);
    
    /**
     * Retrieves the establishments, in the statistics database, having the same type as the 
     * specified <code>type</code>.
     * @param type
     * 			The type of establishment.
     * @return
     * 	a list containing the establishments having a type equal to the specified <code>type</code>.
     */
    public List<Establishment> findEstablishmentsByType(String type);
    
    /**
     * Retrieves the establishments, in the statistics database, having the same type as one contained 
     * in the specified <code>types</code>.
     * @param types
     * 			The establishments types.
     * @return
     * 	a list containing the establishments having a type equal to one of the specified <code>types</code>.
     */
    public List<Establishment> findEstablishmentsByTypes(List<String> types);

    /**
     * Retrieves the establishments, in the statistics database, having the same uai as one contained 
     * in the specified <code>uais</code>.
     * @param uais
     * 			The establishments uais.
     * @return
     * 	a list containing the establishments having a uai equal to one of the specified <code>uais</code>.
     */
    public List<Establishment> findEstablishmentByUais(List<String> uais);
    
}
