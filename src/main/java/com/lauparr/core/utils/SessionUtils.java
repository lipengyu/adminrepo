package com.lauparr.core.utils;

import com.lauparr.app.model.User;
import com.lauparr.core.configuration.security.CustomHttpSessionEventPublisher;
import com.lauparr.core.dto.security.SessionHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lauparr on 21/11/2016.
 */
@Component
public class SessionUtils {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private CustomHttpSessionEventPublisher sessionEventPublisher;

    public void registerNewSession(HttpSession session, String token) {
        sessionEventPublisher.getMapTokenSession().put(session.getId(), new SessionHttp(token, session));
        sessionRegistry.registerNewSession(session.getId(), getUserSession());
    }

    public void unregisterSession(String sessionId) {
        SessionHttp sessionHttp = sessionEventPublisher.getMapTokenSession().get(sessionId);
        sessionHttp.getSession().invalidate();
    }

    public List getUsers() {
        return sessionRegistry.getAllPrincipals();
    }

    public boolean isConnected(String token) {
        return sessionEventPublisher.getMapTokenSession().values().stream().filter(sessionHttp -> sessionHttp.getToken().equals(token)).count() > 0;
    }

    public User getUserSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal.getClass().equals(User.class)) {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return null;
    }

}
