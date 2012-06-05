/**
 * 
 */
package org.esco.indicators.utils.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.esco.indicators.utils.constants.SystemConstants;

/**
 * Class providing functions to make and execute query.<br/>
 * This class is provided to centralize all the DAO queries executions.<br/>
 * Moreover, this class is used to log the thrown exceptions.
 * 
 * @since 2012/06/05
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class QueryManager {
    // ---------------------------------------------------------------------------------- ATTRIBUTES
    /** Logger of the class */
    private static final Logger LOGGER = Logger.getLogger(QueryManager.class);

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    // --------------------------------------------------------------------------- GETTERS / SETTERS

    // ----------------------------------------------------------------------- PUBLIC STATIC METHODS

    /**
     * Makes and executes the named query <code>namedQuery</code> using the provided entity manager
     * <code>entityManager</code>.
     * Moreover, this function sets the query parameters using the provided <code>parameters</code>
     * 
     * @param entityManager
     * 			The entity manager used to make and execute the query.
     * @param namedQuery
     * 			The named query to execute.
     * @param parameters
     * 			The parameters, and their values, of the named query to set.
     * @return
     * 	the result of the query.<br/>
     * 	<code>null</code> if no data has been retrieved.
     */
    public static Object getSingleResult(EntityManager entityManager, String namedQuery, Parameters parameters) {
	// Creation of the query
	Query query = entityManager.createNamedQuery(namedQuery);

	// Setting of the parameters
	for (String parameter : parameters.keySet()) {
	    Object value = parameters.get(parameter);
	    query.setParameter(parameter, value);
	}

	// Retrieval of the result
	// Catching and logging the possible thrown exceptions
	Object result = null;
	try {
	    result = query.getSingleResult();
	} catch (NoResultException e) {
	    logNoResultException(namedQuery, parameters);
	} catch (NonUniqueResultException e) {
	    logNonUniqueResultException(namedQuery, parameters);
	} catch (IllegalStateException e) {
	    logIllegalStateException(namedQuery, parameters);
	}
	return result;
    }

    // ---------------------------------------------------------------------- PRIVATE STATIC METHODS

    /**
     * Logs the thrown <code>IllegalStateException</code> exceptions.
     * 
     * @param namedQuery
     * 			The named query executed which has thrown the exception.
     * @param parameters
     * 			The parameters set to the named query which has thrown the exception.
     */
    private static void logIllegalStateException(String namedQuery, Parameters parameters) {
	StringBuffer messageBuffer = new StringBuffer();
	messageBuffer.append("IllegalStateException - An error occured during the retrieval of the result : "
		+ namedQuery + SystemConstants.NEW_LINE);
	messageBuffer.append(parameters.toString());
	LOGGER.error(messageBuffer);
    }

    /**
     * Logs the thrown <code>NonUniqueResultException</code> exceptions.
     * 
     * @param namedQuery
     * 			The named query executed which has thrown the exception.
     * @param parameters
     * 			The parameters set to the named query which has thrown the exception.
     */
    private static void logNonUniqueResultException(String namedQuery, Parameters parameters) {
	StringBuffer messageBuffer = new StringBuffer();
	messageBuffer.append("NonUniqueResultException - More than one result has been found : " + namedQuery
		+ SystemConstants.NEW_LINE);
	messageBuffer.append(parameters.toString());
	LOGGER.warn(messageBuffer);
    }

    /**
     * Logs the thrown <code>NoResultException</code> exceptions.
     * 
     * @param namedQuery
     * 			The named query executed which has thrown the exception.
     * @param parameters
     * 			The parameters set to the named query which has thrown the exception.
     */
    private static void logNoResultException(String namedQuery, Parameters parameters) {
	StringBuffer messageBuffer = new StringBuffer();
	messageBuffer.append("NoResultException - No result of has been found for the query : " + namedQuery
		+ SystemConstants.NEW_LINE);
	messageBuffer.append(parameters.toString());
	LOGGER.debug(messageBuffer);
    }

}
