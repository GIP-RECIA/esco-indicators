/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.ajax;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Ajax controller handling the requests of the services web page.
 * 
 * @since  2012/07/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/services-ajax")
public class AjaxServiceController extends BasicAjaxController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(AjaxServiceController.class);

    /** Data form service providing information on the data from for the accounts */
    @Autowired
    protected DataFormService dataServiceFormService;
    
    /** Establishment service providing access to establishments data */
    @Autowired
    private EstablishmentService establishmentService;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link BasicAjaxController} class.
     */
    public AjaxServiceController() {
    }
    

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.ajax.BasicAjaxController#getDataFormService()
     */
    @Override
    public DataFormService getDataFormService() {
	return dataServiceFormService;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.ajax.BasicAjaxController#getEstablishmentService()
     */
    @Override
    public EstablishmentService getEstablishmentService() {
	return establishmentService;
    }


    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.ajax.BasicAjaxController#updateEstablishmentsOnSelection(java.lang.String, java.lang.String)
     */
    @Override
    @RequestMapping(value="/update-establishments", method=RequestMethod.POST)
    public @ResponseBody Map<String,List<FormField>> updateEstablishmentsOnSelection(@RequestParam String checkedJspKeys, @RequestParam String selectedJspKeys ) {
	return super.updateEstablishmentsOnSelection(checkedJspKeys, selectedJspKeys);
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.ajax.BasicAjaxController#updateFormOnSelection(java.lang.String)
     */
    @Override
    @RequestMapping(value="/update-form", method=RequestMethod.POST)
    public @ResponseBody Map<String,List<String>> updateFormOnSelection(@RequestParam String checkedJspKeys) {
        return super.updateFormOnSelection(checkedJspKeys);
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
