package final_task_servlet.main.java.com.finaltask.org.example.realization.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Localization filter that allows you to view pages in the desired language
 *
 * @see Filter
 * @see FilterChain
 * @see HttpServletRequest
 * @see javax.servlet.http.HttpServletResponse
 *
 * @author Misha Rudyk
 */
public class LocalizationFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if(request.getParameter("lang") != null){

            request.getSession().setAttribute("lang", request.getParameter("lang"));

            LOGGER.info("The language has changed to {}", request.getParameter("lang"));

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
