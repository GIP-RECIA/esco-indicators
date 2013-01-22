/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.basic.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.FormField;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.constants.xml.DataFormConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Controller that handles requests on the form  used for the statistics of only one establishment.
 * 
 * @since  2012/10/29
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public abstract class BasicEstablishmentFormController extends BasicFormController  {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicEstablishmentFormController.class);

    /** Establishment service */
    @Autowired
    private EstablishmentService establishmentService;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of the {@link BasicEstablishmentFormController} class.
     * 
     * @param formSessionAttribute
     * 			The name of the attribute used to store the submitted form in the user session.
     */
    public BasicEstablishmentFormController(String formSessionAttribute) {
	super(formSessionAttribute);
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS


    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#populateCounties(javax.servlet.http.HttpServletRequest)
     */
    @Override
    @ModelAttribute("countyItems")
    public List<FormField> populateCounties(HttpServletRequest request) {
	// Fields for the county
	List<FormField> countyFields = new ArrayList<FormField>();
	
	// Puts the county of the establishment
	String uai = authenticator.getUser().getEstablishmentUAI();
	Establishment establishment = establishmentService.findEstablishmentByUai(uai);
	String county = "00";
	if(establishment != null) {
	    county = establishment.getCountyNumber();
	}
	FormField countyField = new FormField("", county);
	countyFields.add(countyField);
	
 	return countyFields;
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#populateEstablishments(javax.servlet.http.HttpServletRequest)
     */
    @Override
    @ModelAttribute("establishmentsItems")
    public List<String> populateEstablishments(HttpServletRequest request) {
	return  new ArrayList<String>(authenticator.getAllowedEstablishmentsUai());
    }
    
    /**
     * Populate the establishments names with form fields.<br/>
     * Each form field has : 
     * 	<ul>
     * 		<li>As label : The establishment name</li>
     * 		<li>As value : The establishment UAI</li>
     * 	</ul>
     * 
     * @param request
     * 			The request made by the user.
     * 
     * @return
     * 	the form fields containing the names and UAIs of the establishments.
     */
    @ModelAttribute("establishmentsNames")
    public List<FormField> populateEstablishmentsNames(HttpServletRequest request) {
	List<String> establishmentsUai = new ArrayList<String>(authenticator.getAllowedEstablishmentsUai());
	return getEstablishmentsFieldsByUai(establishmentsUai);
    }
    
    /* (non-Javadoc)
     * @see org.esco.indicators.web.springmvc.controller.basic.form.BasicFormController#populateEstablishmentsTypes(javax.servlet.http.HttpServletRequest)
     */
   @Override
   @ModelAttribute("establishmentsTypesItems")
   public List<FormField> populateEstablishmentsTypes(HttpServletRequest request) {
	// Final result
	List<FormField> formFields = new ArrayList<FormField>();
	
	// Retrieval of the current establishment type
	String establishmentUai = authenticator.getUser().getEstablishmentUAI();
	Establishment establishment = establishmentService.findEstablishmentByUai(establishmentUai);
	if(establishment != null) {
	    FormField establishmentField = new FormField(establishment.getName(), establishment.getType());
	    formFields.add(establishmentField);
	}
	return  formFields;
   }
    
   
    //----------------------------------------------------------------------------- PRIVATE METHODS
   /**
    * Gets the establishments list (in a FormField format) regarding to the given establishments UAI.
    * 
    * @param establishmentsUai
    * 			The UAI of the establishments.
    * 
    * @return
    * 		the list of form fields containing the establishments informations.<br/>
    * 		an empty list if no establishments has been retrieved.
    */
   private List<FormField> getEstablishmentsFieldsByUai(List<String> establishmentsUai) {
	// Retrieval of the establishments with the specified filters
	List<Establishment> establishments = establishmentService.findEstablishmentsByUais(establishmentsUai);
	
	// Translate the establishments into form fields
	List<FormField> formFields = new ArrayList<FormField>();
	for (Establishment establishment : establishments) {
	    FormField formField = new FormField(establishment.getName(), establishment.getUai());
	    formFields.add(formField);
	}
	
	return formFields;
   }
    //------------------------------------------------------------------------------ STATIC METHODS
}
