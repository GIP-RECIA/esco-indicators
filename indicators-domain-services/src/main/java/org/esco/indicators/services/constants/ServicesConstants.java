/**
 * 
 */
package org.esco.indicators.services.constants;

/**
 * Constants concerning the wantedServices.
 * 
 * @since  2012/07/05
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ServicesConstants {
    ///////////////////////////////////////////////////////
    // STATISTICS CONSTANTS
    ///////////////////////////////////////////////////////
    /** 
     * The treshold used to split the set of visitors in two groups :
     * <ul>
     * 	<li> The group of visitors having made less than <code>NUM_CONNECTIONS_TRESHOLD</code> connections</li>
     * 	<li>The group of visitors having made more than <code>NUM_CONNECTIONS_TRESHOLD</code> connections</li>
     * <ul>
     */
    public static final Integer NUM_CONNECTIONS_TRESHOLD = 4;
    
    /**
     * Constant used to index global statistic data in the result rows.
     */
    public static final String GLOBAL_STATISTIC = "GLOBAL_STATISTIC";
    
    
    ///////////////////////////////////////////////////////
    // LDAP CONSTANTS
    ///////////////////////////////////////////////////////
    /**
     * LDAP attribute containing the user display name.
     */
    public static final String LDAP_USER_DISPLAY_NAME = "displayName";
    
    /**
     * LDAP attribute containing the user login.
     */
    public static final String LDAP_USER_LOGIN = "ENTPersonLogin";
    
    /**
     * LDAP attribute containing the user UID.
     */
    public static final String LDAP_USER_UID = "uid";
    
    /**
     * LDAP attribute containing the user groups.
     */
    public static final String LDAP_USER_GROUPS = "isMemberOf";
    
}
