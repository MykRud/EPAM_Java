package final_task_spring.main.java.com.spring_final.SpringFinalProject.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Configuration for Dispatcher Servlet
 *
 * @author Misha Rudyk
 * @see AbstractAnnotationConfigDispatcherServletInitializer
 */
public class MyFrontController extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ConfigProject.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
