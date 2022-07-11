package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import com.finaltask.org.example.realization.validators.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The command that allows user to update profile
 *
 * @see User
 * @see com.finaltask.org.example.realization.dao.interfaces.UserDao
 * @see BusinessServiceImpl
 * @see Command
 * @see ProfileCommand
 * @see UserUpdateCommand
 *
 * @author Misha Rudyk
 */
public class UserProfileUpdateCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute method
     * @return page name with user profile
     */
    @Override
    public String execute() {
        BusinessServiceImpl service = new BusinessServiceImpl();
        User user = (User)request.getSession().getAttribute("authUser");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String contact = request.getParameter("contact");
        String stringAge = request.getParameter("age");
        int age = 0;
        if(stringAge != null) {
            try {
                age = Integer.parseInt(stringAge);
            } catch (NumberFormatException e){
                LOGGER.warn("Cannot parse age");
            }
        }

        if(firstName == null || lastName == null || username == null ||
        password == null || contact == null || age == 0) {
            return "/WEB-INF/pages/profile-update.jsp";
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setContact(contact);
        user.setAge(age);

        int successful = 1;
        String lang = (String) request.getSession().getAttribute("lang");
        List<String> errors = UserValidator.validateState(user, lang);
        if(!errors.isEmpty()){
            request.getSession().setAttribute("errors", errors);
            successful = 0;
            //request.setAttribute("s", successful);
            return "redirect:/userProfileUpdateDisplay?s="+successful;
        }

        BusinessServiceImpl businessService = new BusinessServiceImpl();
        businessService.updateUser(user);
        LOGGER.info("Updating user...");

        successful = 1;
        //request.setAttribute("s", successful);
        return "redirect:/userProfileUpdateDisplay?s="+successful;
    }
}
