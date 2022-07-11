package final_task_servlet.main.test.command;

import com.finaltask.org.example.realization.controller.FrontControllerServlet;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
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

    @Mock
    HttpSession session;

    @Mock
    BusinessServiceImpl businessService;

    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteIncorrectUser() throws IOException, ServletException {

        when(config.getServletContext()).thenReturn(context);

        when(request.getParameter("username")).thenReturn("bruce");
        when(request.getParameter("password")).thenReturn(BCrypt.hashpw("batman", BCrypt.gensalt(10)));

        when(request.getSession()).thenReturn(session);
        when(request.getSession(false)).thenReturn(session);

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/login");
        when(request.getContextPath()).thenReturn("/Final_Task");
        when(request.getServletPath()).thenReturn("");

        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, times(1)).getParameter("username");
        verify(request, times(1)).getParameter("password");
        verify(request, times(2)).getSession();
        verify(response).sendRedirect("/Final_Task/loginDisplay?s=0");
    }

    @Test
    public void testExecuteIncorrectPassword() throws IOException, ServletException {

        when(config.getServletContext()).thenReturn(context);

        when(request.getParameter("username")).thenReturn("batman");
        when(request.getParameter("password")).thenReturn(BCrypt.hashpw("batman", BCrypt.gensalt(10)));

        when(request.getSession()).thenReturn(session);
        when(request.getSession(false)).thenReturn(session);

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/login");
        when(request.getContextPath()).thenReturn("/Final_Task");
        when(request.getServletPath()).thenReturn("");

        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, times(1)).getParameter("username");
        verify(request, times(1)).getParameter("password");
        verify(request, times(2)).getSession();
        verify(response).sendRedirect("/Final_Task/loginDisplay?s=0");
    }

    @Test
    public void testExecuteCorrectUser() throws IOException, ServletException {

        createUser();

        when(config.getServletContext()).thenReturn(context);

        when(request.getParameter("username")).thenReturn("brucewayne");
        when(request.getParameter("password")).thenReturn("MR!QAZ2wsx");

        when(request.getSession()).thenReturn(session);
        when(request.getSession(false)).thenReturn(session);

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/login");
        when(request.getContextPath()).thenReturn("/Final_Task");
        when(request.getServletPath()).thenReturn("");

        //BusinessServiceImpl businessService = new BusinessServiceImpl();
        //        User user = businessService.findUser(username);


        //when(businessService.findUser("admin")).thenReturn(user);

        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, times(1)).getParameter("username");
        verify(request, times(1)).getParameter("password");
        verify(request, times(2)).getSession();
        verify(response).sendRedirect("/Final_Task/index");
    }

}
