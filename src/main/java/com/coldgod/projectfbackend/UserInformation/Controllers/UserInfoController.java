package com.coldgod.projectfbackend.UserInformation.Controllers;

import com.coldgod.projectfbackend.Authentication.Utils.JwtUtils;
import com.coldgod.projectfbackend.UserInformation.DTOs.UserInfoDTO;
import com.coldgod.projectfbackend.UserInformation.Services.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    private final JwtUtils jwtUtils;

    @GetMapping("/user_info")
    public UserInfoDTO getUserInfo(HttpServletRequest request)
    {
        String header = request.getHeader("Authorization");
        String jwt = null;
        if(header.startsWith("Bearer "))
            jwt = header.substring(7);

        String username = jwtUtils.extractUsername(jwt);

        System.out.println("[UserInfoController]: " + username);
        return userInfoService.getUserInfoByUsername(username);
    }

}
