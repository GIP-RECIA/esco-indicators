/**
 * 
 */
package org.esco.indicators.web.springmvc.ajax;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.esco.indicators.web.springmvc.validator.AccountActivationValidator;
import org.esupportail.commons.services.i18n.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.support.RequestContext;

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

    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default construcotr of the {@link AccountActivationAjaxController} class.
     */
    public AccountActivationAjaxController() {
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

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
