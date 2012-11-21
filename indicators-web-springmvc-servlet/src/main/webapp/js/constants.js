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

///////////////////////////////////////
// JSON CONSTANTS
///////////////////////////////////////
var SEPARATOR               = ";";

///////////////////////////////////////
// ELEMENTS IDENTIFIERS
///////////////////////////////////////
var ESTABLISHMENTS_TABLE_ID     = "establishmentsList";

var AVAILABLE_SERVICES_ID       = "availableServicesList";
var WANTED_SERVICES_ID          = "wantedServicesList";
var AVAILABLE_SERVICES_NAME     = "availableServices";
var WANTED_SERVICES_NAME        = "wantedServices";

///////////////////////////////////////
// ELEMENTS CLASSES
///////////////////////////////////////
var CONNECTED_SERVICES_CLS      = "connectedServices";

///////////////////////////////////////
// FORM INPUTS
///////////////////////////////////////
var ATTENDANCE              = new Object( );
ATTENDANCE.name             = "monitoringType.ATTENDANCE";

var CFA_INPUT               = new Object( );
CFA_INPUT.name              = "establishmentType.CFA";

var COLL_INPUT              = new Object( );
COLL_INPUT.name             = "establishmentType.COLL";

var ESTAB_TYPES             = new Object( );
ESTAB_TYPES.name            = "establishmentsTypes";


var LA_INPUT                = new Object( );
LA_INPUT.name               = "establishmentType.LA";
LA_INPUT.dependencies       = new Array("establishmentType.LEGTA", "establishmentType.LPA");

var LEN_INPUT               = new Object( );
LEN_INPUT.name              = "establishmentType.LEN";
LEN_INPUT.dependencies      = new Array("establishmentType.LG", "establishmentType.LPO", "establishmentType.LP", "establishmentType.LEGT", "establishmentType.EREA");

var MONITORING_ATTENDANCE   = new Object( );
MONITORING_ATTENDANCE.name  = "monitoringType.MONITORING_ATTENDANCE";

var SUM_ON_COUNTIES_INPUT           = new Object( );
SUM_ON_COUNTIES_INPUT.name          = "sumOnCounties.DEFAULT";
SUM_ON_COUNTIES_INPUT.dependency    = "county.COUNTY_ALL";

var USERS_PROFILES          = new Object( );
USERS_PROFILES.name         = "usersProfiles";
USERS_PROFILES.values       = new Array( "usersProfiles.EMPLOYERS",
                                         "usersProfiles.RELATIVES",
                                         "usersProfiles.STUDENTS",
                                         "usersProfiles.TEACHERS",
                                         "usersProfiles.STAFF",
                                         "usersProfiles.STAFF_COLLECTIVITY" );

///////////////////////////////////////
// SPINNER OPTIONS
///////////////////////////////////////
var SPINNER_OPTS = {
  lines: 13, // The number of lines to draw
  length: 7, // The length of each line
  width: 2, // The line thickness
  radius: 3, // The radius of the inner circle
  corners: 1, // Corner roundness (0..1)
  rotate: 0, // The rotation offset
  color: '#000', // #rgb or #rrggbb
  speed: 1, // Rounds per second
  trail: 64, // Afterglow percentage
  shadow: false, // Whether to render a shadow
  hwaccel: false, // Whether to use hardware acceleration
  className: 'spinner', // The CSS class to assign to the spinner
  zIndex: 2e9, // The z-index (defaults to 2000000000)
  top: 'auto', // Top position relative to parent in px
  left: 'auto' // Left position relative to parent in px
};
