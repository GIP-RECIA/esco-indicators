/**
 * 
 */
package org.esco.indicators.domain.beans.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.esupportail.commons.utils.Assert;
import org.springframework.beans.factory.InitializingBean;

/**
 * Class representing a permission for viewing informations on establishments for a user.<br/>
 * A permission contains :
 * <ul>
 * 	<li>A pattern</li>
 * 	<li>A list of {@link AttributeNameAndValue}</li>
 * </ul>
 * 
 * This {@link EstablishmentViewPermission} works as follows :<br/>
 * If the pattern is matched by one of the groups of the user, then the user is allowed to see 
 * the establishments having one of its attribute equal to their associated values.<br/>
 * The attribute value can contain references to groups that have been matched in the pattern.<br/>
 * <br/>
 *  
 * <u>For instance :</u> if the permission is defined as follows :
 * <ul>
 * 	<li>Pattern = "user.admin.establishment.([0-9]+)"</li>
 * 	<li>List of attributes and values :</li>
 * 		<ul>
 * 			<li>Establishment attribute = "uai"</li>
 * 			<li>Attribute value = "id_$1"</li>
 * 			<li>Attribute value = "short_id_$1"</li>
 * 		</ul>
 * 		<ul>
 * 			<li>Establishment attribute = "type"</li>
 * 			<li>Attribute value = "college"</li>
 * 		</ul>
 * </ul>
 *
 * Then the user having a group named : "user.admin.establishment.784569" will have the permission
 * to see the informations concerning the establishments having "uai" == "id_784569" or "uai" == "short_id_784569" or "type" == "college".
 * 
 * @since  2012/10/03
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class EstablishmentViewPermission implements InitializingBean {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(EstablishmentViewPermission.class);
    
    /** Pattern that has to be matched */
    private String patternToMatch;
    
    /** Etablishments that can be viewed if the pattern is matched */
    private HashMap<String, List<String>> attributesAndValues;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	Assert.notNull(patternToMatch, 
		"property patternToMatch of class " + this.getClass().getName() + " can not be null");
	Assert.notNull(attributesAndValues, 
		"property attributesAndValues of class " + this.getClass().getName() + " can not be null");
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the pattern to match.
     * 
     * @return 
     * 		the pattern to match.
     */
    public String getPatternToMatch() {
        return patternToMatch;
    }

    /**
     * Sets the pattern to match.
     * 
     * @param patternToMatch 
     * 			The pattern to match to set.
     */
    public void setPatternToMatch(String patternToMatch) {
        this.patternToMatch = patternToMatch;
    }

    /**
     * Gets the attributes names available. 
     * 
     * @return 
     * 	the attributes names.
     */
    public Set<String> getAttributesNames() {
        return attributesAndValues.keySet();
    }
    
    /**
     * Gets the attributes values associated to an attribute name of the allowed establishments. 
     * 
     * @param attributeName
     * 			The name of the attribute.
     * @return 
     * 	the attributes and values.
     */
    public List<String> getValuesByAttribute(String attributeName) {
        return attributesAndValues.get(attributeName);
    }

    /**
     * Sets the attributes and values of the allowed establishments.
     * 
     * @param attributesAndValues 
     * 			The attributes and values to set.
     */
    public void setAttributesAndValues(HashMap<String, List<String>> attributesAndValues) {
        this.attributesAndValues = attributesAndValues;
    }

    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
