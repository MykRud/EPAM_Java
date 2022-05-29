package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.dao.MySQLImpl.TypesOfActivitiesDaoImpl;
import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.Authority;
import com.finaltask.org.example.realization.model.TypeOfActivity;
import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import com.finaltask.org.example.realization.validators.ActivityValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CAdd activity
 *
 * @author Misha Rudyk
 * @see Command
 * @see BusinessServiceImpl
 */
public class ActivitiesAddCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

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

        Activity activity = new Activity(name, type);
        activity.setDescription(description);

        List<String> errors = ActivityValidator.validateState(activity);
        if(!errors.isEmpty()){
            request.setAttribute("errors", errors);
            return "/WEB-INF/pages/admin/add-activity.jsp";
        }

        service.addActivity(activity);
        LOGGER.info("Adding activity {}...", activity.getName());

        return "redirect:/activities";
    }
}
