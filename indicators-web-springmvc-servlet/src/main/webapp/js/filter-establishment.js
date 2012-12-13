/************************************************
 *
 * Author : Frapin Kevin <kevin.frapin@recia.fr>
 * Date : 2012/12/13
 *
 * **********************************************
 *
 * Description :
 * -------------
 *  File containing the functions used to filter
 *  the content of a table.
 *
 * **********************************************
 */

///////////////////////////////////////////////////////////
// JQUERY : DOCUMENT READY FUNCTION
///////////////////////////////////////////////////////////
$(document).ready(function() {
    // //////////////////////////////////////////////////////////////
    // When the filterInput changed : filter the content of the table
    // //////////////////////////////////////////////////////////////
    $("#filterInput").keyup(function() {
		filterTableRowsByLabel("#" + ESTABLISHMENTS_TABLE_ID, $(this).val());
    });
}); 	
    	
    	

///////////////////////////////////////////////////////////
// FUNCTIONS
///////////////////////////////////////////////////////////

/**
 * Function that filters the content of a table.
 * A row is shown when it contains a label containing the given string to filter.
 * A row is hidden in the other cases.
 * The label and the string are compared without taking care of the case.
 */
function filterTableRowsByLabel(tableId, stringToFilter) {
	// Filters the rows
	$(tableId + " label:contains('" + stringToFilter + "')").parent().parent().show();
	$(tableId + " label:contains('" + stringToFilter + "')").parent().parent().addClass("visible");
	$(tableId + " label:not(:contains('" + stringToFilter + "'))").parent().parent().hide();
	$(tableId + " label:not(:contains('" + stringToFilter + "'))").parent().parent().removeClass("visible");

	// Indicates that the table has been updated
	$(tableId).trigger("update");
}
