package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import com.spring_final.SpringFinalProject.service.ActivityService;
import com.spring_final.SpringFinalProject.service.TypeOfActivityService;
import com.spring_final.SpringFinalProject.validator.ActivityValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Add activity controller with which the user can add activities
 *
 * @author Misha Rudyk
 * @see ActivityService
 * @see Activity
 */
@Controller
@Slf4j
public class AddActivity {

    @Autowired
    TypeOfActivityService typeService;
    @Autowired
    ActivityService activityService;

    /**
     * Mapped method
     *
     * @return redirecting to display method
     */
    @Secured("ADMIN")
    @RequestMapping("/admin/activitiesAdd")
    public ModelAndView addActivityPost(@ModelAttribute("activity") Activity activity,
                                        final RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        List<TypeOfActivity> types = typeService.getTypes();
        log.info("Fetching types");
        mv.addObject("types", types);
        redirectAttributes.addFlashAttribute("types", types);
        if (activity.getName() == null) {
            log.info("Get request or null name of activity");
            mv.setViewName("WEB-INF/pages/admin/add-activity");
            return mv;
        }

        log.info("Fetching type of activity");
        TypeOfActivity type = typeService.getType(activity.getType().getName());
        if (type != null)
            activity.setType(type);
        activity.setStatus("Pending");

        redirectAttributes.addFlashAttribute("activity", activity);

        int successful = 1;

        List<String> errors = ActivityValidator.validateState(activity);
        if (!errors.isEmpty()) {
            log.warn("Found some errors. Activity {} has not been saved", activity.getName());
            redirectAttributes.addFlashAttribute("errors", errors);
            successful = 0;
            mv.setViewName("redirect:/admin/addActivityDisplay?s=" + successful);
            return mv;
        }
        log.info("Adding activity {}...", activity.getName());
        activityService.addActivity(activity);
        mv.setViewName("redirect:/admin/addActivityDisplay?s=" + successful);
        return mv;
    }
}
