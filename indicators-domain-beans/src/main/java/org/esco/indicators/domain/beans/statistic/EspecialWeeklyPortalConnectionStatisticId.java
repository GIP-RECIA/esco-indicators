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
 * Class representing the composite key of a {@link EspecialWeeklyPortalConnectionStatistic}.
 * 
 * @since  2012/10/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Embeddable
@SuppressWarnings("unused")
public class EspecialWeeklyPortalConnectionStatisticId implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = 3675930336077273884L;
    
    /** UAI of the establishment */
    @Column(name = "uai")
    private String establishmentUai;
    
    /** First day of the week */
    @Column(name = "premierjoursemaine", nullable = false)
    private Date firstWeekDay;
    
    /** Profile of the user */
    @Column(name = "nomprofil", nullable = false)
    private String userProfile;

    /** Uid of the user */
    @Column(name = "uid", nullable = false)
    private String userUid;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
