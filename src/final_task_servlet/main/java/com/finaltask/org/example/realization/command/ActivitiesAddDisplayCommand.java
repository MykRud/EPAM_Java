package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.service.BusinessServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ActivitiesAddDisplayCommand extends DisplayCommand {

    @Override
    public String execute() {

        setResourceBundle((String) request.getSession().getAttribute("lang"));

        if(request.getParameter("s") != null) {
            int success = Integer.parseInt(request.getParameter("s"));
            if (success == 1) //
                request.setAttribute("result", resourceBundle.getString("display.activity.successful"));
            else
                request.setAttribute("result",
                        resourceBundle.getString("display.activity.error"));
        }
        BusinessServiceImpl service = new BusinessServiceImpl();
        request.setAttribute("types", service.getTypes());
        return "/WEB-INF/pages/admin/add-activity.jsp";

    }

}
