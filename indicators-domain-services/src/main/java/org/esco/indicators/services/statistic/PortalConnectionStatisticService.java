/**
 * 
 */
package org.esco.indicators.services.statistic;

import java.util.List;


/**
 * Interface providing functions to access statistical data on the portal connections.
 * 
 * @since  2012/06/04
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public interface PortalConnectionStatisticService {

    ///////////////////////////////////////////////////////
    // WEEKLY STATISTICS
    ///////////////////////////////////////////////////////
    
    /**
     * Retrieves the average duration time of the connections made on the portal for the specified user profile
     * <code>userProfile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This average only concerns the week having the number <code>week</code> in the year <code>year</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     *      	the average duration time of the connections made on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Float findWeeklyConnectionsAverageDurationByProfile(String establishmentUai, String userProfile,
            Integer week, Integer year);

    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal of the  <code>establishmentsUai</code>.
     * This number of visitors only concerns the week having the number <code>week</code> in the year <code>year</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsAboveTreshold(List<String> establishmentsUai, Integer week, Integer year,
            Integer treshold);
    
    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal, and who has the specified 
     * <code>userProfile</code> the specified <code>establishmentUai</code>.
     * This number of visitors only concerns the week having the number <code>week</code> in the year <code>year</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsAboveTresholdByProfile(String establishmentUai, String userProfile, Integer week, Integer year, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal, and who has the specified 
     * <code>userProfile</code> the specified <code>establishmenstUai</code>.
     * This number of visitors only concerns the week having the number <code>week</code> in the year <code>year</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsAboveTresholdByProfile(List<String> establishmentsUai, String userProfile, Integer week, Integer year, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal of the specified <code>establishmentsUai</code>.
     * This number of visitors only concerns the week having the number <code>week</code> in the year <code>year</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsBelowTreshold(List<String> establishmentsUai, Integer week, Integer year, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal, and who has the specified 
     * <code>userProfile</code> the specified <code>establishmentUai</code>.
     * This number of visitors only concerns the week having the number <code>week</code> in the year <code>year</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsBelowTresholdByProfile(String establishmentUai, String userProfile, Integer week, Integer year, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal, and who has the specified 
     * <code>userProfile</code> the specified <code>establishmentsUai</code>.
     * This number of visitors only concerns the week having the number <code>week</code> in the year <code>year</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made less than <code>treshold</code> connections on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findWeeklyNumVisitorsBelowTresholdByProfile(List<String> establishmentsUai, String userProfile, Integer week, Integer year, Integer treshold);
    
    /**
     * Retrieves the number of connections made on the portal for the specified user profile
     * <code>userProfile</code> and the specified establishments UAI <code>establishmentsUai</code>.<br/>
     * This number of connections only concerns the week having the number <code>week</code> in the year <code>year</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param week
     * 			The week number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @return
     * 	the number of connections made on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved. 
     */
    public Integer findWeeklyNumConnectionsByProfile(List<String> establishmentsUai, String userProfile, Integer week, Integer year);
    
    ///////////////////////////////////////////////////////
    // MONTHLY STATISTICS
    ///////////////////////////////////////////////////////
    
    /**
     * Retrieves the average duration time of the connections made on the portal for the specified user profile
     * <code>userProfile</code> and the specified establishment UAI <code>establishmentUai</code>.<br/>
     * This average only concerns the month having the number <code>month</code> in the year <code>year</code>.
     * 
     * @param establishmentUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param month
     * 			The month number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * 
     * @return
     *      	the average duration time of the connections made on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Float findMonthlyConnectionsAverageDurationByProfile(String establishmentUai, String userProfile, Integer month, Integer year);

    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal of the <code>establishmentsUai</code>.
     * This number of connections only concerns the month having the number <code>month</code> of the year <code>year</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param month
     * 			The week month of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findMonthlyNumVisitorsAboveTreshold(List<String> establishmentsUai, Integer month, Integer year, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made more than <code>treshold</code> connections on the portal, and who has the specified 
     * <code>userProfile</code> the specified <code>establishmentsUai</code>.
     * This number of connections only concerns the month having the number <code>month</code> of the year <code>year</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishment sassociated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param month
     * 			The week month of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The minimum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findMonthlyNumVisitorsAboveTresholdByProfile(List<String> establishmentsUai, String userProfile, Integer month, Integer year, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal of the specified <code>establishmentUai</code>.
     * This number of connections only concerns the month having the number <code>month</code> of the year <code>year</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishment associated to the statistic to retrieve.
     * @param month
     * 			The week month of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findMonthlyNumVisitorsBelowTreshold(List<String> establishmentsUai, Integer month, Integer year, Integer treshold);
    
    /**
     * Retrieves the number of visitors that made less than <code>treshold</code> connections on the portal, and who has the specified 
     * <code>userProfile</code> the specified <code>establishmentsUai</code>.
     * This number of connections only concerns the month having the number <code>month</code> of the year <code>year</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param month
     * 			The week month of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @param treshold
     * 			The maximum number of connections treshold.
     * 
     * @return
     * 	the number of visitors who made more than <code>treshold</code> connections on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved.
     */
    public Integer findMonthlyNumVisitorsBelowTresholdByProfile(List<String> establishmentsUai, String userProfile, Integer month, Integer year, Integer treshold);
    
    /**
     * Retrieves the number of connections made on the portal for the specified user profile
     * <code>userprofile</code> and the specified establishments UAI <code>establishmentUai</code>.<br/>
     * This number of connections only concerns the month having the number <code>month</code> of the year <code>year</code>.
     * 
     * @param establishmentsUai
     * 			The UAI of the establishments associated to the statistic to retrieve.
     * @param userProfile
     * 			The user profile of the statistic to retrieve.
     * @param month
     * 			The month number of the statistic to retrieve.
     * @param year
     * 			The year of the statistic to retrieve.
     * @return
     * 	the number of connections made on the portal.<br/>
     * 	the number 0 if no statistic has been retrieved. 
     */
    public Integer findMonthlyNumConnectionsByProfile(List<String> establishmentsUai, String userProfile, Integer month, Integer year);
    
}
