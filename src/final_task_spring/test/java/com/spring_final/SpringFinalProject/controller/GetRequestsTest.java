package final_task_spring.test.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import com.spring_final.SpringFinalProject.model.ActivityRequest;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {GetRequests.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class GetRequestsTest {

    private MockMvc mvc;

    @MockBean
    private ActivityRequestService requestService;

    @InjectMocks
    private GetRequests getRequestsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(getRequestsController).setRemoveSemicolonContent(false).build();
    }

    @WithUserDetails("admin")
    @Test
    void getActivities() throws Exception {
        ActivityRequest request1 = new ActivityRequest();
        request1.setAction("Add");
        request1.setStatus("Pending");

        ActivityRequest request2 = new ActivityRequest();
        request2.setAction("Complete");
        request2.setStatus("Approved");

        List<ActivityRequest> requests = new ArrayList<>();
        requests.add(request1);
        requests.add(request2);

        when(requestService.getNumberOfRequests()).thenReturn(10);
        when(requestService.getRequestsInLimit(0, 5)).thenReturn(requests);

        mvc.perform(get("/admin/activitiesRequests?page=0&size=5"))
                .andExpect(status().isOk())
                .andExpect(view().name("WEB-INF/pages/admin/activity-requests"))
                .andExpect(model().attribute("requests", requests))
                .andExpect(model().attribute("currentPage", 0))
                .andExpect(model().attribute("pageSize", 5))
                .andExpect(model().attribute("totalPages", 2));

        verify(requestService, times(1)).getNumberOfRequests();
        verify(requestService, times(1)).getRequestsInLimit(0, 5);
        verifyNoMoreInteractions(requestService);
    }
}