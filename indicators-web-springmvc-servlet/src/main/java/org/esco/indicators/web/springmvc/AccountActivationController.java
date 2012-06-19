/**
 * 
 */
package org.esco.indicators.web.springmvc;

import java.awt.Label;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.LabelValue;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.esco.indicators.services.form.DataFormService;
import org.esupportail.commons.services.i18n.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

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

    /** Service providing the data form */
    @Autowired
    private DataFormService dataFormService;
    
    /** Service providing access to internationalized strings */
    @Autowired
    private I18nService i18nService;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * 
     */
    public AccountActivationController() {
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model, HttpServletRequest request){
	///////////////////////////////////////// 
	// Binding of the form
	/////////////////////////////////////////
	AccountActivationForm aaForm = new AccountActivationForm();
	aaForm.setMonitoringType("Frequentation");
	model.addAttribute("accountActivationForm", aaForm);

	///////////////////////////////////////// 
	// Retrieval of the user locale
	///////////////////////////////////////// 
	Locale locale = getLocale(request);
	
	///////////////////////////////////////// 
	// Population of the entries
	/////////////////////////////////////////
	// Monitoring type
	initEntryLabelValues(model, "monitoringTypeItems", "monitoringType", locale);
	
	// Establishments types
	initEntryLabelValues(model, "estbalishmentsTypesItems", "establishmentsTypes", locale);
	
	// Users profiles  types
	initEntryLabelValues(model, "usersProfilesItems", "usersProfiles", locale);
	
	// County items
	initEntryLabelValues(model, "countyItems", "county", locale);
	
	// "Lycee"  types
	initEntryLabelValues(model, "lyceesTypesItems", "lyceesTypes", locale);
	
	// "Lycee Agricole"  types
	initEntryLabelValues(model, "laTypesItems", "laTypes", locale);
	
        ///////////////////////////////////////// 
        // Return to the web page
        /////////////////////////////////////////
	return "accounts-activations";
    }



    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the service providing access to the data form.
     * 
     * @param dataFormService
     * 			The service providing access to the data form to set.
     */
    public void setDataFormService(DataFormService dataFormService) {
        this.dataFormService = dataFormService;
    }

    /**
     * Sets the service providing access to i18n string.
     * 
     * @param i18nService 
     * 			The service providing access to i18n string to set.
     */
    public void setI18nService(I18nService i18nService) {
        this.i18nService = i18nService;
    }    
    
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    

    //------------------------------------------------------------------------- PUBLIC AJAX METHODS
    @RequestMapping(value="/monitoring-type", method=RequestMethod.GET)
    public @ResponseBody Map<String, String> getMonitoringTypeMessage(@RequestParam String monitoringType) {
	Map<String,String> monitoring = new HashMap<String, String>();
	monitoring.put("unselected", "The unselected monitoring type is : " + monitoringType);
        return monitoring;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Gets the web user locale from the request.
     * 
     * @param request
     * 			The request made by the web user.
     * @return
     * 	the locale of the web user.
     */
    private Locale getLocale(HttpServletRequest request) {
	RequestContext requestContext = new RequestContext(request);
	Locale locale = requestContext.getLocale();
	return locale;
    }
    
    /**
     * Initializes the <code>jspAttribute</code> of the <code>model</code> with the available items (labels and values)
     * of the <code>entry</code> using the specified <code>locale</code>.
     * 
     * @param model
     * 			The model containing the JSP attribute.
     * @param jspAttribute
     * 			The JSP attribute to initialize.
     * @param entry
     * 			The entry name associated to the items (labels and values) that are put into the JSP attribute.
     * @param locale
     * 			The locale used to internationalize the labels strings.
     */
    private void initEntryLabelValues(ModelMap model, String jspAttribute, String entry, Locale locale) {
	List<LabelValue> items = getEntryLabelValues(entry, locale);
	model.addAttribute(jspAttribute, items);
    }

    /**
     * Gets the items (labels and values) available of the specified entry.
     * 
     * @param entryName
     * 			The name of the entry associated to the items (labels and values).
     * @param locale
     * 			The locale used to internationalize the labels strings.
     * @return
     * 	the available items (labels and values) of the entry.
     */
    private List<LabelValue> getEntryLabelValues(String entryName, Locale locale) {
	// Retrieval of the entry values
	List<EntryValue> entries = dataFormService.getEntryValues(entryName);
	
	// Creation of the corresponding items (labels and values)
	List<LabelValue> labelValues = new ArrayList<LabelValue>();
	for (EntryValue entry : entries) {
	    String label = i18nService.getString(entry.getI18nKey(), locale);
	    String value = entry.getJspKey();
	    
	    LabelValue labelValue = new LabelValue(label, value);
	    labelValues.add(labelValue);
	}
	return labelValues;
    }
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
