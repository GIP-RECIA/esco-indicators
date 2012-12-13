/**************************************
* Script used to make the tables
* sortable.
**************************************/
$(document).ready(function() {
	$("#" + ESTABLISHMENTS_TABLE_ID).tablesorter({
		headers: {
			0: {sorter: false}
		}	
	});
	$("#" + ESTABLISHMENTS_TABLE_ID).bind("sortEnd", function() {
		// Indicates an update
		$(this).trigger("update");
	});
});
