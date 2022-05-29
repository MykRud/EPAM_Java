package final_task_servlet.test.filter;

import com.finaltask.org.example.realization.filters.AdminAuthenticationFilter;
import com.finaltask.org.example.realization.filters.AuthFilter;
import command.BaseTest;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminAuthFilterTest extends BaseTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    ServletContext context;

    @Mock
    ServletConfig config;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    FilterChain chain;

    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void authTest() throws ServletException, IOException {

        when(config.getServletContext()).thenReturn(context);

        when(session.getAttribute("authUser")).thenReturn(user);
        when(request.getSession(false)).thenReturn(session);
        when(request.getSession(false).getAttribute("authUser")).thenReturn(user);

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        when(request.getRequestURI()).thenReturn("/Final_Task/pages/admin/users");


        AdminAuthenticationFilter filter = new AdminAuthenticationFilter();
        filter.doFilter(request, response, chain);

        verify(request, atLeastOnce()).getSession(false);
    }

}
