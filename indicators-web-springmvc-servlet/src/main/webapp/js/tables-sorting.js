/**************************************
* Script used to make the tables
* sortable.
**************************************/
$(document).ready(function() {
	$("#establishmentsList").tablesorter({
		headers: {
			0: {sorter: false}
		}	
	});
});
