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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {GetActivities.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class GetActivitiesTest {

    private MockMvc mvc;

    @MockBean
    private ActivityService activityService;

    @InjectMocks
    private GetActivities getActivitiesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(getActivitiesController).setRemoveSemicolonContent(false).build();
    }

    @WithUserDetails("qwerty")
    @Test
    void getActivities() throws Exception {
        Activity activity1 = new Activity(null, "Basketball", "Active", "Playing basketball", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());
        Activity activity2 = new Activity(null, "Football", "Active", "Playing football", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());

        List<Activity> activities = new ArrayList<>();
        activities.add(activity1);
        activities.add(activity2);

        when(activityService.getActivitiesInLimitByName(5, 0)).thenReturn(activities);
        when(activityService.getNumberOfActivities()).thenReturn(10);

        mvc.perform(get("/activities?page=0&size=5&sort=by-name"))
                .andExpect(status().isOk())
                .andExpect(view().name("WEB-INF/pages/activities"))
                .andExpect(model().attribute("activities", activities))
                .andExpect(model().attribute("currentPage", 0))
                .andExpect(model().attribute("pageSize", 5))
                .andExpect(model().attribute("totalPages", 2))
                .andExpect(model().attribute("sort", "by-name"));

        verify(activityService, times(1)).getActivitiesInLimitByName(5, 0);
        verify(activityService, times(1)).getNumberOfActivities();
        verifyNoMoreInteractions(activityService);
    }
}