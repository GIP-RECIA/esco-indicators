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

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
