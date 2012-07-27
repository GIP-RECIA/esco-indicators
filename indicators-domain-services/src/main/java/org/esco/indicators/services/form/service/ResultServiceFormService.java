/**
 * 
 */
package org.esco.indicators.services.form.service;

import java.util.List;

import org.esco.indicators.domain.beans.result.BasicResultRow;

/**
 * Interface providing functions for retrieving the result data to put into the result web page
 * concernaning the services statistics.
 * 
 * @since  2012/07/25
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface ResultServiceFormService {

    ///////////////////////////////////////////////////////
    // WEEKLY RESULTS
    ///////////////////////////////////////////////////////
    /**
     * Gets the result rows containing the data on the establishements, the services and the user profile.<br/>
     * These data only concern the specified <code>week</code> of the specified <code>year</code>.<br/>
     * Each result row is associated to one establishment, and contained two kinds of data :
     * <ul>
     * 	<li>The establishment data (name,...)</li>
     * 	<li>The statistic data  (number of visits,...) indexed by service</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per service.<br/>
     * For more informations on the result row content, see {@link BasicResultRow}.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishements.
     * @param services
     * 			The services.
     * @param userProfile
     * 			The user profile.
     * @param week
     * 			The week number.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the result rows containing establishment data, and statistics data, for each user profile in each establishment.
     */
    public List<BasicResultRow> getPunctualWeekResultRows(List<String> establishmentsUai, List<String> services, String userProfile, Integer week, Integer year);
    
    
    ///////////////////////////////////////////////////////
    // MONTHLY RESULTS
    ///////////////////////////////////////////////////////
    /**
     * Gets the result rows containing the data on the establishements and the users profiles.<br/>
     * These data only concern the specified <code>month</code> of the specified <code>year</code>.<br/>
     * Each result row is associated to one establishment, and contained two kinds of data :
     * <ul>
     * 	<li>The establishment data (name,...)</li>
     * 	<li>The statistic data  (number of active accounts,...) indexed by user profile</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per user profile.<br/>
     * For more informations on the result row content, see {@link BasicResultRow}.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishements.
     * @param services
     * 			The services.
     * @param userProfile
     * 			The user profile.
     * @param month
     * 			The month number.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the result rows containing establishment data, and statistics data, for each user profile in each establishment.
     */
//    public List<BasicResultRow> getPunctualMonthResultRows(List<String> establishmentsUai, List<String> services, String userProfile, Integer month, Integer year);
    
    
}
