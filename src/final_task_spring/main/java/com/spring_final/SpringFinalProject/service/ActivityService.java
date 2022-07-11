package final_task_spring.main.java.com.spring_final.SpringFinalProject.service;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.repo.ActivityDaoRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The service with business logic related to activities
 *
 * @author Misha Rudyk
 * @see ActivityDaoRep
 * @see Activity
 */
@Component
@Slf4j
public class ActivityService {

    @Autowired
    private ActivityDaoRep activityDao;

    /**
     * Method that allows to add activity
     *
     * @param activity Activity to add
     */
    public void addActivity(Activity activity) {
        activityDao.save(activity);
        log.info("Activity added");
    }

    /**
     * Method that allows to get activity by its id
     *
     * @param id Id of activity
     * @return Activity with desired id
     */
    public Activity getActivity(int id) {
        Activity activity;
        try {
            activity = activityDao.findById(id).get();
        } catch (Exception e) {
            log.warn("Activity with id {} not found", id);
            activity = null;
        }
        return activity;
    }

    /**
     * Method that allows to get activity by its name
     *
     * @param name Name of activity
     * @return Activity with desired name
     */
    public Activity getActivity(String name) {
        Activity activity = activityDao.getByName(name);
        if (activity == null)
            log.info("Activity {} not found", name);
        log.info("Activity {} found", name);
        return activity;
    }

    /**
     * Method that allows to get list of activities
     *
     * @return list of activities
     */
    public List<Activity> getActivities() {
        List<Activity> activities = activityDao.findAll();
        if (activities.isEmpty())
            log.info("No activities found");
        return activities;
    }

    /**
     * Method that allows to get number of activities
     *
     * @return number of activities
     */
    public int getNumberOfActivities() {
        return (int) activityDao.count();
    }

    /**
     * Method that allows to delete activity by its id
     *
     * @param id Id of activity
     */
    @Transactional
    public void deleteActivity(int id) {
        try {
            activityDao.deleteById(id);
        } catch (Exception e) {
            log.warn("Activity didn't been deleted");
            return;
        }
        log.info("Activity deleted");
    }

    /**
     * Method that allows to mark time for activity by its id
     *
     * @param id       Id of activity
     * @param user     User that wants to mark time
     * @param duration Duration that has to be added to activity
     */
    public void takeActivityTime(int id, User user, int duration) {
        Activity activity = activityDao.findById(id).get();
        if (activity.getStatus().equals("Active")) {
            activity.setDuration(duration);
            activityDao.save(activity);
            log.info("Activity updated");
        }
    }

    /**
     * Method that allows to update information related to some activity
     *
     * @param activity Activity that has to be updated
     */
    @Transactional
    public void updateActivity(Activity activity) {
        activityDao.save(activity);
        log.info("Activity updated");
    }

    /**
     * Method that allows to get pages with activities ordered by its name
     *
     * @param size Max number of activities on page
     * @param page Number of current page
     * @return list of activities
     */
    public List<Activity> getActivitiesInLimitByName(int size, int page) {
        Pageable pages = PageRequest.of(page, size, Sort.by("name"));
        List<Activity> activities = activityDao.findAll(pages).toList();
        log.info("Activities fetched");
        return activities;
    }

    /**
     * Method that allows to get pages with activities ordered by its type
     *
     * @param size Max number of activities on page
     * @param page Number of current page
     * @return list of activities
     */
    public List<Activity> getActivitiesInLimitByType(int size, int page) {
        Pageable pages = PageRequest.of(page, size, Sort.by("type_id"));
        List<Activity> activities = activityDao.findAll(pages).toList();
        log.info("Activities fetched");
        return activities;
    }

    /**
     * Method that allows to get pages with activities ordered by number of users
     *
     * @param size Max number of activities on page
     * @param page Number of current page
     * @return list of activities
     */
    public List<Activity> getActivitiesInLimitByNumberOfUsers(int size, int page) {
        Pageable pages = PageRequest.of(page, size, Sort.by("number_of_users").descending());
        List<Activity> activities = activityDao.findByNumberOfUsers(pages);
        log.info("Activities fetched");
        return activities;
    }

    public ActivityDaoRep getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDaoRep activityDao) {
        this.activityDao = activityDao;
    }
}
