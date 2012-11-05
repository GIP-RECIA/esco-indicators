/**
 * 
 */
package org.esco.indicators.domain.beans.result;

import org.apache.log4j.Logger;

/**
 * Class representing a row present into the establishment table of the result page.<br/>
 * An extended result row can provide the same informations as a {@link BasicResultRow}.<br/>
 * In addition, an extended result row can provide the following informations :
 * <ul>
 * 	<li>The total number of accounts in the establishment</li>
 * 	<li>The total number of activated accounts in the establishment</li>
 * 	<li>The percentage of activated accounts in the establishment (regarding to the total number of accounts)</li>
 * </ul>
 * 
 * @since  2012/07/27
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class ExtendedResultRow extends BasicResultRow {
    //---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(ExtendedResultRow.class);

    /** Data concerning the accounts of the establishment */
    private StatisticOnAccounts statisticOnAccounts;
    
    //-------------------------------------------------------------------------------- CONSTRUCTORS
    /**
     * Default constructor of the {@link ExtendedResultRow} class.<br/>
     * <u>Note :</u> This constructor does not set properly the statistics on the accounts.
     * So, the {@link ExtendedResultRow} created by this constructor should not rely on the accounts statistics.
     */
    public ExtendedResultRow() {
	super();
	this.statisticOnAccounts = new StatisticOnAccounts(null, null);
    }
    
    /**
     * Constructor of the {@link ExtendedResultRow} class.
     * 
     * @param totalAccountNumber
     * 			The total number of accounts present into the establishment.
     * @param activeAccountNumber
     * 			The number of activated accounts present into the establishment.
     */
    public ExtendedResultRow(Integer totalAccountNumber, Integer activeAccountNumber) {
	super();
	this.statisticOnAccounts = new StatisticOnAccounts(totalAccountNumber, activeAccountNumber);
    }
    
    /**
     * Constructor of the {@link ExtendedResultRow} class based on {@link BasicResultRow}.
     * 
     * @param basicResultRow
     * 			The basic result row to duplicate.
     */
    public ExtendedResultRow(BasicResultRow basicResultRow) {
	super(basicResultRow);
    }
    
    /**
     * Constructor by copy of the {@link ExtendedResultRow} class.
     * 
     * @param original
     * 			The original result row to duplicate.
     */
    public ExtendedResultRow(ExtendedResultRow original) {
	super(original);
	this.statisticOnAccounts = original.getStatisticOnAccounts();
    }
    
    //--------------------------------------------------------------------------- GETTERS / SETTERS
    /**
     * Gets the number of total account.
     * 
     * @return the number of total account.
     */
    public Integer getTotalAccountNumber() {
	return statisticOnAccounts.getTotalAccountNumber();
    }

    /**
     * Gets the number of active account.
     * 
     * @return the number of active account.
     */
    public Integer getActiveAccountNumber() {
	return statisticOnAccounts.getActiveAccountNumber();
    }
    
    /**
     * Gets the percentage of active account regarding to the total number of account.
     * 
     * @return
     * 	 the percentage of active account regarding to the total number of account.
     */
    public Float getPercentageActiveAccount() {
	return statisticOnAccounts.getPercentageActiveAccount();
    }
    
    //------------------------------------------------------------------------------ PUBLIC METHODS

    //----------------------------------------------------------------------------- PRIVATE METHODS
    /**
     * Gets the statistics on accounts.
     * 
     * @return
     * 	the statistics on accounts.
     */
    private StatisticOnAccounts getStatisticOnAccounts() {
	return statisticOnAccounts;
    }

    //------------------------------------------------------------------------------ STATIC METHODS
}
