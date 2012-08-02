/************************************************
 *
 * Author : Frapin Kevin <kevin.frapin@recia.fr>
 * Date : 2012/06/28
 *
 * **********************************************
 *
 * Description :
 * -------------
 * File containing the functions linked to the
 * date picker used into the web pages.
 *
 * **********************************************
 */


///////////////////////////////////////////////////////////
// INITIALIZATION FUNCTIONS
///////////////////////////////////////////////////////////

/**
 * Function that initialized the date pickers.
 * Some rules defined here :
 *  - A user can only select a month, if the CFA_INPUT is not checked.
 *  - A user can only select a week, if the CFA_INPUT is the only establishment type selected.
 *  - The end date has to be greater than the start date.
 */
$(document).ready(function() {
    ///////////////////////////////////////////////////////
    // Initialization of the date picker for the start date
    ///////////////////////////////////////////////////////
    $("#startDatePicker").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'MM yy',
        onClose: function(dateText, inst) {
                var year = getSelectedYear();
                var month = getSelectedMonth();
                var day = getAppropriateDay($(this));
                // Update hidden value
                var monthStr = new String( parseInt(month) + 1 );
                $("#startDate").val(formatDate(day,monthStr,year));
                // Update the date picker calendar
                $(this).datepicker("setDate", new Date(year,month,day));
                // Force minimum end date
                forceMinimumEndDate(year, month, day);
            }
    });
    
    ///////////////////////////////////////////////////////
    // Initialization of the date picker for the end date
    ///////////////////////////////////////////////////////
    $("#endDatePicker").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'MM yy',
        onClose: function(dateText, inst) {
                var year = getSelectedYear();
                var month = getSelectedMonth();
                var day = getAppropriateDay($(this));
                // Update hidden value
                var monthStr = new String( parseInt(month) + 1 );
                $("#endDate").val(formatDate(day,monthStr,year));
                // Update the date picker calendar
                $(this).datepicker("setDate", new Date(year,month,day));
            }
    });

    ///////////////////////////////////////////////////////
    // Toggle of the end date picker visibility
    ///////////////////////////////////////////////////////
    $("[value='" + MONITORING_ATTENDANCE.name + "']").change(function() {
        // If the monitoring attendance type has been selected
        if(isChecked(MONITORING_ATTENDANCE.name)) {
            $("#endDatePicker").show();
        } else {
            $("#endDatePicker").hide();
        }
    });
    $("[value='" + MONITORING_ATTENDANCE.name + "']").change();


    $("[value='" + ATTENDANCE.name + "']").change(function() {
        // If the attendance type has been selected
        if(isChecked(ATTENDANCE.name)) {
            $("#endDatePicker").hide();
        } else {
            $("#endDatePicker").show();
        }
    });

    ///////////////////////////////////////////////////////
    // Toggle of the format in the date inputs
    ///////////////////////////////////////////////////////
    $("[name='" + ESTAB_TYPES.name + "']").change(function() {
        // If the only selected type is : CFA
        if(onlyCfaInputChecked()) {
            $("#startDatePicker").datepicker("option", "dateFormat", 'dd MM yy');
            $("#endDatePicker").datepicker("option", "dateFormat", 'dd MM yy');
        } else {
            $("#startDatePicker").datepicker("option", "dateFormat", 'MM yy');
            $("#endDatePicker").datepicker("option", "dateFormat", 'MM yy');
        }
    });
        
    ///////////////////////////////////////////////////////
    // Call of the change method for properly intializing
    // the date formats
    ///////////////////////////////////////////////////////
    $("[name='" + ESTAB_TYPES.name + "']").change();

});



///////////////////////////////////////////////////////////
// FUNCTIONS
///////////////////////////////////////////////////////////

function forceMinimumEndDate(year, month, day) {
    $("#endDatePicker").datepicker("option", "minDate", new Date(year, month, day));
}

/**
 * Function that gets the day that is expected.
 * If the CFA input is selected alone, the expected day is the first of the selected week.
 * Else the expected day is the first of the selected month.
 */
function getAppropriateDay(datePicker) {
    if(onlyCfaInputChecked()) {
        return getFirstWeekDay(datePicker);
    } 
    return "01";
}

/**
 * Function that gets the first day of the week.
 * The first is considered to be Monday.
 *
 * For instance :
 *  If the selected day is : Friday 14
 *  The function will return the day corresponding of the Monday
 *  of this week : 10 
 */
function getFirstWeekDay(datePicker) {
    // Get the day of the month [1-31]
    var day = datePicker.datepicker("getDate").getDate();
    // Get the day in the week [0-6] (Monday = 1)
    var dayWeek = datePicker.datepicker("getDate").getDay();
    if(dayWeek > 1) {
        // Get the date of the monday of the selected week
        day = day - (dayWeek - 1);
    } else if (dayWeek == 0) {
       // Get the date of the monday of the previous week
       day = day - 6; 
    }
    return day;
}

/**
 * Gets the selected month in the current date picker.
 * Range : [0-11]
 */
function getSelectedMonth() {
    return $("#ui-datepicker-div .ui-datepicker-month :selected").val();
}

/**
 * Gets the selected year in the current date picker.
 */
function getSelectedYear() {
    return $("#ui-datepicker-div .ui-datepicker-year :selected").val();
}


/**
 * Function that format the date in dd/MM/yyyy format.
 */
function formatDate(day, month, year) {
    return day + "/" + month + "/" + year;
}

/**
 * Function that indicates if only CFA_INPUT is checked
 */
function onlyCfaInputChecked() {
    return      isChecked(CFA_INPUT.name)
            && !isChecked(COLL_INPUT.name)
            && !isChecked(LEN_INPUT.name)
            && !isChecked(LA_INPUT.name);
}
