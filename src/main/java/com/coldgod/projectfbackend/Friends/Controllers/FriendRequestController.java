package com.coldgod.projectfbackend.Friends.Controllers;

import com.coldgod.projectfbackend.Friends.DTOs.FriendMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FriendRequestController {

    @PostMapping("/friend-request")
    public void friendRequest(@RequestBody FriendMessageDTO message)
    {

    }

}
