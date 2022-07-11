package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import com.spring_final.SpringFinalProject.service.TypeOfActivityService;
import com.spring_final.SpringFinalProject.validator.TypeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Add type controller with which the user can add types
 *
 * @author Misha Rudyk
 * @see TypeOfActivityService
 * @see TypeOfActivity
 */
@Controller
@Slf4j
public class AddType {

    @Autowired
    TypeOfActivityService service;

    /**
     * Mapped method
     *
     * @return redirection to display method
     */
    @Secured("ADMIN")
    @RequestMapping("/admin/typesAdd")
    public ModelAndView addType(@ModelAttribute("type") TypeOfActivity type,
                                final RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        if (type.getName() == null) {
            log.info("Get request or name of type is null");
            mv.setViewName("WEB-INF/pages/admin/add-type");
            return mv;
        }

        redirectAttributes.addFlashAttribute("type", type);

        int successful = 1;
        if (service.getType(type.getName()) != null) {
            log.warn("Type is already represented");
            successful = 0;
            mv.setViewName("redirect:/admin/typeDisplay?s=" + successful);
            return mv;
        }

        List<String> errors = TypeValidator.validateState(type);

        if (!errors.isEmpty()) {
            log.warn("Found some errors: {}", errors);
            log.warn("Type has not been saved");
            redirectAttributes.addFlashAttribute("errors", errors);
            successful = 0;
            mv.setViewName("redirect:/admin/typeDisplay?s=" + successful);
            return mv;
        }

        log.info("Adding type {}...", type.getName());
        service.addType(type);

        mv.setViewName("redirect:/admin/typeDisplay?s=" + successful);
        return mv;
    }

}
