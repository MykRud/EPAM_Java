package final_task_servlet.realization.command;

public class IndexCommand extends Command{
    @Override
    public String execute() {
        return "/index.jsp";
    }
}
