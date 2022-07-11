package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

/**
 * The command responsible for displaying error 404
 *
 * @see Command
 *
 * @author Misha Rudyk
 */
public class UnknownCommand extends Command{

    /**
     * Execute method
     * @return 404 page
     */
    @Override
    public String execute() {
        return "/WEB-INF/pages/error/404.jsp";
    }
}
