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
var SEPARATOR   = ";";

var LEN_INPUT           = new Object( );
LEN_INPUT.name          = "establishmentType.LEN";
LEN_INPUT.dependencies  = new Array("lyceesTypes.LP", "lyceesTypes.LEGT", "lyceesTypes.EREA");

var LA_INPUT            = new Object( );
LA_INPUT.name           = "establishmentType.LA";
LA_INPUT.dependencies   = new Array("laTypes.LEGTA", "laTypes.LPA");

var CFA_INPUT           = new Object( );
CFA_INPUT.name          = "establishmentType.CFA";

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
    // When the LEN_INPUT becomes checked (resp. unchecked), its
    // dependencies inputs become checked (resp. unchecked).  
    // //////////////////////////////////////////////////////////////
    $("[value='" + LEN_INPUT.name + "']").change(function() {
        changeCheckedProperty($(this).prop("checked"), LEN_INPUT.dependencies)    
    });

    // //////////////////////////////////////////////////////////////
    // When the LA_INPUT becomes checked (resp. unchecked), its
    // dependencies inputs become checked (resp. unchecked).  
    // //////////////////////////////////////////////////////////////
    $("[value='" + LA_INPUT.name + "']").change(function() {
        changeCheckedProperty($(this).prop("checked"), LA_INPUT.dependencies)    
    });

    // //////////////////////////////////////////////////////////////
    // When the CFA_INPUT becomes checked :
    //  - the LEN_INPUT dependencies have to be updated with the
    //    "checked" property than the LEN_INPUT itself.
    //  - the LA_INPUT dependencies have to be updated with the
    //    "checked" property than the LA_INPUT itself.
    // //////////////////////////////////////////////////////////////
    $("[value='" + CFA_INPUT.name + "']").change(function() {
        // Update of the LEN_INPUT dependencies
        var element = $("[value='" + LEN_INPUT.name + "']");
        changeCheckedProperty(element.prop("checked"), LEN_INPUT.dependencies)    
        // Update of the LA_INPUT dependencies
        var element = $("[value='" + LA_INPUT.name + "']");
        changeCheckedProperty(element.prop("checked"), LA_INPUT.dependencies)    
    });


    // //////////////////////////////////////////////////////////////
    // When an input is checked, the values of the checked inputs are 
    // send to the server in order to know what inputs have to be 
    // disabled into the form.
    // //////////////////////////////////////////////////////////////
    $('.submit').change(function() {
        // 1. Some elements are submitted in order to update the form
        updateForm();
        // 2. The form has been updated and can be submitted in order to
        //    refresh the establishments list
		updateEstablishments();
    });

});

/**
 * Function that change the "checked" property of elements (referenced by their values).
 * The "checked" property of the elements is set equal to the haveToBeChecked
 * parameter.
 */ 
function changeCheckedProperty(haveToBeChecked, elementValues) {
        if(haveToBeChecked) {
            checkElementsByValues(elementValues);
        } else {
            uncheckElementsByValues(elementValues);
        }
        
    }
/** 
 * Function that changes the property of an element retrieved by its value.
 */
function changeElementProperty(elementValue, property, propertyValue) {
    $('[value="' + elementValue + '"]').prop(property, propertyValue);
}

/**
 * Function that retrieved the values of the checked elements of the form.
 * The values of the checked elements are put into a string
 * respecting this format :
 *  {ELEMENT_VALUE1}{SEPARATOR}{ELEMENT_VALUE2}...
 *
 * Return:
 *      a string containing the values of the checked elements.
 */
function checkedElementsValues() {
    var checkedElements = $("#accountactivationform input:checked");
    var checked = new String( );

    // Retrieval of the values of the checked elements
    for(var i=0; i < checkedElements.length ; i++) {
        checked += checkedElements[i].value + SEPARATOR; 
    }

    return checked;
}

/**
 * Checkes HTML elements by value.
 * All the elements having a value contained in the 'elementValues' list
 * will be checked by the function.
 */
function checkElementsByValues(elementValues) {
    // Check elements one by one
    for(var i = 0; i < elementValues.length; i++) {
        changeElementProperty(elementValues[i], "checked", true);
    }
}

/**
 * Disables HTML elements by value.
 * All the elements having a value contained in the 'elementValues' list
 * will be disabled by the function.
 */
function disableElementsByValues(elementValues) {
    // Disable elements one by one
    for(var i = 0; i < elementValues.length; i++) {
        changeElementProperty(elementValues[i], "disabled", true);
    }
}

/**
 * Enables HTML elements by value.
 * All the elements having a value contained in the 'elementValues' list
 * will be enabled by the function.
 */
function enableElementsByValues(elementValues) {
    // Disable elements one by one
    for(var i = 0; i < elementValues.length; i++) {
        changeElementProperty(elementValues[i], "disabled", false);
    }
}

/**
 * Function that retrieved the values of the selected elements of the form.
 * The values of the selected elements are put into a string
 * respecting this format :
 *  {ELEMENT_VALUE1}{SEPARATOR}{ELEMENT_VALUE2}...
 *
 * Return:
 *      a string containing the values of the selected elements.
 */
function selectedElementsValues() {
    var selectedElements = $("#accountactivationform option:selected");
    var selected = new String( );

    for(var i=0; i < selectedElements.length ; i++) {
        selected += selectedElements[i].value + SEPARATOR; 
    }

    return selected;
}

/**
 * Uncheckes HTML elements by value.
 * All the elements having a value contained in the 'elementValues' list
 * will be unchecked by the function.
 */
function uncheckElementsByValues(elementValues) {
    // Uncheck elements one by one
    for(var i = 0; i < elementValues.length; i++) {
        changeElementProperty(elementValues[i], "checked", false);
    }
}

///////////////////////////////////////////////////////////
// AJAX FUNCTIONS
///////////////////////////////////////////////////////////
function updateForm() {
    // Retrieval of the values of the checked elements
    var checkedValues = checkedElementsValues();

    // Data for the request
    $.post(  "accounts-activations-ajax/update-form", 
                { 
                    checkedJspKeys : checkedValues
                }, 
                function(formState) {
                    enableElementsByValues(formState.to_enable);
                    disableElementsByValues(formState.to_disable);
                }
             );
}

function updateEstablishments() {
    // Retrieval of the values of the checked elements
    var checkedValues = checkedElementsValues();

    // Retrieval of the values of the selected elements
    var selectedValues = selectedElementsValues();

    // Data for the request
    $.post(  "accounts-activations-ajax/update-establishments", 
                { 
                    checkedJspKeys : checkedValues,
                    selectedJspKeys: selectedValues
                }, 
                function(formState) {
                    alert("establishments : " + formState.establishments_list);
                }
             );
}
