/**
 * 
 */
package org.esco.indicators.domain.beans.statistic;

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
 * Class representing the connections of a user on a portal.<br/>
 * These connections are aggregated by month.<br/>
 * An especial user is a user having, for instance, multiple profiles.
 * 
 * @since  2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Entity
@NamedQueries({
    @NamedQuery(
	    name = "EspecialMonthlyPortalConnectionStatistic.findNumConnectionsByProfile",
	    query = "SELECT SUM(empcs.numConnections) FROM EspecialMonthlyPortalConnectionStatistic empcs"
	    	+ " WHERE empcs.establishmentUai = :establishmentUai"
		+ " AND empcs.firstMonthDay = :firstMonthDay AND empcs.userProfile = :userProfile"
    	    ),
    @NamedQuery(
	    name = "EspecialMonthlyPortalConnectionStatistic.findNumVisitorsBelowTreshold",
	    query = "SELECT COUNT( DISTINCT empcs.userUid ) FROM EspecialMonthlyPortalConnectionStatistic empcs"
		+ " WHERE empcs.establishmentUai = :establishmentUai"
		+ " AND empcs.firstMonthDay = :firstMonthDay" 
		+ " AND empcs.userProfile = :userProfile"
		+ " AND empcs.numConnections <= :treshold"
	    ),
    @NamedQuery(
	name = "EspecialMonthlyPortalConnectionStatistic.findNumVisitorsAboveTreshold",
	query = "SELECT COUNT( DISTINCT empcs.userUid ) FROM EspecialMonthlyPortalConnectionStatistic empcs"
		+ " WHERE empcs.establishmentUai = :establishmentUai"
		+ " AND empcs.firstMonthDay = :firstMonthDay" 
		+ " AND empcs.userProfile = :userProfile"
		+ " AND empcs.numConnections > :treshold"
	    )
})
@Table(name = "seconnectemois")
public class EspecialMonthlyPortalConnectionStatistic implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    @Transient
    private static final Logger LOGGER = Logger.getLogger(EspecialMonthlyPortalConnectionStatistic.class);

    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = 578843231991338150L;
    
    /** Generated identifier */
    @Id
    @GeneratedValue
    private long id;
    
    /** Average duration of the user session on the portal */
    @Column(name = "moyennemois")
    private Double averageDuration;
    
    /** UAI of the establishment */
    @Column(name = "uai")
    private String establishmentUai;
    
    /** First day of the month */
    @Column(name = "mois", nullable = false)
    private Date firstMonthDay;
    
    /** Number of connections of the user on the portal */
    @Column(name = "nbconnexionmois")
    private Integer numConnections;
    
    /** Profile of the user */
    @Column(name = "nomprofil", nullable = false)
    private String userProfile;

    /** Uid of the user */
    @Column(name = "uid", nullable = false)
    private String userUid;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link EspecialMonthlyPortalConnectionStatistic} class.
     */
    public EspecialMonthlyPortalConnectionStatistic() {
	super();
    }

    /**
     * Constructor of the {@link EspecialMonthlyPortalConnectionStatistic} class.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic.
     * @param firstMonthDay
     * 			The first day of the week concerned by the statistic.
     * @param userProfile
     * 			The user profile concerned by the statistic.
     * @param userUid
     * 			The user uid concerned by the statistic.
     */
    public EspecialMonthlyPortalConnectionStatistic(String establishmentUai, Date firstMonthDay,
	    String userProfile, String userUid) {
	super();
	this.establishmentUai = establishmentUai;
	this.firstMonthDay = firstMonthDay;
	this.userProfile = userProfile;
	this.userUid = userUid;
    }

    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the average duration of the user sessions on the portal.
     * 
     * @return 
     * 	the average duration of the user sessions on the portal.
     */
    public Double getAverageDuration() {
        return averageDuration;
    }

    /**
     * Sets the average duration of the user sessions on the portal.
     * 
     * @param averageDuration 
     * 			The average duration to set.
     */
    public void setAverageDuration(Double averageDuration) {
        this.averageDuration = averageDuration;
    }

    /**
     * Gets the UAI of the establishment associated to the statistic.
     * 
     * @return 
     * 	 the UAI of the establishment associated to the statistic.
     */
    public String getEstablishmentUai() {
        return establishmentUai;
    }

    /**
     * Sets the UAI of the establishment associated to the statistic.
     *  
     * @param establishmentUai 
     * 			The establishment UAI to set.
     */
    public void setEstablishmentUai(String establishmentUai) {
        this.establishmentUai = establishmentUai;
    }

    /**
     * Gets the first day of the month concerned by the statistic.
     * 
     * @return 
     * 	the first day of the month of the statistic.
     */
    public Date getFirstMonthDay() {
        return firstMonthDay;
    }

    /**
     * Sets the first day of the month concerned by the statistic.
     * 
     * @param firstMonthDay 
     * 			The first day of the week to set.
     */
    public void setFirstMonthDay(Date firstMonthDay) {
        this.firstMonthDay = firstMonthDay;
    }

    /**
     * Gets the number of connections of the user to the portal.
     * 
     * @return 
     * 	the number of connections  of the user to the portal.
     */
    public Integer getNumConnections() {
        return numConnections;
    }

    /**
     * Sets the number of connections of the user to the portal.
     * 
     * @param numConnections 
     * 			The number of connections to set.
     */
    public void setNumConnections(Integer numConnections) {
        this.numConnections = numConnections;
    }

    /**
     * Gets the profile of the user concerned by the statistic.
     * 
     * @return 
     * 	the profile of the user concerned by the statistic.
     */
    public String getUserProfile() {
        return userProfile;
    }

    /**
     * Sets the profile of the user concerned by the statistic.
     * 
     * @param userProfile 
     * 			the profile of the to set.
     */
    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    /**
     * Gets the UID of the user concerned by the statistic.
     * 
     * @return 
     * 	the UID of the user concerned by the statistic.
     */
    public String getUserUid() {
        return userUid;
    }

    /**
     * Sets the UID of the user concerned by the statistic.
     * 
     * @param userUid 
     * 			The UID of the user to set.
     */
    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((averageDuration == null) ? 0 : averageDuration.hashCode());
	result = prime * result + ((establishmentUai == null) ? 0 : establishmentUai.hashCode());
	result = prime * result + ((firstMonthDay == null) ? 0 : firstMonthDay.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((numConnections == null) ? 0 : numConnections.hashCode());
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
	EspecialMonthlyPortalConnectionStatistic other = (EspecialMonthlyPortalConnectionStatistic) obj;
	if (averageDuration == null) {
	    if (other.averageDuration != null)
		return false;
	} else if (!averageDuration.equals(other.averageDuration))
	    return false;
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
	if (id != other.id)
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
