/**
 * 
 */
package org.esco.indicators.domain.beans.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.utils.list.ListUtils;

/**
 * Form containing the basic form elements.
 * 
 * @since  2012/07/02
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class BasicForm {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicForm.class);

    /** Radio button : type of the monitoring */
    protected String monitoringType;
    
    /** Checkboxes : establishments type */
    protected String [ ] establishmentsTypes;
    
    /**
     * Input for displaying the selected start date.
     */
    protected String startDatePicker;
    
    /** 
     * Hidden input.
     * Date : Beginning of the period
     * Format : DD/mm/yyyy
     */
    protected Date startDate;

    /**
     * Input for displaying the selected end date.
     */
    protected String endDatePicker;
    
    /** 
     * Hidden input.
     * Date : End of the period
     * Format : DD/mm/yyyy
     */
    protected Date endDate;
    
    /** Checkboxes : users profiles */
    protected String [] usersProfiles;
    
    /** Dropdown box : county */
    protected String county;
    
    /** Checkbox : sum on counties */
    protected String sumOnCounties;
    
    /** Checkboxes : "lycees" types */
    protected String [] lyceesTypes;
    
    /** Checkboxes: "lycees agricoles" types */
    protected String [] laTypes;
    
    /** Checkboxes : establishments list */
    protected String [] establishments;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the monitoring type.
     * 
     * @return the monitoringType
     */
    public String getMonitoringType() {
        return monitoringType;
    }

    /**
     * Sets the monitoring type of the account activation.
     * 
     * @param monitoringType the monitoringType to set
     */
    public void setMonitoringType(String monitoringType) {
        this.monitoringType = monitoringType;
    }

    
    /**
     *  Gets all the establishments types.<br/>
     *  This array contains :
     *  <ul>
     *  	<li>The establishments types</li>
     *  	<li>The LA types</li>
     *  	<li>The Lycees types</li>
     *  <ul>
     *  
     * @return the establishmentsTypes
     */
    public String[] getAllEstablishmentsTypes() {
	// Adds the principal establishments types
	List<String> allTypes = new ArrayList<String>(Arrays.asList(establishmentsTypes));
	// Adds the others establishments types
	if(laTypes != null) {
	    allTypes.addAll(new ArrayList<String>(Arrays.asList(laTypes)));
	}
	if(lyceesTypes != null) {
	    allTypes.addAll(new ArrayList<String>(Arrays.asList(lyceesTypes)));
	}
	
        return allTypes.toArray(new String [allTypes.size()]);
    }
    
    /**
     *  Gets the establishments types.
     *  
     * @return the establishmentsTypes
     */
    public String[] getEstablishmentsTypes() {
        return establishmentsTypes;
    }

    /**
     * Sets the establishments types.
     * 
     * @param establishmentsTypes the establishmentsTypes to set
     */
    public void setEstablishmentsTypes(String[] establishmentsTypes) {
        this.establishmentsTypes = establishmentsTypes;
    }

    /**
     * Gets the start date.
     * 
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     * 
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     * 
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     * 
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the users profiles.
     * 
     * @return the usersProfiles
     */
    public String[] getUsersProfiles() {
        return usersProfiles;
    }

    /**
     * Sets the users profiles.
     * 
     * @param usersProfiles the usersProfiles to set
     */
    public void setUsersProfiles(String[] usersProfiles) {
        this.usersProfiles = usersProfiles;
    }

    /**
     * Gets the county number.
     * 
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * Sets the county number.
     * 
     * @param county the county to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * Gets the sum on counties field.
     * 
     * @return 
     * 	the sum on counties
     */
    public String getSumOnCounties() {
        return sumOnCounties;
    }

    /**
     * Sets the sum on counties field.
     * 
     * @param sumOnCounties 
     * 			The sum on counties to set
     */
    public void setSumOnCounties(String sumOnCounties) {
        this.sumOnCounties = sumOnCounties;
    }

    /**
     * Gets the types of the "lycees".
     * 
     * @return the lyceesTypes
     */
    public String[] getLyceesTypes() {
        return lyceesTypes;
    }

    /**
     * Sets the types of the "lycees".
     * 
     * @param lyceesTypes the lyceesTypes to set
     */
    public void setLyceesTypes(String[] lyceesTypes) {
        this.lyceesTypes = lyceesTypes;
    }

    /**
     * Gets the types of the "lycees agricoles".
     * 
     * @return the laTypes
     */
    public String[] getLaTypes() {
        return laTypes;
    }

    /**
     * Sets the types of the "lycees agricoles".
     * 
     * @param laTypes the laTypes to set
     */
    public void setLaTypes(String[] laTypes) {
        this.laTypes = laTypes;
    }

    /**
     * Gets the establishments.
     * 
     * @return 
     * 	the establishments
     */
    public String[] getEstablishments() {
        return establishments;
    }

    /**
     * Sets the establishments.
     * 
     * @param establishments 
     * 			The establishments to set.
     */
    public void setEstablishments(String[] establishments) {
        this.establishments = establishments;
    }

    
    /**
     * Gets the start date selected by a date picker.
     * 
     * @return
     * 	the start date selected by a date picker.
     */
    public String getStartDatePicker() {
        return startDatePicker;
    }

    /**
     * Sets the start date selected by a date picker.
     * 
     * @param startDatePicker 
     * 			The start date selected by a date picker to set.
     */
    public void setStartDatePicker(String startDatePicker) {
        this.startDatePicker = startDatePicker;
    }

    /**
     * Gets the end date selected by a date picker.
     * 
     * @return
     * 	the end date selected by a date picker.
     */
    public String getEndDatePicker() {
        return endDatePicker;
    }

    /**
     * Sets the end date selected by a date picker.
     * 
     * @param endDatePicker 
     * 			The end date selected by a date picker to set.
     */
    public void setEndDatePicker(String endDatePicker) {
        this.endDatePicker = endDatePicker;
    }


    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((county == null) ? 0 : county.hashCode());
	result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
	result = prime * result + ((endDatePicker == null) ? 0 : endDatePicker.hashCode());
	result = prime * result + Arrays.hashCode(establishments);
	result = prime * result + Arrays.hashCode(establishmentsTypes);
	result = prime * result + Arrays.hashCode(laTypes);
	result = prime * result + Arrays.hashCode(lyceesTypes);
	result = prime * result + ((monitoringType == null) ? 0 : monitoringType.hashCode());
	result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
	result = prime * result + ((startDatePicker == null) ? 0 : startDatePicker.hashCode());
	result = prime * result + ((sumOnCounties == null) ? 0 : sumOnCounties.hashCode());
	result = prime * result + Arrays.hashCode(usersProfiles);
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	BasicForm other = (BasicForm) obj;
	if (county == null) {
	    if (other.county != null)
		return false;
	} else if (!county.equals(other.county))
	    return false;
	if (endDate == null) {
	    if (other.endDate != null)
		return false;
	} else if (!endDate.equals(other.endDate))
	    return false;
	if (endDatePicker == null) {
	    if (other.endDatePicker != null)
		return false;
	} else if (!endDatePicker.equals(other.endDatePicker))
	    return false;
	if (!Arrays.equals(establishments, other.establishments))
	    return false;
	if (!Arrays.equals(establishmentsTypes, other.establishmentsTypes))
	    return false;
	if (!Arrays.equals(laTypes, other.laTypes))
	    return false;
	if (!Arrays.equals(lyceesTypes, other.lyceesTypes))
	    return false;
	if (monitoringType == null) {
	    if (other.monitoringType != null)
		return false;
	} else if (!monitoringType.equals(other.monitoringType))
	    return false;
	if (startDate == null) {
	    if (other.startDate != null)
		return false;
	} else if (!startDate.equals(other.startDate))
	    return false;
	if (startDatePicker == null) {
	    if (other.startDatePicker != null)
		return false;
	} else if (!startDatePicker.equals(other.startDatePicker))
	    return false;
	if (sumOnCounties == null) {
	    if (other.sumOnCounties != null)
		return false;
	} else if (!sumOnCounties.equals(other.sumOnCounties))
	    return false;
	if (!Arrays.equals(usersProfiles, other.usersProfiles))
	    return false;
	return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "BasicForm [monitoringType=" + monitoringType + ", establishmentsTypes="
		+ Arrays.toString(establishmentsTypes) + ", startDatePicker=" + startDatePicker
		+ ", startDate=" + startDate + ", endDatePicker=" + endDatePicker + ", endDate=" + endDate
		+ ", usersProfiles=" + Arrays.toString(usersProfiles) + ", county=" + county
		+ ", sumOnCounties=" + sumOnCounties + ", lyceesTypes=" + Arrays.toString(lyceesTypes)
		+ ", laTypes=" + Arrays.toString(laTypes) + ", establishments="
		+ Arrays.toString(establishments) + "]";
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
