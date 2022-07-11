package final_task_servlet.main.java.com.finaltask.org.example.realization.tags;

import com.finaltask.org.example.realization.model.Authority;
import com.finaltask.org.example.realization.model.User;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * Tag class that allows to check authority
 */
public class AuthorityTag extends TagSupport {
    private String auth = "";

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAuth(){ return auth; }

    /**
     * Main method that checks if user has some authority
     * @return skip body of tag or display it
     */
    @Override
    public int doStartTag() {

        User authUser = (User) pageContext.getSession().getAttribute("authUser");

        if (authUser != null && authUser.getAuthorities().contains(Authority.valueOf(auth))) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}



