package final_task_servlet.realization.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute() {
        HttpSession session = request.getSession();
        session.invalidate();
        LOGGER.warn("User successfully log out");
        return "/login.jsp";
    }
}
