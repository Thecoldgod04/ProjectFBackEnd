package com.coldgod.projectfbackend.Friends.Services;

import com.coldgod.projectfbackend.Friends.DTOs.FriendMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendRequestService {

    private final SimpMessagingTemplate messagingTemplate;

    public void friendRequest(FriendMessageDTO message)
    {
        //messagingTemplate.convertAndSendToUser();
    }

}
