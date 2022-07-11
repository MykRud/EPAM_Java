package final_task_servlet.main.test.command;

import com.finaltask.org.example.realization.controller.FrontControllerServlet;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MarkTimeCommandTest extends BaseTest{

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

    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() throws IOException, ServletException {

        when(config.getServletContext()).thenReturn(context);

        when(session.getAttribute("authUser")).thenReturn(user);
        when(request.getSession()).thenReturn(session);
        when(request.getSession(false)).thenReturn(session);
        when(request.getSession(false).getAttribute("authUser")).thenReturn(user);

        when(request.getParameter("activity_id")).thenReturn("1");
        when(request.getParameter("days")).thenReturn("2");
        when(request.getParameter("hours")).thenReturn("7");
        when(request.getParameter("minutes")).thenReturn("30");

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/pages/markTime");

        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, atLeastOnce()).getSession(false);
        verify(request, times(2)).getParameter("activity_id");
        verify(request, times(2)).getParameter("days");
        verify(request, times(2)).getParameter("hours");
        verify(request, times(2)).getParameter("minutes");
    }

}
