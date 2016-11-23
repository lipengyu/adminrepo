package com.lauparr.core.dto.security;

import javax.servlet.http.HttpSession;

/**
 * Created by lauparr on 21/11/2016.
 */
public class SessionHttp {

    private String token;

    private HttpSession session;

    public SessionHttp(String token, HttpSession session) {
        this.token = token;
        this.session = session;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
