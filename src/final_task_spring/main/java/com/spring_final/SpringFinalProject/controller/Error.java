package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller responsible for displaying error 404
 *
 * @author Misha Rudyk
 */
@Controller
@Slf4j
public class Error implements ErrorController {

    /**
     * Mapped method
     *
     * @return 404 page
     */
    @RequestMapping("/error")
    public String error() {
        log.warn("Unknown page. 404");
        return "WEB-INF/pages/error/404";
    }

}
