package com.demo.chatting.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class RoomDto {

    @AllArgsConstructor
    @Getter
    public static class CreateRoom {
        private String roomName;
        private Long ownerId;
        private Long maxParticipants;
    }
}
