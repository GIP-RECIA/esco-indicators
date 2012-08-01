/**
 * 
 */
package org.esco.indicators.utils.constants.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

/**
 * Constants concerning the containing data present int the web forms.
 * 
 * @since 2012/06/19
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class DataFormConstants {

    ///////////////////////////////////////////////////////
    // Form IDs
    ///////////////////////////////////////////////////////
    
    
    ///////////////////////////////////////////////////////
    // FORM ENTRIES
    ///////////////////////////////////////////////////////
    
    ///////////////////////////////////
    // Basic form
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
    // Service form
    ///////////////////////////////////
    /** Services */
    public static final String SERVICES = "services";

    /** Available services */
    public static final  String AVAILABLE_SERVICES = "availableServices"; 
    
    /** Wanted services */
    public static final  String WANTED_SERVICES = "wantedServices"; 
    
    ///////////////////////////////////////////////////////
    // FORM VALUES
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

    /** Sum on counties */
    public static final  String JSP_KEY_SUM_ON_COUNTIES = "sumOnCounties.DEFAULT";
    
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
    
    ///////////////////////////////////
    // Services
    ///////////////////////////////////
    
    /************************
     * EDUCATION SERVICES
     ************************/
    /** Moodle service */
    public static final String JSP_KEY_SERVICES_EDUC_MOODLE = "services.education.MOODLE";
    /** Storage service */
    public static final String JSP_KEY_SERVICES_EDUC_STORAGE = "services.education.STORAGE";
    /** Help work service */
    public static final String JSP_KEY_SERVICES_EDUC_HELP_WORK = "services.education.HELP_WORK";
    /** Education services sum */
    public static final String JSP_KEY_SERVICES_EDUC_SUM = "services.education.SUM";
    
    /************************
     * COMMUNICATION 
     * SERVICES
     ************************/
    /** Students mail service */
    public static final String JSP_KEY_SERVICES_COMM_STUDENTS_MAIL = "services.communication.STUDENTS_MAIL";
    /** Academic mail service */
    public static final String JSP_KEY_SERVICES_COMM_ACADEMIC_MAIL = "services.communication.ACADEMIC_MAIL";
    /** Agri. mail service */
    public static final String JSP_KEY_SERVICES_COMM_AGRI_MAIL = "services.communication.AGRI_MAIL";
    /** Annoucement publication service */
    public static final String JSP_KEY_SERVICES_COMM_ANNOUNCEMENT_PUBLICATION = "services.communication.ANNOUNCEMENT_PUBLICATION";
    /** Announcement reading service */
    public static final String JSP_KEY_SERVICES_COMM_ANNOUNCEMENT_READING = "services.communication.ANNOUNCEMENT_READING";
    /** Communication services sum */
    public static final String JSP_KEY_SERVICES_COMM_SUM = "services.communication.SUM";
    
    /************************
     * ESTABLISHMENT 
     * SERVICES
     ************************/
    /** GRR service */
    public static final String JSP_KEY_SERVICES_ESTAB_GRR = "services.services.GRR";
    /** Indicators service */
    public static final String JSP_KEY_SERVICES_ESTAB_INDICATORS = "services.services.INDICATORS";
    /** Establishment services sum */
    public static final String JSP_KEY_SERVICES_ESTAB_SUM = "services.services.SUM";
    
    /************************
     * GUIDE SERVICES
     ************************/
    /** Web file service */
    public static final String JSP_KEY_SERVICES_GUIDE_WEB_FILE = "services.guide.WEB_FILE";
    /** ONISEP service */
    public static final String JSP_KEY_SERVICES_GUIDE_ONISEP = "services.guide.ONISEP";
    /** Etoile service */
    public static final String JSP_KEY_SERVICES_GUIDE_ETOILE = "services.guide.ETOILE";
    /** Guide services sum */
    public static final String JSP_KEY_SERVICES_GUIDE_SUM = "services.guide.SUM";
    
    /************************
     * SCHOOL
     * SERVICES
     ************************/
    /** Text book service */
    public static final String JSP_KEY_SERVICES_SCHOOL_TEXT_BOOK = "services.eservices.TEXT_BOOK";
    /** Pronote service */
    public static final String JSP_KEY_SERVICES_SCHOOL_PRONOTE = "services.eservices.PRONOTE";
    /** Sconet score service */
    public static final String JSP_KEY_SERVICES_SCHOOL_SCONET_SCORE = "services.eservices.SCONET_SCORE";
    /** GEPI service */
    public static final String JSP_KEY_SERVICES_SCHOOL_GEPI = "services.eservices.GEPI";
    /** Net schooling service */
    public static final String JSP_KEY_SERVICES_SCHOOL_NET_SCHOOLING = "services.eservices.NET_SCHOOLING";
    /** Net school life service */
    public static final String JSP_KEY_SERVICES_SCHOOL_NET_SCHOOL_LIFE = "services.eservices.NET_SCHOOL_LIFE";
    /** SCONET absence service */
    public static final String JSP_KEY_SERVICES_SCHOOL_SCONET_ABSENCE = "services.eservices.SCONET_ABSENCE";    
    /** E-services */
    public static final String JSP_KEY_SERVICES_SCHOOL_E_SERVICES = "services.eservices.E_SERVICES";    
    /** School services sum */
    public static final String JSP_KEY_SERVICES_SCHOOL_SUM = "services.eservices.SUM";    
    
    /************************
     * DOCUMENTATION
     * SERVICES
     ************************/
    /** CNS KNE service */
    public static final String JSP_KEY_SERVICES_DOC_CNS_KNE = "services.documentation.CNS_KNE";    
    /** Encyclopedia service */
    public static final String JSP_KEY_SERVICES_DOC_ENCYCLOPEDIA = "services.documentation.ENCYCLOPEDIA";    
    /** Especial resources service */
    public static final String JSP_KEY_SERVICES_DOC_ESPECIAL_RESOURCES = "services.documentation.ESPECIAL_RESOURCES";    
    /** eSidoc service */
    public static final String JSP_KEY_SERVICES_DOC_E_SIDOC = "services.documentation.E_SIDOC";    
    /** Documentation services sum */
    public static final String JSP_KEY_SERVICES_DOC_SUM = "services.documentation.SUM";   
    
    /************************
     * MASTERS AND SLAVES
     * SERVICES
     ************************/
    /** Map containing the masters services (sums) and their slaves */
    public static final Map<String, List<String>> MASTERS_SLAVES_SERVICES;
    static {
	// Initialization
	MASTERS_SLAVES_SERVICES = new HashMap<String, List<String>>();
	
	// Education services
	List<String> educationServices = new ArrayList<String>();
	educationServices.add(JSP_KEY_SERVICES_EDUC_MOODLE);
	educationServices.add(JSP_KEY_SERVICES_EDUC_STORAGE);
	educationServices.add(JSP_KEY_SERVICES_EDUC_HELP_WORK);
	MASTERS_SLAVES_SERVICES.put(JSP_KEY_SERVICES_EDUC_SUM, educationServices);
	
	// Communication services
	List<String> commServices = new ArrayList<String>();
	commServices.add(JSP_KEY_SERVICES_COMM_STUDENTS_MAIL);
	commServices.add(JSP_KEY_SERVICES_COMM_ACADEMIC_MAIL);
	commServices.add(JSP_KEY_SERVICES_COMM_AGRI_MAIL);
	commServices.add(JSP_KEY_SERVICES_COMM_ANNOUNCEMENT_PUBLICATION);
	commServices.add(JSP_KEY_SERVICES_COMM_ANNOUNCEMENT_READING);
	MASTERS_SLAVES_SERVICES.put(JSP_KEY_SERVICES_COMM_SUM, commServices);
	
	// Establishment services
	List<String> estabServices = new ArrayList<String>();
	estabServices.add(JSP_KEY_SERVICES_ESTAB_GRR);
	estabServices.add(JSP_KEY_SERVICES_ESTAB_INDICATORS);
	MASTERS_SLAVES_SERVICES.put(JSP_KEY_SERVICES_ESTAB_SUM, estabServices);
	
	// Guide services
	List<String> guideServices = new ArrayList<String>();
	guideServices.add(JSP_KEY_SERVICES_GUIDE_WEB_FILE);
	guideServices.add(JSP_KEY_SERVICES_GUIDE_ONISEP);
	guideServices.add(JSP_KEY_SERVICES_GUIDE_ETOILE);
	MASTERS_SLAVES_SERVICES.put(JSP_KEY_SERVICES_GUIDE_SUM, guideServices);
	
	// School services
	List<String> schoolServices = new ArrayList<String>();
	schoolServices.add(JSP_KEY_SERVICES_SCHOOL_TEXT_BOOK);
	schoolServices.add(JSP_KEY_SERVICES_SCHOOL_PRONOTE);
	schoolServices.add(JSP_KEY_SERVICES_SCHOOL_SCONET_SCORE);
	schoolServices.add(JSP_KEY_SERVICES_SCHOOL_GEPI);
	schoolServices.add(JSP_KEY_SERVICES_SCHOOL_NET_SCHOOLING);
	schoolServices.add(JSP_KEY_SERVICES_SCHOOL_NET_SCHOOL_LIFE);
	schoolServices.add(JSP_KEY_SERVICES_SCHOOL_SCONET_ABSENCE);
	schoolServices.add(JSP_KEY_SERVICES_SCHOOL_E_SERVICES);
	MASTERS_SLAVES_SERVICES.put(JSP_KEY_SERVICES_SCHOOL_SUM, schoolServices);
	
	// Documentation services
	List<String> docServices = new ArrayList<String>();
	docServices.add(JSP_KEY_SERVICES_DOC_CNS_KNE);
	docServices.add(JSP_KEY_SERVICES_DOC_ENCYCLOPEDIA);
	docServices.add(JSP_KEY_SERVICES_DOC_ESPECIAL_RESOURCES);
	docServices.add(JSP_KEY_SERVICES_DOC_E_SIDOC);
	MASTERS_SLAVES_SERVICES.put(JSP_KEY_SERVICES_DOC_SUM, docServices);
	
	Collections.unmodifiableMap(MASTERS_SLAVES_SERVICES);
    }
}
