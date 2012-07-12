/**
 * 
 */
package org.esco.indicators.domain.beans.form;

import org.apache.log4j.Logger;

/**
 * Class representing the form used to display the available options for the wantedServices statistics.
 * 
 * @since  2012/07/12
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ServiceForm extends BasicForm {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ServiceForm.class);

    /** List : Available services */
    String [] availableServices;
    
    /** List : Services wanted by the user */
    String [] wantedServices;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
 
    /**
     * Gets the available services.
     * 
     * @return 
     * 	the available services
     */
    public String[] getAvailableServices() {
        return availableServices;
    }

    /**
     * Sets the available services.
     * 
     * @param availableServices 
     * 			The available services to set.
     */
    public void setAvailableServices(String[] availableServices) {
        this.availableServices = availableServices;
    }
    
    /**
     * Gets the wantedServices wanted by the user.
     * 
     * @return 
     * 	the  wantedServices wanted by the user. 
     */
    public String[] getWantedServices() {
        return wantedServices;
    }

    /**
     * Sets the wantedServices wanted by the user.
     * 
     * @param wantedServices 
     * 			The wantedServices wanted by the user.
     */
    public void setWantedServices(String[] services) {
        this.wantedServices = services;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
