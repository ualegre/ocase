/*
 * Copyright 2015 @author Unai Alegre 
 * 
 * This file is part of OCASE (Ontologies for Context-Aware Systems Engineering), a module 
 * of Modelio that helps the automatic generation of ontologies based on the Situational 
 * Parameters gathered during the requirements elicitation phase of a Context-Aware System (C-AS),
 *  using RCASE (Requirements for Context-Aware Systems Engineering) tool. 
 * 
 * OCASE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OCASE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OCASE.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.ocase.module.i18n;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Message service <br>
 * Use of "i18n/messages"
 *
 */
public class I18nMessageService {

    private static final String FILE_NAME_MESSAGES = ".messages";
    private static final Logger logger = Logger.getLogger(I18nMessageService.class.getName());
    private static I18nMessageService instance;

    private ResourceBundle messageResource;

    /**
     * Private constructor.
     */
    private I18nMessageService() {
	this.messageResource = ResourceBundle
		.getBundle(I18nMessageService.class.getPackage().getName() + FILE_NAME_MESSAGES);
    }

    /**
     * Singleton creation.
     */
    private static I18nMessageService getInstance() {
	if (null == instance) { // First call
	    instance = new I18nMessageService();
	}
	return instance;
    }

    /**
     * @return the messageResource
     */
    private ResourceBundle getMessageResource() {
	return this.messageResource;
    }

    /**
     * Get message value from key.
     * 
     * @param key
     *            the key for the desired string.
     * @return the string for the given key.
     */
    public static String getString(String key) {
	String message = null;
	try {
	    message = getInstance().getMessageResource().getString(key);
	} catch (MissingResourceException e) {
	    message = '!' + key + '!';
	    logger.log(Level.WARNING, message + "\n" + e.getMessage(), e);
	}
	return message;
    }

    /**
     * Get list of messages values from key with parameters.
     * 
     * @param key
     *            the key for the desired string.
     * @param params
     *            an array of objects to be formatted and substituted.
     * @return the string for the given key.
     */
    public static String getString(String key, String... params) {
	String message = null;
	try {
	    String value = getString(key);
	    message = MessageFormat.format(value, (Object[]) params);
	} catch (MissingResourceException e) {
	    message = '!' + key + '!';
	    logger.log(Level.WARNING, message + "\n" + e.getMessage(), e);
	}
	return message;
    }
}
