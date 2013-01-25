/**
 * 
 */
package org.esco.indicators.domain.beans.profile;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

/**
 * Class representing the link between a user and a profile into an establishment.<br/>
 * A link is defined on a period, i.e., it contains a start date and a end date.<br/>
 * <ul>
 * <li>The start date represents the creation date of the link between the user and the profile into an
 * establishment.</li>
 * <li>The end date (which can be <code>null</code>) represents the destruction date of the link between the
 * user and the profile into an establishment.</li>
 * </ul>
 * 
 * @since 2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Entity 
@NamedQueries({
    @NamedQuery(
	    name = "ProfileLink.findProfileLinksBetween",
	    query = "SELECT pl FROM ProfileLink pl"
	    	+ " WHERE pl.establishmentUai = :establishmentUai"
		+ " AND pl.userProfile = :userProfile"
		+ " AND pl.linkStart <= :linkStart"
		+ " AND (pl.linkEnd >= :linkEnd OR pl.linkEnd IS NULL)"
	    ),
    @NamedQuery(
	    name = "ProfileLink.findTotalNumLinkedAccounts",
	    query = "SELECT COUNT( pl.userUid ) FROM ProfileLink pl"
		    	+ " WHERE pl.establishmentUai IN ( :establishmentUaiList )"
			+ " AND pl.linkStart <= :linkStart"
			+ " AND (pl.linkEnd >= :linkEnd OR pl.linkEnd IS NULL)"
	    ),
    @NamedQuery(
	    name = "ProfileLink.findTotalNumLinkedAccountsForProfile",
	    query = "SELECT COUNT( pl.userUid ) FROM ProfileLink pl"
		    	+ " WHERE pl.establishmentUai IN ( :establishmentUaiList )"
			+ " AND pl.linkStart <= :linkStart"
			+ " AND (pl.linkEnd >= :linkEnd OR pl.linkEnd IS NULL)"
			+ " AND (pl.userProfile IN ( :userProfileList ) )"
	    )
})
@Table(name = "acommeprofil")
@IdClass(value = ProfileLinkId.class)
public class ProfileLink implements Serializable {
    // ---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    @Transient
    private static final Logger LOGGER = Logger.getLogger(ProfileLink.class);
    
    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = 6591234456693894290L;
    
    /** UAI of the establishment */
    @Id
    @Column(name = "uai", nullable = false, insertable = true)
    private String establishmentUai;
    
    /** Date of creation of the link */
    @Id
    @Column(name = "datedebutprofil", nullable = false)
    private Date linkStart;
    
    /** Date of destruction of the link */
    @Column(name = "datefinprofil")
    private Date linkEnd;

    /** Profile of the user */
    @Id
    @Column(name = "nomprofil", nullable = false)
    private String userProfile;
    
    /** UID of the user linked to the profile */
    @Id
    @Column(name = "uid", nullable = false)
    private String userUid;

    // -------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link ProfileLink} class. 
     */
    public ProfileLink() {
	super();
    }

    /**
     * Constructor of the {@link ProfileLink} class.
     * 
     * @param establishmentUai
     * @param linkStart
     * @param userUid
     */
    public ProfileLink(String establishmentUai, Date linkStart, String userUid) {
	super();
	this.establishmentUai = establishmentUai;
	this.linkStart = linkStart;
	this.userUid = userUid;
    }

    // --------------------------------------------------------------------------- GETTERS / SETTERS

    /**
     * Gets the UAI of the establishment associated to the link.
     * 
     * @return
     * 	the UAI of the establishment associated to the link.
     */
    public String getEstablishmentUai() {
        return establishmentUai;
    }

    /**
     * Sets the UAI of the establishment associated to the link.
     * 
     * @param establishmentUai 
     * 			The UAI of the establishment, associated to the link, to set.
     */
    public void setEstablishmentUai(String establishmentUai) {
        this.establishmentUai = establishmentUai;
    }

    /**
     * Gets the date of creation of the link.
     * 
     * @return 
     * 	 the date of creation of the link.
     */
    public Date getLinkStart() {
        return linkStart;
    }

    /**
     * Sets the date of creation of the link.
     * 
     * @param linkStart 
     * 			The date of creation, of the link, to set.
     */
    public void setLinkStart(Date linkStart) {
        this.linkStart = linkStart;
    }

    /**
     * Gets the date of destruction of the link.
     * 
     * @return 
     * 	the date of destruction of the link.
     */
    public Date getLinkEnd() {
        return linkEnd;
    }

    /**
     * Sets the date of destruction of the link.
     * 
     * @param linkEnd 
     * 			The date of destruction, of the link, to set.
     */
    public void setLinkEnd(Date linkEnd) {
        this.linkEnd = linkEnd;
    }

    /**
     * Gets the user profile of the link.
     * 
     * @return 
     * 	the user profile of the link.
     */
    public String getUserProfile() {
        return userProfile;
    }

    /**
     * Sets the user profile of the link.
     * 
     * @param userProfile 
     * 			The user profile, of the link, to set
     */
    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    /**
     * Gets the UID of the user linked to the profile.
     * 
     * @return
     * 	the UID of the user linked to the profile.
     */
    public String getUserUid() {
        return userUid;
    }

    /**
     * Sets the UID of the user linked to the profile.
     * 
     * @param userUid 
     * 			The UID of the user, linked to the profile, to set.
     */
    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }
    

    // ------------------------------------------------------------------------------ PUBLIC METHODS

    // ----------------------------------------------------------------------------- PRIVATE METHODS

    // ------------------------------------------------------------------------------ STATIC METHODS
}
