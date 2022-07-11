package final_task_servlet.main.java.com.finaltask.org.example.realization.validators;

import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.TypeOfActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Validator that allows to validate type
 *
 * @see CommonValidator
 * @see TypeOfActivity
 *
 * @author Misha Rudyk
 */
public class TypeValidator extends CommonValidator{

    /**
     * Main method that handle validation process
     * @param type Type to validate
     * @param lang Language
     * @return List of errors
     */
    public static List<String> validateState(TypeOfActivity type, String lang) {

        setResourceBundle(lang);

        List<String> validationErrors = new ArrayList<>();
        if (!validateForBlankValue(type.getName())) {
            validationErrors.add(resourceBundle.getString("common.error.empty_name"));
        }
        if(!validateForSize(2, 50, type.getName())){
            validationErrors.add(resourceBundle.getString("common.error.name_out_of_range"));
        }

        return validationErrors;
    }

}
