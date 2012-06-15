/**
 * 
 */
package org.esco.indicators.web.springmvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Ajax controller handling the requests and controls the form of the accounts activations web page.
 * 
 * @since  2012/06/15
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/accounts-activations")
public class AccountActivationController  {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(AccountActivationController.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model){
	AccountActivationForm aaForm = new AccountActivationForm();
	aaForm.setMonitoringType("Frequentation");
	
	model.addAttribute("accountActivationForm", aaForm);
	
	return "accounts-activations";
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS

    @ModelAttribute("monitoringTypeItems")
    public List<String> populateMonitoringType() {
	List<String> monitoringTypeItems = new ArrayList<String>();
	monitoringTypeItems.add("Frequentation");
	monitoringTypeItems.add("Suivi de frequentation");
	 
	return monitoringTypeItems;
    }
    

    //------------------------------------------------------------------------- PUBLIC AJAX METHODS
    
    @RequestMapping(value="/monitoring-type", method=RequestMethod.GET)
    public @ResponseBody Map<String, String> getMonitoringTypeMessage(@RequestParam String monitoringType) {
	Map<String,String> monitoring = new HashMap<String, String>();
	monitoring.put("unselected", "The unselected monitoring type is : " + monitoringType);
        return monitoring;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
