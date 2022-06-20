package com.demo.chatting.api.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateRoomDto {
    private String roomName;
    private Long ownerId;
    private Long maxParticipants;
}
