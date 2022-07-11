package final_task_spring.test.java.com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.ActivityRequest;
import com.spring_final.SpringFinalProject.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
class ActivityRequestDaoRepTest {

    @Autowired
    @MockBean
    private ActivityRequestDaoRep underTestRequestRepo;
    @Autowired
    private UserDaoRep userRepo;
    @Autowired
    private ActivityDaoRep activityRepo;


    @AfterEach
    void tearDown() {
        underTestRequestRepo.deleteAll();
    }

    @Test
    void findByUserIdAndActivityId() {
        // given
        User user = new User(1, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        Activity activity = new Activity(1, "Football", "Active", "Playing football", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());

        userRepo.save(user);
        activityRepo.save(activity);

        ActivityRequest expectedRequest = new ActivityRequest();
        expectedRequest.setId(1);
        expectedRequest.setUser(user);
        expectedRequest.setActivity(activity);
        expectedRequest.setAction("Add");
        expectedRequest.setStatus("Approved");

        user.getActivities().add(activity);
        activity.getUsers().add(user);
        user.getActivityRequests().add(expectedRequest);
        activity.getActivityRequests().add(expectedRequest);

        when(underTestRequestRepo.findById(1)).thenReturn(Optional.of(expectedRequest)); // error

        // when
        ActivityRequest actualRequest = underTestRequestRepo.findById(1).get();

        // then
        assertThat(actualRequest).isEqualTo(expectedRequest);
    }

    @Test
    void idShouldNotFindAnyRequestByUserIdAndActivityId() {
        // given nothing

        // when
        Optional<ActivityRequest> opt = underTestRequestRepo.findById(1);
        boolean isEmpty = opt.isEmpty();

        // then
        assertThat(isEmpty).isTrue();
    }
}