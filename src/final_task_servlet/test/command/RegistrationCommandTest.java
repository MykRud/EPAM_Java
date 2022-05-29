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
import java.io.IOException;

public class RegistrationCommandTest extends BaseTest{

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
    public void testExecuteWithCorrectUser() throws IOException, ServletException {

        when(config.getServletContext()).thenReturn(context);

        when(request.getParameter("first_name")).thenReturn("Bruce");
        when(request.getParameter("last_name")).thenReturn("Wayne");
        when(request.getParameter("username_reg")).thenReturn("brucewayne");
        when(request.getParameter("password_reg")).thenReturn(BCrypt.hashpw("batman", BCrypt.gensalt(10)));
        when(request.getParameter("age")).thenReturn("50");
        when(request.getParameter("gender")).thenReturn("Male");
        when(request.getParameter("contact")).thenReturn("+380980689690");

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/registration");

        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, times(1)).getParameter("first_name");
        verify(request, times(1)).getParameter("last_name");
        verify(request, times(1)).getParameter("username_reg");
        verify(request, times(1)).getParameter("password_reg");
        verify(request, times(2)).getParameter("age");
        verify(request, times(1)).getParameter("gender");
        verify(request, times(1)).getParameter("contact");
    }

    @Test
    public void testExecuteWithEmptyFirstName() throws IOException, ServletException {

        when(config.getServletContext()).thenReturn(context);

        when(request.getParameter("first_name")).thenReturn(" ");
        when(request.getParameter("last_name")).thenReturn("Wayne");
        when(request.getParameter("username_reg")).thenReturn("brucewayne");
        when(request.getParameter("password_reg")).thenReturn(BCrypt.hashpw("batman", BCrypt.gensalt(10)));
        when(request.getParameter("age")).thenReturn("50");
        when(request.getParameter("gender")).thenReturn("Male");
        when(request.getParameter("contact")).thenReturn("+380980689690");

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/registration");

        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, times(1)).getParameter("first_name");
        verify(request, times(1)).getParameter("last_name");
        verify(request, times(1)).getParameter("username_reg");
        verify(request, times(1)).getParameter("password_reg");
        verify(request, times(2)).getParameter("age");
        verify(request, times(1)).getParameter("gender");
        verify(request, times(1)).getParameter("contact");
        verify(request, atLeastOnce()).getRequestDispatcher(anyString());
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testExecuteWithEmptyLastName() throws IOException, ServletException {

        when(config.getServletContext()).thenReturn(context);

        when(request.getParameter("first_name")).thenReturn("Bruce");
        when(request.getParameter("last_name")).thenReturn(" ");
        when(request.getParameter("username_reg")).thenReturn("brucewayne");
        when(request.getParameter("password_reg")).thenReturn(BCrypt.hashpw("batman", BCrypt.gensalt(10)));
        when(request.getParameter("age")).thenReturn("50");
        when(request.getParameter("gender")).thenReturn("Male");
        when(request.getParameter("contact")).thenReturn("+380980689690");

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/registration");

        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, times(1)).getParameter("first_name");
        verify(request, times(1)).getParameter("last_name");
        verify(request, times(1)).getParameter("username_reg");
        verify(request, times(1)).getParameter("password_reg");
        verify(request, times(2)).getParameter("age");
        verify(request, times(1)).getParameter("gender");
        verify(request, times(1)).getParameter("contact");
        verify(request, atLeastOnce()).getRequestDispatcher(anyString());
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testExecuteWithIncorrectAge() throws IOException, ServletException {

        when(config.getServletContext()).thenReturn(context);

        when(request.getParameter("first_name")).thenReturn("Bruce");
        when(request.getParameter("last_name")).thenReturn("Wayne");
        when(request.getParameter("username_reg")).thenReturn("brucewayne");
        when(request.getParameter("password_reg")).thenReturn(BCrypt.hashpw("batman", BCrypt.gensalt(10)));
        when(request.getParameter("age")).thenReturn("-1");
        when(request.getParameter("gender")).thenReturn("Male");
        when(request.getParameter("contact")).thenReturn("+380980689690");

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/registration");

        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, times(1)).getParameter("first_name");
        verify(request, times(1)).getParameter("last_name");
        verify(request, times(1)).getParameter("username_reg");
        verify(request, times(1)).getParameter("password_reg");
        verify(request, times(2)).getParameter("age");
        verify(request, times(1)).getParameter("gender");
        verify(request, times(1)).getParameter("contact");
        verify(request, atLeastOnce()).getRequestDispatcher(anyString());
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testExecuteWithIncorrectContact() throws IOException, ServletException {

        when(config.getServletContext()).thenReturn(context);

        when(request.getParameter("first_name")).thenReturn("Bruce");
        when(request.getParameter("last_name")).thenReturn("Wayne");
        when(request.getParameter("username_reg")).thenReturn("brucewayne");
        when(request.getParameter("password_reg")).thenReturn(BCrypt.hashpw("batman", BCrypt.gensalt(10)));
        when(request.getParameter("age")).thenReturn("50");
        when(request.getParameter("gender")).thenReturn("Male");
        when(request.getParameter("contact")).thenReturn("Hello");

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn("/Final_Task/registration");

        FrontControllerServlet servlet = new FrontControllerServlet();
        servlet.init(config);
        servlet.doGet(request, response);

        verify(request, times(1)).getParameter("first_name");
        verify(request, times(1)).getParameter("last_name");
        verify(request, times(1)).getParameter("username_reg");
        verify(request, times(1)).getParameter("password_reg");
        verify(request, times(2)).getParameter("age");
        verify(request, times(1)).getParameter("gender");
        verify(request, times(1)).getParameter("contact");
        verify(request, atLeastOnce()).getRequestDispatcher(anyString());
        verify(requestDispatcher).forward(request, response);
    }

}
