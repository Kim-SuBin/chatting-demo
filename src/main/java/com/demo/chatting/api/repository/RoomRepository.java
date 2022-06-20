package com.demo.chatting.api.repository;

import com.demo.chatting.api.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
