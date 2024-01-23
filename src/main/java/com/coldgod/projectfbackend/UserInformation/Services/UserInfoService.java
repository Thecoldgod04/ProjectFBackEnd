package com.coldgod.projectfbackend.UserInformation.Services;

import com.coldgod.projectfbackend.Authentication.Models.User;
import com.coldgod.projectfbackend.Authentication.Repositories.UserRepository;
import com.coldgod.projectfbackend.Authentication.Services.UserService;
import com.coldgod.projectfbackend.UserInformation.DTOs.UserInfoDTO;
import com.coldgod.projectfbackend.UserInformation.Models.UserInfo;
import com.coldgod.projectfbackend.UserInformation.Repositories.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    private final UserService userService;

    public UserInfoDTO getUserInfoByUsername(String username)
    {
        User user = userService.loadUserByUsername(username);

        UserInfo info = userInfoRepository.findByUser(user).orElse(null);

        if(info == null)
        {
            info = userInfoRepository.save(
                    UserInfo.builder()
                            .level(0)
                            .exp(0)
                            .money(0)
                            .build()
            );
            user.setUserInfo(info);
            userService.save(user);
        }


        return UserInfoDTO.builder()
                .username(username)
                .level(info.getLevel())
                .exp(info.getExp())
                .money(info.getMoney())
                .build();

//        return null;
    }
}
