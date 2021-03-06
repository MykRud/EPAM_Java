package final_task_servlet.main.test.command;

import com.finaltask.org.example.realization.controller.FrontControllerServlet;
import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.Authority;
import com.finaltask.org.example.realization.model.TypeOfActivity;
import com.finaltask.org.example.realization.model.User;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
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
import java.util.ArrayList;
import java.util.Arrays;

public class ActivityDeleteCommandTest extends BaseTest {

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


        createActivity();

        when(request.getParameter("activity_id")).thenReturn("1");

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/pages/activityDelete");



        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, atLeastOnce()).getSession(false);
        verify(request, times(1)).getParameter("activity_id");
    }
}
