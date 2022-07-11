package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The command that allows registered users to log in
 *
 * @see User
 * @see Command
 * @see com.finaltask.org.example.realization.filters.AuthFilter
 *
 * @author Misha Rudyk
 */
public class LoginCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute method
     * @return index page of logged-in user
     */
    @Override
    public String execute() {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username == null || password == null)
            return "/login.jsp";

        BusinessServiceImpl businessService = new BusinessServiceImpl();
        User user = businessService.findUser(username);

        String lang = (String) request.getSession().getAttribute("lang");

        Locale locale = Locale.getDefault();
        if(lang != null)
            locale = Locale.forLanguageTag(lang);

        int successful = 1;
        List<String> errors = new ArrayList<>();
        if(user == null) {
            errors.add(ResourceBundle.getBundle("i18n.messages",
                    locale).getString("login.error.user"));
            request.getSession().setAttribute("errors", errors);
            LOGGER.warn("No such user registered");
            successful = 0;
            return "redirect:/loginDisplay?s="+successful;
        }

        if (!BCrypt.checkpw(password, user.getPassword())) {
            LOGGER.warn("Incorrect password");
            errors.add(ResourceBundle.getBundle("i18n.messages",
                    locale).getString("login.error.password"));
            request.getSession().setAttribute("errors", errors);
            successful = 0;
            return "redirect:/loginDisplay?s="+successful;
        }

        request.getSession().setAttribute("authUser", user);
        LOGGER.warn("User {} has just logged in", user.getUsername());
        return "redirect:/index";
    }
}
