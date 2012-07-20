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

    // //////////////////////////////////////////////////////////////
    // When an element is drop into the list of wanted elements
    // change his name to 'wantedServices'
    // //////////////////////////////////////////////////////////////
    $(".connectedServices").droppable({
        drop: function(event, ui) { 
                  var listId = $(this).prop("id");
                  if(listId == WANTED_SERVICES_ID) {
                    dropWantedServiceHandler($(this), event, ui); 
                  }
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
 * Function that create the depencies between the hidden inputs.
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
    // Sort this list
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
 *Function that sort the keys, and the values, contained in an array.
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
 * Function that removes all the occurences of the target string
 * that are present into the original string.
 */
function stringRemove(originalString, targetString) {
    return originalString.replace(targetString, "");
}
