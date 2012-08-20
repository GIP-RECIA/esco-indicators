/**
 * 
 */
package org.esco.indicators.domain.beans.structure;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

/**
 * Class representing a physical establishment.
 * An establishment can be a college, a university, and so on.
 * 
 * @since 2012/05/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Entity
@NamedQueries({
    	@NamedQuery(
	    	name = "Establishment.findByUai",
	    	query = "SELECT e FROM Establishment e WHERE e.uai = :uai"
	    	),
	@NamedQuery(
		name="Establishment.findByCountyNumbers",
		query="SELECT e FROM Establishment e WHERE e.countyNumber IN ( :countyNumberList ) "
		),
	@NamedQuery(
		name="Establishment.findByTypes",
		query="SELECT e FROM Establishment e WHERE e.type IN ( :typeList )"
		),
	@NamedQuery(
		name="Establishment.findByCountyNumbersAndTypes",
		query=	"SELECT e FROM Establishment e"
				+ " WHERE e.countyNumber IN ( :countyNumberList )" 
				+ " AND e.type IN ( :typeList )"
		)
})
@Table(name = "etablissement")
public class Establishment extends Structure implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    @Transient
    private static final Logger LOGGER = Logger.getLogger(Establishment.class);
    
    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = -126963287110560981L;

    
    /** 
     * County number of the establishment
     * This county number depends on the geographical localisation of the establishment.
     */
    @Column(name = "departement", nullable = false)
    private String countyNumber;

    /** Type of the establishment */
    @Column(name = "typeetablissement", nullable = false)
    private String type;

    /** UAI of the establishment */
    @Id
    @Column(name = "uai")
    private String uai;

    /** Name of the structure */
    @Transient
    private String name;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Default constructor of the class.
     */
    public Establishment() {
	super();
    }

    /**
     * Constructor of the establishment.
     * @param countyNumber
     * 			The county number of the establishment.
     * @param uai
     * 			The UAI of the establishment.
     * @param type
     * 			The type of the establishment.
     */
    public Establishment(String countyNumber, String uai, String type) {
	super();
	this.uai = uai;
	this.countyNumber = countyNumber;
	this.type = type;
    }

  //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the county number of the establishment.
     * This county number depends on the geographical localisation of the establishment.
     * 
     * @return the county number of the establishment.
     */
    public String getCountyNumber() {
        return countyNumber;
    }
    
    /**
     * Sets the county number of the establishment.
     * This county number depends on the geographical localisation of the establishment.
     * 
     * @param countyNumber
     * 			The county number to set.
     */
    public void setCountyNumber(String countyNumber) {
        this.countyNumber = countyNumber;
    }

    /**
     * Gets the name of the structure.
     * 
     * @return
     * 	the name of the strucure.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the structure.
     * 
     * @param name
     * 			The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the type of the estalishment.
     * A type establishment can be something like : college, university, and so on.
     * 
     * @return the type of the establishment.
     */
    public String getType() {
        return type;
    }
    
    /**
     * Sets the type of the establishment.
     * A type establishment can be something like : college, university, and so on.
     * 
     * @param type
     * 			The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the UAI of the establishment.
     * 
     * @return the UAI of the establishment.
     * 
     */
    public String getUai() {
        return uai;
    }

    /**
     * Sets the UAI of the establishment.
     * 
     * @param uai
     * 			The UAI to set.
     */
    public void setUai(String uai) {
        this.uai = uai;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((countyNumber == null) ? 0 : countyNumber.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	result = prime * result + ((uai == null) ? 0 : uai.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Establishment other = (Establishment) obj;
	if (countyNumber == null) {
	    if (other.countyNumber != null)
		return false;
	} else if (!countyNumber.equals(other.countyNumber))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (type == null) {
	    if (other.type != null)
		return false;
	} else if (!type.equals(other.type))
	    return false;
	if (uai == null) {
	    if (other.uai != null)
		return false;
	} else if (!uai.equals(other.uai))
	    return false;
	return true;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    

    //------------------------------------------------------------------------------ STATIC METHODS
}
