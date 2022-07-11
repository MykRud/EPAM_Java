package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import com.spring_final.SpringFinalProject.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * The controller that allows admin to update profile of some user
 *
 * @author Misha Rudyk
 * @see User
 * @see UserService
 * @see UpdateProfile
 */
@Controller
@Slf4j
public class UpdateUser {

    @Autowired
    UserService service;

    /**
     * Mapped method
     *
     * @return profile page of some user
     */
    @Secured("ADMIN")
    @RequestMapping("/admin/userUpdate")
    public ModelAndView updateUser(@RequestParam("user_id") int user_id,
                                   @ModelAttribute("user") User user, final RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();

        if (user.getFirstName() == null || user.getLastName() == null ||
                user.getUsername() == null ||
                user.getAge() == 0 || user.getGender() == null ||
                user.getContact() == null) {
            log.info("Get request or data is not valid");
            mv.setViewName("WEB-INF/pages/admin/update-user");
            return mv;
        }

        redirectAttributes.addFlashAttribute("user_id", user_id);

        log.info("Fetching user with id {}", user_id);
        User foundUser = service.getUser(user_id);

        log.info("Updating user {} with new data", foundUser.getUsername());
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setUsername(user.getUsername());
        foundUser.setAge(user.getAge());
        foundUser.setGender(user.getGender());
        foundUser.setContact(user.getContact());

        List<String> errors = UserValidator.validateState(user);

        int successful = 1;
        if (!errors.isEmpty()) {
            log.warn("Found some errors: {}. Nothing changed", errors);
            redirectAttributes.addFlashAttribute("errors", errors);
            successful = 0;
            mv.setViewName("redirect:/admin/userUpdateDisplay?s=" + successful);
            return mv;
        }

        log.info("Updating user");
        service.updateUser(user);

        mv.setViewName("redirect:/admin/userUpdateDisplay?s=" + successful);
        return mv;
    }
}
