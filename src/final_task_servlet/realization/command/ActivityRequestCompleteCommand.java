package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Add complete request
 *
 * @author Misha Rudyk
 * @see BusinessServiceImpl
 * @see com.finaltask.org.example.realization.model.ActivityRequest
 */
public class ActivityRequestCompleteCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute() {

        User user = (User)request.getSession().getAttribute("authUser");
        int activityId;
        try{
            activityId = Integer.parseInt(request.getParameter("activity_id"));
        } catch (NumberFormatException e){
            LOGGER.warn("Cannot parse id");
            return "redirect:/unknown";
        }
        BusinessServiceImpl service = new BusinessServiceImpl();
        Activity activity = service.findActivity(activityId);

        LOGGER.info("Sending request for adding activity...");
        service.makeCompleteRequest(user, activity);

        return "redirect:/activities";
    }
}
