package final_task_servlet.realization.validators;

import com.finaltask.org.example.realization.model.TypeOfActivity;

import java.util.ArrayList;
import java.util.List;

public class TypeValidator extends CommonValidator{

    public static List<String> validateState(TypeOfActivity type) {
        List<String> validationErrors = new ArrayList<>();
        if (!validateForBlankValue(type.getName())) {
            validationErrors.add("Name shouldn't be empty");
        }
        if(!validateForSize(2, 50, type.getName())){
            validationErrors.add("Name length is out of range");
        }

        return validationErrors;
    }

}
