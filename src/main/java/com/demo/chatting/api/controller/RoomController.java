package com.demo.chatting.api.controller;

import com.demo.chatting.api.command.response.ApiResponse;
import com.demo.chatting.api.command.response.EmptyJsonResponse;
import com.demo.chatting.api.command.room.CreateRoomCommand;
import com.demo.chatting.api.command.room.GetAllRoomCommand;
import com.demo.chatting.api.command.room.UpdateRoomCommand;
import com.demo.chatting.api.domain.Room;
import com.demo.chatting.api.exception.CustomException;
import com.demo.chatting.api.exception.ErrorCode;
import com.demo.chatting.api.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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
    public ApiResponse<List<GetAllRoomCommand>> getAllRoom() {
        log.info("모든 방 조회");
        return ApiResponse.success(roomService.getAllRoom());
    }

    @GetMapping("/{roomId}")
    public ApiResponse<Room> getRoom(@PathVariable Long roomId) {
        log.info("특정 방 조회");
        return ApiResponse.success(roomService.getRoom(roomId));
    }

    @DeleteMapping("{roomId}")
    public ApiResponse<EmptyJsonResponse> deleteRoom(@PathVariable Long roomId, @RequestParam Long memberId) {
        log.info("특정 방 삭제");
        Room room = roomService.getRoom(roomId);
        if (!Objects.equals(memberId, room.getOwnerId())) {
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        }
        return ApiResponse.success(roomService.deleteRoom(roomId));
    }

    @PutMapping("{roomId}")
    public ApiResponse<EmptyJsonResponse> updateRoom(@PathVariable Long roomId, @RequestParam Long memberId,
                                                     @RequestBody @Valid UpdateRoomCommand command) {
        log.info("특정 방 수정");
        Room room = roomService.getRoom(roomId);
        if (!Objects.equals(memberId, room.getOwnerId())) {
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        }
        return ApiResponse.success(roomService.updateRoom(room, command));
    }
}
