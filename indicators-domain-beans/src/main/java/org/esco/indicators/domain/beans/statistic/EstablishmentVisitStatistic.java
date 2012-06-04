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
 * Class representing the statistics on the visits / visitors of an establishment.
 * 
 * @since 2012/05/31
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Entity
@NamedQueries({
    @NamedQuery(
	    name = "EstablishmentVisitStatistic.Daily.findVisitStatistic",
	    query = "SELECT evs FROM EstablishmentVisitStatistic evs"
	    		+ " WHERE evs.establishmentUai = :establishmentUai" 
	    		+ " AND evs.day = :day AND evs.establishmentType = :establishmentType"
	    		+ " AND evs.typeStat = :typeStat"
	    )
})
@Table(name = "nombredevisiteurs")
public class EstablishmentVisitStatistic implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    @Transient
    private static final Logger LOGGER = Logger.getLogger(EstablishmentVisitStatistic.class);

    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = 6340400790820657070L;
    
    /** Generated identifier */
    @Id
    @GeneratedValue
    private long id;
    
    /** Day of the statistic */
    @Column(name = "jour", nullable = false)
    private Date day;
    
    /** Type of the establishment */
    @Column(name = "typeetab", nullable = false)
    private String establishmentType;
    
    /** UAI of the establishment */
    @Column(name = "uai", nullable = false)
    private String establishmentUai;
    
    /** Number of visits */
    @Column(name = "nbvisites")
    private Integer numVisits;
    
    /** Number of visitors */
    @Column(name = "nbvisiteurs")
    private Integer numVisitors;
    
    /** 
     * Type of the statistic.<br/>
     * This field is used to indicate if the row contains a simple statistic or a complex one.<br/>
     * This field can, for instance, indicate if a row is a sum or an aggregation of others rows.
     */
    @Column(name = "typestat", nullable = false)
    private String typeStat;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the EstablishmentVisitStatistic class.
     */
    public EstablishmentVisitStatistic() {
	super();
    }

    
    
    /**
     * Constructor of the EstablishmentVisitStatistic class.
     * 
     * @param day
     * 			The day of the statistic.
     * @param establishmentType
     * 			The type of the establishment concerned by the statistics.
     * @param establishmentUai
     * 			The UAI of the establishment concerned by the statistics.
     * @param typeStat
     * 			The type of the statistic.
     */
    public EstablishmentVisitStatistic(Date day, String establishmentType, String establishmentUai,
	    String typeStat) {
	super();
	this.day = day;
	this.establishmentType = establishmentType;
	this.establishmentUai = establishmentUai;
	this.typeStat = typeStat;
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
     * 	the day of the statistic to set
     */
    public void setDay(Date day) {
        this.day = day;
    }

    /**
     * Gets the establishment type associated to the statistic.
     * 
     * @return 
     * 	the establishment type associated to the statistic.
     */
    public String getEstablishmentType() {
        return establishmentType;
    }

    /**
     * Sets the establishment type of the statistic.
     * 
     * @param establishmentType 
     * 			the establishment type of the statistic to set.
     */
    public void setEstablishmentType(String establishmentType) {
        this.establishmentType = establishmentType;
    }

    /**
     * Gets the establishment UAI associated to the statistic.
     * 
     * @return 
     * 	the establishment UAI associated to the statistic.
     */
    public String getEstablishmentUai() {
        return establishmentUai;
    }

    /**
     * Sets the establishment UAI associated to the statistic.
     * 
     * @param establishmentUai 
     * 			the establishment UAI of the statistic to set.
     */
    public void setEstablishmentUai(String establishmentUai) {
        this.establishmentUai = establishmentUai;
    }

    /**
     * Gets the number of visits of the statistic.
     * 
     * @return 
     * 	the number of visits of the statistic.
     */
    public Integer getNumVisits() {
        return numVisits;
    }

    /**
     * Sets the number of visits of the statistic.
     * 
     * @param numVisits 
     * 			the number of visits of the statistic to set.
     */
    public void setNumVisits(Integer numVisits) {
        this.numVisits = numVisits;
    }

    /**
     * Gets the number of visitors of the statistic.
     * 
     * @return 
     * 	the number of visitors of the statistic.
     */
    public Integer getNumVisitors() {
        return numVisitors;
    }

    /**
     * Sets the number of visitors of the statistic.
     * 
     * @param numVisitors 
     * 			the number of visitors of the statistic to set.
     */
    public void setNumVisitors(Integer numVisitors) {
        this.numVisitors = numVisitors;
    }

    /**
     * Gets the type of the statistic.
     * 
     * @return 
     * 	the type of the statistic.
     */
    public String getTypeStat() {
        return typeStat;
    }

    /**
     * Sets the type of the statistic.
     * 
     * @param typeStat 
     * 			the statistic type to set.
     */
    public void setTypeStat(String typeStat) {
        this.typeStat = typeStat;
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
	result = prime * result + ((establishmentType == null) ? 0 : establishmentType.hashCode());
	result = prime * result + ((establishmentUai == null) ? 0 : establishmentUai.hashCode());
	result = prime * result + ((numVisitors == null) ? 0 : numVisitors.hashCode());
	result = prime * result + ((numVisits == null) ? 0 : numVisits.hashCode());
	result = prime * result + ((typeStat == null) ? 0 : typeStat.hashCode());
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
	EstablishmentVisitStatistic other = (EstablishmentVisitStatistic) obj;
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
	if (numVisitors == null) {
	    if (other.numVisitors != null)
		return false;
	} else if (!numVisitors.equals(other.numVisitors))
	    return false;
	if (numVisits == null) {
	    if (other.numVisits != null)
		return false;
	} else if (!numVisits.equals(other.numVisits))
	    return false;
	if (typeStat == null) {
	    if (other.typeStat != null)
		return false;
	} else if (!typeStat.equals(other.typeStat))
	    return false;
	return true;
    }
    

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
