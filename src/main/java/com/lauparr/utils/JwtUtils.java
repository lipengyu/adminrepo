package com.lauparr.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.compression.CompressionCodecs;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class JwtUtils {

    public final static String JWT_TOKEN_ACCESS = "Authorization";

    public final static String JWT_SALT = "ApplicationSalt";

    public static String getToken(final String subject, final Map parameters) {
        return Jwts.builder().setSubject(subject).setClaims(parameters).signWith(SignatureAlgorithm.HS512, JWT_SALT).compressWith(CompressionCodecs.GZIP).compact();
    }

    public static Claims verify(final String token) throws Exception {
        return getClaims(token);
    }

    public static Claims verify(final HttpServletRequest request) throws Exception {
        final String token = request.getHeader(JWT_TOKEN_ACCESS);
        return getClaims(token);
    }

    private static Claims getClaims(final String token) throws Exception {
        try {
            if (token == null) {
                throw new Exception("Aucun token trouvé");
            }
            return Jwts.parser().setSigningKey(JWT_SALT).parseClaimsJws(token).getBody();
        } catch (final SignatureException e) {
            throw new Exception("Le token passé dans l'entête est incorrect.");
        }
    }

}