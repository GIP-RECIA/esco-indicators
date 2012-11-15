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
        showOn: "button",
        buttonImage: "img/calendar.png",
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        dateFormat: 'MM yy',
        beforeShow: function() {
            // Gets the selected date
            var selectedDate = $("#startDate").prop("value");
            // Update the date picker with the good date 
            if(selectedDate != "") {
                setDefaultDate($(this),selectedDate);
            }
        },
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
                // Show / Hide the endDatePicker
                $("[value='" + MONITORING_ATTENDANCE.name + "']").change();
            }
    });
    
    ///////////////////////////////////////////////////////
    // Initialization of the date picker for the end date
    ///////////////////////////////////////////////////////
    $("#endDatePicker").datepicker({
        showOn: "button",
        buttonImage: "img/calendar.png",
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true,
        dateFormat: 'MM yy',
        showButtonPanel: true,
        beforeShow: function() {
            // Gets the selected date
            var selectedDate = $("#endDate").prop("value");
            // Update the date picker with the good date 
            if(selectedDate != "") {
                setDefaultDate($(this),selectedDate);
            }
        },
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
            $("#endDatePicker").next("img").show();
        } else {
            $("#endDatePicker").hide();
            $("#endDatePicker").next("img").hide();
						$("#endDate").prop("value","");
        }
    });


    $("[value='" + ATTENDANCE.name + "']").change(function() {
        // If the attendance type has been selected
        if(isChecked(ATTENDANCE.name)) {
            $("#endDatePicker").hide();
            $("#endDatePicker").next("img").hide();
            $("#endDate").prop("value","");
        } else {
            $("#endDatePicker").show();
            $("#endDatePicker").next("img").show();
        }
    });

    ///////////////////////////////////////////////////////
    // Toggle of the format in the date inputs
    ///////////////////////////////////////////////////////
    $("[name='" + ESTAB_TYPES.name + "']").change(function() {
        // If the only selected type is : CFA
        if(onlyCfaInputChecked() ) {
            // If the date format needs to be updated
            if(!isDatePickersFormat('dd MM yy')) {
                $("#startDatePicker").datepicker("option", "dateFormat", 'dd MM yy');
                $("#endDatePicker").datepicker("option", "dateFormat", 'dd MM yy');
            }
            // Show the days when the calendar is selected
            showCalendars();
        } else if (!isDatePickersFormat('MM yy')) {
            $("#startDatePicker").datepicker("option", "dateFormat", 'MM yy');
            $("#endDatePicker").datepicker("option", "dateFormat", 'MM yy');
            // Hide the days when the calendar is selected
            hideCalendars();
        }
        // Hide / Show the calendar icon for the endDatePicker
        $("[value='" + MONITORING_ATTENDANCE.name + "']").change();
    });
        
    ///////////////////////////////////////////////////////
    // Initialization of the date pickers
    ///////////////////////////////////////////////////////
    initializeDatePickers();
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
    // Get the date
    var date = datePicker.datepicker("getDate");
    if(date == null) {
        return 1;
    }
    // Get the day of the month [1-31]
    var day = date.getDate();
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


/*
 * Function that forbids the calendar to be displayed.
 */
function hideCalendars() {
  $(".hasDatepicker").focus(function() {
        $(".ui-datepicker-calendar").hide();
  });
}
/**
 * Function that format the date in dd/MM/yyyy format.
 */
function formatDate(day, month, year) {
    return day + "/" + month + "/" + year;
}

/*
 * Function which extracts the day of a date string
 * in dd/MM/yyyy format.
 */
function getDay(date) {
    return date.split("/")[0];
}

/*
 * Function which extracts the month of a date string
 * in dd/MM/yyyy format.
 */
function getMonth(date) {
    return date.split("/")[1];
}

/*
 * Function which extracts the year of a date string
 * in dd/MM/yyyy format.
 */
function getYear(date) {
    return date.split("/")[2];
}

/**
 * Function that initializes the values of the date pickers.
 */
function initializeDatePickers() {
    // Hide the date pickers and show them regarding to the selected attendance type
    hideCalendars();
    $("[value='" + MONITORING_ATTENDANCE.name + "']").change();
    $("[name='" + ESTAB_TYPES.name + "']").change();
    
    // By default : select the date of the day
    if($("#startDatePicker").datepicker("getDate") == null) {
        var now = new Date();
        $("#startDatePicker").datepicker("setDate", now);
        $("#startDate").prop("value", $.datepicker.formatDate("dd/mm/yy", now));
        
        
    }   
}

/*
 * Indicates if the given format is the date format
 * used by all the date pickers present into the web page.
 */
function isDatePickersFormat(format) {
    // Assumes that the format is the one used by all the date pickers
    var isDatePickersFormat = true;
    // Gets all the date pickers
    $(".hasDatepicker").each(function() {
        if($(this).datepicker("option","dateFormat") != format) {
            isDatePickersFormat = false;
        } 
    });
    return isDatePickersFormat; 
}
/**
 * Function that indicates if only CFA_INPUT is checked
 */
function onlyCfaInputChecked() {
    // If we are on an establishment view
    var hiddenType = $("#establishmentsTypes[value='" + CFA_INPUT.name + "']");
    if(hiddenType.size() > 0) {
        return true;
    }   
    // if we are on a super user view
    return      isChecked(CFA_INPUT.name)
            && !isChecked(COLL_INPUT.name)
            && !isChecked(LEN_INPUT.name)
            && !isChecked(LA_INPUT.name);
}

/*
 * Function that allows the calendar to be displayed.
 */
function showCalendars() {
  $(".hasDatepicker").focus(function() {
        $(".ui-datepicker-calendar").show();
  });
}


/*
 * Function which sets the default date of the given date picker.
 * The provided date string respects the dd/MM/yyyy format.
 */
function setDefaultDate(datepicker, date) {
  var year = getYear(date);
  var month = getMonth(date);
  var day = getDay(date);
  // Sets the string date as the default date
  var date = new Date(year,month - 1,day);
  datepicker.datepicker("option", "defaultDate", date);
}
