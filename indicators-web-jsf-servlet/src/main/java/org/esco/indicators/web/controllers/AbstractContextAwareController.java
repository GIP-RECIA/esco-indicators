/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/indicators
 */
package org.esco.indicators.web.controllers;

import org.esco.indicators.domain.beans.people.User;
import org.esupportail.commons.utils.Assert;

/**
 * An abstract class inherited by all the beans for them to get:
 * - the context of the application (sessionController).
 * - the domain service (domainService).
 * - the application service (applicationService).
 * - the i18n service (i18nService).
 */
public abstract class AbstractContextAwareController extends AbstractDomainAwareBean {

	/*
	 ******************* PROPERTIES ******************** */
	
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -1826458262448752328L;
	
	
	/**
	 * The SessionController.
	 */
	private SessionController sessionController;

	/*
	 ******************* INIT ******************** */
	
	/**
	 * Constructor.
	 */
	protected AbstractContextAwareController() {
		super();
	}

	/**
	 * @see org.esco.indicators.web.controllers.AbstractDomainAwareBean#afterPropertiesSetInternal()
	 */
	@Override
	public void afterPropertiesSetInternal() {
		Assert.notNull(this.sessionController, "property sessionController of class " 
				+ this.getClass().getName() + " can not be null");
	}

	/*
	 ******************* CALLBACK ******************** */
	
	
	/*
	 ******************* METHODS ******************** */
	
	/**
	 * @see org.esco.indicators.web.controllers.AbstractDomainAwareBean#getCurrentUser()
	 */
	@Override
	protected User getCurrentUser() throws Exception {
		return sessionController.getCurrentUser();
	}
	
	/**
	 * @param sessionController the sessionController to set
	 */
	public void setSessionController(final SessionController sessionController) {
		this.sessionController = sessionController;
	}

	/**
	 * @return the sessionController
	 */
	public SessionController getSessionController() {
		return sessionController;
	}

	

}
