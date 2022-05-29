package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import com.finaltask.org.example.realization.validators.TimeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MarkTimeCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute() {
        User user = (User)request.getSession().getAttribute("authUser");

        if(request.getParameter("activity_id") == null ||
                request.getParameter("days") == null ||
                request.getParameter("hours") == null ||
                request.getParameter("minutes") == null) {
            LOGGER.warn("Values cannot be null");
            return "redirect:/unknown";
        }

        int activityId = Integer.parseInt(request.getParameter("activity_id"));
        String strDays = request.getParameter("days");
        String strHours = request.getParameter("hours");
        String strMinutes = request.getParameter("minutes");

        List<String> errors = TimeValidator.validateState(strDays, strHours, strMinutes);
        if(!errors.isEmpty()){
            request.setAttribute("errors", errors);
            return "/activities";
        }

        int days = Integer.parseInt(strDays);
        int hours = Integer.parseInt(strHours);
        int minutes = Integer.parseInt(strMinutes);

        int duration = minutes + hours * 60 + days * 24 * 60;

        BusinessServiceImpl service = new BusinessServiceImpl();

        Activity activity = service.findActivity(activityId);
        boolean canMarkTime = false;
        for(User user1 : activity.getUsers()) {
            if (user1.getUsername().equals(user.getUsername())) {
                canMarkTime = true;
                break;
            }
        }
        if(canMarkTime) {
            service.takeActivityTime(activityId, user, duration);
        } else{
            LOGGER.warn("{} cannot mark time for this activity", user.getUsername());
        }

        return "redirect:/activities";

    }
}
