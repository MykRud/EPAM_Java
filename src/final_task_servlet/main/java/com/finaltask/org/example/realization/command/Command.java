package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.model.Authority;
import com.finaltask.org.example.realization.model.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Basic abstract class with common command behavior
 *
 * @see com.finaltask.org.example.realization.controller.FrontControllerServlet
 * @see HttpServletRequest
 * @see HttpServletResponse
 * @see ServletContext
 * @see User
 *
 * @author Misha Rudyk
 */
public abstract class Command {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     * Initialization method
     */
    public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response){
        this.context = context;
        this.request = request;
        this.response = response;
    }

    /**
     * Abstract execution method
     * @return page name
     */
    public abstract String execute();

    /**
     * Verifies if the logged-in user is an administrator
     * @return
     */
    public String checkForAdmin(){
        User authUser = (User) request.getSession().getAttribute("authUser");
        if(!authUser.getAuthorities().contains(Authority.ADMIN))
            return "redirect:/error/unknown";
        return null;
    }

}
