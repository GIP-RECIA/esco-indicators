/**
 * 
 */
package org.esco.indicators.domain.beans.form;

import org.apache.log4j.Logger;

/**
 * Class representing the account activation form displayed on the accounts activations web page.
 * 
 * @since  2012/06/15
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class AccountActivationForm {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(AccountActivationForm.class);
    
    /** Radio button : type of the monitoring */
    String monitoringType;
    
    /** Checkbox : establishments type */
    String [ ] establishmentsTypes;
    
    /** 
     * Date : Beginning of the period
     * Format : yyyy-DD-mm
     */
    String startDate;

    /** 
     * Date : End of the period
     * Format : yyyy-DD-mm
     */
    String endDate;
    
    /** Checkbox : users profiles */
    String [] usersProfiles;
    
    /** Dropdown box : county */
    Integer county;
    
    /** Checkbox : sum on counties */
    boolean sumOnCounties;
    
    /** Checkbox : "lycees" types */
    String [] lyceesTypes;
    
    /** Checkbox : "lycees agricoles" types */
    String [] laTypes;

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
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     * 
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     * 
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     * 
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
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
    public Integer getCounty() {
        return county;
    }

    /**
     * Sets the county number.
     * 
     * @param county the county to set
     */
    public void setCounty(Integer county) {
        this.county = county;
    }

    /**
     * Gets the boolean indicating if a sum by counties is done.
     * 
     * @return the sumOnCounties
     */
    public boolean isSumOnCounties() {
        return sumOnCounties;
    }

    /**
     * Gets the boolean indicating if a sum by counties is done.
     * 
     * @param sumOnCounties the sumOnCounties to set
     */
    public void setSumOnCounties(boolean sumOnCounties) {
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

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
