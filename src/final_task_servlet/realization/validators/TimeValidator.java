package final_task_servlet.realization.validators;

import java.util.ArrayList;
import java.util.List;

public class TimeValidator extends CommonValidator{

    public static List<String> validateState(String stringDays, String stringHours, String stringMinutes){
        List<String> errors = new ArrayList<>();
        int days, hours, minutes;
        try {
            days = Integer.parseInt(stringDays);
            hours = Integer.parseInt(stringHours);
            minutes = Integer.parseInt(stringMinutes);
        } catch (NumberFormatException e){
            errors.add("Cannot parse date");
            return errors;
        }

        if(!validateDays(days)) {
            errors.add("Days value is out of range");
        }

        if(!validateHours(hours)) {
            errors.add("Hours value is out of range");
        }

        if(!validateMinutes(minutes)) {
            errors.add("Minutes value is out of range");
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
