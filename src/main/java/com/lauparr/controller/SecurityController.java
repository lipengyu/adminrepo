package com.lauparr.controller;

import com.google.common.collect.Maps;
import com.lauparr.annotation.Restful;
import com.lauparr.dto.security.LoginDTO;
import com.lauparr.model.User;
import com.lauparr.service.AuthenticationService;
import com.lauparr.utils.JwtUtils;
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

    @Restful
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object getCustomerByPassword(@RequestBody Map body, HttpServletResponse response) throws Exception {
        LoginDTO loginDTO = new LoginDTO((String) body.get("login"), (String) body.get("password"));
        User user = authenticationService.authenticate(loginDTO, response);
        Map result = Maps.newHashMap();
        result.put("user", user);
        result.put("token", response.getHeader(JwtUtils.JWT_TOKEN_ACCESS));
        return result;
    }

}
