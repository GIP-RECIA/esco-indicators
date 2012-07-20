/************************************************
 *
 * Author : Frapin Kevin <kevin.frapin@recia.fr>
 * Date : 2012/06/15
 *
 * **********************************************
 *
 * Description :
 * -------------
 *  File containing the common function used in
 *  others scripts.
 *
 * **********************************************
 */

///////////////////////////////////////////////////////////
// FUNCTIONS
///////////////////////////////////////////////////////////
$.ajaxSetup({"error":function(XMLHttpRequest,textStatus, errorThrown) {
    alert(textStatus);
    alert(errorThrown);
    alert(XMLHttpRequest.responseText);
}});

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
function changeElementPropertyByValue(elementValue, property, propertyValue) {
    $('[value="' + elementValue + '"]').prop(property, propertyValue);
}

/**
 * Function that changes the property of an input element retrieved by its id
 */
function changeInputPropertyById(elementId, property, propertyValue) {
    $('input[id="' + elementId + '"]').prop(property, propertyValue);
}

/**
 * Function that retrieved the values of the checked elements of the form.
 * The values of the checked elements are put into a string
 * respecting this format :
 *  {ELEMENT_VALUE1}{SEPARATOR}{ELEMENT_VALUE2}...
 *
 * Parameters:
 *  - formID : The id of the form containing the elements.
 *
 * Return:
 *      a string containing the values of the checked elements.
 */
function checkedElementsValues(formID) {
    var checkedElements = $("#" + formID + " input:checked");
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
        changeElementPropertyByValue(elementValues[i], "checked", true);
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
        changeElementPropertyByValue(elementValues[i], "disabled", true);
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
        changeElementPropertyByValue(elementValues[i], "disabled", false);
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
 * Parameters:
 *  - formID : The id of the form containing the elements.
 *
 * Return:
 *      a string containing the values of the selected elements.
 */
function selectedElementsValues(formID) {
    var selectedElements = $("#" + formID + " option:selected");
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
        changeElementPropertyByValue(elementValues[i], "checked", false);
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

