/**
 * 
 */
package org.esco.indicators.domain.beans.profile;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.IdClass;

/**
 * Class representing the composite key of a {@link ProfileLink}.
 * 
 * @since  2012/10/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Embeddable
@SuppressWarnings("unused")
public class ProfileLinkId implements Serializable {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Auto generated UID */
    private static final long serialVersionUID = -2232663104736536443L;
    
    /** UAI of the establishment */
    @Column(name = "uai", nullable = false, insertable = true)
    private String establishmentUai;
    
    /** Date of creation of the link */
    @Column(name = "datedebutprofil", nullable = false)
    private Date linkStart;
    
    /** Profile of the user */
    @Column(name = "nomprofil", nullable = false)
    private String userProfile;
    
    /** UID of the user linked to the profile */
    @Column(name = "uid", nullable = false)
    private String userUid;


    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((establishmentUai == null) ? 0 : establishmentUai.hashCode());
	result = prime * result + ((linkStart == null) ? 0 : linkStart.hashCode());
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
	ProfileLinkId other = (ProfileLinkId) obj;
	if (establishmentUai == null) {
	    if (other.establishmentUai != null)
		return false;
	} else if (!establishmentUai.equals(other.establishmentUai))
	    return false;
	if (linkStart == null) {
	    if (other.linkStart != null)
		return false;
	} else if (!linkStart.equals(other.linkStart))
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
