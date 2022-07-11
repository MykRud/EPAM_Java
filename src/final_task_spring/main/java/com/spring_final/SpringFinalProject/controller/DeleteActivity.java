package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Activity controller that allows you to delete activity
 *
 * @author Misha Rudyk
 * @see ActivityService
 * @see com.spring_final.SpringFinalProject.model.Activity
 */
@Controller
@Slf4j
public class DeleteActivity {

    @Autowired
    ActivityService service;

    /**
     * Mapped method
     *
     * @return page with activities
     */
    @Secured("ADMIN")
    @RequestMapping("/admin/activityDelete")
    public String deleteActivity(@RequestParam("activity_id") int id) {
        log.info("Deleting user with id {}", id);
        service.deleteActivity(id);
        return "redirect:/activities";
    }


}
