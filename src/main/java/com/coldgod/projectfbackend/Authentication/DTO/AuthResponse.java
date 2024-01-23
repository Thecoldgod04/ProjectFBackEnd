package com.coldgod.projectfbackend.Authentication.DTO;

import com.coldgod.projectfbackend.API.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse extends BaseResponse {

    private String accessToken;

}
