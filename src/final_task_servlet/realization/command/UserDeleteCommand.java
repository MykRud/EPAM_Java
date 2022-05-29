package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserDeleteCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute() {

        if(checkForAdmin() != null)
            return checkForAdmin();

        int userId;
        try {
            userId = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e){
            LOGGER.warn("Cannot parse id");
            return "redirect:/unknown";
        }
        BusinessServiceImpl service = new BusinessServiceImpl();
        service.deleteUser(userId);
        LOGGER.info("Deleting user...");

        return "redirect:/admin/users";
    }
}
