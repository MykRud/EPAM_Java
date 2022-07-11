package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Activities controller that allows the user to view activity pages
 *
 * @author Misha Rudyk
 * @see Activity
 * @see ActivityService
 */
@Controller
@Slf4j
public class GetActivities {

    @Autowired
    ActivityService service;

    /**
     * Mapped method
     *
     * @return page with activities
     */
    @Secured("USER")
    @RequestMapping("/activities")
    public ModelAndView getActivities(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "5") int size,
                                      @RequestParam(value = "sort", defaultValue = "by-name") String sort) {
        ModelAndView mv = new ModelAndView();

        log.info("Fetching number of activities");
        int numberOfActivities = service.getNumberOfActivities();
        int totalPages = (int) Math.ceil((double) numberOfActivities /
                (double) size);
        List<Activity> activities = null;

        if (sort.equals("by-name")) {
            log.info("Fetching {} activities for page {} ordering by name", size, page);
            activities = service.getActivitiesInLimitByName(size, page);
        } else if (sort.equals("by-category")) {
            log.info("Fetching {} activities for page {} ordering by category", size, page);
            activities = service.getActivitiesInLimitByType(size, page);
        } else if (sort.equals("by-users")) {
            log.info("Fetching {} activities for page {} ordering by number of users", size, page);
            activities = service.getActivitiesInLimitByNumberOfUsers(size, page);
        }

        mv.addObject("activities", activities);
        mv.addObject("currentPage", page);
        mv.addObject("pageSize", size);
        mv.addObject("totalPages", totalPages);
        mv.addObject("sort", sort);
        mv.setViewName("WEB-INF/pages/activities");
        return mv;
    }

}
