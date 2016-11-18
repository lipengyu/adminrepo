package com.lauparr.model;

import com.google.common.collect.Lists;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lauparr on 17/11/2016.
 */
public class Profile {

    @Id
    private String id;

    private String label;

    private String description;

    private boolean root;

    @DBRef
    private List<Role> roles = Lists.newArrayList();

    public Profile() {
    }

    public Profile(String label, String description, boolean root, Role... roles) {
        this.label = label;
        this.description = description;
        this.root = root;
        this.roles = Arrays.asList(roles);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
