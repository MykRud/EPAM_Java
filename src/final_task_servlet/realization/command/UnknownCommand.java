package final_task_servlet.realization.command;

public class UnknownCommand extends Command{
    @Override
    public String execute() {
        return "/WEB-INF/pages/error/404.jsp";
    }
}
