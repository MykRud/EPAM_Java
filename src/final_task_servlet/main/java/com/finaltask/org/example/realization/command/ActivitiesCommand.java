package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


/**
 * Activities command that allows the user to view activity pages
 *
 * @author Misha Rudyk
 * @see Command
 * @see Activity
 * @see BusinessServiceImpl
 * @see com.finaltask.org.example.realization.dao.interfaces.ActivityDao
 */
public class ActivitiesCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute command
     * @return page name
     */
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

        if(sortMethod != null)
            request.getSession().setAttribute("sort-methods", sortMethod);


        BusinessServiceImpl businessService = new BusinessServiceImpl();
        int numberOfActivities = businessService.getNumberOfActivities();
        int totalPages = (int)Math.ceil((double)numberOfActivities/(double)size);
        List<Activity> listOfActivities = null;
        LOGGER.info("Selecting activities for page {}...", page);

        sortMethod = (String) request.getSession().getAttribute("sort-methods");

        if(sortMethod == null || sortMethod.equals("by-name"))
            listOfActivities = businessService.getActivitiesInLimitByName(size, page);
        else if(sortMethod.equals("by-category"))
            listOfActivities = businessService.getActivitiesInLimitByType(size, page);
        else if(sortMethod.equals("by-users"))
            listOfActivities = businessService.getActivitiesInLimitByNumberOfUsers(size, page);

        request.setAttribute("activities", listOfActivities);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", size);
        request.setAttribute("totalPages", totalPages);
        return "/WEB-INF/pages/activities.jsp";
    }
}
