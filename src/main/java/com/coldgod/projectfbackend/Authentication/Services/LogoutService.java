package com.coldgod.projectfbackend.Authentication.Services;

import com.coldgod.projectfbackend.Authentication.Models.Token;
import com.coldgod.projectfbackend.Authentication.Repositories.TokenRepository;
import com.coldgod.projectfbackend.Authentication.Utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final JwtUtils jwtUtils;

    private final UserService userService;

    private final TokenRepository tokenRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {

        String header = request.getHeader("Authorization");
        String token = null;

        //Make sure the "Authorization" header comes with a Bearer Token
        if(StringUtils.hasText(header) && header.startsWith("Bearer "))
        {
            //If true:
            //Extract the token from the header
            token = header.substring(7);
        }

        //Check if the user is currently authenticated
        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            //If not:
            Token storedToken = tokenRepository.findByToken(token).orElseThrow();

            storedToken.setExpired(true);

            tokenRepository.save(storedToken);
        }
    }
}
