/************************************************
 *
 * Author : Frapin Kevin <kevin.frapin@recia.fr>
 * Date : 2012/06/15
 *
 * **********************************************
 *
 * Description :
 * -------------
 * File containing the Ajax functions used to
 * interact with the accounts activations servlet.
 *
 * **********************************************
 */


$.ajaxSetup({"error":function(XMLHttpRequest,textStatus, errorThrown) {   
    alert(textStatus);
    alert(errorThrown);
    alert(XMLHttpRequest.responseText);
}});

$(document).ready(function() {
    // Get the message to display when a monitoring type is selected
    $('#monitoringType1').blur(function() {
        getMonitoringTypeMessage();
    });
});
 
function getMonitoringTypeMessage() {
    $.getJSON(  "accounts-activations/monitoring-type", 
                { 
                    monitoringType: $('#monitoringType1').val() 
                }, 
                function(data) {
                    alert(data.unselected);
                }
             );
}
