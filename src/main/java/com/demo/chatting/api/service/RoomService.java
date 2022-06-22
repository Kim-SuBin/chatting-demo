package com.demo.chatting.api.service;

import com.demo.chatting.api.command.response.EmptyJsonResponse;
import com.demo.chatting.api.command.room.CreateRoomCommand;
import com.demo.chatting.api.command.room.GetAllRoomCommand;
import com.demo.chatting.api.domain.Member;
import com.demo.chatting.api.domain.Room;
import com.demo.chatting.api.exception.CustomException;
import com.demo.chatting.api.exception.ErrorCode;
import com.demo.chatting.api.repository.MemberRepository;
import com.demo.chatting.api.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

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

    public EmptyJsonResponse deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
        return null;
    }
}
