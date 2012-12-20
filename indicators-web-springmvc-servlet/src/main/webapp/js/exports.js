/************************************************
 *
 * Author : Frapin Kevin <kevin.frapin@recia.fr>
 * Date : 2012/17/0
 *
 * **********************************************
 *
 * Description :
 * -------------
 * File containing functions used to export 
 * table data into csv file.
 *
 * **********************************************
 */


///////////////////////////////////////////////////////////
// INITIALIZATION FUNCTIONS
///////////////////////////////////////////////////////////
$(document).ready(function() {
    ///////////////////////////////////////////////////////
    // Handle the submission of the form for CSV export
    ///////////////////////////////////////////////////////
    $("#csvexport").submit(function() {
       // Transforms the table into csv data
       var csvData = $("#resultTable").table2CSV();
       // Injects the csv data into the hidden field
       $("#csvData").prop("value", csvData);
       // Creates a file name regarding to the selected filters
       var fileName = createFileName("csv");
       // Injects the file name into the hidden field
       $("#csvFileName").prop("value", fileName);
    });
    ///////////////////////////////////////////////////////
    // Handle the submission of the form for Excel export
    ///////////////////////////////////////////////////////
    $("#excelexport").submit(function() {
       // Gets the HTML representation of the table
       var excelData = $("#resultTable").outerHTML();
       // Specifies the charset
       excelData = '<meta http-equiv=Content-Type content="text/html; charset=UTF-8">' + excelData;
       // Injects the HTML representation into the hidden field
       $("#excelData").prop("value", excelData);
       // Creates a file name regarding to the selected filters
       var fileName = createFileName("xls");
       // Injects the file name into the hidden field
       $("#excelFileName").prop("value", fileName);
			 // Displays warning message
			 $("#excelWarning").show("blind");
    });
});

///////////////////////////////////////////////////////////
// FUNCTIONS
///////////////////////////////////////////////////////////
/*
 * Functions that creates a file name regarding to the 
 * current section of the application.
 * The extension file name is given as parameter.
 */
function createFileName(fileExtension) {
    // Gets the second link of the ariadne thread 
    var fileName = "";
    $("#ariadneThread").find("a:odd").each(function() {
        // Clean the data
        var cleanData = cleanString($(this).html());
        // Add the filter content to the file name
        fileName = fileName + cleanData + " - ";
    });
    // Remove the last two characters
    fileName = fileName.substring(0,fileName.length - 3);
    return "Export - " + fileName + "." + fileExtension;
}

/*
 * Function that cleans the input data.
 */
function cleanString(input) {
    // Removes the HTML tag
    var regex  = new RegExp(/\<[^\<]+\>/g);
    var output = regexReplace(regex, input, "");
    // Removes the \n character
    var regex = new RegExp(/\n/g);
    var output = regexReplace(regex, output, "");
    // Removes the tab, spaces,... at the begginning
    var regex = new RegExp(/^(\s)+/g);
    var output = regexReplace(regex, output, "");
    // Removes the tab, spaces,... at the end
    var regex = new RegExp(/(\s)+$/g);
    var output = regexReplace(regex, output, "");
    // Removes the duplicate tab, spaces,... characters
    var regex = new RegExp(/(\s)+/g);
    var output = regexReplace(regex, output, " ");
    return output;
}

/*
 * Function that replaces the matching elements
 * by the replacement string into the input string.
 *
 * Parameters :
 *  - regex : The RegExp element to match
 *  - input : The input string to analyze
 *  - replacment : The replacement string for the matched elements
 *
 *  Return :
 *      The input string where the matched elements has been replaced
 *      by the replacement string.
 */
function regexReplace(regex, input, replacement) {
	return input.replace(regex, replacement);
}
