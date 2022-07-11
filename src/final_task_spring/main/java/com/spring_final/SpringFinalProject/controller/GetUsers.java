package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Role;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Users controller that allows the admin to view pages with users
 *
 * @author Misha Rudyk
 * @see User
 * @see UserService
 */
@Controller
@Slf4j
public class GetUsers {

    @Autowired
    UserService service;

    /**
     * Mapped method
     *
     * @return page with users
     */
    @Secured("ADMIN")
    @RequestMapping("/admin/users")
    public ModelAndView getUsers() {
        ModelAndView mv = new ModelAndView();

        log.info("Fetching users");
        List<User> users = service.getUsers();

        mv.addObject("users", users);

        Role admin = service.getRole("ADMIN");
        mv.addObject("adminRole", admin);

        mv.setViewName("WEB-INF/pages/admin/users");
        return mv;
    }

}
