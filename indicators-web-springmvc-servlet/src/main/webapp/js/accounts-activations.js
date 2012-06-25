/************************************************
 *
 * Author : Frapin Kevin <kevin.frapin@recia.fr>
 * Date : 2012/06/15
 *
 * **********************************************
 *
 * Description :
 * -------------
 * File containing the Ajax functions used to
 * interact with the accounts activations servlet.
 *
 * **********************************************
 */

///////////////////////////////////////////////////////////
// CONSTANTS
///////////////////////////////////////////////////////////
var SEPARATOR = ";";

///////////////////////////////////////////////////////////
// FUNCTIONS
///////////////////////////////////////////////////////////
$.ajaxSetup({"error":function(XMLHttpRequest,textStatus, errorThrown) {   
    alert(textStatus);
    alert(errorThrown);
    alert(XMLHttpRequest.responseText);
}});

$(document).ready(function() {
    // //////////////////////////////////////////////////////////////
    // When an input is checked, the values of the checked inputs are 
    // send to the server in order to know what inputs have to be 
    // disabled into the form.
    // //////////////////////////////////////////////////////////////
    jQuery('.submit').change(function() {
        updateForm();
    });

});
 
///////////////////////////////////////////////////////////
// AJAX FUNCTIONS
///////////////////////////////////////////////////////////
function updateForm() {
    var checkedElements = $("#accountactivationform input:checked");
    var checked = new String( );

    // Retrieval of the values of the checked elements
    for(var i=0; i < checkedElements.length ; i++) {
        checked += checkedElements[i].value + SEPARATOR; 
    }

    // Data for the request
    request = { 'checkedValues[]': checked }
    $.post(  "accounts-activations-ajax/update-form", 
                { 
                    checkedJspKeys : checked
                }, 
                function(elementNamesToDisable) {
                    alert("elementsName to disable : " + elementNamesToDisable);
                }
             );
}
