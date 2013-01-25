/**
 * 
 */
package org.esco.indicators.services.form.service;

import java.util.List;

import org.esco.indicators.domain.beans.result.BasicResultRow;
import org.esco.indicators.domain.beans.result.DetailResultRow;
import org.esco.indicators.domain.beans.result.ExtendedResultRow;
import org.esco.indicators.utils.classes.IntegerPair;

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
     * Gets the result rows containing the data on the establishements for the specified period (composed of weeks).<br/>
     * These data only concern the period beginning with the specified <code>startWeek</code> of the <code>startYear</code>, and finishing with the <code>endWeek</code> of the <code>endYear</code>.<br/>
     * Each result row is associated to one establishment and one week, and contained two kinds of data :
     * <ul>
     * 	<li>The establishment data (name,...)</li>
     * 	<li>The statistic data on the services indexed by :
     * 		<ul>
     * 			<li>First level : A pair of week and year (see {@link IntegerPair})</li>
     * 			<li>Second level : A service name</li>
     * 		</ul>
     * 	</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per week contained in the period.<br/>
     * For more informations on the result row content, see {@link ExtendedResultRow}.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments.
     * @param services
     * 			The services.
     * @param usersProfiles
     * 			The users profiles.
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
    public List<ExtendedResultRow> getPeriodicWeekResultRows(List<String> establishmentsUai,
	    List<String> services, List<String> usersProfiles, Integer startWeek, Integer startYear, Integer endWeek,
	    Integer endYear);
    
    /**
     * Gets the result rows containing the data on the establishements for the specified period (composed of weeks).<br/>
     * The data on the establishments are aggregated by county numbers.<br/>
     * These data only concern the period beginning with the specified <code>startWeek</code> of the <code>startYear</code>, and finishing with the <code>endWeek</code> of the <code>endYear</code>.<br/>
     * Each result row is associated to one establishment and one week, and contained two kinds of data :
     * <ul>
     * 	<li>The county data (county number)</li>
     * 	<li>The statistic data on the services indexed by :
     * 		<ul>
     * 			<li>First level : A pair of week and year (see {@link IntegerPair})</li>
     * 			<li>Second level : A service name</li>
     * 		</ul>
     * 	</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per week contained in the period.<br/>
     * For more informations on the result row content, see {@link ExtendedResultRow}.
     * 
     * @param countyNumbers
     * 			The county numbers used to aggregate establishments.
     * @param establishmentsTypes
     * 			The establishments types.
     * @param services
     * 			The services.
     * @param usersProfiles
     * 			The users profiles.
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
     * 	the result rows containing county data, and statistics data, for each period of one week in each county.
     */
    public List<ExtendedResultRow> getPeriodicWeekResultRows(List<String> countyNumbers,
            List<String> establishmentsTypes, List<String> services, List<String> usersProfiles, Integer startWeek,
            Integer startYear, Integer endWeek, Integer endYear);

    /**
     * Gets the result rows containing the data on the establishements, the services and the users profiles.<br/>
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
    public List<ExtendedResultRow> getPunctualWeekResultRows(List<String> establishmentsUai, List<String> services, List<String> usersProfiles, Integer week, Integer year);

    /**
     * Gets the result rows containing the data on the establishement, the services and the user profiles.<br/>
     * These data only concern the specified <code>week</code> of the specified <code>year</code>.<br/>
     * Each result row is associated to one user profile, and contained two kinds of data :
     * <ul>
     * 	<li>The establishment data</li>
     * 	<li>The statistic data  (number of visits,...) indexed by service</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per service.<br/>
     * For more informations on the result row content, see {@link BasicResultRow}.
     * 
     * @param establishmentUai
     * 			The UAI of the establishement.
     * @param services
     * 			The services.
     * @param userProfiles
     * 			The user profiles.
     * @param week
     * 			The week number.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the result rows containing establishment data, and statistics data, for each user profile.
     */
    public List<ExtendedResultRow> getPunctualWeekResultRows(String establishmentUai, List<String> services, List<String> userProfiles, Integer week, Integer year);
    
    /**
     * Gets the result rows containing the data on the establishements, the services and the users profiles.<br/>
     * The data on the establishments are aggregated by county numbers.<br/>
     * These data only concern the specified <code>week</code> of the specified <code>year</code>.<br/>
     * Each result row is associated to one establishment, and contained two kinds of data :
     * <ul>
     * 	<li>The county data (county number)</li>
     * 	<li>The statistic data  (number of visits,...) indexed by service</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per service.<br/>
     * For more informations on the result row content, see {@link BasicResultRow}.
     * 
     * @param countyNumbers
     * 			The county numbers used to aggregate establishments.
     * @param establishmentsTypes
     * 			The establishments types.
     * @param services
     * 			The services.
     * @param usersProfiles
     * 			The users profiles.
     * @param week
     * 			The week number.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the result rows containing county data, and statistics data, for each user profile in each county.
     */
    public List<ExtendedResultRow> getPunctualWeekResultRows(List<String> countyNumbers,
            List<String> establishmentsTypes, List<String> services, List<String> usersProfiles, Integer week,
            Integer year);

    ///////////////////////////////////////////////////////
    // MONTHLY RESULTS
    ///////////////////////////////////////////////////////
    /**
     * Gets the result rows containing the data on the establishements for the specified period (composed of months).<br/>
     * These data only concern the period beginning with the specified <code>startMonth</code> of the <code>startYear</code>, and finishing with the <code>endMonth</code> of the <code>endYear</code>.<br/>
     * Each result row is associated to one establishment and one month, and contained two kinds of data :
     * <ul>
     * 	<li>The establishment data (name,...)</li>
     * 	<li>The statistic data on the services indexed by :
     * 		<ul>
     * 			<li>First level : A pair of month and year (see {@link IntegerPair})</li>
     * 			<li>Second level : A service name</li>
     * 		</ul>
     * 	</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per month contained in the period.<br/>
     * For more informations on the result row content, see {@link ExtendedResultRow}.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments.
     * @param services
     * 			The services.
     * @param usersProfiles
     * 			The users profiles.
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
     * 	the result rows containing establishment data, and statistics data, for each period of one month in each establishment.
     */
    public List<ExtendedResultRow> getPeriodicMonthResultRows(List<String> establishmentsUai,
	    List<String> services, List<String> usersProfiles, Integer startMonth, Integer startYear, Integer endMonth,
	    Integer endYear);
    
    /**
     * Gets the result rows containing the data on the establishements for the specified period (composed of months).<br/>
     * The data on the establishments are aggregated by county numbers.<br/>
     * These data only concern the period beginning with the specified <code>startMonth</code> of the <code>startYear</code>, and finishing with the <code>endMonth</code> of the <code>endYear</code>.<br/>
     * Each result row is associated to one establishment and one week, and contained two kinds of data :
     * <ul>
     * 	<li>The county data (county number)</li>
     * 	<li>The statistic data on the services indexed by :
     * 		<ul>
     * 			<li>First level : A pair of month and year (see {@link IntegerPair})</li>
     * 			<li>Second level : A service name</li>
     * 		</ul>
     * 	</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per week contained in the period.<br/>
     * For more informations on the result row content, see {@link ExtendedResultRow}.
     * 
     * @param countyNumbers
     * 			The county numbers used to aggregate establishments.
     * @param establishmentsTypes
     * 			The establishments types.
     * @param services
     * 			The services.
     * @param usersProfiles
     * 			The users profiles.
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
     * 	the result rows containing county data, and statistics data, for each period of one month in each county.
     */
    public List<ExtendedResultRow> getPeriodicMonthResultRows(List<String> countyNumbers,
            List<String> establishmentsTypes, List<String> services, List<String> usersProfiles, Integer startMonth,
            Integer startYear, Integer endMonth, Integer endYear);

    /**
     * Gets the result rows containing the data on the establishements and the user profile.<br/>
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
    public List<ExtendedResultRow> getPunctualMonthResultRows(List<String> establishmentsUai,  List<String> services, List<String> usersProfiles, Integer month, Integer year);
    
    /**
     * Gets the result rows containing the data on the establishement and the user profiles.<br/>
     * These data only concern the specified <code>month</code> of the specified <code>year</code>.<br/>
     * Each result row is associated to one establishment, and contained two kinds of data :
     * <ul>
     * 	<li>The establishment data</li>
     * 	<li>The statistic data  (number of active accounts,...) indexed by user profile</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per user profile.<br/>
     * For more informations on the result row content, see {@link BasicResultRow}.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment.
     * @param services
     * 			The services.
     * @param userProfiles
     * 			The user profiles.
     * @param month
     * 			The month number.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the result rows containing establishment data, and statistics data, for each user profile in the establishment.
     */
    public List<ExtendedResultRow> getPunctualMonthResultRows(String establishmentUai,  List<String> services, List<String> userProfiles, Integer month, Integer year);

    /**
     * Gets the result rows containing the data on the establishements, the services and the users profiles.<br/>
     * The data on the establishments are aggregated by county numbers.<br/>
     * These data only concern the specified <code>month</code> of the specified <code>year</code>.<br/>
     * Each result row is associated to one establishment, and contained two kinds of data :
     * <ul>
     * 	<li>The county data (county number)</li>
     * 	<li>The statistic data  (number of visits,...) indexed by service</li>
     * </ul>
     * In fact, in each result row, there is one statistic data per service.<br/>
     * For more informations on the result row content, see {@link BasicResultRow}.
     * 
     * @param countyNumbers
     * 			The county numbers used to aggregate establishments.
     * @param establishmentsTypes
     * 			The establishments types.
     * @param services
     * 			The services.
     * @param usersProfiles
     * 			The users profiles.
     * @param month
     * 			The month number.
     * @param year
     * 			The year.
     * 
     * @return
     * 	the result rows containing county data, and statistics data, for each user profile in each county.
     */
    public List<ExtendedResultRow> getPunctualMonthResultRows(List<String> countyNumbers,
	    List<String> establishmentsTypes, List<String> services, List<String> usersProfiles, Integer month,
	    Integer year);

}
