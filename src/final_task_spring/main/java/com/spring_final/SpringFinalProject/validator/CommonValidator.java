package final_task_spring.main.java.com.spring_final.SpringFinalProject.validator;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
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
     *
     * @param value Value to check
     * @return validation result
     */
    protected static boolean validateForBlankValue(String value) {
        return !value.isBlank();
    }

    /**
     * Method that checks size of value
     *
     * @param value Value to check
     * @return validation result
     */
    protected static boolean validateForSize(int minSize, int maxSize, String value) {
        return value.length() >= minSize && value.length() <= maxSize;
    }

    /**
     * Method that set resource bundle
     */
    protected static void setResourceBundle() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        String lang = (String) session.getAttribute("lang");
        if (lang != null)
            resourceBundle = ResourceBundle.getBundle("messages", Locale.forLanguageTag(lang));
        else
            resourceBundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

}
