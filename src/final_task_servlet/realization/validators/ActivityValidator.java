package final_task_servlet.realization.validators;

import com.finaltask.org.example.realization.model.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityValidator extends CommonValidator{

    public static List<String> validateState(Activity activity){
        List<String> validationErrors = new ArrayList<>();
        if (!validateForBlankValue(activity.getName())) {
            validationErrors.add("Name of activity shouldn't be empty");
        }
        if(!validateForSize(2, 50, activity.getName())){
            validationErrors.add("Activity name length is out of range");
        }

        if(!validateForSize(0, 2500, activity.getDescription())){
            validationErrors.add("Description length is out of range");
        }

        return validationErrors;
    }

}
