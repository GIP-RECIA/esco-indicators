/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.usage.form;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.web.SessionConstants;
import org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller that handles the requests made on the form used for the services usage statistics.
 * 
 * @since  2012/10/11
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
@Controller
@RequestMapping("/services-usage")
// TODO : Needs to extend BasicFormController
public class FormServicesUsageController  extends BasicFormController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(FormServicesUsageController.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link FormServicesUsageController} class.
     */
    public FormServicesUsageController() {
	super(SessionConstants.SERVICE_USAGE_FORM_ATTR);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getDataFormService()
     */
    @Override
    public DataFormService getDataFormService() {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getFailureViewName(org.esco.indicators.domain.beans.form.BasicForm)
     */
    @Override
    public String getFailureViewName(BasicForm unvalidForm) {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getFormName()
     */
    @Override
    public String getFormName() {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getSuccessViewName(org.esco.indicators.domain.beans.form.BasicForm)
     */
    @Override
    public String getSuccessViewName(BasicForm validForm) {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getUserFormViewName()
     */
    @Override
    public String getUserFormViewName() {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getSuperUserFormViewName()
     */
    @Override
    public String getSuperUserFormViewName() {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#getValidator()
     */
    @Override
    public Validator getFormValidator() {
	// TODO Auto-generated method stub
	return null;
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
