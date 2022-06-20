package com.demo.chatting.api.controller;

import com.demo.chatting.api.command.response.ApiResponse;
import com.demo.chatting.api.command.room.CreateRoomCommand;
import com.demo.chatting.api.command.room.GetAllRoomCommand;
import com.demo.chatting.api.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @PostMapping("")
    public ApiResponse<Long> createRoom(@RequestBody @Valid CreateRoomCommand command) {
        log.info("방 생성");
        return ApiResponse.success(roomService.createRoom(command));
    }

    @GetMapping("")
    public ApiResponse<List<GetAllRoomCommand>> getRoom() {
        log.info("모든 방 조회");
        return ApiResponse.success(roomService.getRoom());
    }
}
