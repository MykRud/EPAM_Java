package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The controller that allows you to view the user profile
 *
 * @author Misha Rudyk
 * @see UserService
 * @see User
 */
@Controller
@Slf4j
public class Profile {

    @Autowired
    UserService service;

    /**
     * Mapped method
     *
     * @return page with user profile
     */
    @Secured("USER")
    @RequestMapping("/profile")
    public ModelAndView profile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Fetching user {} for profile", username);
        User user = service.getUser(username);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("/WEB-INF/pages/profile");
        return mv;
    }

}
