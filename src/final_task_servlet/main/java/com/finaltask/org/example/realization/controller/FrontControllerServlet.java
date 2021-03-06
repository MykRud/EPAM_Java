package final_task_servlet.main.java.com.finaltask.org.example.realization.controller;

import com.finaltask.org.example.realization.command.Command;
import com.finaltask.org.example.realization.command.UnknownCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The main servlet of the system, which converts HTTP requests to commands,
 *  give request to the appropriate classes and processes the result of these commands
 *
 * @see Command
 * @see HttpServlet
 * @see HttpServletRequest
 * @see HttpServletResponse
 * @see Class
 *
 * @author Misha Rudyk
 */
public class FrontControllerServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * Http Get method handler
     * @param req Http Request
     * @param resp Http Response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.request = req;
        this.response = resp;
        start();
    }

    /**
     * Http Post method handler
     * @param req Http Request
     * @param resp Http Response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.request = req;
        this.response = resp;
        start();
    }

    /**
     * the method that initializes the variables needed to run the command,
     * calls the main command method, and processes the result
     * @throws ServletException
     * @throws IOException
     */
    private void start() throws ServletException, IOException {
        ServletContext context = super.getServletContext();
        Command command;
        command = getCommand();
        command.init(context, request, response);
        String currentPage = command.execute();
        if(currentPage.contains("redirect")){
            String p = request.getContextPath() + request.getServletPath() +
                    currentPage.replace("redirect:", "");
            response.sendRedirect(p);
        } else{
            RequestDispatcher dispatcher = request.getRequestDispatcher(currentPage);
            dispatcher.forward(request, response);
        }
    }

    /**
     * Method that converts HTTP-request to the appropriate command and finds its class
     * @return command class
     */
    private Command getCommand() {
        try {
            String url = request.getRequestURI();

            String typeOfCommand = url.substring(url.lastIndexOf("/") + 1);
            typeOfCommand = typeOfCommand.substring(0, 1).toUpperCase() + typeOfCommand.substring(1);
            LOGGER.info("Selected command: {}", typeOfCommand);
            Class type = Class.forName(String.format(
                    "com.finaltask.org.example.realization.command.%sCommand",
                    typeOfCommand));

            return (Command) type.asSubclass(Command.class)
                    .newInstance();
        } catch (Exception e) {
            LOGGER.warn("Cannot find command");
            return new UnknownCommand();
        }
    }
}
