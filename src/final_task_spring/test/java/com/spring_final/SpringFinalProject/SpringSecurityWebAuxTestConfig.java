package final_task_spring.test.java.com.spring_final.SpringFinalProject;

import com.spring_final.SpringFinalProject.model.Role;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserPrincipal;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User basicUser = new User();
        basicUser.setFirstName("Misha");
        basicUser.setLastName("Rudyk");
        basicUser.setUsername("qwerty");
        basicUser.setPassword("1234");
        basicUser.getRoles().add(new Role(1, "USER"));

        UserPrincipal principalBasic = new UserPrincipal(basicUser);

        User admin = new User();
        admin.setFirstName("Mike");
        admin.setLastName("Rud");
        admin.setUsername("admin");
        admin.setPassword("1234");
        admin.getRoles().add(new Role(1, "USER"));
        admin.getRoles().add(new Role(2, "ADMIN"));

        UserPrincipal principalAdmin = new UserPrincipal(admin);


        return new InMemoryUserDetailsManager(Arrays.asList(
                principalBasic, principalAdmin
        ));
    }
}