package final_task_spring.test.java.com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import com.spring_final.SpringFinalProject.model.TypeOfActivity;
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

import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AddType.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class AddTypeTest {

    private MockMvc mvc;

    @MockBean
    private TypeOfActivityService typeOfActivityService;

    @InjectMocks
    private AddType addTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(addTypeController).setRemoveSemicolonContent(false).build();
    }

    @Test
    @WithUserDetails("admin")
    void addType() throws Exception {

        TypeOfActivity physical = new TypeOfActivity(1, "Physical", new HashSet<>());

        when(typeOfActivityService.getType("Physical")).thenReturn(physical);

        mvc.perform(MockMvcRequestBuilders.post("/admin/typesAdd")
                        .flashAttr("type", physical))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/typeDisplay?s=0"));

        verify(typeOfActivityService, times(1)).getType("Physical");
        verifyNoMoreInteractions(typeOfActivityService);

    }
}