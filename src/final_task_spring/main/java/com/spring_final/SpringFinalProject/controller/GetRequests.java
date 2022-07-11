package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.ActivityRequest;
import com.spring_final.SpringFinalProject.service.ActivityRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Activity Requests controller that allows you to view activity request pages
 *
 * @author Misha Rudyk
 * @see ActivityRequest
 * @see ActivityRequestService
 */
@Controller
@Slf4j
public class GetRequests {

    @Autowired
    ActivityRequestService requestService;

    /**
     * Mapped method
     *
     * @return page with requests
     */
    @Secured("ADMIN")
    @RequestMapping("/admin/activitiesRequests")
    public ModelAndView getActivities(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "5") int size) {
        ModelAndView mv = new ModelAndView();

        log.info("Fetching number of requests");
        int numberOfRequests = requestService.getNumberOfRequests();
        int totalPages = (int) Math.ceil((double) numberOfRequests /
                (double) size);

        log.info("Fetching {} requests for page {}", size, page);
        List<ActivityRequest> requests = requestService.getRequestsInLimit(page, size);

        mv.addObject("requests", requests);
        mv.addObject("currentPage", page);
        mv.addObject("pageSize", size);
        mv.addObject("totalPages", totalPages);
        mv.setViewName("WEB-INF/pages/admin/activity-requests");
        return mv;
    }

}
