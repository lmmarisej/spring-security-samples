package org.javaboy.based_on_url;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PermissionExpression {
    public boolean checkId(Authentication authentication, Integer userId) {
        if (authentication.isAuthenticated()) {
            return userId % 2 == 0;
        }
        return false;
    }

    public boolean check(HttpServletRequest req) {
        return "1".equals(req.getParameter("username"));
    }
}
