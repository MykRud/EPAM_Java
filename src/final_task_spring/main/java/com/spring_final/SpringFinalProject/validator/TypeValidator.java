package final_task_spring.main.java.com.spring_final.SpringFinalProject.validator;

import com.spring_final.SpringFinalProject.model.TypeOfActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Validator that allows to validate type
 *
 * @author Misha Rudyk
 * @see CommonValidator
 * @see TypeOfActivity
 */
public class TypeValidator extends CommonValidator {

    /**
     * Main method that handle validation process
     *
     * @param type Type to validate
     * @return List of errors
     */
    public static List<String> validateState(TypeOfActivity type) {

        setResourceBundle();

        List<String> validationErrors = new ArrayList<>();
        if (!validateForBlankValue(type.getName())) {
            validationErrors.add(resourceBundle.getString("common.error.empty_name"));
        }
        if (!validateForSize(2, 50, type.getName())) {
            validationErrors.add(resourceBundle.getString("common.error.name_out_of_range"));
        }

        return validationErrors;
    }

}
