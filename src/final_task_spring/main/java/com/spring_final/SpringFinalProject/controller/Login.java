package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller that allows registered users to log in
 *
 * @author Misha Rudyk
 * @see User
 * @see com.spring_final.SpringFinalProject.security.SecurityConfig
 * @see com.spring_final.SpringFinalProject.filter.CustomAuthenticationFilter
 * @see com.spring_final.SpringFinalProject.filter.CustomAuthorizationFilter
 */
@Controller
public class Login {

    /**
     * Mapped method
     *
     * @return home page
     */
    @RequestMapping("/login")
    public String addActivityPost() {
        return "login";
    }

}
