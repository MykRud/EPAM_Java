package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * The controller that allows you to log out
 *
 * @author Misha Rudyk
 * @see HttpSession
 * @see com.spring_final.SpringFinalProject.security.SecurityConfig
 */
@Controller
public class Logout {

    /**
     * Mapped method
     *
     * @return home page
     */
    @RequestMapping("/logout-success")
    public String logout() {
        return "redirect:home";
    }

}
