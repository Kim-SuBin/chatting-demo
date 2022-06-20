package com.demo.chatting.api.service;

import com.demo.chatting.api.domain.Member;
import com.demo.chatting.api.domain.Room;
import com.demo.chatting.api.dto.RoomDto;
import com.demo.chatting.api.exception.CustomException;
import com.demo.chatting.api.exception.ErrorCode;
import com.demo.chatting.api.repository.MemberRepository;
import com.demo.chatting.api.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    public Long createRoom(RoomDto.CreateRoom dto) {
        Member member = memberRepository.findById(dto.getOwnerId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
        Room room = new Room(dto.getRoomName(), dto.getOwnerId(), dto.getMaxParticipants(), 0L, member);
        roomRepository.save(room);
        return room.getId();
    }
}
