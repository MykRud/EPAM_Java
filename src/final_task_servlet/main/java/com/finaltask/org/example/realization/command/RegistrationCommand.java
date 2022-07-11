package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import com.finaltask.org.example.realization.validators.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The command that allows you to register a user in the system
 *
 * @see User
 * @see com.finaltask.org.example.realization.dao.interfaces.UserDao
 * @see BusinessServiceImpl
 * @see Command
 *
 * @author Misha Rudyk
 */
public class RegistrationCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute method
     * @return login page
     */
    @Override
    public String execute() {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String username = request.getParameter("username_reg");
        String password = request.getParameter("password_reg");
        int age = 0;
        if(request.getParameter("age") != null){
            if(request.getParameter("age") != "") {
                age = Integer.parseInt(request.getParameter("age"));
            }
        }
        String gender = request.getParameter("gender");
        String contact = request.getParameter("contact");

        if(firstName == null || lastName == null ||
                username == null || password == null ||
                contact == null || gender == null){
            return "/registration.jsp";
        }

        User user = new User(firstName, lastName, username, password, age, gender, contact);

        String lang = (String) request.getSession().getAttribute("lang");

        Locale locale = Locale.getDefault();
        if(lang != null)
            locale = Locale.forLanguageTag(lang);

        List<String> errors = UserValidator.validateState(user, lang);

        BusinessServiceImpl service = new BusinessServiceImpl();

        User findUser = service.findUser(username);
        if(findUser != null){
            LOGGER.warn("A user with this username has already registered in the system");
            errors.add(ResourceBundle.getBundle("i18n.messages",
                    locale).getString("user.error.registered"));
        }

        int successful = 1;
        if(!errors.isEmpty()){
            request.getSession().setAttribute("errors", errors);
            successful = 0;
            return "redirect:/registrationDisplay?s="+successful;
        }

        service.addUser(user);
        LOGGER.warn("User {} has been successfully registered", user.getUsername());

        return "redirect:/login";
    }

}
