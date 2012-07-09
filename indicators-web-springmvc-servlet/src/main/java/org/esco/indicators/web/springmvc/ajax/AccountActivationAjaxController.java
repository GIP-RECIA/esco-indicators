/**
 * 
 */
package org.esco.indicators.web.springmvc.ajax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.constants.web.JsonConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Ajax controller handling the requests of the accounts activations web page.
 * 
 * @since  2012/06/21
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/accounts-activations-ajax")
public class AccountActivationAjaxController  {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(AccountActivationAjaxController.class);

    /** Data form service providing information on the data from */
    @Autowired
    private DataFormService dataFormService;
    
    /** Establishment service providing access to establishments data */
    @Autowired
    private EstablishmentService establishmentService;
    
    /** Object mapper used to deserialize the JSON strings */
    private ObjectMapper objectMapper;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default construcotr of the {@link AccountActivationAjaxController} class.
     */
    public AccountActivationAjaxController() {
	objectMapper = new ObjectMapper();
    }
    

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    
    
    //------------------------------------------------------------------------------ PUBLIC METHODS

    /**
     * Creates a map describing the new establishments list to put into the form regarding to the checked JSP keys in the user view.<br/>
     * This map contains only one key : {@link JsonConstants#ESTABLISHMENTS_LIST}.<br/>
     * This key is associated to a list of form fields (see {@link FormField}) containing informations on the establishments.<br/>
     * So, for each form field present into the list :
     * <ul>
     * 	<li> The label of the form field contains the name of the establishement</li>
     * 	<li> The value of the form field contains the UAI of the establishement</li>
     *  <ul>
     *  
     * @param checkedJspKeys
     * 			The JSP keys that are checked in the user view.<br/>
     * 			This string has to respect this pattern : {JSP_KEY1}{SEPARATOR}{JSP_KEY2}{...}<br/>
     * 			The SEPARATOR corresponds to the {@link JsonConstants#SEPARATOR}.
     * 
     * @param selectedJspKeys
     * 			The JSP keys that are selected in the user view.<br/>
     * 			This string has to respect this pattern : {JSP_KEY1}{SEPARATOR}{JSP_KEY2}{...}<br/>
     * 			The SEPARATOR corresponds to the {@link JsonConstants#SEPARATOR}.
     * 
     * @return
     * 	the map containing the new establishments list.
     */
    @RequestMapping(value="/update-establishments", method=RequestMethod.POST)
    public @ResponseBody Map<String,List<FormField>> updateEstablishmentsOnSelection(@RequestParam String checkedJspKeys, @RequestParam String selectedJspKeys ) {
	// Debug infos
	LOGGER.debug("Parameter (checkedJspKeys) : [" + checkedJspKeys + "]" );
	LOGGER.debug("Parameter (selectedJspKeys) : [" + selectedJspKeys + "]" );
	
	// Explosion of the Json string into a list of parameters
	List<String> parameters = explodeJsonParams(checkedJspKeys);
	parameters.addAll(explodeJsonParams(selectedJspKeys));

	// Cleaning of the list of parameters
	parameters = removeUnknownJspKeys(parameters);
	
	// Keeping the parameters that have influence on the establishments list
	parameters = keepInfluentialJspKeys(parameters);
	
	// Creation of the new establisments list
	List<FormField> establishmentsFields = createNewEstablishmentsList(parameters);
	
	// Creation of the map returned to the Ajax caller
	Map<String,List<FormField>> response = new HashMap<String, List<FormField>>();
	response.put(JsonConstants.ESTABLISHMENTS_LIST, establishmentsFields);
	
	return response;
    }
    
    /**
     * Ajax method used to provide the state of the inputs of the form regarding to the JSP keys already checked in the user view.
     * 
     * @param checkedJspKeys
     * 			JSP keys already checked in the user view.<br/>
     * 			This string has to respect this pattern : {JSP_KEY1}{SEPARATOR}{JSP_KEY2}{...}<br/>
     * 			The SEPARATOR corresponds to the {@link JsonConstants#SEPARATOR}.
     * 
     * @return
     * 	A map containing the new states of the form inputs<br/>
     *		This map contains two strings as keys, an list as values :
     * 	<ul>
     * 		<li> <b>Key :</b> {@link JsonConstants#KEYS_TO_ENABLE} => <b>Value :</b> The list of JSP keys to enable in the user view.</li>
     * 		<li> <b>Key :</b> {@link JsonConstants#KEYS_TO_DISABLE} => <b>Value :</b> The list of JSP keys to disable in the user view.</li>
     * 	</ul>
     * 	These two lists are totally disjoint and do not contain same elements.<br/>
     */
    @RequestMapping(value="/update-form", method=RequestMethod.POST)
    public @ResponseBody Map<String,List<String>> updateFormOnSelection(@RequestParam String checkedJspKeys) {
	// Debug infos
	LOGGER.debug("Parameter (checkedJspKeys) : [" + checkedJspKeys + "]" );
	
	// Remove the keys thtat are not known by the application
	List<String> parameters = explodeJsonParams(checkedJspKeys);
	
	// Cleaning of the list of parameters
	parameters = removeUnknownJspKeys(parameters);
	
	// Creation of the new form state
	// This form state indicates which elements has to be enable / disable in the user view
	Map<String,List<String>> formState = createNewFormState(parameters);

        return formState;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
 
    
    /**
     * Creates a map describing the new state of the form in the user view regarding to the already JSP keys already checked in the user view.<br/>
     * This map contains two strings as keys, an list as values :
     * <ul>
     * 	<li> <b>Key :</b> {@link JsonConstants#KEYS_TO_ENABLE} => <b>Value :</b> The list of JSP keys to enable in the user view.</li>
     * 	<li> <b>Key :</b> {@link JsonConstants#KEYS_TO_DISABLE} => <b>Value :</b> The list of JSP keys to disable in the user view.</li>
     * </ul>
     * 
     * These two lists are totally disjoint and do not contain same elements.<br/>
     * If some rules indicate taht a JSP should be enabled and disabled at the same time, the priority is given to the disabling rule.
     * 
     * @param checkedKeys
     * 			The JSP keys checked in the user view.
     * 
     * @return
     * 	the map containing the new state of the form.
     */
    private Map<String,List<String>> createNewFormState(List<String> checkedKeys) {
	///////////////////////////////////////////////////////////////////////
	// Retrieval of the elements to enable / disable
	///////////////////////////////////////////////////////////////////////
	
	// Gets the JSP keys enabled / disabled by default
	List<String> jspKeysDisabledByDefault = dataFormService.getJspKeysDisabledByDefault();
	List<String> jspKeysEnabledByDefault = dataFormService.getJspKeysEnabledByDefault();
	
	// Gets the JSP keys to enable / disable regarding to the checked ones
	List<String> jspKeysToDisable = dataFormService.getJspKeysToDisable(checkedKeys);
	List<String> jspKeysToEnable = dataFormService.getJspKeysToEnable(checkedKeys);

	///////////////////////////////////////////////////////////////////////
	// Creation of the lists of the elements to enable / disable
	///////////////////////////////////////////////////////////////////////
	// Priority to disabling actions
	// ( ToEnable = ToEnable - ToDisable )
	jspKeysToEnable.removeAll(jspKeysToDisable);

	
	// Activation of the disabled JSP keys by default
	// ( DisabledByDefault = DisabledByDefault - ToEnable )
	jspKeysDisabledByDefault.removeAll(jspKeysToEnable);
	
	// Desactivation of the enabled JSP keys by default
	// ( EnabledByDefault = EnabledByDefault - To Disable );
	jspKeysEnabledByDefault.removeAll(jspKeysToDisable);
	
	// Final elements to enable / disable
	jspKeysToEnable.addAll(jspKeysEnabledByDefault);
	jspKeysToDisable.addAll(jspKeysDisabledByDefault);
	
	///////////////////////////////////////////////////////////////////////
	// Creation of map containing the JSP keys to enable / disable
	///////////////////////////////////////////////////////////////////////
	Map<String,List<String>> formState = new HashMap<String, List<String>>();
	formState.put(JsonConstants.KEYS_TO_DISABLE, jspKeysToDisable);
	formState.put(JsonConstants.KEYS_TO_ENABLE, jspKeysToEnable);
	
	return formState;
    }
    
    

    /**
     * Creates a list of form fields containing establishments informations.<br/>
     * The establishments are the ones corresponding to the filters forced by the checked JSP keys.<br/>
     * Indeed, when some JSP keys are checked, they forced the establishments list to be update and they forced
     * to be filtered.<br/>
     * Each form field contains :
     * <ul>
     * 	<li> The name of the establishment as label</li>
     * 	<li>The UAI of the establishment as value</br>
     * </ul>
     * 
     * @param checkedJspKeys
     * 			The JSP keys forcing establishments list update and filters.
     * 
     * @return
     * 	the list of new form fields containing informations of the establishments
     */
    private  List<FormField> createNewEstablishmentsList(List<String> checkedJspKeys) {
	// Retrieval of the authorized establishments type for the establishments list
	List<String> establishmentsTypes = dataFormService.getEstablishmentsTypesToFilter(checkedJspKeys);
	
	// Retrieval of the authorized county numbers
	List<Integer> countyNumbers = dataFormService.getCountyNumbersToFilter(checkedJspKeys);
	
	// Retrieval of the establishements form fields by type and county
	List<FormField> establishments = getEstablishmentsByCountyNumbersAndTypes(countyNumbers, establishmentsTypes);
	
	return establishments;
    }
    
    /**
     * Gets the establishments list (in a FormField format) regarding to the authorized county numbers and establishments types.
     * 
     * @param countyNumbers
     * 			The authorized county numbers of the establishements.
     * @param establishmentsTypes
     * 			The authorized establishements types of the establishments.
     * 
     * @return
     * 	the list of form fields containing the establishments informations.<br/>
     * 	an empty list if no establishments has been retrieved.
     */
    private List<FormField> getEstablishmentsByCountyNumbersAndTypes(List<Integer> countyNumbers,
	    List<String> establishmentsTypes) {
	// Retrieval of the establishments with the specified filters
	Set<Establishment> establishments = establishmentService.findEstablishmentsByCountyNumbersAndTypes(countyNumbers, establishmentsTypes);
	
	// Translate the establishments into form fields
	List<FormField> formFields = new ArrayList<FormField>();
	for (Establishment establishment : establishments) {
	    FormField formField = new FormField(establishment.getName(), establishment.getUai(), DataFormConstants.ESTABLISHMENTS);
	    formFields.add(formField);
	}
	
	return formFields;
    }


    /**
     * Explodes the Json parameters string into a list of params.
     * 
     * @param parameters
     * 			The Json parameters string.
     * 
     * @return
     * 	a list containing the paramaters present into the specified string.
     */
    private List<String> explodeJsonParams(String parameters) {
	// Extraction of the json params into an array
	String [] values = parameters.split(JsonConstants.SEPARATOR);
	List<String> result = new ArrayList<String>();
	result.addAll(Arrays.asList(values));
	
	return  result;
    }
    

    /**
     * Keeps the JSP keys that have an influence on the establishments list.
     * 
     * @param jspKeys
     * 			The JSP keys to test.
     * @return
     * 	the list of JSP keys having an influence on the establishments list.<br/>
     * 	an empty list if no JSP key has an influence on the establishments list.
     */
    private List<String> keepInfluentialJspKeys(List<String> jspKeys) {
	// Final result
	List<String> influentialJspKeys = new ArrayList<String>();
	
	// Keep the influential JSP keys
	for (String jspKey : jspKeys) {
	    if(dataFormService.hasInfluenceOnEstablishmentsList(jspKey)) {
		influentialJspKeys.add(jspKey);
	    }
	}
	
	return influentialJspKeys;
    }
    
    /**
     * Remove the unknown JSP keys from the list.<br/>
     * Return a copy of the specified keys only containing known keys.
     * 
     * @param jspKeys
     * 			The JSP keys to verify.
     * 
     * @return
     * 	a copy of the specified list without the unknown JSP keys.
     */
    private List<String> removeUnknownJspKeys(List<String> jspKeys) {
	// Final result
	List<String> knownKeys = new ArrayList<String>();
	
	// Only keep the known keys
	for (String jspKey : jspKeys) {
	    if(dataFormService.isKnown(jspKey)) {
		knownKeys.add(jspKey);
	    }
	}
	
	return knownKeys;
    }

    
    //------------------------------------------------------------------------------ STATIC METHODS
}
