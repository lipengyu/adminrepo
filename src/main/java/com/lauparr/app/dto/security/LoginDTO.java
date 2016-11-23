package com.lauparr.app.dto.security;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lauparr on 17/11/2016.
 */
public class LoginDTO {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
