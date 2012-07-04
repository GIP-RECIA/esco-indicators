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
    public List<ResultRow> getPunctualWeekResultRows(List<String> establishmentsUai, List<String> usersProfiles, Integer week, Integer year);
    
    public List<ResultRow> getWeeklyResultRows(List<String> establishmentsUai, List<String> usersProfiles, Integer startWeek, Integer startYear, Integer endWeek, Integer endYear);
    
    ///////////////////////////////////////////////////////
    // MONTHLY RESULTS
    ///////////////////////////////////////////////////////
    public List<ResultRow> getPunctualMonthResultRows(List<String> establishmentsUai, List<String> usersProfiles, Integer month, Integer year);
    
    public List<ResultRow> getMonthlyResultRows(List<String> establishmentsUai, List<String> usersProfiles, Integer startMonth, Integer startYear, Integer endMonth, Integer endYear);
    
}
