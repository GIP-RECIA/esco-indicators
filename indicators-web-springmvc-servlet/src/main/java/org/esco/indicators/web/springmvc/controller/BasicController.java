/**
 * 
 */
package org.esco.indicators.web.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.services.form.DataFormService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class representing a basic controller containing basic functions.
 * 
 * @since  2012/07/09
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public abstract class BasicController {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicController.class);

    /** Data form service providing information on the data from */
    @Autowired
    protected DataFormService dataFormService;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //--------------------------------------------------------------------------- PROTECTED METHODS
    /**
     * Remove the unknown JSP keys from the list.<br/>
     * Return a copy of the specified keys only containing known keys.
     * 
     * @param jspKeys
     * 			The JSP keys to verify.
     * 
     * @return
     * 	a copy of the specified list without the unknown JSP keys.
     */
    protected List<String> removeUnknownJspKeys(List<String> jspKeys) {
	// Final result
	List<String> knownKeys = new ArrayList<String>();
	
	// Only keep the known keys
	for (String jspKey : jspKeys) {
	    if(dataFormService.isKnown(jspKey)) {
		knownKeys.add(jspKey);
	    }
	}
	
	return knownKeys;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
