/************************************************
 *
 * Author : Frapin Kevin <kevin.frapin@recia.fr>
 * Date : 2012/07/12
 *
 * **********************************************
 *
 * Description :
 * -------------
 * File containing the constants used for 
 * manipulating the DOM.
 *
 * **********************************************
 */

///////////////////////////////////////////////////////////
// CONSTANTS
///////////////////////////////////////////////////////////
var SEPARATOR               = ";";
var ESTABLISHMENTS_TABLE_ID = "establishmentsList";

var ESTAB_TYPES             = new Object( );
ESTAB_TYPES.name            = "establishmentsTypes";

var USERS_PROFILES          = new Object( );
USERS_PROFILES.name         = "usersProfiles";
USERS_PROFILES.values       = new Array( "usersProfiles.EMPLOYERS",
                                         "usersProfiles.RELATIVES",
                                         "usersProfiles.STUDENTS",
                                         "usersProfiles.TEACHERS",
                                         "usersProfiles.STAFF",
                                         "usersProfiles.STAFF_COLLECTIVITY" );

var ATTENDANCE              = new Object( );
ATTENDANCE.name             = "monitoringType.ATTENDANCE";

var MONITORING_ATTENDANCE   = new Object( );
MONITORING_ATTENDANCE.name  = "monitoringType.MONITORING_ATTENDANCE";

var CFA_INPUT               = new Object( );
CFA_INPUT.name              = "establishmentType.CFA";

var COLL_INPUT              = new Object( );
COLL_INPUT.name             = "establishmentType.COLLEGE";

var LEN_INPUT               = new Object( );
LEN_INPUT.name              = "establishmentType.LEN";
LEN_INPUT.dependencies      = new Array("lyceesTypes.LP", "lyceesTypes.LEGT", "lyceesTypes.EREA");

var LA_INPUT                = new Object( );
LA_INPUT.name               = "establishmentType.LA";
LA_INPUT.dependencies       = new Array("laTypes.LEGTA", "laTypes.LPA");

var SUM_ON_COUNTIES_INPUT           = new Object( );
SUM_ON_COUNTIES_INPUT.name          = "sumOnCounties.DEFAULT";
SUM_ON_COUNTIES_INPUT.dependency    = "county.COUNTY_ALL";
