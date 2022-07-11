package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Users command that allows the admin to view pages with users
 *
 * @author Misha Rudyk
 * @see Command
 * @see com.finaltask.org.example.realization.model.User
 * @see BusinessServiceImpl
 * @see com.finaltask.org.example.realization.dao.interfaces.UserDao
 */
public class UsersCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute method
     * @return page name with users
     */
    @Override
    public String execute() {

        if(checkForAdmin() != null)
            return checkForAdmin();

        int page = 0;
        int size = 5;

        try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            if (request.getParameter("size") != null) {
                size = Integer.parseInt(request.getParameter("size"));
            }
        } catch (NumberFormatException e){
            LOGGER.warn("Cannot parse page or size");
            return "redirect:/unknown";
        }

        BusinessServiceImpl businessService = new BusinessServiceImpl();
        int numberOfUsers = businessService.getNumberOfUsers();
        int totalPages = (int)Math.ceil(numberOfUsers/size);
        request.setAttribute("users", businessService.getUsersInLimit(size, page));
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", size);
        request.setAttribute("totalPages", totalPages);
        return "/WEB-INF/pages/admin/users.jsp";

    }
}
