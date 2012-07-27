/**
 * 
 */
package org.esco.indicators.services.form.account;

import java.util.Date;
import java.util.List;

import org.esco.indicators.domain.beans.form.AccountActivationForm;
import org.esco.indicators.domain.beans.result.BasicResultRow;

/**
 * Interface providing functions for retrieving the result data to put into the result web page
 * concerning the account statistics.
 * 
 * @since  2012/07/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface ResultAccountFormService {

    ///////////////////////////////////////////////////////
    // WEEKLY RESULTS
    ///////////////////////////////////////////////////////
    /**
     * Gets the result rows containing the data on the establishements and the users profiles.<br/>
     * These data only concern the specified <code>week</code> of the specified <code>year</code>.<br/>
     * Each result row is associated to one establishment, and contained two kinds of data :
     * <ul>
     * 	<li>The establishment data (name,...)</li>
     * 	<li>The statistic data  (number of active accounts,...) indexed by user profile</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per user profile.<br/>
     * Moreover, each result row contains a statistic data that represents a global statistic on all the accounts of the establishment.<br/>
     * For more informations on the result row content, see {@link BasicResultRow}.
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
    public List<BasicResultRow> getPunctualWeekResultRows(List<String> establishmentsUai, List<String> usersProfiles, Integer week, Integer year);
    
    /**
     * Gets the result rows containing the data on the establishements for the specified period (composed of weeks).<br/>
     * These data only concern the period beginning with the specified <code>startWeek</code> of the <code>startYear</code>, and finishing with the <code>endWeek</code> of the <code>endYear</code>.<br/>
     * Each result row is associated to one establishment, and contained two kinds of data :
     * <ul>
     * 	<li>The establishment data (name,...)</li>
     * 	<li>The statistic data  (number of active accounts,...) indexed by week and year</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per week contained in the period.<br/>
     * For more informations on the result row content, see {@link BasicResultRow}.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments.
     * @param userProfile
     * 			The user profile.
     * @param startWeek
     * 			The number of the beginning week (in the beginning year).
     * @param startYear
     * 			The beginning year.
     * @param endWeek
     * 			The number of the finishing week (in the finishing year).
     * @param endYear
     * 			The number of the finishing year.
     * 
     * @return
     * 	the result rows containing establishment data, and statistics data, for each period of one week in each establishment.
     */
    public List<BasicResultRow> getPeriodicWeekResultRows(List<String> establishmentsUai, String userProfile, Integer startWeek, Integer startYear, Integer endWeek, Integer endYear);
    
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
    public List<BasicResultRow> getPunctualMonthResultRows(List<String> establishmentsUai, List<String> usersProfiles, Integer month, Integer year);
    
    /**
     * Gets the result rows containing the data on the establishements for the specified period (composed of months).<br/>
     * These data only concern the period beginning with the specified <code>startMonth</code> of the <code>startYear</code>, and finishing with the <code>endMonth</code> of the <code>endYear</code>.<br/>
     * Each result row is associated to one establishment, and contained two kinds of data :
     * <ul>
     * 	<li>The establishment data (name,...)</li>
     * 	<li>The statistic data  (number of active accounts,...) indexed by month and year</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per month contained in the period.<br/>
     * For more informations on the result row content, see {@link BasicResultRow}.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments.
     * @param userProfile
     * 			The user profile.
     * @param startMonth
     * 			The number of the beginning month (in the beginning year).
     * @param startYear
     * 			The beginning year.
     * @param endMonth
     * 			The number of the finishing month (in the finishing year).
     * @param endYear
     * 			The number of the finishing year.
     * 
     * @return
     * 	the result rows containing establishment data, and statistics data, for each period of one week in each establishment.
     */
    public List<BasicResultRow> getPeriodicMonthResultRows(List<String> establishmentsUai, String userProfile, Integer startMonth, Integer startYear, Integer endMonth, Integer endYear);
    
}
