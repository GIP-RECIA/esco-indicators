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
 * Class representing the composite key of a {@link WeeklyPortalConnectionStatistic}.
 * 
 * @since  2012/10/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Embeddable
@SuppressWarnings("unused")
public class WeeklyPortalConnectionStatisticId implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = 3351340841236117868L;
    
    /** UAI of the establishment */
    @Column(name = "uai")
    private String establishmentUai;
    
    /** First day of the week */
    @Column(name = "semaine", nullable = false)
    private Date firstWeekDay;
    
    /** Number of connections of the profile to the portal */
    @Column(name = "nbconnexion", nullable = false)
    private Integer numConnections;
    
    /** Profile of the user */
    @Column(name = "nomprofil", nullable = false)
    private String userProfile;
    

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
