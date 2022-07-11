package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import com.finaltask.org.example.realization.validators.TimeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The command that allows you to mark the execution time of the activity
 *
 * @see Command
 * @see BusinessServiceImpl
 * @see Activity
 *
 * @author Misha Rudyk
 */
public class MarkTimeCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute method
     * @return page name with activities
     */
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

        BusinessServiceImpl service = new BusinessServiceImpl();

        Activity activity = service.findActivity(activityId);
        boolean canMarkTime = false;
        for(User user1 : activity.getUsers()) {
            if (user1.getUsername().equals(user.getUsername())) {
                canMarkTime = true;
                break;
            }
        }

        String lang = (String) request.getSession().getAttribute("lang");

        Locale locale = Locale.getDefault();
        if(lang != null)
            locale = Locale.forLanguageTag(lang);

        List<String> errors = TimeValidator.validateState(strDays, strHours, strMinutes, lang);

        if(!canMarkTime) {
            errors.add(ResourceBundle.getBundle("i18n.messages",
                    locale).getString("time.error.user"));
            LOGGER.warn("{} cannot mark time for this activity", user.getUsername());
        }

        if(!errors.isEmpty()){
            request.setAttribute("errors", errors);
            return "/WEB-INF/pages/activities.jsp";
        }

        int days = Integer.parseInt(strDays);
        int hours = Integer.parseInt(strHours);
        int minutes = Integer.parseInt(strMinutes);

        int duration = minutes + hours * 60 + days * 24 * 60;

        service.takeActivityTime(activityId, user, duration);

        return "redirect:/activities";

    }
}
