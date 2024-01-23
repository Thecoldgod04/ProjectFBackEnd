package com.coldgod.projectfbackend.UserInformation.DTOs;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDTO {

    private String username;

    private int level;

    private int exp;

    private int money;

}
