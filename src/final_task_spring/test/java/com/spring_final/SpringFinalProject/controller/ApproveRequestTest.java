package final_task_spring.test.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.ActivityRequest;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.ActivityRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ApproveRequest.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class ApproveRequestTest {

    private MockMvc mvc;

    @MockBean
    private ActivityRequestService requestService;

    @InjectMocks
    private ApproveRequest approveRequestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(approveRequestController).setRemoveSemicolonContent(false).build();
    }

    @Test
    @WithUserDetails("admin")
    void approveAddRequest() throws Exception {
        Activity activity = new Activity(null, "Basketball", "Active", "Playing basketball", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());
        User user = new User(1, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());

        ActivityRequest request = new ActivityRequest(1, activity, user, "Add", "Pending");

        when(requestService.getRequest(1)).thenReturn(request);

        mvc.perform(MockMvcRequestBuilders.get("/admin/activityRequestApprove?id=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/activitiesRequests"));

        verify(requestService, times(1)).getRequest(1);
        verify(requestService, times(1)).approveRequest(request);
        verifyNoMoreInteractions(requestService);
    }

    @Test
    @WithUserDetails("admin")
    void approveCompleteRequest() throws Exception {
        Activity activity = new Activity(null, "Basketball", "Active", "Playing basketball", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());
        User user = new User(1, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());

        ActivityRequest request = new ActivityRequest(1, activity, user, "Complete", "Pending");

        when(requestService.getRequest(1)).thenReturn(request);

        mvc.perform(MockMvcRequestBuilders.get("/admin/activityRequestApprove?id=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/activitiesRequests"));

        verify(requestService, times(1)).getRequest(1);
        verify(requestService, times(1)).completeRequest(request);
        verifyNoMoreInteractions(requestService);
    }
}