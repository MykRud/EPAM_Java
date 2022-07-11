package final_task_spring.main.java.com.spring_final.SpringFinalProject.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Custom localization filter
 *
 * @author Misha Rudyk
 * @see ServletRequest
 * @see ServletResponse
 */
@WebFilter(urlPatterns = "/*", dispatcherTypes = {DispatcherType.REQUEST})
@Order(Ordered.LOWEST_PRECEDENCE - 4)
@Slf4j
public class LocalizationFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getParameter("lang") != null) {
            String language = request.getParameter("lang");
            log.info("Language has been changed to {}", language);
            request.getSession().setAttribute("lang", language);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
