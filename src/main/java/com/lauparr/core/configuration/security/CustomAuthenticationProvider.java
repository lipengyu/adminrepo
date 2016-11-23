package com.lauparr.core.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by lauparr on 17/11/2016.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment env;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails user = customUserService.loadUserByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException(env.getProperty("label.bad-login-or-password"));
        }

        boolean passOk = false;
        Map details = (Map) authentication.getDetails();
        if (details.get("hash") != null && (Boolean) details.get("hash")) {
            passOk = passwordEncoder.matches(password, user.getPassword());
        } else {
            passOk = password.equals(user.getPassword());
        }

        if (!passOk) {
            throw new BadCredentialsException(env.getProperty("label.bad-login-or-password"));
        }
        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
