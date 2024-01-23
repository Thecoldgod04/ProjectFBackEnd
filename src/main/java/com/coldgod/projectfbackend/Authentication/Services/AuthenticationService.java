package com.coldgod.projectfbackend.Authentication.Services;

import com.coldgod.projectfbackend.API.BaseResponse;
import com.coldgod.projectfbackend.Authentication.DTO.AuthRequest;
import com.coldgod.projectfbackend.Authentication.DTO.AuthResponse;
import com.coldgod.projectfbackend.Authentication.Models.Token;
import com.coldgod.projectfbackend.Authentication.Models.User;
import com.coldgod.projectfbackend.Authentication.Repositories.TokenRepository;
import com.coldgod.projectfbackend.Authentication.Utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final TokenRepository tokenRepository;

    public BaseResponse register(AuthRequest request)
    {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        //Should throw something like "AlreadyExistException" for later handling
        if(userService.save(user) == null) return new BaseResponse("Username already exist", false);

        //userService.save(user);

        return new BaseResponse("", true);
    }

    public AuthResponse login(AuthRequest request)
    {
        //Authenticate the user from the given info in the request
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        //If authenticated then generate a jwt token and send it back as a response
        String jwt = jwtUtils.generate(request.getUsername());

        //Fetch the user from the database
        User user = userService.loadUserByUsername(request.getUsername());

        //Create a new Token object for that user
        Token token = Token.builder()
                .token(jwt)
                .user(user)
                .isExpired(false)
                .build();

        //Fetch all the valid tokens of that user and set them all to be not valid
        List<Token> allValidTokens = tokenRepository.findAllValidTokensByUserId(user.getId());
        allValidTokens.forEach(t -> {
            t.setExpired(true);
        });

        //Save the new token into the database
        tokenRepository.save(token);

        AuthResponse response = new AuthResponse(jwt);
        response.setMessage("");
        response.setSuccess(true);
        return response;
    }

}
