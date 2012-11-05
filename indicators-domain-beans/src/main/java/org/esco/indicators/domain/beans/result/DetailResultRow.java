/**
 * 
 */
package org.esco.indicators.domain.beans.result;

import org.apache.log4j.Logger;

/**
 * Class representing a result row for displaying the statistic for a particular profile of a particular establishment.
 * 
 * @since  2012/08/01
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class DetailResultRow extends ExtendedResultRow {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(DetailResultRow.class);

    /** User profile concerned by the row */
    private String userProfile;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of {@link DetailResultRow} based on a {@link BasicResultRow} and a user profile.
     * 
     * @param basicResultRow
     * 			The basic result row containing the statistic and establishment data.
     * @param userProfile
     * 			The user profile associated to the statistic data.
     */
    public DetailResultRow(BasicResultRow basicResultRow, String userProfile) {
	super(basicResultRow);
	this.userProfile = userProfile;
	LOGGER.debug("Call of the constructor : DetailResultRow(BasicResultRow, String)");
    }
    
    /**
     * Constructor of {@link DetailResultRow} based on a {@link ExtendedResultRow} and a user profile.
     * 
     * @param extendedResultRow
     * 			The extended result row containing the statistic and establishment data.
     * @param userProfile
     * 			The user profile associated to the statistic data.
     */
    public DetailResultRow(ExtendedResultRow extendedResultRow, String userProfile) {
	super(extendedResultRow);
	this.userProfile = userProfile;
	LOGGER.debug("Call of the constructor : DetailResultRow(ExtendedResultRow, String)");
    }
    
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the user profile associated to the row.
     * 
     * @return 
     * 	the user profile.
     */
    public String getUserProfile() {
        return userProfile;
    }

    /**
     * Sets the user profile associated to the row.
     * 
     * @param userProfile 
     * 			The user profile to set.
     */
    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
