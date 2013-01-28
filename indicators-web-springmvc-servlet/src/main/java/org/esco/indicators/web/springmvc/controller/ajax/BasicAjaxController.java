/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.ajax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.constants.web.JsonConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;

/**
 * Basic Ajax controller handling the requests that are commons to multiple web pages.
 * 
 * @since  2012/06/21
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public abstract class BasicAjaxController  {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicAjaxController.class);
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link BasicAjaxController} class.
     */
    public BasicAjaxController() {
    }
    

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the data form service.
     * 
     * @return
     * 	the data form service.
     */
    public abstract DataFormService getDataFormService();
    
    /**
     * Gets the establishment service.
     * 
     * @return
     * 	the establishment service.
     */
    public abstract EstablishmentService getEstablishmentService();
    
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
    public Map<String,List<FormField>> updateEstablishmentsOnSelection(String checkedJspKeys, String selectedJspKeys ) {
	// Debug infos
	LOGGER.debug("Parameter (checkedJspKeys) : [" + checkedJspKeys + "]" );
	LOGGER.debug("Parameter (selectedJspKeys) : [" + selectedJspKeys + "]" );
	
	// Explosion of the Json string into a list of parameters
	List<String> parameters = explodeJsonParams(checkedJspKeys);
	parameters.addAll(explodeJsonParams(selectedJspKeys));

	// Cleaning of the list of parameters
	parameters = removeUnknownJspKeys(parameters);
	
	// Keeping the parameters that have influence on the establishments list
	List<String> influentialParameters = keepInfluentialJspKeys(parameters);
	
	// Creation of the new establishment list
	List<FormField> establishmentsFields = new ArrayList<FormField>();
	if(!parameters.contains(DataFormConstants.JSP_KEY_SUM_ON_COUNTIES)) {
	    // If the sum on counties is not checked
	    // Creation of the new establisments list
	    establishmentsFields.addAll(createNewEstablishmentsList(influentialParameters));
	}
	
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
    public Map<String,List<String>> updateFormOnSelection(String checkedJspKeys) {
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
    
    
    //--------------------------------------------------------------------------- PROTECTED METHODS
    /**
     * Explodes the Json parameters string into a list of params.
     * 
     * @param parameters
     * 			The Json parameters string.
     * 
     * @return
     * 	a list containing the paramaters present into the specified string.
     */
    protected List<String> explodeJsonParams(String parameters) {
	// Extraction of the json params into an array
	String [] values = parameters.split(JsonConstants.SEPARATOR);
	List<String> result = new ArrayList<String>();
	result.addAll(Arrays.asList(values));
	
	return  result;
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
    protected List<String> removeUnknownJspKeys(List<String> jspKeys) {
	// Final result
	List<String> knownKeys = new ArrayList<String>();
	
	// Only keep the known keys
	for (String jspKey : jspKeys) {
	    if(getDataFormService().isKnown(jspKey)) {
		knownKeys.add(jspKey);
	    }
	}
	
	return knownKeys;
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
	List<String> jspKeysDisabledByDefault = getDataFormService().getJspKeysDisabledByDefault();
	List<String> jspKeysEnabledByDefault = getDataFormService().getJspKeysEnabledByDefault();
	
	// Gets the JSP keys to enable / disable regarding to the checked ones
	List<String> jspKeysToDisable = getDataFormService().getJspKeysToDisable(checkedKeys);
	List<String> jspKeysToEnable = getDataFormService().getJspKeysToEnable(checkedKeys);

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
	List<String> establishmentsTypes = getDataFormService().getEstablishmentsTypesToFilter(checkedJspKeys);
	
	// Retrieval of the authorized county numbers
	List<String> countyNumbers = getDataFormService().getCountyNumbersToFilter(checkedJspKeys);
	
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
    private List<FormField> getEstablishmentsByCountyNumbersAndTypes(List<String> countyNumbers,
	    List<String> establishmentsTypes) {
	// Retrieval of the establishments with the specified filters
	List<Establishment> establishments = getEstablishmentService().findEstablishmentsByCountyNumbersAndTypes(countyNumbers, establishmentsTypes);
	
	// Translate the establishments into form fields
	List<FormField> formFields = new ArrayList<FormField>();
	for (Establishment establishment : establishments) {
	    FormField formField = new FormField(establishment.getName(), establishment.getUai(), DataFormConstants.ESTABLISHMENTS);
	    formFields.add(formField);
	}
	
	return formFields;
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
	    if(getDataFormService().hasInfluenceOnEstablishmentsList(jspKey)) {
		influentialJspKeys.add(jspKey);
	    }
	}
	
	return influentialJspKeys;
    }
    

    
    //------------------------------------------------------------------------------ STATIC METHODS
}
