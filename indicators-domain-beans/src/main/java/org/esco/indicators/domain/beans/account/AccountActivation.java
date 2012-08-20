/**
 * 
 */
package org.esco.indicators.domain.beans.account;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

/**
 * Class representing the account activations of the users.
 * 
 * @since  2012/06/06
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Entity
@NamedQueries({    
    @NamedQuery(
	    name = "AccountActivation.findNumActivatedAccounts",
	    query = "SELECT COUNT( aa.userUid ) FROM AccountActivation aa, ProfileLink pl"
	    	+ " WHERE aa.activationStart <= :activationStart"
		+ " AND ( aa.activationEnd >= :activationEnd OR aa.activationEnd IS NULL)"
	    	+ " AND aa.userUid = pl.userUid"
		+ " AND pl.establishmentUai IN ( :establishmentUaiList )"
	    	+ " AND pl.linkStart <= :activationStart"
		+ " AND ( pl.linkEnd >= :activationEnd OR pl.linkEnd IS NULL)"
	    ),
    @NamedQuery(
	    name = "AccountActivation.findNumActivatedAccountsForProfiles",
	    query = "SELECT COUNT( aa.userUid ) FROM AccountActivation aa, ProfileLink pl"
	    	+ " WHERE aa.activationStart <= :activationStart"
		+ " AND ( aa.activationEnd >= :activationEnd OR aa.activationEnd IS NULL)"
	    	+ " AND aa.userUid = pl.userUid"
		+ " AND pl.establishmentUai IN ( :establishmentUaiList )"
	    	+ " AND pl.userProfile IN ( :userProfileList )"
	    	+ " AND pl.linkStart <= :activationStart"
		+ " AND ( pl.linkEnd >= :activationEnd OR pl.linkEnd IS NULL)"
	    )
})
@Table(name = "estactivee")
public class AccountActivation implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    @Transient
    private static final Logger LOGGER = Logger.getLogger(AccountActivation.class);
    
    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = 179471742392494091L;
    
    /** Generated identifier */
    @Id
    @GeneratedValue
    private Integer id;
    
    /** Beggining date of the account activation */
    @Column(name = "datedebutactivation", nullable = false)
    private Date activationStart;
    
    /** End date of the account activation */
    @Column(name = "datefinactivation")
    private Date activationEnd;
    
    /** UID of the user associated to the account */
    @Column(name = "uid", nullable = false)
    private String userUid;

    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link AccountActivation} class.
     * 
     */
    public AccountActivation() {
	super();
    }


    /**
     * Constructor of the {@link AccountActivation} class.
     * 
     * @param activationStart
     * 			The account activation start date.
     * @param userUid
     * 			The UID of the user associated to the account.
     */
    public AccountActivation(Date activationStart, String userUid) {
	super();
	this.activationStart = activationStart;
	this.userUid = userUid;
    }


    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the start date of account activation.
     * 
     * @return 
     * 	the start date of account activation.
     */
    public Date getActivationStart() {
        return activationStart;
    }


    /**
     *  Sets the start date of account activation.
     *  
     * @param activationStart 
     * 			The  start date to set.
     */
    public void setActivationStart(Date activationStart) {
        this.activationStart = activationStart;
    }


    /**
     * Gets the end date of activation account.
     * 
     * @return 
     * 	the end date of activation account.
     */
    public Date getActivationEnd() {
        return activationEnd;
    }


    /**
     * Sets the end date of activation account.
     * 
     * @param activationEnd 
     * 			The end date of activation account to set.
     */
    public void setActivationEnd(Date activationEnd) {
        this.activationEnd = activationEnd;
    }


    /**
     * Gets the UID user associated to the account.
     * 
     * @return
     * 	the UID user associated to the account.
     */
    public String getUserUid() {
        return userUid;
    }


    /**
     * Sets the UID user associated to the account. 
     * 
     * @param userUid 
     * 			The the UID user associated to the account to set.
     */
    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
