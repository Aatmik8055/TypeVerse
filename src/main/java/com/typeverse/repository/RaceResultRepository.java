package com.typeverse.repository;

import com.typeverse.entity.RaceResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {

    // 1. Live race ranking: highest progress first, ties broken by WPM
    List<RaceResult> findByRaceIdOrderByProgressDescWpmDesc(Long raceId);

    // 2. Final podium results: sorted 1st, 2nd, 3rd (Rank 1, 2, 3...)
    List<RaceResult> findByRaceIdOrderByFinishRankAsc(Long raceId);

    // 3. Check if this player already recorded a result in this race (prevents duplicates)
    Optional<RaceResult> findByRaceIdAndPlayerId(Long raceId, String playerId);

    // 4. User profile history: shows a user's past races, newest first
    List<RaceResult> findByUserIdOrderByCreatedAtDesc(Long userId);
}
