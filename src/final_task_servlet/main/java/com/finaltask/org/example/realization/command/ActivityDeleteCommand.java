package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Activity command that allows you to delete activity
 *
 * @author Misha Rudyk
 * @see Command
 * @see com.finaltask.org.example.realization.model.Activity
 * @see BusinessServiceImpl
 * @see com.finaltask.org.example.realization.dao.interfaces.ActivityDao
 */
public class ActivityDeleteCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute command
     * @return page name with activities
     */
    @Override
    public String execute() {

        if(checkForAdmin() != null)
            return checkForAdmin();

        int activityId;
        try{
            activityId = Integer.parseInt(request.getParameter("activity_id"));
        } catch (NumberFormatException e){
            LOGGER.warn("Cannot parse id");
            return "redirect:/unknown";
        }

        BusinessServiceImpl service = new BusinessServiceImpl();
        LOGGER.info("Deleting activity...");
        service.deleteActivity(activityId);
        return "redirect:/activities";
    }
}
