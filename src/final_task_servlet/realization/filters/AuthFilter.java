package final_task_servlet.realization.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {

            HttpServletRequest httpRequest = (HttpServletRequest) request;

            String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
            LOGGER.info("Requested URI: {}", path);

            if (path.startsWith("/pages/admin/")) {
                chain.doFilter(request, response);
                return;
            }

            HttpSession session = httpRequest.getSession(false);

            boolean isLoggedIn = (session != null && session.getAttribute("authUser") != null);

            String loginURI = httpRequest.getContextPath() + "/pages/login.jsp";
            boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
            boolean isLoginPage = httpRequest.getRequestURI().endsWith("login");

            boolean isRegPage = httpRequest.getRequestURI().endsWith("registration");



            if (isLoggedIn && (isLoginRequest || isLoginPage)) {
                // the user is already logged in and he's trying to login again
                // then forward to the homepage
                httpRequest.getRequestDispatcher("/").forward(request, response);
            } else if (!isLoggedIn && !isRegPage) {
                // the user is not logged in, and the requested page requires
                // authentication, then forward to the login page
                String loginPage = "/pages/login";
                RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
                dispatcher.forward(request, response);
            } else {
                // for other requested pages that do not require authentication
                // or the user is already logged in, continue to the destination
                chain.doFilter(request, response);
            }
        }
}
