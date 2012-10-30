/**
 * 
 */
package org.esco.indicators.web.springmvc.validator.account;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.utils.constants.web.FormValidationConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator of the {@link AccountActivationForm} for the Accouts Activations view 
 * made for only one establishment.
 * 
 * @since  2012/10/29
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentAccountValidator implements Validator {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentAccountValidator.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return AccountActivationForm.class.equals(clazz);
    }

    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) {
	// Retrival of the object
	BasicForm form = (BasicForm) target;
	
	/////////////////////////////////////////////////////////////
	// Inspection of the basic content of the form
	/////////////////////////////////////////////////////////////
	ValidationUtils.rejectIfEmpty(errors, DataFormConstants.MONITORING_TYPE, "error.form.monitoringType.empty");
	ValidationUtils.rejectIfEmpty(errors, DataFormConstants.START_DATE, "error.form.startDate.empty");

	/////////////////////////////////////////
	// Validation of :
	//	- the end date field
	// 	- the user profile field 
	/////////////////////////////////////////
	String monitoringType = (String) errors.getFieldValue(DataFormConstants.MONITORING_TYPE);
	 // If the monitoring type is monitoring attendance
	if(monitoringType != null && monitoringType.equals(DataFormConstants.JSP_KEY_MONITORING_ATTENDANCE)) {
	    // A end date must be specified
	    ValidationUtils.rejectIfEmpty(errors, DataFormConstants.END_DATE, "error.form.endDate.empty");
	}
	
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
