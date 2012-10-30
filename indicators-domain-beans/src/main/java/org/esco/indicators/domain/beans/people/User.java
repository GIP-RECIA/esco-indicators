package org.esco.indicators.domain.beans.people;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.esco.indicators.domain.beans.group.Group;

/**
 * Class representing an authentified user which can interact with this
 * application.
 * 
 * @since 2012/05/24
 * @author Frapin Kevin <kevin.frapin@recia.fr>
 */
public class User implements Serializable {

    // --------------------------------------------------------------------------------- ATTRIBUTES
    /** Auto generated UID */
    private static final long serialVersionUID = -1614824093751989094L;
    
    /** Display Name of the user */
    private String displayName;
    
    /** User groups */
    private List<Group> groups;
    
    /** Prefered language */
     private String language;
     
     /** User login */
     private String login;
     
     /** User UID */
     private String uid;
     
     /** UAI of the user establishment */
     private String establishmentUAI;
     
     //-------------------------------------------------------------------------------- CONSTRUCTORS
     /**
      * Default constructor of the {@link User} class.<br/>
      * The user created is considered as a guest user.
      */
     public User() {
	 this.groups = new ArrayList<Group>();
     }
     
     /**
      * Constructor of the {@link User} class.<br/>
      * The user created is not consiedred a guest user.
      * 
     * @param displayName
     * 			The display name of the user.
     * @param login
     * 			The login of the user.
     * @param uid
     * 			The UID of the user.
     * @param establishmentUAI
     * 			The UAI of the user establishment.
      */
    public User(String displayName, String login, String uid, String establishmentUAI) {
	 this.displayName = displayName;
	 this.login = login;
	 this.uid = uid;
	 this.establishmentUAI = establishmentUAI;
	 this.groups = new ArrayList<Group>();
     }
     
    //--------------------------------------------------------------------------- GETTERS / SETTERS
     /**
      * Indicates if the user is a guest.<br/>
      * A user is considered as a guest if he has no attribute set among :
      * <ul>
      * 	<li>The display name attribute</li>
      * 	<li>The login attribute</li>
      * 	<li>The UID attribute</li>
      * </ul>
      * 
      * @return 
      * 	<code>true</code> if the user is a guest<br/>
      * 	<code>false</code> in other cases.
      */
    public boolean isGuest() {
        return (displayName == null && login == null && uid == null);
    }

    /**
      * @return the user display language.
      */
     public String getDisplayLanguage() {
        Locale locale = new Locale(language);
        return locale.getDisplayLanguage(locale);
     }

    /**
      * @return Returns the displayName.
      */
     public String getDisplayName() {
        return this.displayName;
     }

    /**
      * @param displayName
      *            The displayName to set.
      */
     public void setDisplayName(String displayName) {
        this.displayName = displayName;
     }

     
     /**
      * Gets the UAI of the establishment user.
      * 
     * @return 
     * 	the UAI of the establishment user.
     */
    public String getEstablishmentUAI() {
        return establishmentUAI;
    }

    /**
     * Sets the UAI of the establishment user.
     * 
     * @param establishmentUAI 
     * 			the UAI of the establishment user to set.
     */
    public void setEstablishmentUAI(String establishmentUAI) {
        this.establishmentUAI = establishmentUAI;
    }

    /**
      * Gets the groups of the user.
      * 
      * @return 
      * 	the groups of the user
      */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Gets the login of the user.
     * 
      * @return 
      * 	the login of the user.
      */
     public String getLogin() {
 	return login;
     }

     /**
      * Sets the login of the user.
      * 
      * @param login
      * 	The login of the user.
      */
     public void setLogin(String login) {
 	this.login = login;
     }

     

    //------------------------------------------------------------------------------ PUBLIC METHODS
     /**
      * Adds a group to the user set of groups.
      * 
      * @param group
      *            		The group to add to the user groups.
      */
     public void addGroup(Group group) {
         groups.add(group);
     }
     
     /**
      * Adds groups to the user set of groups.
      * 
      * @param groups
      *            		The groups to add to the user groups.
      */
     @SuppressWarnings("hiding")
     public void addGroups(List<Group> groups) {
         this.groups.addAll(groups);
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
	User other = (User) obj;
	if (displayName == null) {
	    if (other.displayName != null)
		return false;
	} else if (!displayName.equals(other.displayName))
	    return false;
	if (groups == null) {
	    if (other.groups != null)
		return false;
	} else if (!groups.equals(other.groups))
	    return false;
	if (language == null) {
	    if (other.language != null)
		return false;
	} else if (!language.equals(other.language))
	    return false;
	if (login == null) {
	    if (other.login != null)
		return false;
	} else if (!login.equals(other.login))
	    return false;
	if (uid == null) {
	    if (other.uid != null)
		return false;
	} else if (!uid.equals(other.uid))
	    return false;
	return true;
    }

     /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
	result = prime * result + ((groups == null) ? 0 : groups.hashCode());
	result = prime * result + ((language == null) ? 0 : language.hashCode());
	result = prime * result + ((login == null) ? 0 : login.hashCode());
	result = prime * result + ((uid == null) ? 0 : uid.hashCode());
	return result;
    }

     /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "User [displayName=" + displayName + ", groups=" + groups + ", language=" + language
		+ ", login=" + login + ", uid=" + uid + "]";
    }

     //----------------------------------------------------------------------------- PRIVATE METHODS
     /**
      * Sets the groups of the user.
      * @param groups
      * 			The set of groups to set.
      */
    private void setGroups(List<Group> groups) {
        this.groups= groups ;
    }
     
     //------------------------------------------------------------------------------ STATIC METHODS     

 
}