jQuery.fn.table2CSV = function(options) {
    var options = jQuery.extend({
        separator: ',',
        header: [],
        delivery: 'popup' // popup, value
    },
    options);

    var csvData = [];
    var headerArr = [];
    var el = this;

    //header
    var numCols = options.header.length;

	////////////////////////////////////////////////
	// Gets the headers
	////////////////////////////////////////////////
	// Construction of the CSV headers lines
	$(el).find('tr').each(function() {
		var tmpRow = []; 
		// Gets the visible <th> of the row
		var row_th = $(this).find('th').filter(':visible');
		row_th.each(function() {
			if ($(this).css('display') != 'none') tmpRow[tmpRow.length] = formatData($(this).html());
			var colspan = ($(this).attr('colspan') ? $(this).attr('colspan') : 0);
			for(var i = 1; i < colspan; i++) {
				tmpRow[tmpRow.length] = formatData("");
			}
		});
        row2CSV(tmpRow);
	});
	

    // actual data
    $(el).find('tr').each(function() {
        var tmpRow = [];
        $(this).filter(':visible').find('td').each(function() {
			var colspan = ($(this).attr('colspan') ? $(this).attr('colspan') : 0);
			tmpRow[tmpRow.length] = formatData($(this).html());
			for(var i = 0; i < colspan; i++) {
				tmpRow[tmpRow.length] = formatData("EMPTY");
			}
        });
        row2CSV(tmpRow);
    });
    if (options.delivery == 'popup') {
        var mydata = csvData.join('\n');
        return popup(mydata);
    } else {
        var mydata = csvData.join('\n');
        return mydata;
    }

    function row2CSV(tmpRow) {
        var tmp = tmpRow.join('') // to remove any blank rows
        // alert(tmp);
        if (tmpRow.length > 0 && tmp != '') {
            var mystr = tmpRow.join(options.separator);
            csvData[csvData.length] = mystr;
        }
    }
    function formatData(input) {
        // replace " with â€œ
        var regexp = new RegExp(/["]/g);
        var output = input.replace(regexp, "â€œ");
        //HTML
        var regexp = new RegExp(/\<[^\<]+\>/g);
        var output = output.replace(regexp, "");
		// Remove the \n character
		var regex = new RegExp(/\n/g);
		var output = output.replace(regex, "");
		// Remove the tab, spaces,... at the begginning
		var regex = new RegExp(/^(\s)+/g);
		var output = output.replace(regex, "");
		// Remove the tab, spaces,... at the end
		var regex = new RegExp(/(\s)+$/g);
		var output = output.replace(regex, "");
		// Remove the duplicate tab, spaces,... characters
		var regex = new RegExp(/(\s)+/g);
		var output = output.replace(regex, " ");
        if (output == "") return '';
        return '"' + output + '"';
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
