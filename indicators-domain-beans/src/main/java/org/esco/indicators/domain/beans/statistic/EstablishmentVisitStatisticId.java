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

/**
 * Class representing the composite key of a {@link EstablishmentVisitStatistic}.
 * 
 * @since  2012/10/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Embeddable
@SuppressWarnings("unused")
public class EstablishmentVisitStatisticId implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = -8089968836757908571L;
    
    /** Day of the statistic */
    @Column(name = "jour", nullable = false)
    private Date day;
    
    /** Type of the establishment */
    @Column(name = "typeetab", nullable = false)
    private String establishmentType;
    
    /** UAI of the establishment */
    @Column(name = "uai", nullable = false)
    private String establishmentUai;

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
	result = prime * result + ((establishmentType == null) ? 0 : establishmentType.hashCode());
	result = prime * result + ((establishmentUai == null) ? 0 : establishmentUai.hashCode());
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
	EstablishmentVisitStatisticId other = (EstablishmentVisitStatisticId) obj;
	if (day == null) {
	    if (other.day != null)
		return false;
	} else if (!day.equals(other.day))
	    return false;
	if (establishmentType == null) {
	    if (other.establishmentType != null)
		return false;
	} else if (!establishmentType.equals(other.establishmentType))
	    return false;
	if (establishmentUai == null) {
	    if (other.establishmentUai != null)
		return false;
	} else if (!establishmentUai.equals(other.establishmentUai))
	    return false;
	return true;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
