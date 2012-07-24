/**
 * 
 */
package org.esco.indicators.web.springmvc.controller.basic.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.form.BasicForm;
import org.esco.indicators.services.form.DataFormService;
import org.esco.indicators.services.form.ResultFormService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Basic controller used to populate commons fields in the result controllers displaying
 * the results after a form submission.
 * 
 * @since  2012/07/10
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public abstract class BasicResultController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicResultController.class);

    /** Name of the attribute used to retrieve the submitted form in the user session */
    protected String formSessionAttribute;
    
    /** Name of the view associated to the controller */
    protected String viewName;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Constructor of {@link BasicResultController}.
     * 
     * @param viewName
     * 			The view name associated to the controller.
     * @param formSessionAttribute
     * 			The session attribute containing the submitted form.
     */
    public BasicResultController(String viewName, String formSessionAttribute) {
	super();
	this.viewName = viewName;
	this.formSessionAttribute = formSessionAttribute;
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Abstract method providing access to the data form service.
     * 
     * @return
     * 	the data form service
     */
    public abstract DataFormService getDataFormService(); 
    
    /**
     * Abstract method providing access to the establishment service.
     * 
     * @return
     * 	the establishment service
     */
    public abstract EstablishmentService getEstablishmentService(); 
    
    /**
     * Abstract method providing access to the result form service.
     * 
     * @return
     * 	the result form service
     */
    public abstract ResultFormService getResultService(); 
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Initializes the result view and redirect to the view.
     * 
     * @param model
     * 			Model data.
     * @param request
     * 			Request made by the user.
     * @return
     * 	the JSP view name.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model, HttpServletRequest request){
	// Verification of the presence of a submitted form
	if(!containsForm(request.getSession(), formSessionAttribute)) {
	    LOGGER.warn("No submitted form has been found in the user session, so there is no data to process and display. The user is redirected to the welcome page.");
	    return "redirect:welcome";
	}
	
        // Cleaning of the session
	request.getSession().setAttribute(formSessionAttribute, null);
	return viewName;
    }


    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    /**
     * Populate the county field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted value for the county field.
     */
    @ModelAttribute("countyItem")
    public String populateCounty(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String jspKey = form.getCounty();
        String i18nKey = null;
        if(jspKey != null) {
            i18nKey = getDataFormService().getI18nKey(jspKey);
        }
    
        return i18nKey;
    }

    /**
     * Populate the establishments types field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted values for the establishments types field.
     */
    @ModelAttribute("estbalishmentsTypesItems")
    public List<String> populateEstablishmentsTypes(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String [] establishmentsTypes = form.getEstablishmentsTypes();
        List<String> jspKeys = new ArrayList<String>(Arrays.asList(establishmentsTypes));
        List<String> i18nKeys = getI18nKeys(jspKeys);
        
        return i18nKeys;
    }

    /**
     * Populate the 'lycees' types field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted values for the 'lycees' types field.
     */
    @ModelAttribute("lyceesTypesItems")
    public List<String> populateLyceesTypes(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String [] lyceesTypes = form.getLyceesTypes();
        List<String> i18nKeys = null;
        if(lyceesTypes != null) {
        	List<String> jspKeys = new ArrayList<String>(Arrays.asList(lyceesTypes));
        	i18nKeys = getI18nKeys(jspKeys);
        }
        
        return i18nKeys;
    }

    /**
     * Populate the 'la' types field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted values for the 'la' types field.
     */
    @ModelAttribute("laTypesItems")
    public List<String> populateLaTypes(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String [] lyceesTypes = form.getLaTypes();
        List<String> i18nKeys = null;
        if(lyceesTypes != null) {
        	List<String> jspKeys = new ArrayList<String>(Arrays.asList(lyceesTypes));
        	i18nKeys = getI18nKeys(jspKeys);
        }
        
        return i18nKeys;
    }

    /**
     * Populate the monitoring  type field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted value for the monitoring  type  field.
     */
    @ModelAttribute("monitoringTypeItem")
    public String populateMonitoringType(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String jspKey = form.getMonitoringType();
        String i18nKey = getDataFormService().getI18nKey(jspKey);
        
        return i18nKey;
    }

    /**
     * Populate the start date field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted value for the start date field.
     */
    @ModelAttribute("startDateItem")
    public String populateStartDate(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the start date
        String startDate = form.getStartDatePicker();
        
        return startDate;
    }

    /**
     * Populate the sum on counties field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted value for the sum on counties field.
     */
    @ModelAttribute("sumOnCountiesItem")
    public String populateSumOnCounties(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String jspKey = form.getSumOnCounties();
        String i18nKey = null;
        if(jspKey != null) {
            i18nKey = getDataFormService().getI18nKey(jspKey);
        }
        
        return i18nKey;
    }

    /**
     * Populate the users profiles field.
     * 
     * @param request
     * 			The request made by the user.
     * @return
     * 	the submitted values for the users profiles field.
     */
    @ModelAttribute("usersProfilesItems")
    public List<String> populateUsersProfiles(HttpServletRequest request) {
        // Checks if the there is a valid submitted form to process
        if(!containsForm(request.getSession(), formSessionAttribute)) {
            return null;
        }
        
        // Retrieval of the submitted monitoring type value
        BasicForm form =  getSessionForm(request.getSession(), formSessionAttribute);
        
        // Retrieval of the i18n key
        String [] usersProfiles = form.getUsersProfiles();
        List<String> jspKeys = new ArrayList<String>(Arrays.asList(usersProfiles));
        List<String> i18nKeys = getI18nKeys(jspKeys);
        
        return i18nKeys;
    }

    
    //--------------------------------------------------------------------------- PROTECTED METHODS
    /**
     * Gets the form associated to the session attribute : <code>formAttribute</code>.
     * 
     * @param session
     * 			The user session.
     * @param formAttribute
     * 			The session attribute associated to the form.
     * 
     * @return
     * 	the form associated to the session attribute : <code>formAttribute</code>.<br/>.
     * 	<code>null</code> if no form is associated to the session attribute.
     */
    protected BasicForm getSessionForm(HttpSession session, String formAttribute) {
        // Retrieval of the form
        return (BasicForm) session.getAttribute(formAttribute);
    }
    
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    /**
     * Checks if the specified session contains a form associated to the session attribute <code>formAttribute</code>.<br/>
     * 
     * @param session
     * 			The user session.
     * @param formAttribute
     * 			The session attribute associated to the form.
     * 
     * @return
     * 	<code>true</code> if the session contained a form associated to <code>formAttribute</code>.<br/>
     * 	<code>false</code> in other cases.
     */
    protected boolean containsForm(HttpSession session, String formAttribute) {
	// Retrieval of the associated form
	BasicForm basicForm = (BasicForm) session.getAttribute(formAttribute);
	if(basicForm == null) {
	    return false;
	}
	return true;
    }
    
    /**
     * Gets the i18n keys associated to the specified JSP keys.
     * 
     * @param jspKeys
     * 			The JSP keys associated to the i18n keys to retrieve.
     * 
     * @return
     * 	the list of the i18n keys associated to the JSP keys.<br/>
     * 	an empty list if no i18n keys has been retrieved.
     */
    private List<String> getI18nKeys(List<String> jspKeys) {
        // Retrieval of the i18n keys
        List<String> i18nKeys = new ArrayList<String>();
        for (String jspKey : jspKeys) {
            i18nKeys.add(getDataFormService().getI18nKey(jspKey));
        }
        return i18nKeys;
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
