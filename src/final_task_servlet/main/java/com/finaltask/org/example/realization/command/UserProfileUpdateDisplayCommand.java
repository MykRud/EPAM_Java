package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.service.BusinessServiceImpl;

public class UserProfileUpdateDisplayCommand extends DisplayCommand{

    @Override
    public String execute() {

        setResourceBundle((String) request.getSession().getAttribute("lang"));

        if(request.getParameter("s") != null) {
            int success = Integer.parseInt(request.getParameter("s"));
            if (success == 1) {
                request.setAttribute("result", resourceBundle.getString("display.profile.successful"));
            } else {
                request.setAttribute("result",
                        resourceBundle.getString("display.profile.error"));
            }
        }
        return "/WEB-INF/pages/profile-update.jsp";

    }

}
