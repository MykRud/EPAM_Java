package final_task_spring.main.java.com.spring_final.SpringFinalProject.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

/**
 * Custom encoding filter
 *
 * @author Misha Rudyk
 * @see ServletRequest
 * @see ServletResponse
 */
@WebFilter(urlPatterns = "/*", dispatcherTypes = {DispatcherType.REQUEST})
@Order(Ordered.LOWEST_PRECEDENCE - 3)
public class EncodingFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");

        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
