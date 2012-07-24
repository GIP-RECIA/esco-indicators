/**
 * 
 */
package org.esco.indicators.domain.beans.form;

import java.util.Arrays;

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
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Arrays.hashCode(availableServices);
	result = prime * result + Arrays.hashCode(wantedServices);
	return result;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ServiceForm other = (ServiceForm) obj;
	if (!Arrays.equals(availableServices, other.availableServices))
	    return false;
	if (!Arrays.equals(wantedServices, other.wantedServices))
	    return false;
	return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ServiceForm [availableServices=" + Arrays.toString(availableServices) + ", wantedServices="
		+ Arrays.toString(wantedServices) + ", monitoringType=" + monitoringType
		+ ", establishmentsTypes=" + Arrays.toString(establishmentsTypes) + ", startDatePicker="
		+ startDatePicker + ", startDate=" + startDate + ", endDatePicker=" + endDatePicker
		+ ", endDate=" + endDate + ", usersProfiles=" + Arrays.toString(usersProfiles) + ", county="
		+ county + ", sumOnCounties=" + sumOnCounties + ", lyceesTypes="
		+ Arrays.toString(lyceesTypes) + ", laTypes=" + Arrays.toString(laTypes)
		+ ", establishments=" + Arrays.toString(establishments) + "]";
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
