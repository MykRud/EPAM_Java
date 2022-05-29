package final_task_servlet.realization.validators;

import com.finaltask.org.example.realization.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidator extends CommonValidator{

    public static List<String> validateState(User user) {
        List<String> validationErrors = new ArrayList<>();
        if (!validateForBlankValue(user.getFirstName())) {
            validationErrors.add("First name shouldn't be empty");
        }
        if(!validateForSize(2, 50, user.getFirstName())){
            validationErrors.add("First name length is out of range");
        }

        if (!validateForBlankValue(user.getLastName())) {
            validationErrors.add("Last name shouldn't be empty");
        }
        if(!validateForSize(2, 50, user.getLastName())){
            validationErrors.add("Last name length is out of range");
        }

        if (!validateForBlankValue(user.getUsername())) {
            validationErrors.add("Username shouldn't be empty");
        }
        if(!validateForSize(2, 25, user.getUsername())){
            validationErrors.add("Username length is out of range");
        }

        if (!validatePhoneNumber(user.getContact())) {
            validationErrors.add("The phone number does not match the format");
        }

        if(!validateAge(user.getAge())){
            validationErrors.add("Incorrect age");
        }

        return validationErrors;
    }

    private static boolean validatePhoneNumber(String phoneNumber){
        return phoneNumber.matches("^([+]?[\\s0-9]+)?(\\d{3}|[(]?[0-9]+[)])?([-]?[\\s]?[0-9])+$");
    }

    private static boolean validateAge(int age){
        return age >= 0 && age <= 110;
    }

}
