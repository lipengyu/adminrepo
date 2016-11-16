package com.lauparr.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by lauparr on 14/11/2016.
 */
public class Project implements Serializable {

    @Id
    private String id;

    private String label;

    private String description;
    
    private String urlGithub;

    public Project(String label, String description, String urlGithub) {
        this.label = label;
        this.description = description;
        this.urlGithub = urlGithub;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlGithub() {
        return urlGithub;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
