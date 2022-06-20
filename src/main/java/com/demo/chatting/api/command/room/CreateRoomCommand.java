package com.demo.chatting.api.command.room;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateRoomCommand {
    private String roomName;
    private Long ownerId;
    private Long maxParticipants;
}
