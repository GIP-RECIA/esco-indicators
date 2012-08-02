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
// VARIABLES
///////////////////////////////////////////////////////////
// Associative array :
//  - Key   : Master id
//  - Value : Array containing the slaves elements
var mastersAndSlaves = new Array();

// Associative array :
//  - Key   : Slave id
//  - Value : The master element
var slavesAndMasters = new Array();

// Flat array indicating the position of each items
// in the lists
var itemsIds = new Array();

///////////////////////////////////////////////////////////
// FUNCTIONS
///////////////////////////////////////////////////////////

$(document).ready(function() {
    // //////////////////////////////////////////////////////////////
    // Link the lists containing the available, and wanted, services
    // //////////////////////////////////////////////////////////////
    connectLists();


    // //////////////////////////////////////////////////////////////
    // Construct the depencies between the hidden available services.
    // In services there are masters, and slaves services.
    // A master service is a sum of its slaves.
    // //////////////////////////////////////////////////////////////
    createMastersAndSlaves();
    sortKeysAndValues(mastersAndSlaves);
    var mastersAndSlavesIds = keepValuesIds(mastersAndSlaves);
    itemsIds = flattenArray(mastersAndSlavesIds);
    sortList(AVAILABLE_SERVICES_ID);
    sortList(WANTED_SERVICES_ID);

    // //////////////////////////////////////////////////////////////
    // When an element is drop into the list of wanted elements
    // change his name to 'wantedServices'
    // //////////////////////////////////////////////////////////////
    $(".connectedServices").droppable({
        drop: function(event, ui) { 
                  // //////////////////////////////////////
                  // Handles the drop event
                  // //////////////////////////////////////
                  var listId = $(this).prop("id");
                  var newInputName;
                  var newDependencyName;

                  if(listId == WANTED_SERVICES_ID) {
                    dropWantedServiceHandler($(this), event, ui); 
                    newInputName        = WANTED_SERVICES_NAME;
                    newDependencyName   = AVAILABLE_SERVICES_NAME;
                  } else {
                    newInputName        = AVAILABLE_SERVICES_NAME;
                    newDependencyName   = WANTED_SERVICES_NAME;
                  }

                  // //////////////////////////////////////
                  // Update the associated hidden field
                  // //////////////////////////////////////
                  var hiddenId = ui.draggable.prop("id");
                  updateInputName(hiddenId, newInputName); 

                  // //////////////////////////////////////
                  // Update the associated master / slaves 
                  // //////////////////////////////////////
                  var slaves = mastersAndSlaves[hiddenId] ;
                  if(slaves) {
                    updateInputsName(slaves, newDependencyName);
                  }
                  var master = slavesAndMasters[hiddenId] ;
                  if(master) {
                    updateInputName($(master).prop("id"), newDependencyName);
                  }

                  // //////////////////////////////////////
                  // Sort the lists
                  // //////////////////////////////////////
                  sortList(AVAILABLE_SERVICES_ID);
                  sortList(WANTED_SERVICES_ID);
              }
    });
    
    // //////////////////////////////////////////////////////////////
    // When an input is checked, the values of the checked inputs are 
    // send to the server in order to know what inputs have to be 
    // disabled into the form.
    // //////////////////////////////////////////////////////////////
    $('.submit').change(function() {
        // 1. Some elements are submitted in order to update the form
        updateForm("serviceform", "services-ajax/update-form");
        // 2. The form has been updated and can be submitted in order to
        //    refresh the establishments list
		updateEstablishments("serviceform", "services-ajax/update-establishments");
    });

    // //////////////////////////////////////////////////////////////
    // Update of the form if the page has been reloaded or the
    // form has been submitted with errors.
    // Moreover the establishements list is reloaded.
    // //////////////////////////////////////////////////////////////
    updateForm("serviceform", "services-ajax/update-form");
    updateEstablishments("serviceform", "services-ajax/update-establishments");

    ///////////////////////////////////////////////////////
    // Only one user profile can be selected
    ///////////////////////////////////////////////////////
    $("[name='" + USERS_PROFILES.name + "']").change(function() {
        if($(this).prop("checked")) {
            uncheckElementsByValues(USERS_PROFILES.values);
            $(this).prop("checked", true);
        }
    });
});


/**
 * Function that appends elements to a list.
 */
function appendElementsToList(elements, listId) {
    var list = $("#" + listId);
    elements.each(function() {
        $(this).appendTo(list);
    });
}

/**
 * Function that connects the two lists containing the services.
 * When the lists are connected, elements can be drag and drop from one list to another.
 */
function connectLists() {
    $("#" + AVAILABLE_SERVICES_ID).sortable({
        connectWith: ".connectedServices"
    }).disableSelection();
    $("#" + WANTED_SERVICES_ID).sortable({
        connectWith: ".connectedServices"
    }).disableSelection(); 
}

/**
 * Function that creates the depencies between the hidden inputs.
 * Some inputs are considered as masters, while otheers inputs are considered as slaves.
 * A master input represents the sum of its slaves inputs.
 */
function createMastersAndSlaves() {
    // Get all the masters (inputs with id ending with 'SUM')
    var masters = $('li[id$="SUM"]');
    // Link the masters to their slaves
    masters.each(function() {
        var master = $(this);
        var prefix = stringRemove($(this).prop("id"), "SUM");
        var slaves = $('li[id^="' + prefix + '"]:not([id$="SUM"])');
        mastersAndSlaves[master.prop("id")] = slaves; 
        slaves.each(function() {
           slavesAndMasters[$(this).prop("id")] = master;
        });
    });

}


/**
 * Function that compares the content of two elements retrieved by their ids.
 */
function compareInputsContentByIds(firstElementId, secondElementId) {
    // Gets the contents
   var firstElement     = $('input[id="' + firstElementId + '"]'); 
   var secondelement    = $('input[id="' + secondElementId + '"]'); 
   return compareElementsContent(firstElement, secondElement);
}

/**
 * Function that compares the content of two elements.
 */
function compareElementsContent(firstElement, secondElement) {
    // Gets the contents
    var firstContent    = $(firstElement).prop("innerHTML");
    var secondContent   = $(secondElement).prop("innerHTML");
    return firstContent.localeCompare(secondContent);
}

/**
 * Function that handles the drop event on the wanted services list.
 */
function dropWantedServiceHandler(droppableList, event, ui) {
    // Gets the dragged element, his id and the dropped list
    var element     = ui.draggable;
    var elementId   = element.prop("id");

    ///////////////////////////////////////////////////////
    // If the dragged element is a master, all the slaves
    // are put into the list containing the available
    // services
    ///////////////////////////////////////////////////////
    if(mastersAndSlaves.hasOwnProperty(elementId)) {
        var slaves  = mastersAndSlaves[elementId];
        appendElementsToList(slaves, AVAILABLE_SERVICES_ID);
    } 
    ///////////////////////////////////////////////////////
    // If the dragged element is a slave, is allowed to
    // stay in the wanted services but the master has to be 
    // put into the list containing the available services
    ///////////////////////////////////////////////////////
    else {
        var master   = $(slavesAndMasters[elementId]);
        var masterId = master.prop("id");
        if(listContains(droppableList.prop("id"), masterId)) {
            appendElementsToList(master, AVAILABLE_SERVICES_ID);
        }
    }
}

/**
 * Function that put into a new list, all the elements
 * that are present in the sepcified list, and that are
 * before the specified element.
 */
function elementsBefore(list, element) {
    // Final result
    var elements = new Array( );

    // Append to the list all the elements before the searched one
    for(var i=0; 
        i < list.length && list[i] != element; 
        i++) 
    {
       elements.push(list[i]); 
    }

    return elements;
}

/**
 * Function that flattens an array.
 */
function flattenArray(array) {
    // Final result
    var flatArray = new Array();
    for(key in array) {
        flatArray.push(key);
        var values = array[key];
        $.each(values, function(index) {
            flatArray.push(values[index]);
        });
    }
    return flatArray;
}

/**
 * Function that inserts the list item associated to elementId
 * after the list items associated to the previousElementsIds.
 * All these items are manipulated insede the list associated to
 * the listId.
 */
function insertAfter(listId, elementId, previousElementsIds) {
    // Get the element to insert
    var element = $('#' + listId + ' li[id="' + elementId + '"]');

    // Insert the element after the first previous element found
    for(var i=previousElementsIds.length - 1; 
        i > 0;
        i--)
    {
        var previousId = previousElementsIds[i];
        var previousElement = $('#' + listId + ' li[id="' + previousId + '"]');
        if(previousElement) {
            previousElement.after(element);
            return;
        }
    }
    
    // If no previous element has been found
    $('#' + listId).prepend(element);
}

/**
 * Function that creates an associative array from an original associative array.
 * All the values contained in the original array represent html elements.
 * The ids of these elements are retrieved and put into arrays.
 * These arrays are finally associated to the same key present inthe original array.
 */
function keepValuesIds(array) {
    // Final result
    var idsArray = new Array();

    // Create an array only containing elements ids
    for(key in array) {
        var values      = array[key];
        var newValues   = new Array(); 
        $.each(values, function() {
           newValues.push($($(this)).prop("id")); 
        });
        idsArray[key] = newValues;
    }

    return idsArray;
}

/**
 * Function that indicates if a list contains an element (<li>) having the same id
 * as the specified one.
 */
function listContains(listId, elementId) {
    var retrievedElement = $('#' + listId + ' li[id="' + elementId + '"]');
    if(retrievedElement.prop("id")) {
        return true;
    }
    return false;
}

/**
 * Function that sort the keys, and the values, contained in an array.
 * The array keys are composed of : element ids.
 * The array values are composed of : elements.
 *
 * All the comparisons are made on the text content of the elements.
 */
function sortKeysAndValues(arrayToSort) {
    // Sort the keys by their content
    arrayToSort.sort(compareInputsContentByIds);

    // Sort the values
    for(key in arrayToSort) {
        var arrayValues = arrayToSort[key];
        arrayValues.sort(compareElementsContent);
        arrayToSort[key] = arrayValues;
    }
}

/**
 * Sorts the items of the list associated to the listId,
 * regarding to the order present in the itemsId list.
 */
function sortList(listId) {
    // Sort each item of the list
    for(var i=0; i<itemsIds.length; i++) {
        var id = itemsIds[i];
        var element = $('#' + listId + ' li[id="' + id + '"]');
        if(element) {
            $('#' + listId).append(element);
        }
    }
}

/**
 * Function that removes all the occurences of the target string
 * that are present into the original string.
 */
function stringRemove(originalString, targetString) {
    return originalString.replace(targetString, "");
}

/**
 * Function that updates the name of an input field.
 */
function updateInputName(inputId, newName) {
    var input = $('input[id="' + inputId + '"]');
    input.prop("name", newName);
}

/**
 * Function that updates the name of the inputs having the same
 * ids as the ones of the specified elements.
 */
function updateInputsName(elements, newName) {
    $.each(elements, function(index) {
        var inputId = $(elements[index]).prop("id");
        updateInputName(inputId, newName); 
    });
}
