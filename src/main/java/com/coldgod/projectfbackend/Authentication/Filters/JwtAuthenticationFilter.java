package com.coldgod.projectfbackend.Authentication.Filters;

import com.coldgod.projectfbackend.Authentication.Models.Token;
import com.coldgod.projectfbackend.Authentication.Models.User;
import com.coldgod.projectfbackend.Authentication.Repositories.TokenRepository;
import com.coldgod.projectfbackend.Authentication.Services.UserService;
import com.coldgod.projectfbackend.Authentication.Utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private final UserService userService;

    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;

        //Make sure the "Authorization" header comes with a Bearer Token
        if(StringUtils.hasText(header) && header.startsWith("Bearer "))
        {
            //If true:
            //Extract the token from the header
            token = header.substring(7);

            //Extract the username from the token
            username = jwtUtils.extractUsername(token);
        }

        //Check if the user is currently authenticated
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            //If not:
            //Fetch the user from database
            UserDetails userDetails = userService.loadUserByUsername(username);

            boolean isTokenValidInDatabase = tokenRepository.findByToken(token)
                    .map(tok -> tok.isExpired() == false)
                    .orElseThrow();

            //Make sure the JWT token is valid
            if(jwtUtils.isValid(token, userDetails) && isTokenValidInDatabase)
            {
                //If valid:
                //Create a UsernamePasswordAuthenticationToken from the userDetails above
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                //Set details for the usernamePasswordAuthenticationToken
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Set the usernamePasswordAuthenticationToken to the context of the SecurityContextHolder
                //in order to keep the user authenticated

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
