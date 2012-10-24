/**
 * 
 */
package org.esco.indicators.domain.beans.account;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.statistic.ServiceConnectionStatistic;

/**
 * Class representing the composite key of a {@link AccountActivation}.
 * 
 * @since  2012/10/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Embeddable
@SuppressWarnings("unused")
public class AccountActivationId implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = -4135003447341539207L;
    
    /** Beggining date of the account activation */
    @Column(name = "datedebutactivation", nullable = false)
    private Date activationStart;
    
    /** UID of the user associated to the account */
    @Column(name = "uid", nullable = false)
    private String userUid;

    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
