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
 * Class representing the connections of a particular profile to a portal.<br/>
 * These connections are aggregated by week and only concern non-especial user.<br/>
 * 
 * @since  2012/06/05
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Entity
@NamedQueries({
            @NamedQuery(
        	    name = "WeeklyPortalConnectionStatistic.findStatisticsByProfile",
        	    query = "SELECT wpcs FROM WeeklyPortalConnectionStatistic wpcs"
        	    	+ " WHERE wpcs.establishmentUai = :establishmentUai"
        		+ " AND wpcs.firstWeekDay = :firstWeekDay AND wpcs.userProfile = :userProfile"
        	    ),
            @NamedQuery(
        	    name = "WeeklyPortalConnectionStatistic.findNumVisitorsBelowTreshold",
        	    query = "SELECT SUM(wpcs.numUsers) FROM WeeklyPortalConnectionStatistic wpcs"
        	    	+ " WHERE wpcs.establishmentUai = :establishmentUai"
        		+ " AND wpcs.firstWeekDay = :firstWeekDay "
        	    	+ " AND wpcs.userProfile = :userProfile"
        		+ " AND wpcs.numConnections <= :treshold"
        	    ) 
})
@Table(name = "connexionprofilsemaine")
public class WeeklyPortalConnectionStatistic implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    @Transient
    private static final Logger LOGGER = Logger.getLogger(WeeklyPortalConnectionStatistic.class);

    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = -5803893681792340970L;
    
    /** Generated identifier */
    @Id
    @GeneratedValue
    private long id;
    
    /** Average duration of the profile session on the portal */
    @Column(name = "moyenneconnexionsemaine")
    private Double averageDuration;
    
    /** UAI of the establishment */
    @Column(name = "uai")
    private String establishmentUai;
    
    /** First day of the week */
    @Column(name = "premierjoursemaine", nullable = false)
    private Date firstWeekDay;
    
    /** Number of connections of the profile to the portal */
    @Column(name = "nbconnexionsemaine")
    private Integer numConnections;
    
    /** Number of users */
    @Column(name = "nbpersonnesemaine")
    private Integer numUsers;
    
    /** Profile of the user */
    @Column(name = "nomprofil", nullable = false)
    private String userProfile;


    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link WeeklyPortalConnectionStatistic} class.
     */
    public WeeklyPortalConnectionStatistic() {
	super();
    }

    /**
     * Constructor of the {@link WeeklyPortalConnectionStatistic} class.
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
    public WeeklyPortalConnectionStatistic(String establishmentUai, Date firstWeekDay,
	    String userProfile) {
	super();
	this.establishmentUai = establishmentUai;
	this.firstWeekDay = firstWeekDay;
	this.userProfile = userProfile;
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
        return (numConnections != null ? numConnections : 0);
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


    /** Gets the number of users associated to the statistic.
     * 
     * @return 
     * 	the number of users associated to the statistic.
     */
    public Integer getNumUsers() {
        return (numUsers != null ? numUsers : 0);
    }

    /**
     *  Sets the number of users associated to the statistic.
     *  
     * @param numUsers 
     * 			The number of users associated to the statistic.
     */
    public void setNumUsers(Integer numUsers) {
        this.numUsers = numUsers;
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
	result = prime * result + ((firstWeekDay == null) ? 0 : firstWeekDay.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((numConnections == null) ? 0 : numConnections.hashCode());
	result = prime * result + ((numUsers == null) ? 0 : numUsers.hashCode());
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
	WeeklyPortalConnectionStatistic other = (WeeklyPortalConnectionStatistic) obj;
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
	if (id != other.id)
	    return false;
	if (numConnections == null) {
	    if (other.numConnections != null)
		return false;
	} else if (!numConnections.equals(other.numConnections))
	    return false;
	if (numUsers == null) {
	    if (other.numUsers != null)
		return false;
	} else if (!numUsers.equals(other.numUsers))
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
