package final_task_spring.main.java.com.spring_final.SpringFinalProject.service;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.ActivityRequest;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.repo.ActivityDaoRep;
import com.spring_final.SpringFinalProject.repo.ActivityRequestDaoRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * The service with business logic related to requests
 *
 * @author Misha Rudyk
 * @see ActivityRequestDaoRep
 * @see ActivityDaoRep
 * @see ActivityRequest
 */
@Component
@Slf4j
public class ActivityRequestService {

    @Autowired
    private ActivityRequestDaoRep requestDao;
    @Autowired
    private ActivityDaoRep activityDao;

    /**
     * Method that allows to add request
     *
     * @param request ActivityRequest to add
     */
    public void addRequest(ActivityRequest request) {
        requestDao.save(request);
        log.info("Request saved");
    }

    /**
     * Method that allows to get request by its id
     *
     * @param id Id of request
     * @return ActivityRequest with desired id
     */
    public ActivityRequest getRequest(int id) {
        try {
            return requestDao.findById(id).get();
        } catch (Exception e) {
            log.error("Unable to get request with id {}", id);
            return null;
        }
    }

    /**
     * Method that allows to get number of requests
     *
     * @return number of requests
     */
    public int getNumberOfRequests() {
        return (int) requestDao.count();
    }

    /**
     * Method that allows to make request for adding activity
     *
     * @param user     User how wants to get some activity
     * @param activity Desired activity
     */
    public void makeAddRequest(User user, Activity activity) {
        List<ActivityRequest> requests = requestDao.findByUserIdAndActivityId(user.getId(), activity.getId());

        if (!requests.isEmpty()) {
            for (ActivityRequest currentRequest : requests) {
                if (!currentRequest.getAction().equals("Add") && !currentRequest.getStatus().equals("Rejected")) {
                    log.warn("Unable to make add request");
                    return;
                }
            }
        }

        if (activity.getStatus().equals("Completed")) {
            log.warn("Unable to make add request as activity is already completed");
            return;
        } else if (activity.getStatus().equals("Active")) {
            log.warn("Unable to make add request as activity is already been activated");
            return;
        }
        ActivityRequest request = new ActivityRequest();

        request.setUser(user);
        request.setActivity(activity);
        request.setAction("Add");
        request.setStatus("Pending");

        requestDao.save(request);
        log.info("Request added");
    }

    /**
     * Method that allows to make request for completing activity
     *
     * @param user     User how wants to complete some activity
     * @param activity Desired activity
     */
    @Transactional
    public void makeCompleteRequest(User user, Activity activity) {

        List<ActivityRequest> requests = requestDao.findByUserIdAndActivityId(user.getId(), activity.getId()); // Error is here!!!

        if (requests.isEmpty()) {
            log.warn("There is no requests in database");
            return;
        }

        for (ActivityRequest currentRequest : requests) {
            if (currentRequest.getAction().equals("Complete") && !currentRequest.getStatus().equals("Rejected")
                    || currentRequest.getAction().equals("Add") && currentRequest.getStatus().equals("Pending")) {
                log.warn("Unable to make complete request");
                return;
            }
        }

        if (activity.getStatus().equals("Completed")) {
            log.warn("Unable to make complete request as activity is already comleted");
            return;
        } else if (activity.getStatus().equals("Pending")) {
            log.warn("Unable to make complete request as activity is only in pending to be activated");
            return;
        }
        ActivityRequest request = new ActivityRequest();
        request.setUser(user);
        request.setActivity(activity);
        request.setAction("Complete");
        request.setStatus("Pending");

        requestDao.save(request);
        log.info("Request added");

    }

    /**
     * Method that allows admin to approve add request
     *
     * @param request Desired request that has to be approved
     */
    public void approveRequest(ActivityRequest request) {
        Activity activity = request.getActivity();
        User user = request.getUser();

        String status = activity.getStatus();

        if (status.equals("Pending")) {
            Timestamp startTime = Timestamp.valueOf(LocalDateTime.now());
            activity.setStartTime(new Date(startTime.getTime()));
            activity.setStatus("Active");
            activity.getUsers().add(user);
            user.getActivities().add(activity);
            request.setStatus("Approved");

            activityDao.save(activity);
            log.info("Activity updated");
        } else if (status.equals("Active")) {
            activity.getUsers().add(user);
            user.getActivities().add(activity);
            request.setStatus("Approved");

            activityDao.save(activity);
            log.info("Activity updated");
        } else if (status.equals("Completed")) {
            request.setStatus("Rejected");
        }

        requestDao.save(request);
        log.info("Request approved");
    }

    /**
     * Method that allows admin to approve complete request
     *
     * @param request Complete request that has to be approved
     */
    public void completeRequest(ActivityRequest request) {
        Activity activity = request.getActivity();
        User user = request.getUser();

        String status = activity.getStatus();

        if (status.equals("Pending")) {
            log.warn("Unable to approve complete request as activity is only in pending to be activated");
            return;
        } else if (status.equals("Active")) {
            Timestamp startTime = Timestamp.valueOf(LocalDateTime.now());
            activity.setEndTime(new Date(startTime.getTime()));
            activity.setStatus("Completed");
            request.setStatus("Approved");

            activityDao.save(activity);
            log.info("Activity updated");
        } else if (status.equals("Completed")) {
            request.setStatus("Rejected");
        }

        requestDao.save(request);
        log.info("Request added");
    }

    /**
     * Method that allows admin to reject request
     *
     * @param request Desired request that has to be rejected
     */
    public void rejectRequest(ActivityRequest request) {
        request.setStatus("Rejected");
        requestDao.save(request);
        log.info("Request rejected");
    }

    /**
     * Method that allows to get list of requests
     *
     * @return list of requests
     */
    public List<ActivityRequest> getRequests() {
        List<ActivityRequest> requests = requestDao.findAll();
        if (requests.isEmpty())
            log.info("No requests found");
        return requests;
    }

    /**
     * Method that allows to get pages with requests
     *
     * @param page Number of page
     * @param size Number of requests on page
     * @return list of requests
     */
    public List<ActivityRequest> getRequestsInLimit(int page, int size) {
        Pageable pages = PageRequest.of(page, size, Sort.by("id").descending());
        List<ActivityRequest> requests = requestDao.findAll(pages).toList();
        if (requests.isEmpty())
            log.info("No requests found");
        return requests;
    }

    public ActivityRequestDaoRep getRequestDao() {
        return requestDao;
    }

    public void setRequestDao(ActivityRequestDaoRep requestDao) {
        this.requestDao = requestDao;
    }

    public ActivityDaoRep getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDaoRep activityDao) {
        this.activityDao = activityDao;
    }
}
