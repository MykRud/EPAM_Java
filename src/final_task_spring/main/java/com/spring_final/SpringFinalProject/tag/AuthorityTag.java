package final_task_spring.main.java.com.spring_final.SpringFinalProject.tag;


import com.spring_final.SpringFinalProject.model.Role;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.repo.UserDaoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Custom tag that allows to check if user has necessary role
 *
 * @author Mish Rudyk
 * @see javax.servlet.jsp.tagext.Tag
 * @see TagSupport
 * @see User
 * @see Role
 */
public class AuthorityTag extends TagSupport {
    private String auth = "";
    private String user_id = "";

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Autowired
    private UserDaoRep userDaoRep;

    @Override
    public int doStartTag() throws JspException {
        evaluateExpressions();
        List<String> roles = new ArrayList<>();
        if (user_id.equals("Me")) {
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            for (SimpleGrantedAuthority authority : authorities) {
                roles.add(authority.getAuthority());
            }
        } else {
            int id = Integer.parseInt(user_id);
            for (Role role : userDaoRep.findById(id).get().getRoles()) {
                roles.add(role.getName());
            }
        }

        if (roles == null)
            return SKIP_BODY;

        char notEquals = auth.charAt(0);
        if (notEquals == '!') {
            auth = auth.substring(1, auth.length());
            for (String role : roles) {
                if (role.equals(auth)) {
                    return SKIP_BODY;
                }
            }
            return EVAL_BODY_INCLUDE;
        } else {
            for (String role : roles) {
                if (role.equals(auth)) {
                    return EVAL_BODY_INCLUDE;
                }
            }
            return SKIP_BODY;
        }
    }

    private void evaluateExpressions() throws JspException {
        try {
            user_id = (String) ExpressionUtil.evalNotNull("authorityCheck", "user_id", user_id, String.class, this, pageContext);
        } catch (NullAttributeException ex) {
            throw new JspException("Attribute value cannot be null for DecimalFormatTag...");
        }
    }
}