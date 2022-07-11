package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import com.spring_final.SpringFinalProject.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * The controller that allows user to update profile
 *
 * @author Misha Rudyk
 * @see User
 * @see UserService
 * @see UpdateUser
 */
@Controller
@Slf4j
public class UpdateProfile {

    @Autowired
    UserService service;

    /**
     * Mapped method
     *
     * @return profile page of user in session
     */
    @Secured("USER")
    @RequestMapping("/userProfileUpdate")
    public ModelAndView updateProfile(@ModelAttribute("user") User user,
                                      final RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        if (user.getFirstName() == null || user.getLastName() == null ||
                user.getPassword() == null ||
                user.getAge() == 0 ||
                user.getContact() == null) {
            log.info("Get request or profile data is not valid");
            mv.setViewName("WEB-INF/pages/profile-update");
            return mv;
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        log.info("Fetching user {}", username);
        User foundUser = service.getUser(username);

        log.info("Updating user instance with new data");
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setPassword(user.getPassword());
        foundUser.setAge(user.getAge());
        foundUser.setContact(user.getContact());

        redirectAttributes.addFlashAttribute("user", user);

        List<String> errors = UserValidator.validateState(foundUser);

        int successful = 1;
        if (!errors.isEmpty()) {
            log.warn("Found some errors: {}. Nothing changed", errors);
            redirectAttributes.addFlashAttribute("errors", errors);
            successful = 0;
            mv.setViewName("redirect:/profileUpdateDisplay?s=" + successful);
            return mv;
        }

        log.info("Updating user {} with new data", username);
        service.updateProfile(foundUser);

        mv.setViewName("redirect:/profileUpdateDisplay?s=" + successful);
        return mv;
    }

}
