package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.ActivityRequestService;
import com.spring_final.SpringFinalProject.service.ActivityService;
import com.spring_final.SpringFinalProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A controller that allows to send requests to add activity
 *
 * @author Misha Rudyk
 * @see ActivityRequestService
 * @see com.spring_final.SpringFinalProject.model.ActivityRequest
 */
@Controller
@Slf4j
public class AddRequest {

    @Autowired
    ActivityService activityService;
    @Autowired
    UserService userService;
    @Autowired
    ActivityRequestService requestService;

    /**
     * Mapped method
     *
     * @return page with activities
     */
    @Secured("USER")
    @RequestMapping("/activityRequestAdd")
    public String addRequest(@RequestParam("activity_id") int activityId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Fetching user {}", username);
        User user = userService.getUser(username);
        log.info("Fetching activity with id {}", activityId);
        Activity activity = activityService.getActivity(activityId);
        log.info("Adding request with activity {} and user {}", activity.getName(), user.getUsername());
        requestService.makeAddRequest(user, activity);
        return "redirect:/activities";

    }

}
