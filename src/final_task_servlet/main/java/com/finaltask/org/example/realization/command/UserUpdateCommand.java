package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.model.Authority;
import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import com.finaltask.org.example.realization.validators.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The command that allows admin to update profile of some user
 *
 * @see User
 * @see com.finaltask.org.example.realization.dao.interfaces.UserDao
 * @see BusinessServiceImpl
 * @see Command
 * @see ProfileCommand
 * @see UserProfileUpdateCommand
 *
 * @author Misha Rudyk
 */
public class UserUpdateCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute method
     * @return page name with users
     */
    @Override
    public String execute() {

        if(checkForAdmin() != null)
            return checkForAdmin();

        int userId = 0;
        if(request.getParameter("id") != null) {
            try {
                userId = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                LOGGER.warn("Cannot find user");
                return "redirect:/unknown";
            }
        }

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String userName = request.getParameter("username");
        String contact = request.getParameter("contact");
        String gender = request.getParameter("gender");
        String authority = null;
        if(request.getParameter("authorities") != null){
            authority = request.getParameter("authorities");
            authority = authority.toUpperCase();
        }

        int age = 0;
        if(request.getParameter("age") != null) {
            try {
                age = Integer.parseInt(request.getParameter("age"));
            } catch (NumberFormatException e) {
                LOGGER.warn("Cannot parse age");
            }
        }

        BusinessServiceImpl service = new BusinessServiceImpl();

        if(userId == 0 || firstName == null || lastName == null || userName == null ||
                contact == null || gender == null || age == 0 || authority == null){
            request.setAttribute("user", service.findUser(userId));
            request.setAttribute("authorities", Authority.values());
            return "/WEB-INF/pages/admin/update-user.jsp";
        }

        String lang = (String) request.getSession().getAttribute("lang");
        Locale locale = Locale.getDefault();
        if(lang != null)
            locale = Locale.forLanguageTag(lang);

        User user = service.findUser(userId);

        List<String> errors = new ArrayList<>();
        if(user == null){
            LOGGER.warn("User has not been found");
            errors.add(ResourceBundle.getBundle("i18n.messages",
                    locale).getString("user.error.not_found"));
        } else {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(userName);
            user.setContact(contact);
            user.setGender(gender);
            user.setAge(age);


            errors = UserValidator.validateState(user, lang);
        }

        int successful = 1;
        if(!errors.isEmpty()){
            request.getSession().setAttribute("errors", errors);
            successful = 0;
            request.setAttribute("s", successful);
            return "redirect:/userUpdateDisplay?s="+successful;
        }

        service.updateUser(user);
        LOGGER.info("Updating user...");
        if(user.getAuthorities().contains(Authority.ADMIN) &&
                Authority.valueOf(authority) == Authority.valueOf("USER"))
            service.deleteUserAuthority(userId, Authority.ADMIN);
        else {
            service.setAuthority(user.getId(), Authority.valueOf(authority));
        }

        request.getSession().setAttribute("s", successful);
        return "redirect:/userUpdateDisplay?s="+successful;
    }
}
