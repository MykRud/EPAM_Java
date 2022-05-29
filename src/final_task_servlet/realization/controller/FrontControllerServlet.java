package final_task_servlet.realization.controller;

import com.finaltask.org.example.realization.command.Command;
import com.finaltask.org.example.realization.command.IndexCommand;
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

public class FrontControllerServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.request = req;
        this.response = resp;
        start();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.request = req;
        this.response = resp;
        start();
    }

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

    private void forwardPage(String currentPage) throws IOException, ServletException {

    }



}
