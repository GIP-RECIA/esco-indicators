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

///////////////////////////////////////////////////////////
// FUNCTIONS
///////////////////////////////////////////////////////////
$.ajaxSetup({"error":function(XMLHttpRequest,textStatus, errorThrown) {
    alert(textStatus);
    alert(errorThrown);
    alert(XMLHttpRequest.responseText);
}});

$(document).ready(function() {
    // //////////////////////////////////////////////////////////////
    // When the LEN_INPUT becomes checked (resp. unchecked), its
    // dependencies inputs become checked (resp. unchecked).  
    // //////////////////////////////////////////////////////////////
    $("[value='" + LEN_INPUT.name + "']").change(function() {
        if(!isChecked(CFA_INPUT.name)) {
            changeCheckedProperty($(this).prop("checked"), LEN_INPUT.dependencies);
        }
    });

    // //////////////////////////////////////////////////////////////
    // When the LA_INPUT becomes checked (resp. unchecked), its
    // dependencies inputs become checked (resp. unchecked).  
    // //////////////////////////////////////////////////////////////
    $("[value='" + LA_INPUT.name + "']").change(function() {
        if(!isChecked(CFA_INPUT.name)) {
            changeCheckedProperty($(this).prop("checked"), LA_INPUT.dependencies);
        }
    });

    // //////////////////////////////////////////////////////////////
    // When the CFA_INPUT becomes checked :
    //  - the LEN_INPUT dependencies have to be updated with the
    //    "checked" property set to false.
    //  - the LA_INPUT dependencies have to be updated with the
    //    "checked" property set to false.
    //
    // When the CFA_INPUT becomes unchecked :
    //  - the LEN_INPUT dependencies have to be updated with the
    //    "checked" property than the LEN_INPUT itself.
    //  - the LA_INPUT dependencies have to be updated with the
    //    "checked" property than the LA_INPUT itself.
    // //////////////////////////////////////////////////////////////
    $("[value='" + CFA_INPUT.name + "']").change(function() {
        if($(this).prop("checked")) {
            changeCheckedProperty(false, LEN_INPUT.dependencies)    
            changeCheckedProperty(false, LA_INPUT.dependencies)    
        } else {
            var element = $("[value='" + LEN_INPUT.name + "']");
            changeCheckedProperty(element.prop("checked"), LEN_INPUT.dependencies)    
            var element = $("[value='" + LA_INPUT.name + "']");
            changeCheckedProperty(element.prop("checked"), LA_INPUT.dependencies)    
        }
    });


    // //////////////////////////////////////////////////////////////
    // When the SUM_ON_COUNTIES_INPUT is checked, its dependency
    // is selected.
    // //////////////////////////////////////////////////////////////
    $("[value='" + SUM_ON_COUNTIES_INPUT.name + "']").change(function() {
        changeElementProperty(SUM_ON_COUNTIES_INPUT.dependency, "selected", true) 
    });

    ///////////////////////////////////////////////////////
    // When the MONITORING_ATTENDANCE is checked,
    // only one user profile can be selected
    ///////////////////////////////////////////////////////

    // When MONITORING_ATTENDANCE becomes checked, unchecks all users profiles 
    $("[value='" + MONITORING_ATTENDANCE.name + "']").change(function() {
        if($(this).prop("checked")) {
            uncheckElementsByValues(USERS_PROFILES.values);
        }
    });

    $("[name='" + USERS_PROFILES.name + "']").change(function() {
        if(isChecked(MONITORING_ATTENDANCE.name)) {
            if($(this).prop("checked")) {
                uncheckElementsByValues(USERS_PROFILES.values);
                $(this).prop("checked", true);
            }
        }
    });
    
    // //////////////////////////////////////////////////////////////
    // When an input is checked, the values of the checked inputs are 
    // send to the server in order to know what inputs have to be 
    // disabled into the form.
    // //////////////////////////////////////////////////////////////
    $('.submit').change(function() {
        // 1. Some elements are submitted in order to update the form
        updateForm();
        // 2. The form has been updated and can be submitted in order to
        //    refresh the establishments list
		updateEstablishments();
    });


    // //////////////////////////////////////////////////////////////
    // Update of the form if the page has been reloaded or the
    // form has been submitted with errors.
    // Moreover the establishements list is reloaded.
    // //////////////////////////////////////////////////////////////
    updateForm();
    updateEstablishments();
});


///////////////////////////////////////////////////////////
// AJAX FUNCTIONS
///////////////////////////////////////////////////////////
function updateForm() {
    // Retrieval of the values of the checked elements
    var checkedValues = checkedElementsValues("accountactivationform");

    // Data for the request
    $.post(  "accounts-activations-ajax/update-form", 
                { 
                    checkedJspKeys : checkedValues
                }, 
                function(formState) {
                    enableElementsByValues(formState.to_enable);
                    disableElementsByValues(formState.to_disable);
                }
             );
}

function updateEstablishments() {
    // Retrieval of the values of the checked elements
    var checkedValues = checkedElementsValues("accountactivationform");

    // Retrieval of the values of the 'special' elements
    checkedValues += specialCheckedElementsValues();

    // Retrieval of the values of the selected elements
    var selectedValues = selectedElementsValues("accountactivationform");


    // Data for the request
    $.post(  "accounts-activations-ajax/update-establishments", 
                { 
                    checkedJspKeys : checkedValues,
                    selectedJspKeys: selectedValues
                }, 
                function(response) {
                    updateEstablishmentsList(response.establishments_list);
                }
             );
}
