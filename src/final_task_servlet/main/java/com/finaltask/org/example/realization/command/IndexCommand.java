package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

/**
 * The command that allows you to access the index page of the site
 * @see Command
 *
 * @author Misha Rudyk
 */
public class IndexCommand extends Command{

    /**
     * Execute method
     * @return index page
     */
    @Override
    public String execute() {
        return "/index.jsp";
    }
}
