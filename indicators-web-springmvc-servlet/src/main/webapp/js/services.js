/************************************************
 *
 * Author : Frapin Kevin <kevin.frapin@recia.fr>
 * Date : 2012/06/15
 *
 * **********************************************
 *
 * Description :
 * -------------
 * File containing the functions used in the 
 * services statistics form.
 * **********************************************
 */

///////////////////////////////////////////////////////////
// CONSTANTS
///////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////
// FUNCTIONS
///////////////////////////////////////////////////////////

$(document).ready(function() {
    // //////////////////////////////////////////////////////////////
    // Link the lists containing the available, and wanted, services
    // //////////////////////////////////////////////////////////////
    connectLists();
});

function connectLists() {
    $("#availableServicesList").sortable({
        connectWith: ".connectedServices"
    }).disableSelection();
    $("#wantedServicesList").sortable({
        connectWith: ".connectedServices"
    }).disableSelection(); 
}
