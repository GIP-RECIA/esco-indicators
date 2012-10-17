/************************************************
 *
 * Author : Frapin Kevin <kevin.frapin@recia.fr>
 * Date : 2012/10/10
 *
 * **********************************************
 *
 * Description :
 * -------------
 *  File containing the function used to transform
 *  an HTML table to a CSV file.
 *
 * This jQuery plugin is mainly based on :
 *  www.kunalbabre.com/projects/table2CSV.php
 *
 * **********************************************
 */


///////////////////////////////////////////////////////////
// JQUERY : FUNCTION DECLARATION
///////////////////////////////////////////////////////////
jQuery.fn.table2CSV = function(options) {
    var options = jQuery.extend({
        separator: ',',
        header: [],
    },
    options);

	////////////////////////////
	// Variables declarations
	////////////////////////////
    // Arrays for header and data
    var csvData = [];
    var headerArr = [];
    // Current element
    var element = this;

	////////////////////////////
	// Headers retrieval
	////////////////////////////
	// Construction of the CSV headers lines
	$(element).find('tr').each(function() {
		var tmpRow = []; 
		// Gets the visible 'th' of the row
		var row_th = $(this).find('th').filter(':visible');
		row_th.each(function() {
			if ($(this).css('display') != 'none') tmpRow[tmpRow.length] = formatData($(this).html());
            // Add empty value when the 'th' has a defined colspan attribute
			var colspan = ($(this).attr('colspan') ? $(this).attr('colspan') : 0);
			for(var i = 1; i < colspan; i++) {
				tmpRow[tmpRow.length] = formatData("");
			}
		});
        row2CSV(tmpRow);
	});
	

	////////////////////////////
	// Data retrieval
	////////////////////////////
    // Construction of the CSV data lines
    $(element).find('tr').each(function() {
        var tmpRow = [];
		// Gets the visible 'td' of the row
        $(this).find('td').filter(':visible').each(function() {
			var colspan = ($(this).attr('colspan') ? $(this).attr('colspan') : 0);
			tmpRow[tmpRow.length] = formatData($(this).html());
			for(var i = 0; i < colspan; i++) {
				tmpRow[tmpRow.length] = formatData("");
			}
        });
        row2CSV(tmpRow);
    });

    
	////////////////////////////
	// Final result
	////////////////////////////
    var mydata = csvData.join('\n');
    return mydata;

	////////////////////////////////////////////////
	// FUNCTIONS
	////////////////////////////////////////////////
    /*
     * Function that transforms an array to a CSV line.
     * The result line is added to the csvData array.
     *
     * Parameters :
     *  - row : The array to convert into CSV line      
     *
     */
    function row2CSV(row) {
        var tmp = row.join('') // to remove any blank rows
        if (row.length > 0 && tmp != '') {
            var mystr = row.join(options.separator);
            csvData[csvData.length] = mystr;
        }
    }


    /*
     * Function that cleans the input data.
     */
    function cleanData(input) {
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
     * Function that formats the given input data.
     * 
     * Parameters :
     *  - input : The input string to format.
     *
     *  Return :
     *   The formatted input string.
     */
    function formatData(input) {
        ///////////////////////////////
        // String cleaning
        ///////////////////////////////
        var output = cleanString(input);

        ///////////////////////////////
        // String quoting
        ///////////////////////////////
        if (output == "") {
            return '';
        }
        return '"' + output + '"';
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

    function popup(data) {
        var generator = window.open('', 'csv', 'height=400,width=600');
        generator.document.write('<html><head><title>CSV</title>');
        generator.document.write('</head><body >');
        generator.document.write('<textArea cols=70 rows=15 wrap="off" >');
        generator.document.write(data);
        generator.document.write('</textArea>');
        generator.document.write('</body></html>');
        generator.document.close();
        return true;
    }
};
