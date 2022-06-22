package com.demo.chatting.api.command.room;

import com.demo.chatting.api.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class GetAllRoomCommand {
    private Long id;
    private String roomName;
    private Long ownerId;
    private Long maxParticipants;
    private LocalDateTime modifiedAt;

    public GetAllRoomCommand(Room room) {
        this.id = room.getId();
        this.roomName = room.getRoomName();
        this.ownerId = room.getOwnerId();
        this.maxParticipants = room.getMaxParticipants();
        this.modifiedAt = room.getModifiedAt();
    }
}
