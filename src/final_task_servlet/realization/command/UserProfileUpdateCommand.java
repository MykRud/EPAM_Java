package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import com.finaltask.org.example.realization.validators.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserProfileUpdateCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

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
                return "/unknown";
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

        List<String> errors = UserValidator.validateState(user);
        if(!errors.isEmpty()){
            request.setAttribute("errors", errors);
            return "/profile";
        }

        BusinessServiceImpl businessService = new BusinessServiceImpl();
        businessService.updateUser(user);
        LOGGER.info("Updating user...");

        return "redirect:/profile";
    }
}
