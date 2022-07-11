package final_task_spring.test.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import com.spring_final.SpringFinalProject.model.User;
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

import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Profile.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class ProfileTest {

    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private Profile profileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(profileController).setRemoveSemicolonContent(false).build();
    }

    @WithUserDetails("qwerty")
    @Test
    void profile() throws Exception {
        User user = new User(1, "John", "Travolta", "qwerty", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());

        when(userService.getUser("qwerty")).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.post("/profile")
                        .param("activity_id", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", user))
                .andExpect(view().name("/WEB-INF/pages/profile"));

        verify(userService, times(1)).getUser("qwerty");
        verifyNoMoreInteractions(userService);
    }
}