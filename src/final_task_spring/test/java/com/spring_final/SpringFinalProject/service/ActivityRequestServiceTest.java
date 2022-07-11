package final_task_spring.test.java.com.spring_final.SpringFinalProject.service;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.ActivityRequest;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.repo.ActivityDaoRep;
import com.spring_final.SpringFinalProject.repo.ActivityRequestDaoRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ActivityRequestServiceTest {

    @Mock
    private ActivityRequestDaoRep requestRepo;
    @Mock
    private ActivityDaoRep activityRepo;

    private ActivityRequestService underTestActivityService;

    @BeforeEach
    void setUp() {
        underTestActivityService = new ActivityRequestService();
        underTestActivityService.setActivityDao(activityRepo);
        underTestActivityService.setRequestDao(requestRepo);
    }

    @Test
    void addRequest() {
        // given
        Activity activity = new Activity(null, "Football", "Active", "Playing football", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());
        User user = new User(null, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        ActivityRequest request = new ActivityRequest();
        request.setStatus("Pending");
        request.setAction("Add");
        request.setUser(user);
        request.setActivity(activity);

        // when
        underTestActivityService.addRequest(request);

        ArgumentCaptor<ActivityRequest> requestArgumentCaptor = ArgumentCaptor.forClass(ActivityRequest.class);
        verify(requestRepo).save(requestArgumentCaptor.capture());
        ActivityRequest capturedRequest = requestArgumentCaptor.getValue();
        assertThat(capturedRequest).isEqualTo(request);
    }

    @Test
    void getRequest() {
        // when
        underTestActivityService.getRequests();

        // then
        verify(requestRepo).findAll();
    }

    @Test
    void getNumberOfRequests() {
        // when
        underTestActivityService.getNumberOfRequests();

        // then
        verify(requestRepo).count();
    }

    @Test
    void makeAddRequest() {
        // given
        Activity activity = new Activity(null, "Football", "Active", "Playing football", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());
        User user = new User(null, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        ActivityRequest request = new ActivityRequest();
        request.setStatus("Pending");
        request.setAction("Add");
        request.setUser(user);
        request.setActivity(activity);

        // when
        underTestActivityService.makeAddRequest(user, activity);

        // ArgumentCaptor<ActivityRequest> requestArgumentCaptor = ArgumentCaptor.forClass(ActivityRequest.class);
        verify(requestRepo).findByUserIdAndActivityId(null, null);
        // ActivityRequest capturedRequest = requestArgumentCaptor.getValue();
        //assertThat(capturedRequest).isEqualTo(request);
    }

    @Test
    void makeCompleteRequest() {
        // given
        Activity activity = new Activity(null, "Football", "Active", "Playing football", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());
        User user = new User(null, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        ActivityRequest request = new ActivityRequest();
        request.setStatus("Pending");
        request.setAction("Add");
        request.setUser(user);
        request.setActivity(activity);

        // when
        underTestActivityService.makeCompleteRequest(user, activity);

        //ArgumentCaptor<ActivityRequest> requestArgumentCaptor = ArgumentCaptor.forClass(ActivityRequest.class);
        verify(requestRepo).findByUserIdAndActivityId(null, null);
        //ActivityRequest capturedRequest = requestArgumentCaptor.getValue();
        //assertThat(capturedRequest).isEqualTo(request);
    }

    @Test
    void approveRequest() {
        // given
        Activity activity = new Activity(null, "Football", "Active", "Playing football", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());
        User user = new User(null, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        ActivityRequest request = new ActivityRequest();
        request.setStatus("Pending");
        request.setAction("Add");
        request.setUser(user);
        request.setActivity(activity);

        // when
        underTestActivityService.approveRequest(request);

        ArgumentCaptor<ActivityRequest> requestArgumentCaptor = ArgumentCaptor.forClass(ActivityRequest.class);
        verify(requestRepo).save(requestArgumentCaptor.capture());
        ActivityRequest capturedRequest = requestArgumentCaptor.getValue();
        assertThat(capturedRequest).isEqualTo(request);
    }

    @Test
    void completeRequest() {
        // given
        Activity activity = new Activity(null, "Football", "Active", "Playing football", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());
        User user = new User(null, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        ActivityRequest request = new ActivityRequest();
        request.setStatus("Pending");
        request.setAction("Add");
        request.setUser(user);
        request.setActivity(activity);

        // when
        underTestActivityService.completeRequest(request);

        ArgumentCaptor<ActivityRequest> requestArgumentCaptor = ArgumentCaptor.forClass(ActivityRequest.class);
        verify(requestRepo).save(requestArgumentCaptor.capture());
        ActivityRequest capturedRequest = requestArgumentCaptor.getValue();
        assertThat(capturedRequest).isEqualTo(request);

    }

    @Test
    void rejectRequest() {
        // given
        Activity activity = new Activity(null, "Football", "Active", "Playing football", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());
        User user = new User(null, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        ActivityRequest request = new ActivityRequest();
        request.setStatus("Pending");
        request.setAction("Add");
        request.setUser(user);
        request.setActivity(activity);

        // when
        underTestActivityService.rejectRequest(request);

        ArgumentCaptor<ActivityRequest> requestArgumentCaptor = ArgumentCaptor.forClass(ActivityRequest.class);
        verify(requestRepo).save(requestArgumentCaptor.capture());
        ActivityRequest capturedRequest = requestArgumentCaptor.getValue();
        assertThat(capturedRequest).isEqualTo(request);
    }

    @Test
    void getRequests() {
        // when
        underTestActivityService.getRequests();

        // then
        verify(requestRepo).findAll();
    }

    @Test
    void getRequestsInLimit() {
        // when
        assertThatThrownBy(() -> {
            underTestActivityService.getRequestsInLimit(0, 5);
        }).isInstanceOf(NullPointerException.class);
    }
}