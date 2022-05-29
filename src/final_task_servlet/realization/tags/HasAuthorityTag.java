package final_task_servlet.realization.tags;

import com.finaltask.org.example.realization.model.Authority;
import com.finaltask.org.example.realization.model.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Objects;

public class HasAuthorityTag extends TagSupport {
    private String authority = "";

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public int doStartTag() throws JspException {

        User authUser = (User) pageContext.getSession().getAttribute("authUser");

        if (Objects.nonNull(authUser) && authUser.getAuthorities().contains(Authority.valueOf(authority))) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}



