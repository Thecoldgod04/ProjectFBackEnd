package com.coldgod.projectfbackend.UserInformation.Models;

import com.coldgod.projectfbackend.Authentication.Models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_infos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {

    @Id
    @GeneratedValue
    private Long id;

    private int level;

    private int exp;

    private int money;

    @OneToOne(mappedBy = "userInfo")
    private User user;
}
