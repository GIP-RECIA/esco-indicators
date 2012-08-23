/**
 * 
 */
package org.esco.indicators.services.structure;

import java.util.ArrayList;
import java.util.List;

import org.esco.indicators.domain.beans.structure.Establishment;

/**
 * Interface providing wantedServices for access establishments data.
 * 
 * @since 2012/05/29
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface EstablishmentService {

    /**
     * Retrieves the establishment, in database, associated to the specified <code>uai</code>.
     * 
     * @param uai
     *            The UAI of the searched establishment.
     * @return the establishement associated to the <code>uai</code> if it has been retrieved.<br/>
     *         <code>null</code> if no establishment has been retrieved.
     */
    public Establishment findEstablishmentByUai(String uai);

    /**
     * Retrieves the establishments, in database, associated to the specified <code>countyNumber</code>.
     * 
     * @param countyNumber
     *            The county number of the searched establishments.
     * @return the <code>List</code> of establishments associated to the specified <code>countyNumber</code>. <br/>
     *         an empty <code>List</code> if no estbalishment has been retrieved.
     */
    public List<Establishment> findEstablishmentsByCountyNumber(String countyNumber);

    /**
     * Retrieves the establishements, in database, that are geographically localised in the counties
     * (associated to the <code>countyNumbers</code>) and that have the same type as one of the specified
     * <code>types</code>.
     * 
     * @param countyNumbers
     *            The county numbers of the searched establishments.
     * 
     * @param types
     *            The establishment types of the searched establishments.
     * 
     * @return a <code>List</code> containing the establishments geographically localised in the specified county numbers
     *         and with a type among the specified ones.<br/>
     *         an empty <code>List</code> if no estbalishment has been retrieved.
     */
    public List<Establishment> findEstablishmentsByCountyNumbersAndTypes(List<String> countyNumbers,
	    List<String> types);

    /**
     * Retrieves the establishments, in database, associated to the specified <code>type</code>.
     * 
     * @param type
     *            The type of the searched establishments.
     * @return the <code>List</code> of establishments associated to the specified <code>type</code>. <br/>
     *         an empty <code>List</code> if no estbalishment has been retrieved.
     */
    public List<Establishment> findEstablishmentsByType(String type);
    
    /**
     * Gets the UAI of the establishments located in the county and having one of the specified type.
     * 
     * @param countyNumber
     * 			The number of the county.
     * @param establishmentsTypes 
     * 			The types of the establishments.
     * 
     * @return
     * 	the list containing the UAI of the establishments located in the county.<br/>
     * 	an empty list if no establishments UAI has been retrieved.
     */
    public List<String> findEstablishmentsUaiByCounty(String countyNumber, List<String> establishmentsTypes);

}
