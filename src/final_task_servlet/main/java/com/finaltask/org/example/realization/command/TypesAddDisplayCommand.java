package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

public class TypesAddDisplayCommand extends DisplayCommand {

    @Override
    public String execute() {

        setResourceBundle((String) request.getSession().getAttribute("lang"));

        if(request.getParameter("s") != null) {
            int success = Integer.parseInt(request.getParameter("s"));
            if (success == 1)
                request.setAttribute("result", resourceBundle.getString("display.type.successful"));
            else
                request.setAttribute("result",
                        resourceBundle.getString("display.type.error"));
        }
        return "/WEB-INF/pages/admin/add-type.jsp";

    }

}
