package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import final_task_servlet.main.java.com.finaltask.org.example.realization.model.Activity;
import final_task_servlet.main.java.com.finaltask.org.example.realization.model.TypeOfActivity;
import final_task_servlet.main.java.com.finaltask.org.example.realization.model.User;
import final_task_servlet.main.java.com.finaltask.org.example.realization.service.BusinessServiceImpl;
import final_task_servlet.main.java.com.finaltask.org.example.realization.validators.ActivityValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Add activity command with which the user can add activities
 *
 * @author Misha Rudyk
 * @see Command
 * @see BusinessServiceImpl
 * @see Activity
 */
public class ActivitiesAddCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute command
     * @return page name with activities
     */
    @Override
    public String execute() {

        if(checkForAdmin() != null)
            return checkForAdmin();

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String typeName = request.getParameter("type");

        BusinessServiceImpl service = new BusinessServiceImpl();

        if(name == null || description == null || typeName == null) {
            request.setAttribute("types", service.getTypes());
            return "/WEB-INF/pages/admin/add-activity.jsp";
        }

        TypeOfActivity type = service.findType(typeName);

        if(type == null)
            return "/WEB-INF/pages/admin/add-activity.jsp";

        //Activity findActivity = service.findActivity(name);

        Activity activity = new Activity(name, type);
        activity.setDescription(description);

        String lang = (String) request.getSession().getAttribute("lang");

        /*Locale locale = Locale.getDefault();
        if(lang != null)
            locale = Locale.forLanguageTag(lang);*/

        List<String> errors = ActivityValidator.validateState(activity, lang);
        /*if(findActivity != null){
            LOGGER.warn("Such activity has already added");
            errors.add(ResourceBundle.getBundle("i18n.messages",
                    locale).getString("activity_add.error.added"));
        }*/
        int successful = 1;
        if(!errors.isEmpty()){
            request.getSession().setAttribute("errors", errors);
            successful = 0;
            return "redirect:/activitiesAddDisplay?s="+successful;
        }

        service.addActivity(activity);
        LOGGER.info("Adding activity {}...", activity.getName());

        return "redirect:/activitiesAddDisplay?s="+successful;
    }
}
