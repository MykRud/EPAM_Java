package final_task_spring.test.java.com.spring_final.SpringFinalProject.service;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.repo.ActivityDaoRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.HashSet;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    private ActivityDaoRep activityRepo;

    private ActivityService underTestActivityService;

    @BeforeEach
    void setUp() {
        underTestActivityService = new ActivityService();
        underTestActivityService.setActivityDao(activityRepo);
    }

    @Test
    void addActivity() {
        // given
        Activity activity = new Activity();
        activity.setName("Football");
        activity.setStatus("Active");

        // when
        underTestActivityService.addActivity(activity);

        // then
        ArgumentCaptor<Activity> activityArgumentCaptor = ArgumentCaptor.forClass(Activity.class);

        verify(activityRepo).save(activityArgumentCaptor.capture());

        Activity capturedActivity = activityArgumentCaptor.getValue();

        assertThat(capturedActivity).isEqualTo(activity);
    }

    @Test
    void getActivity() {
        // when
        underTestActivityService.getActivity("Football");

        // then
        verify(activityRepo).getByName("Football");
    }

    @Test
    void testGetActivity() {
        // when
        underTestActivityService.getActivity(1);

        // then
        verify(activityRepo).findById(1);
    }

    @Test
    void getActivities() {
        // when
        underTestActivityService.getActivities();

        // then
        verify(activityRepo).findAll();
    }

    @Test
    void getNumberOfActivities() {
        // when
        underTestActivityService.getNumberOfActivities();

        // then
        verify(activityRepo).count();
    }

    @Test
    void deleteActivity() {
        // when
        underTestActivityService.deleteActivity(1);

        // then
        verify(activityRepo).deleteById(1);
    }

    @Test
    void takeActivityTime() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Travolta");
        user.setUsername("john");
        user.setPassword("1234");

        Activity activity = new Activity(null, "Football", "Active", "Playing football", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());

        user.getActivities().add(activity);

        activity.getUsers().add(user);

        assertThatThrownBy(() -> {
            underTestActivityService.takeActivityTime(1, user, 1234234);
        }).isInstanceOf(NoSuchElementException.class);

    }

    @Test
    void updateActivity() {
        // given
        Activity activity = new Activity();
        activity.setName("Football");
        activity.setStatus("Active");
        underTestActivityService.addActivity(activity);

        // when
        activity.setName("Basketball");
        underTestActivityService.updateActivity(activity);

        // then
        ArgumentCaptor<Activity> activityArgumentCaptor = ArgumentCaptor.forClass(Activity.class);

        verify(activityRepo, times(2)).save(activityArgumentCaptor.capture());

        Activity capturedActivity = activityArgumentCaptor.getValue();

        assertThat(capturedActivity).isEqualTo(activity);
    }

    @Test
    void getActivitiesInLimitByName() {
        // when
        assertThatThrownBy(() -> {
            underTestActivityService.getActivitiesInLimitByName(0, 5);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getActivitiesInLimitByType() {
        // when
        assertThatThrownBy(() -> {
            underTestActivityService.getActivitiesInLimitByType(1, 5);
        }).isInstanceOf(NullPointerException.class);
    }

   /* @Test
    void getActivitiesInLimitByNumberOfUsers() {
        // when
        assertThatThrownBy(() -> {
            underTestActivityService.getActivitiesInLimitByNumberOfUsers(1, 5);
        }).isInstanceOf(Exception.class);
    }*/
}