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
// JQUERY : DOCUMENT READY FUNCTION
///////////////////////////////////////////////////////////
$(document).ready(function() {
    // //////////////////////////////////////////////////////////////
    // The readonly inputs should not be modified by the user
    // //////////////////////////////////////////////////////////////
    $("input").click(function(event) {
        if($(this).prop("readonly")) {
            event.preventDefault();
        }   
    });

    // //////////////////////////////////////////////////////////////
    // When the LEN_INPUT becomes checked (resp. unchecked), its
    // dependencies inputs become checked (resp. unchecked).  
    // //////////////////////////////////////////////////////////////
    $("[value='" + LEN_INPUT.name + "']").change(function() {
        var element = $("[value='" + LEN_INPUT.name + "']");
        changeCheckedProperty(element.prop("checked"), LEN_INPUT.dependencies);
    });

    // //////////////////////////////////////////////////////////////
    // When the LA_INPUT becomes checked (resp. unchecked), its
    // dependencies inputs become checked (resp. unchecked).  
    // //////////////////////////////////////////////////////////////
    $("[value='" + LA_INPUT.name + "']").change(function() {
        var element = $("[value='" + LA_INPUT.name + "']");
        changeCheckedProperty(element.prop("checked"), LA_INPUT.dependencies);
    });

    // //////////////////////////////////////////////////////////////
    // When the CFA_INPUT becomes (un)checked :
    //  - the LEN_INPUT dependencies have to be updated with the
    //    "checked" property than the LEN_INPUT itself.
    //  - the LA_INPUT dependencies have to be updated with the
    //    "checked" property than the LA_INPUT itself.
    // //////////////////////////////////////////////////////////////
    $("[value='" + CFA_INPUT.name + "']").change(function() {
		  var element = $("[value='" + LEN_INPUT.name + "']");
		  changeCheckedProperty(element.prop("checked"), LEN_INPUT.dependencies)    
		  var element = $("[value='" + LA_INPUT.name + "']");
		  changeCheckedProperty(element.prop("checked"), LA_INPUT.dependencies)    
    });


    // //////////////////////////////////////////////////////////////
    // When the SUM_ON_COUNTIES_INPUT is checked, its dependency
    // is selected.
    // //////////////////////////////////////////////////////////////
    $("[value='" + SUM_ON_COUNTIES_INPUT.name + "']").change(function() {
        changeElementPropertyByValue(SUM_ON_COUNTIES_INPUT.dependency, "selected", true) 
    });

    ///////////////////////////////////////////////////////
    // When the MONITORING_ATTENDANCE is checked,
    // only one user profile can be selected
    ///////////////////////////////////////////////////////
    // When MONITORING_ATTENDANCE becomes checked, unchecks all users profiles 
    $("[value='" + MONITORING_ATTENDANCE.name + "']").change(function() {
        if($(this).prop("checked")) {
            uncheckElementsByValues(USERS_PROFILES.values);
        }
    });

    ///////////////////////////////////////////////////////
    // Select / Unselect all establishments
    ///////////////////////////////////////////////////////
	$("#toggleEstablishmentsSelection").click(function() {
		var inputs = $("#establishmentsList").find("tr").has("td").find("input");
		var checkedState = $(this).prop("checked");
		toggleInputsStates(inputs,checkedState);
	});

    ///////////////////////////////////////////////////////
    // When the form is sumbitted : hides sumbit buttons
	// and show spinner
    ///////////////////////////////////////////////////////
	$('form').submit(function() {
		$(this).find(":submit").hide();	
		var spinner = new Spinner(SPINNER_OPTS).spin();
		$(this).find(":submit").after(spinner.el);	
	});

    ///////////////////////////////////////////////////////
    // Initialization of the form state
    ///////////////////////////////////////////////////////
    initializeForm();	
}); 	
    	
    	

///////////////////////////////////////////////////////////
// FUNCTIONS
///////////////////////////////////////////////////////////

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
    toAppend += '<input id="' + establishment.value + '" type="checkbox" value="' + establishment.value + '" name="' + establishment.path + '">';
    toAppend += '</td>';
    toAppend += '<td>';
    toAppend += '<label for="' + establishment.value + '">' + establishment.label + '</label>';
    toAppend += '</td>';
    toAppend += '</tr>';

    // Appends the string to the table
    $("#" + ESTABLISHMENTS_TABLE_ID).find("tbody").append(toAppend);

	// Indicates that the table has been updated
	$("#" + ESTABLISHMENTS_TABLE_ID).trigger("update");
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
        changeElementPropertyByValue(elementValues[i], "readonly", true);
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
        changeElementPropertyByValue(elementValues[i], "readonly", false);
    }
}


/**
 * Function that initializes the form when is loaded.
 */
function initializeForm() {
    // If no attendance type is already checked
    if(!isChecked(ATTENDANCE.name) && !isChecked(MONITORING_ATTENDANCE.name)) {
        // Ckecks the attendance type
        changeElementPropertyByValue(ATTENDANCE.name, "checked", true);
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
 * Change the checked state of the given inputs.
 */
function toggleInputsStates(inputs,checkedState) {
	inputs.each(function() {
		$(this).prop("checked",checkedState);
	});	
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
    $("#" + ESTABLISHMENTS_TABLE_ID).find("tr").has("td").remove();

    // Update the list
    for(var i = 0; i < establishments_list.length; i++) {
       // Only display establishments having a label
       var establishment = establishments_list[i];
       if(establishment.label != "") {
           appendEstablishment(establishment);
       }
    }
}

