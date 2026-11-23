package com.typeverse.repository;

import com.typeverse.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    // 1. Used when players enter a room code in the UI to join
    Optional<Room> findByRoomCode(String roomCode);

    // 2. Used when generating new room codes to ensure zero collisions
    boolean existsByRoomCode(String roomCode);
}
