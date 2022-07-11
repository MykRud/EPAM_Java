package final_task_spring.main.java.com.spring_final.SpringFinalProject.validator;


import com.spring_final.SpringFinalProject.model.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Validator that allows to validate activity
 *
 * @author Misha Rudyk
 * @see CommonValidator
 * @see Activity
 */
public class ActivityValidator extends CommonValidator {

    /**
     * Main method that handle validation process
     *
     * @param activity Activity to validate
     * @return List of errors
     */
    public static List<String> validateState(Activity activity) {

        setResourceBundle();

        List<String> validationErrors = new ArrayList<>();
        if (!validateForBlankValue(activity.getName())) {
            validationErrors.add(resourceBundle.getString("common.error.empty_name"));
        }
        if (!validateForSize(2, 50, activity.getName())) {
            validationErrors.add(resourceBundle.getString("activity.error.name_out_of_range"));
        }

        if (!validateForSize(0, 2500, activity.getDescription())) {
            validationErrors.add(resourceBundle.getString("activity.error.description_out_of_range"));
        }

        return validationErrors;
    }

}
