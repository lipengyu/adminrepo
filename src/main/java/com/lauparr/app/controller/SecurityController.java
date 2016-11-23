package com.lauparr.app.controller;

import com.google.common.collect.Maps;
import com.lauparr.app.dto.security.LoginDTO;
import com.lauparr.app.model.User;
import com.lauparr.app.repository.UserRepository;
import com.lauparr.core.annotation.Restful;
import com.lauparr.core.exception.RestException;
import com.lauparr.core.service.AuthenticationService;
import com.lauparr.core.utils.JwtUtils;
import com.lauparr.core.utils.SessionUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by lauparr on 14/11/2016.
 */
@RestController
@RequestMapping("/api/security")
public class SecurityController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private UserRepository userRepository;

    @Restful
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public Object authenticate(@RequestBody Map body, HttpServletRequest request, HttpServletResponse response) throws RestException {
        if (sessionUtils.getUserSession() == null) {
            try {
                LoginDTO loginDTO = new LoginDTO((String) body.get("username"), (String) body.get("password"));
                User user = authenticationService.authenticate(loginDTO, response);
                Map result = Maps.newHashMap();
                String token = response.getHeader(JwtUtils.JWT_TOKEN_ACCESS);
                result.put("user", JwtUtils.verify(token));
                result.put("token", token);
                sessionUtils.registerNewSession(request.getSession(), response.getHeader(JwtUtils.JWT_TOKEN_ACCESS));
                return result;
            } catch (JwtException e) {
                throw new RestException(e);
            }
        } else {
            throw new RestException("Vous êtes déjà connecté");
        }
    }

    @Restful
    @RequestMapping(value = "/already_connected", method = RequestMethod.POST)
    public Object alreadyConnected(HttpServletRequest request, HttpServletResponse response) throws RestException {
        try {
            Claims claims = JwtUtils.verify(request);
            User user = userRepository.findByIdAndEmail((String) claims.get("id"), (String) claims.get("username"));
            if (user != null) {
                String token = authenticationService.authenticate(user);
                sessionUtils.registerNewSession(request.getSession(), token);
                Map result = Maps.newHashMap();
                result.put("user", claims);
                result.put("token", token);
                return result;
            } else {
                throw new RestException("Utilisateur introuvable");
            }
        } catch (JwtException e) {
            throw new RestException(e);
        }
    }

    @Restful
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Object logout(HttpServletRequest request) throws RestException {
        if (sessionUtils.getUserSession() != null) {
            sessionUtils.unregisterSession(request.getSession().getId());
        } else {
            throw new RestException("Vous êtes déjà déconnecté");
        }
        return null;
    }

    @Restful
    @RequestMapping(value = "/principals")
    public Object getPrincipals() {
        return sessionUtils.getUsers();
    }

    @Restful
    @RequestMapping(value = "/connected")
    public Object isConnected(HttpServletRequest request) {
        String token = request.getHeader(JwtUtils.JWT_TOKEN_ACCESS);
        Map map = Maps.newHashMap();
        map.put("connected", token != null && sessionUtils.isConnected(token));
        return map;
    }
}