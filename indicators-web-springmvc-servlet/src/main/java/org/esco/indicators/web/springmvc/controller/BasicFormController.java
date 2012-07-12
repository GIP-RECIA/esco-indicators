/**
 * 
 */
package org.esco.indicators.web.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.xml.form.EntryValue;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Class representing a basic form controller
 * @since  
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public abstract class BasicFormController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicFormController.class);

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the data form service of the form controller.
     * 
     * @return
     * 	the data form service of the form controller
     */
    public abstract DataFormService getDataFormService();
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Populates the county field.
     * 
    * @param request
    * 			The request made by the user.
    * @return
    * 	the available values for the county field.
    */
   @ModelAttribute("countyItems")
   public List<FormField> populateCounty(HttpServletRequest request) {
	return getEntryFormFields(DataFormConstants.COUNTY);
   }
   
   /**
    * Populate the establishments field.
    * 
    * @param request
    * 			The request made by the user.
    * @return
    * 	the available values for the establishments field.
    */
   @ModelAttribute("establishmentsItems")
   public List<FormField> populateEstablishments(HttpServletRequest request) {
	return  (new ArrayList<FormField>());
   }
   
   /**
    * Populate the establishments types field.
    * 
    * @param request
    * 			The request made by the user.
    * @return
    * 	the available values for the establishments types field.
    */
   @ModelAttribute("estbalishmentsTypesItems")
   public List<FormField> populateEstablishmentsTypes(HttpServletRequest request) {
	return getEntryFormFields(DataFormConstants.ESTABLISHMENTS_TYPES);
   }
       
   /**
    * Populate the "lycees"  types field.
    * 
    * @param request
    * 			The request made by the user.
    * @return
    * 	the available values for the "lycees"  types field.
    */
   @ModelAttribute("laTypesItems")
   public List<FormField> populateLaTypes(HttpServletRequest request) {
	return getEntryFormFields(DataFormConstants.LA_TYPES);
   }    
   
   /**
    * Populate the "lycees agricoles"  types field.
    * 
    * @param request
    * 			The request made by the user.
    * @return
    * 	the available values for the "lycees agricoles"  types field.
    */
   @ModelAttribute("lyceesTypesItems")
   public List<FormField> populateLyceesTypes(HttpServletRequest request) {
	return getEntryFormFields(DataFormConstants.LYCEES_TYPES);
   }
       
   /**
    * Populate the monitoring  type field.
    * 
    * @param request
    * 			The request made by the user.
    * @return
    * 	the available values for the monitoring  type  field.
    */
   @ModelAttribute("monitoringTypeItems")
   public List<FormField> populateMonitoringType(HttpServletRequest request) {
	return getEntryFormFields(DataFormConstants.MONITORING_TYPE);
   }
   
   /**
    * Populate the sum on counties field.
    * 
    * @param request
    * 			The request made by the user.
    * @return
    * 	the available values for the  sum on counties field.<br/>
    * 	<b>NOTE :</b> normaly, only one available value should be present in this returned list.
    */
   @ModelAttribute("sumOnCountiesItems")
   public List<FormField> populateSumOnCounties(HttpServletRequest request) {
	return getEntryFormFields(DataFormConstants.SUM_ON_COUNTIES);
   }
   
   /**
    * Populate the users profiles field.
    * 
    * @param request
    * 			The request made by the user.
    * @return
    * 	the available values for the  users profiles field.
    */
   @ModelAttribute("usersProfilesItems")
   public List<FormField> populateUsersProfiles(HttpServletRequest request) {
	return getEntryFormFields(DataFormConstants.USERS_PROFILES);
   }
    
    //--------------------------------------------------------------------------- PROTECTED METHODS
    /**
     * Gets the items (labels, values, disable states) available for the specified entry.
     * @param entryName
     * 			The name of the entry associated to the items (labels and values).
     * 
     * @return
     * 	the available items (labels, values, disable states) for the entry.
     */
    protected List<FormField> getEntryFormFields(String entryName) {
	// Retrieval of the entry values
	List<EntryValue> entries = getDataFormService().getEntryValues(entryName);
	
	// Creation of the corresponding items (labels and values)
	List<FormField> formFields = new ArrayList<FormField>();
	for (EntryValue entry : entries) {
	    String label = entry.getI18nKey();
	    String value = entry.getJspKey();
	    
	    FormField formField = new FormField(label, value);
	    formFields.add(formField);
	    if (entry.isDisabledByDefault()) {
		formField.disable();
	    } else {
		formField.enable();
	    }
	}
	return formFields;
    }
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS

}
