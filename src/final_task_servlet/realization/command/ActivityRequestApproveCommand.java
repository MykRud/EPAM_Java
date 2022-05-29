package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.model.ActivityRequest;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Approve request
 *
 * @author Misha Rudyk
 * @see BusinessServiceImpl
 * @see com.finaltask.org.example.realization.model.ActivityRequest
 */
public class ActivityRequestApproveCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute() {

        if(checkForAdmin() != null)
            return checkForAdmin();

        int requestId;
        try{
            requestId = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e){
            LOGGER.warn("Cannot parse id");
            return "redirect:/unknown";
        }

        BusinessServiceImpl service = new BusinessServiceImpl();
        ActivityRequest activityRequest = service.findRequest(requestId);

        if(!activityRequest.getStatus().equals("Pending")){
            return "redirect:/admin/activitiesRequests";
        }

        if(activityRequest.getAction().equals("Add")){
            LOGGER.info("Approving request...");
            service.approveRequest(activityRequest);
        } else if(activityRequest.getAction().equals("Complete")){
            LOGGER.info("Completing request...");
            service.completeRequest(activityRequest);
        }

        return "redirect:/admin/activitiesRequests";

    }
}
