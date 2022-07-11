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

/**
 * The controller that allows to fetch activity by id or name
 *
 * @author Misha Rudyk
 * @see Activity
 * @see ActivityService
 */
@Controller
@Slf4j
public class GetActivity {

    @Autowired
    ActivityService service;

    /**
     * Mapped method
     *
     * @return page with activities
     */
    @Secured("USER")
    @RequestMapping("/getActivityById")
    public ModelAndView getActivity(@RequestParam("id") int id) {
        ModelAndView mv = new ModelAndView();
        log.info("Fetching activity with id {}", id);
        Activity activity = service.getActivity(id);
        mv.addObject("activity", activity);
        mv.setViewName("/WEB-INF/pages/activities");
        return mv;
    }

    @Secured("USER")
    @RequestMapping("/getActivityByName")
    public ModelAndView getActivity(@RequestParam("name") String name) {
        ModelAndView mv = new ModelAndView();
        log.info("Fetching activity with name {}", name);
        Activity activity = service.getActivity(name);
        mv.addObject("activity", activity);
        mv.setViewName("/WEB-INF/pages/activities");
        return mv;
    }

}
