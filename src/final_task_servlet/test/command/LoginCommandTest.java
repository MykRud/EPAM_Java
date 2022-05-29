package final_task_servlet.test.command;

import com.finaltask.org.example.realization.controller.FrontControllerServlet;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
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

public class LoginCommandTest extends BaseTest{

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

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
    public void testExecuteIncorrectUser() throws IOException, ServletException {

        when(config.getServletContext()).thenReturn(context);

        when(request.getParameter("username")).thenReturn("bruce");
        when(request.getParameter("password")).thenReturn(BCrypt.hashpw("batman", BCrypt.gensalt(10)));

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/login");

        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, times(1)).getParameter("username");
        verify(request, times(1)).getParameter("password");
        verify(request, atLeastOnce()).getRequestDispatcher(anyString());
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testExecuteIncorrectPassword() throws IOException, ServletException {

        when(config.getServletContext()).thenReturn(context);

        when(request.getParameter("username")).thenReturn("batman");
        when(request.getParameter("password")).thenReturn(BCrypt.hashpw("batman", BCrypt.gensalt(10)));

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/login");

        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, times(1)).getParameter("username");
        verify(request, times(1)).getParameter("password");
        verify(request, atLeastOnce()).getRequestDispatcher(anyString());
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testExecuteCorrectUser() throws IOException, ServletException {

        when(config.getServletContext()).thenReturn(context);

        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn(BCrypt.hashpw("admin", BCrypt.gensalt(10)));

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/login");

        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, times(1)).getParameter("username");
        verify(request, times(1)).getParameter("password");
        verify(request, atLeastOnce()).getRequestDispatcher(anyString());
        verify(requestDispatcher).forward(request, response);
    }

}
