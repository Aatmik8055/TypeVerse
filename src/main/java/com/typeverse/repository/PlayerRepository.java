package com.typeverse.repository;

import com.typeverse.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // 1. Look up a player session by their public playerId (e.g. "p-8a1f")
    Optional<Player> findByPlayerId(String playerId);

    // 2. Fetch all participants in a room in chronological join order
    List<Player> findByRoomIdOrderByJoinedAtAsc(Long roomId);

    // 3. Fast count check to see if the room has reached capacity
    long countByRoomId(Long roomId);
}
