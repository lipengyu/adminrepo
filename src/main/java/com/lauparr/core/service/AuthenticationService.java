package com.lauparr.core.service;

import com.google.common.collect.Maps;
import com.lauparr.app.dto.security.LoginDTO;
import com.lauparr.app.model.Role;
import com.lauparr.app.model.User;
import com.lauparr.core.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lauparr on 17/11/2016.
 */
@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public User authenticate(LoginDTO loginDTO, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword());
        Map details = Maps.newHashMap();
        details.put("hash", true);
        authenticationToken.setDetails(details);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication != null) {
            User user = (User) authentication.getPrincipal();
            String token = getToken(user);
            response.setHeader(JwtUtils.JWT_TOKEN_ACCESS, token);
            return user;
        }
        return null;
    }

    public String authenticate(User user) {
        if (user != null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Map details = Maps.newHashMap();
            details.put("hash", false);
            authenticationToken.setDetails(details);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication != null) {
                return getToken(user);
            }
        }
        return null;
    }

    public String getToken(User user) {
        List<String> autorities = null;

        if (user != null && user.getProfile() != null) {
            autorities = user.getProfile().getRoles().stream().map(Role::getAuthority).collect(Collectors.toList());
        }

        Map parameters = Maps.newHashMap();
        parameters.put("id", user.getId());
        parameters.put("username", user.getUsername());
        parameters.put("autorities", autorities);
        return JwtUtils.getToken(null, parameters);
    }

}
