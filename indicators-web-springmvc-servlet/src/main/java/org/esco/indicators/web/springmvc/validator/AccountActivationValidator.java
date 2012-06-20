/**
 * 
 */
package org.esco.indicators.web.springmvc.validator;

import javax.xml.crypto.Data;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.hibernate.classic.Validatable;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator of the {@link AccountActivationForm}.
 * 
 * @since  2012/06/20
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class AccountActivationValidator implements Validator {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(AccountActivationValidator.class);

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
	AccountActivationForm aaForm = (AccountActivationForm) target;
	
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
	
	// Validation of the end date field
	String monitoringType = (String) errors.getFieldValue(DataFormConstants.MONITORING_TYPE);
	if(monitoringType != null && monitoringType.equals(DataFormConstants.JSP_KEY_MONITORING_ATTENDANCE)) {
	    // If the monitoring type is monitoring attendance
	    // A end date must be specified
	    ValidationUtils.rejectIfEmpty(errors, DataFormConstants.END_DATE, "error.form.endDate.empty");
	}
	
	// Validation of the county field
	String sumOnCoutniesStr = (String) errors.getFieldValue(DataFormConstants.SUM_ON_COUNTIES);
	boolean sumOnCounties = Boolean.valueOf(sumOnCoutniesStr);
	if( !sumOnCounties) {
	    //  If no sum on counties is specified
	    // A county must be selected
	    ValidationUtils.rejectIfEmpty(errors, DataFormConstants.COUNTY, "error.form.county.empty");
	}
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
