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
 * Class representing the connections of a particular profile to a portal.<br/>
 * These connections are aggregated by  month and only concern non-especial user.<br/>
 * 
 * @since  2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Entity
@NamedQueries({    
    @NamedQuery(
	    name = "MonthlyPortalConnectionStatistic.findConnectionsAverageDurationByProfiles",
	    query = "SELECT SUM(mpcs.numUsers * mpcs.numConnections * mpcs.averageDuration) / SUM(mpcs.numUsers * mpcs.numConnections) FROM MonthlyPortalConnectionStatistic mpcs"
	    	+ " WHERE mpcs.establishmentUai =  :establishmentUai"
		+ " AND mpcs.firstMonthDay = :firstMonthDay " 
	    	+ " AND mpcs.userProfile IN ( :userProfileList )"
	    ),
    @NamedQuery(
	    name = "MonthlyPortalConnectionStatistic.findStatisticsByProfiles",
	    query = "SELECT mpcs FROM MonthlyPortalConnectionStatistic mpcs"
	    	+ " WHERE mpcs.establishmentUai IN ( :establishmentUaiList )"
		+ " AND mpcs.firstMonthDay = :firstMonthDay AND mpcs.userProfile IN ( :userProfileList )"
	    ),
    @NamedQuery(
	    name = "MonthlyPortalConnectionStatistic.findNumVisitorsBelowTreshold",
	    query = "SELECT SUM(mpcs.numUsers) FROM MonthlyPortalConnectionStatistic mpcs"
	    	+ " WHERE mpcs.establishmentUai IN ( :establishmentUaiList )"
		+ " AND mpcs.firstMonthDay = :firstMonthDay "
		+ " AND mpcs.numConnections <= :treshold"
	    ),
    @NamedQuery(
	    name = "MonthlyPortalConnectionStatistic.findNumVisitorsBelowTresholdByProfiles",
	    query = "SELECT SUM(mpcs.numUsers) FROM MonthlyPortalConnectionStatistic mpcs"
	    	+ " WHERE mpcs.establishmentUai IN ( :establishmentUaiList )"
		+ " AND mpcs.firstMonthDay = :firstMonthDay "
	    	+ " AND mpcs.userProfile IN ( :userProfileList )"
		+ " AND mpcs.numConnections <= :treshold"
	    ),
    @NamedQuery(
	    name = "MonthlyPortalConnectionStatistic.findNumVisitorsAboveTreshold",
	    query = "SELECT SUM(mpcs.numUsers) FROM MonthlyPortalConnectionStatistic mpcs"
		+ " WHERE mpcs.establishmentUai IN ( :establishmentUaiList )"
		+ " AND mpcs.firstMonthDay = :firstMonthDay "
		+ " AND mpcs.numConnections > :treshold"
	    ),
    @NamedQuery(
	name = "MonthlyPortalConnectionStatistic.findNumVisitorsAboveTresholdByProfiles",
	query = "SELECT SUM(mpcs.numUsers) FROM MonthlyPortalConnectionStatistic mpcs"
		+ " WHERE mpcs.establishmentUai IN ( :establishmentUaiList )"
		+ " AND mpcs.firstMonthDay = :firstMonthDay "
		+ " AND mpcs.userProfile IN ( :userProfileList )"
		+ " AND mpcs.numConnections > :treshold"
	    ) 
})
@Table(name = "connexionprofilmois")
@IdClass(value = MonthlyPortalConnectionStatisticId.class)
public class MonthlyPortalConnectionStatistic implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    @Transient
    private static final Logger LOGGER = Logger.getLogger(MonthlyPortalConnectionStatistic.class);

    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = -6504311334614725517L;
    
    /** Average duration of the p session on the portal */
    @Column(name = "moyenneconnexion")
    private Double averageDuration;
    
    /** UAI of the establishment */
    @Id
    @Column(name = "uai")
    private String establishmentUai;
    
    /** First day of the month */
    @Id
    @Column(name = "mois", nullable = false)
    private Date firstMonthDay;
    
    /** Number of connections of the profile on the portal */
    @Id
    @Column(name = "nbconnexion")
    private Integer numConnections;
    
        /** Number of users */
    @Column(name = "nbpersonne")
    private Integer numUsers;
    
    /** Profile of the user */
    @Id
    @Column(name = "nomprofil", nullable = false)
    private String userProfile;

    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link MonthlyPortalConnectionStatistic} class.
     */
    public MonthlyPortalConnectionStatistic() {
	super();
    }

    /**
     * Constructor of the {@link MonthlyPortalConnectionStatistic} class.
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
    public MonthlyPortalConnectionStatistic(String establishmentUai, Date firstMonthDay,
	    String userProfile, String userUid) {
	super();
	this.establishmentUai = establishmentUai;
	this.firstMonthDay = firstMonthDay;
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
	result = prime * result + ((firstMonthDay == null) ? 0 : firstMonthDay.hashCode());
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
	MonthlyPortalConnectionStatistic other = (MonthlyPortalConnectionStatistic) obj;
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
