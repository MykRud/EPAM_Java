package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The command that allows you to delete a user from the system
 *
 * @see User
 * @see com.finaltask.org.example.realization.dao.interfaces.UserDao
 * @see BusinessServiceImpl
 * @see Command
 *
 * @author Misha Rudyk
 */
public class UserDeleteCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute method
     * @return page name with users
     */
    @Override
    public String execute() {

        if(checkForAdmin() != null)
            return checkForAdmin();

        int userId;
        try {
            userId = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e){
            LOGGER.warn("Cannot parse id");
            return "redirect:/unknown";
        }
        BusinessServiceImpl service = new BusinessServiceImpl();

        String lang = (String) request.getSession().getAttribute("lang");

        Locale locale = Locale.getDefault();
        if(lang != null)
            locale = Locale.forLanguageTag(lang);

        User user = service.findUser(userId);

        List<String> errors = new ArrayList<>();
        if(user == null) {
            LOGGER.warn("User has not been found");
            errors.add(ResourceBundle.getBundle("i18n.messages",
                    locale).getString("user.error.not_found"));
        }

        if(!errors.isEmpty()){
            request.setAttribute("errors", errors);
            return "/WEB-INF/pages/admin/users.jsp";
        }

        service.deleteUser(userId);
        LOGGER.info("Deleting user...");

        return "redirect:/admin/users";
    }
}
