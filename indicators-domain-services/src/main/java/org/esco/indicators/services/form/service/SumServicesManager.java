/**
 * 
 */
package org.esco.indicators.services.form.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Class providing functions that allows to know if a service represents the sum of others services.<br/>
 * In fact, there are two kinds of services :
 * <ul>
 * 	<li>The simple services</li>
 * 	<li>The sum services (sum of simple services)</li>
 * </ul>
 * 
 * So, this class is able to :
 * <ul>
 * 	<li>Indicate if a service is a sum service</li>
 * 	<li>Provide the list of simple services aggregated by a sum service</li>
 * </ul>
 * 
 * All these informations are based on the services names.
 * 
 * @since  2012/07/27
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class SumServicesManager {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(SumServicesManager.class);
    
    /** Map containing the simple services names indexed by sum services names */
    Map<String,List<String>> simpleServicesBySumServices;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link SumServicesManager}.
     */
    public SumServicesManager() {
	super();
	this.simpleServicesBySumServices = new HashMap<String, List<String>>();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the map containing the simple services indexed by the sum services.
     * 
     * @param simpleServicesBySumServices 
     * 			the simpleServicesBySumServices to set
     */
    public void setSimpleServicesBySumServices(Map<String, List<String>> simpleServicesBySumServices) {
        this.simpleServicesBySumServices = simpleServicesBySumServices;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Indicates if a service is a sum service.
     * 
     * @param serviceName
     * 			The name of the service.
     * 
     * @return
     * 	<code>true</code> if the service is a sum service.<br/>
     * 	<code>false</code> in other cases.
     */
    public boolean isSumService(String serviceName) {
	return (simpleServicesBySumServices.get(serviceName) != null);
    }
    
    /**
     * Gets the simple services associated the sum service.
     * 
     * @param sumServiceName
     * 			The name of the sum service.
     * 
     * @return
     * 	the list of the simple services names associated to the sum service.<br/>
     * 	<code>null</code> if no simple services are associated to the sum service.
     */
    public List<String> getSimpleServices(String sumServiceName) {
	List<String> simpleServices = simpleServicesBySumServices.get(sumServiceName);
	if(simpleServices != null) {
	    LOGGER.debug("The service with the name : [" + sumServiceName + "] is a sum service associated to the simple services : [" + simpleServices.toString() + "]");
	}
	return simpleServices;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
