package com.demo.chatting.api.service;

import com.demo.chatting.api.command.response.EmptyJsonResponse;
import com.demo.chatting.api.command.room.CreateRoomCommand;
import com.demo.chatting.api.command.room.GetAllRoomCommand;
import com.demo.chatting.api.command.room.UpdateRoomCommand;
import com.demo.chatting.api.domain.Member;
import com.demo.chatting.api.domain.Room;
import com.demo.chatting.api.exception.CustomException;
import com.demo.chatting.api.exception.ErrorCode;
import com.demo.chatting.api.repository.MemberRepository;
import com.demo.chatting.api.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createRoom(CreateRoomCommand command) {
        Member member = memberRepository.findById(command.getOwnerId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
        Room room = new Room(command.getRoomName(), command.getOwnerId(), command.getMaxParticipants(), member);
        roomRepository.save(room);
        return room.getId();
    }

    public List<GetAllRoomCommand> getAllRoom() {
        List<Room> rooms = roomRepository.findAll();
        List<GetAllRoomCommand> allRoomCommands = new ArrayList<>();

        for (Room room : rooms) {
            GetAllRoomCommand getAllRoomCommand = new GetAllRoomCommand(room);
            allRoomCommands.add(getAllRoomCommand);
        }

        return allRoomCommands;
    }

    public Room getRoom(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ROOM));
    }

    @Transactional
    public EmptyJsonResponse deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
        return null;
    }

    @Transactional
    public EmptyJsonResponse updateRoom(Room room, UpdateRoomCommand command) {
        if (command.getRoomName() != null) room.setRoomName(command.getRoomName());
        if (command.getOwnerId() != null) {
            Member member = memberRepository.findById(command.getOwnerId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
            if (!room.getRoomMembers().contains(member)) throw new CustomException(ErrorCode.NOT_FOUND_MEMBER_IN_ROOM);
            room.setOwnerId(command.getOwnerId());
        }
        if (command.getMaxParticipants() != null) room.setMaxParticipants(command.getMaxParticipants());

        return null;
    }
}
