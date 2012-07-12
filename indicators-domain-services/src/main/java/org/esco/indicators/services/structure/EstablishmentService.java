/**
 * 
 */
package org.esco.indicators.services.structure;

import java.util.List;
import java.util.Set;

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
     * @return the <code>Set</code> of establishments associated to the specified <code>countyNumber</code>. <br/>
     *         an empty <code>Set</code> if no estbalishment has been retrieved.
     */
    public Set<Establishment> findEstablishmentsByCountyNumber(Integer countyNumber);

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
     * @return a <code>Set</code> containing the establishments geographically localised in the specified county numbers
     *         and with a type among the specified ones.<br/>
     *         an empty <code>Set</code> if no estbalishment has been retrieved.
     */
    public Set<Establishment> findEstablishmentsByCountyNumbersAndTypes(List<Integer> countyNumbers,
	    List<String> types);

    /**
     * Retrieves the establishments, in database, associated to the specified <code>type</code>.
     * 
     * @param type
     *            The type of the searched establishments.
     * @return the <code>Set</code> of establishments associated to the specified <code>type</code>. <br/>
     *         an empty <code>Set</code> if no estbalishment has been retrieved.
     */
    public Set<Establishment> findEstablishmentsByType(String type);

}
