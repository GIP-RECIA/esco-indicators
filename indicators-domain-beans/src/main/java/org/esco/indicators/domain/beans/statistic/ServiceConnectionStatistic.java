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
import org.hibernate.annotations.NaturalId;

/**
 * Class representing statistics on wantedServices usage of a user.<br/>
 * 
 * @since 2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Entity
@NamedQueries({
    @NamedQuery(
	    name = "ServiceConnectionStatistic.Daily.findNumConnectionsByProfile",
	    query = "SELECT SUM(scs.numConnections) FROM ServiceConnectionStatistic scs"
	    		+ " WHERE scs.day = :day AND scs.establishmentUai = :establishmentUai"
	    		+" AND scs.serviceName IN ( :serviceNameList ) AND scs.userProfile = :userProfile"
	    ),
   @NamedQuery(
	    name = "ServiceConnectionStatistic.findNumVisitorsAboveTreshold",
	    query = "SELECT COUNT( scs.userUid ) FROM ServiceConnectionStatistic scs "
	    			+ "WHERE scs.userUid IN "
        	    			+"( SELECT scsAbove.userUid FROM ServiceConnectionStatistic scsAbove"
        		    		+ " WHERE scsAbove.establishmentUai IN ( :establishmentUaiList )"
        		    		+ " AND scsAbove.serviceName IN ( :serviceNameList ) AND scsAbove.userProfile = :userProfile"
        		    		+ " AND scsAbove.day BETWEEN :startDate AND :endDate"
        		    		+ " GROUP BY scsAbove.userUid HAVING SUM( scsAbove.numConnections ) > :treshold )"
	    ),
    @NamedQuery(
	    name = "ServiceConnectionStatistic.findNumVisitorsBelowTreshold",
		    query = "SELECT COUNT( scs.userUid ) FROM ServiceConnectionStatistic scs "
	    			+ "WHERE scs.userUid IN "
    	    			+"( SELECT scsBelow.userUid FROM ServiceConnectionStatistic scsBelow"
    		    		+ " WHERE scsBelow.establishmentUai IN ( :establishmentUaiList )"
    		    		+ " AND scsBelow.serviceName IN ( :serviceNameList ) AND scsBelow.userProfile = :userProfile"
    		    		+ " AND scsBelow.day BETWEEN :startDate AND :endDate"
    		    		+ " GROUP BY scsBelow.userUid HAVING SUM( scsBelow.numConnections ) <= :treshold )"
	    ),
  @NamedQuery(
	    name = "ServiceConnectionStatistic.findNumVisits",
	    query = "SELECT SUM(scs.numConnections) FROM ServiceConnectionStatistic scs"
	    		+ " WHERE scs.establishmentUai IN ( :establishmentUaiList )"
	    		+ " AND scs.serviceName IN ( :serviceNameList ) AND scs.userProfile = :userProfile"
	    		+ " AND scs.day BETWEEN :startDate AND :endDate"
	    )
})
@Table(name = "connexionservicejour")
@IdClass(value = ServiceConnectionStatisticId.class)
public class ServiceConnectionStatistic implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    @Transient
    private static final Logger LOGGER = Logger.getLogger(ServiceConnectionStatistic.class);
    
    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = -4760655487182103296L;
    
    /** Day of the statistic */
    @Id
    @Column(name = "jour", nullable = false)
    private Date day;
    
    /** UAI of the establishment */
    @Id
    @Column(name = "uai", nullable = false)
    private String establishmentUai;
    
    /** Number of connections of the user to the service */
    @Column(name = "nbconnexionservice")
    private Integer numConnections;

    /** Name of the service */
    @Id
    @Column(name = "nomservice", nullable = false)
    private String serviceName;
    
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
     * Default constructor of the {@link ServiceConnectionStatistic} class.
     */
    public ServiceConnectionStatistic() {
	super();
    }

    /**
     * Constructor of the {@link ServiceConnectionStatistic} class.
     * 
     * @param day
     * 			The day of the statistic.
     * @param establishmentUai
     * 			The establishment concerned by the statistic.
     * @param serviceName
     * 			The name of the service concerned by the statistic.
     * @param userProfile
     * 			The profile of the user concerned by the statistic.
     * @param userUid
     * 			The uid of the user concerned by the statistic.
     */
    public ServiceConnectionStatistic(Date day, String establishmentUai, String serviceName, String userProfile,
	    String userUid) {
	super();
	this.day = day;
	this.establishmentUai = establishmentUai;
	this.serviceName = serviceName;
	this.userProfile = userProfile;
	this.userUid = userUid;
    }


    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the day of the statistic.
     * 
     * @return 
     * 	the day of the statistic.
     */
    public Date getDay() {
        return day;
    }

    /**
     * Sets the day of the statistic.
     * 
     * @param day 
     * 	The day to set.
     */
    public void setDay(Date day) {
        this.day = day;
    }

    /**
     * Gets the UAI of the establishment concerned by the statistic.
     * 
     * @return 
     * 	the UAI of the  establishment concerned by the statistic.
     */
    public String getEstablishmentUai() {
        return establishmentUai;
    }

    /**
     * Sets the UAI of the establishment concerned by the statistic.
     * 
     * @param establishmentUai 
     * 	The UAI of the establishment to set.
     */
    public void setEstablishmentUai(String establishmentUai) {
        this.establishmentUai = establishmentUai;
    }

    /**
     * Gets the number of connections of the user to the service.
     * 
     * @return 
     * 	the number of connections  of the user to the service.
     */
    public Integer getNumConnections() {
        return numConnections;
    }

    /**
     * Sets the number of connections of the user to the service.
     * 
     * @param numConnections 
     * 			The number of connections to set.
     */
    public void setNumConnections(Integer numConnections) {
        this.numConnections = numConnections;
    }

    /**
     * Gets the name of the service concerned by the statistic.
     * 
     * @return 
     * 	the name of the service concerned by the statistic.
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets the name of the service concerned by the statistic.
     * 
     * @param serviceName 
     * 	the name of the service to set.
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
	result = prime * result + ((day == null) ? 0 : day.hashCode());
	result = prime * result + ((establishmentUai == null) ? 0 : establishmentUai.hashCode());
	result = prime * result + ((numConnections == null) ? 0 : numConnections.hashCode());
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
	ServiceConnectionStatistic other = (ServiceConnectionStatistic) obj;
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
	if (numConnections == null) {
	    if (other.numConnections != null)
		return false;
	} else if (!numConnections.equals(other.numConnections))
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
