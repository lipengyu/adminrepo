package com.lauparr.core.configuration.security;

import com.lauparr.core.dto.security.SessionHttp;
import com.lauparr.core.utils.SessionUtils;
import jersey.repackaged.com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Created by lauparr on 21/11/2016.
 */
@Component
public class CustomHttpSessionEventPublisher extends HttpSessionEventPublisher {

    protected Map<String, SessionHttp> mapTokenSession = Maps.newConcurrentMap();
    @Autowired
    private SessionRegistry sessionRegistry;
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private SessionUtils sessionUtils;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss").format(LocalDateTime.now()) + " --- Created: " + event.getSession().getId());
        super.sessionCreated(event);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss").format(LocalDateTime.now()) + " --- Destroyed: " + event.getSession().getId());
        mapTokenSession.remove(event.getSession().getId());
        super.sessionDestroyed(event);
    }

    public Map<String, SessionHttp> getMapTokenSession() {
        return mapTokenSession;
    }
}
