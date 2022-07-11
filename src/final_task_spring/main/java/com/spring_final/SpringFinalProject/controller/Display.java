package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.TypeOfActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Display controllers that prevent multiple post requests (PRG pattern)
 *
 * @author Misha Rudyk
 * @see AddActivity
 * @see Registration
 * @see AddType
 * @see UpdateProfile
 * @see UpdateUser
 */
@Controller
public class Display {

    private static ResourceBundle resourceBundle;

    /**
     * Set bundle depend on selected language
     */
    private static void setResourceBundle() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        String lang = (String) session.getAttribute("lang");
        if (lang != null)
            resourceBundle = ResourceBundle.getBundle("messages", Locale.forLanguageTag(lang));
        else
            resourceBundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

    @Autowired
    private TypeOfActivityService typeService;

    private final Activity instanceOfActivity = new Activity();

    /**
     * Display method for adding activity
     *
     * @return activity page
     */
    @RequestMapping("/admin/addActivityDisplay")
    public ModelAndView addActivityDisplay(@RequestParam(value = "s", defaultValue = "-1") int success,
                                           Model model) {
        setResourceBundle();

        ModelAndView mv = new ModelAndView();

        mv.addObject("types", typeService.getTypes());

        if (model.asMap().containsKey("activity"))
            mv.addObject("activity", model.asMap().get("activity"));
        else
            mv.addObject("activity", instanceOfActivity);

        if (success != -1) {
            if (success == 1) //
                mv.addObject("result", resourceBundle.getString("display.activity.successful"));
            else {
                if (model.asMap().containsKey("errors"))
                    mv.addObject("errors", model.asMap().get("errors"));

                mv.addObject("result",
                        resourceBundle.getString("display.activity.error"));
            }
        }
        //model.addAttribute("activity", model.asMap().get("activity"));
        mv.setViewName("WEB-INF/pages/admin/add-activity");
        return mv;
    }

    private final User instanceOfUser = new User();

    /**
     * Display method for registration
     *
     * @return registration page
     */
    @RequestMapping("/registrationDisplay")
    public ModelAndView registrationDisplay(@RequestParam(value = "s", defaultValue = "-1") int success, Model model) {
        setResourceBundle();

        ModelAndView mv = new ModelAndView();

        if (model.asMap().containsKey("user"))
            mv.addObject("user", model.asMap().get("user"));
        else
            mv.addObject("user", instanceOfUser);

        if (success != -1) {
            if (success == 1)
                mv.addObject("result", resourceBundle.getString("display.registration.successful"));
            else {
                if (model.asMap().containsKey("errors"))
                    mv.addObject("errors", model.asMap().get("errors"));

                mv.addObject("result",
                        resourceBundle.getString("display.registration.error"));
            }
        }
        mv.setViewName("registration");
        return mv;
    }

    private final TypeOfActivity instanceOfType = new TypeOfActivity();

    /**
     * Display method for adding type
     *
     * @return type page
     */
    @RequestMapping("/admin/typeDisplay")
    public ModelAndView typeDisplay(@RequestParam(value = "s", defaultValue = "-1") int success, Model model) {
        setResourceBundle();

        ModelAndView mv = new ModelAndView();

        if (model.asMap().containsKey("type"))
            mv.addObject("type", model.asMap().get("type"));
        else
            mv.addObject("type", instanceOfType);

        if (success != -1) {
            if (success == 1)
                mv.addObject("result", resourceBundle.getString("display.type.successful"));
            else {
                if (model.asMap().containsKey("errors"))
                    mv.addObject("errors", model.asMap().get("errors"));

                mv.addObject("result",
                        resourceBundle.getString("display.type.error"));
            }
        }
        mv.setViewName("WEB-INF/pages/admin/add-type");
        return mv;
    }

    /**
     * Display method for update profile
     *
     * @return update profile page
     */
    @RequestMapping("/profileUpdateDisplay")
    public ModelAndView profileUpdateDisplay(@RequestParam(value = "s", defaultValue = "-1") int success,
                                             Model model) {
        setResourceBundle();

        ModelAndView mv = new ModelAndView();

        if (model.asMap().containsKey("user"))
            mv.addObject("user", model.asMap().get("user"));
        else
            mv.addObject("user", instanceOfUser);

        if (success != -1) {
            if (success == 1) {
                mv.addObject("result", resourceBundle.getString("display.profile.successful"));
            } else {
                if (model.asMap().containsKey("errors"))
                    mv.addObject("errors", model.asMap().get("errors"));

                mv.addObject("result",
                        resourceBundle.getString("display.profile.error"));
            }
        }
        mv.setViewName("WEB-INF/pages/profile-update");
        return mv;
    }

    /**
     * Display method for updating user
     *
     * @return update-user-profile page
     */
    @RequestMapping("/admin/userUpdateDisplay")
    public ModelAndView userUpdateDisplay(@RequestParam(value = "s", defaultValue = "-1") int success,
                                          Model model) {
        setResourceBundle();

        ModelAndView mv = new ModelAndView();

        if (model.asMap().containsKey("user_id"))
            mv.addObject("user_id", model.asMap().get("user_id"));

        if (success != -1) {
            if (success == 1)
                mv.addObject("result", resourceBundle.getString("display.user.update.successful"));
            else {
                if (model.asMap().containsKey("errors"))
                    mv.addObject("errors", model.asMap().get("errors"));

                mv.addObject("result",
                        resourceBundle.getString("display.user.update.error"));
            }
        }
        mv.setViewName("WEB-INF/pages/admin/update-user");
        return mv;
    }

}
