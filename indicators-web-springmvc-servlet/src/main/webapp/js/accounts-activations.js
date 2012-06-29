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
var ESTABLISHMENTS_TABLE_ID = "establishmentsList";

var CFA_INPUT               = new Object( );
CFA_INPUT.name              = "establishmentType.CFA";

var COLL_INPUT              = new Object( );
COLL_INPUT.name             = "establishmentType.COLL";

var LEN_INPUT               = new Object( );
LEN_INPUT.name              = "establishmentType.LEN";
LEN_INPUT.dependencies      = new Array("lyceesTypes.LP", "lyceesTypes.LEGT", "lyceesTypes.EREA");

var LA_INPUT                = new Object( );
LA_INPUT.name               = "establishmentType.LA";
LA_INPUT.dependencies       = new Array("laTypes.LEGTA", "laTypes.LPA");

var SUM_ON_COUNTIES_INPUT           = new Object( );
SUM_ON_COUNTIES_INPUT.name          = "sumOnCounties.DEFAULT";
SUM_ON_COUNTIES_INPUT.dependency    = "county.COUNTY_ALL";

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
        if(!isChecked(CFA_INPUT.name)) {
            changeCheckedProperty($(this).prop("checked"), LEN_INPUT.dependencies);
        }
    });

    // //////////////////////////////////////////////////////////////
    // When the LA_INPUT becomes checked (resp. unchecked), its
    // dependencies inputs become checked (resp. unchecked).  
    // //////////////////////////////////////////////////////////////
    $("[value='" + LA_INPUT.name + "']").change(function() {
        if(!isChecked(CFA_INPUT.name)) {
            changeCheckedProperty($(this).prop("checked"), LA_INPUT.dependencies);
        }
    });

    // //////////////////////////////////////////////////////////////
    // When the CFA_INPUT becomes checked :
    //  - the LEN_INPUT dependencies have to be updated with the
    //    "checked" property set to false.
    //  - the LA_INPUT dependencies have to be updated with the
    //    "checked" property set to false.
    //
    // When the CFA_INPUT becomes unchecked :
    //  - the LEN_INPUT dependencies have to be updated with the
    //    "checked" property than the LEN_INPUT itself.
    //  - the LA_INPUT dependencies have to be updated with the
    //    "checked" property than the LA_INPUT itself.
    // //////////////////////////////////////////////////////////////
    $("[value='" + CFA_INPUT.name + "']").change(function() {
        if($(this).prop("checked")) {
            changeCheckedProperty(false, LEN_INPUT.dependencies)    
            changeCheckedProperty(false, LA_INPUT.dependencies)    
        } else {
            var element = $("[value='" + LEN_INPUT.name + "']");
            changeCheckedProperty(element.prop("checked"), LEN_INPUT.dependencies)    
            var element = $("[value='" + LA_INPUT.name + "']");
            changeCheckedProperty(element.prop("checked"), LA_INPUT.dependencies)    
        }
    });


    // //////////////////////////////////////////////////////////////
    // When the SUM_ON_COUNTIES_INPUT is checked, its dependency
    // is selected.
    // //////////////////////////////////////////////////////////////
    $("[value='" + SUM_ON_COUNTIES_INPUT.name + "']").change(function() {
        changeElementProperty(SUM_ON_COUNTIES_INPUT.dependency, "selected", true) 
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

    // //////////////////////////////////////////////////////////////
    // Update of the form if the page has been reloaded or the
    // form has been submitted with errors.
    // Moreover the establishements list is reloaded.
    // //////////////////////////////////////////////////////////////
    updateForm();
    updateEstablishments();
});


/**
 * Function that appends an establishment into the establishments
 * table of the form.
 * Each establishment contains :
 *  - A label for displaying the name of the establishment
 *  - A value for setting the value attribute of the establishement
 *  - A path for setting the name attribute of the establishement
 */
function appendEstablishment(establishment) {
    // String to append to the table
    var toAppend = '<tr>';
    toAppend += '<td>';
    toAppend += '<input id="' + establishment.value + '" type="checkbox" value="' + establishment.value + '" + name="' + establishment.path + '">';
    toAppend += '</td>';
    toAppend += '<td>';
    toAppend += '<label for="' + establishment.value + '">' + establishment.label + '</label>';
    toAppend += '</td>';
    toAppend += '</tr>';

    // Appends the string to the table
    $("#" + ESTABLISHMENTS_TABLE_ID).append(toAppend);
}

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
 * Function that indicates if an element (finds by its value) is checked.
 */
function isChecked(elementValue) {
    return $("[value='" + elementValue + "']").prop("checked");
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
 * Function that retrieved the values of some special elements even if they are not checked.
 * The values of these checked elements are put into a string
 * respecting this format :
 *  {ELEMENT_VALUE1}{SEPARATOR}{ELEMENT_VALUE2}...
 *
 * These elements are considered as checked in order to get the expected establishements list.
 *
 * Return:
 *      a string containing the values of some special elements.
 */
function specialCheckedElementsValues() {
    // Final result
    var elementsValues = "";

    // If the CFA_INPUT is not checked
    if(!isChecked(CFA_INPUT.name)) {
        return elementsValues;
    }

    // If the LEN_INPUT is checked
    if(isChecked(LEN_INPUT.name)) {
        // The dependencies of the LEN_INPUT must be considered as checked
        for(var i = 0; i < LEN_INPUT.dependencies.length; i++) {
            elementsValues += LEN_INPUT.dependencies[i] + SEPARATOR;
        }
    }

    // If the LA_INPUT is checked
    if(isChecked(LA_INPUT.name)) {
        // The dependencies of the LA_INPUT must be considered as checked
        for(var i = 0; i < LA_INPUT.dependencies.length; i++) {
            elementsValues += LA_INPUT.dependencies[i] + SEPARATOR;
        }
    }

    return elementsValues;
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



/**
 * Update the establishments list of the form with the new specified list.
 * This list contains establishments containing :
 *  - A label for disaplying the name of the establishment
 *  - A value for setting the value attribute of the establishement
 *  - A path for setting the name attribute of the establishement
 */
function updateEstablishmentsList(establishments_list) {
    // Clear the current establishments list
    $("#" + ESTABLISHMENTS_TABLE_ID + " tr").remove();

    // Update the list
    for(var i = 0; i < establishments_list.length; i++) {
       appendEstablishment(establishments_list[i]);
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

    // Retrieval of the values of the 'special' elements
    checkedValues += specialCheckedElementsValues();

    // Retrieval of the values of the selected elements
    var selectedValues = selectedElementsValues();


    // Data for the request
    $.post(  "accounts-activations-ajax/update-establishments", 
                { 
                    checkedJspKeys : checkedValues,
                    selectedJspKeys: selectedValues
                }, 
                function(response) {
                    updateEstablishmentsList(response.establishments_list);
                }
             );
}
