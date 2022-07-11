package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.TypeOfActivity;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import com.finaltask.org.example.realization.validators.TypeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Add type command with which the user can add types
 *
 * @author Misha Rudyk
 * @see Command
 * @see BusinessServiceImpl
 * @see TypeOfActivity
 * @see com.finaltask.org.example.realization.dao.interfaces.TypeOfActivitiesDao
 */
public class TypesAddCommand extends Command{

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Execute method
     * @return page name with types
     */
    @Override
    public String execute() {

        if(checkForAdmin() != null)
            return checkForAdmin();

        String name = request.getParameter("name");

        BusinessServiceImpl service = new BusinessServiceImpl();

        if(name == null) {
            return "/WEB-INF/pages/admin/add-type.jsp";
        }

        TypeOfActivity type = new TypeOfActivity(name);

        String lang = (String) request.getSession().getAttribute("lang");

        Locale locale = Locale.getDefault();
        if(lang != null)
            locale = Locale.forLanguageTag(lang);

        List<String> errors = TypeValidator.validateState(type, lang);

        if(service.findType(name) != null){
            LOGGER.warn("Type is already added");
            errors.add(ResourceBundle.getBundle("i18n.messages",
                    locale).getString("type.error.added"));
        }

        int successful = 1;

        if(!errors.isEmpty()){
            request.getSession().setAttribute("errors", errors);
            successful = 0;
            return "redirect:/typesAddDisplay?s="+successful;
        }

        service.addType(type);
        LOGGER.info("Adding type...");

        request.setAttribute("s", successful);
        return "redirect:/typesAddDisplay?s="+successful;
    }
}
