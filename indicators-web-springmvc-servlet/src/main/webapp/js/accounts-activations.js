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
// JQUERY : DOCUMENT READY FUNCTION
///////////////////////////////////////////////////////////
$(document).ready(function() {
    // //////////////////////////////////////////////////////////////
    // When an input is checked, the values of the checked inputs are 
    // send to the server in order to know what inputs have to be 
    // disabled into the form.
    // //////////////////////////////////////////////////////////////
    $('.submit').change(function() {
        // 1. Some elements are submitted in order to update the form
        updateForm("accountactivationform", "accounts-activations-ajax/update-form");
        // 2. The form has been updated and can be submitted in order to
        //    refresh the establishments list
		updateEstablishments("accountactivationform", "accounts-activations-ajax/update-establishments");
    });

    // //////////////////////////////////////////////////////////////
    // Update of the form if the page has been reloaded or the
    // form has been submitted with errors.
    // Moreover the establishements list is reloaded.
    // //////////////////////////////////////////////////////////////
    updateForm("accountactivationform", "accounts-activations-ajax/update-form");
    updateEstablishments("accountactivationform", "accounts-activations-ajax/update-establishments");
});
