package com.lauparr.configuration;

import com.lauparr.websocket.project.MessageAdminHandler;
import com.lauparr.websocket.project.ProjectLogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by lauparr on 15/11/2016.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ProjectLogHandler projectLogHandler;
    @Autowired
    private MessageAdminHandler messageAdminHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(projectLogHandler, "/project_log").setAllowedOrigins("*");
        registry.addHandler(messageAdminHandler, "/message_admin").setAllowedOrigins("*");
    }
}
