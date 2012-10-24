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
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((activationStart == null) ? 0 : activationStart.hashCode());
	result = prime * result + ((userUid == null) ? 0 : userUid.hashCode());
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
	AccountActivationId other = (AccountActivationId) obj;
	if (activationStart == null) {
	    if (other.activationStart != null)
		return false;
	} else if (!activationStart.equals(other.activationStart))
	    return false;
	if (userUid == null) {
	    if (other.userUid != null)
		return false;
	} else if (!userUid.equals(other.userUid))
	    return false;
	return true;
    }
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
