package final_task_spring.test.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.service.ActivityService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {GetActivity.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class GetActivityTest {

    private MockMvc mvc;

    @MockBean
    private ActivityService activityService;

    @InjectMocks
    private GetActivity getActivityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(getActivityController).setRemoveSemicolonContent(false).build();
    }

    @WithUserDetails("qwerty")
    @Test
    void getActivity() throws Exception {
        Activity activity = new Activity(null, "Football", "Active", "Playing football", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());
        when(activityService.getActivity("Football")).thenReturn(activity);

        mvc.perform(get("/getActivityByName?name=Football"))
                .andExpect(status().isOk())
                .andExpect(view().name("/WEB-INF/pages/activities"))
                .andExpect(model().attribute("activity", activity));

        verify(activityService, times(1)).getActivity("Football");
        verifyNoMoreInteractions(activityService);
    }
}