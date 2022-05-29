package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.model.Authority;
import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import com.finaltask.org.example.realization.validators.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

public class UserUpdateCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute() {

        if(checkForAdmin() != null)
            return checkForAdmin();

        int userId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
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
                return "redirect:/unknown";
            }
        }

        BusinessServiceImpl service = new BusinessServiceImpl();

        if(firstName == null || lastName == null || userName == null ||
        password == null || contact == null || gender == null || age == 0 || authority == null){
            request.setAttribute("user", service.findUser(userId));
            request.setAttribute("authorities", Authority.values());
            return "/WEB-INF/pages/admin/update-user.jsp";
        }

        User user = service.findUser(userId);

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(userName);
        user.setPassword(password);
        user.setContact(contact);
        user.setGender(gender);
        user.setAge(age);

        List<String> errors = UserValidator.validateState(user);
        if(!errors.isEmpty()){
            request.setAttribute("errors", errors);
            return "/admin/users";
        }

        service.updateUser(user);
        LOGGER.info("Updating user...");
        if(user.getAuthorities().contains(Authority.ADMIN) &&
                Authority.valueOf(authority) == Authority.valueOf("USER"))
            service.deleteUserAuthority(userId, Authority.ADMIN);
        else {
            service.setAuthority(user.getId(), Authority.valueOf(authority));
        }

        return "redirect:/admin/users";
    }
}
