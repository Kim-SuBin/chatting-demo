package com.demo.chatting.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Room extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    private Long ownerId;

    private Long maxParticipants;

    private Long lastMessageId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rm_room_id")
    private List<RoomMember> roomMembers;

    @Builder
    public Room(String roomName, Long ownerId, Long maxParticipants, Long lastMessageId, Member member) {
        this.roomName = roomName;
        this.ownerId = ownerId;
        this.maxParticipants = maxParticipants != null ? maxParticipants : 30;
        this.lastMessageId = lastMessageId;
        this.roomMembers = new ArrayList<>();

        RoomMember roomMember = new RoomMember(this.id, member);
        this.roomMembers.add(roomMember);
    }
}
