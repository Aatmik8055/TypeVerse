package com.typeverse.repository;

import com.typeverse.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {

    // Finds the latest / most recent race round in a room
    Optional<Race> findTopByRoomIdOrderByIdDesc(Long roomId);
}