package com.coldgod.projectfbackend.Authentication.Controllers;

import com.coldgod.projectfbackend.API.BaseResponse;
import com.coldgod.projectfbackend.Authentication.DTO.AuthRequest;
import com.coldgod.projectfbackend.Authentication.DTO.AuthResponse;
import com.coldgod.projectfbackend.Authentication.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public BaseResponse register(@RequestBody AuthRequest request)
    {
        return authenticationService.register(request);
    }

    @PostMapping(path = "/login", consumes = "application/json")
    public AuthResponse login(@RequestBody AuthRequest request)
    {
        AuthResponse response = authenticationService.login(request);

        //System.out.println(response.getAccessToken());
        return response;
    }
}
