package com.lauparr.service;

import com.google.common.collect.Maps;
import com.lauparr.configuration.security.CustomUserService;
import com.lauparr.dto.security.LoginDTO;
import com.lauparr.model.User;
import com.lauparr.utils.JwtUtils;
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

    @Autowired
    private CustomUserService customUserService;

    public User authenticate(LoginDTO loginDTO, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication != null) {
            User user = (User) authentication.getPrincipal();
            // Récupération de l'utilisateur après authentication
            List<String> autorities = null;

            if (user != null && user.getProfile() != null) {
                autorities = user.getProfile().getRoles().stream().map(role -> role.getAuthority()).collect(Collectors.toList());
            }

            Map parameters = Maps.newHashMap();
            parameters.put("autorities", autorities);

            response.setHeader(JwtUtils.JWT_TOKEN_ACCESS, JwtUtils.getToken(user.getUsername(), parameters));

            return user;
        }
        return null;
    }

}
