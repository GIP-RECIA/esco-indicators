/**
 * 
 */
package org.esco.indicators.domain.beans.statistic;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
 * Class representing the composite key of a {@link MonthlyPortalConnectionStatistic}.
 * 
 * @since  2012/10/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Embeddable
@SuppressWarnings("unused")
public class MonthlyPortalConnectionStatisticId implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = 6076810371225109299L;

    /** UAI of the establishment */
    @Column(name = "uai")
    private String establishmentUai;
    
    /** First day of the month */
    @Column(name = "mois", nullable = false)
    private Date firstMonthDay;
    
    /** Number of connections of the profile on the portal */
    @Column(name = "nbconnexion")
    private Integer numConnections;
    
    /** Profile of the user */
    @Column(name = "nomprofil", nullable = false)
    private String userProfile;

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
	result = prime * result + ((establishmentUai == null) ? 0 : establishmentUai.hashCode());
	result = prime * result + ((firstMonthDay == null) ? 0 : firstMonthDay.hashCode());
	result = prime * result + ((numConnections == null) ? 0 : numConnections.hashCode());
	result = prime * result + ((userProfile == null) ? 0 : userProfile.hashCode());
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
	MonthlyPortalConnectionStatisticId other = (MonthlyPortalConnectionStatisticId) obj;
	if (establishmentUai == null) {
	    if (other.establishmentUai != null)
		return false;
	} else if (!establishmentUai.equals(other.establishmentUai))
	    return false;
	if (firstMonthDay == null) {
	    if (other.firstMonthDay != null)
		return false;
	} else if (!firstMonthDay.equals(other.firstMonthDay))
	    return false;
	if (numConnections == null) {
	    if (other.numConnections != null)
		return false;
	} else if (!numConnections.equals(other.numConnections))
	    return false;
	if (userProfile == null) {
	    if (other.userProfile != null)
		return false;
	} else if (!userProfile.equals(other.userProfile))
	    return false;
	return true;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
