/**
 * 
 */
package org.esco.indicators.services.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.esco.indicators.domain.beans.result.EstablishmentData;
import org.esco.indicators.domain.beans.result.ResultRow;
import org.esco.indicators.domain.beans.result.StatisticData;
import org.esco.indicators.domain.beans.structure.Establishment;
import org.esco.indicators.services.statistic.PortalConnectionStatisticService;
import org.esco.indicators.services.structure.EstablishmentService;
import org.esco.indicators.utils.date.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Months;
import org.joda.time.ReadableDuration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of the {@link ResultFormService} interface.
 * 
 * @since  2012/07/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ResultFormServiceImpl implements ResultFormService {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ResultFormServiceImpl.class);
    
    /** Service providing access to establishment data */
    @Autowired
    private EstablishmentService establishmentService;
    
    /** Service providing access to the portal connection statistics */
    @Autowired
    private PortalConnectionStatisticService portalConnectionStatisticService;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS


    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Sets the establishment service.
     * @param establishmentService 
     * 				The establishment service to set.
     */
    public void setEstablishmentService(EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS
    
    /**
     * Gets the establishment data for the specified UAI.
     * 
     * @param uai
     * 			The UAI of the establishment.
     * 
     * @return
     * 	the result data associated to the establishment.<br/>
     * 	<code>null</code> if no establishment data has been retrieved.
     */
    private EstablishmentData getEstablishmentData(String uai) {
	// Gets the establishment
	Establishment establishment = establishmentService.findEstablishmentByUai(uai);
	if(establishment == null) {
	    return null;
	}
	
	// Map establishment to establishment data
	String countyNumber = establishment.getCountyNumber().toString();
	String name = establishment.getName();
	String establishmentType = establishment.getType();
	EstablishmentData data = new EstablishmentData(countyNumber, name, establishmentType, uai);
	
	return data;
    }
    
    /**
     * Gets the establishment data for the specified UAIs.
     * 
     * @param uais
     * 			The UAIs of the establishment.
     * 
     * @return
     * 	the result data associated to the establishments.<br/>
     * 	an empty list if no data has been retrieved.
     */
    private List<EstablishmentData> getEstablishmentsData(List<String> uais) {
	// Final result
	List<EstablishmentData> data = new ArrayList<EstablishmentData>();
	
	for (String uai : uais) {
	    EstablishmentData establishmentData = getEstablishmentData(uai);
	    if(establishmentData != null) {
		data.add(establishmentData);
	    } else {
		LOGGER.warn("No establishment  data has been retrieved for the UAI : [" + uai + "]");
	    }
	}
	
	return data;
    }
    
     
    //------------------------------------------------------------------------------ STATIC METHODS
}
