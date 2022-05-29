package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpSession;

public class LoginCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute() {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username == null || password == null)
            return "/login.jsp";

        BusinessServiceImpl businessService = new BusinessServiceImpl();
        User user = businessService.findUser(username);

        if(user == null) {
            LOGGER.warn("No such user registered");
            return "/login.jsp";
        }

        if (!BCrypt.checkpw(password, user.getPassword())) {
            LOGGER.warn("Incorrect password");
            return "/login.jsp";
        }

        HttpSession session = request.getSession();
        session.setAttribute("authUser", user);
        LOGGER.warn("User {} has just logged in", user.getUsername());
        return "redirect:/index";
    }
}
