package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller that allows you to access the index page of the site
 *
 * @author Misha Rudyk
 */
@Controller
public class Home {

    /**
     * Mapped method
     *
     * @return home page
     */
    @RequestMapping("/home")
    public String getHome() {
        return "index";
    }

}
