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
import org.hibernate.annotations.NaturalId;

/**
 * Class representing the composite key of a {@link ServiceConnectionStatistic}.
 * 
 * @since  2012/10/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Embeddable
@SuppressWarnings("unused")
public class ServiceConnectionStatisticId implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Serial version uid of the class */
    @Transient
    private static final long serialVersionUID = 4531627328487554126L;
    
    /** Day of the statistic */
    @Column(name = "jour", nullable = false)
    private Date day;
    
    /** UAI of the establishment */
    @Column(name = "uai", nullable = false)
    private String establishmentUai;
    
    /** Name of the service */
    @Column(name = "nomservice", nullable = false)
    private String serviceName;
    
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
