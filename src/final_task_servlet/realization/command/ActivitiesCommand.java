package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.comparators.ComparatorByCategory;
import com.finaltask.org.example.realization.comparators.ComparatorByName;
import com.finaltask.org.example.realization.comparators.ComparatorByUsers;
import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;


/**
 * Get activities
 *
 * @author Misha Rudyk
 * @see Command

 * @see BusinessServiceImpl
 */
public class ActivitiesCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute() {
        int page = 0;
        int size = 5;

        try{
            if(request.getParameter("page") != null){
                page = Integer.parseInt(request.getParameter("page"));
            }

            if(request.getParameter("size") != null){
                size = Integer.parseInt(request.getParameter("size"));
            }
        } catch (NumberFormatException e){
            LOGGER.warn("Cannot parse page or size");
            return "redirect:/unknown";
        }

        String sortMethod = request.getParameter("sort-methods");
        if(sortMethod == null)
            sortMethod = "by-name";

        BusinessServiceImpl businessService = new BusinessServiceImpl();
        int numberOfActivities = businessService.getNumberOfActivities();
        int totalPages = (int)Math.ceil(numberOfActivities/size);
        List<Activity> listOfActivities = businessService.getActivitiesInLimit(size, page);
        LOGGER.info("Selecting activities for page {}...", page);
        if(sortMethod.equals("by-name"))
            Collections.sort(listOfActivities, new ComparatorByName());
        else if(sortMethod.equals("by-category"))
            Collections.sort(listOfActivities, new ComparatorByCategory());
        else if(sortMethod.equals("by-users"))
            Collections.sort(listOfActivities, new ComparatorByUsers());

        request.setAttribute("activities", listOfActivities);
        String ac = listOfActivities.toString();
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", size);
        request.setAttribute("totalPages", totalPages);
        return "/WEB-INF/pages/activities.jsp";
    }
}
