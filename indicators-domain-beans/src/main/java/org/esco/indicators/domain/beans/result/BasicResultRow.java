/**
 * 
 */
package org.esco.indicators.domain.beans.result;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Class representing a row present into the establishment table of the result page.
 * 
 * @since  2012/07/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class BasicResultRow {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(BasicResultRow.class);

    /** Data concerning the establishment */
    private EstablishmentData establishmentData;
    
    /** Data concerning the statistics classified by a key */
    private Map<Object,Object> statisticDataByKey;

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link BasicResultRow} class. 
     */
    public BasicResultRow() {
	super();
	statisticDataByKey = new HashMap<Object, Object>();
    }

    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the data concerning the establishment.
     * 
     * @return 
     * 	the data concerning the establishment.
     */
    public EstablishmentData getEstablishmentData() {
        return establishmentData;
    }

    /**
     * Sets the data concerning the establishment.
     * 
     * @param establishmentData 
     * 			The data concerning the establishment.
     */
    public void setEstablishmentData(EstablishmentData establishmentData) {
        this.establishmentData = establishmentData;
    }
    
    /**
     * Gets the statistic data map.
     * 
     * @return
     * 	the statistic data map.
     */
    public Map<Object,Object> getStatisticDataByKey() {
	return statisticDataByKey;
    }
    
    /**
     * Gets the statistic data associated to the key.
     * 
     * @param key
     * 			The key associated to the statistic data.
     * 
     * @return
     * 	the statistic data associated to the key.<br/>
     * 	<code>null</code> if no data is associated to the key.
     */
    public Object getStatisticData(Object key) {
	return statisticDataByKey.get(key);
    }
    
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    
    /**
     * Puts a statistic data into this result row.
     * 
     * @param key
     * 			The key associated to the statistic data.
     * @param value
     * 			The value of the statistic data value to insert.
     * 
     * @return
     * 	the previous value associated with the key, or <code>null</code> if no value was associated.
     */
    public Object putStatisticData(Object key, Object value) {
	return statisticDataByKey.put(key, value);
    }

    

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
