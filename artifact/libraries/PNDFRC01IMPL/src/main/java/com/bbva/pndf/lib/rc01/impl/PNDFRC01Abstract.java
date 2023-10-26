package com.bbva.pndf.lib.rc01.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.pndf.lib.rc01.PNDFRC01;

/**
 * This class automatically defines the libraries and utilities that it will use.
 */
public abstract class PNDFRC01Abstract extends AbstractLibrary implements PNDFRC01 {

	protected ApplicationConfigurationService applicationConfigurationService;


	/**
	* @param applicationConfigurationService the this.applicationConfigurationService to set
	*/
	public void setApplicationConfigurationService(ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}

}