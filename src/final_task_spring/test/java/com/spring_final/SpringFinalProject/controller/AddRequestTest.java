package final_task_spring.test.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.ActivityRequestService;
import com.spring_final.SpringFinalProject.service.ActivityService;
import com.spring_final.SpringFinalProject.service.UserService;
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
@SpringBootTest(classes = {AddRequest.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class AddRequestTest {

    private MockMvc mvc;

    @MockBean
    private ActivityRequestService requestService;

    @MockBean
    private UserService userService;

    @MockBean
    private ActivityService activityService;

    @InjectMocks
    private AddRequest addRequestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(addRequestController).setRemoveSemicolonContent(false).build();
    }

    @WithUserDetails("qwerty")
    @Test
    void addRequest() throws Exception {
        Activity activity = new Activity(1, "Basketball", "Pending", "Playing basketball", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());
        TypeOfActivity physical = new TypeOfActivity(null, "Physical", new HashSet<>());
        activity.setType(physical);

        User user = new User(1, "John", "Travolta", "qwerty", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());

        when(userService.getUser("qwerty")).thenReturn(user);
        when(activityService.getActivity(1)).thenReturn(activity);

        mvc.perform(MockMvcRequestBuilders.post("/activityRequestAdd")
                        .param("activity_id", String.valueOf(1)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/activities"));

        verify(userService, times(1)).getUser("qwerty");
        verify(activityService, times(1)).getActivity(1);
        verify(requestService, times(1)).makeAddRequest(user, activity);
        verifyNoMoreInteractions(activityService);
    }
}