package final_task_servlet.main.java.com.finaltask.org.example.realization.validators;

import com.finaltask.org.example.realization.model.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Validator that allows to validate time
 *
 * @see CommonValidator
 *
 * @author Misha Rudyk
 */
public class TimeValidator extends CommonValidator{

    /**
     * Main method that handle validation process
     * @return List of errors
     */
    public static List<String> validateState(String stringDays, String stringHours, String stringMinutes, String lang){

        setResourceBundle(lang);

        List<String> errors = new ArrayList<>();
        int days, hours, minutes;
        try {
            days = Integer.parseInt(stringDays);
            hours = Integer.parseInt(stringHours);
            minutes = Integer.parseInt(stringMinutes);
        } catch (NumberFormatException e){
            errors.add(resourceBundle.getString("time.error.date_parse"));
            return errors;
        }

        if(!validateDays(days)) {
            errors.add(resourceBundle.getString("time.error.days_out_of_range"));
        }

        if(!validateHours(hours)) {
            errors.add(resourceBundle.getString("time.error.hours_out_of_range"));
        }

        if(!validateMinutes(minutes)) {
            errors.add(resourceBundle.getString("time.error.minutes_out_of_range"));
        }

        return errors;
    }

    private static boolean validateDays(int days){
        return days >= 0 && days <= 24;
    }

    private static boolean validateHours(int hours){
        return hours >= 0 && hours <= 59;
    }

    private static boolean validateMinutes(int minutes){
        return minutes >= 0 && minutes <= 59;
    }

}
