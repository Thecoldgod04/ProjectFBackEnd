package com.coldgod.projectfbackend.Authentication.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.coldgod.projectfbackend.Authentication.Models.User;
import com.coldgod.projectfbackend.Authentication.Properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtProperties jwtProperties;

    private final JwtDecoder jwtDecoder;

    public String generate(String username)
    {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.HOURS)))
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }

    public String extractUsername(String token)
    {
        return jwtDecoder.decode(token).getSubject();
    }

    public boolean isValid(String token, UserDetails user)
    {
        return !jwtDecoder.decode(token).getExpiresAt().before(new Date())
                && extractUsername(token).equals(user.getUsername());
    }
}
