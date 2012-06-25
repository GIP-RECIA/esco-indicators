/**
 * 
 */
package org.esco.indicators.web.springmvc.ajax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.esco.indicators.services.form.DataFormService;
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
     * TEST METHOD
     * 
     * Handles an Ajax request.
     */
    @RequestMapping(value="/monitoring-type", method=RequestMethod.GET)
    public @ResponseBody Map<String, String> getMonitoringTypeMessage(@RequestParam String monitoringType) {
	Map<String,String> monitoring = new HashMap<String, String>();
	monitoring.put("unselected", "The unselected monitoring type is : " + monitoringType);
        return monitoring;
    }

    /**
     * TEST METHOD
     * 
     * Handles an Ajax request.
     */
    @RequestMapping(value="/update-form", method=RequestMethod.POST)
    public @ResponseBody String updateFormOnSelection(@RequestParam String checkedJspKeys) {
	// Extraction of the JSP keys of the checked elements
	String [] values = checkedJspKeys.split(JsonConstants.SEPARATOR);
	
	// Remove the keys thtat are not known by the application
	List<String> checkedKeys = Arrays.asList(values);
	checkedKeys = removeUnknownJspKeys(checkedKeys);
	
	// Retrieval of the JSP keys of the elements to disable in the user view
	List<String> jspKeysToDisable = dataFormService.getJspKeysToDisable(checkedKeys);
	System.out.println(jspKeysToDisable);
	
        return jspKeysToDisable.toString();
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
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
