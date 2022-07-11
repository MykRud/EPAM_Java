package final_task_spring.main.java.com.spring_final.SpringFinalProject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuration of spring security
 *
 * @author Misha Rudyk
 * @see WebSecurityConfigurerAdapter
 * @see AuthenticationProvider
 * @see HttpSecurity
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService service;

    /**
     * Bean for AuthenticationProvider
     *
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider getProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;
    }

    /**
     * Main configuration method.
     * Deal with web path's
     *
     * @param http HttpSecurity object
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests().antMatchers("/login/**", "/home/**", "/registration/**", "/logout/**", "/resources/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/activityRequestAdd/**", "/activityRequestComplete/**",
                        "/activities/**", "/getActivityById/**", "/markTime/**", "/profile/**", "/userProfileUpdate/**", "/error/**")
                .hasAnyAuthority("USER", "ADMIN")
                .and()
                .authorizeRequests().antMatchers("/admin/activitiesAdd/**", "/admin/typesAdd/**",
                        "/admin/activityRequestApprove/**", "/admin/activityDelete/**", "/admin/userDelete/**", "/admin/activitiesRequests/**",
                        "/admin/users/**", "/admin/activityRequestReject/**", "/admin/userUpdate/**", "/admin/setAdmin/**",
                        "/admin/deleteAdmin/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/home").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/WEB-INF/pages/error/403.jsp");
    }

}
