package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.model.ActivityRequest;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A command that allows you to reject requests to add or complete an activity
 *
 * @author Misha Rudyk
 * @see BusinessServiceImpl
 * @see ActivityRequest
 * @see com.finaltask.org.example.realization.dao.interfaces.ActivityRequestDao
 */
public class ActivityRequestRejectCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute command
     * @return page name with requests
     */
    @Override
    public String execute() {

        if(checkForAdmin() != null)
            return checkForAdmin();

        int requestId = 0;
        if(request.getParameter("id") != null) {
            try {
                requestId = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                LOGGER.warn("Cannot find request");
                return "redirect:/unknown";
            }
        }
        BusinessServiceImpl service = new BusinessServiceImpl();
        ActivityRequest activityRequest = service.findRequest(requestId);

        if(!activityRequest.getStatus().equals("Pending"))
            return "redirect:/admin/activitiesRequests";

        LOGGER.info("Rejecting request...");
        service.rejectRequest(activityRequest);

        return "redirect:/admin/activitiesRequests";
    }
}
