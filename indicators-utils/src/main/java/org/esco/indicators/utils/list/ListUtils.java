/**
 * 
 */
package org.esco.indicators.utils.list;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.util.IntegerPair;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Months;


/**
 * Class providing utils functions for manipulating lists.
 * 
 * @since 2012/07/25
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ListUtils {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ListUtils.class);

    
    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Indicates if two lists have an intersection.
     * 
     * @param first
     * 			The first list.
     * @param second
     * 			The second list.
     * 
     * @return
     * 	<code>true</code> if there is an intersection between the lists.<br/>
     * 	<code>false</code> in other cases.
     */
    public static boolean haveIntersection(List<String> first, List<String> second) {
	for (String elementOfSecond : second) {
	    if(first.contains(elementOfSecond)) {
		return true;
	    }
	}
	return false;
    }
    
    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
