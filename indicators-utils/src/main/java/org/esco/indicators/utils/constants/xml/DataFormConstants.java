/**
 * 
 */
package org.esco.indicators.utils.constants.xml;

/**
 * Constants concerning the estbalishments in the XML file containing data form.
 * 
 * @since 2012/06/19
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class DataFormConstants {

    ///////////////////////////////////////////////////////
    // Names of the XML  <entry-form> tags
    ///////////////////////////////////////////////////////
    
    ///////////////////////////////////
    // Basic form entries
    ///////////////////////////////////
    /** County  */
    public static final  String COUNTY = "county"; 

    /** End date */
    public static final  String END_DATE = "endDate";

    /** Establishments types */
    public static final  String ESTABLISHMENTS_TYPES = "establishmentsTypes";

    /** "Lycees" types */
    public static final  String LYCEES_TYPES = "lyceesTypes"; 
    
    /** "Lycees agricoles" types */
    public static final  String LA_TYPES = "laTypes";
    
    /** Monitoring type */
    public static final  String MONITORING_TYPE = "monitoringType";

    /** Start date */
    public static final  String START_DATE = "startDate";

    /** Sum on counties */
    public static final  String SUM_ON_COUNTIES = "sumOnCounties";

    /** Users profiles */
    public static final  String USERS_PROFILES = "usersProfiles"; 
    
    /** Establishments */
    public static final  String ESTABLISHMENTS = "establishments"; 
    
    ///////////////////////////////////
    // Service form entries
    ///////////////////////////////////
    /** Services names */
    public static final String SERVICES = "services";
    
    ///////////////////////////////////////////////////////
    // Values of the XML  <jsp-key> tags
    ///////////////////////////////////////////////////////
    
    ///////////////////////////////////
    // Monitoring type
    ///////////////////////////////////
    /** Attendance */
    public static final  String JSP_KEY_ATTENDANCE = "monitoringType.ATTENDANCE";
    
    /** Monitoring attendance */
    public static final  String JSP_KEY_MONITORING_ATTENDANCE = "monitoringType.MONITORING_ATTENDANCE";
    
    ///////////////////////////////////
    // Establishments types
    ///////////////////////////////////
    /** College */
    public static final  String JSP_KEY_COLLEGE = "establishmentType.COLLEGE";
    
    /** CFA */
    public static final  String JSP_KEY_CFA = "establishmentType.CFA";
    
    /** LA */
    public static final  String JSP_KEY_LA = "establishmentType.LA";
    
    /** LEN */
    public static final  String JSP_KEY_LEN = "establishmentType.LEN";
    
    ///////////////////////////////////
    // Users profiles
    ///////////////////////////////////
    /** Employers */
    public static final  String JSP_KEY_ALL_PROFILES = "usersProfiles.ALL";
    
    /** Employers */
    public static final  String JSP_KEY_EMPLOYERS = "usersProfiles.EMPLOYERS";
    
    /** Relatives */
    public static final  String JSP_KEY_RELATIVES = "usersProfiles.RELATIVES";
    
    /** Students */
    public static final  String JSP_KEY_STUDENTS = "usersProfiles.STUDENTS";
    
    /** Teachers */
    public static final  String JSP_KEY_TEACHERS = "usersProfiles.TEACHERS";
    
    /** Staff */
    public static final  String JSP_KEY_STAFF  = "usersProfiles.STAFF";
    
    /** Staff collectivity */
    public static final  String JSP_KEY_STAFF_COLLECTIVITY = "usersProfiles.STAFF_COLLECTIVITY";
    
    ///////////////////////////////////
    // Counties
    ///////////////////////////////////
    /** All counties */
    public static final  String JSP_KEY_COUNTY_ALL = "county.COUNTY_ALL";
    
    /** County 28 */
    public static final  String JSP_KEY_COUNTY_28 = "county.COUNTY_28";
    
    /** County 36 */
    public static final  String JSP_KEY_COUNTY_36 = "county.COUNTY_36";
    
    /** County 37 */
    public static final  String JSP_KEY_COUNTY_37 = "county.COUNTY_37";
    
    /** County 41 */
    public static final  String JSP_KEY_COUNTY_41 = "county.COUNTY_41";
    
    /** County 45 */
    public static final  String JSP_KEY_COUNTY_45 = "county.COUNTY_45";

    
    ///////////////////////////////////
    // Lycees types
    ///////////////////////////////////
    /** EREA */
    public static final  String JSP_KEY_EREA = "lyceesTypes.EREA";
    
    /** LEGT */
    public static final  String JSP_KEY_LEGT = "lyceesTypes.LEGT";
    
    /** EREA */
    public static final  String JSP_KEY_LP  = "lyceesTypes.LP";
    
    
    ///////////////////////////////////
    // LA types
    ///////////////////////////////////
    /** LEGTA */
    public static final  String JSP_KEY_LEGTA = "laTypes.LEGTA";
    
    /** LPA */
    public static final  String JSP_KEY_LPA = "laTypes.LPA";
    
}
