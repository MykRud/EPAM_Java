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
 * A controller that allows you to confirm requests to add or complete an activity
 *
 * @author Misha Rudyk
 * @see ActivityRequest
 * @see ActivityRequestService
 */
@Controller
@Slf4j
public class ApproveRequest {

    @Autowired
    ActivityRequestService service;

    /**
     * Mapped method
     *
     * @return page with requests
     */
    @Secured("ADMIN")
    @RequestMapping("/admin/activityRequestApprove")
    public String approveRequest(@RequestParam("id") int id) {
        ActivityRequest request = service.getRequest(id);

        if (!request.getStatus().equals("Pending")) {
            log.warn("Activity status doesn't not equal to Pending. Nothing changed");
            return "redirect:/admin/activitiesRequests";
        }

        if (request.getAction().equals("Add")) {
            log.info("Making approve request");
            service.approveRequest(request);
        } else if (request.getAction().equals("Complete")) {
            log.info("Making complete request");
            service.completeRequest(request);
        }

        return "redirect:/admin/activitiesRequests";
    }

}
