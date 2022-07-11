package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The controller that allows you to delete a user from the system
 *
 * @author Misha Rudyk
 * @see com.spring_final.SpringFinalProject.model.User
 * @see UserService
 */
@Controller
@Slf4j
public class DeleteUser {

    @Autowired
    UserService service;

    /**
     * Mapped method
     *
     * @return page with users
     */
    @Secured("ADMIN")
    @RequestMapping("/admin/userDelete")
    public String deleteUser(@RequestParam("id") int id) {

        if (service.getUser(id) == null) {
            log.info("User is not found");
            return "WEB-INF/pages/admin/users";
        }

        log.info("Deleting user");
        service.deleteUser(id);
        return "redirect:/admin/users";
    }


}
