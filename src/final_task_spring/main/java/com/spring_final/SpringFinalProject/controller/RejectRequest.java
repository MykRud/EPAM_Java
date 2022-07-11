package final_task_spring.main.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.ActivityRequest;
import com.spring_final.SpringFinalProject.service.ActivityRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A controller that allows you to reject requests to add or complete an activity
 *
 * @author Misha Rudyk
 * @see ActivityRequest
 * @see ActivityRequestService
 */
@Controller
@Slf4j
public class RejectRequest {

    @Autowired
    ActivityRequestService service;

    /**
     * Mapped method
     *
     * @return page with requests
     */
    @Secured("ADMIN")
    @RequestMapping("/admin/activityRequestReject")
    public String rejectRequest(@RequestParam("id") int id) {
        log.info("Fetching request with id {}", id);
        ActivityRequest request = service.getRequest(id);

        if (!request.getStatus().equals("Pending")) {
            log.warn("Status of request is not pending. Nothing changed");
            return "redirect:/admin/activitiesRequests";
        }

        log.info("Rejecting request");
        service.rejectRequest(request);

        return "redirect:/admin/activitiesRequests";
    }

}
