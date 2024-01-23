package com.coldgod.projectfbackend.UserInformation.Repositories;

import com.coldgod.projectfbackend.Authentication.Models.User;
import com.coldgod.projectfbackend.UserInformation.Models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserInfoRepository
        extends JpaRepository<UserInfo, Long>
{
    Optional<UserInfo> findByUser(User user);
}
