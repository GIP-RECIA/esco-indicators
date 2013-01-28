/**
 * 
 */
package org.esco.indicators.web.springmvc.validator.service;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.ServiceForm;
import org.esco.indicators.utils.constants.web.FormValidationConstants;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.springframework.validation.Errors;

/**
 * Validator of the {@link ServiceForm}.
 * 
 * @since  2012/07/24
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ServiceValidator extends BasicServiceValidator {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ServiceValidator.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
	return ServiceForm.class.equals(clazz);
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
	ServiceForm form = (ServiceForm) target;
	
	/////////////////////////////////////////////////////////////
	// Validation of the number of selected users
	// profiles
	/////////////////////////////////////////////////////////////
	String [] usersProfiles = form.getUsersProfiles();
	Integer maxNumProfiles = FormValidationConstants.MAX_SELECTED_PROFILES_SERVICE_FORM;
	if(usersProfiles != null && usersProfiles.length > maxNumProfiles) {
	    // If there are more selected profiles than the maximum allowed
	    errors.rejectValue(DataFormConstants.USERS_PROFILES, "error.form.usersProfiles.maxValue", new Object [ ] { maxNumProfiles }, "error.form.usersProfiles.maxValue");
	}
	
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    //------------------------------------------------------------------------------ STATIC METHODS
}
