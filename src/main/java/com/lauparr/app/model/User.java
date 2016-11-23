package com.lauparr.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by lauparr on 16/11/2016.
 */
public class User implements UserDetails, CredentialsContainer, Authentication {

    @Id
    private String id;

    private String email;

    private String password;

    private String nom;

    private String prenom;

    private LocalDate birthday;

    private boolean authenticated;

    private boolean locked;

    private boolean expired;

    private boolean passwordExpired;

    private boolean enabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @DBRef
    private Profile profile;

    public User() {
        locked = false;
        expired = false;
        passwordExpired = false;
        enabled = true;
        createdAt = LocalDateTime.now();
    }

    public User(String email, String password, String nom, String prenom) {
        this();
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }

    @HandleBeforeSave
    private void handleBeforeSave() {
        updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @JsonIgnore
    @Override
    public Object getCredentials() {
        return password;
    }

    @JsonIgnore
    @Override
    public Object getDetails() {
        return null;
    }

    @JsonIgnore
    @Override
    public Object getPrincipal() {
        return this;
    }

    @JsonIgnore
    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @JsonIgnore
    @Override
    public String getName() {
        return email;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profile.getRoles();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return !passwordExpired;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
