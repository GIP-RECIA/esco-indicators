/**
 * 
 */
package org.esco.indicators.domain.beans.form;

import org.apache.log4j.Logger;

/**
 * Class representing the form used to display the available options for the services statistics.
 * 
 * @since  2012/07/12
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ServiceForm extends BasicForm {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ServiceForm.class);

    /** List : Services wanted by the user */
    String [] services;

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the services wanted by the user.
     * 
     * @return 
     * 	the  services wanted by the user. 
     */
    public String[] getServices() {
        return services;
    }

    /**
     * Sets the services wanted by the user.
     * 
     * @param services 
     * 			The services wanted by the user.
     */
    public void setServices(String[] services) {
        this.services = services;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
