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
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.constants.ajax.JsonConstants;
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

    
    @RequestMapping(value="/update-establishments", method=RequestMethod.POST)
    public @ResponseBody Map<String,List<FormField>> updateEstablishmentsOnSelection(@RequestParam String checkedJspKeys) {
	// Explosion of the Json string into a list of parameters
	List<String> parameters = explodeJsonParams(checkedJspKeys);
	
	// Retrieval of the form fields containing the etablishments list to update in the user view
	List<FormField> establishmentsFields = getEstablishmentsFields(parameters);
	
	Map<String,List<FormField>> response = new HashMap<String, List<FormField>>();
	response.put("establishments", establishmentsFields);
	
	return response;
    }
    
    /**
     * Ajax method used to provide the state of the inputs of the form regarding to the JSP keys already checked in the user view.
     * 
     * @param checkedJspKeys
     * 			JSP keys already checked in the user view.<br/>
     * 			This string has to respect this pattern : {JSP_KEY1}{SEPARATOR}{JSP_KEY2}{...}<br/>
     * 			The SEPARATOR corresponds to the {@link JsonConstants#SEPARATOR}.
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
	// Remove the keys thtat are not known by the application
	List<String> checkedKeys = explodeJsonParams(checkedJspKeys);
	checkedKeys = removeUnknownJspKeys(checkedKeys);

	// Creation of the new form state
	// This form state indicates which elements has to be enable / disable in the user view
	Map<String,List<String>> formState = createNewFormState(checkedKeys);

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
	return Arrays.asList(values);
    }
    
    private List<FormField> getEstablishmentsFields(List<String> checkedJspKeys) {
	// TODO : Inmplement !
	return null;
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
