/**
 * 
 */
package org.esco.indicators.web.springmvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.LabelValue;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esupportail.commons.services.i18n.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
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
	// Binding of the form
	AccountActivationForm aaForm = new AccountActivationForm();
	model.addAttribute("accountactivationform", aaForm);

        // Return to the web page
	return "accounts-activations";
    }
    
     @ModelAttribute("countyItems")
    public List<LabelValue> populateCounty(HttpServletRequest request) {
	return getEntryLabelValues(request, DataFormConstants.COUNTY);
    }
        
    @ModelAttribute("establishmentsItems")
    public List<LabelValue> populateEstablishments(HttpServletRequest request) {
	Establishment estab = new Establishment(45, "0458751U", "CFA");
	estab.setName("CFA des Sports");
	estab.setSiren("4515452");
	
	List<LabelValue> labels = new ArrayList<LabelValue>();
	labels.add(new LabelValue(estab.getName(), estab.getUai()));
	
	return labels;
    }
    
    @ModelAttribute("estbalishmentsTypesItems")
    public List<LabelValue> populateEstablishmentsTypes(HttpServletRequest request) {
	return getEntryLabelValues(request, DataFormConstants.ESTABLISHMENTS_TYPES);
    }
        
    @ModelAttribute("laTypesItems")
    public List<LabelValue> populateLaTypes(HttpServletRequest request) {
	return getEntryLabelValues(request, DataFormConstants.LA_TYPES);
    }    
    
    @ModelAttribute("lyceesTypesItems")
    public List<LabelValue> populateLyceesTypes(HttpServletRequest request) {
	return getEntryLabelValues(request, DataFormConstants.LYCEES_TYPES);
    }
        
    @ModelAttribute("monitoringTypeItems")
    public List<LabelValue> populateMonitoringType(HttpServletRequest request) {
	return getEntryLabelValues(request, DataFormConstants.MONITORING_TYPE);
    }
    
    @ModelAttribute("usersProfilesItems")
    public List<LabelValue> populateUsersProfiles(HttpServletRequest request) {
	return getEntryLabelValues(request, DataFormConstants.USERS_PROFILES);
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("accountactivationform") AccountActivationForm aaForm, BindingResult result, SessionStatus status) {
	if(LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Submitted form : " + aaForm.toString());
	}
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
     * Gets the items (labels and values) available for the specified entry.
     * 
     * @param request
     * 			The request of the user.
     * @param entryName
     * 			The name of the entry associated to the items (labels and values).
     * @return
     * 	the available items (labels and values) for the entry.
     */
    private List<LabelValue> getEntryLabelValues(HttpServletRequest request, String entryName) {
	// Retrival of the locale
	Locale locale = getLocale(request);
	
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
