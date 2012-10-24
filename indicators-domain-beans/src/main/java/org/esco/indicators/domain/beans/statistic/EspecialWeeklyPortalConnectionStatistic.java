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
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

/**
 * Class representing the connections of an especial user to a portal.<br/>
 * These connections are aggregated by week.<br/>
 * An especial user is a user having, for instance, multiple profiles.
 * 
 * @since  2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Entity
@NamedQueries({    
    @NamedQuery(
	    name = "EspecialWeeklyPortalConnectionStatistic.findNumConnections",
	    query = "SELECT SUM(ewpcs.numConnections) FROM EspecialWeeklyPortalConnectionStatistic ewpcs"
	    	+ " WHERE ewpcs.establishmentUai = :establishmentUai"
		+ " AND ewpcs.firstWeekDay = :firstWeekDay"
	    ),
    @NamedQuery(
	    name = "EspecialWeeklyPortalConnectionStatistic.findNumConnectionsByProfile",
	    query = "SELECT SUM(ewpcs.numConnections) FROM EspecialWeeklyPortalConnectionStatistic ewpcs"
	    	+ " WHERE ewpcs.establishmentUai = :establishmentUai"
		+ " AND ewpcs.firstWeekDay = :firstWeekDay AND ewpcs.userProfile = :userProfile"
	    ),
    @NamedQuery(
	    name = "EspecialWeeklyPortalConnectionStatistic.findNumVisitorsBelowTresholdByProfile",
	    query = "SELECT COUNT( DISTINCT ewpcs.userUid ) FROM EspecialWeeklyPortalConnectionStatistic ewpcs"
	    	+ " WHERE ewpcs.establishmentUai IN ( :establishmentUaiList )"
		+ " AND ewpcs.firstWeekDay = :firstWeekDay " 
	    	+ " AND ewpcs.userProfile = :userProfile"
		+ " AND ewpcs.numConnections <= :treshold"
	    ),    
    @NamedQuery(
	    name = "EspecialWeeklyPortalConnectionStatistic.findNumVisitorsBelowTreshold",
	    query = "SELECT COUNT( DISTINCT ewpcs.userUid ) FROM EspecialWeeklyPortalConnectionStatistic ewpcs"
	    	+ " WHERE ewpcs.establishmentUai IN ( :establishmentUaiList )"
		+ " AND ewpcs.firstWeekDay = :firstWeekDay " 
		+ " AND ewpcs.numConnections <= :treshold"
	    ),    
    @NamedQuery(
	    name = "EspecialWeeklyPortalConnectionStatistic.findNumVisitorsAboveTreshold",
	    query = "SELECT COUNT( DISTINCT ewpcs.userUid ) FROM EspecialWeeklyPortalConnectionStatistic ewpcs"
		+ " WHERE ewpcs.establishmentUai IN ( :establishmentUaiList )"
		+ " AND ewpcs.firstWeekDay = :firstWeekDay " 
		+ " AND ewpcs.numConnections > :treshold"
	    ),
    @NamedQuery(
	name = "EspecialWeeklyPortalConnectionStatistic.findNumVisitorsAboveTresholdByProfile",
	query = "SELECT COUNT( DISTINCT ewpcs.userUid ) FROM EspecialWeeklyPortalConnectionStatistic ewpcs"
		+ " WHERE ewpcs.establishmentUai IN ( :establishmentUaiList )"
		+ " AND ewpcs.firstWeekDay = :firstWeekDay " 
		+ " AND ewpcs.userProfile = :userProfile"
		+ " AND ewpcs.numConnections > :treshold"
	    )
})
@Table(name = "seconnectesemaine")
@IdClass(value = EspecialWeeklyPortalConnectionStatisticId.class)
public class EspecialWeeklyPortalConnectionStatistic implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    @Transient
    private static final Logger LOGGER = Logger.getLogger(EspecialWeeklyPortalConnectionStatistic.class);

    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = -1188441169916035945L;
    
    /** Average duration of the user session on the portal */
    @Column(name = "moyennesemaine")
    private Double averageDuration;
    
    /** UAI of the establishment */
    @Id
    @Column(name = "uai")
    private String establishmentUai;
    
    /** First day of the week */
    @Id
    @Column(name = "premierjoursemaine", nullable = false)
    private Date firstWeekDay;
    
    /** Number of connections of the user to the portal */
    @Column(name = "nbconnexionsemaine")
    private Integer numConnections;
    
    /** Profile of the user */
    @Id
    @Column(name = "nomprofil", nullable = false)
    private String userProfile;

    /** Uid of the user */
    @Id
    @Column(name = "uid", nullable = false)
    private String userUid;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link EspecialWeeklyPortalConnectionStatistic} class.
     */
    public EspecialWeeklyPortalConnectionStatistic() {
	super();
    }

    /**
     * Constructor of the {@link EspecialWeeklyPortalConnectionStatistic} class.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic.
     * @param firstWeekDay
     * 			The first day of the week concerned by the statistic.
     * @param userProfile
     * 			The user profile concerned by the statistic.
     * @param userUid
     * 			The user uid concerned by the statistic.
     */
    public EspecialWeeklyPortalConnectionStatistic(String establishmentUai, Date firstWeekDay,
	    String userProfile, String userUid) {
	super();
	this.establishmentUai = establishmentUai;
	this.firstWeekDay = firstWeekDay;
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
     * Gets the first day of the week concerned by the statistic.
     * 
     * @return 
     * 	the first day of the week of the statistic.
     */
    public Date getFirstWeekDay() {
        return firstWeekDay;
    }

    /**
     * Sets the first day of the week concerned by the statistic.
     * 
     * @param firstWeekDay 
     * 			The first day of the week to set.
     */
    public void setFirstWeekDay(Date firstWeekDay) {
        this.firstWeekDay = firstWeekDay;
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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((averageDuration == null) ? 0 : averageDuration.hashCode());
	result = prime * result + ((establishmentUai == null) ? 0 : establishmentUai.hashCode());
	result = prime * result + ((firstWeekDay == null) ? 0 : firstWeekDay.hashCode());
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
	EspecialWeeklyPortalConnectionStatistic other = (EspecialWeeklyPortalConnectionStatistic) obj;
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
	if (firstWeekDay == null) {
	    if (other.firstWeekDay != null)
		return false;
	} else if (!firstWeekDay.equals(other.firstWeekDay))
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
