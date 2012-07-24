/**
 * 
 */
package org.esco.indicators.web.springmvc.validator.basic;

import javax.xml.crypto.Data;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.utils.constants.web.FormValidationConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.hibernate.classic.Validatable;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Basic validator used to validate some {@link BasicForm} fields.
 * 
 * @since  2012/07/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class BasicValidator implements Validator {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicValidator.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return BasicForm.class.equals(clazz);
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
	ValidationUtils.rejectIfEmpty(errors, DataFormConstants.ESTABLISHMENTS_TYPES, "error.form.establishmentsTypes.empty");
	ValidationUtils.rejectIfEmpty(errors, DataFormConstants.START_DATE, "error.form.startDate.empty");
	ValidationUtils.rejectIfEmpty(errors, DataFormConstants.USERS_PROFILES, "error.form.usersProfiles.empty");

	/////////////////////////////////////////////////////////////
	// Validation of the more complicated content
	/////////////////////////////////////////////////////////////
	
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
	
	/////////////////////////////////////////
	// Validation of the county field
	/////////////////////////////////////////
	String sumOnCountiesStr = (String) errors.getFieldValue(DataFormConstants.SUM_ON_COUNTIES);
	if( sumOnCountiesStr == null || sumOnCountiesStr.isEmpty()) {
	    //  If no sum on counties is specified
	    // A county must be selected
	    ValidationUtils.rejectIfEmpty(errors, DataFormConstants.COUNTY, "error.form.county.empty");
	}
	
	/////////////////////////////////////////
	// Validation of the establishments
	// list
	/////////////////////////////////////////
	String [] establishments = form.getEstablishments();
	Integer maxEstablishments = FormValidationConstants.MAX_SELECTED_ESTABLISHMENTS;
	if(establishments != null && establishments.length > maxEstablishments) {
	    errors.rejectValue(DataFormConstants.ESTABLISHMENTS, "error.form.establishments.maxValue", new Object [ ] { maxEstablishments }, "error.form.establishments.maxValue");
	} else {
	    ValidationUtils.rejectIfEmpty(errors, DataFormConstants.ESTABLISHMENTS, "error.form.establishments.empty");
	}
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
