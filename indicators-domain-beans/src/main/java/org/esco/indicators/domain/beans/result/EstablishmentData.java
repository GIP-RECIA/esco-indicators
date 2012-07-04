/**
 * 
 */
package org.esco.indicators.domain.beans.result;

import org.apache.log4j.Logger;

/**
 * Class representing the establishment data to present into a result page.
 * 
 * @since  2012/07/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
/**
 * @since  
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentData {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentData.class);
    
    /** Establishment county number */
    private String countyNumber;
    
    /** Establishment name */
    private String establishmentName;
    
    /** Establishment type */
    private String establishmentType;
    
    /** Lycee type */
    private String lyceeType;
    
    /** Establishment UAI */
    private String uai;


    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link EstablishmentData} class.
     * 
     * @param countyNumber
     * 			The county number of the establishment.
     * @param establishmentName
     * 			The name of the establishment.
     * @param establishmentType
     * 			The type of the establishement.
     * @param uai
     * 			The UAI of the establishment.
     */
    public EstablishmentData(String countyNumber, String establishmentName, String establishmentType,
	    String uai) {
	super();
	this.countyNumber = countyNumber;
	this.establishmentName = establishmentName;
	this.establishmentType = establishmentType;
	this.uai = uai;
    }


    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the lycee type.
     * 
     * @return 	
     * 	the lycee type.
     */
    public String getLyceeType() {
        return lyceeType;
    }


    /**
     * Sets the lycee type.
     * 
     * @param lyceeType 
     * 			The lycee type to set.
     */
    public void setLyceeType(String lyceeType) {
        this.lyceeType = lyceeType;
    }


    /**
     * Gets the county number.
     * 
     * @return 
     * 	the county number.
     */
    public String getCountyNumber() {
        return countyNumber;
    }


    /**
     * Gets the establishment name.
     * 
     * @return 
     * 	the establishment name.
     */
    public String getEstablishmentName() {
        return establishmentName;
    }


    /**
     * Gets the establishment type.
     * 
     * @return 
     * 	the establishment type.
     */
    public String getEstablishmentType() {
        return establishmentType;
    }


    /**
     * Gets the UAI.
     * 
     * @return 
     * 	the uai.
     */
    public String getUai() {
        return uai;
    }
    
    
    
    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
