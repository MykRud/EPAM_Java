package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.ActivityService;
import com.spring_final.SpringFinalProject.service.UserService;
import com.spring_final.SpringFinalProject.validator.TimeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The controller that allows you to mark the execution time of the activity
 *
 * @author Misha Rudyk
 * @see ActivityService
 * @see Activity
 */
@Controller
@Slf4j
public class MarkTime {

    @Autowired
    ActivityService service;
    @Autowired
    UserService userService;

    /**
     * Mapped method
     *
     * @return page with activities
     */
    @Secured("USER")
    @RequestMapping("/markTime")
    public ModelAndView markTime(@RequestParam("activity_id") int activityId,
                                 @RequestParam("days") int days,
                                 @RequestParam("hours") int hours,
                                 @RequestParam("minutes") int minutes,
                                 HttpSession session) {

        ModelAndView mv = new ModelAndView();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Fetching user {}", username);
        User user = userService.getUser(username);
        log.info("Fetching activity with id {}", activityId);
        Activity activity = service.getActivity(activityId);

        boolean canMarkTime = false;
        for (User us : activity.getUsers()) {
            if (us.getUsername().equals(user.getUsername())) {
                canMarkTime = true;
                break;
            }
        }

        if (!canMarkTime) {
            log.warn("User {} is not doing activity {}", username, activity.getName());
            mv.setViewName("/WEB-INF/pages/activities");
            return mv;
        }

        int duration = minutes + hours * 60 + days * 24 * 60;

        List<String> errors = TimeValidator.validateState(String.valueOf(days),
                String.valueOf(hours), String.valueOf(minutes));

        if (!errors.isEmpty()) {
            log.warn("Found some errors: {}. Nothing changed", errors);
            mv.addObject("errors", errors);
            mv.setViewName("/WEB-INF/pages/activities");
            return mv;
        }

        log.info("Taking time for activity {}", activity.getName());
        service.takeActivityTime(activityId, user, duration);

        mv.setViewName("redirect:/activities");
        return mv;
    }

}
