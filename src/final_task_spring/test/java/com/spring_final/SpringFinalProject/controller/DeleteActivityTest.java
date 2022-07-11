package final_task_spring.test.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DeleteActivity.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class DeleteActivityTest {

    private MockMvc mvc;

    @MockBean
    private ActivityService activityService;

    @MockBean
    private TypeOfActivityService typeOfActivityService;

    @InjectMocks
    private DeleteActivity deleteActivityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(deleteActivityController).setRemoveSemicolonContent(false).build();
    }

    @WithUserDetails("admin")
    @Test
    void deleteActivity() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/admin/activityDelete?activity_id=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/activities"));

        verify(activityService, times(1)).deleteActivity(1);
        verifyNoMoreInteractions(activityService);
    }
}