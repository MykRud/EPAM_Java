package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import com.spring_final.SpringFinalProject.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * The controller that allows you to register a user in the system
 *
 * @author Misha Rudyk
 * @see User
 * @see UserService
 */
@Controller
@Slf4j
public class Registration {

    @Autowired
    UserService service;

    /**
     * Mapped method
     *
     * @return login page
     */
    @RequestMapping("/registration")
    public ModelAndView registration(@ModelAttribute("user") User user,
                                     final RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        if (user.getFirstName() == null || user.getLastName() == null ||
                user.getUsername() == null || user.getPassword() == null ||
                user.getAge() == 0 || user.getGender() == null ||
                user.getContact() == null) {
            log.info("Get request or activity's data is not valid");
            mv.setViewName("registration");
            return mv;
        }

        log.info("Fetching user {}", user.getUsername());
        User foundUser = service.getUser(user.getUsername());

        redirectAttributes.addFlashAttribute("user", user);

        int successful = 1;
        if (foundUser != null) {
            log.warn("User is already represented");
            successful = 0;
            mv.setViewName("redirect:/registrationDisplay?s=" + successful);
            return mv;
        }

        List<String> errors = UserValidator.validateState(user);

        if (!errors.isEmpty()) {
            log.warn("Found some errors in registration: {}. User has not been added", errors);
            redirectAttributes.addFlashAttribute("errors", errors);
            successful = 0;
            mv.setViewName("redirect:/registrationDisplay?s=" + successful);
            return mv;
        }

        log.info("Save user");
        service.addUser(user);

        mv.setViewName("redirect:/login");
        return mv;
    }
}
