package final_task_servlet.realization.command;

import com.finaltask.org.example.realization.model.TypeOfActivity;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import com.finaltask.org.example.realization.validators.TypeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TypesAddCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute() {

        if(checkForAdmin() != null)
            return checkForAdmin();

        String name = request.getParameter("name");

        BusinessServiceImpl service = new BusinessServiceImpl();

        if(name == null) {
            return "/WEB-INF/pages/admin/add-type.jsp";
        }

        if(service.findType(name) != null){
            LOGGER.warn("Type is already added");
            return "/WEB-INF/pages/admin/add-type.jsp";
        }

        TypeOfActivity type = new TypeOfActivity(name);

        List<String> errors = TypeValidator.validateState(type);
        if(!errors.isEmpty()){
            request.setAttribute("errors", errors);
            return "/WEB-INF/pages/admin/add-type.jsp";
        }

        service.addType(type);
        LOGGER.info("Adding type...");

        return "redirect:/activities";
    }
}
