/**
 * 
 */
package org.esco.indicators.services.form;

import java.util.Date;
import java.util.List;

import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.result.ResultRow;

/**
 * Interface providing functions for retrieving the result data to put into the result web page.
 * 
 * @since  2012/07/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface ResultFormService {

    ///////////////////////////////////////////////////////
    // WEEKLY RESULTS
    ///////////////////////////////////////////////////////
    /**
     * Gets the result rows containing the data on the establishements and the users profiles.<br/>
     * These data only concern the specified <code>week</code> of the specified <code>year</code>.<br/>
     * Each result row is associated to one establishment, and contained two kinds of data :
     * <ul>
     * 	<li>The establishment data (name,...)</li>
     * 	<li>The statistic data on each user profile (number of active accounts,...)</li>
     * </ul>
     * For more informations on the result row content, see {@link ResultRow}.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishements.
     * @param usersProfiles
     * 			The users profiles.
     * @param week
     * 			The week number.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the result rows containing establishment data, and statistics data, for each user profile in each establishment.
     */
    public List<ResultRow> getPunctualWeekResultRows(List<String> establishmentsUai, List<String> usersProfiles, Integer week, Integer year);
    
    public List<ResultRow> getWeeklyResultRows(List<String> establishmentsUai, List<String> usersProfiles, Integer startWeek, Integer startYear, Integer endWeek, Integer endYear);
    
    ///////////////////////////////////////////////////////
    // MONTHLY RESULTS
    ///////////////////////////////////////////////////////
    /**
     * Gets the result rows containing the data on the establishements and the users profiles.<br/>
     * These data only concern the specified <code>month</code> of the specified <code>year</code>.<br/>
     * Each result row is associated to one establishment, and contained two kinds of data :
     * <ul>
     * 	<li>The establishment data (name,...)</li>
     * 	<li>The statistic data on each user profile (number of active accounts,...)</li>
     * </ul>
     * For more informations on the result row content, see {@link ResultRow}.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishements.
     * @param usersProfiles
     * 			The users profiles.
     * @param month
     * 			The month number.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the result rows containing establishment data, and statistics data, for each user profile in each establishment.
     */
    public List<ResultRow> getPunctualMonthResultRows(List<String> establishmentsUai, List<String> usersProfiles, Integer month, Integer year);
    
    public List<ResultRow> getMonthlyResultRows(List<String> establishmentsUai, List<String> usersProfiles, Integer startMonth, Integer startYear, Integer endMonth, Integer endYear);
    
}
