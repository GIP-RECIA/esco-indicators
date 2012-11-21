/************************************************
 *
 * Author : Frapin Kevin <kevin.frapin@recia.fr>
 * Date : 2012/06/28
 *
 * **********************************************
 *
 * Description :
 * -------------
 * File containing the functions used to 
 * manipulate the content of the result page.
 * Thses manipulations consist in :
 *  - Hiding columns in the result table
 *  - Showing columns in the result table
 * **********************************************
 */




///////////////////////////////////////////////////////////
// INITIALIZATION FUNCTIONS
///////////////////////////////////////////////////////////

/**
 * Function that initializes the possible manipulations on the content.
 */
$(document).ready(function() {

    $("#excelexport").submit(function() {
        $("#excelWarnings").show("blind");
    });

});
