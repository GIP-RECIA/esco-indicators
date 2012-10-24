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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.annotations.NaturalId;

/**
 * Class representing the composite key of a {@link ServiceConnectionStatistic}.
 * 
 * @since  2012/10/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Embeddable
@SuppressWarnings("unused")
public class ServiceConnectionStatisticId implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = 4531627328487554126L;
    
    /** Day of the statistic */
    @Column(name = "jour", nullable = false)
    private Date day;
    
    /** UAI of the establishment */
    @Column(name = "uai", nullable = false)
    private String establishmentUai;
    
    /** Name of the service */
    @Column(name = "nomservice", nullable = false)
    private String serviceName;
    
    /** Profile of the user */
    @Column(name = "nomprofil", nullable = false)
    private String userProfile;

    /** Uid of the user */
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
	result = prime * result + ((day == null) ? 0 : day.hashCode());
	result = prime * result + ((establishmentUai == null) ? 0 : establishmentUai.hashCode());
	result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
	result = prime * result + ((userProfile == null) ? 0 : userProfile.hashCode());
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
	ServiceConnectionStatisticId other = (ServiceConnectionStatisticId) obj;
	if (day == null) {
	    if (other.day != null)
		return false;
	} else if (!day.equals(other.day))
	    return false;
	if (establishmentUai == null) {
	    if (other.establishmentUai != null)
		return false;
	} else if (!establishmentUai.equals(other.establishmentUai))
	    return false;
	if (serviceName == null) {
	    if (other.serviceName != null)
		return false;
	} else if (!serviceName.equals(other.serviceName))
	    return false;
	if (userProfile == null) {
	    if (other.userProfile != null)
		return false;
	} else if (!userProfile.equals(other.userProfile))
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
