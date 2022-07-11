package final_task_servlet.main.java.com.finaltask.org.example.realization.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Encoding filter that allows you to form pages in the desired encoding
 *
 * @see javax.servlet.http.HttpServletRequest
 * @see Filter
 *
 * @author Misha Rudyk
 */
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");

        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
