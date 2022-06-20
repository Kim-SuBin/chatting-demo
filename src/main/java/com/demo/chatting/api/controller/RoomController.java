package com.demo.chatting.api.controller;

import com.demo.chatting.api.dto.ApiResponse;
import com.demo.chatting.api.dto.RoomDto;
import com.demo.chatting.api.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @PostMapping("")
    public ApiResponse<Long> createRoom(@RequestBody @Valid RoomDto.CreateRoom dto) {
        log.info("방 생성");
        return ApiResponse.success(roomService.createRoom(dto));
    }
}
