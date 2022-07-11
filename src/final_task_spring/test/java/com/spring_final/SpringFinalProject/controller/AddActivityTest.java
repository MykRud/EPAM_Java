package final_task_spring.test.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import com.spring_final.SpringFinalProject.service.ActivityService;
import com.spring_final.SpringFinalProject.service.TypeOfActivityService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AddActivity.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class AddActivityTest {

    private MockMvc mvc;

    @MockBean
    private ActivityService activityService;

    @MockBean
    private TypeOfActivityService typeOfActivityService;

    @InjectMocks
    private AddActivity addActivityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(addActivityController).setRemoveSemicolonContent(false).build();
    }

    @WithUserDetails("admin")
    @Test
    void addActivityPost() throws Exception {
        Activity activity1 = new Activity(1, "Basketball", "Active", "Playing basketball", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());

        TypeOfActivity physical = new TypeOfActivity(1, "Physical", new HashSet<>());
        TypeOfActivity job = new TypeOfActivity(1, "Job", new HashSet<>());

        List<TypeOfActivity> types = new ArrayList<>();
        types.add(physical);
        types.add(job);

        activity1.setType(physical);

        physical.getActivities().add(activity1);

        when(typeOfActivityService.getType("Physical")).thenReturn(physical);

        mvc.perform(MockMvcRequestBuilders.post("/admin/activitiesAdd")
                .flashAttr("activity", activity1))
                .andExpect(status().is3xxRedirection()) // TODO: find out why client_error (400)
                .andExpect(view().name("redirect:/admin/addActivityDisplay?s=1"));

        verify(typeOfActivityService, times(1)).getTypes();
        verify(typeOfActivityService, times(1)).getType("Physical");
        verify(activityService, times(1)).addActivity(activity1);
        verifyNoMoreInteractions(activityService);
        verifyNoMoreInteractions(typeOfActivityService);

        // TODO: add captured
    }
}
