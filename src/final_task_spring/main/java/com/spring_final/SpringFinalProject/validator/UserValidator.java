package final_task_spring.main.java.com.spring_final.SpringFinalProject.validator;


import com.spring_final.SpringFinalProject.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Validator that allows to validate user
 *
 * @author Misha Rudyk
 * @see CommonValidator
 * @see User
 */
public class UserValidator extends CommonValidator {

    /**
     * Main method that handle validation process
     *
     * @param user User to validate
     * @return List of errors
     */
    public static List<String> validateState(User user) {

        setResourceBundle();

        List<String> validationErrors = new ArrayList<>();
        if (!validateForBlankValue(user.getFirstName())) {
            validationErrors.add(resourceBundle.getString("user.error.empty_first_name"));
        }
        if (!validateForSize(2, 50, user.getFirstName())) {
            validationErrors.add(resourceBundle.getString("user.error.first_name_out_of_range"));
        }

        if (!validateForBlankValue(user.getLastName())) {
            validationErrors.add(resourceBundle.getString("user.error.empty_last_name"));
        }
        if (!validateForSize(2, 50, user.getLastName())) {
            validationErrors.add(resourceBundle.getString("user.error.last_name_out_of_range"));
        }

        if (!validateForBlankValue(user.getUsername())) {
            validationErrors.add(resourceBundle.getString("user.error.empty_username"));
        }
        if (!validateForSize(2, 25, user.getUsername())) {
            validationErrors.add(resourceBundle.getString("user.error.username_out_of_range"));
        }

        if (!validatePhoneNumber(user.getContact())) {
            validationErrors.add(resourceBundle.getString("user.error.phone_format"));
        }

        if (!validateAge(user.getAge())) {
            validationErrors.add(resourceBundle.getString("user.error.age"));
        }

        return validationErrors;
    }

    private static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^([+]?[\\s0-9]+)?(\\d{3}|[(]?[0-9]+[)])?([-]?[\\s]?[0-9])+$");
    }

    private static boolean validateAge(int age) {
        return age >= 0 && age <= 110;
    }

}
