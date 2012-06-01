package org.esco.indicators.domain.beans.people;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.esco.indicators.domain.beans.group.Group;
import org.esco.indicators.domain.beans.structure.Structure;

/**
 * Class representing an authentified user which can interact with this
 * application.
 * 
 * @since 2012/05/24
 * @author Frapin Kevin <kevin.frapin@recia.fr>
 */
public class User {

    // --------------------------------------------------------------------------------- ATTRIBUTES
    /**True for administrators */
    private boolean admin;

    /** Display Name of the user */
    private String displayName;
    
    /** User groups */
    private Set<Group> groups;
    
    /** Prefered language */
     private String language;
     
     /** User login */
     private String login;
     
     /** Main attachment structure of the user */
     private Structure mainStructure;

     //-------------------------------------------------------------------------------- CONSTRUCTORS
     /**
      * Bean constructor.
      */
     public User() {
 	super();
     }
     
    //--------------------------------------------------------------------------- GETTERS / SETTERS
     /**
      * @return Returns the admin.
      */
     public boolean isAdmin() {
        return this.admin;
     }
     
     /**
      * @param admin
      *            The admin to set.
      */
     public void setAdmin(boolean admin) {
        this.admin = admin;
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
      * Gets the groups of the user.
      * @return the groups of the user
      */
    public Set<Group> getGroups() {
        return groups;
    }
    


    /**
      * @return the language
      */
     public String getLanguage() {
        return language;
     }

    /**
      * @param language
      *            the language to set
      */
     public void setLanguage(String language) {
        this.language = language;
     }

    /**
      * @return the login of the user.
      */
     public String getLogin() {
 	return login;
     }

     /**
      * @param login
      */
     public void setLogin(String login) {
 	this.login = login;
     }
     
     /**
      * Gets the main attachment structure of the user.
      * @return the main attachment structure of the user.
      */
     public Structure getMainStructure() {
        return mainStructure;
    }

     /**
      * Sets the main attachment structure of the user.
      * @param mainStructure
      * 			The main attachment structure to set.
      */
    public void setMainStructure(Structure mainStructure) {
        this.mainStructure = mainStructure;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS
     /**
      * Adds a group to the user set of group.
      * @param group
      *            		The group to add to the user set of groups.
      */
     public void addGroup(Group group) {
	 // Create an empty set of groups if necessary
	 Set<Group> currentGroups = getGroups();
	 if(currentGroups == null) {
             currentGroups = new HashSet<Group>();
             setGroups(currentGroups);
         }
	 
	 // Add a new group
         currentGroups.add(group);
     }
     
     
     @Override
     public boolean equals(Object obj) {
 	if (obj == null) {
 	    return false;
 	}
 	if (!(obj instanceof User)) {
 	    return false;
 	}
 	return login.equals(((User) obj).getLogin());
     }

     @Override
     public int hashCode() {
 	return super.hashCode();
     }

     @Override
     public String toString() {
 	return "User#" + hashCode() + "[login=[" + login + "], displayName=[" + displayName
 		+ "], admin=[" + admin + "], language=[" + language + "]]";
     }

     //----------------------------------------------------------------------------- PRIVATE METHODS
     /**
      * Sets the groups of the user.
      * @param groups
      * 			The set of groups to set.
      */
    private void setGroups(Set<Group> groups) {
        this.groups= groups ;
    }
     
     //------------------------------------------------------------------------------ STATIC METHODS     

 
}