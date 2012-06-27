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
var SEPARATOR               = ";";


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
        clearForm($(this));
        //updateForm();
				updateEstablishments();
    });

});

/**
 * Clears the fom when an input has been changed.
 * This is done in order to have a consistent form to send to the server.
 *
 * For instance, when a monitoring type is selected, all others inputs are reset.
 */
function clearForm(elementEvent) {
   // TODO : Implementation needed !
}
 
/**
 * Disables HTML elements by value.
 * All the elements having a value contained in the 'elementValues' list
 * will be disabled by the function.
 */
function disableElementsByValues(elementValues) {
    // Disable elements one by one
    for(var i = 0; i < elementValues.length; i++) {
        disableElementByValue(elementValues[i]);
    }
}

/**
 * Disables HTML elements by value.
 * All the elements having the specified 'elementValue'
 * will be disabled by the function.
 */
function disableElementByValue(elementValue) {
    $('[value="' + elementValue + '"]').prop("disabled", true);
}

/**
 * Enables HTML elements by value.
 * All the elements having a value contained in the 'elementValues' list
 * will be enabled by the function.
 */
function enableElementsByValues(elementValues) {
    // Disable elements one by one
    for(var i = 0; i < elementValues.length; i++) {
        enableElementByValue(elementValues[i]);
    }
}

/**
 * Enables HTML elements by value.
 * All the elements having the specified 'elementValue'
 * will be enabled by the function.
 */
function enableElementByValue(elementValue) {
    $('[value="' + elementValue + '"]').prop("disabled", false);
}

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
                function(formState) {
                    alert("elementsValues to disable : " + formState.to_disable);
                    alert("elementsValues to enable : " + formState.to_enable);
                    enableElementsByValues(formState.to_enable);
                    disableElementsByValues(formState.to_disable);
                }
             );
}

function updateEstablishments() {
    // Retrieval of the values of the checked elements
    var checkedElements = $("#accountactivationform input:checked");
    var checked = new String( );

    for(var i=0; i < checkedElements.length ; i++) {
        checked += checkedElements[i].value + SEPARATOR; 
    }

    // Retrieval of the values of the selected elements
    var selectedElements = $("#accountactivationform option:selected");
    var selected = new String( );

    for(var i=0; i < selectedElements.length ; i++) {
        selected += selectedElements[i].value + SEPARATOR; 
    }

    // Data for the request
    request = { 'checkedValues[]': checked }
    $.post(  "accounts-activations-ajax/update-establishments", 
                { 
                    checkedJspKeys : checked,
                    selectedJspKeys: selected
                }, 
                function(formState) {
                    alert("establishments : " + formState.establishments_list);
                }
             );
}
