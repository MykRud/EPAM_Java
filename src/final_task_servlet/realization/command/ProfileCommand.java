package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ProfileCommand extends Command{
    @Override
    public String execute() {
        User user = (User)request.getSession().getAttribute("authUser");
        BusinessServiceImpl service = new BusinessServiceImpl();
        user = service.findUser(user.getId());
        request.setAttribute("user", user);
        return "/WEB-INF/pages/profile.jsp";
    }
}
