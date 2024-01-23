package com.coldgod.projectfbackend.Friends.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendMessageDTO {

    private String sender;

    private String receiver;

    private String content;

    private MessageType messageType;

}
