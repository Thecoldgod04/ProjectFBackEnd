package com.coldgod.projectfbackend.API;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    protected String message;

    protected boolean success;


}
