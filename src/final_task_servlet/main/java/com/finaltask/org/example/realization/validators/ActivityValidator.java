package final_task_servlet.main.java.com.finaltask.org.example.realization.validators;

import com.finaltask.org.example.realization.model.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Validator that allows to validate activity
 *
 * @see CommonValidator
 * @see Activity
 *
 * @author Misha Rudyk
 */
public class ActivityValidator extends CommonValidator{

    /**
     * Main method that handle validation process
     * @param activity Activity to validate
     * @param lang Language
     * @return List of errors
     */
    public static List<String> validateState(Activity activity, String lang){

        setResourceBundle(lang);

        List<String> validationErrors = new ArrayList<>();
        if (!validateForBlankValue(activity.getName())) {
            validationErrors.add(resourceBundle.getString("common.error.empty_name"));
        }
        if(!validateForSize(2, 50, activity.getName())){
            validationErrors.add(resourceBundle.getString("activity.error.name_out_of_range"));
        }

        if(!validateForSize(0, 2500, activity.getDescription())){
            validationErrors.add(resourceBundle.getString("activity.error.description_out_of_range"));
        }

        return validationErrors;
    }

}
