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
    // //////////////////////////////////////////////////////////////
    // When an element having the class 'submit' is changed
    // the entire form is submitted in order to refresh the 
    // establishments list 
    // //////////////////////////////////////////////////////////////
    $('.submit').change(function(e) {
        var select = e.target;
        alert('Change on : ' + select.value);
    });

    // Ajax call
    $('#monitoringType1').blur(function() {
        getMonitoringTypeMessage();
    });

});
 
function getMonitoringTypeMessage() {
    $.getJSON(  "accounts-activations-ajax/monitoring-type", 
                { 
                    monitoringType: $('#monitoringType1').val() 
                }, 
                function(data) {
                    alert(data.unselected);
                }
             );
}
