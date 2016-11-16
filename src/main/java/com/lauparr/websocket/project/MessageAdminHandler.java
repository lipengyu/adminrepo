package com.lauparr.websocket.project;

import jersey.repackaged.com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lauparr on 15/11/2016.
 */
@Component
public class MessageAdminHandler extends TextWebSocketHandler {

    Map<String, WebSocketSession> sessions = Maps.newConcurrentMap();

    public void sendMessage(String message) {
        sessions.values().stream().forEach(session -> {
            try {
                if (session != null && session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        sendMessage(message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.sessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        this.sessions.remove(session.getId());
    }
}
