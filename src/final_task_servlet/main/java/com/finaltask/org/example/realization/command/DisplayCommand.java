package final_task_servlet.main.java.com.finaltask.org.example.realization.command;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class DisplayCommand extends Command{

    protected static ResourceBundle resourceBundle;

    protected static void setResourceBundle(String lang){
        if(lang != null)
            resourceBundle = ResourceBundle.getBundle("i18n.messages", Locale.forLanguageTag(lang));
        else
            resourceBundle = ResourceBundle.getBundle("i18n.messages", Locale.getDefault());
    }


}
