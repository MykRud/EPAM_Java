package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;

/**
 * The command that allows you to view the user profile
 *
 * @see Command
 * @see BusinessServiceImpl
 * @see User
 *
 * @author Misha Rudyk
 */
public class ProfileCommand extends Command{

    /**
     * Execute method
     * @return page name with user profile
     */
    @Override
    public String execute() {
        User user = (User)request.getSession().getAttribute("authUser");
        BusinessServiceImpl service = new BusinessServiceImpl();
        user = service.findUser(user.getId());
        request.setAttribute("user", user);
        return "/WEB-INF/pages/profile.jsp";
    }
}
