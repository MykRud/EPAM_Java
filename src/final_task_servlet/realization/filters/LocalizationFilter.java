package final_task_servlet.realization.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if(request.getParameter("lang") != null){
            request.getSession().setAttribute("lang", request.getParameter("lang"));
            LOGGER.info("Language has changed to {}", request.getParameter("lang"));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
