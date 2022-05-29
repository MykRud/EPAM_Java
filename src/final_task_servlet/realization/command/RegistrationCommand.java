package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import com.finaltask.org.example.realization.validators.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RegistrationCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute() {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String username = request.getParameter("username_reg");
        String password = request.getParameter("password_reg");
        int age = 0;
        if(request.getParameter("age") != null)
            age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String contact = request.getParameter("contact");

        if(firstName == null || lastName == null ||
                username == null || password == null ||
                contact == null || gender == null){
            return "/registration.jsp";
        }

        User user = new User(firstName, lastName, username, password, age, gender, contact);

        List<String> errors = UserValidator.validateState(user);
        if(!errors.isEmpty()){
            request.setAttribute("errors", errors);
            return "/registration.jsp";
        }

        BusinessServiceImpl businessService = new BusinessServiceImpl();
        businessService.addUser(user);
        LOGGER.warn("User {} has been successfully registered", user.getUsername());

        return "redirect:/login";
    }

}
