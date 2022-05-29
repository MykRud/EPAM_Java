package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.model.ActivityRequest;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Reject  request
 *
 * @author Misha Rudyk
 * @see BusinessServiceImpl
 * @see com.finaltask.org.example.realization.model.ActivityRequest
 */
public class ActivityRequestRejectCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute() {

        if(checkForAdmin() != null)
            return checkForAdmin();

        int requestId = Integer.parseInt(request.getParameter("id"));

        BusinessServiceImpl service = new BusinessServiceImpl();
        ActivityRequest activityRequest = service.findRequest(requestId);

        if(!activityRequest.getStatus().equals("Pending"))
            return "redirect:/admin/activitiesRequests";

        LOGGER.info("Rejecting request...");
        service.rejectRequest(activityRequest);

        return "redirect:/admin/activitiesRequests";
    }
}
