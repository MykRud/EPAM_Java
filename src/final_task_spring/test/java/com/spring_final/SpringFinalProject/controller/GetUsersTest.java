package final_task_spring.test.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import com.spring_final.SpringFinalProject.model.Role;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {GetUsers.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class GetUsersTest {

    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private GetUsers getUsersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(getUsersController).setRemoveSemicolonContent(false).build();
    }

    @WithUserDetails("admin")
    @Test
    void getUsers() throws Exception {
        User user1 = new User(1, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        User user2 = new User(2, "Johnny", "Depp", "johnny", "1234", 57, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Role role = new Role(null, "ADMIN");

        when(userService.getUsers()).thenReturn(users);
        when(userService.getRole("ADMIN")).thenReturn(role);

        mvc.perform(get("/admin/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("WEB-INF/pages/admin/users"))
                .andExpect(model().attribute("users", users))
                .andExpect(model().attribute("adminRole", role));

        verify(userService, times(1)).getUsers();
        verify(userService, times(1)).getRole("ADMIN");
        verifyNoMoreInteractions(userService);
    }
}