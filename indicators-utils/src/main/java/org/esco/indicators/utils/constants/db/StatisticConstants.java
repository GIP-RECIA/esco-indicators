/**
 * 
 */
package org.esco.indicators.utils.constants.db;

/**
 * Enumeration providing all the constants used in the statistical database.
 * 
 * @since 2012/06/01
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class StatisticConstants {

    ///////////////////////////////////////////////////////
    // BEAN : EstablishmentVisitStatistic 
    ///////////////////////////////////////////////////////
    
    // FIELD : typeStat
    
    /** 
     * Value indicating that the statistic is not an aggregation nor a sum of others statistics.<br/>
     * In this case the statistic contains informations concerning only one establishment.
     */
    public static final String TYPE_STAT_ESTABLISHMENT = "Etablissement";
    
    /**
     * Value indicating that the statistic is an aggregation or a sum made on a county number and an
     * establishment type.<br/>
     * In this case the establishmentUai field is used to store the county number used to made the sum.
     */
    public static final String TYPE_STAT_SUM_ONE_COUNTY_ONE_ESTABLISHMENT_TYPE = "DeptType";
    
    /** 
     * Value indicating that the statistic is an aggregation or a sum made on a county number.<br/>
     * In this case the establishment type is not considered and all the establishments associated to the
     * county number are aggregated.<br/>
     * In this case the establishmentUai field is used to store the county number used to made the sum.
     */
    public static final String TYPE_STAT_SUM_ONE_COUNTY = "DeptTotal";
    
    /** 
     * Value indicating that the statistic is an aggregation or a sum made on an establishment type.<br/>
     * In this case the county number is not considered and all the establishments associated to the
     * establishment type are aggregated.
     */
    public static final String TYPE_STAT_SUM_ONE_ESTABLISHMENT_TYPE = "TotalType";
    
    /** 
     * Value indicating that the statistic is an aggregation or a sum made on all the establishment types
     * and all the counties .<br/>
     */
    public static final String TYPE_STAT_SUM_ALL_COUNTIES_AND_ESTABLISHMENT_TYPES = "TotalTotal";
    
}
