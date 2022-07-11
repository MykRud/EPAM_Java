package final_task_spring.main.java.com.spring_final.SpringFinalProject.configuration;

import com.spring_final.SpringFinalProject.filter.EncodingFilter;
import com.spring_final.SpringFinalProject.filter.LocalizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * Configuration for Spring project
 *
 * @author Misha Rudyk
 * @see WebMvcConfigurer
 * @see LocaleChangeInterceptor
 * @see ResourceHandlerRegistry
 * @see FilterRegistrationBean
 */
@Configuration
@ComponentScan({"com.spring_final.SpringFinalProject"})
public class ConfigProject implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * Bean for localization
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    /**
     * Method to configure resource handler
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new GzipResourceResolver())
                .addResolver(new PathResourceResolver());
    }

    /**
     * Bean to add encoding filter
     */
    @Bean
    public FilterRegistrationBean<EncodingFilter> encodingFilter() {
        FilterRegistrationBean<EncodingFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new EncodingFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(3);

        return registrationBean;
    }

    /**
     * Bean to add localization filter
     */
    @Bean
    public FilterRegistrationBean<LocalizationFilter> localizationFilter() {
        FilterRegistrationBean<LocalizationFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LocalizationFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(4);

        return registrationBean;
    }
}
