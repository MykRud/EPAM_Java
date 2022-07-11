package final_task_spring.test.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Login.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class LoginTest {

    private MockMvc mvc;

    @InjectMocks
    private Login loginController;

    @BeforeEach
    void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(loginController).setRemoveSemicolonContent(false)
                .setViewResolvers(viewResolver).build();
    }

    @WithUserDetails("qwerty")
    @Test
    void login() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}