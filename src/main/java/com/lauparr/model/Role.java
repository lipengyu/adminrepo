package com.lauparr.model;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by lauparr on 17/11/2016.
 */
public class Role implements GrantedAuthority {

    @Id
    private String id;

    private String description;

    public Role() {
    }

    public Role(String code, String description) {
        this.id = code;
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
