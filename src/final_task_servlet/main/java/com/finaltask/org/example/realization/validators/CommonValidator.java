package final_task_servlet.main.java.com.finaltask.org.example.realization.validators;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Common validator that contains the general logic of validation
 *
 * @author Misha Rudyk
 */
public class CommonValidator {

    protected static ResourceBundle resourceBundle;

    /**
     * Method that checks if value is blank
     * @param value Value to check
     * @return validation result
     */
    protected static boolean validateForBlankValue(String value){
        return !value.isBlank();
    }

    /**
     * Method that checks size of value
     * @param value Value to check
     * @return validation result
     */
    protected static boolean validateForSize(int minSize, int maxSize, String value){
        return value.length() >= minSize && value.length() <= maxSize;
    }

    /**
     * Method that set resource bundle
     * @param lang Language
     */
    protected static void setResourceBundle(String lang){
        if(lang != null)
            resourceBundle = ResourceBundle.getBundle("i18n.messages", Locale.forLanguageTag(lang));
        else
            resourceBundle = ResourceBundle.getBundle("i18n.messages", Locale.getDefault());
    }

}
