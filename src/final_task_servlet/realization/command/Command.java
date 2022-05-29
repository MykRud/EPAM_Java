package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.model.Authority;
import com.finaltask.org.example.realization.model.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response){
        this.context = context;
        this.request = request;
        this.response = response;
    }

    public abstract String execute();

    public String checkForAdmin(){
        User authUser = (User) request.getSession().getAttribute("authUser");
        if(!authUser.getAuthorities().contains(Authority.ADMIN))
            return "redirect:/error/unknown";
        return null;
    }

}
