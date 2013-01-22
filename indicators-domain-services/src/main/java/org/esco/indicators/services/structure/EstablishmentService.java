/**
 * 
 */
package org.esco.indicators.services.structure;

import java.util.HashMap;
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
     * Retrieves the establishments associated to the specified <code>countyNumber</code>.
     * 
     * @param countyNumber
     *            The county number of the searched establishments.
     * @return the <code>List</code> of establishments associated to the specified <code>countyNumber</code>. <br/>
     *         an empty <code>List</code> if no estbalishment has been retrieved.
     */
    public List<Establishment> findEstablishmentsByCountyNumber(String countyNumber);
    
    /**
     * Retrieves the establishements that are geographically localised in the counties
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
     * Retrieves the establishments by their properties names and values.<br/>
     * An establishment is retrieved is at least one of the property name and property value is contained in the establishment.<br/>
     * <br/>
     * 
     * <u>For instance :</u><br/>
     * If the given properties values and names are :
     * <ul>
     * 	<li>Property name 1 = "type"</li>
     * 	<ul>
     * 		<li>Property value 1.1 = "University"</li>
     * 		<li>Property value 1.2 = "College"</li>
     * 	</ul>
     * 	<li>Property name 2 = "countyNumber"</li>
     * 	<ul>
     * 		<li>Property value 2.1 = "69"</li>
     * 		<li>Property value 2.2 = "435"</li>
     * 	</ul>
     * </ul>
     * 
     * The following estbalishment will be retrieved :
     * <ul>
     * 	<li>Establishment property 1 = "type"</li>
     * 	<ul>
     * 		<li>Establishment value 1.1 = "University"</li>
     * 	</ul>
     * 	<li>Establishement property 2 = "countyNumber"</li>
     * 	<ul>
     * 		<li>Establishment value 2.1 = "234"</li>
     * 	</ul>
     * </ul>
     * 
     * It will be retrieved because the "type" property is equal to "University" which corresponds to one of the given properties names and values.
     * 
     * @param propertiesNamesAndValues
     * 			The map containing the properties names associated to their respective values.
     * 
     * @return
     * 	the list of estbalishments having at least one property name and value corresponding to the given ones.<br/>
     * 	An empty list if no establishment has been retrieved.
     */
    public List<Establishment> findEstablishmentsByPropertiesNamesAndValues(HashMap<String, Set<String>> propertiesNamesAndValues);
    
    /**
     * Retrieves the establishments associated to the specified <code>type</code>.
     * 
     * @param type
     *            The type of the searched establishments.
     * @return the <code>List</code> of establishments associated to the specified <code>type</code>. <br/>
     *         an empty <code>List</code> if no estbalishment has been retrieved.
     */
    public List<Establishment> findEstablishmentsByType(String type);
    
    /**
     * Retrieves the establishment associated to the specified <code>uai</code>.
     * 
     * @param uai
     *            The UAI of the searched establishment.
     * @return the establishement associated to the <code>uai</code> if it has been retrieved.<br/>
     *         <code>null</code> if no establishment has been retrieved.
     */
    public Establishment findEstablishmentByUai(String uai);
    
    /**
     * Retrieves the establishments associated to the specified <code>uais</code>.
     * 
     * @param uais
     *            The UAIs of the searched establishments.
     *            
     * @return the establishements associated to the <code>uais</code> if it has been retrieved.<br/>
     *         <code>null</code> if no establishment has been retrieved.
     */
    public List<Establishment> findEstablishmentsByUais(List<String> uais);
    
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
