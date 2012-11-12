/************************************************
 *
 * Author : Frapin Kevin <kevin.frapin@recia.fr>
 * Date : 2012/07/24
 *
 * **********************************************
 *
 * Description :
 * -------------
 * File containing the Ajax functions used to
 * interact with the servlet providing functions
 * used to update the form state and the
 * establishments list.
 *
 * **********************************************
 */

///////////////////////////////////////////////////////////
// SETUP ERRORS DISPLAY
///////////////////////////////////////////////////////////
// Uncomment the following lines for displaying ajax errors
/**
 * $.ajaxSetup({"error":function(XMLHttpRequest,textStatus, errorThrown) {
 *     alert(textStatus);
 *     alert(errorThrown);
 *     alert(XMLHttpRequest.responseText);
 * }});
 */

///////////////////////////////////////////////////////////
// FUNCTIONS
///////////////////////////////////////////////////////////
function updateForm(formId, url) {
    // Retrieval of the values of the checked elements
    var checkedValues = checkedElementsValues(formId);

    // Data for the request
    $.post(  url, 
             { 
                 checkedJspKeys : checkedValues
             }, 
             function(formState) {
                 enableElementsByValues(formState.to_enable);
                 disableElementsByValues(formState.to_disable);
             }
          );
}

function updateEstablishments(formId, url) {
    // Retrieval of the values of the checked elements
    var checkedValues = checkedElementsValues(formId);

    // Retrieval of the values of the 'special' elements
    checkedValues += specialCheckedElementsValues();

    // Retrieval of the values of the selected elements
    var selectedValues = selectedElementsValues(formId);


    // Data for the request
    $.post(  url, 
             { 
                 checkedJspKeys : checkedValues,
                 selectedJspKeys: selectedValues
             }, 
             function(response) {
                 updateEstablishmentsList(response.establishments_list);
             }
          );
}
