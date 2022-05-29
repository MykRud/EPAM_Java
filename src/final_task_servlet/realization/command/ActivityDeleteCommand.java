package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.model.Authority;
import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Delete activity
 *
 * @author Misha Rudyk
 * @see com.finaltask.org.example.realization.model.Activity
 * @see BusinessServiceImpl
 */
public class ActivityDeleteCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

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
