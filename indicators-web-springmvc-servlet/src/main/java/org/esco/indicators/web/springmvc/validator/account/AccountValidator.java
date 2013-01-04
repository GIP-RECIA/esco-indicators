/**
 * 
 */
package org.esco.indicators.web.springmvc.validator.account;

import javax.xml.crypto.Data;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.utils.constants.web.FormValidationConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.esco.indicators.web.springmvc.validator.basic.BasicValidator;
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
public class AccountValidator extends BasicValidator {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(AccountValidator.class);

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
	/////////////////////////////////////////////////////////////
	// Validation of the basic fields
	/////////////////////////////////////////////////////////////
	super.validate(target, errors);
	
	/////////////////////////////////////////////////////////////
	// Retrieval of the form
	/////////////////////////////////////////////////////////////
	AccountActivationForm aaForm = (AccountActivationForm) target;
	
	/////////////////////////////////////////////////////////////
	// Validation of the number of selected users
	// profiles
	/////////////////////////////////////////////////////////////
	String monitoringType = (String) errors.getFieldValue(DataFormConstants.MONITORING_TYPE);
	if(monitoringType != null && monitoringType.equals(DataFormConstants.JSP_KEY_MONITORING_ATTENDANCE)) {
	    // If the monitoring type is monitoring attendance
	    // Only one user profile must be specified
	    String [] usersProfiles = aaForm.getUsersProfiles();
	    Integer maxUsersProfiles = FormValidationConstants.MAX_SELECTED_PROFILES_MONITORING;
	    if(usersProfiles != null && usersProfiles.length > maxUsersProfiles) {
		errors.rejectValue(DataFormConstants.USERS_PROFILES, "error.form.usersProfiles.maxValue", new Object [ ] { maxUsersProfiles }, "error.form.usersProfiles.maxValue");
	    }
	}
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
