package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * The command that allows you to log out
 *
 * @see Command
 * @see HttpSession
 * @see com.finaltask.org.example.realization.model.User
 *
 * @author Misha Rudyk
 */
public class LogoutCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute method
     * @return login page
     */
    @Override
    public String execute() {
        HttpSession session = request.getSession();
        session.invalidate();
        LOGGER.warn("User successfully log out");
        return "/login.jsp";
    }
}
