package edu.casetools.ocase.module.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Message service 
 * <br>Use of "i18n/messages"
 *
 */
public class I18nMessageService {

    private final static String FILE_NAME_MESSAGES = ".messages";

    private static I18nMessageService instance;

    private ResourceBundle messageResource;

    /**
     * Private constructor.
     */
    private I18nMessageService() {
        this.messageResource = ResourceBundle.getBundle(I18nMessageService.class.getPackage().getName() + FILE_NAME_MESSAGES);
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
     * @param key the key for the desired string.
     * @return the string for the given key.
     */
    public static String getString(String key) {
        String message = null;
        try {
            message = getInstance().getMessageResource().getString(key);
        } catch (MissingResourceException e) {
            message = '!' + key + '!';
        }
        return message;
    }

    /**
     * Get list of messages values from key with parameters.
     * 
     * @param key the key for the desired string.
     * @param params an array of objects to be formatted and substituted.
     * @return the string for the given key.
     */
    public static String getString(String key, String... params) {
        String message = null;
        try {
            String value = getString(key);
            message = MessageFormat.format(value, (Object[]) params);
        } catch (MissingResourceException e) {
            message = '!' + key + '!';
        }
        return message;
    }
}
