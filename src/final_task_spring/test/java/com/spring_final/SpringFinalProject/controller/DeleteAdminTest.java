package final_task_spring.test.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import com.spring_final.SpringFinalProject.model.Role;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DeleteAdmin.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class DeleteAdminTest {

    private MockMvc mvc;

    @MockBean
    private UserService userService;


    @InjectMocks
    private DeleteAdmin deleteAdminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(deleteAdminController).setRemoveSemicolonContent(false).build();
    }

    @WithUserDetails("admin")
    @Test
    void deleteAdmin() throws Exception {

        User user = new User(1, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        Role userRole = new Role(1, "USER");
        Role adminRole = new Role(2, "ADMIN");
        user.getRoles().add(adminRole);
        user.getRoles().add(userRole);

        when(userService.getUser(1)).thenReturn(user);
        when(userService.getRole("ADMIN")).thenReturn(adminRole);

        mvc.perform(MockMvcRequestBuilders.get("/admin/deleteAdmin?user_id=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:users"));

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userService, times(1)).getUser(1);
        verify(userService, times(1)).getRole("ADMIN");
        verify(userService, times(1)).updateUser(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);

        verifyNoMoreInteractions(userService);
    }
}